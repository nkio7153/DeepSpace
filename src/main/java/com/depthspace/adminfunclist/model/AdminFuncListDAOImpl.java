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
	public void delete(AdminFuncListVO.CompositeDetail id) {
    	AdminFuncListVO acvo = getSession().get(AdminFuncListVO.class, id);
		// 0:失敗 1:成功
		int state;
		if (acvo != null) {
			getSession().delete(acvo);

			state = 1;
		} else {

			state = 0;
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
	public boolean isCollect(Integer adminId, Integer funcId) {
		List<AdminFuncListVO> results =  getSession()
                .createQuery("from AdminFuncListVO where adminId= :adminId AND funcId= :funcId", AdminFuncListVO.class)
                .setParameter("adminId", adminId)
                .setParameter("funcId", funcId)
                .list();
		return !results.isEmpty();
	}
   

}
