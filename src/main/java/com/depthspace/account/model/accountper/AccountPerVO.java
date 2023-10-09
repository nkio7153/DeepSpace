package com.depthspace.account.model.accountper;

import java.io.Serializable;

public class AccountPerVO implements Serializable {
	private Integer accountPerId;
	private String accountPerName;
	private Integer accountId;
	private Integer moneySum;
	private Integer accountSum;
	private Integer debt;

	public AccountPerVO() {

	}

	public AccountPerVO(Integer accountPerId, String accountPerName, Integer accountId, Integer moneySum,
			Integer accountSum, Integer debt) {
		this.accountPerId = accountPerId;
		this.accountPerName = accountPerName;
		this.accountId = accountId;
		this.moneySum = moneySum;
		this.accountSum = accountSum;
		this.debt = debt;
	}

	public Integer getAccountPerId() {
		return accountPerId;
	}

	public void setAccountPerId(Integer accountPerId) {
		this.accountPerId = accountPerId;
	}

	public String getAccountPerName() {
		return accountPerName;
	}

	public void setAccountPerName(String accountPerName) {
		this.accountPerName = accountPerName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getMoneySum() {
		return moneySum;
	}

	public void setMoneySum(Integer moneySum) {
		this.moneySum = moneySum;
	}

	public Integer getAccountSum() {
		return accountSum;
	}

	public void setAccountSum(Integer accountSum) {
		this.accountSum = accountSum;
	}

	public Integer getDebt() {
		return debt;
	}

	public void setDebt(Integer debt) {
		this.debt = debt;
	}

}
