package com.ug.EMS.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ug.EMS.entity.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public List<Employee> getAllEmployees() {

		Session session = sessionFactory.getCurrentSession();

		List<Employee> employeeList = new LinkedList<>();
		String hql = "FROM Employee";

		Query query = session.createQuery(hql);
		for (Object obj : query.list())
			employeeList.add((Employee) obj);

		return employeeList;
	}

	public Employee getEmployee(int empid) {

		Session session = sessionFactory.getCurrentSession();

		Employee employee = (Employee) session.get(Employee.class, empid);

		return employee;
	}

	public void saveEmployee(Employee employee) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(employee);
		
	}

	public void deleteEmployee(int empid) {
		
		Session session = sessionFactory.getCurrentSession();
		Employee employee = new Employee();
		employee.setEmpid(empid);
		session.delete(employee);

	}

}
