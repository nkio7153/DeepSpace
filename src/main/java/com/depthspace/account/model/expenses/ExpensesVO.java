package com.depthspace.account.model.expenses;

import java.io.Serializable;

public class ExpensesVO implements Serializable {
	private Integer accountItemId;
	private Integer accountPerId;
	private Integer perConsumeAccount;
	private Integer money;

	public ExpensesVO() {

	}

	public ExpensesVO(Integer accountItemId, Integer accountPerId, Integer perConsumeAccount, Integer money) {
		this.accountItemId = accountItemId;
		this.accountPerId = accountPerId;
		this.perConsumeAccount = perConsumeAccount;
		this.money = money;
	}

	public Integer getAccountItemId() {
		return accountItemId;
	}

	public void setAccountItemId(Integer accountItemId) {
		this.accountItemId = accountItemId;
	}

	public Integer getAccountPerId() {
		return accountPerId;
	}

	public void setAccountPerId(Integer accountPerId) {
		this.accountPerId = accountPerId;
	}

	public Integer getPerConsumeAccount() {
		return perConsumeAccount;
	}

	public void setPerConsumeAccount(Integer perConsumeAccount) {
		this.perConsumeAccount = perConsumeAccount;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

}
