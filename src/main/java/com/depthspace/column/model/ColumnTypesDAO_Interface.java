package com.depthspace.column.model;

import java.util.List;

public interface ColumnTypesDAO_Interface {
    public void insert(ColumnTypesVO columnTypesVO);
    public void update(ColumnTypesVO columnTypesVO);
    public void delete(Integer colTypeId);
    public ColumnTypesVO findByPrimaryKey(Integer colTypeId);
    public List<ColumnTypesVO> getAll();
}
