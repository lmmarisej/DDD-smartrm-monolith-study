package com.smartrm.smartrmmonolith.trade.adapter.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.trade.domain.Order;
import com.smartrm.smartrmmonolith.trade.domain.OrderState;
import com.smartrm.smartrmmonolith.trade.domain.OrderType;
import com.smartrm.smartrmmonolith.trade.domain.StockedCommodity;
import com.smartrm.smartrmmonolith.trade.domain.repository.OrderRepository;
import com.smartrm.smartrmmonolith.trade.infrastructure.dataobject.OrderDo;
import com.smartrm.smartrmmonolith.trade.infrastructure.dataobject.StockedCommodityDo;
import com.smartrm.smartrmmonolith.trade.infrastructure.mapper.OrderMapper;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    
    @Autowired
    OrderMapper orderMapper;
    
    @Autowired
    DomainEventBus eventBus;
    
    @Override
    public Order getOrderById(long orderId) {
        OrderDo orderDo = orderMapper.selectOne(orderId);
        try {
            StockedCommodityDo[] commodityDos = new ObjectMapper()
                    .readValue(orderDo.getCommodities(), StockedCommodityDo[].class);
            return Order.Builder()
                    .orderId(orderId)
                    .paymentId(orderDo.getPaymentId())
                    .type(OrderType.of(orderDo.getType()))
                    .machineId(orderDo.getMachineId())
                    .state(OrderState.of(orderDo.getState()))
                    .commodities(Arrays.stream(commodityDos).map(
                            d -> new StockedCommodity(d.getCommodityId(),
                                    d.getName(),
                                    d.getImageUrl(),
                                    d.getPrice(),
                                    d.getCount())).collect(
                            Collectors.toList()))
                    .eventBus(eventBus).build();
        } catch (JsonProcessingException e) {
            throw new DomainException(CommonError.PersistentDataError);
        }
    }
    
    @Override
    public void addOrder(Order order) {
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(order.getOrderId());
        orderDo.setMachineId(order.getMachineId());
        orderDo.setPaymentId(order.getPaymentId());
        orderDo.setState(order.getState().code());
        orderDo.setType(order.getType().code());
        try {
            orderDo.setCommodities(
                    new ObjectMapper().writeValueAsString(order.getCommodities().stream().map(c -> {
                        StockedCommodityDo commodityDo = new StockedCommodityDo();
                        commodityDo.setCommodityId(c.getCommodityId());
                        commodityDo.setCount(c.getCount());
                        commodityDo.setImageUrl(c.getImageUrl());
                        commodityDo.setName(c.getName());
                        commodityDo.setPrice(c.getPrice());
                        return commodityDo;
                    }).toArray()));
        } catch (JsonProcessingException e) {
            throw new DomainException(CommonError.PersistentDataError);
        }
        orderMapper.add(orderDo);
    }
    
    @Override
    public void updateOrder(Order order) {
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(order.getOrderId());
        orderDo.setState(order.getState().code());
        orderDo.setPaymentId(order.getPaymentId());
        orderMapper.updateStateAndPayment(orderDo);
    }
    
    @Override
    public void addOrUpdate(Order order) {
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(order.getOrderId());
        orderDo.setType(order.getType().code());
        orderDo.setMachineId(order.getMachineId());
        orderDo.setState(order.getState().code());
        orderDo.setPaymentId(order.getPaymentId());
        try {
            orderDo.setCommodities(
                    new ObjectMapper().writeValueAsString(order.getCommodities().stream().map(c -> {
                        StockedCommodityDo commodityDo = new StockedCommodityDo();
                        commodityDo.setCommodityId(c.getCommodityId());
                        commodityDo.setCount(c.getCount());
                        commodityDo.setImageUrl(c.getImageUrl());
                        commodityDo.setName(c.getName());
                        commodityDo.setPrice(c.getPrice());
                        return commodityDo;
                    }).toArray()));
            orderMapper.addOrUpdate(orderDo);
        } catch (JsonProcessingException e) {
            throw new DomainException(CommonError.PersistentDataError);
        }
    }
    
    @Aspect
    @Component
    public static class AspectClass implements ApplicationContextAware {
        
        private static OrderRepository orderRepository;

//    @Pointcut("execution(* com.smartrm.smartrmmonolith.trade.domain.Order.cancel(..)) "
//        + "|| execution(* com.smartrm.smartrmmonolith.trade.domain.Order.succeed(..)) "
//        + "|| execution(* com.smartrm.smartrmmonolith.trade.domain.Order.setPaymentId(..))")
//    public void orderUpdate() {
//
//    }
//
//    @After("orderUpdate()")
//    public void saveOrder(JoinPoint jp) {
//      Order order = (Order) jp.getTarget();
//      orderRepository.updateOrder(order);
//    }
        
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            orderRepository = applicationContext.getBean(OrderRepository.class);
        }
    }
    
}
