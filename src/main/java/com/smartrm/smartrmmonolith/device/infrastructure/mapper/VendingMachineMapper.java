package com.smartrm.smartrmmonolith.device.infrastructure.mapper;

import com.smartrm.smartrmmonolith.device.infrastructure.dataobject.VendingMachineDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface VendingMachineMapper {
    
    @Select({"select * from vending_machine where machine_id=#{machineId}"})
    VendingMachineDo selectByMachineId(Long machineId);
    
    @Select({
            "select * from vending_machine where iot_product_key=#{productKey} and iot_device_name=#{deviceName}"})
    VendingMachineDo selectByIoTKeys(@Param("productKey") String productKey,
                                     @Param("deviceName") String deviceName);
    
    @Update({
            "update vending_machine set `state`=#{state} where machine_id=#{machineId}"})
    int update(VendingMachineDo vendingMachineDo);
    
}
