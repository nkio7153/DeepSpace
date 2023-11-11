package com.depthspace.utils.commonDto;

public class ResponsePage<T> {
    private  Integer  page;//當前頁數
    private  Integer size;//顯示筆數
    private  Integer  total;//總筆數
    private  T body;
    
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
    
    
}
