package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.UserDAO;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;
	
	public boolean join(UserVo userVo) {
		return userDAO.join(userVo);		
	}
	
	public UserVo getUser(UserVo userVo) {
		
		return userDAO.login(userVo);
	}
	
	public boolean checkId(String id) {
		return userDAO.checkId(id)!=null;
	}
}
