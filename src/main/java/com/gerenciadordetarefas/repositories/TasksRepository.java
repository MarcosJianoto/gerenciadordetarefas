package com.gerenciadordetarefas.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadordetarefas.entities.Tasks;
import com.gerenciadordetarefas.entities.StatusEnum;


@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

	List<Tasks> findByDateHourCreationBetween(LocalDateTime start, LocalDateTime finish);

	List<Tasks> findByDateHourCompleteBetween(LocalDateTime start, LocalDateTime finish);
	
	List<Tasks> findByStatusIn(List<StatusEnum> statusEnum);
	
	void deleteByDateHourCreationBetween(LocalDateTime start, LocalDateTime finish);

}
