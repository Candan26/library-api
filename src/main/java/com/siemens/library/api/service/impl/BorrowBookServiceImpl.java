package com.siemens.library.api.service.impl;

import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.entity.ResourceBorrowing;
import com.siemens.library.api.entity.ResourceMember;
import com.siemens.library.api.mapper.ServiceMapper;
import com.siemens.library.api.model.Error;
import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToBorrowBook;
import com.siemens.library.api.model.RequestBodyToReturnBook;
import com.siemens.library.api.repository.BookRepository;
import com.siemens.library.api.repository.BorrowRepository;
import com.siemens.library.api.repository.MembershipRepository;
import com.siemens.library.api.service.BorrowBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static com.siemens.library.api.util.LibraryUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BorrowBookServiceImpl implements BorrowBookService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final ServiceMapper serviceMapper;
    private final MembershipRepository membershipRepository;

    @Override
    public LibraryResponse createNewBorrowing(RequestBodyToBorrowBook request) {
        StringBuilder em = new StringBuilder();
        StringBuilder ec = new StringBuilder();
        // check format error
        if (checkStructureError(request, em, ec)) {
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(em.toString(), ec.toString()));
        }
        // check is person a member of library
        if (!membershipRepository.findById(request.getBookId()).isPresent()) {
            return new LibraryResponse(HttpStatus.NOT_FOUND, FAILED, "", new Error(MEMBER_NOT_FOUND_ERROR, MEMBER_NOT_FOUND_ERROR_CODE));
        }
        Optional<ResourceBook> resourceBookOptional = bookRepository.findById(request.getBookId());
        if (!resourceBookOptional.isPresent()) {
            return new LibraryResponse(HttpStatus.NOT_FOUND, FAILED, "", new Error(BOOK_NOT_FOUND_ERROR, BOOK_NOT_FOUND_ERROR_CODE));
        }
        if (!resourceBookOptional.get().isAvailable()) {
            return new LibraryResponse(HttpStatus.CONFLICT, FAILED, "", new Error(BOOK_IS_NOT_AVAILABLE_EMPTY_ERROR, BOOK_IS_NOT_AVAILABLE_EMPTY_ERROR_CODE));
        }
        // borrow book
        ResourceBorrowing resourceBorrowing = ResourceBorrowing.
                builder().
                bookId(request.getBookId()).
                memberId(request.getMemberId()).
                borrowDate(request.getBorrowDate()).
                dueDate(request.getDueDate()).
                build();
        return new LibraryResponse(HttpStatus.OK, SUCCEED, serviceMapper.borrowingToDto(borrowRepository.save(resourceBorrowing)), null);
    }

    @Override
    public LibraryResponse returnBookInformation(String id, RequestBodyToReturnBook request) {
        if (checkStructureError(id, request)) {
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(BORROWING_NOT_FOUND_ERROR, BORROWING_NOT_FOUND_ERROR_CODE));
        }
        Optional<ResourceBorrowing> resourceBorrowingOptional = borrowRepository.findById(id);
        if(!resourceBorrowingOptional.isPresent()){
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(BORROWING_NOT_FOUND_ERROR, BORROWING_NOT_FOUND_ERROR_CODE));
        }
        Optional<ResourceBook> resourceBookOptional = bookRepository.findById(resourceBorrowingOptional.get().getBookId());
        if(!resourceBookOptional.isPresent()){
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(BOOK_NOT_FOUND_ERROR,BOOK_NOT_FOUND_ERROR_CODE));
        }
        Optional<ResourceMember> resourceMemberOptional = membershipRepository.findById(resourceBorrowingOptional.get().getMemberId());
        if(!resourceMemberOptional.isPresent()){
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(MEMBER_NOT_FOUND_ERROR,MEMBER_NOT_FOUND_ERROR_CODE));
        }
        ResourceBorrowing resourceBorrowing = resourceBorrowingOptional.get();
        resourceBorrowing.setReturnDate(request.getReturnDate());
        return new LibraryResponse(HttpStatus.OK, SUCCEED, serviceMapper.borrowingToDto(borrowRepository.save(resourceBorrowing)), null);
    }

    private boolean checkStructureError(String id, RequestBodyToReturnBook request) {
        boolean hasError = false;
        SimpleDateFormat sdf = new SimpleDateFormat(publishDateFormat);
        if (id == null || request == null || request.getReturnDate() == null) {
            hasError = true;
        }
        try {
            assert request != null;
            sdf.parse(request.getReturnDate());
        } catch (ParseException e) {
            log.error("ParseException", e);
            hasError = true;
        }
        return hasError;
    }


    private boolean checkStructureError(RequestBodyToBorrowBook request, StringBuilder em, StringBuilder ec) {
        boolean hasError = true;
        SimpleDateFormat sdf = new SimpleDateFormat(publishDateFormat);

        if (request == null || request.getBookId() == null || request.getMemberId() == null
                || request.getBorrowDate() == null || request.getDueDate() == null) {
            em.append(REQUIRED_OBJ_MISSING_BORROW_ERROR);
            ec.append(REQUIRED_OBJ_MISSING_BORROW_ERROR_CODE);
        }
        //check name
        else if (request.getBookId().length() > bookIdLength) {
            em.append(NAME_LENGTH_ERROR);
            ec.append(NAME_LENGTH_ERROR_CODE);
        }
        //check surname
        else if (request.getMemberId().length() > memberIdLength) {
            em.append(SURNAME_LENGTH_ERROR);
            ec.append(SURNAME_LENGTH_ERROR_CODE);
        } else {
            // no errror found
            hasError = false;
            //check publishing Date we cannot put inside if else block in
            // order to reduce redundancy of if else block code because of exception thrown on wrong parsing
            int i = 0;
            try {
                sdf.parse(request.getBorrowDate());
                i++;
                sdf.parse(request.getDueDate());
            } catch (ParseException e) {
                log.error("ParseException", e);
                if (i == 0) {
                    em.append(BORROW_DATE_PARSE_ERROR);
                    ec.append(BORROW_DATE_PARSE_ERROR_CODE);
                } else {
                    em.append(PUBLISH_DATE_PARSE_ERROR);
                    ec.append(PUBLISH_DATE_PARSE_ERROR_CODE);
                }
                hasError = true;
            }

        }
        return hasError;
    }
}
