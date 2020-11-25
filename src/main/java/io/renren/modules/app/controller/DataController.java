package io.renren.modules.app.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.service.IFaCertService;

/**
 * 
 * @author Yww
 * 数据统计 前端控制器
 *
 */
@RestController
@RequestMapping("/data")
public class DataController {
	
	@Autowired
	public IFaCertService faCertService;
	/**
	 * 企业的今日开具数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("enter")
	public R getEnterDay() {
		Map<String, Object> param=new HashedMap();
		Map<String, Integer> se=DateUtils.getDateSE(new Date());
		param.putAll(se);
		param.put("lb", 2);
		JSONArray arr=new JSONArray();
		JSONObject obj=new JSONObject();
		obj.put("1", "蔬菜类");
		obj.put("2", "林果类");
		obj.put("3", "养殖水产类");
		obj.put("4", "畜禽类");
		obj.put("5", "禽蛋类");
		obj.put("6", "农副加工类");
		for (String key:obj.keySet()) {
			JSONObject o=new JSONObject();
			String name=obj.getString(key);
			o.put("id", key);
			o.put("name", name);
			param.put("pid", Integer.valueOf(key));
			Map<String, Integer> data=faCertService.getData(param);
			if(data!=null) {
				if(data.get("cou")!=null)o.put("cou", data.get("cou"));
				if(data.get("count")!=null)o.put("count", data.get("count"));
				arr.add(o);
			}
		}
		param=new HashedMap();
		param.put("data", arr);
		return R.ok(param);
	}
	/**
	 * 个人的今日开具数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("person")
	public R getPersonDay() {
		Map<String, Object> param=new HashedMap();
		Map<String, Integer> se=DateUtils.getDateSE(new Date());
		param.putAll(se);
		param.put("lb", 1);
		JSONArray arr=new JSONArray();
		JSONObject obj=new JSONObject();
		obj.put("1", "蔬菜类");
		obj.put("2", "林果类");
		obj.put("3", "养殖水产类");
		obj.put("4", "畜禽类");
		obj.put("5", "禽蛋类");
		obj.put("6", "农副加工类");
		for (String key:obj.keySet()) {
			JSONObject o=new JSONObject();
			String name=obj.getString(key);
			o.put("id", key);
			o.put("name", name);
			param.put("pid", Integer.valueOf(key));
			Map<String, Integer> data=faCertService.getData(param);
			if(data!=null) {
				if(data.get("cou")!=null)o.put("cou", data.get("cou"));
				if(data.get("count")!=null)o.put("count", data.get("count"));
				arr.add(o);
			}
		}
		param=new HashedMap();
		param.put("data", arr);
		return R.ok(param);
	}

}
