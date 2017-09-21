package com.ordinov.ersp.product.service;

import java.util.List;

import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.product.entity.Product;

/**
 * 产品管理Service
 * @author lilk
 *
 */
public interface IProductMngService {

	/**
	 * 分页查询工作产品列表
	 * @param page 当前页数
	 * @param rows 行数
	 * @param sord 排序方式
	 * @param sidx 排序字段
	 * @param product 工作产品实体(包含的查询条件)
	 * @return
	 */
	public List<Product> getProductList(int page,int rows,String sord,String sidx,Product product);
	
	/**
	 * 获取符合条件的产品总数
	 * @param product
	 * @return
	 */
	public int getProductCount(Product product);
	
	/**
	 * 发布产品
	 * @param product
	 * @return
	 */
	public String publishProduct(Product product) throws Exception;
	
	/**
	 * 修改产品
	 * @param product
	 * @return
	 */
	public boolean updateProduct(Product product)throws Exception;
	
	/**
	 * 批量删除产品
	 * @param products
	 * @return
	 */
	public boolean deleteProduct(String ids) throws Exception;
	
	/**
	 * 验证产品相关信息的唯一性
	 * @param product
	 * @return
	 */
	public boolean validateProductByParam(Product product) throws Exception;
	
	/**
	 * 获取产品类型列表
	 * @return
	 */
	public List<Dictionary> getProductTypeList();
	
	/**
	 * 获取产品详细信息
	 * @param id
	 * @return
	 */
	public Product detail(String id) throws Exception;
}
