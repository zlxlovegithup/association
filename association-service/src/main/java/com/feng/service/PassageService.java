package com.feng.service;

import com.feng.dto.PassageTypeDto;
import com.feng.dto.PassageFileDto;
import com.feng.entity.Passage;
import com.feng.vo.PassagePageVo;
import com.feng.vo.PassageVo;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zlx
 * @since 2020-05-08
 */
public interface PassageService {
    PassageVo getTopN(int n, Passage search);


    PageInfo<PassageTypeDto> findPage(int pageNum, int pageSize, Passage search);

    PassagePageVo getPageWithTypeList(int pageNum, int pageSize, Integer passageTypeId);

    PassageFileDto getInfoById(Integer id);


    Passage add(Passage passage);

    PassageFileDto updateInfoById(PassageFileDto passage);

    boolean deleteById(Serializable id);
}
