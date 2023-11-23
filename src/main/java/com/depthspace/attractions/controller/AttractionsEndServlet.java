package com.depthspace.attractions.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.depthspace.attractions.model.AreaVO;
import com.depthspace.attractions.model.AttractionsImagesVO;
import com.depthspace.attractions.model.AttractionsTypeVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AreaService;
import com.depthspace.attractions.service.AttractionsImageService;
import com.depthspace.attractions.service.AttractionsService;
import com.depthspace.attractions.service.AttractionsTypeService;
import com.depthspace.attractions.service.CityService;
import com.google.gson.Gson;

@WebServlet("/attractionsEnd/*")
@MultipartConfig
public class AttractionsEndServlet extends HttpServlet {
	private AttractionsTypeService attrTypeService;
	private AttractionsService attractionService;
	private AttractionsImageService attractionsImageService;
	private CityService cityService;
	private AreaService areaService;
	
	public void init() throws ServletException {
		attrTypeService = new AttractionsTypeService();
		attractionService = new AttractionsService();
		attractionsImageService = new AttractionsImageService();
		cityService = new CityService();
		areaService = new AreaService();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
		case "/list": // 景點列表
			doList(req, res);
			break;
		case "/search": //搜尋
			doSearch(req, res);
			break;
		case "/add": //新增前
			doAdd(req, res);
			break;
		case "/add2": //新增後
			doAdd2(req, res);
			break;
		case "/getArea": //尋找新增時的縣市資料
			doGetArea(req, res);
			break;
		case "/view": //查看該景點
			doView(req, res);
			break;
		case "/edit": //編輯前
			doEdit(req, res);
			break;
		case "/edit2": //編輯完成
			doEdit2(req, res);
			break;
		}
	
	}
	private void doEdit2(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//錯誤存list
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		Integer attractionsTypesId;
		String attractionsName = req.getParameter("attractionsName");
		Integer attractionsId;
		Integer cityId;
		Integer areaId;
		String base64Image;
        byte[] pic=null;
        String description = req.getParameter("description");
		String address = req.getParameter("address");
		Part picture;
		try {
			
			attractionsId = Integer.valueOf(req.getParameter("attractionsId"));
			attractionsTypesId = Integer.valueOf(req.getParameter("attractionsTypesId"));
			
			cityId = Integer.valueOf(req.getParameter("city"));
			areaId = Integer.valueOf(req.getParameter("area"));
			
			System.out.println("attractionsId=" + attractionsId);
//			if(description == null || description.trim().length() == 0) {
//				errorMsgs.add("景點描述請勿空白");
//			}
			
			//處理圖片
			picture = req.getPart("attractionsImg");
            if (picture != null && picture.getSize() > 0) {
            	System.out.println("進入新照片");
            	InputStream inputStream = picture.getInputStream();
				pic = inputStream.readAllBytes();
				inputStream.close();
//				buffer.close();
            } else {
            	System.out.println("進入舊照片");
            	AttractionsImagesVO avo = attractionsImageService.findByPrimaryKey(attractionsId);
                if(avo.getAttractionsImg() == null ) {
                	pic = null;
                } else {
                	pic=avo.getAttractionsImg();
                }

            }
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		//更新資料
		Integer status = 0;
		String typeName = attrTypeService.getOne(attractionsTypesId).getTypeName();
		
//		處理地址
		String city = cityService.findByPrimaryKey(cityId).getCityName();
		String area = areaService.findByPrimaryKey(areaId).getAreaName();
		String newAddress = city + area + address;
		
		AttractionsTypeVO attrTypevo = new AttractionsTypeVO(attractionsTypesId, typeName);
		AttractionsVO attrvo = new AttractionsVO(attractionsId , attrTypevo , areaId , attractionsName , description , status , newAddress);
		//更新景點
		AttractionsVO avo = null;
		avo = attractionService.update(attrvo);
		System.out.println("attractionsId= " + attractionsId);
		//更新圖片
		//用attractionsId找到該筆物件
		AttractionsImagesVO attrImgVo = attractionsImageService.findByAttrId(attractionsId);
		System.out.println("attrImgVo物件=" + attrImgVo);
//		由景點編號找到imageId
		Integer attractionsImagesId = attrImgVo.getAttractionsImagesId();
		//用set的方式
		attrImgVo.setAttractionsImagesId(attractionsImagesId);
		attrImgVo.setAttractionsId(attractionsId);
		attrImgVo.setAttractionsImg(pic);
		attractionsImageService.update(attrImgVo);
		
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/attractions/edit.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		} else {
			res.sendRedirect(req.getContextPath() + "/attractionsEnd/list");				
		}
		
	}
	private void doEdit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer attrId = Integer.valueOf(req.getParameter("attractionsId"));
		AttractionsVO attrvo = attractionService.getAttractionsById(attrId);
//		System.out.println("attrvo裡的區域編號= " + attrvo.getAreaId());
//		處理地址
		if (attrvo.getAddress().length() > 6) {
		    String newAddress = attrvo.getAddress().substring(6);
//		    System.out.println(result);
		    req.setAttribute("newAddress", newAddress);
		} else {
		    // 如果字串的長度小於或等於六，可以處理相應的邏輯，例如返回原始字串或顯示錯誤訊息
		    System.out.println("字串長度不足六");
		}
		//找景點表格裡的區域Id attrvo.getAreaId()
//		[attractionsId=1, attractionsTypeId=AttractionsTypeVO [attractionsTypeId=1, typeName=種類1], areaId=14, attractionsName=台北101, description=台北地標建築，高度508米。, attractionsStatus=0, address=台北市信義區信義路五段7號, lon=121.5654, lat=25.0339]
		//再依據areaId找cityId
		AreaVO avo = areaService.findByPrimaryKey(attrvo.getAreaId());
//		System.out.println("avo的物件=" + avo);
		//再由cityId找到對應縣市名稱
//		avo.getCityId()
		CityVO cvo = cityService.findByPrimaryKey(avo.getCityId());
//		System.out.println("cvo的物件=" + cvo);
		//將值傳至jsp
		req.setAttribute("cvo", cvo);
		
		//所有景點類型
		List<AttractionsTypeVO> attractionsTypes = attrTypeService.getAll();
		req.setAttribute("attractionsTypes", attractionsTypes);
		
		//找到所有區域
		List<AreaVO> area = areaService.getAll();
		req.setAttribute("area", area);
//		System.out.println("area=" + area);

		//找尋所有縣市
		List<CityVO> city = cityService.getAll();
		req.setAttribute("city", city);
		
		req.setAttribute("attrvo", attrvo);
		
		req.getRequestDispatcher("/backend/attractions/edit.jsp").forward(req, res);
	}
	private void doView(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer attrId = Integer.valueOf(req.getParameter("attractionsId"));
		System.out.println("attrId=" + attrId);
		
		AttractionsVO attrvo = attractionService.getAttractionsById(attrId);
		
		setJsonResponse(res, attrvo);
		
	}
	private void doGetArea(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer cityId;
		
		try {
			cityId = Integer.valueOf(req.getParameter("city"));
		} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
		}
		List<AreaVO> list = areaService.getAllArea(cityId);
		
		setJsonResponse(res, list);
		
	}
	private void doAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsTypeService attrTypeService = new AttractionsTypeService();
		
		List<AttractionsTypeVO> attractionsTypes = attrTypeService.getAll();
		List<CityVO> city = cityService.getAll();
		List<AreaVO> area = areaService.getAll();
		req.setAttribute("attractionsTypes", attractionsTypes);
		req.setAttribute("city", city);
		req.setAttribute("area", area);
		req.getRequestDispatcher("/backend/attractions/add.jsp").forward(req, res);
		
		
	}
	//新增景點
	private void doAdd2(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String checkAttractionsTypesId = req.getParameter("attractionsTypesId");
		Integer attractionsTypesId = null;
		String checkCityId = req.getParameter("city");
		Integer cityId = null;
		String checkAreaId = req.getParameter("area");
		Integer areaId = null;
		String base64Image;
        byte[] pic = null;
		String description = req.getParameter("description");
		String attractionsName = req.getParameter("attractionsName");
		String address = req.getParameter("address");
		Part picture;
		try {
//			attractionsTypesId = Integer.valueOf(req.getParameter("attractionsTypesId"));
//			cityId = Integer.valueOf(req.getParameter("city"));
//			areaId = Integer.valueOf(req.getParameter("area"));
			
			//處理圖片
			picture = req.getPart("attractionsImg");
            if (picture != null && picture.getSize() > 0) {
            	InputStream inputStream = picture.getInputStream();
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[1024];
				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				buffer.flush();
				pic = buffer.toByteArray();
				inputStream.close();
				buffer.close();
            } else {
                String webAppPath = getServletContext().getRealPath("/");
                String relativePath = "backend/attractions/images/none3.png";
                String absoultePath = webAppPath + relativePath;

                File defaultImageFile = new File(absoultePath);
                String defaultImagePath = defaultImageFile.getPath();

                if (defaultImageFile.exists()) {
                    byte[] localImagePath = Files.readAllBytes(Path.of(defaultImagePath));
                    base64Image = Base64.getEncoder().encodeToString(localImagePath);

                    res.setContentType("text/plain");
                    res.getWriter().write(base64Image);
                    req.setAttribute("base64Image", base64Image);
                } else {
                    System.out.println("圖不存在");
                }

            }
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		boolean hasError = false;
        
        if (description == null || description.trim().isEmpty()) {
        	req.setAttribute("errorMessageDescription", "必填");
            hasError = true;
        } else {
        	
        }
        if (attractionsName == null || attractionsName.trim().isEmpty()) {
        	req.setAttribute("errorMessageAattractionsName", "必填");
            hasError = true;
        }
        if (address == null || address.trim().isEmpty()) {
        	req.setAttribute("errorMessageAddress", "必填");
            hasError = true;
        }
        if (checkAttractionsTypesId == null || checkAttractionsTypesId.trim().isEmpty()) {
        	req.setAttribute("errorMessageAttractionsTypesId", "必選");
            hasError = true;
        } else {
        	attractionsTypesId = Integer.valueOf(req.getParameter("attractionsTypesId"));
        }
        if (checkCityId == null || checkCityId.trim().isEmpty()) {
        	req.setAttribute("errorMessageCityId", "必選");
            hasError = true;
        } else {
        	cityId = Integer.valueOf(req.getParameter("city"));
        }
        if (checkAreaId == null || checkAreaId.trim().isEmpty()) {
        	req.setAttribute("errorMessageAreaId", "必選");
            hasError = true;
        } else {
        	areaId = Integer.valueOf(req.getParameter("area"));
        }
       
        if (hasError) {
        	req.setAttribute("checkAttractionsTypesId", checkAttractionsTypesId);
        	req.setAttribute("checkCityId", checkCityId);
        	req.setAttribute("checkAreaId", checkAreaId);
        	req.setAttribute("description", description);
        	req.setAttribute("attractionsName", attractionsName);
        	req.setAttribute("address", address);
        	RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/attractions/add.jsp");
            dispatcher.forward(req, res);
            return;
        }

		
		
//		找對應的景點種類
		String typeName = attrTypeService.getOne(attractionsTypesId).getTypeName();
		
//		處理地址
		String city = cityService.findByPrimaryKey(cityId).getCityName();
		String area = areaService.findByPrimaryKey(areaId).getAreaName();
		String newAddress = city + area + address;
		
		
		//新增物件
		Integer attractionsId = null;
		Integer status = 0;
		AttractionsTypeVO attrTypevo = new AttractionsTypeVO(attractionsTypesId, typeName);
		AttractionsVO attrvo = new AttractionsVO(attractionsId , attrTypevo , areaId , attractionsName , description , status , newAddress);
		//存入景點
		AttractionsVO avo = null;
		avo = attractionService.insert(attrvo);
		
		//取得剛剛存的景點ID
		int lastAttractionsId = attrvo.getAttractionsId();
//		System.out.println("剛剛存的景點ID：" + lastAttractionsId);
		//存入照片
		Integer attractionsImagesId = null;
		AttractionsImagesVO attrImg = new AttractionsImagesVO(attractionsImagesId, lastAttractionsId , pic);
		AttractionsImagesVO atvo =  null;
		atvo = attractionsImageService.save(attrImg);
		
		res.sendRedirect(req.getContextPath() + "/attractionsEnd/list");
		
	}
	
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsService attractionService = new AttractionsService();
		AttractionsTypeService attrTypeService = new AttractionsTypeService();
		Integer attractionsTypeId = null;
//		String attractionsName = req.getParameter("attractionsName");
    	Map<String, String[]> map =req.getParameterMap();
    	
    	try {
    		attractionsTypeId = Integer.valueOf(req.getParameter("attrTypeId"));
    		System.out.println("attrTypeId= " + attractionsTypeId);
    	} catch (NumberFormatException e) {
    		attractionsTypeId = null;
    	}
    	
    	List<AttractionsVO> list = new ArrayList<>();
    	
    	if (attractionsTypeId != null) {
    		List<AttractionsVO> atteList = attractionService.getAllAttrType(attractionsTypeId);
    		list.addAll(atteList);
		} 
    	
    	String[] attrTitle = map.get("attractionsName");
    	if (attrTitle != null && attrTitle.length > 0 && !attrTitle[0].isEmpty()) {
			List<AttractionsVO> attrName = attractionService.getAttractionsByCompositeQuery(map);
			list.addAll(attrName);
		}
    	
    	
    	List<AttractionsVO> attrList = attractionService.getAll();
    	Set<AttractionsTypeVO> uniqueTypes = new HashSet<>();
		for (AttractionsVO avo : attrList) {
			uniqueTypes.add(avo.getAttractionsTypeId());
//			System.out.println("123="+avo);
		}
//		System.out.println("456="+new ArrayList<>(uniqueTypes));
		
		req.setAttribute("uniqueTypes", new ArrayList<>(uniqueTypes));
    	
//    	System.out.println(list);
    	req.setAttribute("list", list);
		req.getRequestDispatcher("/backend/attractions/search.jsp").forward(req, res);
	}
	
	//總列表
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AttractionsService attractionService = new AttractionsService();
		List<AttractionsVO> list = attractionService.getAll();
		req.setAttribute("list", list);
//
		List<AttractionsVO> attrList = attractionService.getAll();
		
//		int pageQty = attractionService.getPageTotal();
//		req.getSession().setAttribute("pageQty", pageQty);
//		System.out.println("attrList=" + attrList);
//		System.out.println("currentPage=" + currentPage);
//		
//		req.setAttribute("attrList", attrList);
//		req.setAttribute("currentPage", currentPage);
//		List<AttractionsTypeVO> attractionsTypes = attrTypeService.getAll();
		//處理類型不重複
		Set<AttractionsTypeVO> uniqueTypes = new HashSet<>();
		for (AttractionsVO avo : attrList) {
			uniqueTypes.add(avo.getAttractionsTypeId());
//			System.out.println("123="+avo);
		}
//		System.out.println("456="+new ArrayList<>(uniqueTypes));
		
		req.setAttribute("uniqueTypes", new ArrayList<>(uniqueTypes));
		
		req.getRequestDispatcher("/backend/attractions/list.jsp").forward(req, res);
		
	}
	
	// fetch返回json格式
	private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonData = gson.toJson(obj);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);
	}
	
}
