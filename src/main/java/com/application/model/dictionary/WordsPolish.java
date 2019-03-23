package com.application.model.dictionary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity

@Table(name= "words_polish")
public class WordsPolish {

    public WordsPolish(String words, Dictionary dictionary) {
        this.words = words;
        this.dictionary = dictionary;
    }

    public WordsPolish() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "words_polish_seq")
    @SequenceGenerator(name = "words_polish_seq", sequenceName = "WORDS_POLISH_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "words_polish_id")
    private long id;

    @Column(name = "words")
    private String words;

    @OneToOne
    @JoinColumn(name="dictionary_id")
    private Dictionary dictionary;

    public long getId() {
        return id;
    }

    public WordsPolish setId(long id) {
        this.id = id;
        return this;
    }

    public String getWords() {
        return words;
    }

    public WordsPolish setWords(String words) {
        this.words = words;
        return this;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public WordsPolish setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
        return this;
    }
}