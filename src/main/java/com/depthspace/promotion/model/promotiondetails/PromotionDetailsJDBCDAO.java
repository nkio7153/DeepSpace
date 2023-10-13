package com.depthspace.promotion.model.promotiondetails;


import com.depthspace.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDetailsJDBCDAO implements PromotionDetailsDAO_Interface {
    public static final String INSERT_STMT=
            "INSERT INTO PROMOTION_DETAILS(PROMOTION_ID, TICKET_ID, DISCOUNT) VALUES(?,?,?)";
    public static final String DELETE=
            "DELETE FROM PROMOTION_DETAILS WHERE PROMOTION_ID=? AND TICKET_ID=?";
    public static final String UPDATE=
            "UPDATE PROMOTION_DETAILS SET DISCOUNT=? WHERE PROMOTION_ID=? AND TICKET_ID=?";
    public static final String GET_ONE_STMT=
            "SELECT PROMOTION_ID, TICKET_ID, DISCOUNT FROM PROMOTION_DETAILS WHERE PROMOTION_ID=? AND TICKET_ID=?";
    public static final String GET_ALL_STMT=
            "SELECT PROMOTION_ID, TICKET_ID, DISCOUNT FROM PROMOTION_DETAILS ORDER BY PROMOTION_ID";

    @Override
    public void insert(PromotionDetailsVO pdo) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(INSERT_STMT);
            ps.setInt(1,pdo.getPromotionId());
            ps.setInt(2,pdo.getTicketId());
            ps.setBigDecimal(3,pdo.getDiscount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(PromotionDetailsVO pdo) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setBigDecimal(1,pdo.getDiscount());
            ps.setInt(2,pdo.getPromotionId());
            ps.setInt(3,pdo.getTicketId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }

    }

    @Override
    public void delete(Integer promotionId, Integer ticketId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, promotionId);
            ps.setInt(2, ticketId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public PromotionDetailsVO findByPrimaryKey(Integer promotionId, Integer ticketId) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        PromotionDetailsVO pdo=new PromotionDetailsVO();
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1,promotionId);
            ps.setInt(2,ticketId);
            rs = ps.executeQuery();
            if(rs.next()){
                pdo.setPromotionId(rs.getInt("PROMOTION_ID"));
                pdo.setTicketId(rs.getInt("TICKET_ID"));
                pdo.setDiscount(rs.getBigDecimal("DISCOUNT"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return pdo;
    }

    @Override
    public List<PromotionDetailsVO> getAll() {
        ArrayList<PromotionDetailsVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while(rs.next()){
                PromotionDetailsVO pdo=new PromotionDetailsVO();
                pdo.setPromotionId(rs.getInt("PROMOTION_ID"));
                pdo.setTicketId(rs.getInt("TICKET_ID"));
                pdo.setDiscount(rs.getBigDecimal("DISCOUNT"));
                list.add(pdo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
