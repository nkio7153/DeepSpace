package com.depthspace.promotion.model.promotiondetails;

import com.depthspace.promotion.model.promotion.PromotionTicketView;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HbProDeDaoImpl implements HbProDeDao_Interface {
    //宣告一個factory變數
    private SessionFactory factory;
    //建構子為factory變數賦值，取得工具類的工廠
    public HbProDeDaoImpl(SessionFactory factory){
        this.factory= factory;
    }
    //取得當前session
    private Session getSession(){
        return factory.getCurrentSession();
    }
    //插入一筆資料
    @Override
    public int insert(PromotionDetailsVO entity) {
        return (Integer)getSession().save(entity);
    }
    //插入多筆資料
    @Override
    public void insertBatch(List<PromotionDetailsVO> entities){
        for(PromotionDetailsVO entity: entities){
            getSession().save(entity);
        }
    }
    //更新一筆資料
    @Override
    public int update(PromotionDetailsVO entity) {
        try {
            getSession().update(entity);
            return 1;
        }catch(Exception e){
            return -1;
        }
    }
    //刪除一筆資料
    @Override
    public int delete(PromotionDetailsVO.CompositeDetail id) {
        PromotionDetailsVO proDe = getSession().get(PromotionDetailsVO.class, id);
        if(proDe != null){
            getSession().delete(proDe);
            return 1;
        }else{
            return -1;
        }
    }
    //取得一筆促銷明細
    @Override
    public PromotionDetailsVO getById(PromotionDetailsVO.CompositeDetail id) {
        return getSession().get(PromotionDetailsVO.class, id);
    }
    //用促銷編號取得對應的促銷明細
    @Override
    public List<PromotionTicketView> getByProId(Integer proId) {
//        return getSession()
//                .createQuery("from PromotionDetailsVO where promotionId= :proId",PromotionDetailsVO.class)
//                .setParameter("proId", proId)
//                .list();
//        return getSession()
//                .createQuery("SELECT DISTINCT * from PromotionTicketView where promotionId= :proId", PromotionTicketView.class)
//                .setParameter("proId",proId)
//                .list();
        return getSession()
                .createQuery("SELECT DISTINCT ptv from PromotionTicketView ptv where ptv.promotionId= :proId", PromotionTicketView.class)
                .setParameter("proId", proId)
                .list();
    }

    //取得所有資料
    @Override
    public List<PromotionDetailsVO> getAll() {
        return getSession().createQuery("from PromotionDetailsVO",PromotionDetailsVO.class).list();
    }
    //取得當前頁面資料
    @Override
    public List<PromotionDetailsVO> getAll(int currentPage) {
        return null;
    }
    //取得資料筆數
    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from PromotionDetailsVO", long.class).uniqueResult();
    }
    //用促銷編號刪除所有對應的促銷明細
    public int deleteByProId(Integer proId){
        return getSession().createQuery("delete from PromotionDetailsVO where promotionId= :proId")
                .setParameter("proId",proId)
                .executeUpdate();
    }

}
