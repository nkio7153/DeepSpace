package com.depthspace.column.model;

import java.util.List;

public interface ColumnArticlesDAO_Interface {
    public void insert(ColumnArticlesVO columnArticlesVO);
    public void update(ColumnArticlesVO columnArticlesVO);
    public void delete(Integer aritId);
    public ColumnArticlesVO findByPrimaryKey(Integer aritId);
    public List<ColumnArticlesVO> getAll();
}
