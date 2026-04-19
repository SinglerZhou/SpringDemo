现在，我将为前端开发人员生成接口文档：
# 用户服务API文档

## 概述
本API文档描述了用户服务提供的注册和登录接口。所有接口均返回JSON格式数据。

## 基础信息
- 协议: HTTP/HTTPS
- 基础URL: `http://localhost:8080/SpringDemo/api/user`
- 请求格式: JSON
- 响应格式: JSON
- 字符编码: UTF-8

## 通用响应格式
所有API接口返回统一的响应格式：
json { "code": 200, "message": "success", "data": {} }


| 字段 | 类型 | 说明 |
| --- | --- | --- |
| code | int | 状态码，200表示成功，其他表示失败 |
| message | string | 消息描述 |
| data | object | 返回的数据，可能为null |

## 接口列表

### 1. 用户注册
- **接口地址**: `/register`
- **请求方式**: POST
- **请求头**:
    - Content-Type: application/json
- **请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| name | string | 是 | 用户姓名 |
| email | string | 是 | 邮箱地址 |
| password | string | 是 | 密码 |
| phone | string | 否 | 手机号码 |

- **请求示例**:
  json { "name": "张三", "email": "zhangsan@example.com", "password": "123456", "phone": "13800138000" }
- **成功响应**:
  json { "code": 200, "message": "success", "data": { "id": "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8", "name": "张三", "email": "zhangsan@example.com", "phone": "13800138000", "address": null, "password": "123456" } }
- **失败响应**:
  json { "code": 500, "message": "邮箱已被注册", "data": null }

### 2. 用户登录
- **接口地址**: `/login`
- **请求方式**: POST
- **请求头**:
    - Content-Type: application/json
- **请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
| --- | --- | --- | --- |
| email | string | 是 | 邮箱地址 |
| password | string | 是 | 密码 |

- **请求示例**:
  json { "email": "zhangsan@example.com", "password": "123456" }

- **成功响应**:
  json { "code": 200, "message": "success", "data": { "id": "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8", "name": "张三", "email": "zhangsan@example.com", "phone": "13800138000", "address": null, "password": "123456" } }

- **失败响应**:
  json { "code": 500, "message": "用户不存在", "data": null }

## 错误码说明
| 错误码 | 说明 |
| --- | --- |
| 200 | 成功 |
| 500 | 服务器内部错误或业务逻辑错误 |

## 使用说明
1. 启动应用后，服务将运行在`http://localhost:8080/SpringDemo`
2. 可以使用Postman等工具测试接口
3. 前端可以通过fetch或axios调用这些接口

## 注意事项
- 当前版本使用内存存储用户数据，重启应用后数据会丢失
- 密码未加密存储，仅用于演示目的，生产环境应使用BCrypt等算法加密
- 未实现JWT令牌认证，登录后无法验证用户身份
- 跨域设置为允许所有域名，生产环境应限制具体域名




