package com.vwmam.eventm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vwmam.eventm.entity.EventLevel;

public interface EventLevelDao extends JpaRepository<EventLevel,String>,JpaSpecificationExecutor<EventLevel>{

}
