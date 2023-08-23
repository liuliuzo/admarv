package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.AdaccountDetail;

@Mapper
public interface AdaccountDetailMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AdaccountDetail record);

	AdaccountDetail selectByPrimaryKey(Integer id);

	List<AdaccountDetail> selectAll();

	int updateByPrimaryKey(AdaccountDetail record);

	AdaccountDetail selectOneByEntity(AdaccountDetail record);

	List<AdaccountDetail> selectByEntity(AdaccountDetail record);
}