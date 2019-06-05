package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean join(UserVo userVo) {
		int count = sqlSession.insert("user.join_user",userVo);
		count = sqlSession.insert("user.join_blog",userVo);
		count = sqlSession.insert("user.join_category",userVo);
		return count == 1;
	}
	
	public UserVo login(UserVo userVo) {
		UserVo uservo = sqlSession.selectOne("user.getByIdAndPassword", userVo);
		return uservo;
	}
	
	public UserVo checkId(String id) {
		UserVo uservo = sqlSession.selectOne("user.getById", id);
		return uservo;
		
	}
	
}
