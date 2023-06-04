package com.hbpu.backend.controller;

import com.hbpu.intetface.pojo.User;
import com.hbpu.intetface.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>UserController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2023/5/26 20:14.
 *
 * @author 皮
 */
@RestController
public class UserController {

    @DubboReference
    private UserService userService;

    @RequestMapping("/user/{id}")
    public User test1(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }

}
