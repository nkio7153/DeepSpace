package com.depthspace.account.model.accountper;

import java.util.List;

public interface AccountPerDAO_Interface {
	public void insert(AccountPerVO tod);
    public void update(AccountPerVO tod);
    public void delete(Integer accountPerId);
    public AccountPerVO findByPrimaryKey(Integer accountPerId);
    public List<AccountPerVO> getAll();
}
