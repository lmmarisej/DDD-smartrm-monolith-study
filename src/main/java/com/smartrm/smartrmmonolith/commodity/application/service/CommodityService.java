package com.smartrm.smartrmmonolith.commodity.application.service;

import com.smartrm.smartrmmonolith.commodity.application.dto.CommodityInfoDto;

import java.util.List;

/**
 * @author: yoda
 * @description: 商品上下文应用层服务接口
 */
public interface CommodityService {
    
    /**
     * 获取商品详情信息
     *
     * @param commodityId 商品id
     * @return 商品信息
     */
    CommodityInfoDto getCommodityDetail(String commodityId);
    
    /**
     * 批量获取商品详情信息
     *
     * @param commodityIds
     * @return 商品信息列表
     */
    List<CommodityInfoDto> getCommodityList(List<String> commodityIds);
    
}
