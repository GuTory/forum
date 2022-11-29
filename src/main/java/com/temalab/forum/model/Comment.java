package com.temalab.forum.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Comment", indexes = {
        @Index(name = "idx_comment_id", columnList = "id")
})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String response;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "topic_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Topic topic;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "response_to_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Comment responseTo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "respondent_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User respondent;

    public void setResponseTo(Comment responseTo) {
        this.responseTo = responseTo;
        if(!this.topic.equals(responseTo.topic))
            this.topic = responseTo.topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) && response.equals(comment.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, response);
    }
}
