package com.temalab.forum.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "User", indexes = {
        @Index(name = "idx_user_id", columnList = "Id")
})
public class User {

    /**
     * Only getter for ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    private String Email;

    public Long getId() {
        return id;
    }

    public void setId(Long Id){
        this.id = Id;
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
}
