package com.depthspace.ticketshoppingcart.model;

import java.util.HashMap;
import java.util.Map;

public class RedisCart {
    private Map<Integer, Integer> items;
    public RedisCart(){
        this.items=new HashMap<Integer,Integer>();
    }
    public void addItem(Integer ticketId,Integer quantity ){
        this.items.put(ticketId,quantity);
    }
    public Map<Integer, Integer> getItems(){
        return this.items;
    }
}
