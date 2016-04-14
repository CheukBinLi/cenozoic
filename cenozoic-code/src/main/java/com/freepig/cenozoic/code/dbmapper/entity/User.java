package com.freepig.cenozoic.code.dbmapper.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "RES_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	/**手机号*/
	@Column(unique = true)
	private String phone;
	/** 密码 */
	private String password;
	/**  姓名 */
	private String name;
	/** 权限 */
	private int permissions;

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

	public int getPermissions() {
		return permissions;
	}

	public User setPermissions(int permissions) {
		this.permissions = permissions;
		return this;
	}

	public User() {
		super();
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User(String phone, String password) {
		super();
		this.phone = phone;
		this.password = password;
	}

	public User(String phone, String password, String name) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
	}

}
