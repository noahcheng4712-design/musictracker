package com.noah.musictracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbc;

    public TaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<MusicTask> findAll() {
        String sql = """
                SELECT id, title, project, status
                FROM music_tasks
                ORDER BY id
                """;

        return jdbc.query(sql, this::mapRow);
    }

    public MusicTask create(String title, String project) {
        String sql = """
                INSERT INTO music_tasks (title, project, status)
                VALUES (?, ?, 'TODO')
                RETURNING id, title, project, status
                """;

        return jdbc.queryForObject(sql, this::mapRow, title, project);
    }

    private MusicTask mapRow(ResultSet row, int rowNumber)
            throws SQLException {
        return new MusicTask(
                row.getLong("id"),
                row.getString("title"),
                row.getString("project"),
                row.getString("status")
        );
    }
}