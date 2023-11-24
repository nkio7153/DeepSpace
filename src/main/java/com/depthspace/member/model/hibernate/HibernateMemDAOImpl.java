package com.depthspace.member.model.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.member.model.MemVO;
public class HibernateMemDAOImpl implements HibernateMemDAO_Interface {
	//宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HibernateMemDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
	public HibernateMemDAOImpl() {
		super();
	}

	@Override
	public void insert(MemVO entity) {
		getSession().save(entity);
	}

	@Override
	public int update(MemVO entity) {
		try {
			getSession().update(entity);
			System.out.println("更新成功");
			return 1;
		} catch (Exception e) {
			System.out.println("更新失敗");
			return -1;
		}
	}
	@Override
	public int updateStatus(Integer memId, byte status) {
        try {
            String hql = "UPDATE MemVO SET accStatus = :status WHERE memId = :id";
            Query query = getSession().createQuery(hql);
            query.setParameter("status", status);
            query.setParameter("id", memId);
            query.executeUpdate();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

	@Override
	public int delete(Integer memId) {
		MemVO memVO = getSession().get(MemVO.class, memId);
		if (memVO != null) {
			getSession().delete(memVO);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public MemVO getById(Integer memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemVO> getAll() {
		return getSession().createQuery("from MemVO", MemVO.class).list();
	}

	@Override
	public List<MemVO> getAll(int currentPage) {
		return null;
	}
	//uniqueResult如果沒有就會回傳null
	@Override
	public MemVO findByMemAcc(String memAcc) {
//		 String queryString = "from MemVO where memAcc = :memAcc";
		 try {
			 return (MemVO) getSession()
				.createQuery("from MemVO mem where mem.memAcc = :memAcc", MemVO.class)
			 	.setParameter("memAcc", memAcc)
			 	.getSingleResult();
		 } catch (NoResultException e) {
	            return null;
	        }
		            
		            
	}
	@Override
	public MemVO getById(String memAcc) {
		return getSession().get(MemVO.class, memAcc);
	}
	@Override
	public MemVO findOneMem(String memAcc) {
		return getSession().get(MemVO.class, memAcc);
	}
	
	@Override
	public MemVO getOneMem(Integer memId) {
		return getSession().get(MemVO.class, memId);
	}
	
	@Override
	public List<MemVO> searchMembers(String memName) {
		Query <MemVO> query = getSession().createQuery("FROM MemVO WHERE memName LIKE :memName ", MemVO.class);
		query.setParameter("memName", "%" + memName + "%");
		List<MemVO> searchMembersList = query.list();
		return searchMembersList ;
	}
	@Override
	public int updatePassword(String memAcc,String memPwd) {
		// TODO Auto-generated method stub
		return 0;
	}
	// 萬用查詢
		@Override
		public List<MemVO> getByCompositeQuery(Map<String, List<String>> map) {
		    
		    if (map.size() == 0) {
		        return getAll();
		    }

		    CriteriaBuilder builder = getSession().getCriteriaBuilder();
		    CriteriaQuery<MemVO> criteria = builder.createQuery(MemVO.class);
		    Root<MemVO> root = criteria.from(MemVO.class);

		    List<Predicate> predicates = new ArrayList<>();

		    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
		        String key = entry.getKey();
		        List<String> values = entry.getValue();
	 
//		        switch (key) {
//		            case "artiTitle":
//		                predicates.add(builder.like(root.get("artiTitle"), "%" + values.get(0) + "%"));
//		                break;
//		            case "colTypeId":
//		                CriteriaBuilder.In<Integer> inColTypeId = builder.in(root.get("colType").get("colTypeId"));
//		                for (String value : values) {
//		                	inColTypeId.value(Integer.parseInt(value));
//		                }
//		                predicates.add(inColTypeId);
//		                break;
//		            case "artiId":
//		                predicates.add(builder.equal(root.get("artiId"), Integer.parseInt(values.get(0))));
//		                break;
//		        }
		    }

		    criteria.where(builder.and(predicates.toArray(new Predicate[0])));
		    criteria.orderBy(builder.asc(root.get("artiId")));
		    TypedQuery<MemVO> query = getSession().createQuery(criteria);

		    return query.getResultList();
		}

}
