package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson2.JSON;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.CityService;
import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.restaurant.service.MemBookingService;
import com.depthspace.restaurant.service.MemBookingServiceImpl;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;
import com.depthspace.restaurant.service.RestcollectionService;
import com.depthspace.restaurant.service.RestcollectionServiceImpl;
import com.depthspace.utils.JedisUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import redis.clients.jedis.Jedis;

@WebServlet("/Rest/*")
public class RestController extends HttpServlet {

	private RestService restService;
	private MemBookingService membookingService;
	private RestcollectionService restcollectionService;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		restService = new RestServiecImpl();
		membookingService = new MemBookingServiceImpl();
		restcollectionService = new RestcollectionServiceImpl();
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
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
		case "/memCollection":
			forwardPath = memCollection(req, resp);
			break;
		case "/membooking":
			forwardPath = membooking(req, resp);
			break;

		}

		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);
	}

	private String getRests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CityService cityService = new CityService();
		List<CityVO> citylist = cityService.getAll();
		req.setAttribute("citylist", citylist);
		
		List<RestVO> restList = restService.showRest();
		
		// 複合查詢
		String city = req.getParameter("city");
		String restType = req.getParameter("restType");
		String restName = req.getParameter("restName");
		if (city != null || restType != null || restName != null) {
			Map<String, String> map = new HashMap<>();
			map.put("restAddress", !city.isEmpty() ? city : null);
			map.put("restType", !restType.isEmpty() ? restType : null);
			map.put("restName", !restName.isEmpty() ? restName : null);
			System.out.println(map);
			List<RestVO> to = restService.sreach(map);
			System.out.println(to);
			req.setAttribute("restList", to);
		} else {
			req.setAttribute("restList", restList);
		}
		
		// 查出所有類型用HashSet去重
		HashSet<String> typeList = new HashSet<String>();
		for (RestVO vo : restList) {
			typeList.add(vo.getRestType());
		}
		req.setAttribute("restType", typeList);
		// 從session取會員ID顯示已收藏的餐廳
		HttpSession session = req.getSession();
		
		Integer memId = (Integer) session.getAttribute("memId");
		if (memId != null) {
			System.out.println("登入="+memId);
			RestcollectionService rcs = new RestcollectionServiceImpl();
			List<RestCollectionVO> rcList = rcs.findByMemId(memId);
			req.setAttribute("rcList", rcList);
		}
		
		return "/frontend/rest/restlist.jsp";
	}

	private String toRestinfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		RestService restService = new RestServiecImpl();
		RestVO restList = restService.getRestByRestId(Integer.valueOf(restId));
		req.setAttribute("rest", restList);
		
		// booking
		HttpSession session = req.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		if (memId != null) {
			req.setAttribute("memId", memId);
		}
		return "/frontend/rest/rest.jsp";

	}

	private void toRedis(String vo) {
		Jedis jedis = JedisUtil.getJedisPool().getResource();
		jedis.select(15);
//		if (!jedis.exists("rests")) {
//			jedis.set("rests", vo);
//		}
		// 暫時處理修改優化
		jedis.del("rests", vo);
		jedis.set("rests", vo);
		jedis.close();
	}

	private List<RestVO> getRedis(String restType, String restName) {
		Jedis jedis = JedisUtil.getJedisPool().getResource();
		jedis.select(15);
		String rests = jedis.get("rests");

		List<RestVO> restvo = JSON.parseArray(rests, RestVO.class);
//		List<RestVO> restvo = JSON.parseObject(rests, new TypeReference<List<RestVO>>() {});
		List<RestVO> list = new ArrayList<>();
		if (restType != null && restName != null) {
			for (RestVO vo : restvo) {
				if (vo.getRestType().equals(restType) && vo.getRestName().contains(restName)) {
					list.add(vo);
				}
			}
		} else if (restType != null) {
			for (RestVO vo : restvo) {
				if (vo.getRestType().equals(restType)) {
					list.add(vo);
				}
			}
		} else if (restName != null) {
			for (RestVO vo : restvo) {
				if (vo.getRestName().contains(restName)) {
					list.add(vo);
				}
			}
		}
		jedis.close();
		return list;
	}
	
	private String memCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		List<RestCollectionVO> rcList = restcollectionService.findByMemId(memId);
		req.setAttribute("rcList", rcList);
		return "/frontend/rest/memCollection.jsp";
	}
	
	private String membooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		List<MemBookingVO> mbList =  membookingService.getByMemId(memId);
		req.setAttribute("mbList", mbList);
		return "/frontend/rest/membooking.jsp";
	}

	
}
