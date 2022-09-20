package com.smartrm.smartrmmonolith.operation.infrastructure.mapper;

import com.smartrm.smartrmmonolith.operation.infrastructure.dataobject.VendingMachineModelDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: yoda
 * @description: 售卖机型号信息mapper
 */
@Mapper
public interface VendingMachineModelMapper {
    
    @Select({"SELECT * from `vending_machine_model` where model_code=#{code}"})
    VendingMachineModelDo selectByCode(int code);
}
