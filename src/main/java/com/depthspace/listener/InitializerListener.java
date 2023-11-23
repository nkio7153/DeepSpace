package com.depthspace.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.depthspace.promotion.service.ProService;
import com.depthspace.promotion.service.ProServiceImpl;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;

public class InitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
		System.out.println("產生 Redis Pool");
		JedisUtil.getJedisPool();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("關閉 Redis Pool");
		JedisUtil.shutdownJedisPool();
		System.out.println("context ended");
		HibernateUtil.shutdown();
	}

}
