package com.smartrm.smartrmmonolith.operation.adapter.erp.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.operation.domain.VendingMachineInstallOrder;
import com.smartrm.smartrmmonolith.operation.domain.remote.DevicePurchaseService;
import com.smartrm.smartrmmonolith.operation.infrastructure.OperationError;
import com.smartrm.smartrmmonolith.operation.infrastructure.VendingMachineModelDo;
import com.smartrm.smartrmmonolith.operation.infrastructure.VendingMachineModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;

/**
 * @author: yoda
 * @description:
 */
@Service
public class DevicePurchaseServiceImpl implements DevicePurchaseService {
    
    @Autowired
    private VendingMachineModelMapper vendingMachineModelMapper;
    
    private K3CloudApi client;
    
    @PostConstruct
    public void init() {
        client = new K3CloudApi();
    }
    
    @Override
    public void placeInstallOrder(VendingMachineInstallOrder order) {
        VendingMachineModelDo modelData = vendingMachineModelMapper
                .selectByCode(order.getDeviceModel().code());
        FPOOrderEntry orderEntry = new FPOOrderEntry();
        orderEntry.setFMaterialId(new ERPNumberId(modelData.getMaterialId()));
        orderEntry.setFMaterialDesc(modelData.getMaterialDesc());
        orderEntry.setFProductType(modelData.getProductType());
        orderEntry.setFProcesser(new ERPNumberId(modelData.getProcessor()));
        orderEntry.setFBomId(new ERPNumberId(modelData.getBomId()));
        orderEntry.setFQty(order.getCount());
        orderEntry.setFEntryID(0);
        //TODO: 填充其他字段
        
        ERPPurchaseOrder purchaseOrder = new ERPPurchaseOrder();
        purchaseOrder.setFPurchaseOrgId(new ERPNumberId(modelData.getPurchaseOrgId()));
        purchaseOrder.setFPurchaseDeptId(new ERPNumberId(modelData.getPurchaseDeptId()));
        purchaseOrder.setFPurchaserId(new ERPNumberId(modelData.getPurchaserId()));
        purchaseOrder.setFBusinessType("");
        purchaseOrder.setFBillTypeID(new ERPNumberId(""));
        purchaseOrder.setFDate(order.getCreatedTime().format(DateTimeFormatter.BASIC_ISO_DATE));
        purchaseOrder.addEntry(orderEntry);
        //TODO: 填充其他字段
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(purchaseOrder);
            String resultString = client.save("PUR_PurchaseOrder", json);
            JsonNode result = objectMapper.readTree(resultString);
            if (!result.get("Result").get("ResponseStatus").get("IsSuccess").asBoolean()) {
                throw new DomainException(OperationError.ERPError);
            } else {
                String id = result.get("Result").get("ID").asText();
                String number = result.get("Result").get("Number").asText();
                ObjectNode request = objectMapper.createObjectNode();
                request.putArray("Ids").add(id);
                request.putArray("Numbers").add(number);
                resultString = client.submit("PUR_PurchaseOrder", objectMapper.writeValueAsString(request));
                result = objectMapper.readTree(resultString);
                if (!result.get("Result").get("ResponseStatus").get("IsSuccess").asBoolean()) {
                    throw new DomainException(OperationError.ERPError);
                }
            }
        } catch (DomainException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
