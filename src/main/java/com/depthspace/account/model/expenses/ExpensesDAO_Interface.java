package com.depthspace.account.model.expenses;

import java.util.List;

public interface ExpensesDAO_Interface {
    public void insert(ExpensesVO tod);
    public void update(ExpensesVO tod);
    public void delete(Integer accountItemId, Integer accountPerId);
    public ExpensesVO findByPrimaryKey(Integer accountItemId, Integer accountPerId);
    public List<ExpensesVO> getAll();
}
