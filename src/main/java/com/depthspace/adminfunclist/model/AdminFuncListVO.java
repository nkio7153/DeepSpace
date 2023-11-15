package com.depthspace.adminfunclist.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.google.gson.annotations.Expose;
import com.depthspace.adminfunclist.model.AdminFuncListVO;


@Entity
@Table(name="ADMIN_FUNC_LIST")
@IdClass(AdminFuncListVO.CompositeDetail.class)
public class AdminFuncListVO implements Serializable {

	@Id
	@Column(name="ADMIN_ID")
	private Integer adminId;
	@Id
	@Column(name="FUNC_ID")
	private Integer funcId;
	
	public AdminFuncListVO() {

	}
	
	public AdminFuncListVO(Integer adminId, Integer funcId) {
		this.adminId = adminId;
		this.funcId = funcId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getFuncId() {
		return funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}	
	
	public static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer adminId;
		private Integer funcId;

		public CompositeDetail() {
			
		}

		public CompositeDetail(Integer adminId, Integer funcId) {
			this.adminId = adminId;
			this.funcId = funcId;
		}

		public Integer getAdminId() {
			return adminId;
		}

		public void setAdminId(Integer adminId) {
			this.adminId = adminId;
		}

		public Integer getFuncId() {
			return funcId;
		}

		public void setFuncId(Integer funcId) {
			this.funcId = funcId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(adminId, funcId);
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
			return Objects.equals(adminId, other.adminId) && Objects.equals(funcId, other.funcId);
		}
	}
}
