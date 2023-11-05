package com.depthspace.ticket.controller;

import org.json.JSONArray;

import java.io.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/ticketallimage")
public class TicketAllImageServlet extends HttpServlet {

    Connection con;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String ticketIdParam = req.getParameter("ticketId");
        if (ticketIdParam == null || ticketIdParam.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket ID is required.");
            return;
        }
        String ticketId = ticketIdParam.trim();
        String action = req.getParameter("action");

        try {
            if ("getIds".equals(action)) {
                JSONArray serialIds = new JSONArray();
                String sql = "SELECT SERIAL_ID FROM TICKET_IMAGES WHERE TICKET_ID=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, ticketId);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    serialIds.put(rs.getString("SERIAL_ID"));
                }

                res.setContentType("application/json");
                PrintWriter out = res.getWriter();
                out.print(serialIds);
                out.flush();
                out.close();
            } else if ("getImage".equals(action)) {
                if (ticketId.isEmpty()) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket ID is required for getImage.");
                    return;
                }

                String imageId = req.getParameter("imageId"); 
                if (imageId == null || imageId.isEmpty()) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image ID is required.");
                    return;
                }

                String sql = "SELECT IMAGE FROM TICKET_IMAGES WHERE SERIAL_ID=? AND TICKET_ID=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, imageId);
                pstmt.setString(2, ticketId);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    InputStream in = rs.getBinaryStream("IMAGE");
                    res.setContentType("image/jpeg");
                    ServletOutputStream out = res.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    in.close();
                    out.flush();
                    out.close();
                } else {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                // Handle other actions or error
                res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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





//package com.depthspace.ticket.controller;
//
//import org.json.JSONArray;
//
//import java.io.*;
//import java.sql.*;
//import javax.naming.Context;
//import javax.naming.NamingException;
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import javax.sql.DataSource;
//
//@WebServlet("/ticketallimage")
//public class TicketAllImageServlet extends HttpServlet {
//
//	Connection con;
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        String ticketId = req.getParameter("ticketId").trim();
//        String action = req.getParameter("action");
//
//        JSONArray serialIds = new JSONArray();
//        
//        try {
//            String sql = "SELECT IMAGE FROM TICKET_IMAGES WHERE TICKET_ID=?";
//            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, ticketId);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if("getIds".equals(action)) {
//                while (rs.next()) {
//                    serialIds.put(rs.getString("SERIAL_ID"));
//                }
//
//                res.setContentType("application/json");
//                PrintWriter out = res.getWriter();
//                out.print(serialIds);
//                out.flush();
//            } else {
//                if(rs.next()) {
//                    InputStream in = rs.getBinaryStream("IMAGE");
//                    if (in != null) {
//                        int length = in.available();
//                        byte[] imageBytes = new byte[length];
//                        in.read(imageBytes, 0, length);
//
//                        res.setContentType("image/jpeg");
//                        ServletOutputStream out = res.getOutputStream();
//                        out.write(imageBytes);
//                        out.close();
//                    }
//                } else {
//                    InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
//                    byte[] b = in.readAllBytes();
//                    in.close();
//                    res.getOutputStream().write(b);
//                }
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
//            byte[] b = in.readAllBytes();
//            in.close();
//            res.getOutputStream().write(b);
//        }
//    }
//
//	public void init() throws ServletException {
//		try {
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CHA103G3");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void destroy() {
//		try {
//			if (con != null)
//				con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
//}