package com.lxy.seckill.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillMessage {
    private User user;
    private Long goodsId;
}