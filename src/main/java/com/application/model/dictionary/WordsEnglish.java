package com.application.model.dictionary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity

@Table(name= "words_english")
public class WordsEnglish {
    public WordsEnglish(String words, String audioUrl, Dictionary dictionary) {
        this.words = words;
        this.audioUrl = audioUrl;
        this.dictionary = dictionary;
    }

    public WordsEnglish() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "words_english_seq")
    @SequenceGenerator(name="words_english_seq", sequenceName = "WORDS_ENGLISH_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "english_id")
    private long id;

    @Column(name = "words")
    private String words;
    @Column(name="audio_url")
    private String audioUrl;

    @OneToOne
    @JoinColumn(name="dictionary_id")
    private Dictionary dictionary;

    public long getId() {
        return id;
    }

    public WordsEnglish setId(long id) {
        this.id = id;
        return this;
    }

    public String getWords() {
        return words;
    }

    public WordsEnglish setWords(String words) {
        this.words = words;
        return this;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public WordsEnglish setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
        return this;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public WordsEnglish setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
        return this;
    }
}
