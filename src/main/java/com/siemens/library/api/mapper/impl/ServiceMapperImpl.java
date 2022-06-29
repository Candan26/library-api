package com.siemens.library.api.mapper.impl;

import com.siemens.library.api.dto.BookDto;
import com.siemens.library.api.dto.BorrowingDto;
import com.siemens.library.api.dto.MemberDto;
import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.entity.ResourceBorrowing;
import com.siemens.library.api.entity.ResourceMember;
import com.siemens.library.api.mapper.ServiceMapper;
import com.siemens.library.api.model.RequestBodyToCreateBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceMapperImpl implements ServiceMapper {
    @Override
    public BookDto bookToDto(ResourceBook book) {
        return BookDto.builder().
                bookId(book.getId()).
                isbn(book.getIsbn()).
                title(book.getTitle()).
                author(book.getAuthor()).
                category(book.getCategory()).
                publisher(book.getPublisher()).
                edition(book.getEdition()).
                available(book.getAvailable()).
                build();
    }

    @Override
    public ResourceBook clone(RequestBodyToCreateBook book, ResourceBook resourceBook) {
        resourceBook.setIsbn(book.getIsbn());
        resourceBook.setTitle(book.getTitle());
        resourceBook.setAuthor(book.getAuthor());
        resourceBook.setCategory(book.getCategory());
        resourceBook.setPublisher(book.getPublisher());
        resourceBook.setPublishingDate(book.getPublishingDate());
        resourceBook.setEdition(book.getEdition());
        resourceBook.setAvailable(true);
        return resourceBook;
    }

    @Override
    public MemberDto memberToDto(ResourceMember resourceMember) {
        return MemberDto.builder()
                .memberId(resourceMember.getId()).
                name(resourceMember.getName()).
                surname(resourceMember.getSurname()).
                email(resourceMember.getEmail()).
                phoneNumber(resourceMember.getPhoneNumber()).
                joinDate(resourceMember.getJoinDate()).
                build();
    }

    @Override
    public BorrowingDto borrowingToDto(ResourceBorrowing resourceBorrowing) {
        return BorrowingDto.
                builder().
                borrowingId(resourceBorrowing.getId()).
                bookId(resourceBorrowing.getBookId()).
                memberId(resourceBorrowing.getMemberId()).
                borrowDate(resourceBorrowing.getBorrowDate()).
                dueDate(resourceBorrowing.getDueDate()).
                returnDate(resourceBorrowing.getReturnDate()).
                build();
    }
}
