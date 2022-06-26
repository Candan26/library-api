package com.siemens.library.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BookDto {
    private String bookId;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private String publisher;
    private String publishingDate;
    private String edition;
    private boolean available;
}
