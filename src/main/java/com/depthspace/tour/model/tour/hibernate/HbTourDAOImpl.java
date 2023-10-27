package com.depthspace.tour.model.tour.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.tour.model.tour.TourVO;

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

}
