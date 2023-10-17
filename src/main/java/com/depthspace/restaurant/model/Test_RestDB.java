package com.depthspace.restaurant.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.restaurant.RestDAO;
import com.depthspace.restaurant.model.restaurant.RestDAOHibernateImpl;
import com.depthspace.restaurant.model.restaurant.RestVO;

@WebServlet("/Test_RestDB")
public class Test_RestDB extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			out.print("test");
			RestDAO dao = new RestDAOHibernateImpl();
			RestVO rest3 = dao.findByPK(1);
			System.out.println(rest3.getRestId() + ",");
			System.out.println(rest3.getRestName() + ",");
			System.out.println(rest3.getRestTel() + ",");
			System.out.println(rest3.getRestAddress() + ",");
			System.out.println(rest3.getRestOpen() + ",");
			System.out.println(rest3.getRestStatus() + ",");
			System.out.println(rest3.getBookingLimit() + ",");
			System.out.println(rest3.getAdminId());
			
		}catch (Exception e) {
				e.printStackTrace();
				out.print("DB error");
		}
		
	}
	
}
