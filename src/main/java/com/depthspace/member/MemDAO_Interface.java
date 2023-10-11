package com.depthspace.member;

import java.util.List;

public interface MemDAO_Interface {
	 public void insert(MemVO MemVO);
	 public void update(MemVO MemVO);
	 public void delete(Integer memId);
	 public MemVO findByPrimaryKey(Integer memId);
	 public List<MemVO> getAll();
}
