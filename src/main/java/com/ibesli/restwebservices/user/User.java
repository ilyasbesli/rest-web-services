package com.ibesli.restwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Detail about user")
public class User {

	private Integer id;

	@Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "Birth date should have at least 2 characterst")
	private String name;

	@Past
    @ApiModelProperty(notes = "Birth date should be in the past")
	private Date birthDate;

	public User() {

	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("User [idInteger=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
