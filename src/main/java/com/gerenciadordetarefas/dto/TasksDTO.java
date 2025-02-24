package com.gerenciadordetarefas.dto;

public class TasksDTO {

	private Integer id;

	private Integer typeId;

	private String description;

	private String status;

	private String dateHourCreation;

	private String dateHourComplete;

	private String dateHourEdit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateHourCreation() {
		return dateHourCreation;
	}

	public void setDateHourCreation(String dateHourCreation) {
		this.dateHourCreation = dateHourCreation;
	}

	public String getDateHourComplete() {
		return dateHourComplete;
	}

	public void setDateHourComplete(String dateHourComplete) {
		this.dateHourComplete = dateHourComplete;
	}

	public String getDateHourEdit() {
		return dateHourEdit;
	}

	public void setDateHourEdit(String dateHourEdit) {
		this.dateHourEdit = dateHourEdit;
	}
	
	

}
