package com.noah.musictracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TaskController {

    private final TaskRepository repository;

    // Injecting the SQLite Repository
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/tasks")
    public List<MusicTask> getTasks() {
        return repository.findAll(); // Fetches directly from SQLite
    }

    @PostMapping("/api/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public MusicTask createTask(@RequestBody CreateTaskRequest request) {

        if (request.getTitle() == null || request.getTitle().isBlank()
                || request.getProject() == null
                || request.getProject().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title and project are required"
            );
        }

        // Saves directly to SQLite
        return repository.create(request.getTitle().trim(), request.getProject().trim());
    }
    @PutMapping("/api/tasks/{id}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeTask(@PathVariable long id) {
        repository.markTaskDone(id);
    }
    @DeleteMapping("/api/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id) {
        repository.delete(id);
    }
}