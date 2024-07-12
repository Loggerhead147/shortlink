package com.allin.shortlink.project.service;

import com.allin.shortlink.project.dao.entity.ShortLinkDO;
import com.allin.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.allin.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.allin.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.allin.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.allin.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 分页查询短链接
     *
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询短链接分组数量
     *
     * @param requestParam 查询短链接分组数量请求参数
     * @return 查询短链接分组数量返回结果
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);
}
