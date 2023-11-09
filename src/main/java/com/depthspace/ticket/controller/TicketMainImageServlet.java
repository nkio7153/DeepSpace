package com.depthspace.ticket.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.depthspace.ticket.model.TicketImagesVO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/ticketmainimage")
public class TicketMainImageServlet extends HttpServlet {

	private SessionFactory sessionFactory;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Session session = null;
		Transaction tx = null;
		try {
			Integer ticketId = Integer.valueOf(req.getParameter("ticketId").trim());

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// 使用Hibernate查詢
			Query<TicketImagesVO> query = session.createQuery(
					"FROM TicketImagesVO WHERE ticket.ticketId = :ticketId AND isMainImage = 1", TicketImagesVO.class);
			query.setParameter("ticketId", ticketId);
			TicketImagesVO ticketImage = query.uniqueResult();

			if (ticketImage != null) {
				byte[] imageBytes = ticketImage.getImage();
				res.setContentType("image/jpeg");
				ServletOutputStream out = res.getOutputStream();
				out.write(imageBytes);
				out.close();
			} else {
				sendDefaultImage(res);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			sendDefaultImage(res);
		} finally {
			if (session != null)
				session.close();
		}
	}

	private void sendDefaultImage(HttpServletResponse res) throws IOException {
		InputStream defaultImageStream = getServletContext().getResourceAsStream("/images/none3.jpg");
		byte[] defaultImageBytes = defaultImageStream.readAllBytes();
		defaultImageStream.close();

		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		out.write(defaultImageBytes);
		out.close();
	}

	public void init() throws ServletException {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}

//@WebServlet("/ticketmainimage")
//public class TicketMainImageServlet extends HttpServlet {
//
//	DataSource ds;
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Connection con = null;
//		String ticketId = req.getParameter("ticketId").trim();
//		try {
//			con = ds.getConnection();
//			String sql;
//			PreparedStatement pstmt;
//			sql = "SELECT IMAGE FROM TICKET_IMAGES WHERE TICKET_ID=? AND IS_MAIN_IMAGE=1";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, ticketId);
//
//			ResultSet rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				InputStream in = rs.getBinaryStream("IMAGE");
//				if (in != null) {
//					int length = in.available();
//					byte[] imageBytes = new byte[length];
//					in.read(imageBytes, 0, length);
//
//					res.setContentType("image/jpeg");
//					ServletOutputStream out = res.getOutputStream();
//					out.write(imageBytes);
//					out.close();
//				}
//			} else {
//				// 如果没有找到
//				InputStream defaultImageStream = getServletContext().getResourceAsStream("/images/none3.jpg");
//				int defaultImageLength = defaultImageStream.available();
//				byte[] defaultImageBytes = new byte[defaultImageLength];
//				defaultImageStream.read(defaultImageBytes, 0, defaultImageLength);
//
//				res.setContentType("image/jpeg");
//				ServletOutputStream out = res.getOutputStream();
//				out.write(defaultImageBytes);
//				out.close();
//			}
//
//			rs.close();
//			pstmt.close();
//		} catch (Exception e) {
//			InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
//			byte[] b = in.readAllBytes();
//			in.close();
//		} finally {
//			try {
//				if (con != null)
//					con.close();
//			} catch (SQLException e) {
//				System.out.println(e);
//
//			}
//
//		}
//
//	}
//
//	public void init() throws ServletException {
//		try {
//			Context ctx = new javax.naming.InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CHA103G3");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//}