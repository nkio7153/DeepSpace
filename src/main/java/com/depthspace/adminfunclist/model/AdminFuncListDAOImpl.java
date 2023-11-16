package com.depthspace.adminfunclist.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.utils.HibernateUtil;

public class AdminFuncListDAOImpl implements AdminFuncListDAO{
	private SessionFactory factory;

	public AdminFuncListDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
    
    private Session getSession() {
		return factory.getCurrentSession();
	}

    @Override
    public void insert(AdminFuncListVO entity) {
    	getSession().save(entity);
    }
    
    @Override
    public void update(AdminFuncListVO entity) {
        getSession().update(entity);
    }

    @Override
	public void delete(AdminFuncListVO.CompositeDetail id) {
    	AdminFuncListVO acvo = getSession().get(AdminFuncListVO.class, id);
        if (acvo != null) {
            getSession().delete(acvo);
        } else {
            throw new RuntimeException("刪除失敗：找不到該項目");
        }
    }

    @Override
	public List<AdminFuncListVO> getByAdminId(Integer adminId) {
		return getSession()
                .createQuery("from AdminFuncListVO where adminId= :adminId", AdminFuncListVO.class)
                .setParameter("adminId", adminId)
                .list();
	}

    @Override
    public boolean hasPermission(Integer adminId, Integer funcId) {
        Query<Long> query = getSession().createQuery(
                "select count(*) from AdminFuncListVO where adminId = :adminId and funcId = :funcId", Long.class);
        query.setParameter("adminId", adminId);
        query.setParameter("funcId", funcId);
        return query.uniqueResult() > 0;
    }

}
