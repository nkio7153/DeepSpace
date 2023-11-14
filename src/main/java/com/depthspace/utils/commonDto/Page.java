package com.depthspace.utils.commonDto;

/**
 * 分頁功能
 * */

public class Page<T>{
    private  Integer limit;//限制欄位
    private  Integer offset;//從第幾筆開始

    private  Integer total;//總查詢數，可用於頁數顯示 total/limit
    private  T rs;
    
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public T getRs() {
		return rs;
	}
	public void setRs(T rs) {
		this.rs = rs;
	}


}
