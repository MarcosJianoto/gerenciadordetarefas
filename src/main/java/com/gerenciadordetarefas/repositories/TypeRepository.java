package com.gerenciadordetarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadordetarefas.entities.Type;

@Repository
public interface TypeRepository extends  JpaRepository<Type, Integer>{

}
