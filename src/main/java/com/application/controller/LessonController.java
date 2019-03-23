package com.application.controller;

import com.application.model.lesson.Lesson;
import com.application.model.lesson.LessonContent;
import com.application.repository.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class LessonController {

    private LessonRepository repoLesson;
    private LessonGroupRepository repoGroupLesson;
    private LessonContentRepository repoContent;


    public LessonController(LessonRepository repoLesson, LessonGroupRepository repoGroupLesson, LessonContentRepository repoContent) {
        this.repoLesson = repoLesson;
        this.repoGroupLesson = repoGroupLesson;
        this.repoContent = repoContent;
    }

    // Add New Lesson to Group Lesson
    @PostMapping(path="/lesson/{idgroup}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public Lesson saveLesson(@RequestBody Lesson lesson,@PathVariable Long idgroup){
        lesson.setLessonGroup(repoGroupLesson.findLessonGroupById(idgroup));
        return this.repoLesson.save(lesson);
    }
    // Add New Lesson to Group Lesson
    @PostMapping(path="/lesson/edit/{idlesson}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public Lesson editLesson(@RequestBody Lesson lesson,@PathVariable Long idlesson){
        return this.repoLesson.save(repoLesson.getLessonsById(idlesson).setTitle( lesson.getTitle()));
    }
    //Delete LessonGroup
    @DeleteMapping(path="/lessonGroup/delete/{idgroup}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public void deleteLessonGroup(@PathVariable Long idgroup){
        repoGroupLesson.deleteById(idgroup);
    }

    //Delete Lesson
    @DeleteMapping(path="/lesson/delete/{idlesson}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public void deleteLesson(@PathVariable Long idlesson){
        repoLesson.deleteById(idlesson);
    }

    // Add New ContentLesson to  Lesson
    @PostMapping(path="/lessonItem/{idlesson}")
    @CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
    public LessonContent addLessonContent(@RequestBody LessonContent lessonContent, @PathVariable Long idlesson){
        if(this.repoLesson.getLessonsById(idlesson).getLessonContent() == null) {
            return this.repoContent.save(lessonContent.setLesson(this.repoLesson.getLessonsById(idlesson)));
        }else {
            this.repoContent.save(lessonContent.setId(this.repoLesson.getLessonsById(idlesson).getLessonContent().getId()));
            return this.repoLesson.getLessonsById(idlesson).getLessonContent();
        }
    }

}
