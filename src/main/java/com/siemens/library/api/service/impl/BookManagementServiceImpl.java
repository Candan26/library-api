package com.siemens.library.api.service.impl;


import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.mapper.ServiceMapper;
import com.siemens.library.api.model.Error;
import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateBook;
import com.siemens.library.api.repository.BookRepository;
import com.siemens.library.api.service.BookManagementService;
import com.siemens.library.api.util.LibraryUtil;
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
public class BookManagementServiceImpl implements BookManagementService {
    private final BookRepository bookRepository;
    private final ServiceMapper serviceMapper;
    @Override
    public LibraryResponse createNewBook(RequestBodyToCreateBook bookRequest) {
        StringBuilder em = new StringBuilder();
        StringBuilder ec = new StringBuilder();
        boolean hasError = checkStructureError(bookRequest, em,ec);
        if (hasError) {
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(em.toString(),  ec.toString()));
        }
        ResourceBook book = ResourceBook.builder().
                                isbn(bookRequest.getIsbn()).
                                author(bookRequest.getAuthor()).
                                title(bookRequest.getTitle()).
                                category(bookRequest.getCategory()).
                                publisher(bookRequest.getPublisher()).
                                publishingDate(bookRequest.getPublishingDate()).
                                edition(bookRequest.getEdition()).
                                available(true).
                                build();
        return new LibraryResponse(HttpStatus.OK,SUCCEED, serviceMapper.bookToDto(bookRepository.save(book)),null);
    }

    @Override
    public LibraryResponse queryBooks(String isbn, String title, String author, String category, boolean available) {
        return null;
    }

    @Override
    public LibraryResponse modifyBook(String id, RequestBodyToCreateBook book) {
        Optional<ResourceBook> resourceBookOpt = bookRepository.findById(id);
        StringBuilder em = new StringBuilder();
        StringBuilder ec = new StringBuilder();
        if(!resourceBookOpt.isPresent()){
            return new LibraryResponse(HttpStatus.NOT_FOUND, FAILED, "", new Error(BOOK_NOT_FOUND_ERROR, BOOK_NOT_FOUND_ERROR_CODE));
        }
        ResourceBook resourceBook = resourceBookOpt.get();
        if(checkStructureError(book,em,ec)){
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(em.toString(), ec.toString()));
        }
        resourceBook =  serviceMapper.clone(book,resourceBook);
        return new LibraryResponse(HttpStatus.OK,SUCCEED, serviceMapper.bookToDto(bookRepository.save(resourceBook)),null);
    }

    @Override
    public LibraryResponse findBookById(String id) {
        if(id==null){
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(BOOK_ID_EMPTY_ERROR, BOOK_ID_EMPTY_CODE));

        }
        Optional<ResourceBook> resourceBookOpt = bookRepository.findById(id);
        return resourceBookOpt.map(resourceBook -> new LibraryResponse(HttpStatus.OK, SUCCEED, serviceMapper.bookToDto(resourceBook), null)).orElseGet(() -> new LibraryResponse(HttpStatus.NOT_FOUND, FAILED, "", new Error(BOOK_NOT_FOUND_ERROR, BOOK_NOT_FOUND_ERROR_CODE)));
    }

    @Override
    public LibraryResponse deleteBookById(String id) {
        bookRepository.deleteById(id);
        return new LibraryResponse(HttpStatus.OK,SUCCEED,"book is deleted",null);
    }

    private boolean checkStructureError(RequestBodyToCreateBook book, StringBuilder em, StringBuilder ec) {
        boolean hasError = true;
        SimpleDateFormat sdf = new SimpleDateFormat(publishDateFormat);

        if (book == null || book.getIsbn() == null || book.getTitle() == null || book.getAuthor() == null) {
            em.append(REQUIRED_OBJ_MISSING_BOOK_ERROR);
            ec.append(REQUIRED_OBJ_MISSING_BOOK_ERROR_CODE);
        }
        // check isbn
        else if (!book.getIsbn().matches(LibraryUtil.isbnRegex) || book.getIsbn().length() > isnLength) {
            em.append(ISBN_FORMAT_ERROR);
            ec.append(ISBN_FORMAT_ERROR_CODE);
        }
        //check title
        else if (book.getTitle().length() > titleLength) {
            em.append(TITLE_LENGTH_ERROR);
            ec.append(TITLE_LENGTH_ERROR_CODE);
        }
        //check Author
        else if (book.getAuthor().length() > authorLength) {
            em.append(AUTHOR_LENGTH_ERROR);
            ec.append(AUTHOR_LENGTH_ERROR_CODE);
        }
        //check category
        else if (book.getCategory() != null && book.getCategory().length() > categoryLength) {
            em.append(CATEGORY_LENGTH_ERROR);
            ec.append(CATEGORY_LENGTH_ERROR_CODE);
        }
        //check publisher
        else if (book.getPublisher() != null && book.getPublisher().length() > publisherLength) {
            em.append(PUBLISHER_LENGTH_ERROR);
            ec.append(PUBLISHER_LENGTH_ERROR_CODE);
        }
        //check edition
        else if (book.getEdition() != null && book.getEdition().length() > editionLength) {
            em.append(EDITION_LENGTH_ERROR);
            ec.append(EDITION_LENGTH_ERROR_CODE);
        } else {
            // no errror found
            hasError = false;
            //check publishing Date we cannot put inside if else block in
            // order to reduce redundancy of if else block code because of exception thrown on wrong parsing
            if (book.getPublishingDate() != null) {
                try {
                    sdf.parse(book.getPublishingDate());
                } catch (ParseException e) {
                    log.error("ParseException", e);
                    em.append(PUBLISH_DATE_PARSE_ERROR);
                    ec.append(PUBLISH_DATE_PARSE_ERROR_CODE);
                    hasError = true;
                }
            }
        }
        return hasError;
    }
}
