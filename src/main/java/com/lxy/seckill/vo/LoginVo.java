package com.lxy.seckill.vo;

import com.lxy.seckill.validator.IsMobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登录入参
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;
}
