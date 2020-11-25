package io.renren.modules.app.entity;

import io.renren.modules.app.form.Page;
import lombok.Data;

@Data
public class Search extends Page {

	private int type;//1合格证 2企业 3产品
	private String key;//查询关键字
}
