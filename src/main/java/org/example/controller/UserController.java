package org.example.controller;

import org.example.common.Result;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     * POST /api/user/register
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    /**
     * 用户登录接口
     * POST /api/user/login
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
