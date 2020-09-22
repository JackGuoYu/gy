package com.gy.hystrix.pay;

import com.gy.client.pay.PayClient;
import com.gy.entry.PayInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 常规的后备模式
 */
@Service
public class PayFeignFallback implements PayClient {
    @Override
    public String addPay(@RequestBody PayInfo payInfo) {
        return "调用服务失败！！！";
    }
}
