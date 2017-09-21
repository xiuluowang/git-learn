package com.ordinov.ersp.index;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseTester;

import com.ordinov.ersp.index.entity.Student;
import com.ordinov.ersp.index.entity.TeachingClass;
import com.ordinov.ersp.index.service.ITransactionService;


public class TransactionTester extends BaseTester{
	
	@Autowired
	private ITransactionService transService = null;
	
	@Test
	public void testTransaction(){
		TeachingClass tClass = new TeachingClass();
		tClass.setName("2017级1701-S");
		
		Student student = new Student();
		student.setName("中国好学生");

		this.transService.add(tClass, student);
	}
	
	@Test
	public void testTransactionPartialFail(){
		TeachingClass tClass = new TeachingClass();
		tClass.setName("2017级1701-F");

		Student student = new Student();
		student.setName("中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生中国好学生");

		this.transService.add(tClass, student);
	}
}
