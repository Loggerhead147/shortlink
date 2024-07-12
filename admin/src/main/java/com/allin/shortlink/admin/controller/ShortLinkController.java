package com.allin.shortlink.admin.controller;

import com.allin.shortlink.admin.common.convention.result.Result;
import com.allin.shortlink.admin.remote.ShortLinkRemoteService;
import com.allin.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.allin.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.allin.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.allin.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.allin.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接后管控制层
 */
@RestController
public class ShortLinkController {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

    /**
     * 查询短链接分组内数量
     */
    @GetMapping("/api/short-link/admin/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return shortLinkRemoteService.listGroupShortLinkCount(requestParam);
    }

}
