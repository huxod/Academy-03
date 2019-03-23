package com.application.repository;

import com.application.model.lesson.LessonGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonGroupRepository extends JpaRepository<LessonGroup,Long> {
    @Override
    List<LessonGroup> findAll();
    LessonGroup findLessonGroupById(Long id);
    @Transactional
    void deleteById(Long id);
}


