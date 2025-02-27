package com.gerenciadordetarefas.entities;

import java.time.LocalDateTime;

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
@Table(name = "tasks")
public class Tasks {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
	@SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;
	
	@Column(name = "date_hour_creation")
	private LocalDateTime dateHourCreation;

	@Column(name = "date_hour_complete")
	private LocalDateTime dateHourComplete;

	@Column(name = "date_hour_edit")
	private LocalDateTime dateHourEdit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String  getStatus() {
		return status;
	}

	public void setStatus(String  status) {
		this.status = status;
	}

	public LocalDateTime getDateHourCreation() {
		return dateHourCreation;
	}

	public void setDateHourCreation(LocalDateTime dateHourCreation) {
		this.dateHourCreation = dateHourCreation;
	}

	public LocalDateTime getDateHourComplete() {
		return dateHourComplete;
	}

	public void setDateHourComplete(LocalDateTime dateHourComplete) {
		this.dateHourComplete = dateHourComplete;
	}

	public LocalDateTime getDateHourEdit() {
		return dateHourEdit;
	}

	public void setDateHourEdit(LocalDateTime dateHourEdit) {
		this.dateHourEdit = dateHourEdit;
	}
	
	

}
