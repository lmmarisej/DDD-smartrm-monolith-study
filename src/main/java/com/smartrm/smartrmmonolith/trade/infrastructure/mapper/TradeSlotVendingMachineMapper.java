package com.smartrm.smartrmmonolith.trade.infrastructure.mapper;

import com.smartrm.smartrmmonolith.trade.infrastructure.dataobject.TradeSlotVendingMachineDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface TradeSlotVendingMachineMapper {
    
    @Select({"SELECT * from trade_slot_vending_machine where machine_id=#{machineId}"})
    TradeSlotVendingMachineDo selectOne(long machineId);
    
    @Update({
            "UPDATE trade_slot_vending_machine set state=#{state}, cur_order_id=#{curOrderId}, version=#{version} " +
                    "where machine_id=#{machineId} and version=#{version}-1"})
    int update(TradeSlotVendingMachineDo machine);
    
}
