package io.renren.modules.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.date.DateUtil;
import io.renren.modules.app.entity.FaCert;
import io.renren.modules.app.entity.FaCertcodelog;
import io.renren.modules.app.entity.FaUsers;
import io.renren.modules.app.entity.Search;
import io.renren.modules.app.service.IFaCertService;
import io.renren.modules.app.service.IFaCertcodelogService;
import io.renren.modules.app.service.IFaUsersService;
import io.renren.common.utils.R;

@RestController
@RequestMapping("/cert")
public class SearchController {

	@Autowired
	public IFaCertService faCertService;
	@Autowired
	public IFaUsersService faUsersService;
	@Autowired
	public IFaCertcodelogService faCertcodelogService;
	
	@GetMapping("hello")
	public R hello() {
		return R.ok("hello...");
	}
	
	@PostMapping("search")
	public R Search(@RequestBody Search sea) {
		Map<String, Object> res=new HashMap<String, Object>();
		if(sea==null||StringUtils.isEmpty(sea.getKey())||sea.getType()==0) {
			return R.error("查询参数输入不正确，请确认...");
		}
		int type=sea.getType();//1合格证 2企业 3产品
		String key=sea.getKey();
		if(type==1) {
			QueryWrapper<FaCert> qw=new QueryWrapper<FaCert>();
			qw.like("qrcode", key);
			List<FaCert> list=faCertService.list(qw);
			if(list==null||(list!=null&&list.size()>1)) {
				return R.error("查询产品合格证错误...");
			}else {
				FaCert cert=list.get(0);
				res.put("cert", cert);
				return R.ok(res);
			}
		}else if(type==2) {
			IPage<FaUsers> page=new Page<FaUsers>(sea.getPage(),sea.getSize());
			QueryWrapper<FaUsers> qw=new QueryWrapper<FaUsers>();
			qw.like("name", key);
			page=faUsersService.page(page, qw);
			res.put("data", page);
			return R.ok(res);
		}else if(type==3) {
			IPage<FaCert> page=new Page<FaCert>(sea.getPage(),sea.getSize());
			QueryWrapper<FaCert> qw=new QueryWrapper<FaCert>();
			qw.like("product_name", key);
			qw.groupBy("users_name","users_address","product_name"); 
			page=faCertService.page(page, qw); 
			res.put("data", page);
			return R.ok(res);
		}
		return R.ok();
	}
	/**
	 * 查看企业的详情
	 * @param users
	 * @return
	 */
	@PostMapping("enterprise")
	public R enterprise(@RequestBody FaUsers users) {
		
		Map<String, Object> res=new HashMap<String, Object>();
		if(users==null||users.getId()==null) {
			return R.error("企业的id不能为空");
		}
		users=faUsersService.getById(users.getId());
		res.put("data", users);
		return R.ok(res);
	}
	
}
