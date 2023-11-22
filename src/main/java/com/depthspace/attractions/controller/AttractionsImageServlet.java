package com.depthspace.attractions.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.AttractionsImagesVO;

@WebServlet("/attractionsImage/*")
public class AttractionsImageServlet extends HttpServlet {

	private SessionFactory sessionFactory;
	
	public void init() throws ServletException {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Session session = null;
		Transaction tx = null;
		try {
			Integer attractionsId = Integer.valueOf(req.getParameter("attractionsId").trim());
//			System.out.println("attractionsId= " + attractionsId);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// 使用Hibernate查詢
			Query<AttractionsImagesVO> query = session.createQuery(
					"FROM AttractionsImagesVO WHERE attractionsId = :attractionsId ", AttractionsImagesVO.class);
			query.setParameter("attractionsId", attractionsId);
			AttractionsImagesVO AttractionsImages = query.uniqueResult();

			if (AttractionsImages != null) {
				byte[] imageBytes = AttractionsImages.getAttractionsImg();
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

	

}
