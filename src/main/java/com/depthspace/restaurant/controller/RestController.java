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

import org.json.JSONArray;
import org.json.JSONObject;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.service.MemBookingService;
import com.depthspace.restaurant.service.MemBookingServiceImpl;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;
import com.depthspace.utils.JedisUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
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
//			toRedis(vo);
		}
		req.setAttribute("restType", restType);
		
		toRedis(gson.toJson(restList));
//		String json = gson.toJson(restList);
//		toRedis(json);
//		String getType = req.getParameter("restType");
//		String byType = toRedisBy(getType);
//		System.out.println(byType);
		
		return "/frontend/rest/restlist.jsp";
	}
	
	private String toRestinfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("restId", req.getParameter("restId"));
		return "/frontend/rest/rest.jsp";
		
	}
	
	private void toRedis(String vo) {
	    Jedis jedis = JedisUtil.getJedisPool().getResource();
	    jedis.select(15);
	    
	    if (!jedis.exists("rests")) {
	      jedis.set("rests", vo);
	    }
	    
	    String rests = jedis.get("rests");
	    List<RestVO> restvo = JSON.parseArray(rests, RestVO.class);
	    List<RestVO> restvo1 = JSON.parseObject(rests, new TypeReference<List<RestVO>>(){}); 
	    for (RestVO vo1 : restvo) {
	    	System.out.println(vo1.getRestType());
	    }
	    for (RestVO vo2 : restvo1) {
	    	System.out.println(vo2.getRestName());
	    }
	    
	    jedis.close();
	}
	
	private String toRedisBy(String restType) {
		Jedis jedis = JedisUtil.getJedisPool().getResource();
	    jedis.select(15);
	    
	    String rests = jedis.get("rests");
	    
	    jedis.close();
	    return rests;
	    
	}
	
	
	
	
	
}
