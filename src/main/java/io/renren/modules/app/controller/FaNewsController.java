package io.renren.modules.app.controller;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.renren.common.utils.R;
import io.renren.modules.app.entity.FaNews;
import io.renren.modules.app.form.Page;
import io.renren.modules.app.service.IFaNewsService;

/**
 * <p>
 * 新闻表 前端控制器
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/news")
public class FaNewsController {
	
	@Autowired
	public IFaNewsService faNewsService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("listAll")
	public R getallNews(@RequestBody Page p) {
		IPage<FaNews> page=new com.baomidou.mybatisplus.extension.plugins.pagination.Page<FaNews>(p.getPage(),p.getSize());
		Map<String, Object> res=new HashedMap();
		page=faNewsService.listAll(page);
		res.put("data", page);
		return R.ok(res);
	}
	@SuppressWarnings("unchecked")
	@PostMapping("getBYId")
	public R getOne(@RequestBody FaNews news) {
		Map<String, Object> res=new HashedMap();
		if(news==null||news.getId()==null) {
			return R.error("新闻id不能为空...");
		}
		news=faNewsService.getByIdNews(news.getId());
		res.put("data", news);
		return R.ok(res);
	}

}
