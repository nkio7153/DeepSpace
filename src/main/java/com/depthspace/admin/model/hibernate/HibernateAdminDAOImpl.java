package com.depthspace.admin.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.member.model.MemVO;

import java.util.List;

import javax.persistence.NoResultException;

public class HibernateAdminDAOImpl implements HibernateAdminDAO_Interface {

	private SessionFactory factory; // 宣告一個 SessionFactory 用於創建 Session

    public HibernateAdminDAOImpl(SessionFactory factory) { // 帶有參數的建構子
        this.factory = factory; // 初始化 SessionFactory
    }

    public Session getSession() { // 取得當前 Session 的方法
        return factory.getCurrentSession(); // 從 SessionFactory 中取得當前的 Session
    }
    
    public HibernateAdminDAOImpl() { // 無參數的建構子
		super(); // 調用父類別的建構子
	}

    @Override
    public AdminVO insert(AdminVO entity) {
    	return (AdminVO) getSession().save(entity); // 保存實體到數據庫並返回該實體
    }

    @Override
    public int update(AdminVO entity) {
    	try {
			getSession().update(entity);
			return 1; // 更新成功時返回 1
		} catch (Exception e) {
			return -1; // 更新失敗時返回 -1
		}
    }
    
    @Override
	public int updateStatus(Integer adminId, byte status) {
        try {
            String hql = "UPDATE AdminVO SET adminStatus = :status WHERE adminId = :id"; // HQL 更新語句
            Query query = getSession().createQuery(hql); // 創建 Query 物件
            query.setParameter("status", status); // 設置參數
            query.setParameter("id", adminId);
            query.executeUpdate(); // 執行更新
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int delete(Integer adminId) {
    	AdminVO adminVO = getSession().get(AdminVO.class, adminId); // 依照 id 查找實體
		if (adminVO != null) {
			getSession().delete(adminVO); // 如果找到實體，則刪除
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
    }
    
    @Override
	public AdminVO getById(Integer adminId) {
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
			 	.setParameter("adminAcc", adminAcc) // 設置參數
			 	.getSingleResult(); // 獲取單一結果
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
		query.setParameter("adminName", "%" + adminName + "%"); // 設置參數，進行模糊匹配
		List<AdminVO> searchAdminsList = query.list();
		return searchAdminsList ;
	}

}


    