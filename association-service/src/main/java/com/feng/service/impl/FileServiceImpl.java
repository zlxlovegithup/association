package com.feng.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feng.dao.FileMapper;
import com.feng.entity.File;
import com.feng.service.FileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlx
 * @since 2020-05-08
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Integer save(File file) {
        return fileMapper.save(file);
    }

    /**
     * 分页查询所有的文件
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    public PageInfo<File> getPage(int pageNum, int pageSize, File search) {
        //创建查询条件对象
        Wrapper<File> fileWrapper = new EntityWrapper<>();

        if (search != null && search.getFileTypeId() != null) {
            //根据文件的类型查询文件
            //普通图片
            //常用文档
            //社团文件
            //首页轮播图
            //文章内部图片
            //活动内部图片
            fileWrapper.eq("file_type_id", search.getFileTypeId());
        }
        if (search != null && !StringUtils.isEmpty(search.getFileName())) {
            //根据文件名称查询文件
            fileWrapper.like("file_name", search.getFileName());
        }

        //根据创建时间进行降序排序
        fileWrapper.orderBy("create_time",false);
        //设置分页 pageNum:当前页码 pageSize:每页显示的记录数量
        PageHelper.startPage(pageNum, pageSize);
        //根据 entity 条件，查询全部记录
        List<File> fileList = fileMapper.selectList(fileWrapper);
        System.out.println(fileList);
        return new PageInfo<>(fileList);
    }
}
