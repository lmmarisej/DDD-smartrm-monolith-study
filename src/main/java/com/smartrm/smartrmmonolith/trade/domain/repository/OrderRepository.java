package com.smartrm.smartrmmonolith.trade.domain.repository;

import com.smartrm.smartrmmonolith.trade.domain.Order;

/**
 * @author: yoda
 * @description:
 */
public interface OrderRepository {
    
    Order getOrderById(long orderId);
    
    void addOrder(Order order);
    
    void updateOrder(Order order);
    
    void addOrUpdate(Order order);
    
}
