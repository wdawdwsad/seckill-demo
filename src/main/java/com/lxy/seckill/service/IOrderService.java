package com.lxy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.seckill.pojo.Order;
import com.lxy.seckill.pojo.User;
import com.lxy.seckill.vo.GoodsVo;
import com.lxy.seckill.vo.OrderDetailVo;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface IOrderService extends IService<Order> {
    /**
     * 秒杀
     *
     * @param user
     * @param goods
     * @return
     */
    Order seckill(User user, GoodsVo goods);

    OrderDetailVo detail(Long orderId);
}