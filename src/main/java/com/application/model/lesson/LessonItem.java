package com.application.model.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


@Entity
@Table(name="lesson_item")
public class LessonItem {
    public LessonItem() { }

    public LessonItem(Lesson lesson,  String imageUrl) {
        this.lesson = lesson;
        this.imageUrl = imageUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_item_seq")
    @SequenceGenerator(name="lesson_item_seq", sequenceName = "LESSON_ITEM_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "lesson_item_id")
    private long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;
    @Column(name="image_url")
    private String imageUrl;
    public long getId() {
        return id;
    }

    //Getters and Setters
    public LessonItem setId(long id) {
        this.id = id;
        return this;
    }
    public Lesson getLesson() {
        return lesson;
    }
    public LessonItem setLesson(Lesson lesson) {
        this.lesson = lesson;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }
    public LessonItem setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
