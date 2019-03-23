package com.application.model.lesson;

import com.application.model.dictionary.Dictionary;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.util.Set;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "lesson")
public class Lesson {

    public Lesson() {}

    public Lesson(String title, String lesson) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name="lesson_seq", sequenceName = "LESSON_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "lesson_id")
    private long id;

    @Column(name = "title")
    private String title;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private LessonContent lessonContent;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private LessonItem lessonItem;

    @ManyToOne
    @JoinColumn(name="lesson_group_id")
    private LessonGroup lessonGroup;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dictionary> dictionaries;

    public long getId() {
        return id;
    }

    public Lesson setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Lesson setTitle(String title) {
        this.title = title;
        return this;
    }

    public LessonItem getLessonItem() {
        return lessonItem;
    }

    public Lesson setLessonItem(LessonItem lessonItem) {
        this.lessonItem = lessonItem;
        return this;
    }

    public LessonGroup getLessonGroup() {
        return lessonGroup;
    }

    public Lesson setLessonGroup(LessonGroup lessonGroup) {
        this.lessonGroup = lessonGroup;
        return this;
    }

    public void setLessonContent(LessonContent lessonContent) {
        if (lessonContent == null) {
            if (this.lessonContent != null) {
                this.lessonContent.setLesson(null);
            }
        }
        else {
            lessonContent.setLesson(this);
        }
        this.lessonContent = lessonContent;
    }

    public LessonContent getLessonContent() {
        return lessonContent;
    }

    public Set<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Set<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }
}
