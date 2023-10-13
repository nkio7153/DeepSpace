package com.depthspace.ticketorders.model.ticketorderdetail;

import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            TicketOrderDetailVO tod = new TicketOrderDetailVO();
            tod.setOrderId(7);  // 這裡只是一個示例值，實際情況可能會不同
            tod.setTicketId(9);  // 同上
            tod.setQuantity(2);
            tod.setUnitPrice(100);
            tod.setDiscountPrice(90);
            tod.setSubtotal(180);
            tod.setTicketReviews("Very good!");
            tod.setStars(Byte.valueOf("5"));
            session.save(tod);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.shutdown();
        }
    }
}
