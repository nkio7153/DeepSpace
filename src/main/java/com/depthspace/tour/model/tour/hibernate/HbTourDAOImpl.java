package com.depthspace.tour.model.tour.hibernate;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.promotion.model.promotion.PromotionVO;
import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;

public class HbTourDAOImpl implements HbTourDAO_Interface{
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbTourDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
    //新增行程
	@Override
	public int insert(TourVO entity) {
		return (Integer) getSession().save(entity);
	}
	//更新行程
	@Override
	public int update(TourVO entity) {
		try {
            getSession().update(entity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
	}
	
//	刪除行程
	@Override
	public int delete(Integer tourId , Integer memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TourVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
//	用會員編號查該會員所有行程
	@Override
	public List<TourVO> getByMemId(Integer memId) {
		return getSession()
                .createQuery("from TourVO where memId= :memId",TourVO.class)
                .setParameter("memId", memId)
                .list();
	}
	@Override
	public int delete(Integer tourId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//查詢最後一筆行程
	@Override
	public TourVO getLastTourId(Integer tourId) {
		 try {
	            return getSession()
	            		.createQuery("from TourVO order by tourId desc", TourVO.class)
	                    .setMaxResults(1)
	                    .getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	@Override
	public List<TourView> getOneTourList(Integer tourId, Integer memId) {
////		System.out.println("tourId=" + tourId + "memId=" + memId);
//	    Session session = getSession();
//	    Transaction tx = null;
	    List<TourView> list = null;
//	    
//	    try {
////	        tx = session.beginTransaction();
//
//	        list = session.createQuery("SELECT DISTINCT tv from TourView tv where tv.tourId= :tourId AND tv.memId = :memId", TourView.class)
//	            .setParameter("tourId", tourId)
//	            .setParameter("memId", memId)
//	            .list();
//	        
////	        tx.commit();
//	    } catch (Exception e) {
//	        if (tx != null) {
////	            tx.rollback();
//	        	System.out.println("錯誤");
//	        }
//	        e.printStackTrace();
//	    } finally {
//	        session.close();
//	    }

	    return list;
	}

		
//		return getSession()
//				.createQuery("SELECT DISTINCT tv from TourView tv where tv.tourId= :tourId AND tv.memId = :memId" , TourView.class)
//				.setParameter("tourId", tourId)
//			    .setParameter("memId", memId)
//			    .list();
//	}
	
	

}
