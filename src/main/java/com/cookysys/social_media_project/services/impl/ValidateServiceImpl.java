package com.cookysys.social_media_project.services.impl;

import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService  {@Override
	public boolean userNameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userNameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
