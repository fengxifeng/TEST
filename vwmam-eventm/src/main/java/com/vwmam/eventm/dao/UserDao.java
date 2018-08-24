package com.vwmam.eventm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vwmam.eventm.entity.User;

/**
 * 用户 dao 接口
 * @author weijinyun
 *
 */
public interface UserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>{

}
