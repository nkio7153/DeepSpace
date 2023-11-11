package com.depthspace.column.service;

import java.util.Map;
import java.util.HashMap;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.model.ColumnImagesVO;
import com.depthspace.column.service.ColumnImagesService;

import redis.clients.jedis.Jedis;

public class ColumnRedisService {
	
	private Jedis jedis = new Jedis("localhost", 6379);
	private ColumnImagesService columnImagesService;
	
	public void RedisService(ColumnImagesService columnImagesService) {
		this.columnImagesService = columnImagesService;
	}
	
//	public List<ColumnArticlesVO> get
	public void randomToRedis(ColumnArticlesVO columnArticles) {

		jedis.select(3);
		if(columnArticles.getArtiStatus() != 0 ) {
			return;
		}
		
		try {
			String key = "columnArticles:" +columnArticles.getArtiId();
			Map<String, String> data = new HashMap<>();
			data.put("artiTitle", columnArticles.getArtiTitle());
			data.put("artiContent", columnArticles.getArtiContent());
			data.put("artiType", columnArticles.getColType().getColTypeName());
			
			ColumnImagesVO mainImg = columnImagesService.getMainImageByArticleId(columnArticles.getArtiId());
			if(mainImg != null) {
				data.put("artiMainImg", mainImg.getColImgId().toString());
			}
		
			jedis.hmset(key, data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    jedis.close();
	}
	
}
