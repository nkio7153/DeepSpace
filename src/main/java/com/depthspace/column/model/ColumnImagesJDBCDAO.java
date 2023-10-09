package com.depthspace.column.model;

import com.depthspace.utils.DBUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColumnImagesJDBCDAO implements ColumnImagesDAO_Interface {

    private static final String INSERT_STMT = 
        "INSERT INTO COLUMN_IMAGES (ARTI_ID, COL_IMG) VALUES(?, ?)";
    private static final String UPDATE_STMT = 
        "UPDATE COLUMN_IMAGES SET ARTI_ID=?, COL_IMG=? WHERE COL_IMG_ID=?";
    private static final String DELETE_STMT = 
        "DELETE FROM COLUMN_IMAGES WHERE COL_IMG_ID=?";
    private static final String GET_ONE_STMT = 
        "SELECT COL_IMG_ID, ARTI_ID, COL_IMG FROM COLUMN_IMAGES WHERE COL_IMG_ID=?";
    private static final String GET_ALL_STMT = 
        "SELECT COL_IMG_ID, ARTI_ID, COL_IMG FROM COLUMN_IMAGES";

    @Override
    public void insert(ColumnImagesVO columnImagesVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            
            pstmt.setInt(1, columnImagesVO.getArtiId());
            pstmt.setBytes(2, columnImagesVO.getColImg());
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public void update(ColumnImagesVO columnImagesVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(UPDATE_STMT);
            
            pstmt.setInt(1, columnImagesVO.getArtiId());
            pstmt.setBytes(2, columnImagesVO.getColImg());
            pstmt.setInt(3, columnImagesVO.getColImgId());
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public void delete(Integer colImgId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(DELETE_STMT);
            
            pstmt.setInt(1, colImgId);
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public ColumnImagesVO findByPrimaryKey(Integer colImgId) {
        ColumnImagesVO columnImagesVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            
            pstmt.setInt(1, colImgId);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                columnImagesVO = new ColumnImagesVO();
                columnImagesVO.setColImgId(rs.getInt("COL_IMG_ID"));
                columnImagesVO.setArtiId(rs.getInt("ARTI_ID"));
                columnImagesVO.setColImg(rs.getBytes("COL_IMG"));
            }
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, rs);
        }
        return columnImagesVO;
    }

    @Override
    public List<ColumnImagesVO> getAll() {
        List<ColumnImagesVO> list = new ArrayList<>();
        ColumnImagesVO columnImagesVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                columnImagesVO = new ColumnImagesVO();
                columnImagesVO.setColImgId(rs.getInt("COL_IMG_ID"));
                columnImagesVO.setArtiId(rs.getInt("ARTI_ID"));
                columnImagesVO.setColImg(rs.getBytes("COL_IMG"));
                list.add(columnImagesVO);
            }
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, rs);
        }
        return list;
    }
    


//    public static void main(String[] args) {
//        ColumnImagesJDBCDAO dao = new ColumnImagesJDBCDAO();
//
////         新增
//        byte[] pic = null;
//        try {
//            pic = getPictureByteArray("src/main/resources/images/tomcat.gif");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ColumnImagesVO columnImagesVO1 = new ColumnImagesVO();
//        columnImagesVO1.setArtiId(1);
//        columnImagesVO1.setColImg(pic);
//        dao.insert(columnImagesVO1);
//        System.out.println("完成新增");
//
//        // 修改
//        byte[] updatedPic = null;
//        try {
//            updatedPic = getPictureByteArray("src/main/resources/images/tomcat.gif");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ColumnImagesVO columnImagesVO2 = new ColumnImagesVO();
//        columnImagesVO2.setColImgId(1);
//        columnImagesVO2.setArtiId(1);
//        columnImagesVO2.setColImg(updatedPic);
//        dao.update(columnImagesVO2);
//        System.out.println("完成修改");
//
//        // 查詢一個
//        ColumnImagesVO columnImagesVO3 = dao.findByPrimaryKey(1);
//        System.out.print(columnImagesVO3.getColImgId() + ",");
//        outputImage(columnImagesVO3.getColImg(), "C:/output/1.png");
//        System.out.println("完成查詢");
//
//        // 查詢全部
//        List<ColumnImagesVO> list = dao.getAll();
//        for (ColumnImagesVO all : list) {
//            System.out.print(all.getColImgId() + ",");
//            outputImage(all.getColImg(), "C:/output/" + all.getColImgId() + ".png");
//            System.out.println("--------------");
//        }
//
//        // 刪除
//        dao.delete(6);
//        System.out.println("完成刪除");
//    }
//
////    新增圖片
//    public static byte[] getPictureByteArray(String path) throws IOException {
//        FileInputStream fis = new FileInputStream(path);
//        byte[] buffer = fis.readAllBytes();
//        fis.close();
//        return buffer;
//    }
//
//    //查找圖片
//    private static void outputImage(byte[] imageData, String outputPath) {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(outputPath);
//            fos.write(imageData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
    


