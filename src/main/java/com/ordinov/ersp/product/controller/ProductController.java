package com.ordinov.ersp.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.product.entity.Product;
import com.ordinov.ersp.product.excetion.DBDataNotExist;
import com.ordinov.ersp.product.excetion.DBDataOperateFail;
import com.ordinov.ersp.product.service.IProductMngService;
import com.ordinov.ersp.product.util.Responsecode;
import com.ordinov.ersp.product.util.ServerAnswer;
import com.ordinov.ersp.tools.BSTableObject;
import com.ordinov.ersp.tools.BootstrapTableDataUtils;

@Controller
@Scope(scopeName=WebApplicationContext.SCOPE_REQUEST)
@RequestMapping(value="/api/product")
public class ProductController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(ProductController.class);
	
	@Autowired
	private IProductMngService productMngService = null;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ServerAnswer> add(Product product){
		
		LOGGER.debug("添加产品_start");
		ServerAnswer answer = null;
		String id = null;
		try {
			id = productMngService.publishProduct(product);
			answer = new ServerAnswer(true, Responsecode.OK, id);
		}catch(IllegalAccessException ill){
			
			LOGGER.error(Responsecode.PARAM_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION, "");
			ill.printStackTrace();
		}catch (DBDataOperateFail of) {
			
			LOGGER.error(Responsecode.FAIL.getDesc());
			answer = new ServerAnswer(false, Responsecode.FAIL, "");
			of.printStackTrace();
		} catch (Exception e) {
			
			LOGGER.error("发布产品失败："+e.getMessage());
			answer = new ServerAnswer(false, Responsecode.FAIL, "");
			e.printStackTrace();
		}
		LOGGER.debug("添加产品_end");
		return new ResponseEntity<ServerAnswer>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(method={RequestMethod.PUT,RequestMethod.POST},value="/{id}")
	public ResponseEntity<ServerAnswer> update(@PathVariable String id,Product product){
		
		LOGGER.debug("修改产品_start");
		boolean flag = false;
		ServerAnswer answer = null;
		try {
			
			flag = productMngService.updateProduct(product);
			if(flag){
				
				answer = new ServerAnswer(true, Responsecode.OK, product.getId());
			}else{
				
				answer = new ServerAnswer(false, Responsecode.FAIL, "A1");
			}
		} catch(IllegalAccessException ill){
			
			LOGGER.error(Responsecode.PARAM_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION, "A2");
			ill.printStackTrace();
		} catch (DBDataNotExist of) {
			
			LOGGER.error(Responsecode.DB_DATA_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.DB_DATA_EXCETION, "A3");
			of.printStackTrace();
		} catch (Exception e) {
			
			LOGGER.error("修改产品失败");
			answer = new ServerAnswer(false, Responsecode.FAIL, "A4");
			e.printStackTrace();
		}
		LOGGER.debug("修改产品_end");
		return new ResponseEntity<ServerAnswer>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseEntity<?> list(@RequestParam("page") int page,
			@RequestParam("rows") int rows
			,@RequestParam("sord") String sord,
			@RequestParam("sidx") String sidx,
			@RequestParam("proName") String proName,
			@RequestParam("proType") String type){

		LOGGER.debug("分页查询产品列表_start");
		LOGGER.info("page:"+page+"rows:"+rows+"sord:"+sord+"sidx:"+sidx+"proName:"+proName
				+"proType:"+type);
		
		Product pro = new Product();
		if(proName != null && !"".equals(proName.trim())){
			
			pro.setName(proName);
		}
//		if(type != null && !"".equals(type.trim())){
//			
//			Dictionary dic = new Dictionary();
//			dic.setId(type);
//			pro.setType(dic);
//		}
		BSTableObject<?> object = BootstrapTableDataUtils.getObject(productMngService.getProductList(page, rows, sord, sidx, pro), productMngService.getProductCount(pro));
		return new ResponseEntity<BSTableObject>(object, HttpStatus.OK);
	}
	
	/**
	 * 验证工作产品名称
	 * @param name 验证的值
	 * @param id 当前产品编号
	 * @return
	 */
	
	@RequestMapping(value="/validate/name")
	@ResponseBody
	public String validateProductName(
			@RequestParam("name") String name,@RequestParam("id") String id){
		
		LOGGER.debug("验证产品名称的唯一性_start");
		Product product = new Product();
		product.setName(name);
		product.setId(id);
		String json = null;
		try {
			
			json = this.validate(product);
		}catch(IllegalAccessException ill){
			
			LOGGER.error(Responsecode.PARAM_EXCETION.getDesc());
			ill.printStackTrace();
		} catch (Exception e) {
			
			LOGGER.error("验证失败："+e.getMessage());
			e.printStackTrace();
		}
        LOGGER.debug("验证产品名称的唯一性_end");
		return json;
	}
	
	/**
	 * 验证产品标识
	 * @param mark
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/validate/model")
	@ResponseBody
	public String validateProductModel(
			@RequestParam("model") String model,@RequestParam("id") String id){
		
		LOGGER.debug("验证产品标识的唯一性_start");
		String json="";
		Product product = new Product();
		product.setModel(model);
		product.setId(id);
		try {
			
			json = this.validate(product);
		}catch(IllegalAccessException ill){
			
			LOGGER.error(Responsecode.PARAM_EXCETION.getDesc());
			ill.printStackTrace();
		} catch (Exception e) {
			
			LOGGER.error("验证失败："+e.getMessage());
			e.printStackTrace();
		}
		LOGGER.debug("验证产品标识的唯一性_end");
		return json;
	}
	
	private String validate(Product product) throws Exception{
		
		boolean flag =  productMngService.validateProductByParam(product);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("valid", flag);
        Gson gson = new Gson();
        String json= gson.toJson(map);
        return json;
	}
	
	/**
	 * 获取产品类型列表
	 * @return
	 */
	@RequestMapping(value="producttype")
	public ResponseEntity<List<Dictionary>> getProductTypeList(){
		
		LOGGER.debug("获取产品类型列表_start");
		List<Dictionary> list = productMngService.getProductTypeList();
		LOGGER.debug("获取产品类型列表_end");
		return new ResponseEntity<List<Dictionary>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}")
	public ResponseEntity<?> getProductDetail(@PathVariable String id){
		
		LOGGER.debug("获取产品详细信息_start");
		Product product = null;
		ServerAnswer answer = null;
		try {
			product = productMngService.detail(id);
			answer = new ServerAnswer(true, Responsecode.OK, product);
		}catch(IllegalAccessException ill){
			
			LOGGER.error(Responsecode.PARAM_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION, "");
			ill.printStackTrace();
		}catch (DBDataNotExist of) {
			
			LOGGER.error(Responsecode.DB_DATA_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.DB_DATA_EXCETION, "");
			of.printStackTrace();
		} catch (Exception e) {

			LOGGER.error("获取产品详述失败!"+e.getMessage());
			answer = new ServerAnswer(false, Responsecode.FAIL, "");
			e.printStackTrace();
		}
		LOGGER.debug("获取产品详细信息_end");
		return new ResponseEntity<ServerAnswer>(answer,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="{id}")
	public ResponseEntity<ServerAnswer> deleteProduct(@PathVariable String id){
		
		LOGGER.debug("批量删除产品信息_start");
		boolean flag = false;
		ServerAnswer answer = null;
		try {
			
			flag = productMngService.deleteProduct(id);
			if(flag){
				
				answer = new ServerAnswer(true, Responsecode.OK, "");
			}else{
				
				answer = new ServerAnswer(false, Responsecode.FAIL,"");
			}
		} catch(IllegalAccessException ill){
			
			LOGGER.error(Responsecode.PARAM_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION, "");
			ill.printStackTrace();
		} catch (DBDataNotExist of) {
			
			LOGGER.error(Responsecode.DB_DATA_EXCETION.getDesc());
			answer = new ServerAnswer(false, Responsecode.DB_DATA_EXCETION, "");
			of.printStackTrace();
		}catch (Exception e) {
			
			LOGGER.error("批量删除产品信息失败："+e.getMessage());
			answer = new ServerAnswer(false, Responsecode.FAIL, "");
			e.printStackTrace();
		}
		LOGGER.debug("批量删除产品信息_end");
		return new ResponseEntity<ServerAnswer>(answer,HttpStatus.OK);
	}
}
