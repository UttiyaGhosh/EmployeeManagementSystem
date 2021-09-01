package com.ug.EMS.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ug.EMS.entity.Department;
import com.ug.EMS.service.ComplianceService;
import com.ug.EMS.service.DepartmentService;
import com.ug.EMS.service.EmployeeService;

@RestController
public class DepartmentController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ComplianceService complianceService;

	private static Logger logger = Logger.getLogger(DepartmentController.class);

	@GetMapping("/alterDepartment")
	public ModelAndView alterEmployee(@RequestParam("act") String action, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			if (action.equals("add")) {
				mv.setViewName("editDepartment");
			} else if (action.equals("edit")) {
				mv.setViewName("editDepartment");
				mv.addObject("department",
						departmentService.getDepartment(Integer.parseInt(request.getParameter("id"))));
			} else {
				departmentService.deleteDepartment(Integer.parseInt(request.getParameter("id")));
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

	@PostMapping("/saveDepartment")
	public ModelAndView editEmployee(@RequestParam("departmentid") String departmentidStr,
			@RequestParam("departmentName") String departmentName, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			Department department = new Department();
			if (departmentidStr != "")
				department.setDepartmentId(Integer.parseInt(departmentidStr));
			department.setDepartmentName(departmentName);

			if (departmentName == "") {
				mv.setViewName("editDepartment");
				mv.addObject("message", "Please fill all the details");
				mv.addObject("department", department);
			} else {
				departmentService.saveDepartment(department);
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
