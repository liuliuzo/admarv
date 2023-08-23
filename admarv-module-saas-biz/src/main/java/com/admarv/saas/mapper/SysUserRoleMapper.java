package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.SysUserRole;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
    
    SysUserRole selectOneByEntity(SysUserRole record);

    List<SysUserRole> selectByEntity(SysUserRole record);
}