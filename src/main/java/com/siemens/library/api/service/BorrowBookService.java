package com.siemens.library.api.service;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToBorrowBook;
import com.siemens.library.api.model.RequestBodyToReturnBook;

public interface BorrowBookService {
    LibraryResponse createNewBorrowing(RequestBodyToBorrowBook request);

    LibraryResponse returnBookInformation(String id, RequestBodyToReturnBook request);

    LibraryResponse queryBorrowing(String bookId, String memberId, Boolean late, Boolean returned);
}
