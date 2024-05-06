package com.lxy.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.seckill.exception.GlobalException;
import com.lxy.seckill.mapper.OrderMapper;
import com.lxy.seckill.pojo.Order;
import com.lxy.seckill.pojo.SeckillGoods;
import com.lxy.seckill.pojo.SeckillOrder;
import com.lxy.seckill.pojo.User;
import com.lxy.seckill.service.IGoodsService;
import com.lxy.seckill.service.IOrderService;
import com.lxy.seckill.service.ISeckillGoodsService;
import com.lxy.seckill.service.ISeckillOrderService;
import com.lxy.seckill.utils.JsonUtil;
import com.lxy.seckill.vo.GoodsVo;
import com.lxy.seckill.vo.OrderDetailVo;
import com.lxy.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 秒杀
     *
     * @param user
     * @param goods
     * @return
     */
    @Override
    @Transactional
    public Order seckill(User user, GoodsVo goods) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new
                QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
        boolean seckillGoodsResult = seckillGoodsService.update(
                new UpdateWrapper<SeckillGoods>().setSql("stock_count = stock_count-1").eq("goods_id", goods.getId()).gt("stock_count", 0));
        if (seckillGoods.getStockCount() < 1) {
            //判断是否还有库存
            valueOperations.set("isStockEmpty:" + goods.getId(), "0");
            return null;
        }
        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);
        valueOperations.set("order:" + user.getId() + ":" + goods.getId(),
                JsonUtil.object2JsonStr(seckillOrder));
        return order;
    }

    /**
     * 订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDetailVo detail(Long orderId) {
        if (null == orderId) {
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo detail = new OrderDetailVo();
        detail.setGoodsVo(goodsVo);
        detail.setOrder(order);
        return detail;
    }
}