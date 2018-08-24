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
public class EventLevelVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272076178476863840L;
	
	
	@ApiModelProperty(value ="事件等级code")
    @NotEmpty(message = "事件等级code不能为空")
	private String code;
	
	@ApiModelProperty(value ="事件等级")
    @NotEmpty(message = "事件等级name不能为空")
	private String name;
	
	@ApiModelProperty(value ="事件等级remark")
	private String remark;
	
	@ApiModelProperty(value ="事件等级所属类id")
    @NotEmpty(message = "事件等级所属类id不能为空")
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

	@Override
	public String toString() {
		return "EventVO [code=" + code + ", name=" + name + ", remark=" + remark + ", classId=" + classId + "]";
	}
	
	
}
