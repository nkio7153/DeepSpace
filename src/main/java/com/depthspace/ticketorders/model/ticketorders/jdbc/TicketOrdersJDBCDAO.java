package com.depthspace.ticketorders.model.ticketorders.jdbc;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketOrdersJDBCDAO implements TicketOrdersDAO_Interface {
    private static final String INSERT_STMT =
            "INSERT INTO TICKET_ORDERS(ORDER_ID, MEM_ID, ORDER_DATE, TOTAL_AMOUNT, POINTS_FEEDBACK, AMOUNT_PAID, STATUS, PAYMENT_METHOD) VALUES(?,?,?,?,?,?,?,?)";
    private static final String DELETE =
            "DELETE FROM TICKET_ORDERS WHERE ORDER_ID=?";
    private static final String UPDATE =
            "UPDATE TICKET_ORDERS SET MEM_ID=?, ORDER_DATE=?, TOTAL_AMOUNT=?, POINTS_FEEDBACK=?, AMOUNT_PAID=?, STATUS=?, PAYMENT_METHOD=? WHERE ORDER_ID=?";
    private static final String GET_ONE_STMT =
            "SELECT ORDER_ID, MEM_ID, ORDER_DATE, TOTAL_AMOUNT, POINTS_FEEDBACK, AMOUNT_PAID, STATUS, PAYMENT_METHOD FROM TICKET_ORDERS WHERE ORDER_ID=?";
    private static final String GET_ALL_STMT =
            "SELECT ORDER_ID, MEM_ID, ORDER_DATE, TOTAL_AMOUNT, POINTS_FEEDBACK, AMOUNT_PAID, STATUS, PAYMENT_METHOD FROM TICKET_ORDERS";
    @Override
    public void insert(TicketOrdersVO tod) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(INSERT_STMT);
            ps.setInt(1,tod.getOrderId());
            ps.setInt(2,tod.getMemId());
            ps.setTimestamp(3,tod.getOrderDate());
            ps.setInt(4,tod.getTotalAmount());
            ps.setInt(5, tod.getPointsFeedback());
            ps.setInt(6,tod.getAmountPaid());
            ps.setByte(7,tod.getStatus());
            ps.setByte(8,tod.getPaymentMethod());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(TicketOrdersVO tod) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1,tod.getMemId());
            ps.setTimestamp(2,tod.getOrderDate());
            ps.setInt(3,tod.getTotalAmount());
            ps.setInt(4, tod.getPointsFeedback());
            ps.setInt(5,tod.getAmountPaid());
            ps.setByte(6,tod.getStatus());
            ps.setByte(7,tod.getPaymentMethod());
            ps.setInt(8,tod.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer orderId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1,orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public TicketOrdersVO findByPrimaryKey(Integer orderId) {
        TicketOrdersVO to=new TicketOrdersVO();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1,orderId);
            rs = ps.executeQuery();
            if(rs.next()){
                to.setOrderId(rs.getInt("ORDER_ID"));
                to.setMemId(rs.getInt("MEM_ID"));
                to.setOrderDate(rs.getTimestamp("ORDER_DATE"));
                to.setTotalAmount(rs.getInt("TOTAL_AMOUNT"));
                to.setPointsFeedback(rs.getInt("POINTS_FEEDBACK"));
                to.setAmountPaid(rs.getInt("AMOUNT_PAID"));
                to.setStatus(rs.getByte("STATUS"));
                to.setPaymentMethod(rs.getByte("PAYMENT_METHOD"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return to;
    }

    @Override
    public List<TicketOrdersVO> getAll() {
        ArrayList<TicketOrdersVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while(rs.next()){
                TicketOrdersVO to=new TicketOrdersVO();
                to.setOrderId(rs.getInt("ORDER_ID"));
                to.setMemId(rs.getInt("MEM_ID"));
                to.setOrderDate(rs.getTimestamp("ORDER_DATE"));
                to.setTotalAmount(rs.getInt("TOTAL_AMOUNT"));
                to.setPointsFeedback(rs.getInt("POINTS_FEEDBACK"));
                to.setAmountPaid(rs.getInt("AMOUNT_PAID"));
                to.setStatus(rs.getByte("STATUS"));
                to.setPaymentMethod(rs.getByte("PAYMENT_METHOD"));
                list.add(to);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
