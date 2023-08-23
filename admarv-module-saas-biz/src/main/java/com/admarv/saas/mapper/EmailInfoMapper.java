package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.EmailInfo;

@Mapper
public interface EmailInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailInfo record);

    EmailInfo selectByPrimaryKey(Integer id);

    List<EmailInfo> selectAll();

    int updateByPrimaryKey(EmailInfo record);
    
    EmailInfo selectOneByEntity(EmailInfo record);

    List<EmailInfo> selectByEntity(EmailInfo record);
}