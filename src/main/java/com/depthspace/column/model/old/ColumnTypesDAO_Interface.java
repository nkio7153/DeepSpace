package com.depthspace.column.model.old;

import java.util.List;

import com.depthspace.column.model.ColumnTypesVO;

public interface ColumnTypesDAO_Interface {
    public void insert(ColumnTypesVO columnTypesVO);
    public void update(ColumnTypesVO columnTypesVO);
    public void delete(Integer colTypeId);
    public ColumnTypesVO findByPrimaryKey(Integer colTypeId);
    public List<ColumnTypesVO> getAll();
}
