package com.smartrm.smartrmmonolith.trade.domain;

import com.smartrm.smartrmmonolith.trade.domain.remote.UserInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:
 */
@Service
public class ActivityService {
    
    public BigDecimal caculateOrderAmount(Order order, UserInfo userInfo) {
        // caculate order amount with activity
        return BigDecimal.ZERO;
    }
}
