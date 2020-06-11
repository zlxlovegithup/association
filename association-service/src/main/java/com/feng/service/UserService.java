package com.feng.service;

import com.baomidou.mybatisplus.service.IService;
import com.feng.entity.User;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zlx
 * @since 2020-05-08
 */
public interface UserService{
    PageInfo<User> getUserPage(int num, int size,User search);

    User login(User user);

    List<User> getByAccount(String account);

    User add(User user);

    User updateById(User user);

    User getById(Serializable id);

   void  DeleteById(Serializable id);

    User getOneByAccount(String account);

    User register(User user,String rePassWord);
}
