from locust import HttpUser, TaskSet, task
import json
import random


class Robot(TaskSet):

    machines = [1, 12, 13, 14, 15, 16, 17, 18, 19, 20]

    def buy(self, machineId, commodityId):
        response = self.client.post('/trade/slot/select', json={"platformType":"Wechat", "machineId":machineId, "commodityId":commodityId})
        if response.ok:
            result = json.loads(response.text)
            if result['code'] == 0:
                s = result['data']['codeUrl']
                s1 = s[s.find('orderId:')+len('orderId:'):]
                orderId = s1[:s1.find(';')]
                self.client.post('/mock/payment/pay/'+orderId)

    def chooseMachine(self):
        return Robot.machines[random.randint(0,9)]

    def releaseMachine(self, machineId):
        pass


    @task(100)
    def buy1000001(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000001')
        self.releaseMachine(machineId)

    @task(90)
    def buy1000002(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000002')
        self.releaseMachine(machineId)

    @task(80)
    def buy1000003(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000003')
        self.releaseMachine(machineId)


    @task(70)
    def buy1000004(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000004')
        self.releaseMachine(machineId)

    @task(60)
    def buy1000005(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000005')
        self.releaseMachine(machineId)

    @task(50)
    def buy1000006(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000006')
        self.releaseMachine(machineId)

    @task(40)
    def buy1000007(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000007')
        self.releaseMachine(machineId)

    @task(30)
    def buy1000008(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000008')
        self.releaseMachine(machineId)

    @task(20)
    def buy1000009(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000009')
        self.releaseMachine(machineId)

    @task(10)
    def buy1000010(self):
        machineId = self.chooseMachine()
        self.buy(machineId, '1000010')
        self.releaseMachine(machineId)

class Test(HttpUser):
    tasks = [Robot]
    host = "http://localhost:10081"

