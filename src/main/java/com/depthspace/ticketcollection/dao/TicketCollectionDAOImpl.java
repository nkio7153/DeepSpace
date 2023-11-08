package com.depthspace.ticketcollection.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO;
import com.depthspace.ticketcollection.model.TicketCollectionVO.CompositeDetail;

public class TicketCollectionDAOImpl implements TicketCollectionDAO{

	private SessionFactory factory;
	
	public TicketCollectionDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	//新增一筆
	@Override 
	public int insert(TicketCollectionVO ticketCollection) {
		return (Integer) getSession().save(ticketCollection);
	}
	//更新
	@Override
	public int update(TicketCollectionVO ticketCollection) {
        try {
            getSession().update(ticketCollection);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
	//刪除(根據主鍵)
	@Override
	public int delete(TicketCollectionVO.CompositeDetail id) {
		TicketCollectionVO ticketCollection = getSession().get(TicketCollectionVO.class, id);
        if (ticketCollection != null) {
            getSession().delete(ticketCollection);
            return 1;
        } else {
            return -1;
        }
    }
	//刪除全部(根據會員)
	@Override
	public int deleteAll(Integer memId) {
        int deleteCount = getSession()
                .createQuery("delete from TicketCollectionVO where memId= :memId")
                .setParameter("memId",memId)
                .executeUpdate();
        if (deleteCount >0) {
            return 1;
        } else {
            return -1;
        }
    }
	//取得(會員)
	@Override
	public List<TicketCollectionVO> getByMemId(Integer memId) {
        return getSession()
//                .createQuery("from TicketCollectionVO where memId= :memId", TicketCollectionVO.class)
        		.createQuery("SELECT tc FROM TicketCollectionVO tc JOIN FETCH tc.ticketVO WHERE tc.memId = :memId", TicketCollectionVO.class)
                .setParameter("memId", memId)
                .list();
	}
	//取得全部
	@Override
	public List<TicketCollectionVO> getAll() {
        return getSession().createQuery("from TicketCollectionVO", TicketCollectionVO.class).list();
    }
	//取得會員ID的收藏票券數
	@Override
	public long getTotal(Integer memId) {
	    return (Long) getSession()
	            .createQuery("select count(*) from TicketCollectionVO where memId = :memId")
	            .setParameter("memId", memId)
	            .uniqueResult();
	}
}
