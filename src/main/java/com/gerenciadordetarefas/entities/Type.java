package com.gerenciadordetarefas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_sequence")
	@SequenceGenerator(name = "type_sequence", sequenceName = "type_sequence", allocationSize = 1)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TypeEnum typeEnum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(TypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

}
