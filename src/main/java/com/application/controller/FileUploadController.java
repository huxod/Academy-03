package com.application.controller;


import com.application.model.lesson.LessonItem;
import com.application.repository.EnglishRepository;
import com.application.repository.LessonItemRepository;
import com.application.repository.LessonRepository;
import com.application.service.FileStorageService;
import com.application.tools.UploadFileResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = {"http://localhost:8081","http://localhost:3000","http://localhost:8080","*"})
@RestController
public class FileUploadController {



    private final FileStorageService fileStorageService;
    private final LessonItemRepository itemRepo;
    private final LessonRepository lessonRepo;
    private final EnglishRepository englishRepo;

    public FileUploadController(FileStorageService fileStorageService,
                                LessonItemRepository itemRepo,
                                LessonRepository lessonRepo,
                                EnglishRepository englishRepo) {

        this.fileStorageService = fileStorageService;
        this.itemRepo = itemRepo;
        this.lessonRepo = lessonRepo;
        this.englishRepo = englishRepo;
    }

    //Upload and Save url LessonItem

    @PostMapping("/uploadFile/{idlesson}")
    public UploadFileResponse uploadFile(@RequestParam("Files") MultipartFile file, @PathVariable Long idlesson) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/gif")) {
            if (this.lessonRepo.getLessonsById(idlesson).getLessonItem() == null) {
                this.itemRepo.save(new LessonItem().setImageUrl(fileDownloadUri).setLesson(this.lessonRepo.getLessonsById(idlesson)));
            } else {
                this.lessonRepo.save(
                        this.lessonRepo.getLessonsById(idlesson).setLessonItem(
                                this.lessonRepo.getLessonsById(idlesson).getLessonItem().setImageUrl(fileDownloadUri)));
            }
        }

        return file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("image/png") ||
                file.getContentType().equals("image/gif")
                ? new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()) : null;
    }

    //Upload and Save url LessonItem
    @PostMapping("/uploadAudio/{englishword}")
    public UploadFileResponse uploadAudioFile(@RequestParam("audioenglish") MultipartFile audioenglish, @PathVariable String englishword) {
        String fileName = fileStorageService.storeFile(audioenglish);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        if (this.englishRepo.findAllByWords(englishword) != null && audioenglish.getContentType().equals("audio/mp3")) {
            this.englishRepo.findAllByWords(englishword).forEach(english -> this.englishRepo.save(english.setAudioUrl(fileDownloadUri)));
        }
        return audioenglish.getContentType().equals("audio/mp3") ? new UploadFileResponse(fileName, fileDownloadUri,
                audioenglish.getContentType(), audioenglish.getSize()) : null;
    }
}