package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.UserAuths;

@Mapper
public interface UserAuthsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuths record);

    UserAuths selectByPrimaryKey(Integer id);

    List<UserAuths> selectAll();

    int updateByPrimaryKey(UserAuths record);
    
    UserAuths selectOneByEntity(UserAuths record);

    List<UserAuths> selectByEntity(UserAuths record);
}