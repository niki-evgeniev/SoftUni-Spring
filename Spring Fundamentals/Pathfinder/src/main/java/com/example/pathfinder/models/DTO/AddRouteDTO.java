package com.example.pathfinder.models.DTO;

import com.example.pathfinder.models.entity.LevelEnum;
import com.example.pathfinder.models.entity.CategoriesName;

import java.util.Set;


public class AddRouteDTO {

    private String name;

    private String description;

    private LevelEnum level;

    private Set<CategoriesName> categories;

    private String videoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoriesName> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoriesName> categories) {
        this.categories = categories;
    }
}
