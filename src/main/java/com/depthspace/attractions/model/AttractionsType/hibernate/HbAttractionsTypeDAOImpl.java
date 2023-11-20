package com.depthspace.attractions.model.AttractionsType.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.attractions.model.AttractionsTypeVO;
import com.depthspace.attractions.model.AttractionsType.HbAttracttionsTypeDAO;
import com.depthspace.member.model.MemVO;


public class HbAttractionsTypeDAOImpl implements HbAttracttionsTypeDAO{
	
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbAttractionsTypeDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
    
    public HbAttractionsTypeDAOImpl() {
    	
    }

	@Override
	public AttractionsTypeVO insert(AttractionsTypeVO entity) {
		return (AttractionsTypeVO) getSession().save(entity);
	}

	@Override
	public int update(AttractionsTypeVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer attractionsTypeId) {
		AttractionsTypeVO attr = getSession().get(AttractionsTypeVO.class, attractionsTypeId);
		if (attr != null) {
			getSession().delete(attr);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public AttractionsTypeVO getOneById(Integer attractionsTypeId) {
		return getSession().get(AttractionsTypeVO.class, attractionsTypeId);
	}

	@Override
	public List<AttractionsTypeVO> getAll() {
		return getSession().createQuery("from AttractionsTypeVO", AttractionsTypeVO.class).list();
	}

}
