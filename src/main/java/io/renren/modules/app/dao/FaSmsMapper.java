package io.renren.modules.app.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.FaSms;

/**
 * <p>
 * 短信验证码表 Mapper 接口
 * </p>
 *
 * @author Yww
 * @since 2020-11-16
 */
@Mapper
public interface FaSmsMapper extends BaseMapper<FaSms> {

}
