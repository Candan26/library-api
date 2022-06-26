package com.siemens.library.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BorrowingDto {
    private String borrowingId;
    private String bookId;
    private String memberId;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
}
