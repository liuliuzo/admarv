package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.CampaignInfo;

@Mapper
public interface CampaignInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CampaignInfo record);

    CampaignInfo selectByPrimaryKey(Integer id);

    List<CampaignInfo> selectAll();

    int updateByPrimaryKey(CampaignInfo record);
    
    CampaignInfo selectOneByEntity(CampaignInfo record);

	List<CampaignInfo> selectByEntity(CampaignInfo record);
}