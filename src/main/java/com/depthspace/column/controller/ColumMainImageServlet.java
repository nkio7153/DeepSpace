package com.depthspace.column.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/columnmainimage")
public class ColumMainImageServlet extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String artiId = req.getParameter("artiId").trim();
		try {
			String sql;
			PreparedStatement pstmt;
			sql = "SELECT COL_IMG FROM COLUMN_IMAGES WHERE ARTI_ID=? AND IS_MAIN_IMAGE=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, artiId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				InputStream in = rs.getBinaryStream("COL_IMG");
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


//public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//    res.setContentType("image/jpeg");
//
//    String artiId = req.getParameter("artiId").trim();
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//
//    try {
//        String sql = "SELECT COL_IMG FROM COLUMN_IMAGES WHERE ARTI_ID=? AND IS_MAIN_IMAGE=1";
//        pstmt = con.prepareStatement(sql);
//        pstmt.setString(1, artiId);
//
//        rs = pstmt.executeQuery();
//
//        if (rs.next()) {
//            try (InputStream in = rs.getBinaryStream("COL_IMG");
//                 OutputStream out = res.getOutputStream()) {
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = in.read(buffer)) != -1) {
//                    out.write(buffer, 0, bytesRead);
//                }
//            }
//        } else {
//            serveDefaultImage(res);
//        }
//    } catch (SQLException e) {
//        serveDefaultImage(res);
//    } finally {
//        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
//        if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
//    }
//}
//
//private void serveDefaultImage(HttpServletResponse res) throws IOException {
//    try (InputStream defaultImageStream = getServletContext().getResourceAsStream("/images/none3.jpg");
//         OutputStream out = res.getOutputStream()) {
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//        while ((bytesRead = defaultImageStream.read(buffer)) != -1) {
//            out.write(buffer, 0, bytesRead);
//        }
//    }
//}