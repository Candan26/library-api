package com.siemens.library.api.model;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class RequestBodyToCreateBook {
    String isbn;
    String title;
    String author;
    String category;
    String publisher;
    String publishingDate;
    String edition;
}
