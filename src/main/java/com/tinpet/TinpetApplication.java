package com.tinpet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tinpet.servicios.UsarioServ;

@SpringBootApplication
public class TinpetApplication extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsarioServ usuarioS;
	
	public static void main(String[] args) {
		SpringApplication.run(TinpetApplication.class, args);
	}
	
	public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioS).passwordEncoder(new BCryptPasswordEncoder());
	}

}
