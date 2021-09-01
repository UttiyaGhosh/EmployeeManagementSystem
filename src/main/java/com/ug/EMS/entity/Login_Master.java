package com.ug.EMS.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login_Master {

	@Id
	private int userid;
	private String password;
	private String role;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public boolean equals(Object login_Master) {

		if (login_Master == this)
			return true;

		if (!(login_Master instanceof Login_Master))
			return false;

		Login_Master newObj = (Login_Master) login_Master;
		if (this.getUserid() == newObj.getUserid() && this.password.equals(newObj.getPassword())
				&& this.role.equals(newObj.getRole()))
			return true;

		return false;

	}
}
