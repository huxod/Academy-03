package com.application.controller;

import com.application.model.dictionary.Dictionary;
import com.application.repository.*;
import com.application.service.ScoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DictionaryController {

    private final LessonRepository repoLesson;
    private final LessonScoreRepository lessonScoreRepo;
    private final DicrtionaryRepository dicrtionaryRepo;
    private final ScoreService scoreService;

    public DictionaryController(LessonRepository repoLesson, LessonScoreRepository lessonScoreRepo, DicrtionaryRepository dicrtionaryRepo, ScoreService scoreService) {
        this.repoLesson = repoLesson;
        this.lessonScoreRepo = lessonScoreRepo;
        this.dicrtionaryRepo = dicrtionaryRepo;
        this.scoreService = scoreService;
    }

    //Get Dictionary with Lesson
    @GetMapping(path="/dictionary/{id}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public List<Dictionary> getWordsFromLesson(@PathVariable Long id) {
        return this.dicrtionaryRepo.findDictionariesByLesson_Id(id);
    }

    //Get Dictionary with Lesson
    @GetMapping(path="/dictionary-group/{id}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public List<Dictionary> getWordsFromLessonGroup(@PathVariable Long id) {
        return this.dicrtionaryRepo.findDictionariesByLesson_LessonGroup_Id(id);
    }

    // Add  Dictionary
    @PostMapping(path="/dictionary/save/{idlesson}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public Dictionary addDictionary(@RequestBody Dictionary dictionary, @PathVariable Long idlesson){
        this.dicrtionaryRepo.save(dictionary);
        dictionary.getWordsPolish().setDictionary(dictionary);
        dictionary.getWordsEnglish().setDictionary(dictionary);
        dictionary.setLesson(this.repoLesson.getLessonsById(idlesson));
        return this.dicrtionaryRepo.save(dictionary);
    }


    //Delete Lesson
    @DeleteMapping(path="/dictionary/delete/{iddictionary}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public void deleteDictionary(@PathVariable Long iddictionary){
        this.scoreService.unsetScore(iddictionary);
        dicrtionaryRepo.deleteById(iddictionary);
    }
}
