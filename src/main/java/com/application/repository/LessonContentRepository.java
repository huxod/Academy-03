package com.application.repository;

import com.application.model.lesson.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonContentRepository extends JpaRepository<LessonContent,Long> {
    List<LessonContent> findAll();
    LessonContent findByLesson_Id(Long id);
    @Transactional
    void deleteLessonContentById(Long id);
}
