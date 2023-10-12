package com.depthspace.ticketorders.model.ticketorders;

import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;

import java.sql.Timestamp;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            TicketOrdersVO to = new TicketOrdersVO();
            to.setMemId(4);
            to.setOrderDate(Timestamp.valueOf("2023-10-08 00:00:00"));
            to.setTotalAmount(400);
            to.setPointsFeedback(650);
            to.setAmountPaid(300);
            to.setStatus(Byte.valueOf("0"));
            to.setPaymentMethod(Byte.valueOf("3"));
            session.save(to);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.shutdown();
        }

    }
}

