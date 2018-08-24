package com.vwmam.eventm.result;

import java.io.Serializable;

public class JsonResult {
	
	public static <T extends Serializable> ResultModel<T> success(){
		return success(null);		
	}
	
	public static <T extends Serializable> ResultModel<T> success(T data){
		return success("成功",data);		
	}
	
	public static <T extends Serializable> ResultModel<T> success(String message,T data){
		return success(true,message,data);
	}
	
	public static <T extends Serializable> ResultModel<T> success(boolean status,String message,T data){
		ResultModel<T> result=new ResultModel<T>(status,message,data);
		return result;
	}
	
	
	public static <T extends Serializable> ResultModel<T> error(){
		return error(null);		
	}
	
	public static <T extends Serializable> ResultModel<T> error(T data){
		return error("失败",data);		
	}
	
	public static <T extends Serializable> ResultModel<T> error(String message){
		return error(message,null);		
	}
	
	public static <T extends Serializable> ResultModel<T> error(String message,T data){
		return error(false,message,data);
	}
	
	public static <T extends Serializable> ResultModel<T> error(boolean status,String message,T data){
		ResultModel<T> result=new ResultModel<T>(status,message,data);
		return result;
	}
}
