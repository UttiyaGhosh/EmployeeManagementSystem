package com.ug.EMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ug.EMS.dao.LoginMasterDao;
import com.ug.EMS.entity.Login_Master;

@Service
public class loginMasterService {

	@Autowired
	private LoginMasterDao loginMasterDAO;
	
	@Transactional
	public Login_Master getLoginMaster(int userid) {
		
		Login_Master login_Master = loginMasterDAO.getLoginMaster(userid);
		
		return login_Master;
	}
	
	@Transactional
	public void saveLoginMaster(Login_Master login_Master) {
		
		loginMasterDAO.saveEmployee(login_Master);
		
	}
	
	@Transactional
	public void deleteLoginMaster(int empId) {
		
		loginMasterDAO.deleteEmployee(empId);
		
	}
}
