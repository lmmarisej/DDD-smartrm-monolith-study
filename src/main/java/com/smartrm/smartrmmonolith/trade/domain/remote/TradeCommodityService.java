package com.smartrm.smartrmmonolith.trade.domain.remote;

import com.google.common.collect.Maps;
import com.smartrm.smartrmmonolith.trade.domain.StockedCommodity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
public interface TradeCommodityService {
    
    /**
     * 获取商品详情信息
     *
     * @param commodityId 商品id
     * @return 商品信息
     */
    CommodityInfo getCommodityDetail(String commodityId);
    
    /**
     * 批量获取商品详情信息
     *
     * @param commodityIds
     * @return 商品信息列表
     */
    List<CommodityInfo> getCommodityList(List<String> commodityIds);
    
    default List<StockedCommodity> inventoryToCommodity(List<InventoryInfo> inventoryInfos) {
        Map<String, Integer> inventoryMap = Maps.newHashMap();
        List<CommodityInfo> infos = getCommodityList(
                inventoryInfos.stream().map(info -> {
                    inventoryMap.put(info.getCommodityId(), info.getCount());
                    return info.getCommodityId();
                }).collect(
                        Collectors.toList()));
        return infos.stream().map(info -> new StockedCommodity(info.getCommodityId()
                , info.getCommodityName(), info.getImageUrl(), info.getPrice(),
                inventoryMap.get(info.getCommodityId()))).collect(Collectors.toList());
        
    }
    
}
