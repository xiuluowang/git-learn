package com.ordinov.user.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordinov.user.entity.User;
import com.ordinov.user.service.IUserManagerService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserManagerService userService = null;
	/*
	 * GET
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> list(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="rows",required=false)Integer rows){
		List<User> list = new ArrayList<User>();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	/**
	 * GET
	 */
	@RequestMapping(value="{uid}",method=RequestMethod.GET)
	public ResponseEntity<User> get(@PathVariable String uid){
		
		System.out.println("Request Id is " + uid);
		User user = this.userService.queryUser(uid);
		System.out.println(JSONObject.fromObject(user).toString());
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		if(user != null){
			status = HttpStatus.OK;
		}
		return new ResponseEntity<User>(user,status);
		
	}
	
	
	/**
	 * POST
	 * @param user
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Integer> add(@RequestBody User user){
		
		System.out.println(user);
		
		User newUser = userService.addUser(user);
		
		MultiValueMap<String, String> headers = new HttpHeaders();
		
		headers.set(HttpHeaders.LOCATION,"/users/" + newUser.getUserid());
		return new ResponseEntity<Integer>(headers,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="{uid}",method=RequestMethod.PUT)
	public ResponseEntity<Integer> modify(@PathVariable String uid,@RequestBody User user,@RequestHeader("If-Match") String eTag){
		System.out.println("[PUT] Requested Object is " + JSONObject.fromObject(user).toString());
		
		HttpStatus status = HttpStatus.OK;
		
		boolean isClean = this.userService.isClean(uid, eTag);
		System.out.println("Is Clean ? " + isClean);
		if(isClean){
			status = this.userService.modifyUser(user)?status:HttpStatus.NOT_FOUND;
		}else{
			status = HttpStatus.CONFLICT;
		}
		
		return new ResponseEntity<Integer>(status);
	}
	
}
