package com.kq.project.service;

import com.kq.project.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootTest
public class InsertUserTest {

    @Resource
    private UserService userService;

    @Test
    public void doInsertUser(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100;
        ArrayList<User> arrayList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("zzzkkk");
            user.setUserAccount("kkkkqqq");
            user.setAvatarUrl("https://tse2-mm.cn.bing.net/th/id/OIP-C.P7IxvMAx8MteSa1_VqmX3wHaKO?w=145&h=200&c=7&r=0&o=5&dpr=1.25&pid=1.7");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("12334");
            user.setEmail("123@qq.com");
            user.setTags("[]");
            user.setUserStatus(0);
            user.setUserRole(0);
            arrayList.add(user);
        }
        userService.saveBatch(arrayList,10);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


}
