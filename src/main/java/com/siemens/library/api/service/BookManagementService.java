package com.siemens.library.api.service;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateBook;

public interface BookManagementService {
    LibraryResponse createNewBook(RequestBodyToCreateBook bookRepository);
    LibraryResponse queryBooks(String isbn, String title, String author, String category, boolean available);
    LibraryResponse modifyBook(String id, RequestBodyToCreateBook book);
    LibraryResponse findBookById(String id);
    LibraryResponse deleteBookById(String id);
}
