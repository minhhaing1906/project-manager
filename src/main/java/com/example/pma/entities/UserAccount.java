package com.example.pma.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {
//    @Id
//    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
//    @SequenceGenerator(name="user_accounts_seq", sequenceName = "db_seq", allocationSize=1)

    // Don't know why this works and above doesn't
    @Id
    @GeneratedValue(generator="user_accounts_seq")
    @Column(name="user_id")
    private long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "enabled")
    private boolean enabled = true;

}
