package com.depthspace.tour.model.tourdays.hibernate;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.tour.model.TourDaysVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;

public class HbTourDaysDAOImpl implements HbTourDaysDAO_Interface{
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbTourDaysDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
    //新增行程
	@Override
	public int insert(TourDaysVO entity) {
		return (Integer) getSession().save(entity);
		
	}
	@Override
	public void update(TourDaysVO entity) {
		getSession().update(entity);
		
	}
	@Override
	public void delete(Integer tourDaysId) {
		getSession().delete(tourDaysId);
		
	}
	@Override
	public void deleteByTourId(Integer tourId){
		getSession().createQuery("delete from TourDaysVO where tourId =:tourId")
				.setParameter("tourId",tourId)
				.executeUpdate();
	}
	@Override
	public TourDaysVO findByPrimaryKey(Integer tourDaysId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TourDaysVO> getAll() {
		return getSession().createQuery("from TourDaysVO", TourDaysVO.class).list();
		}
		
	@Override
	public List<TourDaysVO> getOneTour(Integer tourId) {
		return getSession()
                .createQuery("from TourDaysVO where tourId= :tourId",TourDaysVO.class)
                .setParameter("tourId", tourId)
                .list();
	
	}
	@Override
	public TourDaysVO getLastTourDaysId(Integer tourDaysId) {
		try {
            return getSession()
            		.createQuery("from TourDaysVO order by tourDaysId desc", TourDaysVO.class)
            		.setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
	
}
