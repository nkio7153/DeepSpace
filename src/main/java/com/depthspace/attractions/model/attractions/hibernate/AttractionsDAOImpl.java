package com.depthspace.attractions.model.attractions.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public class AttractionsDAOImpl implements AttractionsDAO_Interface{
	private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public AttractionsDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    public AttractionsDAOImpl() {
	}
	//取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
	@Override
	public void insert(AttractionsVO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(AttractionsVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
		
	}

	@Override
	public void delete(Integer attractionsId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttractionsVO findByPrimaryKey(Integer attractionsId) {
		return getSession().get(AttractionsVO.class, attractionsId);
	}

	@Override
	public List<AttractionsVO> getAll() {
		return getSession().createQuery("FROM AttractionsVO",AttractionsVO.class).list();
	}
	@Override
	public List<AttractionsVO> getAllAttractions(Integer attractionsId) {
		Query<AttractionsVO> query = getSession().createQuery("FROM AttractionsVO WHERE attractionsId = :attractionsId", AttractionsVO.class);
		query.setParameter("attractionsId", attractionsId); // 將cityId綁定到命名參數
		List<AttractionsVO> list = query.list();
		return list;
	}
	@Override
	public List<AttractionsVO> findOneAttractions() {
		Query <AttractionsVO> query = getSession().createQuery("FROM AttractionsVO WHERE ADDRESS LIKE '%台北市%'", AttractionsVO.class);
		List<AttractionsVO> addressList = query.list();
		return addressList ;
	}
	
	

}
