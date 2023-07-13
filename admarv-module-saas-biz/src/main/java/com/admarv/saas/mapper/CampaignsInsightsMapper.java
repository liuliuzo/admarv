package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.CampaignsInsights;

@Mapper
public interface CampaignsInsightsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CampaignsInsights record);

    CampaignsInsights selectByPrimaryKey(Integer id);

    List<CampaignsInsights> selectAll();

    int updateByPrimaryKey(CampaignsInsights record);
}