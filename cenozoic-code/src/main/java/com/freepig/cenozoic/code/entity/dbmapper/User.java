package com.freepig.cenozoic.code.entity.dbmapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "RES_USER")
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String phone;
	private String password;
	private String name;

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public User setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

}
