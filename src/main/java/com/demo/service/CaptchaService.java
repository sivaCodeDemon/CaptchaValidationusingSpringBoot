package com.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.entity.UserEntity;
import com.demo.model.NewUser;
import com.demo.repository.CaptchaRepo;

@Service
public class CaptchaService {
	
	private static final Logger logger=LoggerFactory.getLogger(CaptchaService.class);
	private CaptchaRepo captchaRepo;
	
	
	public String saveNewUserIntoDb(NewUser user) {
		logger.info("START   :saveNewUserIntoDb    :CaptchaService");
		UserEntity entity= new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		captchaRepo.save(entity);
		logger.info("END   :saveNewUserIntoDb    :CaptchaService");
		return "USER ADDED SUCCESSFULLY";
	}
	

}
