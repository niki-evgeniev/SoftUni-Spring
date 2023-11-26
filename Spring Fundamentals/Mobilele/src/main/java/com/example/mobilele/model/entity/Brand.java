package com.example.mobilele.model.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {


    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "brand"
    )
    private List<Model> models;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<Model> getModels() {
        return models;
    }


    public void setModels(List<Model> models) {
        this.models = models;
    }
}
