package com.allin.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录接口返回参数
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginRespDTO {
    /**
     * 用户Token
     */
    private String token;
}
