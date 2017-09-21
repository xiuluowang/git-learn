package com.ordinov.ersp.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ordinov.ersp.product.util.Responsecode;
import com.ordinov.ersp.product.util.ServerAnswer;
import com.ordinov.ersp.tools.BSTableObject;
import com.ordinov.ersp.tools.BootstrapTableDataUtils;
import com.ordinov.ersp.user.entity.User;
import com.ordinov.ersp.user.service.IUserMngService;

/**
 * 用户Controller
 * @author zhaowy
 *
 */
@Controller
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/api/users")
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserMngService userService;
	
	
    /**
     * 异步查询用户列表
     * @param id
     */
    @SuppressWarnings("rawtypes")
	@ResponseBody
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<BSTableObject> listUsers(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="rows",required=false)Integer rows,
			@RequestParam(value="sidx",required=false)String sidx,
			@RequestParam(value="sord",required=false)String sord,
			@RequestParam(value="search",required=false)String search){
    	
    	LOGGER.debug("进入列表方法......");
    	List<User> dataList = this.userService.queryUsers(search, page, rows, sidx, sord);
    	long total = this.userService.queryTotal(search);
    	BSTableObject<?> object = BootstrapTableDataUtils.getObject(dataList,total);
		return new ResponseEntity<BSTableObject>(object, HttpStatus.OK);
    }
	
    /**
     * 新增用户
     * @param user
     */
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ServerAnswer> addUser(User user){
    	LOGGER.debug("进入添加方法......");
    	ServerAnswer answer = null;
    	try {
    		String userId = this.userService.addUser(user);
    		if (userId != null && !"".equals(userId)) {
    			answer = new ServerAnswer(true, Responsecode.OK, userId);
    		} else {
    			answer = new ServerAnswer(false, Responsecode.FAIL,"添加用户失败");
    		}
    	} catch (IllegalArgumentException e) {
    		answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION,e.getMessage());
    		e.printStackTrace();
    	} catch (Exception e) {
    		answer = new ServerAnswer(false, Responsecode.FAIL,e.getMessage());
    		e.printStackTrace();
    	}
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }
    
    /**
     * 查看用户详细信息
     * @param id
     */
    @RequestMapping(method=RequestMethod.GET,value="{id}")
    public ModelAndView viewUser(@PathVariable("id")String id){
    	LOGGER.debug("进入详情方法......");
        User user = this.userService.findUserByID(id);
        ModelMap model=new ModelMap();
        model.addAttribute("user",user);
        return new ModelAndView("user/view",model);
    }
    
    /**
     * 删除用户
     * @param id
     */
    @ResponseBody
    @RequestMapping(method=RequestMethod.DELETE,value="{id}")
    public ResponseEntity<ServerAnswer> deleteUser(@PathVariable("id")String id){
    	LOGGER.debug("进入删除用户方法......");
    	ServerAnswer answer = null;
    	try {
        	boolean result = this.userService.deleteUsers(id);
        	if (result) {
        		answer = new ServerAnswer(true, Responsecode.OK, id);
        	} else {
        		answer = new ServerAnswer(false, Responsecode.FAIL,"删除用户失败");
        	}
    	} catch (IllegalArgumentException e) {
    		answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION,e.getMessage());
    		e.printStackTrace();
    	} catch (Exception e) {
    		answer = new ServerAnswer(false, Responsecode.FAIL,e.getMessage());
    		e.printStackTrace();
    	}
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }
    
    /**
     * 跳转到编辑页面
     * @param id
     * @return ModelAndView
     */
    @RequestMapping("{id}/edit")
    public ModelAndView toEdit(@PathVariable("id")String id){
    	LOGGER.debug("进入查看编辑数据方法......");
        User user = this.userService.findUserByID(id);
        ModelMap model = new ModelMap();
        model.addAttribute("user",user);
        return new ModelAndView("user/edit",model);
    }
     
    /**
     * 更新用户
     * @param user
     * A
     */
    @RequestMapping(value="{id}",method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ServerAnswer> edit(@PathVariable("id")String id,User user){
    	LOGGER.debug("进入更新方法......");
    	ServerAnswer answer = null;
    	try {
    		boolean result = this.userService.modifyUser(user);
    		if (result) {
    			answer = new ServerAnswer(true, Responsecode.OK, id);
    		} else {
    			answer = new ServerAnswer(false, Responsecode.FAIL,"修改用户失败");
    		}
    	} catch (IllegalArgumentException e) {
    		answer = new ServerAnswer(false, Responsecode.PARAM_EXCETION,e.getMessage());
    		LOGGER.error("Here is a error!", e);
    	} catch (Exception e) {
    		answer = new ServerAnswer(false, Responsecode.FAIL,e.getMessage());
    		LOGGER.error("Here is a error!", e);
    	}
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }
    
    /**
     * 验证用户名B
     */
    @RequestMapping(value="valUname",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String valUName(@RequestParam("uname") String uname,@RequestParam("id") String id){
    	LOGGER.debug("进入验证用户名方法......");
    	User user = new User();
    	user.setUname(uname);
    	user.setId(id);
		String resultJson = this.validateParam(user);
		return resultJson;
	}
    
    /**
     * 验证联系电话B
     */
    @RequestMapping(value="valPhone",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String valPhone(@RequestParam("telephone") String phone,@RequestParam("id") String id){
    	LOGGER.debug("进入验证联系电话方法......");
    	User user = new User();
    	user.setTelephone(phone);
    	user.setId(id);
		//String resultJson = ;
		return this.validateParam(user);
	}
    
    /**
     * 验证邮箱B
     */
    @RequestMapping(value="valEmail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String valEmail(@RequestParam("email") String email,@RequestParam("id") String id){
    	LOGGER.debug("进入验证邮箱方法......");
    	User user = new User();
    	user.setEmail(email);
    	user.setId(id);
		String resultJson = this.validateParam(user);
		return resultJson;
	}
    
    private String validateParam(User user){
    	String json = null;
		boolean result = this.userService.valUserByParam(user);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("valid", result);
        Gson gson = new Gson();
        json= gson.toJson(map);
        return json;
	}
    
   
    
}
