package com.siemens.library.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class MemberDto {
    private String memberId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String joinDate;
}
