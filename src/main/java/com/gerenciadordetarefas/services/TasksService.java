package com.gerenciadordetarefas.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gerenciadordetarefas.dto.TasksDTO;
import com.gerenciadordetarefas.entities.StatusEnum;
import com.gerenciadordetarefas.entities.Tasks;
import com.gerenciadordetarefas.repositories.TasksRepository;

import jakarta.transaction.Transactional;

@Service
public class TasksService {

	private final TasksRepository tasksRepository;

	public TasksService(TasksRepository tasksRepository) {
		this.tasksRepository = tasksRepository;
	}

	public void saveTask(TasksDTO tasksDTO) {

		Tasks tasks = new Tasks();
		tasks.setDescription(tasksDTO.getDescription());
		tasks.setStatus(StatusEnum.valueOf(tasksDTO.getStatus()));
		tasks.setDateHourCreation(LocalDateTime.now());

		tasks.setDateHourComplete(
				Objects.equals(tasksDTO.getStatus(), StatusEnum.CONCLUIDA.toString()) ? LocalDateTime.now() : null);

		tasks.setDateHourEdit(LocalDateTime.now());

		tasksRepository.save(tasks);

	}

	public void editTask(Integer id, TasksDTO tasksDTO) {

		Tasks tasksFindById = tasksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Task not found"));

		tasksFindById.setDescription(tasksDTO.getDescription());
		tasksFindById.setStatus(StatusEnum.valueOf(tasksDTO.getStatus()));

		tasksFindById.setDateHourComplete(
				Objects.equals(tasksDTO.getStatus(), StatusEnum.CONCLUIDA.toString()) ? LocalDateTime.now() : null);

		tasksFindById.setDateHourEdit(LocalDateTime.now());

		tasksRepository.save(tasksFindById);

	}

	private TasksDTO convertToDTO(Tasks task) {
		TasksDTO dto = new TasksDTO();
		dto.setId(task.getId());
		dto.setStatus(task.getStatus().toString());
		dto.setDescription(task.getDescription());
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

			if (tasks.getStatus().equals(StatusEnum.EM_ANDAMENTO) || tasks.getStatus().equals(StatusEnum.PENDENTE)) {
				listDtos.add(convertToDTO(tasks));

			}
		}
		return listDtos;
	}

	public List<TasksDTO> getTasksDiary() {
		LocalDate today = LocalDate.now();
		return getTasksByDateRange(today.atStartOfDay(), today.atTime(23, 59, 59));
	}

	public List<TasksDTO> getTasksDiaryByDate(LocalDate date) {
		return getTasksByDateRange(date.atStartOfDay(), date.atTime(LocalTime.MAX));
	}

	public void deleteTasks(Integer id) {
		if (!tasksRepository.existsById(id)) {
			throw new IllegalArgumentException("Task not found");
		}

		tasksRepository.deleteById(id);
	}

	public List<TasksDTO> getTasksDiaryComplete() {
		LocalDate today = LocalDate.now();

		List<Tasks> tasksDiaryComplete = tasksRepository.findByDateHourCompleteBetween(today.atStartOfDay(),
				today.atTime(LocalTime.MAX));

		List<TasksDTO> tasksDTOs = new ArrayList<>();
		for (Tasks tasks : tasksDiaryComplete) {
			tasksDTOs.add(convertToDTO(tasks));
		}

		return tasksDTOs;
	}

	public void editDiaryToComplete() {

		LocalDate today = LocalDate.now();

		List<Tasks> taskFindStatus = tasksRepository
				.findByStatusIn(Arrays.asList(StatusEnum.EM_ANDAMENTO, StatusEnum.PENDENTE));

		for (Tasks task : taskFindStatus) {

			if (task.getDateHourCreation().toLocalDate().equals(today)) {

				task.setStatus(StatusEnum.CONCLUIDA);
				task.setDateHourEdit(LocalDateTime.now());
				task.setDateHourComplete(LocalDateTime.now());

				tasksRepository.save(task);
			}

		}

	}

	public void editDiaryToPendente(Integer id) {

		Tasks taskFindStatus = tasksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id not found"));

		Tasks tasks = new Tasks();
		tasks.setDescription(taskFindStatus.getDescription());
		tasks.setStatus(StatusEnum.PENDENTE);
		tasks.setDateHourCreation(LocalDateTime.now());
		tasks.setDateHourComplete(null);

		tasks.setDateHourEdit(LocalDateTime.now());

		tasksRepository.save(tasks);
		tasksRepository.deleteById(id);

	}

	public void editDiaryToComplete(Integer id) {

		Tasks taskFindStatus = tasksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id not found"));

		taskFindStatus.setStatus(StatusEnum.CONCLUIDA);
		taskFindStatus.setDateHourEdit(LocalDateTime.now());
		taskFindStatus.setDateHourComplete(LocalDateTime.now());

		tasksRepository.save(taskFindStatus);

	}

	@Transactional
	public void deleteAllDiary() {

		LocalDate today = LocalDate.now();
		tasksRepository.deleteByDateHourCreationBetween(today.atStartOfDay(), today.atTime(LocalTime.MAX));
	}

}
