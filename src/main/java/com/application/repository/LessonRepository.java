package com.application.repository;

import com.application.model.lesson.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LessonRepository extends JpaRepository<Lesson , Long> {
    Lesson findLessonByIdAndLessonGroup_Id(Long id,Long groupId);
    Page<Lesson> findLessonsByLessonGroup_Id(Long id, Pageable page);
    void deleteById(Long id);
    Lesson getLessonsById(Long id);
}



