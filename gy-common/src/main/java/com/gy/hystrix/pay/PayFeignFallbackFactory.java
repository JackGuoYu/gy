package com.gy.hystrix.pay;

import com.gy.client.pay.PayClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 推荐使用这种后备方式，可以获取打印异常
 */
@Component
@Slf4j
public class PayFeignFallbackFactory implements FallbackFactory<PayClient> {
    @Override
    public PayClient create(Throwable throwable) {
        return payInfo -> {
            log.warn("addPay降级服务, err:{}", throwable.toString());
            return  "支付服务不可用!!!";
        };
    }
}
