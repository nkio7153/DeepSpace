package com.depthspace.ticket.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.depthspace.ticket.dao.TicketDAOImpl;
import com.depthspace.utils.HibernateUtil;

import java.io.IOException;

@WebServlet("/ReadImage/*")
public class ReadImageServlet extends HttpServlet{

//		private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//
//		@Override
//		protected void doGet(HttpServletRequest req, HttpServletResponse res)
//				throws ServletException, IOException {
//	        String servletPath = req.getServletPath() + req.getPathInfo();
//			res.setContentType("image/jpeg");
//			ServletOutputStream outputStream = res.getOutputStream();
//			
//			try {
//				
//				int id = Integer.parseInt(req.getParameter("id"));
//				int position = Integer.parseInt(req.getParameter("position"));
////				
////				TicketImageDAOImpl ticketDaoImpl = new TicketDAOImpl(sessionFactory);
////				TicketVO ticketVO = ticket
////				AdminHibernateDAO adminHibernateDAO = new AdminHibernateDAO(sessionFactory);
////				AdminVO adminVO = adminHibernateDAO.findByPrimaryKey(id);
////				if (adminVO != null) {
//					byte[] image = adminVO.getAdminPic();
//					System.out.println("image");
//					outputStream.write(image);
//					outputStream.close();
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}
}
