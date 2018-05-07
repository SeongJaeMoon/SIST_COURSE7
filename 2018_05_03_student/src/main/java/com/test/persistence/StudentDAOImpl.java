package com.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.domain.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<Student> studentList() {
		return this.sqlSession.selectList("com.test.mapper.StudentMapper.studentList");
	}

}
