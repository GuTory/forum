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

<<<<<<< Updated upstream
=======

    @NotNull
>>>>>>> Stashed changes
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

<<<<<<< Updated upstream
    private String eMail;
=======
    private String Email;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String eMail) {
        this.Email = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
>>>>>>> Stashed changes
}
