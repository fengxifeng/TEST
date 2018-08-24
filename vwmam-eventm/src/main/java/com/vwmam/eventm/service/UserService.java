package com.vwmam.eventm.service;

import java.util.List;

import com.vwmam.eventm.entity.User;

public interface UserService {

	/**
	 * 获取所有用户
	 * @return
	 */
	List<User> getAllUsers();

}
