package com.smartrm.smartrmmonolith.operation.adapter.erp;

import com.smartrm.smartrmmonolith.operation.adapter.erp.model.ERPNumberId;
import com.smartrm.smartrmmonolith.operation.adapter.erp.model.ERPPurchaseOrder;
import com.smartrm.smartrmmonolith.operation.adapter.erp.model.FPOOrderEntry;
import com.smartrm.smartrmmonolith.operation.domain.VendingMachineInstallOrder;
import com.smartrm.smartrmmonolith.operation.domain.remote.erp.DevicePurchaseService;
import com.smartrm.smartrmmonolith.operation.infrastructure.dataobject.VendingMachineModelDo;
import com.smartrm.smartrmmonolith.operation.infrastructure.mapper.VendingMachineModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;

/**
 * @author: yoda
 * @description: erp防腐层示例，调用采购服务申请安装售卖机
 */
@Service
public class DevicePurchaseServiceImpl implements DevicePurchaseService {
    
    private static String ERP_BUSSINESS_TYPE = ""; //业务类型
    private static String ERP_BILL_TYPE = "";  //单据类型
    
    @Autowired
    private VendingMachineModelMapper machineModelMapper;

//  private K3CloudApi client;
    
    @PostConstruct
    public void init() {
//    client = new K3CloudApi();
    }
    
    @Override
    public void placeInstallOrder(VendingMachineInstallOrder order) {
        VendingMachineModelDo modelData = machineModelMapper
                .selectByCode(order.getDeviceModel().code());
        FPOOrderEntry orderEntry = new FPOOrderEntry();
        orderEntry.setFEntryID(0);
        //物料信息
        orderEntry.setFMaterialId(new ERPNumberId(modelData.getMaterialId()));
        orderEntry.setFMaterialDesc(modelData.getMaterialDesc());
        orderEntry.setFProductType(modelData.getProductType());
        orderEntry.setFProcesser(new ERPNumberId(modelData.getProcessor()));
        orderEntry.setFBomId(new ERPNumberId(modelData.getBomId()));
        //数量
        orderEntry.setFStockQty(order.getCount());
        //TODO: 填充订单项目更多字段
        
        //创建采购订单
        ERPPurchaseOrder purchaseOrder = new ERPPurchaseOrder();
        purchaseOrder.setFBusinessType(ERP_BUSSINESS_TYPE);
        purchaseOrder.setFBillTypeID(new ERPNumberId(ERP_BILL_TYPE));
        purchaseOrder.setFPurchaseOrgId(new ERPNumberId(modelData.getPurchaseOrgId()));
        purchaseOrder.setFPurchaseDeptId(new ERPNumberId(modelData.getPurchaseDeptId()));
        purchaseOrder.setFPurchaserId(new ERPNumberId(modelData.getPurchaserId()));
        purchaseOrder.setFDate(order.getCreatedTime().format(DateTimeFormatter.BASIC_ISO_DATE));
        purchaseOrder.addEntry(orderEntry);
        //TODO: 填充采购订单更多字段
/*
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(purchaseOrder);
      String resultString = client.save("PUR_PurchaseOrder", json);
      JsonNode resultJson = objectMapper.readTree(resultString);
      if (!resultJson.get("Result").get("ResponseStatus").get("IsSuccess").asBoolean()) {
        throw new DomainException(OperationError.ERPError);
      } else {
        String id = resultJson.get("Result").get("ID").asText();
        String number = resultJson.get("Result").get("Number").asText();
        order.setOrderId(new InstallOrderId(id, number));
        //提交订单
        ObjectNode submitRequest = objectMapper.createObjectNode();
        submitRequest.putArray("Ids").add(id);
        submitRequest.putArray("Numbers").add(number);
        String submitResult = client
            .submit("PUR_PurchaseOrder", objectMapper.writeValueAsString(submitRequest));
        resultJson = objectMapper.readTree(submitResult);
        if (!resultJson.get("Result").get("ResponseStatus").get("IsSuccess").asBoolean()) {
          throw new DomainException(OperationError.ERPError);
        }
      }
    } catch (DomainException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
 */
    }
}
