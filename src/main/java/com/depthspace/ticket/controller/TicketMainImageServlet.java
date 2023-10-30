package com.depthspace.ticket.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/ticketmainimage")
public class TicketMainImageServlet extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String ticketId = req.getParameter("ticketId").trim();
		try {
			String sql;
			PreparedStatement pstmt;
			sql = "SELECT IMAGE FROM TICKET_IMAGES WHERE TICKET_ID=? AND IS_MAIN_IMAGE=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ticketId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				InputStream in = rs.getBinaryStream("IMAGE");
				if (in != null) {
					int length = in.available();
					byte[] imageBytes = new byte[length];
					in.read(imageBytes, 0, length);

					res.setContentType("image/jpeg");
					ServletOutputStream out = res.getOutputStream();
					out.write(imageBytes);
					out.close();
				}
			} else {
				// 如果没有找到
				InputStream defaultImageStream = getServletContext().getResourceAsStream("/images/none3.jpg");
				int defaultImageLength = defaultImageStream.available();
				byte[] defaultImageBytes = new byte[defaultImageLength];
				defaultImageStream.read(defaultImageBytes, 0, defaultImageLength);

				res.setContentType("image/jpeg");
				ServletOutputStream out = res.getOutputStream();
				out.write(defaultImageBytes);
				out.close();
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
			byte[] b = in.readAllBytes();
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CHA103G3");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}