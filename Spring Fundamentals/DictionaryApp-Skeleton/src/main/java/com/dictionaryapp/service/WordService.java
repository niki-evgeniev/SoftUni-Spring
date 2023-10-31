package com.dictionaryapp.service;

import com.dictionaryapp.model.BindingModel.WordAddBindingModel;
import com.dictionaryapp.model.view.ViewHomeModel;

public interface WordService {
    void addWord(WordAddBindingModel wordAddBindingModel);

    ViewHomeModel getAllInfo();

    void removeWord(Long id);

    void removeAll();
}
