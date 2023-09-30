package com.example.pathfinder.models.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "text", columnDefinition = "text")
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private User recipient;

    public Message() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
