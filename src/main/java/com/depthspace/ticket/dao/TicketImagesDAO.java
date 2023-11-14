package com.depthspace.ticket.dao;

import java.util.List;

import com.depthspace.ticket.model.TicketImagesVO;

public interface TicketImagesDAO {
	
//	TicketImagesVO saveImage(byte[] imageBytes);
	
    void save(TicketImagesVO ticketImage);
    
    void save(List<TicketImagesVO> ticketImages);
    
    void update(TicketImagesVO ticketImagesVO);
    
    void delete(Integer serialId);
    
    TicketImagesVO findByPrimaryKey(Integer serialId);
    
	void TicketIsMainImage (byte isMainImage) ;
    
    List<TicketImagesVO> getAll();
    
    List<TicketImagesVO> findImagesByTicketId(Integer ticketId);
//    // 更新或新增圖片
//    void updata(TicketImagesVO ticketImagesVO);
    
    TicketImagesVO getImageById(Integer serialId);
}
