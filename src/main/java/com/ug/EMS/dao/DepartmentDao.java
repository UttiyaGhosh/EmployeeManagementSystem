package com.ug.EMS.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ug.EMS.entity.Department;

@Repository
public class DepartmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("rawtypes")
	public List<Department> getAllDepartments() {

		Session session = sessionFactory.getCurrentSession();

		List<Department> departmentList = new LinkedList<>();
		String hql = "FROM Department";

		Query query = session.createQuery(hql);
		for (Object obj : query.list())
			departmentList.add((Department) obj);

		return departmentList;
	}
	
	public Department getDepartment(int departmentid) {

		Session session = sessionFactory.getCurrentSession();

		Department department = (Department) session.get(Department.class, departmentid);

		return department;
	}

	public void saveDepartment(Department department) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(department);

	}

	public void deleteDepartment(int departmentid) {
		
		Session session = sessionFactory.getCurrentSession();
		Department department = new Department();
		department.setDepartmentId(departmentid);
		session.delete(department);

	}

}
