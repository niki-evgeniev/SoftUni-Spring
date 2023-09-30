package com.example.pathfinder.models.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "text_content", columnDefinition = "text")
    private String textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;

    public Comment() {
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
