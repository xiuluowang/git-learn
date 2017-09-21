package com.ordinov.ersp.product.service.impl;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordinov.ersp.product.entity.Product;
import com.ordinov.ersp.product.service.IProductMngService;
import com.ordinov.ersp.product.util.ServerAnswer;

import base.BaseTester;

public class ProductServiceTest extends BaseTester {

	@Autowired
	private IProductMngService service = null;
	
	@Test
	public void testSave() {
		
		Product product = new Product();
		product.setName("e");
		product.setPublishDate(new Date());
		String id;
		try {
			id = service.publishProduct(product);
			Assert.assertNotNull(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
