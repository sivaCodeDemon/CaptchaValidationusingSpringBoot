package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.NewUser;
import com.demo.service.CaptchaService;
import com.demo.util.CaptchaUtil;


/**
 * {@link CaptchaService}
 * @author SivaDev
 *@implNote {Creating the captcha while signup   the user}
 */
@RestController
@RequestMapping("/api")
public class CaptchaController {
	
	private static final Logger logger= LoggerFactory.getLogger(CaptchaController.class);
	
	@Autowired
	private CaptchaService service;
	
	@GetMapping("/getCaptcha")
	public NewUser getCaptcha() {
		logger.info("START : getCaptcha() : CaptchaController.class");
		NewUser user= new NewUser();
		CaptchaUtil.setCaptchaForNewUser(user);
		logger.info("END : getCaptcha() : CaptchaController.class");
		return user;
	}
	
	
	@PostMapping("/newUserWithCaptcha")
	public ResponseEntity<String> signUpUserWithCaptcha(@RequestBody NewUser user){
		logger.info("START : signUpUserWithCaptcha() : CaptchaController.class");
		String message=null;
		if(user.getHidden().equals(user.getCaptcha())) {
		 message=service.saveNewUserIntoDb(user);
		}else {
			/*
			 * if the captcha is not coming again creating the new captcha
			 */
			logger.info("MIDDLE : signUpUserWithCaptcha() : CaptchaController.class");
			CaptchaUtil.setCaptchaForNewUser(user);
		}
		logger.info("END : signUpUserWithCaptcha() : CaptchaController.class");

		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}

}
