package io.renren.modules.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.FaUsers;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Yww
 * @since 2020-11-14
 */
@Mapper
public interface FaUsersMapper extends BaseMapper<FaUsers> {

}
