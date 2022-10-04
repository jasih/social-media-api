package com.cookysys.social_media_project.Controllers.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.cookysys.social_media_project.exceptions.BadRequestException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(basePackages = { "com.cookysys.social_media_project.controllers" })
public class SocialMediaControllerAdvice {

//	@ExceptionHandler(BadRequestException.class)
//	public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
//		return new ErrorDto(badRequestException.getMessage());
//	}
	
}