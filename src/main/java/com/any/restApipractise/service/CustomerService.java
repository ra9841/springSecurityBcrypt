package com.any.restApipractise.service;

import java.util.List;

import com.any.restApipractise.vo.CustomerVo;

public interface CustomerService {

	CustomerVo registerCustomerRecord(CustomerVo customerVo);

	List<CustomerVo> showAllCustomerRecord();

	CustomerVo getParticularRecord(String username, CustomerVo customerVo);

}
