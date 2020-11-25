package io.renren.modules.app.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.R;
import io.renren.modules.app.entity.FaSms;
import io.renren.modules.app.form.SmsVo;
import io.renren.modules.app.service.IFaSmsService;
import io.renren.modules.app.utils.IpUtil;
import io.renren.modules.app.utils.PhoneUtil;
import io.renren.modules.app.utils.SmsUtil;

@RestController
@RequestMapping("/sms")
public class SmsController {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IFaSmsService faSmsService;
	
	@PostMapping("send")
	public R send(@RequestBody SmsVo sms){
		if(!PhoneUtil.isMobileNO(sms.getMobile())) {
			return R.error("请输入正确的手机号");
		}
		String param=SmsUtil.smsRandom();
		boolean send=SmsUtil.sendSms(sms.getMobile(),param, request);
		if(send) {
			//存入数据库
			FaSms s=new FaSms();
			s.setCode(param);
			s.setEvent("sms");
			s.setMobile(sms.getMobile());
			s.setCreatetime((int)(System.currentTimeMillis()/1000));
			s.setIp(IpUtil.getIpFromRequest(request));
			QueryWrapper<FaSms> qw=new QueryWrapper<FaSms>(); 
			qw.eq("mobile", sms.getMobile()).eq("event", "sms");
			FaSms sm2=faSmsService.getOne(qw);
			if(sm2!=null) {
				s.setId(sm2.getId());
				faSmsService.updateById(s);
			}else {
				faSmsService.save(s);
			}
			return R.ok("验证码发送成功");
		}else {
			return R.error("验证码发送错误，请重试...");
		}
		
	}

}
