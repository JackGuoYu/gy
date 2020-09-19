package com.gy.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.gy.entry.User;
import com.gy.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    public Page<User> selectUserPage(Page<User> page){
        page.setRecords(userMapper.selectUserList(page));
        return page;
    }

    public Integer addUser(User user){
        log.info("=== 插入用户信息 user:{}", JSON.toJSONString(user));
        return userMapper.insert(user);
    }

    public Integer deleteById(Integer id){
        return userMapper.deleteById(id);
    }

    public Page<User> selectList(Page<User> page){
        Page<User> userIPage = (Page<User>) userMapper.selectPage(page,null);
        return userIPage;
    }

}
