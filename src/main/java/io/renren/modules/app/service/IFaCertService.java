package io.renren.modules.app.service;


import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

import io.renren.modules.app.entity.FaCert;

/**
 * <p>
 * 合格证表 服务类
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
public interface IFaCertService extends IService<FaCert> {

	Map<String, Integer> getData(Map<String, Object> map);
}
