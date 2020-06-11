package com.feng.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.feng.entity.File;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zlx
 * @since 2020-04-08
 */
public interface FileMapper extends BaseMapper<File> {
    Integer save(File file);
}
