package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.modules.app.entity.FaSms;

/**
 * <p>
 * 短信验证码表 服务类
 * </p>
 *
 * @author Yww
 * @since 2020-11-16
 */
public interface IFaSmsService extends IService<FaSms> {
	void Save(FaSms sms);

}
