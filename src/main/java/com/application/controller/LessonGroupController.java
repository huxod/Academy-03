package com.application.controller;

import com.application.model.lesson.Lesson;
import com.application.model.lesson.LessonGroup;
import com.application.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081","*"})
@RestController
public class LessonGroupController {

    private final LessonRepository repoLesson;
    private final LessonGroupRepository repoGroupLesson;

    public LessonGroupController(LessonRepository repoLesson, LessonGroupRepository repoGroupLesson) {
        this.repoLesson = repoLesson;
        this.repoGroupLesson = repoGroupLesson;
    }

    //Get Lessons with LessonGroup
    @GetMapping(path = "/lesson-json/{id}")
    public LessonGroup getLesson(@PathVariable Long id) {
        return this.repoGroupLesson.findLessonGroupById(id);
    }

    //Get Lessons from LessonGroup
    @GetMapping(path = "/lessons/{id}")
    public Page<Lesson> getLessons(@PathVariable Long id, Pageable page) {
        return this.repoLesson.findLessonsByLessonGroup_Id(id, page);
    }

    //Get Lesson from LessonGroup
    @GetMapping(path = "/lesson-json/{groupId}/{lessonId}")
    public Lesson getContent(@PathVariable("groupId") Long groupId, @PathVariable("lessonId") Long lessonId) {
        return this.repoLesson.findLessonByIdAndLessonGroup_Id(lessonId, groupId);
    }

    //Get All LessonGroup
    @GetMapping(path = "/lesson-json")
    public List<LessonGroup> getAllLesson() {
        return this.repoGroupLesson.findAll();
    }


    /*Save New Lesson Group*/
    @PostMapping(path = "/lessonGroup")
    public LessonGroup saveLessonGroup(@RequestBody LessonGroup lessonGroup) {
        return this.repoGroupLesson.save(lessonGroup);
    }

    /*Edit Lesson Group*/
    @PostMapping(path = "/lessonGroup/{idgroup}")
    public LessonGroup editLessonGroup(@RequestBody LessonGroup lessonGroup, @PathVariable Long idgroup) {
        return repoGroupLesson.save(repoGroupLesson.findLessonGroupById(idgroup)
                .setLessonGroupTitle(lessonGroup.getLessonGroupTitle()));
    }
}



