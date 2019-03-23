package com.application.service;

import com.application.model.dictionary.Dictionary;
import com.application.model.lesson.LessonScore;
import com.application.model.users.Users;
import com.application.repository.DicrtionaryRepository;
import com.application.repository.LessonScoreRepository;
import com.application.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

@Service
public class ScoreService {

    private final UsersRepository usersRepo;
    private final LessonScoreRepository lessonScoreRepo;
    private final DicrtionaryRepository dicrtionaryRepo;


    public ScoreService(UsersRepository usersRepo, LessonScoreRepository lessonScoreRepo, DicrtionaryRepository dicrtionaryRepo) {
        this.usersRepo = usersRepo;
        this.lessonScoreRepo = lessonScoreRepo;
        this.dicrtionaryRepo = dicrtionaryRepo;
    }

    public LessonScore setScore(long userid, Dictionary dictionary){
        Users users = this.usersRepo.findUsersById(userid);
        LessonScore lessonScore  = users.getLessonScore();
        lessonScore.setDictionary(dictionary);
        lessonScoreRepo.save(lessonScore);
        lessonScore.setScore(lessonScore.getDictionaries().size());
        return lessonScoreRepo.save(lessonScore);
    }
    public Iterator<LessonScore> unsetScore(long iddictionary){
        Set<LessonScore> lessonScores = this.lessonScoreRepo.findByDictionaries(dicrtionaryRepo.getById(iddictionary));
        lessonScores.forEach(lessonScore -> {lessonScore.removeDictionary(dicrtionaryRepo.getById(iddictionary));});
        return this.lessonScoreRepo.saveAll(lessonScores).iterator();
    }
}




