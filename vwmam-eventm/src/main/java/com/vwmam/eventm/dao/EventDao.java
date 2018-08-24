package com.vwmam.eventm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vwmam.eventm.entity.Event;

public interface EventDao extends JpaRepository<Event,String>,JpaSpecificationExecutor<Event>{

}
