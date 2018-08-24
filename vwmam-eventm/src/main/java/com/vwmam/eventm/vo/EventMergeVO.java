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
public class EventMergeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272076178476863840L;
	
	@NotEmpty(message = "事件id不能为空")
	@ApiModelProperty(value ="事件code")
	private String eventId;
	
	@ApiModelProperty(value ="事件code")
	private String code;
	
	@ApiModelProperty(value ="事件name")
	private String name;
	
	@ApiModelProperty(value ="事件remark")
	private String remark;
	
	@ApiModelProperty(value ="事件所属类id")
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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "EventMergeVO [eventId=" + eventId + ", code=" + code + ", name=" + name + ", remark=" + remark
				+ ", classId=" + classId + "]";
	}

	
	
}
