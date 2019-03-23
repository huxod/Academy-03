package com.application.repository;

import com.application.model.dictionary.WordsEnglish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnglishRepository extends CrudRepository<WordsEnglish, Long> {
    List<WordsEnglish> findAllByWords(String words);
}





