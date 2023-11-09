package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.service.MemBookingService;
import com.depthspace.restaurant.service.MemBookingServiceImpl;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;
import com.depthspace.utils.JedisUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import redis.clients.jedis.Jedis;

@WebServlet("/Rest/*")
public class RestController extends HttpServlet {
	
	private RestService restService;
	private MemBookingService membookingService;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		restService = new RestServiecImpl();
		membookingService = new MemBookingServiceImpl();
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		String forwardPath = "";
		switch (pathInfo) {
			case "/getRests":
				forwardPath = getRests(req, resp);
				break;
			case "/Restinfo":
				forwardPath = toRestinfo(req, resp);
				break;
				
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);
	}
	
	private String getRests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getParameter("restType") != null) {
			String restType = req.getParameter("restType");
			String json = toRedisBy(restType);
			
		}
		
		List<RestVO> restList = restService.showRest();
		req.setAttribute("restList", restList);
		HashSet<String> restType = new HashSet<String>();
		for (RestVO vo : restList) {
			restType.add(vo.getRestType());
		}
		req.setAttribute("restType", restType);
		String json = gson.toJson(restList);
		toRedis(json);
		
		String getType = req.getParameter("restType");
		String byType = toRedisBy(getType);
//		System.out.println(byType);
		
		return "/frontend/rest/restlist.jsp";
	}
	
	private String toRestinfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("restId", req.getParameter("restId"));
		return "/frontend/rest/rest.jsp";
		
	}
	
	private void toRedis(String restList) {
	    Jedis jedis = JedisUtil.getJedisPool().getResource();
	    jedis.select(15);
	    
	    if (!jedis.exists("rests")) {
	      jedis.set("rests", restList);
	      String rList = jedis.get("rests");
	      System.out.println("get");
	      System.out.println(rList);
	    }
//	    System.out.println("redis work");
//	    String json = jedis.get("rests");
//	    System.out.println(json);
	      
	    
//	    RestVO[] voList = gson.fromJson(json, RestVO[].class);
//	    for (RestVO v : voList) {
//	      System.out.println(v);
//	      System.out.println(v.getRestName());
//	    }
//	    Type ListType = new TypeToken<List<RestVO>>(){}.getType();
//	        List<RestVO> restVOList = gson.fromJson(json, ListType);
	//
//	    for (RestVO vo : restVOList) {
//	      System.out.println(vo.getRestName());
//	    }
	    jedis.close();
	}
	
	private String toRedisBy(String restType) {
		Jedis jedis = JedisUtil.getJedisPool().getResource();
	    jedis.select(15);
	    
	    String rests = jedis.get("rests");
	    List<RestVO> rs = new ArrayList<>();
	    
	    List<RestVO> restVOs = gson.fromJson(rests, new TypeToken<List<RestVO>>() {}.getType());

        for (RestVO restVO : restVOs) {
            System.out.println(restVO);
        }
	    
	    jedis.close();
	    return rests;
	    
	}
	
	
	
	
	
}
