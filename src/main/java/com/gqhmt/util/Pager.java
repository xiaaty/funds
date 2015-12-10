package com.gqhmt.util;

import java.util.HashMap;
import java.util.Map;


public class Pager {
	private long totalRows=0; // 总行数

	private long pageSize= GlobalConstants.PAGE_SIZE; // 每页显示的行数

	private long currentPage=1; // 当前页号

	private long totalPages=1; // 总页数

	private long startRow=0; // 当前页在数据库中的起始行

	private String linkUrl; // 要跳转的URL
	
	
	private Map<String,String>  searchCondtions;

	public Pager() {
	}
 
	public Pager(long _currentPage,long _totalRows, long _pageSize)
	{
		
		totalRows = _totalRows;
		pageSize = _pageSize;
		totalPages = totalRows / pageSize;

        long mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		this.currentPage=_currentPage;
		if(this.currentPage==0){
			this.currentPage=1;
		}
			this.setStart(_currentPage);
	}
	public void setStart(long currentPage) {
		if(currentPage==0||currentPage==1){
			this.startRow=0;
		}else{
			this.startRow = (currentPage - 1) * pageSize;
		}
}
	public static int getStartRow(int currentPage){
		if(currentPage==0||currentPage==1){
			return 0;
		}else{
			return (currentPage - 1) * GlobalConstants.PAGE_SIZE;
		}
	}
	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getLimit(){
		return getStartRow()+pageSize;
	}
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public void addCondition(String name,String value) {
		if(this.searchCondtions==null){
			this.searchCondtions=new HashMap<String,String>();
		}
		this.searchCondtions.put(name, value);
	}

	public Map<String, String> getSearchCondtions() {
		return searchCondtions;
	}

	public void setSearchCondtions(Map<String, String> searchCondtions) {
		this.searchCondtions = searchCondtions;
	}
	
}
