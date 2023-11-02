package com.depthspace.memticketowned.model.hibernate;

import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HbMtoDaoImpl implements HbMtoDao {
    //宣告一個factory變數
    private SessionFactory factory;
    //構造器為該物件的factory變數賦值
    public HbMtoDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
    //取得當前session
    private Session getSession(){
        return factory.getCurrentSession();
    }
    //插入一筆資料
    @Override
    public void insertBatch(List<MemTicketOwnedVO> mtoList) {
        for(MemTicketOwnedVO vo:mtoList){
            getSession().save(vo);
        }
    }


    //更新一筆資料
    @Override
    public int update(MemTicketOwnedVO entity) {
        try{
            getSession().update(entity);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    //刪除一筆資料
    @Override
    public int delete(Integer id) {
        MemTicketOwnedVO mto = getSession().get(MemTicketOwnedVO.class, id);
        if(mto!=null){
            getSession().delete(mto);
            return 1;
        }else{
            return -1;
        }
    }
    //取得一筆資料
    @Override
    public MemTicketOwnedVO getById(Integer id) {

        return getSession().get(MemTicketOwnedVO.class, id);
    }
    //用會員編號取得所有資料
    @Override
    public List<MemTicketOwnedVO> getByMemId(Integer memId) {
        return getSession()
                .createQuery("from MemTicketOwnedVO where memId= :memId",MemTicketOwnedVO.class)
                .setParameter("memId", memId)
                .list();
    }

    //取得
    @Override
    public List<MemTicketOwnedVO> getAll() {
        return getSession().createQuery("from MemTicketOwnedVO ", MemTicketOwnedVO.class).list();
    }

    @Override
    public List<MemTicketOwnedVO> getAll(int currentPage) {
        return null;
    }

    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from MemTicketOwnedVO", Long.class).uniqueResult();
    }
}
