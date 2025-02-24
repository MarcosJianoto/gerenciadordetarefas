package com.gerenciadordetarefas.dto;

import com.gerenciadordetarefas.entities.TypeEnum;

public class TypeDTO {

	private Integer id;

	private TypeEnum type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

}
