package io.renren.modules.app.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.renren.modules.app.entity.FaNews;

/**
 * <p>
 * 新闻表 Mapper 接口
 * </p>
 *
 * @author Yww
 * @since 2020-11-19
 */
@Mapper
public interface FaNewsMapper extends BaseMapper<FaNews> {

	@Select("SELECT n.*,t.name as newsName FROM fa_news n LEFT JOIN fa_news_class t ON n.news_class_id=t.id")
	@ResultType(FaNews.class)
	List<FaNews> listAll(IPage<FaNews> page);
	@Select("SELECT n.*,t.name AS newsName FROM fa_news n LEFT JOIN fa_news_class t ON n.news_class_id = t.id WHERE n.id = #{id}")
	@ResultType(FaNews.class)
	FaNews getByIdNews(@Param("id")Integer id);
}
