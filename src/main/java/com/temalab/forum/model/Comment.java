package com.temalab.forum.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Objects;

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

    public User getRespondent() {
        return respondent;
    }

    public void setRespondent(User respondent) {
        this.respondent = respondent;
    }

    public Comment getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(Comment responseTo) {
        this.responseTo = responseTo;
        if(!this.topic.equals(responseTo.topic))
            this.topic = responseTo.topic;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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
