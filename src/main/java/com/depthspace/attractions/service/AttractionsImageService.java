package com.depthspace.attractions.service;

import java.util.List;

import com.depthspace.attractions.model.AttractionsImagesVO;
import com.depthspace.attractions.model.attractionsImages.hibernate.AttractionsImagesImpl;
import com.depthspace.attractions.model.attractionsImages.hibernate.AttractionsImages_Interface;
import com.depthspace.utils.HibernateUtil;

public class AttractionsImageService {
	private AttractionsImages_Interface dao;
	
	public AttractionsImageService() {
		dao = new AttractionsImagesImpl(HibernateUtil.getSessionFactory());
	}
	//取得一筆或多筆圖片資料
	public List<AttractionsImagesVO> getAttractionsImagesById(Integer attractionsImagesId){
		return dao.getAttractionsImagesById(attractionsImagesId);
	}
	public AttractionsImagesVO findByPrimaryKey(Integer attractionsImagesId){
		return dao.findByPrimaryKey(attractionsImagesId);
	}
	public AttractionsImagesVO findByAttrId(Integer attractionsId){
		return dao.findByAttrId(attractionsId);
	}
	
	
	public AttractionsImagesVO save(AttractionsImagesVO attrImg) {
		dao.add(attrImg);
		return attrImg;
//		try {
//	        dao.add(attrImg);
//	        return attrImg;
//	    } catch (Exception e) {
//	        // 在發生例外時，請確保進行事務回滾或其他必要的清理工作
//	        e.printStackTrace(); // 在實際環境中，你可能會想要以適當的方式處理例外狀況，例如記錄錯誤或拋出自定義例外狀況
//	        throw new RuntimeException("保存圖片時發生錯誤", e);
//	    } finally {
//	        // 確保釋放資源，無論是否發生例外
//	        HibernateUtil.shutdown(); // 這是一個虛構的方法，你需要根據你的 HibernateUtil 進行相應的資源釋放
//	    }
	}
	
	public void update(AttractionsImagesVO attrImg) {
		dao.update(attrImg);
	}
	
}
