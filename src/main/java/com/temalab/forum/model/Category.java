package com.temalab.forum.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_category_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Category parentCategory;

    private String name;
}
