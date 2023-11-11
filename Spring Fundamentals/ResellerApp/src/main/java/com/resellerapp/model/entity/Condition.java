package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionNameEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Column(name = "condition_name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionNameEnum conditionName;

    @Column(name = "descriptions", nullable = false)
    private String description;

    @OneToMany(mappedBy = "condition")
    private Set<Offer> offers;

    public Condition() {
    }

    public ConditionNameEnum getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionNameEnum conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
