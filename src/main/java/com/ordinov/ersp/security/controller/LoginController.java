package com.ordinov.ersp.security.controller;

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

import com.ordinov.ersp.reqirement.entity.ServerAnswer;
import com.ordinov.ersp.security.service.ILoginService;
import com.ordinov.ersp.user.entity.User;

/**
 * 系统登录
 * @author zhaowy
 *
 */
@Controller
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/sso")
public class LoginController {
	
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService loginService;
	
	/**
     * 登录
     */
    @RequestMapping(value="login",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ServerAnswer> login(@RequestParam("data") String data,@RequestParam("password") String password){
    	LOGGER.debug("进入登录方法......");
    	User info = this.loginService.login(data, password);
    	ServerAnswer answer = new ServerAnswer();
    	if (info != null) {
    		answer.setResult(true);
    		answer.setMsg(info.getUtype());
    	} else {
    		answer.setResult(false);
    	}
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }
	
	@RequestMapping(value="logout",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServerAnswer> logout(){
    	LOGGER.debug("进入登出方法......");
        boolean result = this.loginService.logout();
        ServerAnswer answer = new ServerAnswer();
        answer.setResult(result);
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }
	
	@RequestMapping(value="loginInfo",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<ServerAnswer> loginInfo(){
    	LOGGER.debug("进入获取登录信息方法......");
        String userName = this.loginService.queryLoginInfo();
        ServerAnswer answer = new ServerAnswer();
        answer.setMsg(userName);
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
    }

}
