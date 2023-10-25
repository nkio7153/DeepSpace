package com.depthspace.ticket.oscardao;

import com.depthspace.ticket.model.TicketImagesVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HbTiDao {
    private SessionFactory factory;
    //factory 用於建立與資料庫的連線的SessionFactory物件
    public HbTiDao(SessionFactory factory){
        this.factory=factory;
    }
    //取得與當前資料庫的連線Session。
    private Session getSession(){
        return factory.getCurrentSession();
    }
    public TicketImagesVO getBySid(Integer serialId) {
        return getSession().createQuery("from TicketImagesVO where serialId= :serialId", TicketImagesVO.class)
                .setParameter("serialId", serialId)
                .uniqueResult();
    }
}
