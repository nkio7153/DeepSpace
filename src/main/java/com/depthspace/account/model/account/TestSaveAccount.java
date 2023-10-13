package com.depthspace.account.model.account;

import org.hibernate.Session;

import com.depthspace.utils.HibernateUtil;

public class TestSaveAccount {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			AccountVO accountVO = new AccountVO();		
			accountVO.setAccountName("彥廷是帥哥");
			accountVO.setMemId(6969);

			session.save(accountVO);

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
