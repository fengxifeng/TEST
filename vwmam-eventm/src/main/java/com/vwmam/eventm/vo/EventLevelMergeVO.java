package com.vwmam.eventm.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "event事件对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventLevelMergeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272076178476863840L;
	
	@NotEmpty(message = "事件级别d不能为空")
	@ApiModelProperty(value ="事件级别code")
	private String levelId;
	
	@ApiModelProperty(value ="事件级别code")
	private String code;
	
	@ApiModelProperty(value ="事件级别name")
	private String name;
	
	@ApiModelProperty(value ="事件级别remark")
	private String remark;
	
	@ApiModelProperty(value ="事件级别所属类id")
	private String classId;

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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	@Override
	public String toString() {
		return "EventMergeVO [eventId=" + levelId + ", code=" + code + ", name=" + name + ", remark=" + remark
				+ ", classId=" + classId + "]";
	}

	
	
}
