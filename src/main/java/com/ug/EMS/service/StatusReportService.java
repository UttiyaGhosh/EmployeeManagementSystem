package com.ug.EMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ug.EMS.dao.StatusReportDao;
import com.ug.EMS.entity.StatusReport;

@Service
public class StatusReportService {

	@Autowired
	private StatusReportDao statusReportDao;

	@Transactional
	public StatusReport getStatusReportByCompAndEmp(int complianceId, int empid) {

		StatusReport statusReport = statusReportDao.getStatusReportByCompAndEmp(complianceId,empid);
		return statusReport;
	}
	
	@Transactional
	public void saveStatusReport(StatusReport statusReport) {
		
		statusReportDao.saveStatusReport(statusReport);
		
	}
	
}
