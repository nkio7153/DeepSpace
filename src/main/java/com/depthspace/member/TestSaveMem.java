package com.depthspace.member;

import java.sql.Date;

import org.hibernate.Session;

import com.depthspace.utils.HibernateUtil;

public class TestSaveMem {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			MemVO memVO = new MemVO();
			memVO.setMemAcc("user006");
			memVO.setMemPwd("password6");
			memVO.setMemName("余八");
			memVO.setMemIdentity("F123456780");
			memVO.setMemBth(Date.valueOf("1997-05-21"));
			memVO.setMemSex((byte) 1);
			memVO.setMemEmail("user006@email.com");
			memVO.setMemTel(956789013);
			memVO.setMemAdd("台中市西屯區");
			memVO.setAccStatus((byte) 2);
			memVO.setMemPoint(700);

			session.save(memVO);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
