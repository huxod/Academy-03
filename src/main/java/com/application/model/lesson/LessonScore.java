package com.application.model.lesson;

import com.application.model.dictionary.Dictionary;
import com.application.model.users.Users;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "lesson_score")
public class LessonScore {

    public LessonScore() {}

    public LessonScore(Set<Dictionary> dictionaries, Users users) {
        this.dictionaries = dictionaries;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_score_seq")
    @SequenceGenerator(name = "lesson_score_seq", sequenceName = "LESSON_SCORE_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "lesson_score_id")
    private long id;
    @Column(name = "score")
    private int score;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "score_dictionary", joinColumns = @JoinColumn(name = "lesson_score_id"), inverseJoinColumns = @JoinColumn(name = "dictionary_id"))
    private Set<Dictionary> dictionaries;
    public long getId() {
        return id;
    }
    @OneToOne(mappedBy = "lessonScore",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Users users;

    public LessonScore setId(long id) {
        this.id = id;
        return this;
    }

    public Set<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public LessonScore setDictionaries(Set<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
        return this;
    }

    public LessonScore setDictionary(Dictionary dictionary) {
        this.dictionaries.add(dictionary);
        return this;
    }
    public LessonScore removeDictionary(Dictionary dictionary) {
        this.dictionaries.remove(dictionary);
        return this;
    }

    public Users getUsers() {
        return users;
    }

    public LessonScore setUsers(Users users) {
        this.users = users;
        return this;
    }

    public int getScore() {
        return score;
    }

    public LessonScore setScore(int score) {
        this.score = score;
        return this;
    }
}
