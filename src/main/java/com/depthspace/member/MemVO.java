package com.depthspace.member;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemVO implements Serializable{
	private Integer memId;
	private String memAcc;
	private String memPwd;
	private String memName;
	private String memIdentity;
	private Timestamp memBth;
	private byte memSex;
	private String memEmail;
	private Integer memTel;
	private String memAdd;
	private byte accStatus;
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
	
}
