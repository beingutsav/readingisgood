package com.getir.readingisgoodservicerest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Value("${security.user.name}")
	private String userName;
	
	@Value("${security.user.password}")
	private String password;
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		if (userName.equals(user)) {
			return new User(user, password,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + user);
		}
	}
}