package com.feng.controller;


import com.feng.entity.File;
import com.feng.entity.ResponseResult;
import com.feng.service.FileService;
import com.feng.util.ResponseResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zlx
 * @since 2020-05-06
 */
@RestController
@CrossOrigin
@RequestMapping("/files")
@Api(tags = "文件管理系统前台文章接口")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    @ApiOperation("根据条件分页查询所有文件")
    public ResponseResult list(File search,
                               @RequestParam(defaultValue = "1") int pageNum,  //默认当前第一页
                               @RequestParam(defaultValue = "8") int pageSize) { //默认每页8条数据
        //分页查询文件file表中的所有的文件
        PageInfo<File> filePageInfo = fileService.getPage(pageNum,pageSize,search);
        return ResponseResultUtil.renderSuccess(filePageInfo);
    }


}

