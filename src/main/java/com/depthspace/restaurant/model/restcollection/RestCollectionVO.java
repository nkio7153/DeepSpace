package com.depthspace.restaurant.model.restcollection;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT_COLLECTION")
@IdClass(RestCollectionVO.CompositeDetail.class)
public class RestCollectionVO implements Serializable {
	@Id
	@Column(name = "REST_ID")
	private Integer restId;
	
	@Id
	@Column(name = "MEM_ID")
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

	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(restId, memId);
	}
	
	public void setCompositeKey(CompositeDetail key) {
		this.restId = key.getRestId();
		this.memId = key.getMemId();
	}
	
	
	@Override
	public String toString() {
		return "RestCollectionVO [restId=" + restId + ", memId=" + memId + "]";
	}


	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer restId;
		private Integer memId;

		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer restId, Integer memId) {
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
		public String toString() {
			return "CompositeDetail [restId=" + restId + ", memId=" + memId + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(memId, other.memId) && Objects.equals(restId, other.restId);
		}
		


	}

}
