package io.renren.modules.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.app.dao.FaSmsMapper;
import io.renren.modules.app.entity.FaSms;
import io.renren.modules.app.service.IFaSmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信验证码表 服务实现类
 * </p>
 *
 * @author Yww
 * @since 2020-11-16
 */
@Service
public class FaSmsServiceImpl extends ServiceImpl<FaSmsMapper, FaSms> implements IFaSmsService {
	@Autowired
	private FaSmsMapper faSmsMapper;

	@Override
	public void Save(FaSms sms) {
		faSmsMapper.insert(sms);
		
	}

}
