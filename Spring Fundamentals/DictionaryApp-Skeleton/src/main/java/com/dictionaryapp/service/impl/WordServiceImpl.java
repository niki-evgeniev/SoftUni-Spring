package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.BindingModel.WordAddBindingModel;
import com.dictionaryapp.model.LoggedUser;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageNameEnum;
import com.dictionaryapp.model.view.dataDTO;
import com.dictionaryapp.model.view.ViewHomeModel;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public WordServiceImpl(LanguageRepository languageRepository, UserRepository userRepository,
                           WordRepository wordRepository, LoggedUser loggedUser,
                           ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addWord(WordAddBindingModel wordAddBindingModel) {

        Language language = languageRepository.findByLanguageName(wordAddBindingModel.getLanguage());
        User user = userRepository.findByUsername(loggedUser.getUsername());

        Word word = modelMapper.map(wordAddBindingModel, Word.class);
        word.setLanguage(language);
        word.setAddedBy(user);

        wordRepository.save(word);
    }

    @Override
    public ViewHomeModel getAllInfo() {

        List<Word> all = wordRepository.findAll();

        List<dataDTO> germanList = new ArrayList<>();
        List<dataDTO> spanishList = new ArrayList<>();
        List<dataDTO> frenchList = new ArrayList<>();
        List<dataDTO> italianList = new ArrayList<>();

//        for (Word word : all) {
//            if (word.getLanguage().getLanguageName().toString().equals("GERMAN")) {
//                germanList.add(new dataDTO(word));
//            } else if (word.getLanguage().getLanguageName().toString().equals("SPANISH")) {
//                spanishList.add(new dataDTO(word));
//            } else if (word.getLanguage().getLanguageName().toString().equals("FRENCH")) {
//                frenchList.add(new dataDTO(word));
//            } else if (word.getLanguage().getLanguageName().toString().equals("ITALIAN")) {
//                italianList.add(new dataDTO(word));
//            }
//        }
        for (Word word : all) {
            if (word.getLanguage().getLanguageName().equals(LanguageNameEnum.GERMAN)) {
                germanList.add(new dataDTO(word));

            } else if (word.getLanguage().getLanguageName().equals(LanguageNameEnum.SPANISH)) {
                spanishList.add(new dataDTO(word));

            } else if (word.getLanguage().getLanguageName().equals(LanguageNameEnum.FRENCH)) {
                frenchList.add(new dataDTO(word));

            } else if (word.getLanguage().getLanguageName().equals(LanguageNameEnum.ITALIAN)) {
                italianList.add(new dataDTO(word));
            }
        }

        return new ViewHomeModel(germanList, spanishList, italianList, frenchList);
    }

    @Override
    public void removeWord(Long id) {

        wordRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        System.out.println();
        wordRepository.deleteAll();
    }
}
