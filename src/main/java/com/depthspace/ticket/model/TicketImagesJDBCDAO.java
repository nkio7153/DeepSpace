package com.depthspace.ticket.model;

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

public class TicketImagesJDBCDAO implements TicketImagesDAO_Interface {

    private static final String INSERT_STMT = 
        "INSERT INTO TICKET_IMAGES (TICKET_ID, IMAGE,IS_MAIN_IMAGE) VALUES(?, ?, ?)";
    private static final String UPDATE_STMT = 
        "UPDATE TICKET_IMAGES SET TICKET_ID=?, IMAGE=?, IS_MAIN_IMAGE=? WHERE SERIAL_ID=?";
    private static final String DELETE_STMT = 
        "DELETE FROM TICKET_IMAGES WHERE SERIAL_ID=?";
    private static final String GET_ONE_STMT = 
        "SELECT SERIAL_ID, TICKET_ID, IMAGE FROM TICKET_IMAGES WHERE SERIAL_ID=?";
    private static final String GET_ALL_STMT = 
        "SELECT SERIAL_ID, TICKET_ID, IMAGE FROM TICKET_IMAGES";

    @Override
    public void insert(TicketImagesVO ticketImagesVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            
            pstmt.setInt(1, ticketImagesVO.getTicketId());
            pstmt.setBytes(2, ticketImagesVO.getImage());
//            pstmt.setByte(3, ticketImagesVO.isMainImage());
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public void update(TicketImagesVO ticketImagesVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(UPDATE_STMT);
            
            pstmt.setInt(1, ticketImagesVO.getTicketId());
            pstmt.setBytes(2, ticketImagesVO.getImage());
//            pstmt.setByte(3, ticketImagesVO.isMainImage());
            pstmt.setInt(4, ticketImagesVO.getSerialId());
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public void delete(Integer serialId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(DELETE_STMT);
            
            pstmt.setInt(1, serialId);
            pstmt.executeUpdate();
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public TicketImagesVO findByPrimaryKey(Integer serialId) {
        TicketImagesVO ticketImagesVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            
            pstmt.setInt(1, serialId);
            rs = pstmt.executeQuery();

            while(rs.next()) {
//                ticketImagesVO = new TicketImagesVO();
                ticketImagesVO.setSerialId(rs.getInt("SERIAL_ID"));
                ticketImagesVO.setTicketId(rs.getInt("TICKET_ID"));
                ticketImagesVO.setImage(rs.getBytes("IMAGE"));
            }
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, rs);
        }
        return ticketImagesVO;
    }

    @Override
    public List<TicketImagesVO> getAll() {
        List<TicketImagesVO> list = new ArrayList<>();
        TicketImagesVO ticketImagesVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while(rs.next()) {
//                ticketImagesVO = new TicketImagesVO();
                ticketImagesVO.setSerialId(rs.getInt("SERIAL_ID"));
                ticketImagesVO.setTicketId(rs.getInt("TICKET_ID"));
                ticketImagesVO.setImage(rs.getBytes("IMAGE"));
                list.add(ticketImagesVO);
            }
            
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, rs);
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        TicketImagesJDBCDAO dao = new TicketImagesJDBCDAO();
//
//        // 新增
//     byte[] pic = null;
//     try {
//		pic = getPictureByteArray("src/main/resources/images/tomcat.gif");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        TicketImagesVO ticketImagesVO1 = new TicketImagesVO();
//        ticketImagesVO1.setTicketId(324001);
//        ticketImagesVO1.setImage(pic);
//        dao.insert(ticketImagesVO1);
//        System.out.println("完成新增");
//
////        // 修改
//        byte[] updatedPic = null;
//        try {
//            updatedPic = getPictureByteArray("C:/Users/Tibame_T14/Downloads/新增資料夾/16479310383226352680.jpg"); 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        TicketImagesVO ticketImagesVO2 = new TicketImagesVO();
//        ticketImagesVO2.setSerialId(2);  
////        ticketImagesVO2.setTicketId(324002);
//        ticketImagesVO2.setImage(updatedPic);  
//        dao.update(ticketImagesVO2);
//        System.out.println("完成修改");
//    }
////
////        // 查詢一個
//////        TicketImagesVO ticketImagesVO3 = dao.findByPrimaryKey(7);
//////        System.out.print(ticketImagesVO3.getSerialId() + ",");
////////        System.out.println(ticketImagesVO3.getImage());  
//////        outputImage(ticketImagesVO3.getImage(), "C:/output/1.png");         
//////        System.out.println("完成查詢");
////
////        // 查詢全部
//////        List<TicketImagesVO> list = dao.getAll();
//////        for (TicketImagesVO all : list) {
//////            System.out.print(all.getSerialId() + ",");
//////            outputImage(all.getImage(), "C:/output/"+all.getSerialId()+".png"); 
//////            System.out.println("--------------");
//////        }
////
////        // 刪除
//////        dao.delete(5);
//////        System.out.println("完成刪除");
////    }
////    
////
////
////	//新增圖片
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = fis.readAllBytes();
//		
//		fis.close();
//		return buffer;
//
//	}
////	//查找圖片
//	private static void outputImage(byte[] imageData, String outputPath) {
//	    FileOutputStream fos = null;
//	    try {
//	        fos = new FileOutputStream(outputPath);
//	        fos.write(imageData);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } finally {
//	        if (fos != null) {
//	            try {
//	                fos.close();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//	}
//

   
}
