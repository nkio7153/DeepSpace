package com.depthspace.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.depthspace.promotion.service.ProService;
import com.depthspace.promotion.service.ProServiceImpl;
import com.depthspace.utils.HibernateUtil;

public class InitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context ended");
		HibernateUtil.shutdown();
	}

}
