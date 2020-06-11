package com.feng.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.feng.dto.ActivityFileDto;
import com.feng.dto.ActivityTypeDto;
import com.feng.entity.Activity;

import java.util.List;

/**
 * MyBatisPlus中的Mapper接口需要继承BaseMapper 接口
 * @author zlx
 * @since 2020-05-08
 */
public interface ActivityMapper extends BaseMapper<Activity> {
    List<ActivityTypeDto> findActivity(Activity activity);

    ActivityFileDto getInfoById(Integer id);

    Integer add(Activity activity);
}
