package com.cookysys.social_media_project.services;



public interface ValidateService {

	boolean userNameExists(String username);

	boolean userNameAvailable(String username);

	boolean labelExists(String label);
	
	

}
