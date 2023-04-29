package com.any.restApipractise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.any.restApipractise.entity.CustomerEntity;
import com.any.restApipractise.repository.CustomerRepository;
import com.any.restApipractise.vo.CustomerVo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private	CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public CustomerVo registerCustomerRecord(CustomerVo customerVo) {
		CustomerEntity customerEntity=new CustomerEntity();
		BeanUtils.copyProperties(customerVo, customerEntity);
		customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
		customerRepository.save(customerEntity);
		return customerVo;
	}

	@Override
	public List<CustomerVo> showAllCustomerRecord() {
		List<CustomerEntity> customerEntity=customerRepository.findAll();
		List<CustomerVo>customewrVoList=new ArrayList<>();
		for(CustomerEntity Customer:customerEntity) {
			CustomerVo customerVo=new CustomerVo();
			BeanUtils.copyProperties(Customer, customerVo);
			customewrVoList.add(customerVo);
		}
		return customewrVoList;
	}

	@Override
	public CustomerVo getParticularRecord(String username, CustomerVo customerVo) {
		Optional<CustomerEntity>existRecord=customerRepository.findByUsername(username);
		if(existRecord.isPresent()) {
			CustomerEntity customer=existRecord.get();
			CustomerVo customerVos=new CustomerVo();
			BeanUtils.copyProperties(customer, customerVos);
			return customerVos;
		}
		return null;
	}

}
