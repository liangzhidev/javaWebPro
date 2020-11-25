package io.renren.modules.app.controller;



import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import cn.hutool.core.util.IdcardUtil;
import io.renren.modules.app.entity.FaSms;
import io.renren.modules.app.entity.FaUsers;
import io.renren.modules.app.service.IFaSmsService;
import io.renren.modules.app.service.IFaUsersService;
import io.renren.modules.app.utils.Graphics2DUtil;
import io.renren.modules.app.utils.HttpClientUtil;
import io.renren.modules.app.utils.Md5Util;
import io.renren.modules.app.utils.PhoneUtil;
import io.renren.modules.app.utils.SmsUtil;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Yww
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/users")
public class FaUsersController {
	
	@Autowired
	private IFaSmsService faSmsService;
	@Autowired
	private IFaUsersService faUsersService;
	
	/**
	 * 用户注册
	 * @param users
	 * @return
	 */
	@PostMapping("register")
	public R<Object> Register(@RequestBody FaUsers users){
		String tel=users.getTel();
		String code=users.getCode();
		String idNum=users.getIdNum();
		String addr=users.getProductionaddress();
		if(StringUtils.isEmpty(tel)||StringUtils.isEmpty(code)||StringUtils.isEmpty(idNum)||StringUtils.isEmpty(addr)) {
			return R.failed("手机号或验证码或身份证号或生产地址不能为空...");
		}
		if(!IdcardUtil.isValidCard(idNum)) {
			return R.failed("您输入的身份证号码不正确，请重新输入...");
		}
		//判断手机号是否正确
		if(!PhoneUtil.isMobileNO(tel)) {
			return R.failed("输入的手机号格式不正确，请重新输入...");
		}
		//查询验证码比对
		QueryWrapper<FaSms> qw=new QueryWrapper<FaSms>();
		qw.eq("mobile", tel).eq("event", "sms");
		List<FaSms> list=faSmsService.list(qw);
		
		if(list==null||(list!=null && list.size()!=1)){
			return R.failed("验证码输入错误，请重新输入...");
		}else {
			FaSms sms=list.get(0);
			String coo=sms.getCode();
			if(!code.equals(coo)) {
				return R.failed("验证码输入错误，请重新输入...");
			}
		}
		faSmsService.remove(qw);
		//判断手机号是否已注册
		QueryWrapper<FaUsers> qwu=new QueryWrapper<FaUsers>();
		qwu.eq("tel", tel);
		List<FaUsers> uList=faUsersService.list(qwu);
		if(uList!=null&&uList.size()>0) {
			return R.failed("此手机号("+tel+")已经注册了，请勿重复注册");
		}
		
		//如果是企业注册，生产电子公章并上传到PHP后台
		String type=users.getLeibie();
		if(StringUtils.isNotEmpty(type)&&type.equals("2")) {
			String name=users.getName();
			if(StringUtils.isEmpty(name)) {
				return R.failed("企业名称不能为空...");
			}else {
				BufferedImage image=Graphics2DUtil.startGraphics2D(name,"","");
				String filePath=System.getProperty("user.dir")+File.separatorChar+"files";
				File file=new File(filePath);
				if(!file.exists()) {
					file.mkdirs();
				}
				filePath=filePath+File.separatorChar+"seal.png";
				//删除之前的文件
				File temp=new File(filePath);
				temp.deleteOnExit();
				try {
					ImageIO.write(image, "png", temp);//将其保存在filePath下，得有这个目录
					file=new File(filePath);
					//把电子签章保存到PHP后台去
					if(file.exists()) {
						// 创建一个表单
				        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
				        entityBuilder.addPart("email", new StringBody("", ContentType.create("text/plain", Consts.UTF_8)));
				        entityBuilder.addPart("filearg", new FileBody(file));
				        String url=SmsUtil.baseUri+"/index/file/uploads";
				        String body  = HttpClientUtil.send(url, "utf-8", entityBuilder);
				        if(StringUtils.isNotEmpty(body)) {
				        	JSONObject object=JSONObject.parseObject(body);
				        	if(object.getIntValue("code")==1) {
				        		object= object.getJSONObject("data");
				        		if(object!=null&&object.get("url")!=null) {
				        			String uri=object.getString("url");
				        			users.setSignatureImage(uri);
				        		}
				        		
				        	}else {
				        		return R.failed("保存电子签章发生错误，请稍后再试...");
				        	}
				        }
					}
				} catch (Exception e) {
					e.printStackTrace();
					return R.failed("生成电子签章发生错误，请稍后再试...");
				} 
				
			}
		}else {
			if(StringUtils.isEmpty(users.getSignatureImage())){
				return R.failed("名字签名图片地址不能为空...");
			}
		}
		//生成密码
		String pwd=Md5Util.Md5(tel.substring(5));
		users.setPassword(pwd);
		//保存用户信息
		users.setCreatetime((int)(System.currentTimeMillis()/1000));
		users.setUpdatetime((int)(System.currentTimeMillis()/1000));
		users.setRegWay("3");
		//判断是修改还是新增的
		if(users.getId()!=null) {
			faUsersService.updateById(users);
		}else {
			faUsersService.save(users);
		}
		return R.ok(true);
	}
	/**
	 * 用户登录
	 * @param users
	 * @return
	 */
	public R Login(@RequestBody FaUsers users) {
		Map<String, Object> res=new HashMap<String, Object>();
		if(users==null||users.getTel()==null) {
			return R.failed("手机号不能为空...");
		}
		String tel=users.getTel();
		if(users.getType()==1) {//1.密码登录
			if(users.getPassword()==null) {
				return R.failed("用密码登录时，密码不能为空...");
			}
			QueryWrapper<FaUsers> qw=new QueryWrapper<FaUsers>();
			qw.eq("tel", tel);
			FaUsers u=faUsersService.getOne(qw);
			if(u==null) {
				return R.failed("用户还未注册，请注册后使用...");
			}else if("1".equals(u.getStatus())) {
				return R.failed("您的账号已被锁定，如有问题，请联系当地的管理人员...");
			}else {
				String passwd=users.getPassword();
				String pwd=Md5Util.Md5(users.getPassword());
				if(!passwd.equals(pwd)) {
					return R.failed("手机号或密码错误，请重新登录");
				}else {
					u.setPassword("");
					res.put("user", u);
					return R.ok(res);
				}
			}
			
			
		}else {// 2.验证码登录
			if(users.getCode()==null) {
				return R.failed("用验证码登录时，验证码不能为空...");
			}
			String code=users.getCode();
			QueryWrapper<FaSms> qw=new QueryWrapper<FaSms>();
			qw.eq("mobile", tel).eq("event", "sms");
			List<FaSms> list=faSmsService.list(qw);
			
			if(list==null||(list!=null && list.size()!=1)){
				return R.failed("验证码输入错误，请重新输入...");
			}else {
				FaSms sms=list.get(0);
				String coo=sms.getCode();
				if(!code.equals(coo)) {
					return R.failed("验证码输入错误，请重新输入...");
				}
			}
			faSmsService.remove(qw);
			QueryWrapper<FaUsers> qw2=new QueryWrapper<FaUsers>();
			qw.eq("tel", tel);
			FaUsers u=faUsersService.getOne(qw2);
			if(u==null) {
				return R.failed("用户还未注册，请注册后使用...");
			}else if("1".equals(u.getStatus())) {
				return R.failed("您的账号已被锁定，如有问题，请联系当地的管理人员...");
			}else {
				u.setPassword("");
				res.put("user", u);
				return R.ok(res);
			}
		}
	}
	/**
	 * 获取用户信息
	 * @param users
	 * @return
	 */
	public R getMsg(@RequestBody FaUsers users) {
		Map<String, Object> res=new HashMap<String, Object>();
		if(users==null||users.getId()==null) {
			return R.failed("用户id不能为空...");
		}
		users=faUsersService.getById(users.getId());
		res.put("user", users);
		return R.ok(res);
	}
}
