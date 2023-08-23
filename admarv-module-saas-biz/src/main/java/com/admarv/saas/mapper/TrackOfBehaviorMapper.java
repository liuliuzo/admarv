package com.admarv.saas.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.admarv.saas.model.TrackOfBehavior;

@Mapper
public interface TrackOfBehaviorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrackOfBehavior record);

    TrackOfBehavior selectByPrimaryKey(Integer id);

    List<TrackOfBehavior> selectAll();

    int updateByPrimaryKey(TrackOfBehavior record);

    TrackOfBehavior selectOneByEntity(TrackOfBehavior record);

    List<TrackOfBehavior> selectByEntity(TrackOfBehavior record);
}