package com.noah.musictracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TaskController {
    private final List<MusicTask> tasks = new ArrayList<>();
    private long nextId = 1;

    @GetMapping("/api/tasks")
    public synchronized List<MusicTask> getTasks() {
        return List.copyOf(tasks);
    }

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized MusicTask createTask(
            @RequestBody CreateTaskRequest request) {

        if (request.getTitle() == null || request.getTitle().isBlank()
                || request.getProject() == null
                || request.getProject().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title and project are required"
            );
        }

        MusicTask task = new MusicTask(
                nextId++,
                request.getTitle().trim(),
                request.getProject().trim(),
                "TODO"
        );

        tasks.add(task);
        return task;
    }
}