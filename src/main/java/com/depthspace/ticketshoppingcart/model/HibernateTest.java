package com.depthspace.ticketshoppingcart.model;

import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;

import java.sql.Timestamp;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            TicketShoppingCartVO tsc = new TicketShoppingCartVO();
            tsc.setMemId(6);
            tsc.setTicketId(324002);
            tsc.setQuantity(1);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            tsc.setAddedDate(currentTime);
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
