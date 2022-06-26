package com.siemens.library.api.controller;


import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyAuth;
import com.siemens.library.api.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> getAuth(@RequestBody RequestBodyAuth request) {
        try {
            LibraryResponse response = authService.getToken(request);
            if (response.getError() != null) {
                return new ResponseEntity<>(response.getError(), response.getStatus());
            }
            return new ResponseEntity<>(response.getData(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception on ", ex);
            return new ResponseEntity<>("Service Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
