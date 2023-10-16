package com.depthspace.memticketowned.model.jdbc;

import com.depthspace.memticketowned.model.MemTicketOwnedVO;

import java.util.List;

public interface MemTicketOwnedDAO_Interface {
    public void insert(MemTicketOwnedVO memTicketOwnedVO);
    public void update(MemTicketOwnedVO memTicketOwnedVO);
    public void delete(Integer ticketOwnedId);
    public MemTicketOwnedVO findByPrimaryKey(Integer ticketOwnedId);
    public List<MemTicketOwnedVO> getAll();
}