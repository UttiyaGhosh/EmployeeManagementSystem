package com.ug.EMS.controller;

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
import com.ug.EMS.service.ComplianceService;
import com.ug.EMS.service.DepartmentService;
import com.ug.EMS.service.EmployeeService;

@RestController
public class ComplianceController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ComplianceService complianceService;

	private static Logger logger = Logger.getLogger(ComplianceController.class);

	@GetMapping("/alterCompliance")
	public ModelAndView altercompliance(@RequestParam("act") String action, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("departmentList", departmentService.getAllDepartments());
			if (action.equals("add")) {
				mv.setViewName("editCompliance");
			} else if (action.equals("edit")) {
				mv.setViewName("editCompliance");
				mv.addObject("compliance",
						complianceService.getcompliance(Integer.parseInt(request.getParameter("id"))));
			} else {
				complianceService.deletecompliance(Integer.parseInt(request.getParameter("id")));
				mv.setViewName("adminHome");
				mv.addObject("employeeList", employeeService.getAllEmployees());
				mv.addObject("departmentList", departmentService.getAllDepartments());
				mv.addObject("complianceList", complianceService.getAllCompliances());
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

	@PostMapping("/saveCompliance")
	public ModelAndView editcompliance(@RequestParam("complianceId") String complianceidStr,
			@RequestParam("rlType") String rlType, @RequestParam("details") String details,
			@RequestParam("departmentid") String departmentIdStr, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String createDateStr = request.getParameter("createDate");
			System.out.println(createDateStr);
			Date createDate = sdf.parse(createDateStr);

			Compliance compliance = new Compliance();
			if (complianceidStr != "")
				compliance.setComplianceId(Integer.parseInt(complianceidStr));
			compliance.setRlType(rlType);
			compliance.setDetails(details);
			compliance.setCreateDate(createDate);
			compliance.setDepartment(departmentService.getDepartment(Integer.parseInt(departmentIdStr)));

			if (rlType == "" || details == "" || createDate == null) {
				mv.setViewName("editcompliance");
				mv.addObject("message", "Please fill all the details");
				mv.addObject("compliance", compliance);
			} else {
				complianceService.savecompliance(compliance);
				mv.setViewName("adminHome");
				mv.addObject("employeeList", employeeService.getAllEmployees());
				mv.addObject("departmentList", departmentService.getAllDepartments());
				mv.addObject("complianceList", complianceService.getAllCompliances());

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
