package com.siemens.library.api.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceBook {
    @Id
    @Column(name = "ID", nullable = false,length = 100)
    private String id;

    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(name = "BookTitle", length = 200)
    private String title;

    @Column(name = "Author", length = 200)
    private String author;

    @Column(name = "Category", length = 100)
    private String category;

    @Column(name = "Publisher", length = 100)
    private String publisher;

    @Column(name = "PublishingDate")
    private String publishingDate;

    @Column(name = "Edition", length = 100)
    private String edition;

    @Column(name = "BookAvailability")
    private Boolean available;


}
