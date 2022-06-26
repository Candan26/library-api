package com.siemens.library.api.model;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class RequestBodyAuth {
    private String iss;
    private Long iat;
    private Long exp;
    private String aud;
    private String sub;
    private ArrayList<String>scopes;
}
