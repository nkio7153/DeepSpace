package com.depthspace.notifications.model;

import java.util.List;

public interface NotificationsDAO_Interface {
    void insert(NotificationsVO notificationsVO);
    void update(NotificationsVO notificationsVO);
    void delete(Integer noteId);
    NotificationsVO findByPrimaryKey(Integer noteId);
    List<NotificationsVO> getAll();
}
