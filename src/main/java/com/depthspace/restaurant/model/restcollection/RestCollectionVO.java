package com.depthspace.restaurant.model.restcollection;

import java.io.Serializable;

public class RestCollectionVO implements Serializable {
	private Integer restId;
	private Integer memId;

	public RestCollectionVO() {
		super();
	}

	public RestCollectionVO(Integer restId, Integer memId) {
		super();
		this.restId = restId;
		this.memId = memId;
	}

	public Integer getRestId() {
		return restId;
	}

	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

}
