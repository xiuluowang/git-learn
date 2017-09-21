package com.ordinov.ersp.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ordinov.ersp.product.util.Responsecode;
import com.ordinov.ersp.product.util.ServerAnswer;
import com.ordinov.ersp.security.service.IRegisterService;
import com.ordinov.ersp.user.entity.User;

/**
 * 用户注册
 * @author zhaowy
 *
 */
@Controller
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/sso")
public class RegisterController {
	
	private static final Logger LOGGER = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private IRegisterService registerService;
	
	 /**
     * 注册用户
     * @param user
     */
    @RequestMapping(value="register",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ServerAnswer> register(User user){
    	LOGGER.debug("进入注册用户方法......");
    	ServerAnswer answer = null;
    	try {
    		String userId = this.registerService.register(user);
    		if (userId != null && !"".equals(userId)) {
    			answer = new ServerAnswer(true, Responsecode.OK, userId);
    		} else {
    			answer = new ServerAnswer(false, Responsecode.FAIL,userId);
    		}
    	} catch (IllegalArgumentException e) {
    		answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION,e.getMessage());
    		LOGGER.error("exception", e);
    	} catch (Exception e) {
    		answer = new ServerAnswer(false, Responsecode.FAIL,e.getMessage());
    		LOGGER.error("exception", e);
    	}
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }
    
    /**
     * 验证用户名
     */
    @RequestMapping(value="valUname",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String valUName(@RequestParam("uname") String uname,@RequestParam("id") String id){
    	LOGGER.debug("进入验证用户名方法......");
    	User user = new User();
    	user.setUname(uname);
    	user.setId(id);
		return this.validateParam(user);
	}
    
    /**
     * 验证联系电话
     */
    @RequestMapping(value="valPhone",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String valPhone(@RequestParam("telephone") String phone,@RequestParam("id") String id){
    	LOGGER.debug("进入验证联系电话方法......");
    	User user = new User();
    	user.setTelephone(phone);
    	user.setId(id);
		return this.validateParam(user);
	}
    
    /**
     * 验证邮箱
     */
    @RequestMapping(value="valEmail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String valEmail(@RequestParam("email") String email,@RequestParam("id") String id){
    	LOGGER.debug("进入验证邮箱方法......");
    	User user = new User();
    	user.setEmail(email);
    	user.setId(id);
		return this.validateParam(user);
	}
    
    private String validateParam(User user){
    	String json = null;
		boolean result = this.registerService.valUserByParam(user);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("valid", result);
        Gson gson = new Gson();
        json= gson.toJson(map);
        return json;
	}

}
