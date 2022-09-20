package com.smartrm.smartrmmonolith.device.infrastructure.mapper;

import com.smartrm.smartrmmonolith.device.infrastructure.dataobject.VendingMachineInventoryDo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface VendingMachineInventoryMapper {
    
    @Select({"SELECT * from vending_machine_inventory where machine_id=#{machineId}"})
    List<VendingMachineInventoryDo> selectByMachineId(Long machineId);
    
    @Delete({
            "delete from vending_machine_inventory where machine_id=#{machineId} and commodity_id=#{commodityId}"})
    int delete(VendingMachineInventoryDo inventoryDo);
    
    @Delete({"delete from vending_machine_inventory where machine_id=#{machineId}"})
    int deleteByMachineId(Long machineId);
    
    @Update({
            "update vending_machine_inventory set `count`=#{count} where machine_id=#{machineId} and commodity_id=#{commodityId}"})
    int update(VendingMachineInventoryDo inventoryDo);
    
    int insertBatch(@Param("list") List<VendingMachineInventoryDo> inventoryDos);
    
}
