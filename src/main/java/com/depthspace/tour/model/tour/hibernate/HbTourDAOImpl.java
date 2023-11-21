package com.depthspace.tour.model.tour.hibernate;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
                .createQuery("from TourVO where memId= :memId Order by tourId desc",TourVO.class)
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
	public List<TourVO> getOneByMemId(Integer tourId,Integer memId) {
		System.out.println("tourId= " + tourId + "memId= " + memId);
		return getSession()
                .createQuery("from TourVO where TOUR_Id= :tourId AND MEM_ID = :memId",TourVO.class)
                .setParameter("tourId", tourId)
                .setParameter("memId", memId)
                .list();
	}
	
	@Override
	public List<TourView> getOneTourList(Integer tourId, Integer memId) {
//		System.out.println("tourId=" + tourId + "memId=" + memId);
//	    Session session = getSession();
	    List<TourView> list = getSession().createQuery("from TourView where tourId= :tourId AND memId = :memId", TourView.class)
	            .setParameter("tourId", tourId)
	            .setParameter("memId", memId)
	            .list(); 
//		List<TourView> list = getSession().createQuery("select distinct tv from TourView tv where tv.tourId = :tourId and tv.memId = :memId", TourView.class)
//		        .setParameter("tourId", tourId)
//		        .setParameter("memId", memId)
//		        .list();
//		List<TourView> list = getSession().createQuery("select tv from TourView tv where tv.tourId = :tourId and tv.memId = :memId group by tv.tourId, tv.memId", TourView.class)
//		        .setParameter("tourId", tourId)
//		        .setParameter("memId", memId)
//		        .list();
//		List<TourView> list = getSession().createQuery("select tv from TourView3 tv where tv.tourId = :tourId and tv.memId = :memId group by tv.tourId, tv.memId", TourView.class)
//		        .setParameter("tourId", tourId)
//		        .setParameter("memId", memId)
//		        .list();

	    
	    
	    //1110測試
//	   System.out.println(list);
	   return list;
	}

		
//		return getSession()
//				.createQuery("SELECT DISTINCT tv from TourView tv where tv.tourId= :tourId AND tv.memId = :memId" , TourView.class)
//				.setParameter("tourId", tourId)
//			    .setParameter("memId", memId)
//			    .list();
//	}
	
	

}
