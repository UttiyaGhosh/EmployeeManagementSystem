package com.ug.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ug.EMS.dao.ComplianceDao;
import com.ug.EMS.dao.StatusReportDao;
import com.ug.EMS.entity.Compliance;
import com.ug.EMS.entity.Employee;
import com.ug.EMS.entity.StatusReport;

@Service
public class ComplianceService {

	@Autowired
	private loginMasterService loginMasterService;
	
	@Autowired
	private ComplianceDao complianceDao;
	
	@Autowired
	private StatusReportDao statusReportDao;
	
	@Transactional
	public List<Compliance> getAllCompliances() {

		List<Compliance> complianceList= complianceDao.getAllCompliances();
		for (Compliance compliance:complianceList)
			compliance.setStatus(findStatus(compliance));
		return complianceList;
	}
	
	@Transactional
	public Compliance getcompliance(int complianceid) {
		
		Compliance compliance = complianceDao.getCompliance(complianceid);
		compliance.setStatus(findStatus(compliance));
		return compliance;
	}

	@Transactional
	public void savecompliance(Compliance compliance) {
		
		complianceDao.saveCompliance(compliance);
		
	}

	@Transactional
	public void deletecompliance(int complianceid) {
		
		Compliance compliance = complianceDao.getCompliance(complianceid);
		for(StatusReport statusReport:compliance.getStatusReports())
			statusReportDao.deleteStatusReport(statusReport);
		complianceDao.deleteCompliance(compliance);
		
	}
	
	public String findStatus(Compliance compliance) {
		
		int nUsers=0;
		List<Employee> employees =  compliance.getDepartment().getEmployees();
		
		for (Employee employee:employees)
			if(loginMasterService.getLoginMaster(employee.getEmpid()).getRole().equals("user"))
				nUsers++;
		
		if(compliance.getStatusReports().size()==nUsers)
			return "closed";
			
		return "open";
	}

	@Transactional
	public List<Compliance> getCompliancesByEmployee(Employee employee) {

		int departmentid = employee.getDepartment().getDepartmentId();
		List<Compliance> complianceList = complianceDao.getAllcompliancesByDept(departmentid);
		for (Compliance compliance:complianceList)
			compliance.setStatus(findStatus(compliance));
		return complianceList;
	}
}
