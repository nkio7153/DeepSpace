package com.depthspace.promotion.model.promotion;


import com.depthspace.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionJDBCDAO implements PromotionDAO_Interface {
    public static final String INSERT_STMT=
            "INSERT INTO PROMOTION(PROMOTION_ID, PROMO_NAME, START_DATE, END_DATE, DESCRIPTION, PICTURE) VALUES(?,?,?,?,?,?)";
    public static final String DELETE=
            "DELETE FROM PROMOTION WHERE PROMOTION_ID=?";
    public static final String UPDATE=
            "UPDATE PROMOTION SET PROMO_NAME=?, START_DATE=?, END_DATE=?, DESCRIPTION=?, PICTURE=? WHERE PROMOTION_ID=?";
    public static final String GET_ONE_STMT=
            "SELECT PROMOTION_ID, PROMO_NAME, START_DATE, END_DATE, DESCRIPTION, PICTURE FROM PROMOTION WHERE PROMOTION_ID=?";
    public static final String GET_ALL_STMT=
            "SELECT PROMOTION_ID, PROMO_NAME, START_DATE, END_DATE, DESCRIPTION, PICTURE FROM PROMOTION ORDER BY PROMOTION_ID";

    @Override
    public void insert(PromotionVO pro) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(INSERT_STMT);
            ps.setInt(1,pro.getPromotionId());
            ps.setString(2,pro.getPromoName());
            ps.setTimestamp(3,pro.getStartDate());
            ps.setTimestamp(4,pro.getEndDate());
            ps.setString(5,pro.getDescription());
            ps.setBytes(6,pro.getPicture());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(PromotionVO pro) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1,pro.getPromoName());
            ps.setTimestamp(2,pro.getStartDate());
            ps.setTimestamp(3,pro.getEndDate());
            ps.setString(4,pro.getDescription());
            ps.setBytes(5,pro.getPicture());
            ps.setInt(6,pro.getPromotionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer promotionId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1,promotionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public PromotionVO findByPrimaryKey(Integer promotionId) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        PromotionVO pro=new PromotionVO();
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1,promotionId);
            rs = ps.executeQuery();
            if(rs.next()){
                pro.setPromotionId(rs.getInt("PROMOTION_ID"));
                pro.setPromoName(rs.getString("PROMO_NAME"));
                pro.setStartDate(rs.getTimestamp("START_DATE"));
                pro.setEndDate(rs.getTimestamp("END_DATE"));
                pro.setDescription(rs.getString("DESCRIPTION"));
                pro.setPicture(rs.getBytes("PICTURE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return pro;
    }

    @Override
    public List<PromotionVO> getAll() {
        ArrayList<PromotionVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while(rs.next()){
                PromotionVO pro=new PromotionVO();
                pro.setPromotionId(rs.getInt("PROMOTION_ID"));
                pro.setPromoName(rs.getString("PROMO_NAME"));
                pro.setStartDate(rs.getTimestamp("START_DATE"));
                pro.setEndDate(rs.getTimestamp("END_DATE"));
                pro.setDescription(rs.getString("DESCRIPTION"));
                pro.setPicture(rs.getBytes("PICTURE"));
                list.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
