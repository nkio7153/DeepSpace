package com.depthspace.attractions.model.city.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public class CityDAOImpl implements CityDAO_Interface{
	 private SessionFactory factory;
	    public CityDAOImpl(SessionFactory factory){
	        this.factory=factory;
	    }
	    private Session getSession(){
	        return factory.getCurrentSession();
	    }

	@Override
	public void insert(CityVO CityVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CityVO CityVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer cityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CityVO findByPrimaryKey(Integer cityId) {
		return getSession().get(CityVO.class, cityId);
	}

	@Override
	public List<CityVO> getAll() {
		return getSession().createQuery("from CityVO",CityVO.class).list();
	}

}
