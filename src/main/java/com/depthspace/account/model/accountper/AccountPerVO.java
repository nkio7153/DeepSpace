package com.depthspace.account.model.accountper;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_PER")
public class AccountPerVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_PER_ID")
	private Integer accountPerId;
	@Column(name = "ACCOUNT_PER_NAME")
	private String accountPerName;
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;
	@Column(name = "MONEY_SUM")
	private Integer moneySum;
	@Column(name = "ACCOUNT_SUM")
	private Integer accountSum;
	@Column(name = "DEBT")
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

	@Override
	public String toString() {
		return "AccountPerVO [accountPerId=" + accountPerId + ", accountPerName=" + accountPerName + ", accountId="
				+ accountId + ", moneySum=" + moneySum + ", accountSum=" + accountSum + ", debt=" + debt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountPerId, accountPerName, accountSum, debt, moneySum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountPerVO other = (AccountPerVO) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(accountPerId, other.accountPerId)
				&& Objects.equals(accountPerName, other.accountPerName) && Objects.equals(accountSum, other.accountSum)
				&& Objects.equals(debt, other.debt) && Objects.equals(moneySum, other.moneySum);
	}

}
