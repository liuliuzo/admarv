package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.SysRole;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(String id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
    
    SysRole selectOneByEntity(SysRole record);

    List<SysRole> selectByEntity(SysRole record);
}