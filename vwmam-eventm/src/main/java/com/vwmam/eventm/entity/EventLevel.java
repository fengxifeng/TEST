package com.vwmam.eventm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EventLevel 实体类
 * @author chuxunfeng
 * Thu Aug 23 10:02:52 CST 2018
**/
@Entity
@Table(name = "event_level")
public class EventLevel implements java.io.Serializable {

	private static final long serialVersionUID = 1534989772386L;
	
	
	private String levelId;
	
	@Column(name = "class_id", nullable = false, precision = 32, scale = 0)
	private String classId;
	
	@Column(name = "Code", nullable = false, precision = 255, scale = 0)
	private String code;
	
	@Column(name = "Name", nullable = false, precision = 255, scale = 0)
	private String name;
	
	@Column(name = "Remark", nullable = false, precision = 255, scale = 0)
	private String remark;
	
	@Column(name = "add_time", nullable = false, precision = 19, scale = 0)
	private Date addTime;

	
	@Id
	@Column(name = "level_id", nullable = false, precision = 32, scale = 0)
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
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

