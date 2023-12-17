package com.example.restapi.service;

import com.example.restapi.DTO.Translation;
import com.example.restapi.repository.TranslationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TranslationService {

    private final TranslationRepo translationRepo;

    @Autowired
    public TranslationService(TranslationRepo translationRepo) {
        this.translationRepo = translationRepo;
    }

    public List<Translation> getAllTranslations() {
        return translationRepo.findAll();
    }

    public Translation saveTranslation(Translation translation) {
        return translationRepo.save(translation);
    }

    public String getTranslationByKeyAndLocale(String key, Locale locale) {
        String langCode = locale.getLanguage();
        Translation translation = translationRepo.findByKeyNameAndLangCode(key, langCode)
                .orElse(null);

        //CHECKING
        System.out.println("Key: " + key + ", LangCode: " + langCode);

        if (translation != null) {
            return translation.getTranslationValue();
        } else {
            System.out.println("Translation not found for Key: " + key + ", LangCode: " + langCode);

            return "Translation not found for key: " + key;
        }
    }
}
