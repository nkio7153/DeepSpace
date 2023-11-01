package com.depthspace.column.model.old;

import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColumnTypesJDBCDAO implements ColumnTypesDAO_Interface {

    private static final String INSERT_STMT = "INSERT INTO COLUMN_TYPES (COL_TYPE_NAME) VALUES(?)";
    private static final String UPDATE_STMT = "UPDATE COLUMN_TYPES SET COL_TYPE_NAME=? WHERE COL_TYPE_ID=?";
    private static final String DELETE_STMT = "DELETE FROM COLUMN_TYPES WHERE COL_TYPE_ID=?";
    private static final String GET_ONE_STMT = "SELECT COL_TYPE_ID, COL_TYPE_NAME FROM COLUMN_TYPES WHERE COL_TYPE_ID=?";
    private static final String GET_ALL_STMT = "SELECT COL_TYPE_ID, COL_TYPE_NAME FROM COLUMN_TYPES";

    @Override
    public void insert(ColumnTypesVO columnTypesVO) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT)) {

            pstmt.setString(1, columnTypesVO.getColTypeName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting: " + e.getMessage());
        }
    }

    @Override
    public void update(ColumnTypesVO columnTypesVO) {
        try (
        	Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_STMT)) {

            pstmt.setString(1, columnTypesVO.getColTypeName());
            pstmt.setInt(2, columnTypesVO.getColTypeId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while updating: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer colTypeId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(DELETE_STMT)) {

            pstmt.setInt(1, colTypeId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting: " + e.getMessage());
        }
    }

    @Override
    public ColumnTypesVO findByPrimaryKey(Integer colTypeId) {
        ColumnTypesVO columnTypesVO = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT)) {

            pstmt.setInt(1, colTypeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    columnTypesVO = new ColumnTypesVO();
                    columnTypesVO.setColTypeId(rs.getInt("COL_TYPE_ID"));
                    columnTypesVO.setColTypeName(rs.getString("COL_TYPE_NAME"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching: " + e.getMessage());
        }
        return columnTypesVO;
    }

    @Override
    public List<ColumnTypesVO> getAll() {
        List<ColumnTypesVO> list = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ColumnTypesVO columnTypesVO = new ColumnTypesVO();
                columnTypesVO.setColTypeId(rs.getInt("COL_TYPE_ID"));
                columnTypesVO.setColTypeName(rs.getString("COL_TYPE_NAME"));
                list.add(columnTypesVO);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching all: " + e.getMessage());
        }
        return list;
    }

//    public static void main(String[] args) {
//        ColumnTypesJDBCDAO dao = new ColumnTypesJDBCDAO();
//
//        // Test Insert
////        ColumnTypesVO columnTypesVO1 = new ColumnTypesVO();
////        columnTypesVO1.setColTypeName("地方名產");
////        dao.insert(columnTypesVO1);
////        System.out.println("Insert complete!");
//
//        // Test Update
////        ColumnTypesVO columnTypesVO2 = dao.findByPrimaryKey(3);
////        columnTypesVO2.setColTypeName("地方小吃");
////        dao.update(columnTypesVO2);
////        System.out.println("Update complete!");
////
//        // Test Find By Primary Key
////        ColumnTypesVO columnTypesVO3 = dao.findByPrimaryKey(1);
////        System.out.println(columnTypesVO3.getColTypeName());
////
//        // Test Get All
////        List<ColumnTypesVO> list = dao.getAll();
////        for (ColumnTypesVO vo : list) {
////            System.out.println(vo.getColTypeId() + ", " + vo.getColTypeName());
////        }
//
//        // Test Delete
////        dao.delete(5);
////        System.out.println("Delete complete!");
//    }
}
