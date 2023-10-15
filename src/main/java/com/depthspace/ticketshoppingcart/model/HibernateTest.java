package com.depthspace.ticketshoppingcart.model;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;

import java.sql.Date;
import java.sql.Timestamp;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            TicketShoppingCartVO tsc = new TicketShoppingCartVO();
            tsc.setMemId(7);
            tsc.setTicketId(324003);
            tsc.setQuantity(1);
            Date date = new Date(System.currentTimeMillis());
            tsc.setAddedDate(date);
            session.save(tsc);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.shutdown();
        }
    }
}
