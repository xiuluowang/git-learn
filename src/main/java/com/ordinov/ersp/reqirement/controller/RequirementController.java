package com.ordinov.ersp.reqirement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ordinov.ersp.reqirement.entity.Requirement;
import com.ordinov.ersp.reqirement.entity.ServerAnswer;
import com.ordinov.ersp.reqirement.entity.ValidResultAnswer;
import com.ordinov.ersp.reqirement.service.IRequirementMngService;
import com.ordinov.ersp.tools.BSTableObject;
import com.ordinov.ersp.tools.BootstrapTableDataUtils;
import com.ordinov.ersp.user.entity.User;

@Controller
@RequestMapping("/api/requirements")
public class RequirementController {

	@Autowired
	private IRequirementMngService service = null;
	
	/**
	 * Ëé∑ÂèñÂàÜÈ°µÂàóË°®
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method={RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<BSTableObject> listRequirements(
			final @RequestParam(value="page",required=false)Integer page, 
			final @RequestParam(value="rows",required=false)Integer rows, 
			final @RequestParam(value="sidx",required=false)String sidx, 
			final @RequestParam(value="sord",required=false)String sord, 
			final @RequestParam(value="search",required=false)String search){
		
		BSTableObject<?> object = BootstrapTableDataUtils.getObject(this.service.list(null,search, page, rows, sidx, sord), this.service.total(null,search));
		
		return new ResponseEntity<BSTableObject>(object, HttpStatus.OK);
	}
	
	@RequestMapping(method={RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<ServerAnswer> addRequirement(
			//@RequestBody Requirement requirement
			@ModelAttribute Requirement requirement,HttpSession httpSession){
		
		requirement.setEnterprise((User) httpSession.getAttribute("user"));
		
		String newId = this.service.publish(requirement); // NOPMD by dengfx on 17-9-21 …œŒÁ11:08
		
		ServerAnswer answer = new ServerAnswer();
		answer.setResult(true);
		answer.setMsg(newId);
		
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
	}
	
	@RequestMapping(value="{reqId}",method={RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<Requirement> getRequirement(@PathVariable String reqId){
		Requirement requirement = this.service.getRequirementById(reqId);
		return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}
	
	@RequestMapping(value="{reqId}/edit",method={RequestMethod.GET})
	public ModelAndView beforeEdit(@PathVariable String reqId){
		Requirement requirement = this.service.getRequirementById(reqId);
		String veiwName = "requirement/edit";
	    ModelMap model = new ModelMap();
	    model.addAttribute("req",requirement);
		return new ModelAndView(veiwName,model);
	}
	
	@RequestMapping(value="{reqId}",method={RequestMethod.DELETE})
	@ResponseBody
	public ResponseEntity<ServerAnswer> deleteRequirement(@PathVariable String reqId){
		ServerAnswer answer = new ServerAnswer();
		boolean isok = false;
		try {
			isok = this.service.delete(reqId);
		} catch (Exception e) {
			e.printStackTrace();
			isok = false;
		}
		answer.setResult(isok);
		
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
	}
	
	@RequestMapping(value="{reqId}",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<ServerAnswer> modifyRequirement(@PathVariable String reqId,@ModelAttribute Requirement requirement){
		ServerAnswer answer = new ServerAnswer();
		boolean isok = false;
		try {
			isok = this.service.modify(requirement);
		} catch (Exception e) {
			e.printStackTrace();
			isok = false;
		}
		answer.setResult(isok);
		
		return new ResponseEntity<ServerAnswer>(answer, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="check/reqname",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<ValidResultAnswer> checkReqName(
			@RequestParam String reqName){
		
		ValidResultAnswer answer = new ValidResultAnswer();
		
		boolean isExist = this.service.isExist(null, reqName);
		answer.setValid(!isExist);
		
		return new ResponseEntity<ValidResultAnswer>(answer, HttpStatus.OK);
	}
	
}
