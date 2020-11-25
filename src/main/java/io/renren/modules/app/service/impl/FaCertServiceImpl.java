package io.renren.modules.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.app.dao.FaCertMapper;
import io.renren.modules.app.entity.FaCert;
import io.renren.modules.app.service.IFaCertService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合格证表 服务实现类
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@Service
public class FaCertServiceImpl extends ServiceImpl<FaCertMapper, FaCert> implements IFaCertService {

	@Autowired
	public FaCertMapper faCertMapper;
	@Override
	public Map<String, Integer> getData(Map<String, Object> map) {
		return faCertMapper.getData(map);
	}

}
