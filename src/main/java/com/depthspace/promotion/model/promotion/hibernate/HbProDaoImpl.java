package com.depthspace.promotion.model.promotion.hibernate;
import com.depthspace.promotion.model.promotion.PromotionVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class HbProDaoImpl implements HbProDao {

    private SessionFactory factory;

    public HbProDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
    private Session getSession(){
        return factory.getCurrentSession();
    }
    //插入一筆資料
    @Override
    public int insert(PromotionVO entity) {
        return (Integer)getSession().save(entity);
    }
    //更新一筆資料
    @Override
    public int update(PromotionVO entity) {
        try{
            getSession().update(entity);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //刪除一筆資料
    @Override
    public int delete(Integer id) {

        PromotionVO pro = getSession().get(PromotionVO.class, id);
        if(pro!=null){
            getSession().delete(pro);
            return 1;
        }else {
            return -1;
        }
    }
    //取得一筆資料
    @Override
    public PromotionVO getById(Integer id) {
        return getSession().get(PromotionVO.class, id);
    }
    //取得所有資料
    @Override
    public List<PromotionVO> getAll() {
        return getSession().createQuery("from PromotionVO", PromotionVO.class).list();
    }
    //取得當前頁面資料
    @Override
    public List<PromotionVO> getAll(int currentPage) {
        return null;
    }
    //取得資料筆數
    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from PromotionVO", Long.class).uniqueResult();
    }
    //用會員編號取得最新一筆訂單物件(為了拿訂單流水號)
    @Override
    public PromotionVO getLatestOrder() {
        try {
            return getSession()
                    .createQuery("from PromotionVO order by promotionId desc", PromotionVO.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
