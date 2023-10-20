package com.depthspace.ticket.service;



public interface TicketService {
	

	
	
}
/***JDBC***/
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import com.depthspace.ticket.dao.TicketDAO;
//import com.depthspace.ticket.dao.TicketDAOlmpl;
//
//
//public class TicketService {
//	
//	private TicketDAO dao; 
////	private TicketImagesDAO_Interface imgDao;
//	
//	public TicketService() {
////		dao = new TicketJDBCDAO(); //創建實體(JDBC)
//		dao = new TicketDAOlmpl(); //創建實體(Hibernate)
//	}
//
//	/*****新增票券******/
//	public TicketVO addTicket(TicketVO ticketVO) {
//		dao.insert(ticketVO);
//		return ticketVO;
//	}
//	
//	/*****取得所有票券資料******/
//	public List<TicketInfo> getTicketsInfo(Map<String, String[]> map) {
//        return dao.getTicketsInfo(null);
//	}
//
//////新增圖片
//	public byte[] getPictureByteArray(String path) throws IOException {
//	FileInputStream fis = new FileInputStream(path);
//	byte[] buffer = fis.readAllBytes();
//	
//	fis.close();
//	return buffer;
//
//	}
//////查找圖片
//	private outputImage(byte[] imageData, String outputPath) {
//    FileOutputStream fos = null;
//    try {
//        fos = new FileOutputStream(outputPath);
//        fos.write(imageData);
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        if (fos != null) {
//            try {
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

//}
