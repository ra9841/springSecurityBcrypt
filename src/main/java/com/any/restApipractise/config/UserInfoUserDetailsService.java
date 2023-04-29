package com.any.restApipractise.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.any.restApipractise.entity.CustomerEntity;
import com.any.restApipractise.repository.CustomerRepository;

public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomerEntity>existRecord=customerRepository.findByUsername(username);
		
		if (existRecord.isPresent()) {
		    return new CustomerEntityUserDetails(existRecord.get());
		} else {
		    throw new UsernameNotFoundException("user not found" + username);
		}
		
		//return existRecord.map(CustomerEntityUserDetails::new)
		//.orElseThrow(()->new UsernameNotFoundException("user not found"+username));
		
	}

}
