package com.depthspace.account.model.accountitem;

import java.io.Serializable;
import java.sql.Date;

public class AccountItemVO implements Serializable {
	private Integer accountItemId;
	private String accountItem;
	private Date date;
	private Integer consumeAccount;

	public AccountItemVO() {

	}

	public AccountItemVO(Integer accountItemId, String accountItem, Date date, Integer consumeAccount) {
		this.accountItemId = accountItemId;
		this.accountItem = accountItem;
		this.date = date;
		this.consumeAccount = consumeAccount;
	}

	public Integer getAccountItemId() {
		return accountItemId;
	}

	public void setAccountItemId(Integer accountItemId) {
		this.accountItemId = accountItemId;
	}

	public String getAccountItem() {
		return accountItem;
	}

	public void setAccountItem(String accountItem) {
		this.accountItem = accountItem;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getConsumeAccount() {
		return consumeAccount;
	}

	public void setConsumeAccount(Integer consumeAccount) {
		this.consumeAccount = consumeAccount;
	}

}
