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
public class ResourceBorrowing {
    @Id
    @Column(name = "BorrowingId", nullable = false)
    private String id;

    @Column(name = "BookId", length = 100)
    private String bookId;

    @Column(name = "MemberId", length = 100)
    private String memberId;

    @Column(name = "BorrowDate")
    private String borrowDate;

    @Column(name = "DueDate")
    private String dueDate;

    @Column(name = "ReturnDate")
    private String returnDate;
}
