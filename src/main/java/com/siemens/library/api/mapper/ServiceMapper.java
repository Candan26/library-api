package com.siemens.library.api.mapper;

import com.siemens.library.api.dto.BookDto;
import com.siemens.library.api.dto.BorrowingDto;
import com.siemens.library.api.dto.MemberDto;
import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.entity.ResourceBorrowing;
import com.siemens.library.api.entity.ResourceMember;
import com.siemens.library.api.model.RequestBodyToCreateBook;

public interface ServiceMapper {
    BookDto bookToDto(ResourceBook book);
    ResourceBook clone(RequestBodyToCreateBook book, ResourceBook resourceBook);
    MemberDto memberToDto(ResourceMember resourceMember);
    BorrowingDto borrowingToDto(ResourceBorrowing resourceBorrowing);
}
