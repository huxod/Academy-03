package com.application.repository;

import com.application.model.dictionary.Dictionary;
import com.application.model.lesson.LessonScore;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LessonScoreRepository extends CrudRepository<LessonScore,Long> {
    LessonScore findByUsers_Id(Long id);
   Set<LessonScore> findByDictionaries(Dictionary dictionary);
}
