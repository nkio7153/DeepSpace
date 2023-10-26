package com.depthspace.ticketshoppingcart.model.hibernate;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HbTscDaoImpl implements HbTscDao_Interface {
    //宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbTscDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession() {
        return factory.getCurrentSession();
    }
    //插入一筆資料
    @Override
    public int insert(TicketShoppingCartVO entity) {
        return (Integer) getSession().save(entity);
    }
    //更新一筆資料
    @Override
    public int update(TicketShoppingCartVO entity) {
        try {
            getSession().update(entity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    // 根據組合主鍵（會員ID和票券ID）刪除一筆購物車項目
    @Override
    public int delete(TicketShoppingCartVO.CompositeDetail id) {
        TicketShoppingCartVO tsc = getSession().get(TicketShoppingCartVO.class, id);
        if (tsc != null) {
            getSession().delete(tsc);
            // 回傳給 service，1代表刪除成功
            return 1;
        } else {
            // 回傳給 service，-1代表刪除失敗
            return -1;
        }
    }

    // 根據會員編號清空購物車
    @Override
    public int deleteAll(Integer memId) {
        int deleteCount = getSession()
                .createQuery("delete from TicketShoppingCartVO where memId= :memId")
                .setParameter("memId",memId)
                .executeUpdate();
        if (deleteCount >0) {
            // 回傳給 service，1代表刪除成功
            return 1;
        } else {
            // 回傳給 service，-1代表刪除失敗
            return -1;
        }
    }
    // 根據會員ID獲取會員購物車項目
    @Override
    public List<TicketShoppingCartVO> getByMemId(Integer memId) {
        return getSession()
                .createQuery("from TicketShoppingCartVO where memId= :memId", TicketShoppingCartVO.class)
                .setParameter("memId", memId)
                .list();
    }

    // 獲取所有購物車項目
    @Override
    public List<TicketShoppingCartVO> getAll() {
        return getSession().createQuery("from TicketShoppingCartVO", TicketShoppingCartVO.class).list();
    }

    // 獲取指定頁面的所有購物車項目
    @Override
    public List<TicketShoppingCartVO> getAll(int currentPage) {
        return null;
    }
    // 獲取購物車項目的總數量
    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from TicketShoppingCartVO", Long.class).uniqueResult();
    }
}
