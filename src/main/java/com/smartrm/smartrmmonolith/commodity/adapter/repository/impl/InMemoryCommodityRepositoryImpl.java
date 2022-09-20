package com.smartrm.smartrmmonolith.commodity.adapter.repository.impl;

import com.google.common.collect.Lists;
import com.smartrm.smartrmmonolith.commodity.adapter.CommodityProcessor;
import com.smartrm.smartrmmonolith.commodity.domain.model.Commodity;
import com.smartrm.smartrmmonolith.commodity.domain.repository.CommodityRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yoda
 * @description: 商品资源库实现
 */
@Repository
public class InMemoryCommodityRepositoryImpl implements CommodityRepository {
    
    private Map<String, Commodity> commodities = new HashMap<>();
    
    @Override
    public Commodity findById(String commodityId) {
        return commodities.get(commodityId);
    }
    
    @Override
    public List<Commodity> findBatchByIds(List<String> ids) {
        List<Commodity> ret = Lists.newArrayList();
        for (String id : ids) {
            Commodity c = commodities.get(id);
            if (c != null) {
                ret.add(c);
            }
        }
        return ret;
    }
    
    @Override
    public void put(Commodity commodity) {
        commodities.put(commodity.getCommodityId(), commodity);
    }
    
    @Override
    public void traverse(CommodityProcessor processor) throws IOException {
        for (Commodity commodity : commodities.values()) {
            processor.onCommodity(commodity);
        }
    }
}
