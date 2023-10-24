package com.depthspace.restaurant.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.depthspace.restaurant.model.restaurant.RestDAO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restaurant.dao.RestDAOHibernateImpl;
import com.depthspace.restaurant.model.restaurant.dao.RestDAO_interface;
import com.depthspace.restaurant.model.restaurant.dao.RestHibernateDAO;
import com.depthspace.restaurant.model.restaurant.dao.RestJDBCDAOImpl;
import com.depthspace.restaurant.model.restbookingdate.dao.RestBookingDateHibernateDAOImpl;
import com.depthspace.utils.HibernateUtil;

@WebServlet("/Test_RestDB")
public class Test_RestDB extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = resp.getWriter();
//		try {
//			out.print("test");
////			RestDAO_interface dao = new RestJDBCDAOImpl();
////			RestVO restVO3 = dao.findByPrimaryKey(1);
////			System.out.println(restVO3.getRestId());
////			System.out.println(restVO3.getRestName());
////			System.out.println(restVO3.getRestTel());
////			System.out.println(restVO3.getRestAddress());
////			System.out.println(restVO3.getRestOpen());
////			System.out.println(restVO3.getRestStatus());
////			System.out.println(restVO3.getBookingLimit());
////			System.out.println(restVO3.getAdminId());
//			
//			RestDAO dao = new RestDAOHibernateImpl();
//			RestVO rest3 = dao.findByPK(1);
//			System.out.println(rest3.getRestId() + ",");
//			System.out.println(rest3.getRestName() + ",");
//			System.out.println(rest3.getRestTel() + ",");
//			System.out.println(rest3.getRestAddress() + ",");
//			System.out.println(rest3.getRestOpen() + ",");
//			System.out.println(rest3.getRestStatus() + ",");
//			System.out.println(rest3.getBookingLimit() + ",");
//			System.out.println(rest3.getAdminId());
//			
//		}catch (Exception e) {
//				e.printStackTrace();
//				out.print("DB error");
//		}
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			
//			RestVO rest = s1.get(RestVO.class, 10);
//			System.out.println(rest);
//			
//			tx1.commit();
			
			List<RestVO> list = session.createQuery("from RestVO", RestVO.class).list();
			System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		
	}
	
}
