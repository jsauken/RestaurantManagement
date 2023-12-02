package com.example.restapi.repository;

import com.example.restapi.DTO.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepo extends JpaRepository<Translation, Long> {
    Optional<Translation> findByKeyNameAndLangCode(String keyName, String langCode);
}
