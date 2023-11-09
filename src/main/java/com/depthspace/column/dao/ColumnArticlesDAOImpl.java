package com.depthspace.column.dao;

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
import org.hibernate.Transaction;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.ticket.model.TicketVO;

public class ColumnArticlesDAOImpl implements ColumnArticlesDAO {
	public static final int PAGE_MAX_RESULT = 10;
	private SessionFactory factory;

	public ColumnArticlesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ColumnArticlesVO columnArticlesVO) {
//		getSession().save(columnArticlesVO);

		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(columnArticlesVO);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}

	@Override
	public void update(ColumnArticlesVO columnArticlesVO) {
		getSession().update(columnArticlesVO);
	}

	@Override
	public void delete(Integer id) {
		ColumnArticlesVO columnArticles = getSession().get(ColumnArticlesVO.class, id);
		getSession().delete(columnArticles);
	}

	@Override
	public ColumnArticlesVO getById(Integer artiId) {
		return getSession().get(ColumnArticlesVO.class, artiId);
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ColumnArticlesVO", Long.class).uniqueResult();

	}

	@Override
	public List<ColumnArticlesVO> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT; // 計算當前頁面第一條索引
		return getSession().createQuery("from ColumnArticlesVO", ColumnArticlesVO.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT) // 每頁紀錄數量
				.list(); // 查出的資料存於此列表
	}

	@Override
	public List<ColumnArticlesVO> getAll() {
		return getSession().createQuery("from ColumnArticlesVO", ColumnArticlesVO.class).list();
	}
	
	// 萬用查詢
	@Override
	public List<ColumnArticlesVO> getByCompositeQuery(Map<String, List<String>> map) {
	    
	    if (map.size() == 0) {
	        return getAll();
	    }

	    CriteriaBuilder builder = getSession().getCriteriaBuilder();
	    CriteriaQuery<ColumnArticlesVO> criteria = builder.createQuery(ColumnArticlesVO.class);
	    Root<ColumnArticlesVO> root = criteria.from(ColumnArticlesVO.class);

	    List<Predicate> predicates = new ArrayList<>();

	    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	        String key = entry.getKey();
	        List<String> values = entry.getValue();
 
	        switch (key) {
	            case "artiTitle":
	                predicates.add(builder.like(root.get("artiTitle"), "%" + values.get(0) + "%"));
	                break;
	            case "colTypeId":
	                // 使用 builder.in 
	                CriteriaBuilder.In<Integer> inColTypeId = builder.in(root.get("colType").get("colTypeId"));
	                for (String value : values) {
	                	inColTypeId.value(Integer.parseInt(value));
	                }
	                predicates.add(inColTypeId);
	                break;
	            case "artiId":
	                predicates.add(builder.equal(root.get("artiId"), Integer.parseInt(values.get(0))));
	                break;
	        }
	    }

	    criteria.where(builder.and(predicates.toArray(new Predicate[0])));
	    criteria.orderBy(builder.asc(root.get("artiId")));
	    TypedQuery<ColumnArticlesVO> query = getSession().createQuery(criteria);

	    return query.getResultList();
	}
	

}