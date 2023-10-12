package com.depthspace.restaurant.model.restcollection;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(memId, restId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestCollectionVO other = (RestCollectionVO) obj;
		return Objects.equals(memId, other.memId) && Objects.equals(restId, other.restId);
	}

	@Override
	public String toString() {
		return "RestCollectionVO [restId=" + restId + ", memId=" + memId + "]";
	}

}
