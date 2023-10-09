package com.depthspace.ticketshoppingcart.model;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketShoppingCartJDBCDAO implements TicketShoppingCartDAO_Interface {
    private static final String INSERT_STMT=
            "INSERT INTO TICKET_SHOPPING_CART(MEM_ID, TICKET_ID, QUANTITY, ADDED_DATE) VALUES(?,?,?,?)";
    private static final String DELETE_ONE=
            "DELETE FROM TICKET_SHOPPING_CART WHERE MEM_ID=? AND TICKET_ID=?";
    private static final String DELETE_ALL=
            "DELETE FROM TICKET_SHOPPING_CART WHERE MEM_ID=?";
    private static final String UPDATE=
            "UPDATE TICKET_SHOPPING_CART SET QUANTITY=?, ADDED_DATE=? WHERE MEM_ID=? AND TICKET_ID=?";
    private static final String GET_ONE_STMT=
            "SELECT MEM_ID, TICKET_ID, QUANTITY, ADDED_DATE FROM TICKET_SHOPPING_CART WHERE MEM_ID=? AND TICKET_ID=?";
    private static final String GET_ALL_STMT_BY_MEMID=
            "SELECT MEM_ID, TICKET_ID, QUANTITY, ADDED_DATE FROM TICKET_SHOPPING_CART WHERE MEM_ID=?";
    private static final String GET_ALL_STMT=
            "SELECT MEM_ID, TICKET_ID, QUANTITY, ADDED_DATE FROM TICKET_SHOPPING_CART ORDER BY MEM_ID";

    @Override
    public void insert(TicketShoppingCartVO tsc) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(INSERT_STMT);
            ps.setInt(1,tsc.getMemId());
            ps.setInt(2,tsc.getTicketId());
            ps.setInt(3,tsc.getQuantity());
            ps.setTimestamp(4,tsc.getAddedDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(TicketShoppingCartVO tsc) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1,tsc.getQuantity());
            ps.setTimestamp(2,tsc.getAddedDate());
            ps.setInt(3, tsc.getMemId());
            ps.setInt(4, tsc.getTicketId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer memId, Integer ticketId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE_ONE);
            ps.setInt(1,memId);
            ps.setInt(2,ticketId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }

    }

    @Override
    public void delete(Integer memId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE_ALL);
            ps.setInt(1,memId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public TicketShoppingCartVO findByPrimaryKey(Integer memId, Integer ticketId) {
        TicketShoppingCartVO tsc = new TicketShoppingCartVO();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1,memId);
            ps.setInt(2,ticketId);
            rs = ps.executeQuery();
            if(rs.next()){
                tsc.setMemId(rs.getInt("MEM_ID"));
                tsc.setTicketId(rs.getInt("TICKET_ID"));
                tsc.setQuantity(rs.getInt("QUANTITY"));
                tsc.setAddedDate(rs.getTimestamp("ADDED_DATE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return tsc;
    }

    @Override
    public List<TicketShoppingCartVO> findByMemId(Integer memId) {
        ArrayList<TicketShoppingCartVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT_BY_MEMID);
            ps.setInt(1,memId);
            rs = ps.executeQuery();
            while(rs.next()){
                TicketShoppingCartVO tsc = new TicketShoppingCartVO();
                tsc.setMemId(rs.getInt("MEM_ID"));
                tsc.setTicketId(rs.getInt("TICKET_ID"));
                tsc.setQuantity(rs.getInt("QUANTITY"));
                tsc.setAddedDate(rs.getTimestamp("ADDED_DATE"));
                list.add(tsc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<TicketShoppingCartVO> getAll() {
        ArrayList<TicketShoppingCartVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while(rs.next()){
                TicketShoppingCartVO tsc = new TicketShoppingCartVO();
                tsc.setMemId(rs.getInt("MEM_ID"));
                tsc.setTicketId(rs.getInt("TICKET_ID"));
                tsc.setQuantity(rs.getInt("QUANTITY"));
                tsc.setAddedDate(rs.getTimestamp("ADDED_DATE"));
                list.add(tsc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
