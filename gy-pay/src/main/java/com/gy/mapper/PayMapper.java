package com.gy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gy.entry.PayInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper extends BaseMapper<PayInfo> {
    List<PayInfo> selectPayList(Page page);
}
