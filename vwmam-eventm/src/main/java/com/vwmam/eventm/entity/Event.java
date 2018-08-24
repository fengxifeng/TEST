package com.vwmam.eventm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Event 实体类
 * @author chuxunfeng
 * Thu Aug 23 10:01:31 CST 2018
**/
@Entity
@Table(name = "event")
public class Event implements java.io.Serializable {

	private static final long serialVersionUID = 1534989691505L;
	
	@Id
	@Column(name = "event_id", nullable = false, precision = 32, scale = 0)
	private String eventId;
	
	@Column(name = "class_id", nullable = false, precision = 32, scale = 0)
	private String classId;
	
	@Column(name = "code", nullable = false, precision = 255, scale = 0)
	private String code;
	
	@Column(name = "name", nullable = false, precision = 255, scale = 0)
	private String name;
	
	@Column(name = "remark", nullable = true, precision = 255, scale = 0)
	private String remark;
	
	@Column(name = "add_time", nullable = false, precision = 19, scale = 0)
	private Date addTime;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	

}

