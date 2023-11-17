package com.depthspace.admin.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.member.model.MemVO;

import java.util.List;

import javax.persistence.NoResultException;

public class HibernateAdminDAOImpl implements HibernateAdminDAO_Interface {

	private SessionFactory factory;

    public HibernateAdminDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }
    
    public HibernateAdminDAOImpl() {
		super();
	}

    @Override
    public AdminVO insert(AdminVO entity) {
    	return (AdminVO) getSession().save(entity);
    }

    @Override
    public int update(AdminVO entity) {
    	try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
    }
   
//    @Override
//    public int update2(AdminVO entity) {
//    	AdminVO originalData  = findByAdminAcc(entity.getAdminAcc());
//    	try {
//    		//修改之前 先查出該管理員的原始資料
//			//並把HD 和 Status放回 不接受前端參數修改
//			//---------------------------------------
//    		entity.setAdminStatus(originalData.getAdminStatus());
//    		entity.setAdminVerifyStatus(originalData.getAdminVerifyStatus());
//    		entity.setAdminFuncName(originalData.getAdminFuncName());
//			getSession().merge(entity);
//			//---------------------------------------
//			return 1;
//		} catch (Exception e) {
//			return -1;
//		}
//    }
    
    @Override
	public int updateStatus(Integer adminId, byte status) {
        try {
            String hql = "UPDATE AdminVO SET adminStatus = :status WHERE adminId = :id";
            Query query = getSession().createQuery(hql);
            query.setParameter("status", status);
            query.setParameter("id", adminId);
            query.executeUpdate();
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
	public AdminVO getById(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminVO> getAll() {
		return getSession().createQuery("from AdminVO", AdminVO.class).list();
	}

	@Override
	public List<AdminVO> getAll(int currentPage) {
		return null;
	}
	
	@Override
	public AdminVO findByAdminAcc(String adminAcc) {
		 try {
			 return (AdminVO) getSession()
				.createQuery("from AdminVO admin where admin.adminAcc = :adminAcc", AdminVO.class)
			 	.setParameter("adminAcc", adminAcc)
			 	.getSingleResult();
		 } catch (NoResultException e) {
	            return null;
	        }
		            
		            
	}
	@Override
	public AdminVO getById(String adminAcc) {
		return getSession().get(AdminVO.class, adminAcc);
	}
	@Override
	public AdminVO findOneAdmin(String adminAcc) {
		return getSession().get(AdminVO.class, adminAcc);
	}

	@Override
	public AdminVO getOneAdmin(Integer adminId) {
		return getSession().get(AdminVO.class, adminId);
	}
	
	@Override
	public List<AdminVO> searchAdmins(String adminName) {
		Query <AdminVO> query = getSession().createQuery("FROM AdminVO WHERE adminName LIKE :adminName ", AdminVO.class);
		query.setParameter("adminName", "%" + adminName + "%");
		List<AdminVO> searchAdminsList = query.list();
		return searchAdminsList ;
	}

}


    