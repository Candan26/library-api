package com.siemens.library.api.service.impl;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyAuth;
import com.siemens.library.api.service.AuthService;
import com.siemens.library.api.util.LibrarianJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Value("${token.jwt.expiration.time.second}")
    private long expirationTime;
    private  final LibrarianJwtUtil jwtUtil;
    @Override
    public LibraryResponse getToken(RequestBodyAuth request) {
        return null;
    }
}
