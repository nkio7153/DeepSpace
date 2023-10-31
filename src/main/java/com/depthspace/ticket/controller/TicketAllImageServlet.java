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
        String ticketId = req.getParameter("ticketId").trim();
        String action = req.getParameter("action");

        JSONArray serialIds = new JSONArray();
        
        try {
            String sql = "SELECT SERIAL_ID, IMAGE FROM TICKET_IMAGES WHERE TICKET_ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ticketId);

            ResultSet rs = pstmt.executeQuery();

            if("getIds".equals(action)) {
                while (rs.next()) {
                    serialIds.put(rs.getString("SERIAL_ID"));
                }

                res.setContentType("application/json");
                PrintWriter out = res.getWriter();
                out.print(serialIds);
                out.flush();
            } else {
                if(rs.next()) {
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
                    InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
                    byte[] b = in.readAllBytes();
                    in.close();
                    res.getOutputStream().write(b);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            InputStream in = getServletContext().getResourceAsStream("/images/null.jpg");
            byte[] b = in.readAllBytes();
            in.close();
            res.getOutputStream().write(b);
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