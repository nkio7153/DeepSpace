package com.depthspace.memticketowned.model;

import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;

public class HbTest {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        HbMtoDaoImpl dao = new HbMtoDaoImpl(factory);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // 获取当前时间的时间戳（毫秒）
        long currentTimeMillis = System.currentTimeMillis();
        // 加一年的时间（一年的毫秒数为 365 天 * 24 小时 * 60 分钟 * 60 秒 * 1000 毫秒）
        long oneYearMillis = 365L * 24 * 60 * 60 * 1000;
        // 计算加一年后的时间戳
        long newTimeMillis = currentTimeMillis + oneYearMillis;
        Timestamp now = new Timestamp(currentTimeMillis);
        Timestamp later = new Timestamp(newTimeMillis);
        MemTicketOwnedVO mto = new MemTicketOwnedVO(null,4,4,now, later,null);
        int answer = dao.insert(mto);
        session.getTransaction().commit();
    }
}
