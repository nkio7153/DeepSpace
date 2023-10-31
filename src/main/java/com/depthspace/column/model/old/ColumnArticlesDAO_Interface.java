package com.depthspace.column.model.old;

import java.util.List;

import com.depthspace.column.model.ColumnArticlesVO;

public interface ColumnArticlesDAO_Interface {
    public void insert(ColumnArticlesVO columnArticlesVO);
    public void update(ColumnArticlesVO columnArticlesVO);
    public void delete(Integer aritId);
    public ColumnArticlesVO findByPrimaryKey(Integer aritId);
    public List<ColumnArticlesVO> getAll();
}
