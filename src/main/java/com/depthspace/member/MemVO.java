package com.depthspace.member;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="MEM")
public class MemVO implements Serializable{
	@Id
	@Column(name="MEM_ID")
	private Integer memId;
	
	@Column(name="MEM_ACC")
	private String memAcc;
	
	@Column(name="MEM_PWD")
	private String memPwd;
	
	@Column(name="MEM_NAME")
	private String memName;
	
	@Column(name="MEM_IDENTITY")
	private String memIdentity;
	
	@Column(name="MEM_BTH")
	private Timestamp memBth;
	
	@Column(name="MEM_SEX")
	private byte memSex;
	
	@Column(name="MEM_EMAIL")
	private String memEmail;
	
	@Column(name="MEM_TEL")
	private Integer memTel;
	
	@Column(name="MEM_ADD")
	private String memAdd;
	
	@Column(name="ACC_STATUS")
	private byte accStatus;
	
	@Column(name="MEM_POINT")
	private Integer memPoint;
	public MemVO() {
		super();
	}
	public MemVO(Integer memId, String memAcc, String memPwd, String memName, String memIdentity, Timestamp memBth,
			byte memSex, String memEmail, Integer memTel, String memAdd, byte accStatus, Integer memPoint) {
		super();
		this.memId = memId;
		this.memAcc = memAcc;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memIdentity = memIdentity;
		this.memBth = memBth;
		this.memSex = memSex;
		this.memEmail = memEmail;
		this.memTel = memTel;
		this.memAdd = memAdd;
		this.accStatus = accStatus;
		this.memPoint = memPoint;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemIdentity() {
		return memIdentity;
	}
	public void setMemIdentity(String memIdentity) {
		this.memIdentity = memIdentity;
	}
	public Timestamp getMemBth() {
		return memBth;
	}
	public void setMemBth(Timestamp memBth) {
		this.memBth = memBth;
	}
	public byte getMemSex() {
		return memSex;
	}
	public void setMemSex(byte memSex) {
		this.memSex = memSex;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public Integer getMemTel() {
		return memTel;
	}
	public void setMemTel(Integer memTel) {
		this.memTel = memTel;
	}
	public String getMemAdd() {
		return memAdd;
	}
	public void setMemAdd(String memAdd) {
		this.memAdd = memAdd;
	}
	public byte getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(byte accStatus) {
		this.accStatus = accStatus;
	}
	public Integer getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(Integer memPoint) {
		this.memPoint = memPoint;
	}
	@Override
	public String toString() {
		return "MemVO [memId=" + memId + ", memAcc=" + memAcc + ", memPwd=" + memPwd + ", memName=" + memName
				+ ", memIdentity=" + memIdentity + ", memBth=" + memBth + ", memSex=" + memSex + ", memEmail="
				+ memEmail + ", memTel=" + memTel + ", memAdd=" + memAdd + ", accStatus=" + accStatus + ", memPoint="
				+ memPoint + "]";
	}
	
	
}
