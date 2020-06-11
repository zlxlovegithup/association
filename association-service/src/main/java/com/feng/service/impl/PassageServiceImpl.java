package com.feng.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.feng.dao.FileMapper;
import com.feng.dao.PassageMapper;
import com.feng.dao.PassageTypeMapper;
import com.feng.dto.PassageTypeDto;
import com.feng.dto.PassageFileDto;
import com.feng.entity.Passage;
import com.feng.entity.PassageType;
import com.feng.enums.ErrorEnum;
import com.feng.exception.BusinessException;
import com.feng.service.PassageService;
import com.feng.vo.PassagePageVo;
import com.feng.vo.PassageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zlx
 * @since 2020-05-08
 */
@Service
public class PassageServiceImpl implements PassageService {
    @Autowired
    private PassageMapper passageMapper;
    //社团类型
    @Autowired
    private PassageTypeMapper passageTypeMapper;
    @Autowired
    private FileMapper fileMapper;

    /**
     * 查找最近发布的n篇新闻或者新闻(根据passage_type_id进行区别是新闻还是通知)
     * @param n 查询的新闻或者通知数量
     * @param search 查询条件
     * @return
     */
    @Override
    public PassageVo getTopN(int n, Passage search) {

        PassageType passageType = null;
        //查询条件对象
        Wrapper<Passage> passageWrapper = new EntityWrapper<>();

        if (search != null && search.getPassageTypeId() != 0) {
            //根据文章类型id进行查询
            passageWrapper.eq("passage_type_id", search.getPassageTypeId());
            //先在社团类型表中根据id查询出文章类型
            passageType = passageTypeMapper.selectById(search.getPassageTypeId());
        }
        //SQL中orderby关键字跟的条件语句，可根据变更动态排序 isAsc:是否升序
        //将查询条件设置为： 根据数据库表passage中的publish_time字段进行降序排序
        passageWrapper.orderBy("publish_time", false);
        //开始分页: pageNum:当前页  n:查询n条记录
        PageHelper.startPage(1, n);
        List<Passage> passageList = passageMapper.selectList(passageWrapper);
        return new PassageVo(passageType, passageList);
    }


    @Override
    public PageInfo<PassageTypeDto> findPage(int pageNum, int pageSize, Passage search) {
        PageHelper.startPage(pageNum, pageSize);
        List<PassageTypeDto> passageList = passageMapper.findPassage(search);
        return new PageInfo<>(passageList);
    }

    @Override
    public PassagePageVo getPageWithTypeList(int pageNum, int pageSize, Integer passageTypeId) {
        PassageType passageType = null;
        Wrapper<Passage> passageWrapper = new EntityWrapper<>();
        if (passageTypeId != null) {
            passageWrapper.eq("passage_type_id", passageTypeId);
            passageType = passageTypeMapper.selectById(passageTypeId);
        }
        passageWrapper.orderBy("publish_time", false);
        PageHelper.startPage(pageNum, pageSize);
        List<Passage> passageList = passageMapper.selectList(passageWrapper);
        return new PassagePageVo(passageType, new PageInfo<>(passageList));
    }

    /**
     * 通过文章的id查询到文章的详细内容
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "passage")
    public PassageFileDto getInfoById(Integer id) {
        //通过文章id查询到社团的详细信息
        PassageFileDto passageFileDto = passageMapper.getInfoById(id);
        if (passageFileDto == null) {
            throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("文章不存在"));
        }
        return passageFileDto;
    }


    @Override
//    @CachePut(value = "passage", key = "#passage.id")
    public Passage add(Passage passage) {
        if (StringUtils.isEmpty(passage.getSource())) {
            passage.setSource("社团管理员");
        }
        if (StringUtils.isEmpty(passage.getPublisher())) {
            passage.setPublisher("社团管理员");
        }
        passageMapper.add(passage);
        return passage;
    }

    @Override
    @CacheEvict(value = "passage", key = "#id")
    public boolean deleteById(Serializable id) {
        Assert.notNull(id, "文章id不能为空");
        passageMapper.deleteById(id);
        return true;
    }

    @Override
    @CachePut(value = "passage", key = "#passage.id")
    public PassageFileDto updateInfoById(PassageFileDto passage) {
        passageMapper.updateById(passage);
        return  passage;
    }
}
