package com.depthspace.ticketshoppingcart.model.hibernate;

import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HbTscDaoImpl implements HbTscDao {
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
    //根據ticketId集合取得對應的購物車清單
    @Override
    public List<CartInfo> getByTicketIds(Set<Integer> ticketIds) {
        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // 首先獲取所有匹配的票券，無論其促銷狀態如何
        List<CartInfo> allCartInfos = getSession().createQuery("FROM CartInfo WHERE ticketId IN (:ticketIds)", CartInfo.class)
                .setParameterList("ticketIds", ticketIds)
                .list();

        Map<Integer, CartInfo> selectedCartInfos = new HashMap<>();
        for (CartInfo cartInfo : allCartInfos) {
            Integer ticketId = cartInfo.getTicketId();
            // 檢查是否已經有對應這個 ticketId 的 CartInfo
            if (!selectedCartInfos.containsKey(ticketId)) {
                // 如果沒有，直接添加
                selectedCartInfos.put(ticketId, cartInfo);
            } else {
                // 如果已有，檢查現有的是否有有效的促銷，如果沒有，則檢查當前 CartInfo 是否有有效的促銷
                CartInfo existingCartInfo = selectedCartInfos.get(ticketId);
                if (!isValidPromotion(existingCartInfo, date) && isValidPromotion(cartInfo, date)) {
                    selectedCartInfos.put(ticketId, cartInfo);
                }
            }
        }

        return new ArrayList<>(selectedCartInfos.values());
    }
    private boolean isValidPromotion(CartInfo cartInfo, Date currentDate) {
            // 檢查促銷是否有效
        return cartInfo.getStartDate() != null && cartInfo.getEndDate() != null &&
                !currentDate.before(cartInfo.getStartDate()) && !currentDate.after(cartInfo.getEndDate());
    }
}
