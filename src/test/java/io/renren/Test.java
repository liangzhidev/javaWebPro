package io.renren;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class Test {
	public static void main(String[] args) {
//		System.out.println((int)(System.currentTimeMillis()/1000));
//		String time=TimeStamp2Date("1605751046", "yyyy-MM-dd HH:mm:ss");
//		System.out.println(time);
//		String pwd=Md5Util.Md5("123456");
//		System.out.println(pwd);
		Date sec=new Date();
		int a=(int) (DateUtil.beginOfDay(sec).toInstant().toEpochMilli()/1000);
		int b=(int) (DateUtil.endOfDay(sec).toInstant().toEpochMilli()/1000);
		System.out.println(a+"================"+b+"=================="+(b-a));
	}

}
