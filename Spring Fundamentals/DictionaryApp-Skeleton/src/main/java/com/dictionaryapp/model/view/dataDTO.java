package com.dictionaryapp.model.view;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;

import java.time.LocalDate;

public class dataDTO {

    private Long id;

    private String term;

    private String translation;

    private String example;

    private User user;

    private LocalDate inputDate;

    public dataDTO() {
    }

    public dataDTO(Word word) {
        this.term = word.getTerm();
        this.translation = word.getTranslation();
        this.example = word.getExample();
        this.id = word.getId();
        this.user = word.getAddedBy();
        this.inputDate = word.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }
}
