package com.gy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gy.client.user.UserClient;
import com.gy.entry.PayInfo;
import com.gy.entry.User;
import com.gy.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private UserClient userClient;

    @PostMapping("/add")
    public String addPay(@RequestBody PayInfo payInfo){
        payService.addPayInfo(payInfo);
        return "修改成功";
    }

    @GetMapping("/feign/test")
    public Object feginTest(){
        String text = userClient.testConfigValue();
        return text;
    }
}
