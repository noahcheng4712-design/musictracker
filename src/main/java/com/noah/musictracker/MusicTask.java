package com.noah.musictracker;

public class MusicTask {
    private final long id;
    private final String title;
    private final String project;
    private final String status;

    public MusicTask(long id, String title, String project, String status) {
        this.id = id;
        this.title = title;
        this.project = project;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProject() {
        return project;
    }

    public String getStatus() {
        return status;
    }
}