package com.agi.controller;

import com.agi.payload.request.LessonRequest;
import com.agi.payload.response.LessonResponse;
import com.agi.payload.response.MessageResponse;
import com.agi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @GetMapping("/{id}")
    public ResponseEntity<List<LessonResponse>> index(@PathVariable Long id){
        List<LessonResponse> lessonResponses = lessonService.index(id);
        return new ResponseEntity<>(lessonResponses, HttpStatus.OK);
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<LessonResponse> show(@PathVariable Long id){
        LessonResponse lessonResponse = lessonService.show(id);
        return new ResponseEntity<>(lessonResponse, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<LessonResponse> create(@PathVariable Long id,@Valid @RequestBody LessonRequest lessonRequest){
        LessonResponse lessonResponse = lessonService.create(id, lessonRequest);
        return new ResponseEntity<>(lessonResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> edit(@PathVariable Long id, @Valid @RequestBody LessonRequest lessonRequest){
        LessonResponse lessonResponse = lessonService.edit(id, lessonRequest);
        return new ResponseEntity<>(lessonResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        MessageResponse messageResponse = lessonService.delete(id);
        return new ResponseEntity<>(messageResponse, HttpStatus.ACCEPTED);
    }

}
