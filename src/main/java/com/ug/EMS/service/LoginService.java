package com.ug.EMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ug.EMS.dao.LoginDAO;
import com.ug.EMS.entity.Login_Master;

@Service
public class LoginService {

	 @Autowired
	 private LoginDAO loginDAO;

	@Transactional
	public Boolean checkUser(Login_Master login_Master) {
		
		Login_Master login_MasterDb = loginDAO.getUser(login_Master.getUserid());
		if(login_MasterDb == null)
			return false;
		else
			return login_MasterDb.equals(login_Master);

	}

}
