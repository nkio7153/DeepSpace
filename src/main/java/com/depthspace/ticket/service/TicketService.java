package com.depthspace.ticket.service;

import java.util.List;
import java.util.Map;

import com.depthspace.ticket.model.TicketVO;


public interface TicketService {
	
    int addTicket(TicketVO ticketVO);
    
    TicketVO updateTicket(TicketVO ticketVO);

    int deleteTicket(Integer ticketId);

    TicketVO getTicketById(Integer ticketId);
    
    List<TicketVO> getAllTickets(int currentPage);
    
    List<TicketVO> getTicketsWithCity();
    
    int getPageTotal();
    
    List<TicketVO> getAllTicketsWithMainImages();
    
    List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> map);

	List<TicketVO> getAllTickets();
	
    long getTotalTickets(); //取得總票券數

//    List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> map);
	
 

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
