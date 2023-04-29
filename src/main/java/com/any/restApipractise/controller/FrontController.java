package com.any.restApipractise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.any.restApipractise.service.CustomerService;
import com.any.restApipractise.vo.CustomerVo;

@RestController
@RequestMapping("/v")
public class FrontController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/register")
	public CustomerVo registerRecord(@RequestBody CustomerVo customerVo ) {
		CustomerVo customer=customerService.registerCustomerRecord(customerVo);
		return customer;
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to spring security";
	}
	
	
	@GetMapping("/show")
	@PreAuthorize("hasAuthority('admin')")//method level authorization
	public List<CustomerVo> showAllRecord(){
		List<CustomerVo> customer=customerService.showAllCustomerRecord();
		return customer;
	}
	
	@GetMapping("/One/{username}")
	@PreAuthorize("hasAuthority('user')")//method level authorization
	public CustomerVo getRecordByusername(@PathVariable String username,@ModelAttribute CustomerVo customerVo) {
		CustomerVo customer=customerService.getParticularRecord(username,customerVo);
		return customer;
		
	}

	

}
