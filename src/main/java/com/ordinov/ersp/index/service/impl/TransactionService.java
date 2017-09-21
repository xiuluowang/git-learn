package com.ordinov.ersp.index.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.index.entity.Student;
import com.ordinov.ersp.index.entity.TeachingClass;
import com.ordinov.ersp.index.service.IStudentMngService;
import com.ordinov.ersp.index.service.ITeachingClassMngService;
import com.ordinov.ersp.index.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITeachingClassMngService classService = null;
	
	@Autowired
	private IStudentMngService studentService = null;

	@Transactional(rollbackOn={Exception.class})
	@Override
	public boolean add(TeachingClass tClass, Student student) {
		boolean result = false;
		
		this.classService.addTeachingClass(tClass);
		
		student.settClass(tClass);
		
		this.studentService.addStudent(student);
		
		result = true;
		
		return result;
	}
	
	
}
