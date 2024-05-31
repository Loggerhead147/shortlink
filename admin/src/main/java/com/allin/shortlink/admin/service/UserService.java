package com.allin.shortlink.admin.service;

import com.allin.shortlink.admin.dao.entity.UserDO;
import com.allin.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.allin.shortlink.admin.dto.resp.UserRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 用户存在返回 True，不存在返回 False
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     * @param requestParam 注册用户请求参数
     */
    void register(UserRegisterReqDTO requestParam);
}
