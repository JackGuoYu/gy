package com.gy.client.pay;

import com.gy.entry.PayInfo;
import com.gy.hystrix.pay.PayFeignFallback;
import com.gy.hystrix.pay.PayFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="gy-pay", path = "pay", fallbackFactory = PayFeignFallbackFactory.class)
public interface PayClient {
    @RequestMapping(method = RequestMethod.POST, value= "/add")
    String addPay(@RequestBody PayInfo payInfo);

}
