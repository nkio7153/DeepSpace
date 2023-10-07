package com.depthspace.ticket.model.memticketowned;

import java.util.List;

public interface MemTicketOwnedDAO {
    public void insert(MemTicketOwnedVO memTicketOwnedVO);
    public void update(MemTicketOwnedVO memTicketOwnedVO);
    public void delete(Integer ticketOwnedId);
    public MemTicketOwnedVO findByPrimaryKey(Integer ticketOwnedId);
    public List<MemTicketOwnedVO> getAll();
}
