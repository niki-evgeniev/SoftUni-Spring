package com.dictionaryapp.model.view;

import java.util.List;

public class ViewHomeModel {

    List<dataDTO> germanWord;

    List<dataDTO> spanishWord;

    List<dataDTO> italianWord;

    List<dataDTO> frenchWord;


    public ViewHomeModel() {
    }

    public ViewHomeModel(List<dataDTO> germanList, List<dataDTO> spanishList, List<dataDTO> italianList, List<dataDTO> frenchList) {
        this.germanWord = germanList;
        this.spanishWord = spanishList;
        this.italianWord = italianList;
        this.frenchWord = frenchList;
    }

    public List<dataDTO> getGermanWord() {
        return germanWord;
    }

    public void setGermanWord(List<dataDTO> germanWord) {
        this.germanWord = germanWord;
    }

    public List<dataDTO> getSpanishWord() {
        return spanishWord;
    }

    public void setSpanishWord(List<dataDTO> spanishWord) {
        this.spanishWord = spanishWord;
    }

    public List<dataDTO> getItalianWord() {
        return italianWord;
    }

    public void setItalianWord(List<dataDTO> italianWord) {
        this.italianWord = italianWord;
    }

    public List<dataDTO> getFrenchWord() {
        return frenchWord;
    }

    public void setFrenchWord(List<dataDTO> frenchWord) {
        this.frenchWord = frenchWord;
    }

    public int allCount() {
        int count = germanWord.size() + frenchWord.size() + italianWord.size() + spanishWord.size();

        return count;

    }
}
