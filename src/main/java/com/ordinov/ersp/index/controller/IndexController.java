package com.ordinov.ersp.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ordinov.ersp.index.entity.TeachingClass;
import com.ordinov.ersp.index.service.ITeachingClassMngService;

/**
 * @author dengfx
 */
@Controller
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/index/")
public class IndexController {
	
	@Autowired
	private ITeachingClassMngService springConfigMngService;
	
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String index(){
		return "aaa";
	}
	
	@RequestMapping(value="tests",method=RequestMethod.GET)
	@ResponseBody
	public String listTests(){
		return "list tests";
	}
	
	@RequestMapping(value="tests",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TeachingClass> add(@RequestBody TeachingClass tester){
		this.springConfigMngService.addTeachingClass(tester);	
		tester.setId("xxxxxxxx-xxx-xxx-xxx--xxxxxx");
		return new ResponseEntity<TeachingClass>(tester, HttpStatus.OK);
	}
}
