package com.gy.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gy.entry.PayInfo;
import com.gy.mapper.PayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayService extends ServiceImpl<PayMapper, PayInfo> {
    @Autowired
    private PayMapper payMapper;

    public Page<PayInfo> selectPayPage(Page<PayInfo> page){
        page.setRecords(payMapper.selectPayList(page));
        return page;
    }

    public Integer addPayInfo(PayInfo payInfo){
        log.info("=== 插入支付信息 pay:{}", JSON.toJSONString(payInfo));
        return payMapper.insert(payInfo);
    }
}
