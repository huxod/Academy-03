package com.application.repository;

import com.application.model.dictionary.WordsPolish;
import org.springframework.data.repository.CrudRepository;

public interface PolishRepository extends CrudRepository<WordsPolish, Long> {

}
