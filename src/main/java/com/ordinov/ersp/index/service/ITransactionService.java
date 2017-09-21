package com.ordinov.ersp.index.service;

import com.ordinov.ersp.index.entity.Student;
import com.ordinov.ersp.index.entity.TeachingClass;

public interface ITransactionService {
	
	public boolean add(TeachingClass tClass,Student student);
}
