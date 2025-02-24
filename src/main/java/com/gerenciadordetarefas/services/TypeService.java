package com.gerenciadordetarefas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadordetarefas.dto.TypeDTO;
import com.gerenciadordetarefas.entities.Type;
import com.gerenciadordetarefas.repositories.TypeRepository;

@Service
public class TypeService {

	private final TypeRepository typeRepository;

	@Autowired
	public TypeService(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	public void saveType(TypeDTO typeDTO) {

		Type type = new Type();
				
		
	}

}
