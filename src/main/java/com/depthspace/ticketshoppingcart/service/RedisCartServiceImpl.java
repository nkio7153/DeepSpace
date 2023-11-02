package com.depthspace.ticketshoppingcart.service;

import com.depthspace.ticketshoppingcart.model.RedisCart;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class RedisCartServiceImpl {
    private JedisPool jedisPool;
    public RedisCartServiceImpl(JedisPool jedisPool) {
        this.jedisPool=jedisPool;
    }
    //添加購物車
    public void saveCart(Integer memId, RedisCart cart){
        try(Jedis jedis=jedisPool.getResource()){
            // 先從 Redis 中取出已經存在的購物車數據
            Map<String, String> existCart = jedis.hgetAll("cart" + memId);
            // 準備一個新的 Map 來存放更新後的購物車數據
            Map<String, String> updateCart = new HashMap<>();
            // 遍歷新購物車的每一項
            for (Map.Entry<Integer, Integer> entry : cart.getItems().entrySet()) {
                String ticketId = entry.getKey().toString();
                Integer newQuantity = entry.getValue();

                //檢查這個ticketId是否已經存在於購物車中
                if(existCart.containsKey(ticketId)){
                    Integer existQuantity = Integer.valueOf(existCart.get(ticketId));
                    newQuantity+=existQuantity;
                }
                updateCart.put(entry.getKey().toString(), newQuantity.toString());

            }
            jedis.hmset("cart" + memId, updateCart);
        }
    }
    //從redis查出會員購物車 票券id, 票券數量
    public RedisCart getCart(Integer memId){
        try(Jedis jedis=jedisPool.getResource()){
            Map<String, String> redisCart = jedis.hgetAll("cart" + memId);
            RedisCart cart = new RedisCart();
            for(Map.Entry<String,String> entry: redisCart.entrySet()){
                cart.addItem(Integer.valueOf(entry.getKey()),Integer.valueOf(entry.getValue()));
            }
            return cart;
        }
    }
    //更新購物車數量
    public void updateCart(Integer memId, Integer ticketId, Integer quantity){
        try(Jedis jedis = jedisPool.getResource()){
            jedis.hset("cart"+memId, ticketId.toString(), quantity.toString());
        }
    }
    //刪除一種票券
    public void deleteCart(Integer memId,Integer ticketId){
        try(Jedis jedis=jedisPool.getResource()){
            jedis.hdel("cart"+memId,ticketId.toString());
        }
    }
    //刪除所有票券
    public void deleteAllCart(Integer memId){
        try(Jedis jedis=jedisPool.getResource()){
            jedis.del("cart"+memId);
        }
    }
}
