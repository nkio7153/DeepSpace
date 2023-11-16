package com.depthspace.function.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.function.model.FunctionVO;
import com.depthspace.function.service.FunctionService;
import com.depthspace.function.service.FunctionServiceImpl;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;


@WebServlet("/func/*")
@MultipartConfig
public class FunctionServlet extends HttpServlet {
	
	private FunctionService functionService;

	public void init() throws ServletException {
		functionService = new FunctionServiceImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}

        switch (pathInfo) {
            case "/add":
                add(req, res);
                break;
            case "/update":
                update(req, res);
                break;
            case "/delete":
                delete(req, res);
                break;
            case "/showFunction":
            	showFunction(req, res);
                break;
            case "/listFunctions":
            	listFunctions(req, res);
                break;
                
            default:
    			System.out.println("Path not handled: " + pathInfo);
        }
    }

	private void listFunctions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    List<FunctionVO> list = functionService.getAll();
	    req.setAttribute("functionList", list);
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/function/listFunctions.jsp");
	    dispatcher.forward(req, res);
	}


	private void showFunction(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String idStr = req.getParameter("funcId");
	    Integer id = Integer.parseInt(idStr);
	    FunctionVO functionVO = functionService.getOneFunction(id);
	    req.setAttribute("function", functionVO);
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/function/showFunction.jsp");
	    dispatcher.forward(req, res);
	}


	private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 從請求中獲取數據並創建 FunctionVO 對象
	    FunctionVO functionVO = new FunctionVO();
	    // 設置 functionVO 的屬性
	    functionService.add(functionVO);

	    res.sendRedirect(req.getContextPath() + "/function/listFunctions");
	}


	private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String idStr = req.getParameter("funcId");
	    String funcName = req.getParameter("funcName");
	    // 可能還有其他參數

	    Integer id = Integer.parseInt(idStr);
	    FunctionVO functionVO = functionService.getOneFunction(id); // 或者 new FunctionVO()，取決於具體邏輯
	    functionVO.setFuncName(funcName);
	    // 設置其他屬性

	    functionService.update(functionVO);

	    res.sendRedirect(req.getContextPath() + "/function/listFunctions");
	}



	private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String idStr = req.getParameter("funcId");
	    Integer id = Integer.parseInt(idStr);
	    functionService.delete(id);

	    res.sendRedirect(req.getContextPath() + "/function/listFunctions");
	}

	

}