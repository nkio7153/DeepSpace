package com.depthspace.admin.dto;


public class QueryAdminParameter {
    private  String search;
    private Integer page;//當前頁數
    private Integer size;//該頁顯示筆數
    
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
    
    
}
