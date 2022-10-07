package com.cookysys.social_media_project.dtos;

import javax.persistence.Embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialsDto {
	
	@Embedded
	private String username;
	
	@Embedded
	private String password;

}
