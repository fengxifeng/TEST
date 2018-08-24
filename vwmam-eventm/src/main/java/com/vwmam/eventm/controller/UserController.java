package com.vwmam.eventm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vwmam.eventm.entity.User;
import com.vwmam.eventm.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user",produces = { "application/json;charset=UTF-8" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	@ApiOperation(value = "图形验证码获取", notes = "备注非必填")
	public List<User> getAllUsers() {
		System.out.println("只有第一次才会打印sql语句");
		return userService.getAllUsers();
	}

}
