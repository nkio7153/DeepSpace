package com.depthspace.ticketorders.model.ticketorderdetail;

import com.depthspace.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketOrderDetailJDBCDAO implements TicketOrderDetailDAO_Interface {
    private static final String INSERT_STMT =
            "INSERT INTO TICKET_ORDER_DETAIL(ORDER_ID, TICKET_ID ,QUANTITY, UNIT_PRICE, DISCOUNT_PRICE, SUBTOTAL, TICKET_REVIEWS, STARS) VALUES(?,?,?,?,?,?,?,?)";
    private static final String DELETE =
            "DELETE FROM TICKET_ORDER_DETAIL WHERE ORDER_ID=? AND TICKET_ID=?";
    private static final String UPDATE =
            "UPDATE TICKET_ORDER_DETAIL SET QUANTITY=?, UNIT_PRICE=?, DISCOUNT_PRICE=?, SUBTOTAL=?, TICKET_REVIEWS=?, STARS=? WHERE ORDER_ID=? AND TICKET_ID=?";
    private static final String GET_ONE_STMT =
            "SELECT ORDER_ID, TICKET_ID, QUANTITY, UNIT_PRICE, DISCOUNT_PRICE, SUBTOTAL, TICKET_REVIEWS, STARS FROM TICKET_ORDER_DETAIL WHERE ORDER_ID=? AND TICKET_ID=?";
    private static final String GET_ALL_STMT =
            "SELECT ORDER_ID, TICKET_ID, QUANTITY, UNIT_PRICE, DISCOUNT_PRICE, SUBTOTAL, TICKET_REVIEWS, STARS FROM TICKET_ORDER_DETAIL ORDER BY ORDER_ID";
    @Override
    public void insert(TicketOrderDetailVO tod) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(INSERT_STMT);
            ps.setInt(1, tod.getOrderId());
            ps.setInt(2, tod.getTicketId());
            ps.setInt(3, tod.getQuantity());
            ps.setInt(4, tod.getUnitPrice());
            ps.setInt(5, tod.getDiscountPrice());
            ps.setInt(6, tod.getSubtotal());
            ps.setString(7, tod.getTicketReviews());
            ps.setByte(8, tod.getStars());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(TicketOrderDetailVO tod) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, tod.getQuantity());
            ps.setInt(2, tod.getUnitPrice());
            ps.setInt(3, tod.getDiscountPrice());
            ps.setInt(4, tod.getSubtotal());
            ps.setString(5, tod.getTicketReviews());
            ps.setByte(6, tod.getStars());
            ps.setInt(7, tod.getOrderId());
            ps.setInt(8, tod.getTicketId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer orderId, Integer ticketId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, orderId);
            ps.setInt(2, ticketId);
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public TicketOrderDetailVO findByPrimaryKey(Integer orderId, Integer ticketId) {
        TicketOrderDetailVO tod=new TicketOrderDetailVO();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1, orderId);
            ps.setInt(2, ticketId);
            rs = ps.executeQuery();
            if (rs.next()){
                tod.setOrderId(rs.getInt("ORDER_ID"));
                tod.setTicketId(rs.getInt("TICKET_ID"));
                tod.setQuantity(rs.getInt("QUANTITY"));
                tod.setUnitPrice(rs.getInt("UNIT_PRICE"));
                tod.setDiscountPrice(rs.getInt("DISCOUNT_PRICE"));
                tod.setSubtotal(rs.getInt("SUBTOTAL"));
                tod.setTicketReviews(rs.getString("TICKET_REVIEWS"));
                tod.setStars(rs.getByte("STARS"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return tod;
    }

    @Override
    public List<TicketOrderDetailVO> getAll() {
        ArrayList<TicketOrderDetailVO> tods = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while (rs.next()){
                TicketOrderDetailVO tod = new TicketOrderDetailVO();
                tod.setOrderId(rs.getInt("ORDER_ID"));
                tod.setTicketId(rs.getInt("TICKET_ID"));
                tod.setQuantity(rs.getInt("QUANTITY"));
                tod.setUnitPrice(rs.getInt("UNIT_PRICE"));
                tod.setDiscountPrice(rs.getInt("DISCOUNT_PRICE"));
                tod.setSubtotal(rs.getInt("SUBTOTAL"));
                tod.setTicketReviews(rs.getString("TICKET_REVIEWS"));
                tod.setStars(rs.getByte("STARS"));
                tods.add(tod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return tods;
    }
}
