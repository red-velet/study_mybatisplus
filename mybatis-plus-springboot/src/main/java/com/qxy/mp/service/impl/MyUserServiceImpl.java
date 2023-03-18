package com.qxy.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qxy.mp.dao.UserMapper;
import com.qxy.mp.domain.User;
import com.qxy.mp.service.MyUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: SayHello
 * @Date: 2023/3/18 20:55
 * @Introduction:
 */
@Service
public class MyUserServiceImpl extends ServiceImpl<UserMapper, User> implements MyUserService {
}
