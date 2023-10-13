package com.depthspace.account.model.accountitem;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_ITEM")
public class AccountItemVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ITEM_ID")
	private Integer accountItemId;
	@Column(name = "ACCOUNT_ITEM")
	private String accountItem;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "CONSUMEACCOUNT")
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

	@Override
	public String toString() {
		return "AccountItemVO [accountItemId=" + accountItemId + ", accountItem=" + accountItem + ", date=" + date
				+ ", consumeAccount=" + consumeAccount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountItem, accountItemId, consumeAccount, date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountItemVO other = (AccountItemVO) obj;
		return Objects.equals(accountItem, other.accountItem) && Objects.equals(accountItemId, other.accountItemId)
				&& Objects.equals(consumeAccount, other.consumeAccount) && Objects.equals(date, other.date);
	}

}
