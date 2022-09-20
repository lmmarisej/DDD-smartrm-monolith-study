package com.smartrm.smartrmmonolith.device.infrastructure.mapper;

import com.smartrm.smartrmmonolith.device.infrastructure.dataobject.VendingMachineInventoryDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface InventorySnapshotMapper {
    
    @Select({"SELECT * from inventory_snapshot where machine_id=#{machineId}"})
    List<VendingMachineInventoryDo> selectByMachineId(Long machineId);
    
    @Delete({"delete from inventory_snapshot where machine_id=#{machineId}"})
    int deleteByMachineId(Long machineId);
    
    int insertBatch(@Param("list") List<VendingMachineInventoryDo> inventoryDos);
    
}

