package com.hendi.banktransfersystem.infrastructure.config.web.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userId) {
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		return User.withUsername(userId).password("userPassword")
				.authorities(roles.toArray(new String[roles.size()])).build();
	}

}
