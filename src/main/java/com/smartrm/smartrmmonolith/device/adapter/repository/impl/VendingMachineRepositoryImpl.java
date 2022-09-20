package com.smartrm.smartrmmonolith.device.adapter.repository.impl;

import com.smartrm.smartrmmonolith.device.domain.DeviceModel;
import com.smartrm.smartrmmonolith.device.domain.InventoryInfo;
import com.smartrm.smartrmmonolith.device.domain.VendingMachine;
import com.smartrm.smartrmmonolith.device.domain.VendingMachineType;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetDoorState;
import com.smartrm.smartrmmonolith.device.domain.cabinet.CabinetVendingMachine;
import com.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceId;
import com.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceService;
import com.smartrm.smartrmmonolith.device.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmmonolith.device.domain.slot.NormalSlotVendingMachine;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotCommodityService;
import com.smartrm.smartrmmonolith.device.domain.slot.SlotVendingMachine;
import com.smartrm.smartrmmonolith.device.infrastructure.DeviceError;
import com.smartrm.smartrmmonolith.device.infrastructure.dataobject.VendingMachineDo;
import com.smartrm.smartrmmonolith.device.infrastructure.dataobject.VendingMachineInventoryDo;
import com.smartrm.smartrmmonolith.device.infrastructure.mapper.InventorySnapshotMapper;
import com.smartrm.smartrmmonolith.device.infrastructure.mapper.VendingMachineInventoryMapper;
import com.smartrm.smartrmmonolith.device.infrastructure.mapper.VendingMachineMapper;
import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
@Repository
public class VendingMachineRepositoryImpl implements VendingMachineRepository {
    
    private static Logger LOGGER = LoggerFactory.getLogger(VendingMachineRepositoryImpl.class);
    
    @Autowired
    private VendingMachineMapper vendingMachineMapper;
    
    @Autowired
    private VendingMachineInventoryMapper inventoryMapper;
    
    @Autowired
    private InventorySnapshotMapper inventorySnapshotMapper;
    
    @Autowired
    private DomainEventBus eventBus;
    
    @Autowired
    private IoTDeviceService iotService;
    
    @Autowired
    private SlotCommodityService slotCommodityService;
    
    private Map<Long, VendingMachine> vendingMachineMap = new ConcurrentHashMap<Long, VendingMachine>();
    
    @Override
    public VendingMachine getVendingMachine(long machineId) {
        VendingMachineDo vendingMachineDo = vendingMachineMapper.selectByMachineId(machineId);
        if (vendingMachineDo == null) {
            return null;
        }
        List<VendingMachineInventoryDo> inventory = inventoryMapper.selectByMachineId(machineId);
        List<InventoryInfo> inventoryInfos = inventory.stream()
                .map(d -> new InventoryInfo(d.getCommodityId(), d.getCount())).collect(
                        Collectors.toList());
        final DeviceModel model = DeviceModel.of(vendingMachineDo.getModel());
        if (model == DeviceModel.NormalSlot) {
            return NormalSlotVendingMachine.Builder()
                    .machineId(machineId)
                    .type(VendingMachineType.of(vendingMachineDo.getMachineType()))
                    .model(model)
                    .deviceId(new IoTDeviceId(vendingMachineDo.getIotProductKey(),
                            vendingMachineDo.getIotDeviceName()))
                    .inventoryInfo(inventoryInfos)
                    .iotService(iotService)
                    .slotCommodityService(slotCommodityService)
                    .build();
        } else if (model == DeviceModel.NormalCabinet) {
            inventory = inventorySnapshotMapper.selectByMachineId(machineId);
            List<InventoryInfo> inventorySnapshot = inventory.stream()
                    .map(d -> new InventoryInfo(d.getCommodityId(), d.getCount())).collect(
                            Collectors.toList());
            return CabinetVendingMachine.Builder().machineId(machineId)
                    .inventoryInfo(inventoryInfos)
                    .state(CabinetDoorState.of(vendingMachineDo.getCabinetDoorState()))
                    .inventoryWhenOpen(inventorySnapshot)
                    .eventBus(eventBus)
                    .build();
        } else {
            throw new DomainException(DeviceError.UnsupportedDeviceModel);
        }
    }
    
    @Override
    public SlotVendingMachine getSlotVendingMachineById(long machineId) {
        VendingMachineDo vendingMachineDo = vendingMachineMapper.selectByMachineId(machineId);
        if (vendingMachineDo == null
                || vendingMachineDo.getMachineType() != VendingMachineType.SLOT.code()) {
            return null;
        }
        List<VendingMachineInventoryDo> inventory = inventoryMapper.selectByMachineId(machineId);
        List<InventoryInfo> inventoryInfos = inventory.stream()
                .map(d -> new InventoryInfo(d.getCommodityId(), d.getCount())).collect(
                        Collectors.toList());
        DeviceModel model = DeviceModel.of(vendingMachineDo.getModel());
        if (model == null) {
            throw new DomainException(CommonError.PersistentDataError);
        }
        if (model == DeviceModel.NormalSlot) {
            return NormalSlotVendingMachine.Builder()
                    .machineId(machineId)
                    .type(VendingMachineType.of(vendingMachineDo.getMachineType()))
                    .model(model)
                    .deviceId(new IoTDeviceId(vendingMachineDo.getIotProductKey(),
                            vendingMachineDo.getIotDeviceName()))
                    .inventoryInfo(inventoryInfos)
                    .iotService(iotService)
                    .slotCommodityService(slotCommodityService)
                    .build();
        } else {
            throw new DomainException(DeviceError.UnsupportedDeviceModel);
        }
    }
    
    @Override
    public SlotVendingMachine getSlotVendingMachineByDeviceId(IoTDeviceId deviceId) {
        VendingMachineDo vendingMachineDo = vendingMachineMapper
                .selectByIoTKeys(deviceId.productKey(), deviceId.deviceName());
        long machineId = vendingMachineDo.getMachineId();
        if (vendingMachineDo == null
                || vendingMachineDo.getMachineType() != VendingMachineType.SLOT.code()) {
            return null;
        }
        List<VendingMachineInventoryDo> inventory = inventoryMapper.selectByMachineId(machineId);
        List<InventoryInfo> inventoryInfos = inventory.stream()
                .map(d -> new InventoryInfo(d.getCommodityId(), d.getCount())).collect(
                        Collectors.toList());
        DeviceModel model = DeviceModel.of(vendingMachineDo.getModel());
        if (model == null) {
            throw new DomainException(CommonError.PersistentDataError);
        }
        if (model == DeviceModel.NormalSlot) {
            return NormalSlotVendingMachine.Builder()
                    .machineId(machineId)
                    .type(VendingMachineType.of(vendingMachineDo.getMachineType()))
                    .model(model)
                    .deviceId(new IoTDeviceId(vendingMachineDo.getIotProductKey(),
                            vendingMachineDo.getIotDeviceName()))
                    .inventoryInfo(inventoryInfos)
                    .iotService(iotService)
                    .slotCommodityService(slotCommodityService)
                    .build();
        } else {
            throw new DomainException(DeviceError.UnsupportedDeviceModel);
        }
    }
    
    @Override
    public CabinetVendingMachine getCabinetVendingMachineById(long machineId) {
        VendingMachineDo vendingMachineDo = vendingMachineMapper.selectByMachineId(machineId);
        if (vendingMachineDo == null
                || vendingMachineDo.getMachineType() != VendingMachineType.CABINET.code()) {
            return null;
        }
        List<VendingMachineInventoryDo> inventory = inventoryMapper.selectByMachineId(machineId);
        List<InventoryInfo> inventoryInfos = inventory.stream()
                .map(d -> new InventoryInfo(d.getCommodityId(), d.getCount())).collect(
                        Collectors.toList());
        inventory = inventorySnapshotMapper.selectByMachineId(machineId);
        List<InventoryInfo> inventorySnapshot = inventory.stream()
                .map(d -> new InventoryInfo(d.getCommodityId(), d.getCount())).collect(
                        Collectors.toList());
        return CabinetVendingMachine.Builder().machineId(machineId).inventoryInfo(inventoryInfos)
                .state(CabinetDoorState.of(vendingMachineDo.getCabinetDoorState()))
//        .curUserOpenId(vendingMachineDo.getCurUserOpenId())
                .inventoryWhenOpen(inventorySnapshot)
                .eventBus(eventBus)
                .build();
    }
    
    @Override
    public void add(VendingMachine vendingMachine) {
        return;
    }
    
    
    @Override
    public void updateInventory(VendingMachine machine) {
        inventoryMapper.deleteByMachineId(machine.getMachineId());
        List<InventoryInfo> inventoryInfos = machine.getInventory();
        if (inventoryInfos.isEmpty()) {
            return;
        }
        inventoryMapper.insertBatch(inventoryInfos.stream().map(info -> {
            VendingMachineInventoryDo inventoryDo = new VendingMachineInventoryDo();
            inventoryDo.setCommodityId(info.getCommodityId());
            inventoryDo.setCount(info.getCount());
            inventoryDo.setMachineId(machine.getMachineId());
            return inventoryDo;
        }).collect(Collectors.toList()));
    }
    
    @Override
    public void clearInventorySnapshot(long machineId) {
        inventorySnapshotMapper.deleteByMachineId(machineId);
    }
    
    @Override
    public void saveInventorySnapshot(CabinetVendingMachine machine) {
        if (machine.getInventorySnapshotWhenOpen() != null &&
                !machine.getInventorySnapshotWhenOpen().isEmpty()) {
            inventorySnapshotMapper
                    .insertBatch(machine.getInventorySnapshotWhenOpen().stream().map(info -> {
                        VendingMachineInventoryDo inventoryDo = new VendingMachineInventoryDo();
                        inventoryDo.setMachineId(machine.getMachineId());
                        inventoryDo.setCommodityId(info.getCommodityId());
                        inventoryDo.setCount(info.getCount());
                        return inventoryDo;
                    }).collect(Collectors.toList()));
        }
    }
    
    @Override
    public void updateCabinetVendingMachineState(CabinetVendingMachine machine) {
        VendingMachineDo vendingMachineDo = new VendingMachineDo();
        vendingMachineDo.setMachineId(machine.getMachineId());
        vendingMachineDo.setMachineType(machine.getType().code());
        vendingMachineDo.setCabinetDoorState(machine.getDoorState().code());
        vendingMachineMapper.update(vendingMachineDo);
    }
}
