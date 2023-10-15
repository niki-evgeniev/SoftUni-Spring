package com.plannerapp.model.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(name = "priority_name", nullable = false)
    private PriorityNameEnum priorityName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> task;

    public Priority() {
    }

    public PriorityNameEnum getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityNameEnum priorityName) {
        this.priorityName = priorityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }
}
