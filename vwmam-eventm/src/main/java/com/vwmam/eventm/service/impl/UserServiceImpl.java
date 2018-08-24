package com.vwmam.eventm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vwmam.eventm.dao.GenericDao;
import com.vwmam.eventm.dao.UserDao;
import com.vwmam.eventm.entity.User;
import com.vwmam.eventm.service.UserService;

@Service
//@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	GenericDao genericDao;

	/**
	 * 2、在 Service 层的实现类中的方法@缓存
	 * ① 指定缓存的 key，为 wiselyKeyGenerator 的 bean
	 * 
	 */
	@Override
	//@Cacheable(value = "getAllUsers",keyGenerator="wiselyKeyGenerator")
	public List<User> getAllUsers() {
		//return userDao.findAll();
		try {
			return  genericDao.findAll(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}