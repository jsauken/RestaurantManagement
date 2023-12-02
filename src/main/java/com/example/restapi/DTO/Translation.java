package com.example.restapi.DTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;


import lombok.*;



@Entity
@Table(name = "translations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_name", nullable = false)
    private String keyName;

    @Column(name = "lang_code", nullable = false)
    private String langCode;

    @Column(name = "translation_value", columnDefinition = "TEXT")
    private String translationValue;
}
