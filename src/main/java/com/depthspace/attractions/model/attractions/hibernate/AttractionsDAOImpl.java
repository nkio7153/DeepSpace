package com.depthspace.attractions.model.attractions.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.column.model.ColumnArticlesVO;
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
	public AttractionsVO getAttractionsById(Integer attractionsId) {
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
	@Override
	public List<AttractionsVO> findOtherAttractions(String cityName) {
		Query <AttractionsVO> query = getSession().createQuery("FROM AttractionsVO WHERE ADDRESS LIKE :cityName ", AttractionsVO.class);
		query.setParameter("cityName", "%" + cityName + "%");
		List<AttractionsVO> addressList = query.list();
		return addressList ;
	}
	@Override
	public List<AttractionsVO> getAttractionsName(String attractionsName) {
		Query <AttractionsVO> query = getSession().createQuery("FROM AttractionsVO WHERE attractionsName LIKE :attractionsName ", AttractionsVO.class);
		query.setParameter("attractionsName", "%" + attractionsName + "%");
		List<AttractionsVO> attractionsList = query.list();
		return attractionsList ;
	}
	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from AttractionsVO", Long.class).uniqueResult();
	}
	
	public List<AttractionsVO>  getByCompositeQuery(Map<String, List<String>> map) {
	    
	    if (map.size() == 0) {
	        return getAll();
	    }

	    CriteriaBuilder builder = getSession().getCriteriaBuilder();
	    CriteriaQuery<AttractionsVO> criteria = builder.createQuery(AttractionsVO.class);
	    Root<AttractionsVO> root = criteria.from(AttractionsVO.class);

	    List<Predicate> predicates = new ArrayList<>();

	    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	        String key = entry.getKey();
	        List<String> values = entry.getValue();
 
	        switch (key) {
	            case "attractionsName":
	                predicates.add(builder.like(root.get("attractionsName"), "%" + values.get(0) + "%"));
	                break;
	            case "attrTypeId":
	                // 使用 builder.in 
	                CriteriaBuilder.In<Integer> attractionsTypeId = builder.in(root.get("attractionsTypeId").get("attractionsTypeId"));
	                for (String value : values) {
	                	attractionsTypeId.value(Integer.parseInt(value));
	                }
	                predicates.add(attractionsTypeId);
	                break;
	            case "attractionsId":
	                predicates.add(builder.equal(root.get("attractionsId"), Integer.parseInt(values.get(0))));
	                break;
	        }
	    }

	    criteria.where(builder.and(predicates.toArray(new Predicate[0])));
	    criteria.orderBy(builder.asc(root.get("attractionsId")));
	    TypedQuery<AttractionsVO> query = getSession().createQuery(criteria);

	    return query.getResultList();
	}

}
