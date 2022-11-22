package com.temalab.forum.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Topic", indexes = {
        @Index(name = "idx_topic_id", columnList = "id")
})
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private User issuer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "Topic_categories",
            joinColumns = @JoinColumn(name = "Topic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id", referencedColumnName = "id"))
    private List<Category> categories;

    public User getIssuer() {
        return issuer;
    }

    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }

    public List<Category> getCategories() {
        if (this.categories == null) this.categories = new ArrayList<>();
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        getCategories().add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id.equals(topic.id) && name.equals(topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
