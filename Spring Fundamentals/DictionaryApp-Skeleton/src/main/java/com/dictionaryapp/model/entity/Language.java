package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageNameEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {

    @Column(name = "language_name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageNameEnum languageName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<Word> words;

    public Language() {
    }

    public LanguageNameEnum getLanguageName() {
        return languageName;
    }

    public void setLanguageName(LanguageNameEnum languageName) {
        this.languageName = languageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
