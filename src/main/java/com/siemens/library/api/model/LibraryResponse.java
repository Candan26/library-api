package com.siemens.library.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class LibraryResponse {
    private HttpStatus status;
    private String returnCode;
    private Object data;
    private  Error error;
}
