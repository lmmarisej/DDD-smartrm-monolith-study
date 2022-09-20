package com.smartrm.smartrmmonolith.payment.domain;

import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.trade.domain.OrderType;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
public class OrderInfo {
    
    private final long machineId;
    private final long orderId;
    private final OrderType type;
    private final BigDecimal totalAmount;
    
    public OrderInfo(long machineId, long orderId, OrderType type, BigDecimal totalAmount) {
        this.machineId = machineId;
        this.orderId = orderId;
        this.type = type;
        this.totalAmount = totalAmount;
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public Long getMachineId() {
        return machineId;
    }
    
    public OrderType getType() {
        return type;
    }
    
    public static class Builder {
        
        private long machineId;
        private long orderId;
        private OrderType type;
        private BigDecimal totalAmount;
        
        public Builder machineId(long machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public Builder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }
        
        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        
        public Builder type(OrderType type) {
            this.type = type;
            return this;
        }
        
        public OrderInfo build() {
            //属性校验
            if (this.machineId == 0 || this.totalAmount == null || this.orderId == 0
                    || this.totalAmount.doubleValue() <= 0) {
                throw new DomainException(CommonError.InvalidProperty);
            }
            return new OrderInfo(this.machineId, this.orderId, this.type, this.totalAmount);
        }
        
    }
    
}
