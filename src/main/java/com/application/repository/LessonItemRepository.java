package com.application.repository;

import com.application.model.lesson.LessonItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonItemRepository extends JpaRepository<LessonItem, Long> {
}
