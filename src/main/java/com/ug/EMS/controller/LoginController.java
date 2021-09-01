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

import com.ug.EMS.entity.Employee;
import com.ug.EMS.entity.Login_Master;
import com.ug.EMS.service.ComplianceService;
import com.ug.EMS.service.DepartmentService;
import com.ug.EMS.service.EmployeeService;
import com.ug.EMS.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ComplianceService complianceService;

	private static Logger logger = Logger.getLogger(LoginController.class);

	@GetMapping("/")
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("index");
			
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

	@PostMapping("/login")
	public ModelAndView loginUser(@RequestParam("userid") String userid, @RequestParam("password") String password,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {
			request.getSession().setAttribute("userid", userid);
			Employee employee = new Employee();
			Login_Master login_Master = new Login_Master();
			String role = request.getParameter("role"), loginMessage = "Wrong Credentials";

			if (userid == "" || password == "" || role == null)
				loginMessage = "Please fill all the details";
			else
				try {

					employee = employeeService.getEmployee(Integer.parseInt(userid));
					if (employee == null) {
						loginMessage = "User ID does not exist";
					} else {

						login_Master.setUserid(employee.getEmpid());
						login_Master.setPassword(password);
						login_Master.setRole(role);

						loginMessage = loginService.checkUser(login_Master) ? "Success" : "Wrong Credentials";
					}

				} catch (NumberFormatException e) {
					loginMessage = "User ID must be integer";
				}

			if (loginMessage.equals("Success")) {

				if (login_Master.getRole().equals("admin")) {

					mv.setViewName("adminHome");
					mv.addObject("employeeList", employeeService.getAllEmployees());
					mv.addObject("departmentList", departmentService.getAllDepartments());
					mv.addObject("complianceList", complianceService.getAllCompliances());
				} else {
					mv.setViewName("employeeHome");
					mv.addObject("employee", employee);
					mv.addObject("complianceList", complianceService.getCompliancesByEmployee(employee));
				}
			} else {
				mv.setViewName("index");
				mv.addObject("message", loginMessage);
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

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		try {

			request.getSession().invalidate();
			mv.setViewName("index");
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
