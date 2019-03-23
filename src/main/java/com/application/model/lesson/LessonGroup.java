package com.application.model.lesson;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "lesson_group")
public class LessonGroup {
    public LessonGroup() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_group_seq")
    @SequenceGenerator(name="lesson_group_seq", sequenceName = "LESSON_GROUP_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "lesson_group_id")
    private long id;
    @Column(name="lesson_group_title")
    private String lessonGroupTitle;
    @JsonIgnore
    @OneToMany(mappedBy = "lessonGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons;

    //Getter and Setter
    public long getId() {
        return id;
    }

    public LessonGroup setId(long id) {
        this.id = id;
        return this;
    }

    public String getLessonGroupTitle() {
        return lessonGroupTitle;
    }

    public LessonGroup setLessonGroupTitle(String lessonGroupTitle) {
        this.lessonGroupTitle = lessonGroupTitle;
        return this;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public LessonGroup setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
        return this;
    }
}
