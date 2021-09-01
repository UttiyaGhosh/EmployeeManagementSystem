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
import javax.persistence.Transient;

@Entity
public class Compliance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int complianceId;
	
	private String rlType;
	
	private String details;
	
	@Transient
	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@ManyToOne
	private Department department;

	@OneToMany(mappedBy = "compliance")
	private List<StatusReport> statusReports;
	
	public int getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(int complianceId) {
		this.complianceId = complianceId;
	}

	public String getRlType() {
		return rlType;
	}

	public void setRlType(String rlType) {
		this.rlType = rlType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<StatusReport> getStatusReports() {
		return statusReports;
	}

	public void setStatusReports(List<StatusReport> statusReports) {
		this.statusReports = statusReports;
	}
	
	
}
