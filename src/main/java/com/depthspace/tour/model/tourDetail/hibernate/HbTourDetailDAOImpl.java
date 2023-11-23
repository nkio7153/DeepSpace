package com.depthspace.tour.model.tourDetail.hibernate;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.TourDetailVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;

public class HbTourDetailDAOImpl implements HbTourDetailDAO_Interface{
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbTourDetailDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
	@Override
	public void insert(TourDetailVO entity) {
		getSession().save(entity);
		
	}
	@Override
	public void update(TourDetailVO tourDetail) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Integer tourDaysId, Integer attractionsId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteByTourDaysId(Integer tourDaysId){
		getSession().createQuery("delete from TourDetailVO where tourDaysId =:tourDaysId")
				.setParameter("tourDaysId",tourDaysId)
				.executeUpdate();
	}
	@Override
	public TourDetailVO findByPrimaryKey(Integer tourDaysId, Integer attractionsId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TourDetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TourDetailVO> getTourDaysId(Integer tourDaysId) {
		return getSession()
                .createQuery("from TourDetailVO where tourDaysId= :tourDaysId",TourDetailVO.class)
                .setParameter("tourDaysId", tourDaysId)
                .list();
	
	}
	
    
}
