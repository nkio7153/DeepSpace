package com.depthspace.ticket.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/ticketimage")
public class TicketMainImageServlet extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String ticketId =req.getParameter("ticketId").trim();//加上trim()
			ResultSet rs = stmt.executeQuery(
				"SELECT IMAGE FROM TICKET_IMAGES WHERE TICKET_ID="+ticketId+" AND IS_MAIN_IMAGE = 1"); 

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("IMAGE"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);//404碼
				InputStream in = getServletContext().getResourceAsStream("/images/none3.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
			byte[] b = in.readAllBytes();  // Java 9 的新方法(要留意2G內才建議)
//			byte[] b = new byte[in.available()];//(要留意2G內才建議) 
//			in.read(b);
			out.write(b);
			in.close();
		}
	
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CHA103G3"); //資料庫連線
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}