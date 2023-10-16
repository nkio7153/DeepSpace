package com.depthspace.ticketorders.service;

import com.depthspace.ticketorders.model.ticketorderdetail.jdbc.TicketOrderDetailDAO_Interface;
import com.depthspace.ticketorders.model.ticketorderdetail.jdbc.TicketOrderDetailJDBCDAO;
import com.depthspace.ticketorders.model.ticketorders.jdbc.TicketOrdersDAO_Interface;
import com.depthspace.ticketorders.model.ticketorders.jdbc.TicketOrdersJDBCDAO;

public class TicketOrderService {
    private TicketOrderDetailDAO_Interface todDao;//票券訂單明細
    private TicketOrdersDAO_Interface toDao;//票券訂單
    //創建票券訂單dao的方法
    public TicketOrderService() {
        toDao = new TicketOrdersJDBCDAO();
        todDao=new TicketOrderDetailJDBCDAO();
    }
}
