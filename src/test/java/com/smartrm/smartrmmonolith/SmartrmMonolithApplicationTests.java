package com.smartrm.smartrmmonolith;

import com.smartrm.smartrmmonolith.commodity.adapter.file.CommodityRepositoryJsonReader;
import com.smartrm.smartrmmonolith.commodity.adapter.file.CommodityRepositoryJsonWriter;
import com.smartrm.smartrmmonolith.commodity.application.service.CommodityService;
import com.smartrm.smartrmmonolith.device.domain.VendingMachineType;
import com.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.smartrm.smartrmmonolith.device.infrastructure.mapper.InventorySnapshotMapper;
import com.smartrm.smartrmmonolith.device.infrastructure.mapper.VendingMachineInventoryMapper;
import com.smartrm.smartrmmonolith.infracore.event.DomainEventBus;
import com.smartrm.smartrmmonolith.trade.application.AppTradeService;
import com.smartrm.smartrmmonolith.user.infrastructure.mapper.UserAccountMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartrmMonolithApplicationTests {
    
    Logger LOGGER = LoggerFactory.getLogger(SmartrmMonolithApplicationTests.class);
    @Autowired
    CommodityService commodityService;
    @Autowired
    AppTradeService tradeService;
    @Autowired
    private CommodityRepositoryJsonReader commodityRepositoryReader;
    @Autowired
    private CommodityRepositoryJsonWriter commodityRepositoryWriter;
    @Autowired
    @Qualifier("simpleEventBusImpl")
    private DomainEventBus domainEventBus;
//
//  @Test
//  void testVendingMachineCommodityList() {
//    CommodityInfoDto info1 = commodityService.getCommodityDetail("1000001");
//    CommodityInfoDto info2 = commodityService.getCommodityDetail("1000002");
//    StockedCommodity commodity1 = new StockedCommodity(info1.getCommodityId(),
//        info1.getCommodityName(), info1.getImageUrl(), info1.getPrice(), 10);
//    System.out.println(commodity1.hashCode());
//    StockedCommodity commodity2 = new StockedCommodity(info2.getCommodityId(),
//        info2.getCommodityName(), info2.getImageUrl(), info2.getPrice(), 10);
//    System.out.println(commodity2.hashCode());
//    Assertions.assertNotEquals(commodity1, commodity2);
//    StockedCommodity commodity3 = commodity1.withCount(1);
//    System.out.println(commodity3.hashCode());
//    Assertions.assertNotEquals(commodity1, commodity3);
//    Assertions.assertEquals(
//        new StockedCommodity(
//            info1.getCommodityId(),
//            info1.getCommodityName(),
//            info1.getImageUrl(),
//            info1.getPrice(), 1),
//        commodity3);
//    List<StockedCommodity> list1 = Lists.newArrayList(commodity1, commodity2);
//    List<StockedCommodity> list2 = Lists.newArrayList(commodity2, commodity3);
//    List<StockedCommodity> list3 = Lists
//        .newArrayList(new StockedCommodity(commodity2), new StockedCommodity(commodity3));
//    VendingMachineCommodityList l1 = new VendingMachineCommodityList(1, list1);
//    VendingMachineCommodityList l2 = new VendingMachineCommodityList(2, list2);
//    VendingMachineCommodityList l3 = new VendingMachineCommodityList(2, list3);
//    Assertions.assertNotEquals(l1, l2);
//    Assertions.assertEquals(l2, l3);
//    Assertions.assertEquals(l2.hashCode(), l3.hashCode());
//    System.out.println(l1.hashCode());
//    System.out.println(l2.hashCode());
//    System.out.println(l3.hashCode());
//  }
//
//  @Test
//  void testOrder() {
//    CommodityInfoDto info1 = commodityService.getCommodityDetail("1000001");
//    CommodityInfoDto info2 = commodityService.getCommodityDetail("1000002");
//    StockedCommodity commodity1 = new StockedCommodity(info1.getCommodityId(),
//        info1.getCommodityName(), info1.getImageUrl(), info1.getPrice(), 10);
//    StockedCommodity commodity2 = new StockedCommodity(info2.getCommodityId(),
//        info2.getCommodityName(), info2.getImageUrl(), info2.getPrice(), 10);
//    List<StockedCommodity> list1 = Lists.newArrayList(commodity1, commodity2);
//    Order order = Order.Builder().commodities(list1).machineId(1).state(OrderState.Start).type(
//        OrderType.SlotQrScanePaid).eventBus(domainEventBus).build();
//    System.out.println(order.totalAmount());
//    Assertions.assertEquals(order.totalAmount(),
//        info1.getPrice().multiply(new BigDecimal(10))
//            .add(info2.getPrice().multiply(new BigDecimal(10))));
//  }
//
//  @Test
//  void commodityLoadDump() throws IOException {
//    commodityRepositoryReader.load();
//    commodityRepositoryWriter.dumpRepository();
//  }
//
//  @Test
//  void testEventBus() {
//
//    domainEventBus.register(new TestEventHandler());
//    domainEventBus.post(new TestEvent());
////    domainEventBus.post(new TestEvent1());
//  }
//
////  @Autowired
////  private UniqueIdGenerator idGenerator;
//
//  @Test
//  void testUid() {
//    for (int i = 0; i < 10; i++) {
//      System.out.println("uid:" + UniqueIdGeneratorUtil.instance().nextId());
//    }
//  }
    @Autowired
    private VendingMachineInventoryMapper vendingMachineInventoryMapper;
    @Autowired
    private UserAccountMapper accountMapper;
    @Autowired
    private InventorySnapshotMapper inventorySnapshotMapper;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    public void testScheduler() {
        DeviceFailureEvent event = new DeviceFailureEvent();
        event.setMachineType(VendingMachineType.SLOT);
        event.setMachineId(1L);
        event.setOrderId(25L);
        tradeService.onDeviceFailure(event);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//  @Test
//  void testMapper() {
//    List<VendingMachineInventoryDo> inventory = vendingMachineInventoryMapper.selectByMachineId(1L);
//    for (VendingMachineInventoryDo d : inventory) {
//      LOGGER.info("{},{},{}", d.getMachineId(), d.getCommodityId(), d.getCount());
//      d.setCount(d.getCount() - 1);
//      vendingMachineInventoryMapper.update(d);
//    }
//    inventory = vendingMachineInventoryMapper.selectByMachineId(1L);
//    for (VendingMachineInventoryDo d : inventory) {
//      LOGGER.info("{},{},{}", d.getMachineId(), d.getCommodityId(), d.getCount());
//      vendingMachineInventoryMapper.delete(d);
//    }
//    inventory = vendingMachineInventoryMapper.selectByMachineId(1L);
//    LOGGER.info("inventory:{}", inventory.size());
//
//    UserAccountDo accountDo = new UserAccountDo();
//    accountDo.setWxOpenId("onqOjjmM1tad-3ROpncN-yUfa6ua");
//    LOGGER.info("insert account ret:{}", accountMapper.insert(accountDo));
//    LOGGER.info("account:{},{},{},{}", accountDo.getAccountId(), accountDo.getContractId(),
//        accountDo.getWxOpenId(), accountDo.getWxUnionId());
//    accountDo.setContractId("Wx15463511252015071056489715");
//    accountDo.setWxUnionId("onqOjjmM1tad-3ROpncN-yUfa6ub");
//    LOGGER.info("update account ret:{}", accountMapper.update(accountDo));
//    LOGGER.info("account:{},{},{},{}", accountDo.getAccountId(), accountDo.getContractId(),
//        accountDo.getWxOpenId(), accountDo.getWxUnionId());
//
//    List<VendingMachineInventoryDo> inventoryDos = Lists.newArrayList();
//    for (int i = 0; i < 10; i++) {
//      VendingMachineInventoryDo inventoryDo = new VendingMachineInventoryDo();
//      inventoryDo.setMachineId(1000001);
//      inventoryDo.setCommodityId(UUID.randomUUID().toString());
//      inventoryDo.setCount(1);
//      inventoryDos.add(inventoryDo);
//    }
//    inventorySnapshotMapper.insertBatch(inventoryDos);
//    inventoryDos = inventorySnapshotMapper.selectByMachineId(1000001L);
//    for (VendingMachineInventoryDo inventoryDo : inventoryDos) {
//      LOGGER.info("inventory snapshot:{},{},{}", inventoryDo.getMachineId(),
//          inventoryDo.getCommodityId(), inventoryDo.getCount());
//    }
//    inventorySnapshotMapper.deleteByMachineId(1000001L);
//
//
//  }

}
