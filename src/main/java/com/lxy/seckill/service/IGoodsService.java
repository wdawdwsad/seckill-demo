package com.lxy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.seckill.pojo.Goods;
import com.lxy.seckill.vo.GoodsVo;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface IGoodsService extends IService<Goods> {

    Object findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
