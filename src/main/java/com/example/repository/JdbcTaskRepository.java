package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Task;

@Repository
public class JdbcTaskRepository implements TaskRepository {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTaskRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<Task> findAll() {
		String sql = "SELECT * FROM tasks";
		return jdbcTemplate.query(sql, this::mapRowToTask);
	}
	@Override
	public List<Task> findById(int id) {
		String sql = "SELECT * FROM tasks WHERE id = " + id;
		return jdbcTemplate.query(sql, this::mapRowToTask);
	}
	@Override
	public void save(Task task) {
		String sql = "INSERT INTO tasks (task_name,duration, status) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, task.getTaskName(), task.getDuration(), task.getStatus());
	}
	@Override
	public void update(Task task) {
		String sql = "UPDATE tasks SET task_name = ?,duration= ?, status = ? WHERE id = ?";
		jdbcTemplate.update(sql, task.getTaskName(), task.getDuration(), task.getStatus(), task.getId());
	}
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM tasks WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
	private Task mapRowToTask(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
		task.setId(rs.getInt("id"));
		task.setTaskName(rs.getString("task_name"));
		task.setStatus(rs.getString("status"));
		task.setDuration(rs.getString("duration"));
		return task;
	}
}
