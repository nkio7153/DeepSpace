package com.depthspace.admin.model.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import static com.depthspace.utils.Constants1.PAGE_MAX_RESULT;

import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.Constants;

import java.util.List;
import java.util.Map;


public class AdminDAOImpl implements AdminDAO {

	private SessionFactory factory;

    public AdminDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public int insert(AdminVO adminVO) {
    	return (Integer) getSession().save(adminVO);
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
    public int delete(Integer id) {
    	AdminVO adminVO = getSession().get(AdminVO.class, id);
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
    public AdminVO getById(Integer id) {
    	return getSession().get(AdminVO.class, id);
    }

    @Override
    public List<AdminVO> getAll() {
    	return getSession().createQuery("from Emp", AdminVO.class).list();
    }

    

    @Override
    public List<AdminVO> getAll(int currentPage) {
    	int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from AdminVO", AdminVO.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
    }

    @Override
    public long getTotal() {
    	return getSession().createQuery("select count(*) from AdminVO", Long.class).uniqueResult();
    }
    
}