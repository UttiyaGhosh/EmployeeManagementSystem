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

import com.ug.EMS.entity.Employee;
import com.ug.EMS.entity.Login_Master;
import com.ug.EMS.service.ComplianceService;
import com.ug.EMS.service.DepartmentService;
import com.ug.EMS.service.EmployeeService;
import com.ug.EMS.service.loginMasterService;

@RestController
public class EmployeeController {

	@Autowired
	private loginMasterService loginMasterService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ComplianceService complianceService;

	private static Logger logger = Logger.getLogger(EmployeeController.class);

	@GetMapping("/alterEmployee")
	public ModelAndView alterEmployee(@RequestParam("act") String action, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("departmentList", departmentService.getAllDepartments());
			if (action.equals("add")) {
				mv.setViewName("editEmployee");
			} else if (action.equals("edit")) {
				int empid = Integer.parseInt(request.getParameter("id"));
				mv.setViewName("editEmployee");
				mv.addObject("employee", employeeService.getEmployee(empid));
				mv.addObject("loginMaster", loginMasterService.getLoginMaster(empid));
			} else {
				int empid = Integer.parseInt(request.getParameter("id"));
				loginMasterService.deleteLoginMaster(empid);
				employeeService.deleteEmployee(empid);
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

	@PostMapping("/saveEmployee")
	public ModelAndView editEmployee(@RequestParam("empid") String empidStr,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("departmentid") String departmentIdStr, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String dobStr = request.getParameter("dob");
			Date dob;

			try {
				dob = sdf.parse(dobStr);
			} catch (ParseException e) {
				dob = null;
			}

			Employee employee = new Employee();
			if (empidStr != "")
				employee.setEmpid(Integer.parseInt(empidStr));
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setDob(dob);
			employee.setEmail(email);
			employee.setDepartment(departmentService.getDepartment(Integer.parseInt(departmentIdStr)));

			if (firstName == "" || lastName == "" || email == "" || dob == null || password == "") {
				mv.setViewName("editEmployee");
				mv.addObject("message", "Please fill all the details");
				mv.addObject("employee", employee);
			} else {

				employeeService.saveEmployee(employee);

				Login_Master login_Master = new Login_Master();
				login_Master.setUserid(employee.getEmpid());
				login_Master.setPassword(password);
				login_Master.setRole("user");

				loginMasterService.saveLoginMaster(login_Master);

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
