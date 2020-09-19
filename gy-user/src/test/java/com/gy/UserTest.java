package com.gy;

import com.alibaba.fastjson.JSON;
import com.gy.common.CacheConstant;
import com.gy.entry.User;
import com.gy.mapper.UserMapper;
import com.gy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void deleteUser() throws Exception{
        Integer result = userService.deleteById(15);
        System.out.println(result);
    }


    @Test
    public void setUserInfo(){
        String key = CacheConstant.getGyTestUserKey(1);
        User user = new User();
        user.setAge(12);
        user.setName("郭聪");
        user.setId(1);
        user.setFlag(1);
        String userInfo = JSON.toJSONString(user);
        redisTemplate.opsForValue().set(key, userInfo, CacheConstant.GY_TEST_USER_EXPIRE, TimeUnit.SECONDS);
    }

    @Test
    public void getUserInfo(){
        String key = CacheConstant.getGyTestUserKey(1);
        String userInfo = redisTemplate.opsForValue().get(key);
        System.out.println(userInfo);
    }


}
