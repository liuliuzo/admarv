package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.AdaccountPageInfo;

@Mapper
public interface AdaccountPageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdaccountPageInfo record);

    AdaccountPageInfo selectByPrimaryKey(Integer id);

    List<AdaccountPageInfo> selectAll();

    int updateByPrimaryKey(AdaccountPageInfo record);
    
    AdaccountPageInfo selectOneByEntity(AdaccountPageInfo record);

	List<AdaccountPageInfo> selectByEntity(AdaccountPageInfo record);
}