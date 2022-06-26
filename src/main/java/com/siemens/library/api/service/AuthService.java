package com.siemens.library.api.service;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyAuth;

public interface AuthService {
    LibraryResponse getToken(RequestBodyAuth request);
}
