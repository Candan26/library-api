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
public class ResourceMember {
    @Id
    @Column(name = "memberId", nullable = false)
    private String id;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Surname", length = 100)
    private String surname;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "PhoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "JoinDate")
    private String joinDate;
}
