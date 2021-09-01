package com.ug.EMS.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ug.EMS.entity.Login_Master;

@Repository
public class LoginMasterDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Login_Master getLoginMaster(int userid) {

		Session session = sessionFactory.getCurrentSession();

		Login_Master login_Master = (Login_Master) session.get(Login_Master.class, userid);

		return login_Master;
	}

	public void saveEmployee(Login_Master login_Master) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(login_Master);

	}

	public void deleteEmployee(int empid) {

		Session session = sessionFactory.getCurrentSession();

		Login_Master login_Master = new Login_Master();
		login_Master.setUserid(empid);

		session.delete(login_Master);

	}
}
