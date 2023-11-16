package com.depthspace.function.model;

import java.util.List;
import java.util.Map;

public interface FunctionDAO {

	public void insert(FunctionVO functionVO);
	public void update(FunctionVO functionVO);
	public void delete(Integer funcId);
	public FunctionVO findByPrimaryKey(Integer funcId);
    public List<FunctionVO> getAll();
}