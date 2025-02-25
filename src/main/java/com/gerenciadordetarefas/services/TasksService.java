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
		tasksFindById.setDateHourCreation(LocalDateTime.now());

		tasksFindById.setDateHourComplete(
				Objects.equals(tasksDTO.getStatus(), StatusEnum.CONCLUIDA) ? LocalDateTime.now() : null);

		tasksFindById.setDateHourEdit(LocalDateTime.now());

		tasksRepository.save(tasksFindById);

	}

	public TasksDTO getTasksDTO(Integer id) {

		Tasks tasksFindById = tasksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("task not found"));

		TasksDTO tasksDTO = new TasksDTO();
		tasksDTO.setId(id);
		tasksDTO.setStatus(tasksFindById.getStatus().toString());
		tasksDTO.setDescription(tasksFindById.getDescription());
		tasksDTO.setTypeId(tasksFindById.getTypeId().getId());
		tasksDTO.setDateHourCreation(tasksFindById.getDateHourCreation().toString());
		tasksDTO.setDateHourEdit(tasksFindById.getDateHourEdit().toString());
		tasksDTO.setDateHourComplete(tasksFindById.getDateHourComplete().toString());

		return tasksDTO;
	}

	public List<TasksDTO> getTasks() {

		List<Tasks> tasksFindById = tasksRepository.findAll();
		List<TasksDTO> listDtos = new ArrayList<>();

		if (!tasksFindById.isEmpty()) {

			for (Tasks task : tasksFindById) {
				TasksDTO taskDTO = new TasksDTO();
				taskDTO.setId(task.getId());
				taskDTO.setStatus(task.getStatus().toString());
				taskDTO.setDescription(task.getDescription());
				taskDTO.setTypeId(task.getTypeId().getId());
				taskDTO.setDateHourCreation(task.getDateHourCreation().toString());
				taskDTO.setDateHourEdit(task.getDateHourEdit().toString());
				taskDTO.setDateHourComplete(task.getDateHourComplete().toString());
				listDtos.add(taskDTO);
			}

		}

		return listDtos;
	}

	public List<TasksDTO> getTasksDiary() {

		LocalDate today = LocalDate.now();
		LocalDateTime start = today.atStartOfDay();
		LocalDateTime finish = today.atTime(23, 59, 59);

		List<Tasks> tasksFindByDiary = tasksRepository.findByDateHourCreationBetween(start, finish);
		List<TasksDTO> listDtos = new ArrayList<>();

		if (!tasksFindByDiary.isEmpty()) {

			for (Tasks task : tasksFindByDiary) {
				TasksDTO taskDTO = new TasksDTO();
				taskDTO.setId(task.getId());
				taskDTO.setStatus(task.getStatus().toString());
				taskDTO.setDescription(task.getDescription());
				taskDTO.setTypeId(task.getTypeId().getId());
				taskDTO.setDateHourCreation(task.getDateHourCreation().toString());
				taskDTO.setDateHourEdit(task.getDateHourEdit().toString());
				taskDTO.setDateHourComplete(task.getDateHourComplete().toString());
				listDtos.add(taskDTO);
			}

		}

		return listDtos;
	}

	public void deleteTasks(Integer id) {
		tasksRepository.deleteById(id);
	}

}
