package com.ug.EMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ug.EMS.dao.DepartmentDao;
import com.ug.EMS.entity.Department;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Transactional
	public List<Department> getAllDepartments() {

		List<Department> departmentList= departmentDao.getAllDepartments();
		return departmentList;
	}
	
	@Transactional
	public Department getDepartment(int departmentid) {
		
		Department department = departmentDao.getDepartment(departmentid);
		
		return department;
	}

	@Transactional
	public void saveDepartment(Department department) {
		
		departmentDao.saveDepartment(department);
		
	}

	@Transactional
	public void deleteDepartment(int departmentid) {
		
		departmentDao.deleteDepartment(departmentid);
		
	}
}
