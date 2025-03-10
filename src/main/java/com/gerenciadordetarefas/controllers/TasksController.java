package com.gerenciadordetarefas.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciadordetarefas.dto.TasksDTO;
import com.gerenciadordetarefas.services.TasksService;

@RestController
@CrossOrigin(origins = "*")
public class TasksController {

	private final TasksService tasksService;

	public TasksController(TasksService tasksService) {
		this.tasksService = tasksService;
	}

	@PostMapping("/tasks")
	public ResponseEntity<Void> saveCategory(@RequestBody TasksDTO tasksDTO) {

		tasksService.saveTask(tasksDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Void> updateTask(@RequestBody TasksDTO tasksDTO, @PathVariable Integer id) {

		tasksService.editTask(id, tasksDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/tasks/alltocomplete")
	public ResponseEntity<Void> updateTasksToComplete() {
		tasksService.editDiaryToComplete();
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/tasks/tasktopendente/{id}")
	public ResponseEntity<Void> updateTaskToPendente(@PathVariable Integer id) {
		tasksService.editDiaryToPendente(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/tasks/tasktocomplete/{id}")
	public ResponseEntity<Void> updateTaskToComplete(@PathVariable Integer id) {
		tasksService.editDiaryToComplete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/tasks/repete/{id}")
	public ResponseEntity<Void> updateTaskToRepete(@PathVariable Integer id){
		tasksService.editRepete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<TasksDTO> getTasksById(@PathVariable Integer id) {
		TasksDTO tasksDTO = tasksService.getTaskById(id);
		return ResponseEntity.ok().body(tasksDTO);
	}

	@GetMapping("/tasks/all")
	public ResponseEntity<List<TasksDTO>> getAllTasks() {
		List<TasksDTO> tasksDTO = tasksService.getAllTasks();
		return ResponseEntity.ok().body(tasksDTO);
	}

	@GetMapping("/tasks/diary")
	public ResponseEntity<List<TasksDTO>> getTasksDiary() {
		List<TasksDTO> tasksDTO = tasksService.getTasksDiary();
		return ResponseEntity.ok().body(tasksDTO);
	}

	@GetMapping("/tasks/diary/{date}")
	public ResponseEntity<List<TasksDTO>> getTasksDiaryByDate(@PathVariable String date) {
		LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);// YYYY-MM-DD

		List<TasksDTO> tasksDTO = tasksService.getTasksDiaryByDate(parsedDate);
		return ResponseEntity.ok().body(tasksDTO);
	}

	@GetMapping("/tasks/diarycomplete")
	public ResponseEntity<List<TasksDTO>> getTasksDiaryComplete() {
		List<TasksDTO> tasksDTOs = tasksService.getTasksDiaryComplete();
		return ResponseEntity.ok().body(tasksDTOs);
	}

	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
		tasksService.deleteTasks(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/tasks")
	public ResponseEntity<Void> deleteAllTask() {
		tasksService.deleteAllDiary();
		return ResponseEntity.noContent().build();
	}
}
