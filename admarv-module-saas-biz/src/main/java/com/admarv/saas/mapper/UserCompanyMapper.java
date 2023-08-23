package com.admarv.saas.mapper;

import com.admarv.saas.model.UserCompany;
import java.util.List;

public interface UserCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCompany record);

    UserCompany selectByPrimaryKey(Integer id);

    List<UserCompany> selectAll();

    int updateByPrimaryKey(UserCompany record);
}