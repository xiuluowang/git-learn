package com.ordinov.ersp.index.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.index.dao.IStudentMngDAO;
import com.ordinov.ersp.index.entity.Student;

@Repository
public class StudentMngDAO implements IStudentMngDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public String addStudent(Student student) {
		String id = null;
		try {
			student.setId(UUID.randomUUID().toString());
			id = (String) hibernateTemplate.save(student);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return id;
	}

}
