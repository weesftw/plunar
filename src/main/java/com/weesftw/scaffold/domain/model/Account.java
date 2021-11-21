package com.weesftw.scaffold.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
public class Account
{
    @Id
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String description;
    private String zipCode;

    @Column(unique = true)
    private String username;
    private String password;

    private boolean isEnable;

    @ManyToOne
    private Group group;
}