package io.renren.modules.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import io.renren.modules.app.entity.FaNews;

/**
 * <p>
 * 新闻表 服务类
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
public interface IFaNewsService extends IService<FaNews> {
	
	IPage<FaNews> listAll(IPage<FaNews> page);
	FaNews getByIdNews(Integer id);

}
