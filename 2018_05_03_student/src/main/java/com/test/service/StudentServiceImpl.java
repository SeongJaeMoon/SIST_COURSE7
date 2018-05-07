package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.domain.Student;
import com.test.persistence.StudentDAO;

@Service
public class StudentServiceImpl implements StudentService {

	@Inject
	private StudentDAO studentDAO;
	
	@Override
	public List<Student> studentList() {
		return this.studentDAO.studentList();
	}

}
