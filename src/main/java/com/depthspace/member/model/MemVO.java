package com.depthspace.member.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEM")
public class MemVO implements Serializable {
	@Id
	@Column(name = "MEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memId;

	@Column(name = "MEM_ACC")
	private String memAcc;

	@Column(name = "MEM_PWD")
	private String memPwd;

	@Column(name = "MEM_NAME")
	private String memName;

	@Column(name = "MEM_IDENTITY")
	private String memIdentity;

	@Column(name = "MEM_BTH")
	private Date memBth;

	@Column(name = "MEM_SEX")
	private byte memSex;

	@Column(name = "MEM_EMAIL")
	private String memEmail;

	@Column(name = "MEM_TEL")
	private Integer memTel;

	@Column(name = "MEM_ADD")
	private String memAdd;

	@Column(name = "ACC_STATUS")
	private byte accStatus;

	@Column(name = "MEM_POINT")
	private Integer memPoint;

	@Column(name = "MEM_IMAGE", columnDefinition = "mediumblob")
	private byte[] memImage;
	
	private String base64Image;

	public String getBase64Image() {
        return base64Image;
    }
	
	public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

	public MemVO() {
	}
	
	public MemVO(Integer memId, String memAcc, String memPwd, String memName, String memIdentity, java.sql.Date memBth,
			byte memSex, String memEmail, Integer memTel, String memAdd, byte accStatus, Integer memPoint,
			byte[] memImage) {
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
		this.memImage = memImage;
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

	public Date getMemBth() {
		return memBth;
	}

	public void setMemBth(Date memBth) {
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

	public byte[] getMemImage() {
		return memImage;
	}

	public void setMemImage(byte[] memImage) {
		this.memImage = memImage;
	}
	@Override
	public String toString() {
		return "MemVO [memId=" + memId + ", memAcc=" + memAcc + ", memPwd=" + memPwd + ", memName=" + memName
				+ ", memIdentity=" + memIdentity + ", memBth=" + memBth + ", memSex=" + memSex + ", memEmail="
				+ memEmail + ", memTel=" + memTel + ", memAdd=" + memAdd + ", accStatus=" + accStatus + ", memPoint="
				+ memPoint + ", memImage=" + Arrays.toString(memImage) + ", base64Image=" + base64Image + "]";
	}

}
