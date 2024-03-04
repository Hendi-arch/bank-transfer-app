package com.hendi.banktransfersystem.infrastructure.config.web.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hendi.banktransfersystem.entity.user.model.UserAccountModel;
import com.hendi.banktransfersystem.usecase.user.GetUserUseCase;

@Service
public class MyUserDetailService implements UserDetailsService {

	private final GetUserUseCase getUserUseCase;

	public MyUserDetailService(GetUserUseCase getUserUseCase) {
		this.getUserUseCase = getUserUseCase;
	}

	@Override
	public UserDetails loadUserByUsername(String identity) {
		UserAccountModel userAccount = getUserUseCase.findByUsername(identity);
		String username = userAccount.getUsername();
		String password = userAccount.getPassword();

		List<String> roles = new ArrayList<>();
		roles.add("USER");
		return User.withUsername(username).password(password)
				.authorities(roles.toArray(new String[roles.size()])).build();
	}

}
