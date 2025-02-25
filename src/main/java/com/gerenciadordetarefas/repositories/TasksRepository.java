package com.gerenciadordetarefas.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadordetarefas.entities.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

	List<Tasks> findByDateHourCreationBetween(LocalDateTime start, LocalDateTime finish);

}
