package com.sc.jysc.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@ApiModel
public class CurrentPage<T> implements Serializable{
	@ApiModelProperty("页码,第一页为0")
	private int pageNum; //页码,第一页为0
	@ApiModelProperty("每页数据量")
	private int pageSize; //每页数据量
	@ApiModelProperty("数据总量")
	private int rowCount; //数据总量
	private List<T> pageItems = new ArrayList<T>();
	
	public CurrentPage(){}
	
	public CurrentPage(int pageNum, int pageSize, int rowCount){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
	} 
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<T> getPageItems() {
		return pageItems;
	}
	public void setPageItems(List<T> pageItems) {
		this.pageItems = pageItems;
	}
	
	
}
