package com.admarv.saas.mapper;

import com.admarv.saas.model.CompanyInfo;
import java.util.List;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Integer id);

    List<CompanyInfo> selectAll();

    int updateByPrimaryKey(CompanyInfo record);
}