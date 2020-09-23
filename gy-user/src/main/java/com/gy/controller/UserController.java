package com.gy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gy.client.pay.PayClient;
import com.gy.entry.PayInfo;
import com.gy.entry.User;
import com.gy.kafka.producer.KafkaProducer;
import com.gy.kafka.topic.KafkaTopic;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
@RefreshScope   //此配置可检测刷新，在内存中更新配置
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private PayClient payClient;

    @GetMapping("/page")
    public Object selectPage(@RequestParam Integer pageIndex){
        Page page = new Page(pageIndex,3);
        page = userService.selectUserPage(page);
        return page;
    }

    @GetMapping("/list")
    public Page selectList(@RequestParam Integer pageIndex){
        Page page = new Page(pageIndex,3);
        page = userService.selectList(page);
        return page;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "修改成功";
    }

    /**
     * 逻辑删除
     * @return
     */
    @GetMapping("/deleteV2")
    public Object deleteV2User(@RequestParam Integer id){
        userService.deleteById(id);
        return "删除成功V2";
    }

    /**
     * kafka发送消息测试
     * @param msg
     * @return
     */
    @GetMapping("/kafka/test")
    public Object sendMsg(@RequestParam String msg){
        kafkaProducer.send(KafkaTopic.TEST_TOPIC,msg);
        return "发送成功";
    }

    @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private Integer age;

    @GetMapping("/conf/value")
    public String testConfigValue(){
        return this.name + "今年" + this.age + "岁";
    }

    /**
     * 远程调用测试
     * @return
     */
    @GetMapping("/feign/test")
    public String feginTest(){
        PayInfo payInfo = new PayInfo();
        payInfo.setAmount(new BigDecimal(12.24));
        payInfo.setNote("test");
        String msg = payClient.addPay(payInfo);
        return msg;
    }

    /**
     * 分布式事务测试
     */
    @GetMapping("/add/test")
    public String addData(){
        userService.addData();
        return "添加成功";
    }
}
