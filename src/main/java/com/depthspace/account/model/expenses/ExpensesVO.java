package com.depthspace.account.model.expenses;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "EXPENSES")
@IdClass(ExpensesVO.CompositeDetail.class)
public class ExpensesVO implements Serializable {
	@Id
	@Column(name = "ACCOUNT_ITEM_ID")
	private Integer accountItemId;
	@Id
	@Column(name = "ACCOUNT_PER_ID")
	private Integer accountPerId;
	@Column(name = "PER_CONSUME_ACCOUNT")
	private Integer perConsumeAccount;
	@Column(name = "MONEY")
	private Integer money;

	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(accountItemId, accountPerId);
	}

	public void setCompositeKey(CompositeDetail key) {
		this.accountItemId = key.getAccountItemId();
		this.accountPerId = key.getAccountPerId();
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

	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer accountItemId;
		private Integer accountPerId;

		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer accountItemId, Integer accountPerId) {
			super();
			this.accountItemId = accountItemId;
			this.accountPerId = accountPerId;
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

		@Override
		public int hashCode() {
			return Objects.hash(accountItemId, accountPerId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(accountItemId, other.accountItemId)
					&& Objects.equals(accountPerId, other.accountPerId);
		}

	}

	@Override
	public String toString() {
		return "ExpensesVO [accountItemId=" + accountItemId + ", accountPerId=" + accountPerId + ", perConsumeAccount="
				+ perConsumeAccount + ", money=" + money + "]";
	}

}
