package com.siemens.library.api.service;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateMember;

public interface MembershipService {
    LibraryResponse createNewMember(RequestBodyToCreateMember memberRequest);

    LibraryResponse deleteBookById(String id);

    LibraryResponse findMemberById(String id);

    LibraryResponse queryBooks(String name, String surname, String email, String phoneNumber);
}
