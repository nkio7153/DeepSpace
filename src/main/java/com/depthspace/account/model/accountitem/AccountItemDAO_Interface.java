package com.depthspace.account.model.accountitem;

import java.util.List;

public interface AccountItemDAO_Interface {
	public void insert(AccountItemVO tod);
    public void update(AccountItemVO tod);
    public void delete(Integer accountItemId);
    public AccountItemVO findByPrimaryKey(Integer accountItemId);
    public List<AccountItemVO> getAll();
}
