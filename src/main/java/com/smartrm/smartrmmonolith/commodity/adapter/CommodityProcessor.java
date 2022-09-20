package com.smartrm.smartrmmonolith.commodity.adapter;

import com.smartrm.smartrmmonolith.commodity.domain.model.Commodity;

import java.io.IOException;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityProcessor {
    
    public void onCommodity(Commodity commodity) throws IOException;
    
}
