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