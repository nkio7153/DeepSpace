package com.depthspace.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.depthspace.utils.DBUtil;

public class TicketJDBCDAO implements TicketDAO_Interface {
	
	private static final String INSERT_STMT =
			"INSERT INTO TICKET (TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS, AREA_ID, ADDRESS, LONGITUDE, LATITUDE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT TICKET_ID, TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS, AREA_ID, ADDRESS, LONGITUDE, LATITUDE FROM TICKET ORDER BY TICKET_ID";
	private static final String GET_ONE_STMT =
			"SELECT TICKET_ID, TICKET_TYPE_ID, TICKET_NAME, DESCRIPTION, PRICE, STOCK, VALID_DAYS, STATUS, PUBLISHED_DATE, TOTAL_STAR_RATINGS, TOTAL_STARS, AREA_ID, ADDRESS, LONGITUDE, LATITUDE FROM TICKET WHERE TICKET_ID = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM TICKET WHERE TICKET_ID = ?";		
	private static final String UPDATE_STMT = 
			"UPDATE TICKET SET TICKET_TYPE_ID=?, TICKET_NAME=?, DESCRIPTION=?, PRICE=?, STOCK=?, VALID_DAYS=?, STATUS=?, PUBLISHED_DATE=?, TOTAL_STAR_RATINGS=?, TOTAL_STARS=?, AREA_ID=?, ADDRESS=?, LONGITUDE=?, LATITUDE=? WHERE TICKET_ID=?";
	//後台列表內容
//	private static final String GET_TICKETS_STMT =  
//	        "WITH RankedImages AS (" +
//	        "    SELECT" +
//	        "        ti.TICKET_ID," +
//	        "        ti.IMAGE," +
//	        "        ROW_NUMBER() OVER(PARTITION BY ti.TICKET_ID ORDER BY ti.SERIAL_ID) AS rn" +
//	        "    FROM TICKET_IMAGES ti" +
//	        "    WHERE ti.IS_MAIN_IMAGE = 1" + // 確保只有主圖被選擇
//	        ") " +
//	        "SELECT t.TICKET_ID, tt.TYPE_NAME, t.TICKET_NAME, t.DESCRIPTION, t.PRICE, t.STOCK, t.STATUS, t.PUBLISHED_DATE, t.ADDRESS, rti.IMAGE " +
//	        "FROM TICKET t " +
//	        "JOIN RankedImages rti ON t.TICKET_ID = rti.TICKET_ID AND rti.rn = 1 " + // 加入 rn 條件是為了確保每個票據ID只對應一個圖像
//	        "JOIN TICKET_TYPES tt ON t.TICKET_TYPE_ID = tt.TICKET_TYPE_ID " +
//	        "GROUP BY t.TICKET_ID, tt.TYPE_NAME, t.TICKET_NAME, t.DESCRIPTION, t.PRICE, t.STOCK, t.STATUS, t.PUBLISHED_DATE, t.ADDRESS, rti.IMAGE " +
//	        "ORDER BY t.TICKET_ID";
	private static final String GET_TICKETS_STMT = 
	        "WITH RankedImages AS (" +
	        "    SELECT" +
	        "        ti.TICKET_ID," +
	        "        ti.IMAGE," +
	        "        ROW_NUMBER() OVER(PARTITION BY ti.TICKET_ID ORDER BY ti.SERIAL_ID) AS rn" +
	        "    FROM TICKET_IMAGES ti" +
	        "    WHERE ti.IS_MAIN_IMAGE = 1" + // 選擇主圖
	        ") " +
	        "SELECT t.TICKET_ID, t.TICKET_NAME, rti.IMAGE " +
	        "FROM TICKET t " +
	        "JOIN RankedImages rti ON t.TICKET_ID = rti.TICKET_ID AND rti.rn = 1 " + // 加入 rn 條件是為了確保每個票據ID只對應一個圖像
	        "ORDER BY t.TICKET_ID"; 

	




	@Override
	public void insert(TicketVO ticketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, ticketVO.getTicketTypeId());
			pstmt.setString(2, ticketVO.getTicketName());
			pstmt.setString(3, ticketVO.getDescription());
			pstmt.setInt(4, ticketVO.getPrice());
			pstmt.setInt(5, ticketVO.getStock());
			pstmt.setInt(6, ticketVO.getValidDays());
			pstmt.setByte(7, ticketVO.getStatus());
			pstmt.setTimestamp(8, ticketVO.getPublishedDate());
//			pstmt.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setInt(9, ticketVO.getTotalStarRatings());
			pstmt.setInt(10, ticketVO.getTotalStars());
			pstmt.setInt(11, ticketVO.getAreaId());
			pstmt.setString(12, ticketVO.getAddress());
			pstmt.setDouble(13, ticketVO.getLongitude());
			pstmt.setDouble(14, ticketVO.getLatitude());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}	

	@Override
	public void update(TicketVO ticketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, ticketVO.getTicketTypeId());
			pstmt.setString(2, ticketVO.getTicketName());
			pstmt.setString(3, ticketVO.getDescription());
			pstmt.setInt(4, ticketVO.getPrice());
			pstmt.setInt(5, ticketVO.getStock());
			pstmt.setInt(6, ticketVO.getValidDays());
			pstmt.setByte(7, ticketVO.getStatus());
			pstmt.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setInt(9, ticketVO.getTotalStarRatings());
			pstmt.setInt(10, ticketVO.getTotalStars());
			pstmt.setInt(11, ticketVO.getAreaId());
			pstmt.setString(12, ticketVO.getAddress());
			pstmt.setDouble(13, ticketVO.getLongitude());
			pstmt.setDouble(14, ticketVO.getLatitude());
			pstmt.setInt(15, ticketVO.getTicketId()); // 
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
	}			
	
	@Override
	public void delete(Integer ticketId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ticketId);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}		
	}

	@Override
	public TicketVO findByPrimaryKey(Integer ticketId) {
		
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //ResultSet為查詢結果
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ticketId);
			rs = pstmt.executeQuery();
			while(rs.next()) { //移動查詢
				ticketVO = new TicketVO();
				ticketVO.setTicketId(rs.getInt("TICKET_ID")); 
				ticketVO.setTicketTypeId(rs.getInt("TICKET_TYPE_ID")); 				
				ticketVO.setTicketName(rs.getString("TICKET_NAME")); 
				ticketVO.setDescription(rs.getString("DESCRIPTION")); 
				ticketVO.setPrice(rs.getInt("PRICE")); 
				ticketVO.setStock(rs.getInt("STOCK")); 
				ticketVO.setValidDays(rs.getInt("VALID_DAYS")); 	
				ticketVO.setStatus(rs.getByte("STATUS")); 
				ticketVO.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE")); 
				ticketVO.setTotalStarRatings(rs.getInt("TOTAL_STAR_RATINGS")); 
				ticketVO.setTotalStars(rs.getInt("TOTAL_STARS")); 
				ticketVO.setAreaId(rs.getInt("AREA_ID"));
				ticketVO.setAddress(rs.getString("ADDRESS"));
				ticketVO.setLongitude(rs.getDouble("LONGITUDE"));
				ticketVO.setLatitude(rs.getDouble("LATITUDE"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
		return ticketVO;
	}
	
	@Override
	public List<TicketVO> getAll(Map<String, String[]> map) {
		List<TicketVO> list = new ArrayList<>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //ResultSet為查詢結果
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) { //移動查詢
				ticketVO = new TicketVO();
				ticketVO.setTicketId(rs.getInt("TICKET_ID")); 
				ticketVO.setTicketTypeId(rs.getInt("TICKET_TYPE_ID")); 				
				ticketVO.setTicketName(rs.getString("TICKET_NAME")); 
				ticketVO.setDescription(rs.getString("DESCRIPTION")); 
				ticketVO.setPrice(rs.getInt("PRICE")); 
				ticketVO.setStock(rs.getInt("STOCK")); 
				ticketVO.setValidDays(rs.getInt("VALID_DAYS")); 	
				ticketVO.setStatus(rs.getByte("STATUS")); 
				ticketVO.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE")); 
				ticketVO.setTotalStarRatings(rs.getInt("TOTAL_STAR_RATINGS")); 
				ticketVO.setTotalStars(rs.getInt("TOTAL_STARS")); 
				ticketVO.setAreaId(rs.getInt("AREA_ID"));
				ticketVO.setAddress(rs.getString("ADDRESS"));
				ticketVO.setLongitude(rs.getDouble("LONGITUDE"));
				ticketVO.setLatitude(rs.getDouble("LATITUDE"));
				list.add(ticketVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
		return list;
	}

	@Override
	public List<TicketInfo> getTicketsInfo(Map<String, String[]> map) {
        List<TicketInfo> list = new ArrayList<TicketInfo>();
        TicketInfo TicketInfo = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_TICKETS_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TicketInfo ticketInfo = new TicketInfo(); 
                ticketInfo.setTicketId(rs.getInt("TICKET_ID"));
//                ticketInfo.setTypeName(rs.getString("TYPE_NAME")); 
                ticketInfo.setTicketName(rs.getString("TICKET_NAME"));
//                ticketInfo.setDescription(rs.getString("DESCRIPTION"));
//                ticketInfo.setPrice(rs.getInt("PRICE"));
//                ticketInfo.setStock(rs.getInt("STOCK"));
//                ticketInfo.setValidDays(rs.getInt("VALID_DAYS"));
//                ticketInfo.setStatus(rs.getByte("STATUS"));
//                ticketInfo.setPublishedDate(rs.getTimestamp("PUBLISHED_DATE"));
//                ticketInfo.setTotalStarRatings(rs.getInt("TOTAL_STAR_RATINGS"));
//                ticketInfo.setTotalStars(rs.getInt("TOTAL_STARS"));
//                ticketInfo.setAreaId(rs.getInt("AREA_ID"));
//                ticketInfo.setAddress(rs.getString("ADDRESS"));
//                ticketInfo.setLongitude(rs.getDouble("LONGITUDE"));
//                ticketInfo.setLatitude(rs.getDouble("LATITUDE"));
                ticketInfo.setImage(rs.getBytes("IMAGE"));
//                ticketInfo.setSalesVolume(rs.getInt("SALES_VOLUME")); 

                list.add(ticketInfo);
            }
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());			
		} finally {
            DBUtil.close(con, pstmt, null);
		}
		return list;
	}

	
	
//	public static void main(String[] args) {
//		TicketJDBCDAO dao = new TicketJDBCDAO();
//
//		//新增
////		TicketVO ticketVO1 = new TicketVO();
////		ticketVO1.setTicketTypeId(1);
////		ticketVO1.setTicketName("Xpark 都會型水生公園門票");
////		ticketVO1.setDescription("Xpark 為台灣首座新都會型水生公園。將生活在地球上各種地域的生物們的環境，透過空間演出與科技的融合，加以忠實的重現。");
////		ticketVO1.setPrice(600);
////		ticketVO1.setStock(500);
////		ticketVO1.setValidDays(365);
////		ticketVO1.setStatus((byte)1);
////		ticketVO1.setPublishedDate(new java.sql.Timestamp(System.currentTimeMillis()));
////		ticketVO1.setTotalStarRatings(20);
////		ticketVO1.setTotalStars(100);
////		dao.insert(ticketVO1);
////		System.out.println("完成新增");
//	
//		//修改
////		TicketVO ticketVO2 = new TicketVO();
////		ticketVO2.setTicketId(324001);
////		ticketVO2.setTicketTypeId(1);
////		ticketVO2.setTicketName("劍湖山");
////		ticketVO2.setDescription("台灣遊樂園！");
////		ticketVO2.setPrice(799);
////		ticketVO2.setStock(800);
////		ticketVO2.setValidDays(365);
////		ticketVO2.setStatus((byte)1);
////		ticketVO2.setPublishedDate(new java.sql.Timestamp(System.currentTimeMillis()));
////		ticketVO2.setTotalStarRatings(10);
////		ticketVO2.setTotalStars(450);
////		dao.update(ticketVO2);
////		System.out.println("完成修改");
//		
//		// 刪除
////		dao.delete(1);
////		System.out.println("完成刪除");
//		
//		// 查詢一個
////		TicketVO ticketVO3 = dao.findByPrimaryKey(324001);
////		System.out.print(ticketVO3.getTicketId() + ",");
////		System.out.print(ticketVO3.getTicketTypeId() + ",");
////		System.out.print(ticketVO3.getTicketName() + ",");
////		System.out.print(ticketVO3.getDescription() + ",");
////		System.out.print(ticketVO3.getPrice() + ",");
////		System.out.print(ticketVO3.getStock() + ",");
////		System.out.print(ticketVO3.getValidDays() + ",");
////		System.out.print(ticketVO3.getStatus() + ",");
////		System.out.print(ticketVO3.getPublishedDate() + ",");
////		System.out.print(ticketVO3.getTotalStarRatings() + ",");
////		System.out.println(ticketVO3.getTotalStars());
////		System.out.println("完成查詢");
//		
//		// 查詢全部
////		List<TicketVO> list = dao.getAll(null);
////		for (TicketVO allTicket : list) {
////			System.out.print(allTicket.getTicketId() + ",");
////			System.out.print(allTicket.getTicketTypeId() + ",");
////			System.out.print(allTicket.getTicketName() + ",");
////			System.out.print(allTicket.getDescription() + ",");
////			System.out.print(allTicket.getPrice() + ",");
////			System.out.print(allTicket.getStock() + ",");
////			System.out.print(allTicket.getValidDays() + ",");
////			System.out.print(allTicket.getStatus() + ",");
////			System.out.print(allTicket.getPublishedDate() + ",");
////			System.out.print(allTicket.getTotalStarRatings() + ",");
////			System.out.println(allTicket.getTotalStars());
////			System.out.println("--------------");
////		}
//	}
}


