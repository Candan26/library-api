package com.siemens.library.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class RequestBodyToCreateMember {
    String name;
    String surname;
    String email;
    String phoneNumber;
    String joinDate;
}
