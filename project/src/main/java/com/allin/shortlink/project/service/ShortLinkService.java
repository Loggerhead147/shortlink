package com.allin.shortlink.project.service;

import com.allin.shortlink.project.dao.entity.ShortLinkDO;
import com.allin.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.allin.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @param requestParam 短链接创建请求参数
     * @return 短链接信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
