package com.depthspace.account.model.account;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class AccountVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private Integer accountId;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "MEM_ID")
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

	@Override
	public String toString() {
		return "AccountVO [accountId=" + accountId + ", accountName=" + accountName + ", memId=" + memId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountName, memId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountVO other = (AccountVO) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(accountName, other.accountName)
				&& Objects.equals(memId, other.memId);
	}

}
