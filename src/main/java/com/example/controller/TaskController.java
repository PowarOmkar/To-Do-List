package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.model.Task;
import com.example.repository.TaskRepository;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private final TaskRepository taskRepository;

	@Autowired
	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping // done
	public String getAllTasks(Model model) {
		System.out.println("in get data");
		List<Task> tasks = taskRepository.findAll();
		model.addAttribute("tasks", tasks);
		return "tasks";
	}

	@GetMapping("/addTask") // done
	public String showAddTaskForm(Model model) {
		model.addAttribute("task", new Task());
		return "addTask";
	}

	@PostMapping("/add")
	public String addTask(@ModelAttribute Task task) {
		taskRepository.save(task);
		return "redirect:/tasks";
	}

	@GetMapping("/edit1/{id}")
	public String showEditTaskForm(@PathVariable int id, Model model) {
		List<Task> task = taskRepository.findById(id);
		System.out.println(task);
		model.addAttribute("task", task);
		return "editTask";
	}

	@PostMapping("/edit/{id}")
	public String editTask(@PathVariable int id, @ModelAttribute Task task) {
		task.setId(id);
		System.out.println(task);
		taskRepository.update(task);
		return "redirect:/tasks";
	}

	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable int id) {
		taskRepository.delete(id);
		return "redirect:/tasks";
	}
}
