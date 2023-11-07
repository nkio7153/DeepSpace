package com.depthspace.ticketorders.model.ticketorders.hibernate;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HbToDaoImpl implements HbToDao {
    public static final int PAGE_MAX_RESULT = 10;
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
    //用會員編號取得最新一筆訂單物件(為了拿訂單流水號)
    @Override
    public TicketOrdersVO getLatestOrderByMemId(Integer memId) {
        try {
            return getSession()
                    .createQuery("from TicketOrdersVO where memId= :memId order by orderId desc", TicketOrdersVO.class)
                    .setParameter("memId", memId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    //查當前頁面全部列表
    @Override
    public List<TicketOrdersVO> getAll(int currentPage) {
        int first=(currentPage - 1) * PAGE_MAX_RESULT;
        return getSession().createQuery("from TicketOrdersVO",TicketOrdersVO.class)
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .list();
    }
    //查全部列表
    @Override
    public List<TicketOrdersVO> getAll() {
        return getSession().createQuery("from TicketOrdersVO",TicketOrdersVO.class)
                .list();
    }
    //用會員編號取得當前頁面的所有列表
    @Override
    public List<TicketOrdersVO> getAllByMemId(int currentPage, Integer memId) {
        int first = (currentPage - 1) * PAGE_MAX_RESULT;
        return getSession()
                .createQuery("from TicketOrdersVO where memId= :memId",TicketOrdersVO.class)
                .setParameter("memId", memId)
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .list();
    }
    //取得列表總數
    @Override
    public long getTotal() {
        return getSession().createQuery("select count(*) from TicketOrdersVO", Long.class).uniqueResult();
    }
    //取得會員列表總數
    @Override
    public long getTotalByMemId(Integer memId) {
        return getSession().createQuery("select count(*) from TicketOrdersVO where memId= :memId", Long.class)
                .setParameter("memId",memId)
                .uniqueResult();
    }

    @Override
    public List<TicketOrdersVO> getByCompositeQuery(Map<String, String> map, int page) {
        System.out.println("***map***"+map);
		if(map.size()==0) {
			System.out.println("map:沒資料"+map);
			return getAll(page);
		}
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<TicketOrdersVO> criteria = builder.createQuery(TicketOrdersVO.class);
        Root<TicketOrdersVO> root = criteria.from(TicketOrdersVO.class);
//
        ArrayList<Predicate> predicates = new ArrayList<>();

        // 處理訂單日期範圍的查詢
        if (map.containsKey("startOrderDate") && map.containsKey("endOrderDate")) {
            predicates.add(builder.between(root.get("orderDate"), Timestamp.valueOf(map.get("startOrderDate")), Timestamp.valueOf(map.get("endOrderDate"))));
        } else {
            // 分別處理只有開始日期或結束日期的情況
            if (map.containsKey("startOrderDate")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("orderDate"), Timestamp.valueOf(map.get("startOrderDate"))));
            }
            if (map.containsKey("endOrderDate")) {
                predicates.add(builder.lessThanOrEqualTo(root.get("orderDate"), Timestamp.valueOf(map.get("endOrderDate"))));
            }
        }
        // 處理會員編號的查詢
        if (map.containsKey("memId")) {
            predicates.add(builder.equal(root.get("memId"), Integer.valueOf(map.get("memId"))));
        }

        // 添加其他可能的篩選條件
        // 例如：處理訂單狀態的查詢
        if (map.containsKey("status")) {
            predicates.add(builder.equal(root.get("status"), Integer.valueOf(map.get("status"))));
        }

        // 將所有條件組合在一起
        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        // 執行查詢
        Query<TicketOrdersVO> query = getSession().createQuery(criteria);

        // 分頁處理
        int first = (page - 1) * PAGE_MAX_RESULT;
        query.setFirstResult(first);
        query.setMaxResults(PAGE_MAX_RESULT);

        // 獲取結果並返回
        return query.getResultList();
     }
    @Override
    public int getResultTotal(Map<String, String> map) {
        if(map.size()==0) {
            System.out.println("map:沒資料"+map);
        }
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<TicketOrdersVO> criteria = builder.createQuery(TicketOrdersVO.class);
        Root<TicketOrdersVO> root = criteria.from(TicketOrdersVO.class);
//
        ArrayList<Predicate> predicates = new ArrayList<>();

        // 處理訂單日期範圍的查詢
        if (map.containsKey("startOrderDate") && map.containsKey("endOrderDate")) {
            predicates.add(builder.between(root.get("orderDate"), Timestamp.valueOf(map.get("startOrderDate")), Timestamp.valueOf(map.get("endOrderDate"))));
        } else {
            // 分別處理只有開始日期或結束日期的情況
            if (map.containsKey("startOrderDate")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("orderDate"), Timestamp.valueOf(map.get("startOrderDate"))));
            }
            if (map.containsKey("endOrderDate")) {
                predicates.add(builder.lessThanOrEqualTo(root.get("orderDate"), Timestamp.valueOf(map.get("endOrderDate"))));
            }
        }
        // 處理會員編號的查詢
        if (map.containsKey("memId")) {
            predicates.add(builder.equal(root.get("memId"), Integer.valueOf(map.get("memId"))));
        }

        // 添加其他可能的篩選條件
        // 例如：處理訂單狀態的查詢
        if (map.containsKey("status")) {
            predicates.add(builder.equal(root.get("status"), Integer.valueOf(map.get("status"))));
        }

        // 將所有條件組合在一起
        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        // 執行查詢
        Query<TicketOrdersVO> query = getSession().createQuery(criteria);

        // 獲取結果並返回
        List<TicketOrdersVO> resultList = query.getResultList();
        int total = resultList.size();
        return total;
    }
}
