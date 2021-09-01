package com.ug.EMS.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;
	
	private String departmentName;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
	
	@OneToMany(mappedBy = "department")
	private List<StatusReport> statusReports;
	
	@OneToMany(mappedBy = "department")
	private List<Compliance>compliances;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<StatusReport> getStatusReports() {
		return statusReports;
	}

	public void setStatusReports(List<StatusReport> statusReports) {
		this.statusReports = statusReports;
	}

	public List<Compliance> getCompliances() {
		return compliances;
	}

	public void setCompliances(List<Compliance> compliances) {
		this.compliances = compliances;
	}
	
	
}
