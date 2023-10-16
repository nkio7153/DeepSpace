package com.depthspace.ticketorders.model.ticketorders.hibernate;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HbToDaoImpl implements HbToDao_Interface {
    //SessionFactory物件，用於取得與資料庫的連線Session。
    private SessionFactory factory;
    //factory 用於建立與資料庫的連線的SessionFactory物件
    public HbToDaoImpl(SessionFactory factory){
        this.factory=factory;
    }
    //取得與當前資料庫的連線Session。
    private Session getSession(){
        return factory.getCurrentSession();
    }
    //插入
    @Override
    public int insert(TicketOrdersVO entity) {
        return (Integer)getSession().save(entity);
    }
    //更新
    @Override
    public int update(TicketOrdersVO entity) {
        try{
            getSession().update(entity);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }
    //刪除
    @Override
    public int delete(Integer id) {
        TicketOrdersVO to = getSession().get(TicketOrdersVO.class, id);
        if(to!=null){
            getSession().delete(to);
            return 1;
        }else{
            return -1;
        }
    }
    //用主鍵查一列
    @Override
    public TicketOrdersVO getById(Integer id) {
        return getSession().get(TicketOrdersVO.class, id);
    }

    //用會員編號查詢訂單
    @Override
    public List<TicketOrdersVO> getByMemId(Integer memId) {
        return getSession()
                .createQuery("from TicketOrdersVO where memId= :memId",TicketOrdersVO.class)
                .setParameter("memId", memId)
                .list();
    }

    //查全部列表
    @Override
    public List<TicketOrdersVO> getAll() {
        return getSession().createQuery("from TicketOrdersVO",TicketOrdersVO.class).list();
    }

    @Override
    public List<TicketOrdersVO> getAll(int currentPage) {
        return null;
    }
    //取得列表總數
    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from TicketOrdersVO", Long.class).uniqueResult();
    }
}
