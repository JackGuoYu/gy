package com.gy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gy.entry.User;
import com.gy.kafka.producer.KafkaProducer;
import com.gy.kafka.topic.KafkaTopic;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/page")
    public Object selectPage(@RequestParam Integer pageIndex){
        Page page = new Page(pageIndex,3);
        page = userService.selectUserPage(page);
        return page;
    }

    @GetMapping("/list")
    public Object selectList(@RequestParam Integer pageIndex){
        Page page = new Page(pageIndex,3);
        page = userService.selectList(page);
        return page;
    }

    @PostMapping("/add")
    public Object addUser(@RequestBody User user){
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

    @GetMapping("/kafka/test")
    public Object sendMsg(@RequestParam String msg){
        kafkaProducer.send(KafkaTopic.TEST_TOPIC, msg);
        return "发送成功";
    }


}
