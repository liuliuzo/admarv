package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.admarv.saas.model.LeadCntry;
import com.admarv.saas.model.LeadGen;

/**
 * 
 * @author liuliu
 *
 */
@Mapper
public interface LeadGenMapper {
    
    int deleteByPrimaryKey(Integer id);
    
    int insert(LeadGen record);
    
    LeadGen selectByPrimaryKey(Integer id);
    
    List<LeadGen> selectAll();
    
    int updateByPrimaryKey(LeadGen record);
    
    LeadGen selectOneByEntity(LeadGen record);
    
    List<LeadGen> selectByEntity(LeadGen record);
    
    int countByDate(@Param("date") String date, 
                    @Param("userId") String userId,
                    @Param("pageId") String pageId);

//    int countBetween(@Param("startDate") String startDate, 
//                     @Param("endDate") String endDate, 
//                     @Param("userId") String userId);
    
    int countBetweenByPageId(@Param("startDate") String startDate,
                             @Param("endDate") String endDate,
                             @Param("userId") String userId,
                             @Param("pageId") String pageId);
    
    /**
     * 根据用户查询总询盘数量
     */
    int countAllByUser(@Param("userId") String userId);
    
    /**
     * 根据用户和跟踪状态查询询盘数量
     */
	int countAllByUserAndFlwpStat(@Param("userId") String userId, @Param("flwpStat") String flwpStat);
    
    /**
     * 查询可见的询盘 = 属于自己的询盘 + 管理员分配给我的询盘
     */
    List<LeadGen> selectDispLeads(LeadGen record);
    
    /**
     * 按照国家地区
     */
    List<LeadCntry> countInquiriesByRegn(@Param("top") int top);
    
    /**
     * 按照询盘IDs获取询盘
     */
    List<LeadGen> selectByIdList(@Param("idList") List<Integer> idList);
}