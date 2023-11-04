package com.aquariuxdemo.h2database.entity;

import jakarta.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

}
