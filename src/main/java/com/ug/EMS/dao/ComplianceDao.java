package com.ug.EMS.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ug.EMS.entity.Compliance;

@Repository
public class ComplianceDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("rawtypes")
	public List<Compliance> getAllCompliances() {

		Session session = sessionFactory.getCurrentSession();

		List<Compliance> complianceList = new LinkedList<>();
		String hql = "FROM Compliance";

		Query query = session.createQuery(hql);
		for (Object obj : query.list())
			complianceList.add((Compliance) obj);

		return complianceList;
	}
	
	public Compliance getCompliance(int complianceid) {

		Session session = sessionFactory.getCurrentSession();

		Compliance compliance = (Compliance) session.get(Compliance.class, complianceid);

		return compliance;
	}

	public void saveCompliance(Compliance compliance) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(compliance);

	}

	public void deleteCompliance(Compliance compliance) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(compliance);

	}

	@SuppressWarnings("rawtypes")
	public List<Compliance> getAllcompliancesByDept(int departmentid) {

		Session session = sessionFactory.getCurrentSession();

		List<Compliance> complianceList = new LinkedList<>();
		
		String hql = "FROM Compliance where department.departmentId = :deptId";

		Query query = session.createQuery(hql);
		query.setParameter("deptId", departmentid);

		for (Object obj : query.list())
			complianceList.add((Compliance) obj);
		
		System.out.println(complianceList.size());
		return complianceList;
	}

}
