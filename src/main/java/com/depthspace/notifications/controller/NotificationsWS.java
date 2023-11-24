package com.depthspace.notifications.controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/notificationsws")
public class NotificationsWS {

	private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("ws open!!!!!");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
    	 sendNotification(message);
         System.out.println("ws sendNotification!!!!!");
    }

    @OnClose
    public void onClose(Session session) {
    	System.out.println("WSCOLSE" + session.getId());
        sessions.remove(session);
        System.out.println("ws close!!!!!");
    }

    @OnError
    public void onError(Session session, Throwable e) {
    	System.out.println("ws Error: " + e.toString());
	}

    public static void sendNotification(String message) {
        synchronized (sessions) {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}