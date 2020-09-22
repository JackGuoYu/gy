package com.gy.client.user;

import com.gy.entry.PayInfo;
import com.gy.entry.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="gy-user", path = "user")
public interface UserClient {

    @PostMapping("/add")
    public String addUser(@RequestBody User user);

    @GetMapping("/list")
    Object selectList(Integer pageIndex);

    @GetMapping("/conf/value")
    String testConfigValue();

}
