package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.FbPosts;

@Mapper
public interface FbPostsMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(FbPosts record);

	FbPosts selectByPrimaryKey(Integer id);

	List<FbPosts> selectAll();

	int updateByPrimaryKey(FbPosts record);

	FbPosts selectOneByEntity(FbPosts record);

	List<FbPosts> selectByEntity(FbPosts record);
}