package com.temalab.forum.model;

import com.sun.istack.NotNull;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
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

    private String password;

    private String firstName;

    private String lastName;

    private String Email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Email.equals(user.Email) && userName.equals(user.userName);
    }
}
