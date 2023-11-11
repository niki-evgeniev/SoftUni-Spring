package com.resellerapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{


    @Column(name = "description", nullable = false)
    @Size(min = 2, max = 50)
    private String description;

    @Column(name = "price", nullable = false)
    @Positive
    private BigDecimal price;

    @ManyToOne
    private User user;

    @ManyToOne
    private User offersBay;

    @ManyToOne
    private Condition condition;

    public Offer() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOffersBay() {
        return offersBay;
    }

    public void setOffersBay(User offersBay) {
        this.offersBay = offersBay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
