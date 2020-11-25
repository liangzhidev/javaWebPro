package io.renren.modules.app.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.renren.modules.app.entity.FaCert;

/**
 * <p>
 * 合格证表 Mapper 接口
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@Mapper
public interface FaCertMapper extends BaseMapper<FaCert> {
	
	@Select("SELECT SUM(c.pro_number) AS cou,SUM(LEFT(c.product_unit_name,INSTR(c.product_unit_name,'公斤')-1)*c.pro_number) AS count FROM fa_cert c LEFT JOIN fa_users u ON u.id_num=c.users_id_num "
			+ "WHERE u.leibie=#{map.lb} AND c.createtime BETWEEN 0 AND 9999999999 AND c.product_name IN (SELECT t.name FROM fa_product t WHERE t.product_class_id IN (SELECT pc.id FROM fa_product_class pc "
			+ "WHERE pc.pid=#{map.pid}))")//#{map.be} AND #{map.en}
	@ResultType(Map.class)
	Map<String, Integer> getData(@Param("map")Map<String, Object> map);

}
