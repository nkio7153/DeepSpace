package com.depthspace.column.model.old;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.notifications.model.NotificationsVO;
import com.depthspace.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ColumnArticlesJDBCDAO implements ColumnArticlesDAO_Interface {

    private static final String INSERT_STMT = 
        "INSERT INTO COLUMN_ARTICLES (COL_TYPE_ID, ARTI_TITLE, ARTI_CONTENT, ARTICLE_DATE, ADMIN_ID, ARTI_STATUS) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_STMT = 
        "UPDATE COLUMN_ARTICLES SET COL_TYPE_ID=?, ARTI_TITLE=?, ARTI_CONTENT=?, ARTICLE_DATE=?, ADMIN_ID=?, ARTI_STATUS=? WHERE ARTI_ID=?";
    private static final String DELETE_STMT = 
        "DELETE FROM COLUMN_ARTICLES WHERE ARTI_ID=?";
    private static final String GET_ONE_STMT = 
        "SELECT ARTI_ID, COL_TYPE_ID, ARTI_TITLE, ARTI_CONTENT, ARTICLE_DATE, ADMIN_ID, ARTI_STATUS FROM COLUMN_ARTICLES WHERE ARTI_ID=?";
    private static final String GET_ALL_STMT = 
        "SELECT ARTI_ID, COL_TYPE_ID, ARTI_TITLE, ARTI_CONTENT, ARTICLE_DATE, ADMIN_ID, ARTI_STATUS FROM COLUMN_ARTICLES";

    @Override
    public void insert(ColumnArticlesVO columnArticlesVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            
//            pstmt.setInt(1, columnArticlesVO.getColTypeId());
            pstmt.setString(2, columnArticlesVO.getAritTitle());
            pstmt.setString(3, columnArticlesVO.getArtiContent());
            pstmt.setTimestamp(4, (Timestamp) columnArticlesVO.getArticleDate());
            pstmt.setInt(5, columnArticlesVO.getAdminId());
            pstmt.setByte(6, columnArticlesVO.getArtiStatus());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting: " + e.getMessage());
        }
    }

    @Override
    public void update(ColumnArticlesVO columnArticlesVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(UPDATE_STMT);

//            pstmt.setInt(1, columnArticlesVO.getColTypeId());
            pstmt.setString(2, columnArticlesVO.getAritTitle());
            pstmt.setString(3, columnArticlesVO.getArtiContent());
            pstmt.setTimestamp(4, (Timestamp) columnArticlesVO.getArticleDate());
            pstmt.setInt(5, columnArticlesVO.getAdminId());
            pstmt.setByte(6, columnArticlesVO.getArtiStatus());
            pstmt.setInt(7, columnArticlesVO.getAritId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while updating: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer aritId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(DELETE_STMT);


            pstmt.setInt(1, aritId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting: " + e.getMessage());
        }
    }

    @Override
    public ColumnArticlesVO findByPrimaryKey(Integer aritId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ColumnArticlesVO columnArticlesVO = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, aritId);
            rs = pstmt.executeQuery();

            
                while (rs.next()) {
                    columnArticlesVO = new ColumnArticlesVO();
                    columnArticlesVO.setAritId(rs.getInt("ARTI_ID"));
//                    columnArticlesVO.setColTypeId(rs.getInt("COL_TYPE_ID"));
                    columnArticlesVO.setAritTitle(rs.getString("ARTI_TITLE"));
                    columnArticlesVO.setArtiContent(rs.getString("ARTI_CONTENT"));
                    columnArticlesVO.setArticleDate(rs.getTimestamp("ARTICLE_DATE"));
                    columnArticlesVO.setAdminId(rs.getInt("ADMIN_ID"));
                    columnArticlesVO.setArtiStatus(rs.getByte("ARTI_STATUS"));
                }

        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching: " + e.getMessage());
        }
        return columnArticlesVO;
    }

    @Override
    public List<ColumnArticlesVO> getAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ColumnArticlesVO> list = new ArrayList<>();

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ColumnArticlesVO columnArticlesVO = new ColumnArticlesVO();
                columnArticlesVO.setAritId(rs.getInt("ARTI_ID"));
//                columnArticlesVO.setColTypeId(rs.getInt("COL_TYPE_ID"));
                columnArticlesVO.setAritTitle(rs.getString("ARTI_TITLE"));
                columnArticlesVO.setArtiContent(rs.getString("ARTI_CONTENT"));
                columnArticlesVO.setArticleDate(rs.getTimestamp("ARTICLE_DATE"));
                columnArticlesVO.setAdminId(rs.getInt("ADMIN_ID"));
                columnArticlesVO.setArtiStatus(rs.getByte("ARTI_STATUS"));
                list.add(columnArticlesVO);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching all: " + e.getMessage());
        }
        return list;
    }

}
