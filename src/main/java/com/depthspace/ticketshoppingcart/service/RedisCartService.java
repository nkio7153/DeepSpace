package com.depthspace.ticketshoppingcart.service;

import com.depthspace.ticketshoppingcart.model.RedisCart;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public interface RedisCartService {
    public void saveCart(Integer memId, RedisCart cart);
    //從redis查出會員購物車 票券id, 票券數量
    public RedisCart getCart(Integer memId);
    //更新購物車數量
    public void updateCart(Integer memId, Integer ticketId, Integer quantity);
    //刪除一種票券
    public void deleteCart(Integer memId,Integer ticketId);
    //刪除所有票券
    public void deleteAllCart(Integer memId);

    //取得購物車品項數量
    public int getCartNum(Integer memId);
}
