package com.application.model.lesson;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "lesson_content")
public class LessonContent {

    public LessonContent() {}

    public LessonContent(String title, String content) {
        this.title = title;
        this.content =  content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_content_seq")
    @SequenceGenerator(name="lesson_content_seq", sequenceName = "LESSON_CONTENT_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "lesson_content_id")
    private long id;
    @OneToOne
    @JoinColumn(name = "lesson_id")

    private Lesson lesson;
    @Column(name = "title" )
    private String title;

    @Column(name = "content", length = 1024)
    private String content;

    public long getId() {
        return id;
    }

    public LessonContent setId(long id) {
        this.id = id;
        return this;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public LessonContent setLesson(Lesson lesson) {
        this.lesson = lesson;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public LessonContent setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public LessonContent setContent(String content) {
        this.content = content;
        return this;
    }
}
