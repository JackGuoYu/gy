package com.gy.entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName(value = "pay")
public class PayInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private BigDecimal amount;
    private String note;
}
