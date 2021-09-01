package com.ug.EMS.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ug.EMS.entity.StatusReport;

@Repository
public class StatusReportDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings({ "rawtypes" })
	public StatusReport getStatusReportByCompAndEmp(int complianceId, int empid) {

		Session session = sessionFactory.getCurrentSession();

		StatusReport statusReport;

		String hql = "FROM StatusReport where compliance.complianceId = :cid and employee.empid = :eid";

		Query query = session.createQuery(hql);
		query.setParameter("cid", complianceId);
		query.setParameter("eid", empid);

		List resultSet = query.list();
		if (resultSet.size() == 0)
			statusReport = null;
		else
			statusReport = (StatusReport) resultSet.get(0);

		return statusReport;
	}

	public void saveStatusReport(StatusReport statusReport) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(statusReport);

	}

	public void deleteStatusReport(StatusReport statusReport) {

		Session session = sessionFactory.getCurrentSession();

		session.delete(statusReport);

	}
}
