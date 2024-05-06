package com.lxy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.seckill.pojo.SeckillOrder;
import com.lxy.seckill.pojo.User;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}
