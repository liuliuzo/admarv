package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.SysUserFbBind;

@Mapper
public interface SysUserFbBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserFbBind record);

    SysUserFbBind selectByPrimaryKey(Integer id);

    List<SysUserFbBind> selectAll();

    int updateByPrimaryKey(SysUserFbBind record);
    
    SysUserFbBind selectOneByEntity(SysUserFbBind record);

    List<SysUserFbBind> selectByEntity(SysUserFbBind record);
}