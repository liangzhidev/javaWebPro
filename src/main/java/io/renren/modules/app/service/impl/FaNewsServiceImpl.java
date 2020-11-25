package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.app.dao.FaNewsMapper;
import io.renren.modules.app.entity.FaNews;
import io.renren.modules.app.service.IFaNewsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻表 服务实现类
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@Service
public class FaNewsServiceImpl extends ServiceImpl<FaNewsMapper, FaNews> implements IFaNewsService {

	@Autowired
	public FaNewsMapper faNewsMapper;

	@Override
	public IPage<FaNews> listAll(IPage<FaNews> page) {
		List<FaNews> list=faNewsMapper.listAll(page);
		page.setRecords(list);
		return page;
	}

	@Override
	public FaNews getByIdNews(Integer id) {
		return faNewsMapper.getByIdNews(id);
	}
	

}
