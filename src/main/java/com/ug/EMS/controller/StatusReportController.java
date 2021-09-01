package com.ug.EMS.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ug.EMS.entity.Compliance;
import com.ug.EMS.entity.Department;
import com.ug.EMS.entity.Employee;
import com.ug.EMS.entity.StatusReport;
import com.ug.EMS.service.ComplianceService;
import com.ug.EMS.service.DepartmentService;
import com.ug.EMS.service.EmployeeService;
import com.ug.EMS.service.StatusReportService;

@RestController
public class StatusReportController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ComplianceService complianceService;

	@Autowired
	private StatusReportService statusReportService;

	private static Logger logger = Logger.getLogger(StatusReportController.class);

	@GetMapping("/alterStatusReport")
	public ModelAndView altercompliance(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			int complianceId = Integer.parseInt(request.getParameter("cid"));
			int empid = Integer.parseInt(request.getParameter("eid"));
			Employee employee = employeeService.getEmployee(empid);

			mv.setViewName("editStatusReport");
			mv.addObject("compliance", complianceService.getcompliance(complianceId));
			mv.addObject("statusReport", statusReportService.getStatusReportByCompAndEmp(complianceId, empid));
			mv.addObject("empid", empid);
			mv.addObject("departmentId", employee.getDepartment().getDepartmentId());
		} catch (Exception e) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error occurred. Please check logs.", e);
			mv.setStatus(status);
			mv.setViewName("error");
			mv.addObject("exception", e);
			mv.addObject("code", status.value());
			mv.addObject("message", status.getReasonPhrase());
		}
		return mv;
	}

	@PostMapping("/saveStatusReport")
	public ModelAndView editcompliance(@RequestParam("complianceId") String complianceIdStr,
			@RequestParam("comments") String comments, @RequestParam("empid") String empIdStr,
			@RequestParam("departmentId") String departmentIdStr, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			int empid = Integer.parseInt(empIdStr);
			int complianceId = Integer.parseInt(complianceIdStr);
			int departmentId = Integer.parseInt(departmentIdStr);

			Employee employee = employeeService.getEmployee(empid);
			Department department = departmentService.getDepartment(departmentId);
			Compliance compliance = complianceService.getcompliance(complianceId);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String submissionDateStr = request.getParameter("submissionDate");
			Date submissionDate;

			try {
				submissionDate = sdf.parse(submissionDateStr);
			} catch (ParseException e) {
				submissionDate = null;
			}

			StatusReport statusReport = statusReportService.getStatusReportByCompAndEmp(complianceId, empid);
			if (statusReport == null)
				statusReport = new StatusReport();
			statusReport.setComments(comments);
			statusReport.setCreateDate(submissionDate);
			statusReport.setDepartment(department);
			statusReport.setEmployee(employee);
			statusReport.setCompliance(compliance);

			if (comments == "" || submissionDate == null) {
				mv.setViewName("editStatusReport");
				mv.addObject("message", "Please fill all the details");
				mv.addObject("compliance", complianceService.getcompliance(complianceId));
				mv.addObject("statusReport", statusReportService.getStatusReportByCompAndEmp(complianceId, empid));
				mv.addObject("empid", empid);
				mv.addObject("departmentId", departmentId);
			} else {
				statusReportService.saveStatusReport(statusReport);
				mv.setViewName("employeeHome");
				mv.addObject("employee", employee);
				mv.addObject("complianceList", complianceService.getCompliancesByEmployee(employee));

			}
		} catch (Exception e) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Error occurred. Please check logs.", e);
			mv.setStatus(status);
			mv.setViewName("error");
			mv.addObject("exception", e);
			mv.addObject("code", status.value());
			mv.addObject("message", status.getReasonPhrase());
		}

		return mv;
	}
}
