package com.application.controller;

import com.application.model.lesson.LessonScore;
import com.application.model.dictionary.Dictionary;
import com.application.repository.*;
import com.application.service.ScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {


    private final DicrtionaryRepository dictionaryRepo;
    private final LessonScoreRepository lessonScoreRepo;
    private final ScoreService scoreService;

    public ScoreController(DicrtionaryRepository dictionaryRepo, LessonScoreRepository lessonScoreRepo, ScoreService scoreService) {
        this.dictionaryRepo = dictionaryRepo;
        this.lessonScoreRepo = lessonScoreRepo;
        this.scoreService = scoreService;
    }

    //Set Lesson Score
    @PostMapping(path="/score/{id}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public LessonScore addUserScore(@RequestBody Dictionary dictionary,@PathVariable Long id) {
        return scoreService.setScore(id ,dictionary);
    }
}
