package com.siemens.library.api.service;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateMember;

public interface MembershipService {
    LibraryResponse createNewMember(RequestBodyToCreateMember memberRequest);
}
