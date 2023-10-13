package com.depthspace.admin.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class AdminDAOImpl implements AdminDAO {

    private SessionFactory sessionFactory;

    public AdminDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 新增資料
    @Override
    public int insert(AdminVO entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity); // 儲存資料
        transaction.commit(); // 提交交易
        session.close(); // 關閉Session
        return entity.getAdminId(); // 返回新增資料的ID
    }

    // 更新資料
    @Override
    public int update(AdminVO entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity); // 更新資料
        transaction.commit(); // 提交交易
        session.close(); // 關閉Session
        return entity.getAdminId(); // 返回更新資料的ID
    }

    // 刪除資料
    @Override
    public int delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AdminVO admin = session.get(AdminVO.class, id);
        session.delete(admin); // 刪除資料
        transaction.commit(); // 提交交易
        session.close(); // 關閉Session
        return id; // 返回被刪除的資料ID
    }

    // 根據ID查詢資料
    @Override
    public AdminVO getById(Integer id) {
        Session session = sessionFactory.openSession();
        AdminVO admin = session.get(AdminVO.class, id); // 根據ID查詢資料
        session.close(); // 關閉Session
        return admin; // 返回查詢到的資料
    }

    // 獲取所有資料
    @Override
    public List<AdminVO> getAll() {
        Session session = sessionFactory.openSession();
        Query<AdminVO> query = session.createQuery("from AdminVO", AdminVO.class);
        List<AdminVO> admins = query.getResultList(); // 查詢所有資料
        session.close(); // 關閉Session
        return admins; // 返回查詢到的資料集合
    }

    // 根據條件查詢資料（複合查詢）
    @Override
    public List<AdminVO> getByCompositeQuery(Map<String, String> map) {
        // 實作複合查詢的邏輯
        return null;
    }

    // 獲取指定頁數的資料
    @Override
    public List<AdminVO> getAll(int currentPage) {
        Session session = sessionFactory.openSession();
        Query<AdminVO> query = session.createQuery("from AdminVO", AdminVO.class);
        int pageSize = 10; // 定義每頁顯示的記錄數
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<AdminVO> admins = query.getResultList(); // 查詢指定頁數的資料
        session.close(); // 關閉Session
        return admins; // 返回查詢到的資料集合
    }

    // 獲取資料總數
    @Override
    public long getTotal() {
        Session session = sessionFactory.openSession();
        Query<Long> query = session.createQuery("select count(*) from AdminVO", Long.class);
        long total = query.uniqueResult(); // 獲取資料總數
        session.close(); // 關閉Session
        return total; // 返回資料總數
    }
}
