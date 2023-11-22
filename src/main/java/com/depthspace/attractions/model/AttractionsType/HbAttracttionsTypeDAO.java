package com.depthspace.attractions.model.AttractionsType;

import java.util.List;

import com.depthspace.attractions.model.AttractionsTypeVO;


public interface HbAttracttionsTypeDAO {
		//新增
		AttractionsTypeVO insert(AttractionsTypeVO entity);
		// 修改
		int update(AttractionsTypeVO entity);

		//刪除
		int delete(Integer attractionsTypeId);

		List<AttractionsTypeVO> getOneById(Integer attractionsTypeId);
		AttractionsTypeVO getOne(Integer attractionsTypeId);
		
		List<AttractionsTypeVO> getAll();
		

}
