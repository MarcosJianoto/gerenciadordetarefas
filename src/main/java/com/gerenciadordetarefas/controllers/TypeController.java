package com.gerenciadordetarefas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciadordetarefas.dto.TypeDTO;
import com.gerenciadordetarefas.services.TypeService;

@RestController
public class TypeController {

	private final TypeService typeService;

	public TypeController(TypeService typeService) {
		this.typeService = typeService;
	}

	@PostMapping("/type")
	public ResponseEntity<Void> saveCategory(@RequestBody TypeDTO typeDTO) {

		typeService.saveType(typeDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/type/{id}")
	public ResponseEntity<Void> updateType(@RequestBody TypeDTO typeDTO, @PathVariable Integer id) {

		typeService.editType(id, typeDTO);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/type/{id}")
	public ResponseEntity<TypeDTO> getTypeById(@PathVariable Integer id) {
		TypeDTO typeDTO = typeService.geTypeDTO(id);
		return ResponseEntity.ok().body(typeDTO);
	}

	@DeleteMapping("/type/{id}")
	public ResponseEntity<Void> deleteType(@PathVariable Integer id) {
		typeService.deleteType(id);
		return ResponseEntity.noContent().build();
	}
}
