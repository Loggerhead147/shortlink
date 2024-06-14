package com.allin.shortlink.project.dao.mapper;

import com.allin.shortlink.project.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短链接持节层
 */
@Mapper
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {
}
