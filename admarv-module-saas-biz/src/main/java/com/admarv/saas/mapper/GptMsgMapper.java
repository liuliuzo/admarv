package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.GptMsg;

@Mapper
public interface GptMsgMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GptMsg record);

	GptMsg selectByPrimaryKey(Integer id);

	List<GptMsg> selectAll();

	int updateByPrimaryKey(GptMsg record);

	GptMsg selectOneByEntity(GptMsg record);

	List<GptMsg> selectByEntity(GptMsg record);
}