package com.lxy.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.seckill.pojo.Goods;
import com.lxy.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
