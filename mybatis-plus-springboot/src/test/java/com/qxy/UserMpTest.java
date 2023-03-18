package com.qxy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qxy.mp.MybatisPlusApplication;
import com.qxy.mp.dao.UserMapper;
import com.qxy.mp.domain.User;
import com.qxy.mp.service.MyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/18 17:38
 * @Introduction:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class UserMpTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testDemo() {
        System.out.println("hello");
    }

    @Test
    public void testFindById() {
        User user = userMapper.selectById(1L);
        System.out.println("user = " + user);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName123("jack&rose");
        user.setAge(18);
        user.setEmail("blue@qq.com");
        int affectRows = userMapper.insert(user);
        System.out.println("affectRows = " + affectRows);
    }

    @Test
    public void testDelete() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack&rose");
        int i = userMapper.deleteByMap(map);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(6L);
        user.setName123("jack&rose");
        user.setAge(18);
        user.setEmail("blue@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    public void testSelectByPage() {
        long currentPage = 1L;
        long pageSize = 2L;
        IPage<User> page = new Page<>(currentPage, pageSize);
        userMapper.selectPage(page, null);

        List<User> records = page.getRecords();
        long total = page.getTotal();
        long pages = page.getPages();

        System.out.println("总页数:> " + pages);
        System.out.println("总数据条数:> " + total);
        System.out.println("分页数据:> " + records);
    }

    @Test
    public void testSimpleQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age", 18, 22);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println("userList = " + userList);
    }

    @Test
    public void testSimpleQueryWrapperOr() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 18).or().gt("age", 25);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println("userList = " + userList);
    }

    @Test
    public void testSimpleQueryWrapperLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "jack");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println("userList = " + userList);
    }

    @Test
    public void testSimpleQueryWrapperOrder() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20).orderByDesc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println("userList = " + userList);
    }

    @Test
    public void testSimpleQueryWrapperField() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20).orderByDesc("id").select("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println("userList = " + userList);
    }

    @Test
    public void testSimpleQueryWrapperAndPage() {
        //当前页码
        int current = 1;
        //每页显示条数
        int size = 2;
        //1. 构建分页对象
        Page<User> page = new Page<>(current, size);
        //2. 构建条件对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20).orderByDesc("id").select("id");
        userMapper.selectPage(page, queryWrapper);

        List<User> records = page.getRecords();
        long total = page.getTotal();
        long pages = page.getPages();

        System.out.println("总页数:> " + pages);
        System.out.println("总数据条数:> " + total);
        System.out.println("分页数据:> " + records);
    }

    @Test
    public void testSimpleLambdaQueryWrapperAndPage() {
        //当前页码
        int current = 1;
        //每页显示条数
        int size = 2;
        //1. 构建分页对象
        Page<User> page = new Page<>(current, size);
        //2. 构建条件对象
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .gt(User::getAge, 20)
                .orderByDesc(User::getId)
                .select(User::getId);
        userMapper.selectPage(page, queryWrapper);

        List<User> records = page.getRecords();
        long total = page.getTotal();
        long pages = page.getPages();

        System.out.println("总页数:> " + pages);
        System.out.println("总数据条数:> " + total);
        System.out.println("分页数据:> " + records);
    }

    @Test
    public void testDeleteCondition() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName123, "tom");
        userMapper.delete(wrapper);
    }

    @Test
    public void testSimpleLambdaUpdateWrapper1() {
        //1. 构建条件对象
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(User::getName123, "jack")
                .set(User::getAge, 99)
                .set(User::getName123, "jack&rose");
        userMapper.update(null, updateWrapper);
    }

    @Test
    public void testSimpleLambdaUpdateWrapper2() {
        //1. 构建条件对象
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getName123, "jack&rose");

        User user = new User();
        user.setAge(99);
        user.setName123("jack");
        userMapper.update(user, updateWrapper);
    }

    @Autowired
    MyUserService myUserService;

    @Test
    public void testMyUserServiceImpl() {
        User user = myUserService.getById(1);
        System.out.println("user = " + user);
    }
}
