package com.temalab.forum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class User {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    private String userName;

    private String firstName;

    private String lastName;

    private String eMail;
}
