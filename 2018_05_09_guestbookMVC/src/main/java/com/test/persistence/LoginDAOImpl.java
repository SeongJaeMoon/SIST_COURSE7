package com.test.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.Login;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public Login login(Login l) {
		return this.sqlSession.selectOne("com.test.mapper.LoginMapper.login", l);
	}

}
