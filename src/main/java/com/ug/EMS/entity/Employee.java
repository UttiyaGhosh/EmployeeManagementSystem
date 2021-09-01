package com.ug.EMS.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empid;
	
	private String firstName;
	
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	private String email;
	
	@ManyToOne
	private Department department;
	
	@OneToMany(mappedBy = "employee")
	private List<StatusReport> statusReports;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<StatusReport> getStatusReports() {
		return statusReports;
	}

	public void setStatusReports(List<StatusReport> statusReports) {
		this.statusReports = statusReports;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", " + (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "") + (dob != null ? "dob=" + dob + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (department != null ? "department=" + department + ", " : "")
				+ (statusReports != null ? "statusReports=" + statusReports : "") + "]";
	}

	
}
