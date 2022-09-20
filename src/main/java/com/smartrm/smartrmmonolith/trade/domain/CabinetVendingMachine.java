package com.smartrm.smartrmmonolith.trade.domain;

import com.google.common.collect.Maps;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetDoorState;
import com.smartrm.smartrmmonolith.device.domain.event.CabinetVendingMachineLockedEvent;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.infracore.idgenerator.UniqueIdGeneratorUtil;
import com.smartrm.smartrmmonolith.trade.domain.remote.InventoryInfo;
import com.smartrm.smartrmmonolith.trade.domain.remote.TradeCommodityService;
import com.smartrm.smartrmmonolith.trade.domain.remote.TradeDeviceService;
import com.smartrm.smartrmmonolith.trade.infrastructure.TradeError;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: yoda
 * @description:
 */
public class CabinetVendingMachine {
    
    private long machineId;
    
    private CabinetDoorState state;
    
    private String curUserOpenId;
    
    private DomainEventBus eventBus;
    
    private CabinetVendingMachine() {
    
    }
    
    public static Builder Builder() {
        return new Builder();
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public CabinetDoorState getState() {
        return state;
    }
    
    public String getCurUserOpenId() {
        return curUserOpenId;
    }
    
    public void open(String openId, TradeDeviceService deviceService) {
        if (state == CabinetDoorState.Open) {
            throw new DomainException(TradeError.VendingMachineStateNotRight);
        }
        deviceService.openCabinetVendingMachine(machineId);
        state = CabinetDoorState.Open;
        this.curUserOpenId = openId;
    }
    
    public Order onLocked(CabinetVendingMachineLockedEvent event, TradeCommodityService commodityService) {
        if (state == CabinetDoorState.Locked) {
            throw new DomainException(TradeError.VendingMachineStateNotRight);
        }
        state = CabinetDoorState.Locked;
        Map<String, Integer> preInventory = Maps.newHashMap();
        event.getInventorySnapshotWhenOpen()
                .forEach(info -> preInventory.put(info.getCommodityId(), info.getCount()));
        List<StockedCommodity> commodities = commodityService.inventoryToCommodity(
                event.getInventoryWhenLock().stream()
                        .flatMap(info -> {
                            Integer count = preInventory.get(info.getCommodityId());
                            if (count != null && count > info.getCount()) {
                                return Stream.of(new InventoryInfo(info.getCommodityId(), count - info.getCount()));
                            } else {
                                return Stream.empty();
                            }
                        })
                        .collect(Collectors.toList())
        );
        return generateOrder(commodities);
    }
    
    private Order generateOrder(Collection<StockedCommodity> commodities) {
        Order order = Order.Builder().commodities(commodities)
                .orderId(UniqueIdGeneratorUtil.instance().nextId())
                .state(OrderState.Start)
                .type(OrderType.CabinetAutoDeduction)
                .machineId(this.machineId)
                .eventBus(eventBus)
                .build();
        if (order.totalAmount().doubleValue() <= 0) {
            throw new DomainException(TradeError.OrderAmountZero);
        }
        return order;
    }
    
    public static class Builder {
        
        private long machineId;
        private CabinetDoorState state;
        private String curUserOpenId;
        
        public Builder machineId(long machineId) {
            this.machineId = machineId;
            return this;
        }
        
        public Builder state(CabinetDoorState state) {
            this.state = state;
            return this;
        }
        
        public Builder curUserOpenId(String curUserOpenId) {
            this.curUserOpenId = curUserOpenId;
            return this;
        }
        
        public CabinetVendingMachine build() {
            CabinetVendingMachine machine = new CabinetVendingMachine();
            machine.machineId = this.machineId;
            machine.curUserOpenId = this.curUserOpenId;
            machine.state = this.state;
            return machine;
        }
    }
}
