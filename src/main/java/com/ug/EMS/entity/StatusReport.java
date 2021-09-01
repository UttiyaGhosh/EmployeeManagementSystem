package com.ug.EMS.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class StatusReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusRptId;
	
	private String comments;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@ManyToOne
	private Department department;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Compliance compliance;

	public int getStatusRptId() {
		return statusRptId;
	}

	public void setStatusRptId(int statusRptId) {
		this.statusRptId = statusRptId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Compliance getCompliance() {
		return compliance;
	}

	public void setCompliance(Compliance compliance) {
		this.compliance = compliance;
	}
	
	
}
