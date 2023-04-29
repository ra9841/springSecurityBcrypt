package com.any.restApipractise.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.any.restApipractise.entity.CustomerEntity;

public class CustomerEntityUserDetails implements UserDetails {
	
	private String username;
	private String password;
	private List<GrantedAuthority> grantedAuthority;
	

	public CustomerEntityUserDetails(CustomerEntity customerEntity) {
		username=customerEntity.getUsername();
		password=customerEntity.getPassword();
		
		//List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<>();
		//for (String role : customerEntity.getRole().split(",")) {//The split(",") method is called on this
		//	//String object to split it into an array of String objects using commas as the delimiter.
		//    grantedAuthority.add(new SimpleGrantedAuthority(role));
		//}
		grantedAuthority=Arrays.stream(customerEntity.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
