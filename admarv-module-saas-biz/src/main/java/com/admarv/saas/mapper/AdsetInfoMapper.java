package com.admarv.saas.mapper;

import com.admarv.saas.model.AdsetInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdsetInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AdsetInfo record);

	AdsetInfo selectByPrimaryKey(Integer id);

	List<AdsetInfo> selectAll();

	int updateByPrimaryKey(AdsetInfo record);

	AdsetInfo selectOneByEntity(AdsetInfo record);

	List<AdsetInfo> selectByEntity(AdsetInfo record);
}