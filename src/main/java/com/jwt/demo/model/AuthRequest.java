package com.jwt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
	
	private String name;
	 private String email;
	    private String password;
	    
		
	    

}
