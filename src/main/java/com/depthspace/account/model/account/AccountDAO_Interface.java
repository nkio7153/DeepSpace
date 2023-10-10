package com.depthspace.account.model.account;

import java.util.List;

public interface AccountDAO_Interface {
	public void insert(AccountVO tod);
    public void update(AccountVO tod);
    public void delete(Integer accountId);
    public AccountVO findByPrimaryKey(Integer accountId);
    public List<AccountVO> getAll();
}
