package com.depthspace.attractions.model.area.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public class AreaDAOImpl implements AreaDAO_Interface{
	private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public AreaDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
	@Override
	public void insert(AreaVO AreaVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AreaVO AreaVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer areaId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AreaVO findByPrimaryKey(Integer areaId) {
		return getSession().get(AreaVO.class, areaId);
	}

	@Override
	public List<AreaVO> getAll() {
		return getSession().createQuery("from AreaVO",AreaVO.class).list();
	}
	@Override
	public List<AreaVO> getAllArea(Integer cityId) {
		return getSession().createQuery("from AreaVO",AreaVO.class).list();
	}
	@Override
	public List<AreaVO> getArea(Integer cityId) {
		// TODO Auto-generated method stub
		return null;
	}

}
