package com.siemens.library.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class RequestBodyToBorrowBook {
    String bookId;
    String memberId;
    String borrowDate;
    String dueDate;
}
