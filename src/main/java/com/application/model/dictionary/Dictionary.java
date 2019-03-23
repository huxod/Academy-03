package com.application.model.dictionary;

import com.application.model.lesson.Lesson;
import com.application.model.lesson.LessonScore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name="dictionary")
public class Dictionary {

    public Dictionary() { }

    public Dictionary(WordsEnglish wordsEnglish, WordsPolish wordsPolish, Lesson lesson) {
        this.wordsEnglish = wordsEnglish;
        this.wordsPolish = wordsPolish;
        this.lesson = lesson;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_seq")
    @SequenceGenerator(name="dictionary_seq", sequenceName = "DICTIONARY_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "dictionary_id")
    private long id;

    @OneToOne(mappedBy = "dictionary", cascade = CascadeType.ALL ,orphanRemoval = true)
    private WordsEnglish wordsEnglish;

    @OneToOne(mappedBy = "dictionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private WordsPolish wordsPolish;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    public long getId() {
        return id;
    }

    public Dictionary setId(long id) {
        this.id = id;
        return this;
    }


    public Lesson getLesson() {
        return lesson;
    }

    public Dictionary setLesson(Lesson lesson) {
        this.lesson = lesson;
        return this;
    }

    public WordsEnglish getWordsEnglish() {
        return wordsEnglish;
    }

    public Dictionary setWordsEnglish(WordsEnglish wordsEnglish) {
        this.wordsEnglish = wordsEnglish;
        return this;
    }

    public WordsPolish getWordsPolish() {
        return wordsPolish;
    }

    public Dictionary setWordsPolish(WordsPolish wordsPolish) {
        this.wordsPolish = wordsPolish;
        return this;
    }
}
