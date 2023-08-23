package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.WhatsappMsg;

@Mapper
public interface WhatsappMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WhatsappMsg record);

    WhatsappMsg selectByPrimaryKey(Integer id);

    List<WhatsappMsg> selectAll();

    int updateByPrimaryKey(WhatsappMsg record);
    
    WhatsappMsg selectOneByEntity(WhatsappMsg record);
    
    List<WhatsappMsg> selectByEntity(WhatsappMsg record);
}