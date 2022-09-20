package com.smartrm.smartrmmonolith.trade.infrastructure.mapper;

import com.smartrm.smartrmmonolith.trade.infrastructure.dataobject.OrderDo;
import org.apache.ibatis.annotations.*;

/**
 * @author: yoda
 * @description:
 */
@Mapper
public interface OrderMapper {
    
    
    @Select({"SELECT * from `order` where order_id=#{orderId}"})
    OrderDo selectOne(long orderId);
    
    @Insert({
            "insert into `order` ( "
                    + "order_id, "
                    + "machine_id, "
                    + "`state`, "
                    + "`type`, "
                    + "payment_id, "
                    + "commodities) values ("
                    + "#{orderId}, "
                    + "#{machineId}, "
                    + "#{state}, "
                    + "#{type}, "
                    + "#{paymentId}, "
                    + "#{commodities})"})
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    int add(OrderDo order);
    
    @Update({
            "update `order` set "
                    + "`state` = #{state}, "
                    + "payment_id = #{paymentId} where order_id=#{orderId}"})
    int updateStateAndPayment(OrderDo order);
    
    @Insert({
            "insert into `order` ( "
                    + "order_id, "
                    + "machine_id, "
                    + "`state`, "
                    + "`type`, "
                    + "payment_id, "
                    + "commodities) values ( "
                    + "#{orderId}, "
                    + "#{machineId}, "
                    + "#{state}, "
                    + "#{type}, "
                    + "#{paymentId}, "
                    + "#{commodities}) on duplicate key update `state` = #{state}, payment_id = #{paymentId}"})
    int addOrUpdate(OrderDo order);
    
}
