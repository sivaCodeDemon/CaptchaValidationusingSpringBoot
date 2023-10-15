package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.UserEntity;

@Repository
public interface CaptchaRepo  extends JpaRepository<UserEntity, Integer> {

}
