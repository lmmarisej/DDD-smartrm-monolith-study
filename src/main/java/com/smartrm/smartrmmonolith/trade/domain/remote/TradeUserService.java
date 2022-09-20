package com.smartrm.smartrmmonolith.trade.domain.remote;

/**
 * @author: yoda
 * @description:
 */
public interface TradeUserService {
    
    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    UserInfo getUserInfo(String openId);
    
    
}
