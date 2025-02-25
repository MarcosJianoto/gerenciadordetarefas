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
		type.setTypeEnum(typeDTO.getType());

		typeRepository.save(type);

	}

	public void editType(Integer id, TypeDTO typeDTO) {

		Type typeFindById = typeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Type not found"));

		typeFindById.setTypeEnum(typeDTO.getType());
		typeRepository.save(typeFindById);

	}

	public TypeDTO geTypeDTO(Integer id) {

		Type typeFindById = typeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("type not found"));

		TypeDTO typeDTO = new TypeDTO();
		typeDTO.setId(id);
		typeDTO.setType(typeFindById.getTypeEnum());

		return typeDTO;

	}

	public void deleteType(Integer id) {
		typeRepository.deleteById(id);
	}

}
