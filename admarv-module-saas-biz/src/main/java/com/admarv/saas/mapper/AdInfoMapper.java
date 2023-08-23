package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.AdInfo;

@Mapper
public interface AdInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AdInfo record);

	AdInfo selectByPrimaryKey(Integer id);

	List<AdInfo> selectAll();

	int updateByPrimaryKey(AdInfo record);

	AdInfo selectOneByEntity(AdInfo record);

	List<AdInfo> selectByEntity(AdInfo record);
}