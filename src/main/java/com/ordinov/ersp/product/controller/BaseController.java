package com.ordinov.ersp.product.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ordinov.ersp.product.editor.DoubleEditor;

@Controller
public class BaseController {

   @InitBinder    
   public void initBinder(WebDataBinder binder) {    
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));    
	   binder.registerCustomEditor(double.class, new DoubleEditor());    
   } 
}
