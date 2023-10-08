package com.depthspace.account.model.account;

import java.io.Serializable;

public class AccountVO implements Serializable {
	private Integer accountId;
	private String accountName;
	private Integer memId;

	public AccountVO() {

	}

	public AccountVO(Integer accountId, String accountName, Integer memId) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.memId = memId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

}
