package com.admarv.saas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.admarv.saas.model.CustomerInfo;

/**
 * 
 * @author liuliu
 *
 */
@Mapper
public interface CustomerInfoMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(Integer id);

    List<CustomerInfo> selectAll();

    int updateByPrimaryKey(CustomerInfo record);
    
    CustomerInfo selectOneByEntity(CustomerInfo record);
    
    List<CustomerInfo> selectByEntity(CustomerInfo record);
    
    int updateByEntity(CustomerInfo record);
    
    int getCustomerInfoCount();
}