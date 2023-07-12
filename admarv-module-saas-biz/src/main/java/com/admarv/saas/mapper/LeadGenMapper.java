package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.LeadGen;

@Mapper
public interface LeadGenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeadGen record);

    LeadGen selectByPrimaryKey(Integer id);

    List<LeadGen> selectAll();

    int updateByPrimaryKey(LeadGen record);
    
    LeadGen selectByOneEntity(LeadGen record);
    
    List<LeadGen>  selectByEntity(LeadGen record);
}