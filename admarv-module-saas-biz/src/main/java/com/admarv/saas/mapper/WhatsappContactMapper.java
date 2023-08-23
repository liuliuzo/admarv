package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.WhatsappContact;

@Mapper
public interface WhatsappContactMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WhatsappContact record);

    WhatsappContact selectByPrimaryKey(Integer id);

    List<WhatsappContact> selectAll();

    int updateByPrimaryKey(WhatsappContact record);
    
    WhatsappContact selectOneByEntity(WhatsappContact record);
}