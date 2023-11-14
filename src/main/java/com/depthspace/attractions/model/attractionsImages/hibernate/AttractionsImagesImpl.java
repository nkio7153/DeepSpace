package com.depthspace.attractions.model.attractionsImages.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsImagesVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public class AttractionsImagesImpl implements AttractionsImages_Interface{
	private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public AttractionsImagesImpl(SessionFactory factory) {
        this.factory = factory;
    }
    public AttractionsImagesImpl() {
	}
	//取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
	@Override
	public void insert(AttractionsImagesVO AttractionsImagesVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(AttractionsImagesVO AttractionsImagesVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer attractionsImagesId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public AttractionsImagesVO findByPrimaryKey(Integer attractionsImagesId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AttractionsImagesVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//根據景點Id取得一或多筆圖片資料
	@Override
	public List<AttractionsImagesVO> getAttractionsImagesById(Integer attractionsImagesId) {
		return getSession().createQuery("from AttractionsImagesVO where attractionsImagesId= :attractionsImagesId", AttractionsImagesVO.class)
				.setParameter("attractionsImagesId", attractionsImagesId).list();

	}
	
	
	

}
