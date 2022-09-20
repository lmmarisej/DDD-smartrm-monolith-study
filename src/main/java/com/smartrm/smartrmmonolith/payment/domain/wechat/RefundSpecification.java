package com.smartrm.smartrmmonolith.payment.domain.wechat;

import com.smartrm.smartrmmonolith.payment.domain.Payment;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yoda
 * @description: Specification(规格)模式的应用：退款Specification
 */
@Configuration
public class RefundSpecification {
    
    public boolean isSatisfiedBy(Payment payment) {
//    1、交易时间超过一年的订单无法提交退款
//    2、微信支付退款支持单笔交易分多次退款（不超50次），多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号
//    3、错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次
//    4、每个支付订单的部分退款次数不能超过50次
//    5、如果同一个用户有多笔退款，建议分不同批次进行退款，避免并发退款导致退款失败
//    6、申请退款接口的返回仅代表业务的受理情况，具体退款是否成功，需要通过退款查询接口获取结果
//    7、一个月之前的订单申请退款频率限制为：5000/min
        //TODO: 根据微信支付平台的约束决定是否要进行退款重试
        
        return true;
    }
    
}
