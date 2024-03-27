package com.bookshop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "user_id", insertable = false)
    private Long userID;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", length = 64)
    private String firstName;

    @Column(name = "last_name", length = 64)
    private String lastName;

    @Column(length = 64)
    private String emailAddress;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "profile_picture", length = 128)
    private String profilePicture;

    @Column(nullable = false, length = 50)
    private String role;

    @Column(nullable = false, length = 1, insertable = false, updatable = false)
    private boolean enabled;

    @Column(nullable = false, length = 1, insertable = false, updatable = false)
    private boolean locked;

}
