package com.application.repository;

import com.application.model.dictionary.Dictionary;
import com.application.model.lesson.LessonScore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface DicrtionaryRepository extends CrudRepository<Dictionary, Long> {
    List<Dictionary> findDictionariesByLesson_LessonGroup_Id(Long id);
    List<Dictionary> findDictionariesByLesson_Id(Long id);
    Dictionary getById(Long id);
}
