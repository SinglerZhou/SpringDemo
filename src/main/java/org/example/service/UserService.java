package org.example.service;

import org.example.common.Result;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户服务类
 */
@Service
public class UserService {
    // 使用Map模拟数据库存储用户信息
    private Map<String, User> users = new HashMap<>();
    // 使用Map存储邮箱与用户ID的映射，便于通过邮箱查找用户
    private Map<String, String> emailToUserId = new HashMap<>();

    /**
     * 用户注册
     * @param registerRequest 注册请求
     * @return 注册结果
     */
    public Result register(RegisterRequest registerRequest) {
        // 检查邮箱是否已存在
        if (emailToUserId.containsKey(registerRequest.getEmail())) {
            return Result.error("邮箱已被注册");
        }

        // 创建新用户
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword()); // 实际项目中应该对密码进行加密
        user.setPhone(registerRequest.getPhone());

        // 保存用户
        users.put(user.getId(), user);
        emailToUserId.put(user.getEmail(), user.getId());

        return Result.success(user);
    }

    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    public Result login(LoginRequest loginRequest) {
        // 根据邮箱查找用户
        String userId = emailToUserId.get(loginRequest.getEmail());
        if (userId == null) {
            return Result.error("用户不存在");
        }

        User user = users.get(userId);
        // 验证密码（实际项目中应该使用加密后的密码进行比较）
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return Result.error("密码错误");
        }

        // 登录成功，返回用户信息
        return Result.success(user);
    }

    /**
     * 根据用户ID获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    public User getUserById(String userId) {
        return users.get(userId);
    }
}
