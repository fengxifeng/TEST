package com.vwmam.eventm.result;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PageResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3061806411696876766L;

	@ApiModelProperty(value ="当前页")
	private int currentPage;
	
	@ApiModelProperty(value ="每页数量")
	private int size;
	
	@ApiModelProperty(value ="总数量")
	private long count;
	
	@ApiModelProperty(value ="返回数据")
	private List<T> list;

	
	
	public PageResult(int currentPage, int size, long count, List<T> list) {
		super();
		this.currentPage = currentPage;
		this.size = size;
		this.count = count;
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageResult [currentPage=" + currentPage + ", size=" + size + ", count=" + count + ", list=" + list
				+ "]";
	}
	
	
}
