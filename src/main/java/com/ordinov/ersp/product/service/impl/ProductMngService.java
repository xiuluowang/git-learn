package com.ordinov.ersp.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ordinov.ersp.dictionary.dao.IDictionaryMngDAO;
import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;
import com.ordinov.ersp.product.dao.IProductMngDAO;
import com.ordinov.ersp.product.entity.Product;
import com.ordinov.ersp.product.excetion.DBDataNotExist;
import com.ordinov.ersp.product.excetion.DBDataOperateFail;
import com.ordinov.ersp.product.service.IProductMngService;

@Service
public class ProductMngService implements IProductMngService {

	@Autowired
	private IProductMngDAO productMngDAO = null;
	
	@Autowired
	private IDictionaryMngDAO dictionaryDAO = null;
	
	@Override
	public List<Product> getProductList(int page, int rows, String sord, String sidx, Product product) {
		
		return productMngDAO.getProductList(page, rows, sord, sidx, product);
	}

	@Override
	public int getProductCount(Product product) {

		return productMngDAO.getProductCount(product);
	}
	
	

	@Override
	@Transactional(rollbackOn=Exception.class)
	public String publishProduct(Product product) throws Exception {
		
		String id = null;
		if(product == null || product.getName() == null
				|| product.getName().isEmpty()){
			
			throw new IllegalAccessException();
		}
		id = productMngDAO.publishProduct(product);
		if(id == null || id.isEmpty()){
			
			throw new DBDataOperateFail();
		}
		return id;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public boolean updateProduct(Product product) throws Exception {
		
		if(product == null ||
				product.getId() == null || 
				product.getId().isEmpty() || product.getName() == null
				|| product.getName().isEmpty()){
			
			throw new IllegalAccessException();
		}
		Product oldProduct = productMngDAO.detail(product.getId());
		if (oldProduct == null || oldProduct.getId() == null || oldProduct.getId().isEmpty()) {
			
			throw new DBDataNotExist();
		}
		oldProduct.setDescription(product.getDescription());
		oldProduct.setModel(product.getModel());
		oldProduct.setName(product.getName());
		oldProduct.setPublishDate(product.getPublishDate());
		oldProduct.setReferenceprice(product.getReferenceprice());
		oldProduct.setType(product.getType());
		return productMngDAO.updateProduct(oldProduct);
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public boolean deleteProduct(String ids) throws Exception{
		
		if(ids == null || ids.isEmpty()){
			
			throw new IllegalAccessException();
		}
		List<Product> list = new ArrayList<Product>();
		int length = 0;
		if(ids != null && ids.length() > 0){
			
			String[] idsArray = ids.split(",");
			length = idsArray.length;
			list = productMngDAO.getProductListByIds(idsArray);
		}
		if(list == null || list.isEmpty() || list.size() != length){
			
			throw new DBDataNotExist();
		}
		return productMngDAO.deleteProduct(list);
	}

	@Override
	public boolean validateProductByParam(Product product) throws Exception{

		if(product == null || 
				((product.getName() == null || product.getName().isEmpty())
						&& (product.getModel() == null || product.getModel().isEmpty()))
				){
			
			throw new IllegalAccessException();
		}
		return productMngDAO.validateProductByParam(product);
	}

	@Override
	public List<Dictionary> getProductTypeList() {

		return dictionaryDAO.getDictionnaryListByType(DictionaryType.PRODUCTTYPE);
	}

	@Override
	public Product detail(String id) throws Exception {
		
		Product product = null;
		if(id == null || id.isEmpty()){
			
			throw new IllegalAccessException();
		}
		product = productMngDAO.detail(id);
		if(product == null || product.getId() == null 
				|| product.getId().isEmpty()){
			
			throw new DBDataNotExist();
		}
		
		return product;
	}

}
