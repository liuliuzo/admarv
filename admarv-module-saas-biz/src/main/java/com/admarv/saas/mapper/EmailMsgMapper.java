package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.EmailMsg;

@Mapper
public interface EmailMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailMsg record);

    List<EmailMsg> selectAll();

    int updateByPrimaryKey(EmailMsg record);
    
    EmailMsg selectOneByEntity(EmailMsg record);

    List<EmailMsg> selectByEntity(EmailMsg record);
}