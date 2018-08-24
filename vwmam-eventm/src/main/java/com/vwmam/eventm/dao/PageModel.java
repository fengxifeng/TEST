/**
 * Copyright: Volkswagen Group China – Mobility Asia
 */
package com.vwmam.eventm.dao;

import java.util.List;

/**
 * 分页功能
 */
public class PageModel {
	private List objectList = null;//选择的数据内容详情
	private boolean success = true;
	private int hasNext = 0;// 是否有下一页
	private int totalPage = 0;// 总页数
	private int pageNo = 0;// 当前页数
	private int rowCount = 0;// 总条数
	private int start = 1;
	private int limit;

	public PageModel() {
	}

	public PageModel(int pageNo, int pageSize) {
		pageSize = pageSize == 0 ? 20 : pageSize;
		this.start = (pageNo <= 1 ? 1 : (pageNo - 1) * pageSize + 1);
		this.limit = pageSize;
	}

	public List getObjectList() {
		return this.objectList;
	}

	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return this.limit == 0 ? 20 : this.limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getPageNo() {
		return getStart() / getLimit() + 1;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getHasNext() {
		return hasNext;
	}

	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}
}