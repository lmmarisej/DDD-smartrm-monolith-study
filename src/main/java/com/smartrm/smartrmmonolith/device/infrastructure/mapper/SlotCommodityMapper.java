package com.smartrm.smartrmmonolith.device.infrastructure.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface SlotCommodityMapper {
    
    @Select({
            "select slot_id from slot_commodity where machine_id=#{machineId} and commodity_id=#{commodityId}"})
    Integer findSlot(@Param("machineId") Long machineId, @Param("commodityId") String commodityId);
    
    @Select({
            "select commodity_id from slot_commodity where machine_id=#{machineId} and slot_id=#{slotId}"})
    String getCommodityBySlot(@Param("machineId") Long machineId, @Param("slotId") Integer slotId);
    
}
