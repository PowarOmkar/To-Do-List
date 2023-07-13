package com.example.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Task;
@Service
public interface TaskRepository {
	
    List<Task> findAll();
    List<Task> findById(int id);
    void save(Task task);
    void update(Task task);
    void delete(int id);
}

