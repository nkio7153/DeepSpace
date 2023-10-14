package com.depthspace.admin.model;

import org.hibernate.Session;
import com.depthspace.utils.HibernateUtil;

public class TestAdmin {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
            // 新增一個AdminVO物件
//            AdminVO adminVO = new AdminVO();        
//            adminVO.setAdminName("閔萱超派");
//            adminVO.setAdminAcc("管理員5");
//            adminVO.setAdminPwd("123");
//            adminVO.setAdminStatus(1);
//
//            session.save(adminVO);

			
			// 要刪除的 AdminVO 物件的 ID
//			int adminIdToDelete = 9; // 假設要刪除的 AdminVO 的 ID 是 1
//
//			// 使用session.get()取得指定主鍵值的AdminVO物件
//			AdminVO adminVO = session.get(AdminVO.class, adminIdToDelete);
//
//			// 如果找到了對應的AdminVO物件，就刪除它
//			if (adminVO != null) {
//				session.delete(adminVO);
//				System.out.println("AdminVO 物件已刪除，ADMIN_ID 為：" + adminIdToDelete);
//			} else {
//				System.out.println("找不到指定的 AdminVO 物件。");
//			}
			
			//更新(還沒好)
//			AdminVO adminVO = new AdminVO();
//			adminVO.getAdminId();
//			adminVO.setAdminName("管理員9487");
//			adminVO.setAdminAcc("admin9487");
//			adminVO.setAdminPwd("password9487");
//			adminVO.setAdminStatus(0);
//			session.update(adminVO);
			
			
			
			
			
			
			
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}