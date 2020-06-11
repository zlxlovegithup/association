package com.feng.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.feng.dao.UserMapper;
import com.feng.entity.User;
import com.feng.enums.ErrorEnum;
import com.feng.exception.BusinessException;
import com.feng.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class UserServiceImpl implements UserService {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> getUserPage(int num, int size, User search) {
        PageHelper.startPage(num, size);
        Wrapper<User> userWrapper = new EntityWrapper();
        List<User> userList = userMapper.selectList(userWrapper);
        return new PageInfo<>(userList);
    }

    /**
     * 进行登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        //获取账号(邮箱)
        List<User> userList = getByAccount(user.getAccount());
        //如果账号为空则抛出异常
        if (userList.isEmpty()) {
            //用户名错误！
            throw new BusinessException(ErrorEnum.USER_NAME_ERROR);
        }
        //拿到账号
        User loginUser = userList.get(0);
        //对比用户的输入的密码和数据库中的密码是否一致
        if (!loginUser.getPassword().equals(user.getPassword())) {
            //不一致则抛出异常"用户密码错误"
            throw new BusinessException(ErrorEnum.USER_PASSWORD_ERROR);
        }
        //将账号返回出去
        return loginUser;
    }

    /**
     * 根据账号去查询这个用户
     * @param account
     * @return
     */
    @Override
    public List<User> getByAccount(String account) {
        //设置查询条件
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        //根据账号查询用户(账号为邮箱)
        userEntityWrapper.eq("account", account);
        //根据条件，查询全部记录
        return userMapper.selectList(userEntityWrapper);
    }

    @Override
    public User getOneByAccount(String account) {
        List<User> userList = getByAccount(account);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
          return   userList.get(0);
        }
    }

    @Override
    public User add(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    @CachePut(value = "user", key = "#user.id")
    public User updateById(User user) {
        userMapper.updateById(user);
        return user;
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getById(Serializable id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("该用户不存在"));
        }
        return user;
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void DeleteById(Serializable id) {
        userMapper.deleteById(id);
    }

    /**
     * 用户注册
     * @param user
     * @param rePassword
     * @return
     */
    @Override
    public User register(User user, String rePassword) {
//        Assert.notNull(rePassword,"确认密码不能为空！");
        List<User> userList = getByAccount(user.getAccount());
        if (!userList.isEmpty()) {
            User user1 = userList.get(0);
            if (user1.getActive()) {
                throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("邮箱已经激活，请直接登录"));
            } else {
                throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("请尽快激活邮箱"));
            }
        }
        if (user.getPassword().equals(rePassword)) {
            throw new BusinessException(ErrorEnum.USER_RE_PASSWORD_ERROR);
        }
        //将注册成功了的用户保存到数据库之中
        userMapper.insert(user);

        return user;
    }
}
