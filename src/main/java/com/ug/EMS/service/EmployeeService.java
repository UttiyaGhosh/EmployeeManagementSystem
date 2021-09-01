package com.ug.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ug.EMS.dao.EmployeeDao;
import com.ug.EMS.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDAO;

	@Transactional
	public List<Employee> getAllEmployees() {

		List<Employee> employeeList= employeeDAO.getAllEmployees();

		return employeeList;
	}

	@Transactional
	public Employee getEmployee(int empid) {
		
		Employee employee = employeeDAO.getEmployee(empid);
		
		return employee;
	}

	@Transactional
	public void saveEmployee(Employee employee) {
		
		employeeDAO.saveEmployee(employee);
		
	}

	@Transactional
	public void deleteEmployee(int empid) {
		
		employeeDAO.deleteEmployee(empid);
		
	}

}
