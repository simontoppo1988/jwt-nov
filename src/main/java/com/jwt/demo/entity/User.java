package com.jwt.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // optional, you can name the table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private String email;

    private String password;

    
    
}
