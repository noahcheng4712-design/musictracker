package com.noah.musictracker;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/api/tasks")
    public List<MusicTask> getTasks() {
        MusicTask task1 = new MusicTask(
                1,
                "Balance the violin and vocals",
                "Demo Track",
                "TODO"
        );

        MusicTask task2 = new MusicTask(
                2,
                "Master the bass frequencies",
                "Demo Track",
                "IN_PROGRESS"
        );

        return List.of(task1, task2);
    }
}
