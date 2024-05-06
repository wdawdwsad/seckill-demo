package com.lxy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.seckill.pojo.User;
import com.lxy.seckill.vo.LoginVo;
import com.lxy.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface IUserService extends IService<User> {

    /**
     * 功能描述: 登录
     */
    RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getByUserTicket(String userTicket, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新密码
     *
     * @param userTicket
     * @param id
     * @param password
     * @return
     */
    RespBean updatePassword(String userTicket, Long id, String password);
}
