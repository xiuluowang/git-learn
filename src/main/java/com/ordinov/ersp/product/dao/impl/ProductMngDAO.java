package com.ordinov.ersp.product.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.product.dao.IProductMngDAO;
import com.ordinov.ersp.product.entity.Product;
import com.ordinov.ersp.reqirement.dao.tools.PaginationQueryHibernateCallback;

@Repository
public class ProductMngDAO implements IProductMngDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate = null;
	
	private static final Logger LOGGER = Logger.getLogger(ProductMngDAO.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductList(int page, int rows, String sord, String sidx, Product product) {
		
		LOGGER.debug("DAO_分页获取产品列表_start");
		List<Product> list = new ArrayList<Product>();
		StringBuffer hqlStr = this.getHQL(product);
		if(hqlStr != null){
			
			try {
				if (sidx != null && !sidx.isEmpty()) {
					hqlStr.append(" order by ");
					hqlStr.append(sidx);
				} else {
					hqlStr.append(" order by id ");
				}
				if (sord != null && !sord.isEmpty()) {
					
					hqlStr.append(" ");
					hqlStr.append(sord);
					
				} else {
					hqlStr.append(" desc");
				}
				PaginationQueryHibernateCallback callback 
					= new PaginationQueryHibernateCallback(hqlStr.toString(), page, rows);
				list = (List<Product>)hibernateTemplate.execute(callback);
			} catch (Exception e) {
				LOGGER.error("DAO_分页获取产品列表失败："+e.getMessage());
				e.printStackTrace();
			}
		}
		LOGGER.debug("DAO_分页获取产品列表_end");
		return list;
	}
	
	@Override
	public int getProductCount(Product product) {

		LOGGER.debug("DAO_获取产品总数_start");
		int result = 0;
		StringBuffer hql = this.getHQL(product);
		if(hql != null){
			
			StringBuffer hqlCountStr = new StringBuffer("select count(*) ");
			hqlCountStr.append(hql);
			Session session = null;
			try {
				session =hibernateTemplate.getSessionFactory().openSession();
				@SuppressWarnings("unchecked")
				List<Long> count = session.createQuery(hqlCountStr.toString()).list();
				if (count != null && !count.isEmpty()) {
					result = count.get(0).intValue();
				}
			} catch (Exception ex) {
				LOGGER.error("DAO_获取产品总数_失败：" + ex.getMessage());
				ex.printStackTrace();
				result = 0;
			} finally {
				
				if (session != null) {
					
					session.clear();
					session.close();
				}
			}
		}
		LOGGER.debug("DAO_获取产品总数_end");
		return result;
	}
	
	/**
	 * 获取HQL语句
	 * @param product
	 * @return
	 */
	private StringBuffer getHQL(Product product){
		
		StringBuffer hql = new StringBuffer("from Product where 1=1");
		if(product != null){
			
			if(product.getName() != null && !"".equals(product.getName().trim())){
				
				hql.append(" and upper(name) like '%" + product.getName().trim().toUpperCase() + "%'");
			}
		}
		return hql;
	}

	@Override
	public boolean updateProduct(Product product) {
		
		LOGGER.debug("DAO_修改产品_start");
		boolean flag =false;
		if(product != null){
			
			try{
				
				hibernateTemplate.update(product);
				flag = true;
			}catch(Exception ex){
				
				flag = false;
				LOGGER.error("DAO_修改产品失败："+ex.getMessage());
				ex.printStackTrace();
			}
		}
		LOGGER.debug("DAO_修改产品_end");
		return flag;
	}
	
	@Override
	public List<Product> getProductListByIds(String[] ids){
		
		LOGGER.debug("DAO_获取产品列表_start");
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.in("id", ids));
		List<Product> list = (List<Product>)hibernateTemplate.findByCriteria(criteria);
		LOGGER.debug("DAO_获取产品列表_end");
		return list;
	}

	@Override
	public boolean deleteProduct(List<Product> products) {

		LOGGER.debug("DAO_批量删除产品信息_start");
		boolean flag = false;
		if(products != null && !products.isEmpty()){
			
			try{
				
				hibernateTemplate.deleteAll(products);
				flag = true;
			}catch(Exception ex){
				
				flag = false;
				LOGGER.error("DAO_批量删除产品失败："+ex.getMessage());
				ex.printStackTrace();
			}
			
		}
		LOGGER.debug("DAO_批量删除产品信息_end");
		return flag;
	}

	@Override
	public String publishProduct(Product product) {
		
		LOGGER.debug("DAO_发布产品_start");
		String id = null;
		if(product != null){
			
			try{
				
				product.setId(UUID.randomUUID().toString());
				id = (String)hibernateTemplate.save(product);
			}catch(Exception ex){
				
				LOGGER.error("DAO_发布产品失败："+ex.getMessage());
				ex.printStackTrace();
			}
		}
		LOGGER.debug("DAO_发布产品_end");
		return id;
	}

	@Override
	public boolean validateProductByParam(Product product) {

		LOGGER.debug("DAO_验证产品的唯一性_start");
		boolean flag = false;
		if(product != null){

			StringBuffer hql = new StringBuffer("select count(*) from Product where 1=1");
			if(product.getName() != null && !product.getName().trim().isEmpty()){
				
				hql.append(" and name='").append(product.getName()).append("'");
			}else if(product.getModel() != null && !product.getModel().isEmpty()){
				
				hql.append(" and model='").append(product.getModel()).append("'");
			}
			if(product.getId() != null && !product.getId().isEmpty()){
				
				hql.append(" and id !='").append(product.getId()).append("'");
			}
			
			
			Session session = null;
			try {
				session =hibernateTemplate.getSessionFactory().openSession();
				@SuppressWarnings("unchecked")
				List<Long> count = session.createQuery(hql.toString()).list();
				if (count != null && !count.isEmpty()) {
					
					int result = count.get(0).intValue();
					if(result > 0){
						
						flag = false;
					}else{
						
						flag = true;
					}
				}
			} catch (Exception ex) {
				
				flag = false;
				LOGGER.error("DAO_获取产品总数_失败：" + ex.getMessage());
				ex.printStackTrace();
			} finally {
				
				if (session != null) {
					
					session.clear();
					session.close();
				}
			}
		}
		LOGGER.debug("DAO_验证产品的唯一性_end");
		return flag;
	}

	@Override
	public Product detail(String id) {

		LOGGER.debug("DAO_获取产品详细信息_start");
		Product product= null;
		try{
			
			product = hibernateTemplate.get(Product.class, id);
			LOGGER.debug("DAO_获取产品详细信息_end");
			return product;
		}catch(Exception ex){
			
			LOGGER.error("DAO_获取产品详细信息_失败："+ex.getMessage());
			ex.printStackTrace();
		}
		return product;
	}
}
