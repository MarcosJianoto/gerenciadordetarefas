package com.gerenciadordetarefas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gerenciadordetarefas.dto.TasksDTO;
import com.gerenciadordetarefas.entities.StatusEnum;
import com.gerenciadordetarefas.entities.Tasks;
import com.gerenciadordetarefas.entities.Type;
import com.gerenciadordetarefas.repositories.TasksRepository;
import com.gerenciadordetarefas.repositories.TypeRepository;

@Service
public class TasksService {

	private final TasksRepository tasksRepository;
	private final TypeRepository typeRepository;

	public TasksService(TasksRepository tasksRepository, TypeRepository typeRepository) {
		this.tasksRepository = tasksRepository;
		this.typeRepository = typeRepository;
	}

	public void saveTask(TasksDTO tasksDTO) {

		Type typeFindById = typeRepository.findById(tasksDTO.getTypeId())
				.orElseThrow(() -> new IllegalArgumentException("Type not found"));

		Tasks tasks = new Tasks();
		tasks.setTypeId(typeFindById);
		tasks.setDescription(tasksDTO.getDescription());
		tasks.setStatus(StatusEnum.valueOf(tasksDTO.getStatus()));
		tasks.setDateHourCreation(LocalDateTime.now());

		tasks.setDateHourComplete(
				Objects.equals(tasksDTO.getStatus(), StatusEnum.CONCLUIDA) ? LocalDateTime.now() : null);

		tasks.setDateHourEdit(LocalDateTime.now());

		tasksRepository.save(tasks);

	}

	public void editTask(Integer id, TasksDTO tasksDTO) {

		Tasks tasksFindById = tasksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Task not found"));

		Type typeFindById = typeRepository.findById(tasksDTO.getTypeId())
				.orElseThrow(() -> new IllegalArgumentException("Type not found"));

		tasksFindById.setTypeId(typeFindById);
		tasksFindById.setDescription(tasksDTO.getDescription());
		tasksFindById.setStatus(StatusEnum.valueOf(tasksDTO.getStatus()));

		tasksFindById.setDateHourComplete(
				Objects.equals(tasksDTO.getStatus(), StatusEnum.CONCLUIDA) ? LocalDateTime.now() : null);

		tasksFindById.setDateHourEdit(LocalDateTime.now());

		tasksRepository.save(tasksFindById);

	}

	private TasksDTO convertToDTO(Tasks task) {
		TasksDTO dto = new TasksDTO();
		dto.setId(task.getId());
		dto.setStatus(task.getStatus().toString());
		dto.setDescription(task.getDescription());
		dto.setTypeId(task.getTypeId().getId());
		dto.setDateHourCreation(task.getDateHourCreation().toString());
		dto.setDateHourEdit(task.getDateHourEdit().toString());
		dto.setDateHourComplete(task.getDateHourComplete() != null ? task.getDateHourComplete().toString() : null);
		return dto;
	}

	public TasksDTO getTaskById(Integer id) {
		
		Tasks tasksFindById = tasksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("task not found"));
		TasksDTO tasksDTO = convertToDTO(tasksFindById);
		
		return tasksDTO;
	}

	public List<TasksDTO> getAllTasks() {
		return tasksRepository.findAll().stream().map(this::convertToDTO).toList();
	}

	private List<TasksDTO> getTasksByDateRange(LocalDateTime start, LocalDateTime finish) {
		
		List<Tasks> tasksFindByDiary = tasksRepository.findByDateHourCreationBetween(start, finish);
		List<TasksDTO> listDtos = new ArrayList<>();

		for (Tasks tasks : tasksFindByDiary) {
			listDtos.add(convertToDTO(tasks));
		}
		return listDtos;
	}

	public List<TasksDTO> getTasksDiary() {
		LocalDate today = LocalDate.now();
		return getTasksByDateRange(today.atStartOfDay(), today.atTime(23, 59, 59));
	}

	public List<TasksDTO> getTasksDiaryByDate(LocalDate date) {
		return getTasksByDateRange(date.atStartOfDay(), date.atTime(23, 59, 59));
	}

	public void deleteTasks(Integer id) {
		if (!tasksRepository.existsById(id)) {
			throw new IllegalArgumentException("Task not found");
		}

		tasksRepository.deleteById(id);
	}

}
