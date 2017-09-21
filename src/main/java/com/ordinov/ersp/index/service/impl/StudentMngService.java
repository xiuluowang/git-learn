package com.ordinov.ersp.index.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.index.dao.IStudentMngDAO;
import com.ordinov.ersp.index.entity.Student;
import com.ordinov.ersp.index.service.IStudentMngService;

@Service
public class StudentMngService implements IStudentMngService {
	
	@Autowired
	private IStudentMngDAO dao = null;
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public String addStudent(Student student) {
		return this.dao.addStudent(student);
	}
	
	
}
