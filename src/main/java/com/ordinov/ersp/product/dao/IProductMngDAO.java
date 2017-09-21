package com.ordinov.ersp.product.dao;

import java.util.List;

import com.ordinov.ersp.product.entity.Product;

/**
 * 产品管理DAO接口
 * @author lilk
 *
 */
public interface IProductMngDAO {

	
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
	public String publishProduct(Product product);
	
	/**
	 * 修改产品
	 * @param product
	 * @return
	 */
	public boolean updateProduct(Product product);
	
	/**
	 * 批量删除产品
	 * @param products
	 * @return
	 */
	public boolean deleteProduct(List<Product> products);
	
	/**
	 * 验证产品相关信息的唯一性
	 * @param product
	 * @return
	 */
	public boolean validateProductByParam(Product product);
	
	/**
	 * 获取产品详细信息
	 * @param id
	 * @return
	 */
	public Product detail(String id);
	
	/**
	 * 通过产品编号查询产品列表
	 * @param ids
	 * @return
	 */
	public List<Product> getProductListByIds(String[] ids);
}
