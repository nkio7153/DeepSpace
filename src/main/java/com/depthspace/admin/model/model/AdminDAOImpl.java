package com.depthspace.admin.model.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import static com.depthspace.utils.Constants1.PAGE_MAX_RESULT;

import java.util.List;
import com.depthspace.admin.model.model.AdminDAOImpl;


public class AdminDAOImpl implements AdminDAO {

	private SessionFactory factory;

    public AdminDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void insert(AdminVO adminVO) {
		getSession().save(adminVO);
    }

    @Override
    public int update(AdminVO adminVO) {
    	try {
			getSession().update(adminVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
    }

    @Override
    public int delete(Integer adminId) {
    	AdminVO adminVO = getSession().get(AdminVO.class, adminId);
		if (adminVO != null) {
			getSession().delete(adminVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
    }
    
    @Override
	public AdminVO findByAdminId(Integer adminId) {
		Query<AdminVO> query = getSession().createQuery("FROM AdminVO where adminId=:adminId",AdminVO.class);
		query.setParameter("adminId", adminId);
		return query.getSingleResult();
	}
    
    @Override
	public List<AdminVO> getAll() {
		return getSession().createQuery("FROM AdminVO", AdminVO.class).list();
	}
    
}