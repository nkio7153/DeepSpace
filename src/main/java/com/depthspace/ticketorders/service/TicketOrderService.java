package com.depthspace.ticketorders.service;

import com.depthspace.memticketowned.MemTicketOwnedDAO_Interface;
import com.depthspace.memticketowned.MemTicketOwnedJDBCDAO;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailDAO_Interface;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailJDBCDAO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersDAO_Interface;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersJDBCDAO;

public class TicketOrderService {
    private TicketOrderDetailDAO_Interface todDao;//票券訂單明細
    private TicketOrdersDAO_Interface toDao;//票券訂單
    //創建票券訂單dao的方法
    public TicketOrderService() {
        toDao = new TicketOrdersJDBCDAO();
        todDao=new TicketOrderDetailJDBCDAO();
    }
}
