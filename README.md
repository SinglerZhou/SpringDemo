# SpringDemo - 用户服务后端项目

## 📋 项目简介

这是一个基于 Spring Boot 的用户服务后端项目，提供用户注册和登录的 RESTful API 接口。项目采用标准的分层架构设计，适合前端开发者学习和对接使用。

## 🏗️ 项目结构

### 标准分层架构

```
src/main/java/org/example/
├── Application.java                          # 启动类（根包）
├── controller/
│   └── UserController.java                   # 控制器层 - 处理HTTP请求
├── service/
│   └── UserService.java                      # 服务层 - 业务逻辑处理
├── model/
│   └── User.java                             # 实体类 - 数据模型
├── dto/
│   ├── RegisterRequest.java                  # 注册请求DTO
│   └── LoginRequest.java                     # 登录请求DTO
└── common/
    └── Result.java                           # 通用响应类
```

### 分层架构说明

| 层级 | 包名 | 职责 | 示例 |
|------|------|------|------|
| **Controller** | `controller` | 接收HTTP请求，参数校验，调用Service | UserController |
| **Service** | `service` | 业务逻辑处理，事务控制 | UserService |
| **Model/Entity** | `model` | 数据库实体，领域模型 | User |
| **DTO** | `dto` | 数据传输对象，接口入参/出参 | RegisterRequest, LoginRequest |
| **Common** | `common` | 通用工具类，常量，统一响应 | Result |

### 各层职责详解

#### 1. Controller 层（控制器层）
- 负责接收 HTTP 请求
- 参数验证和绑定
- 调用 Service 层处理业务
- 返回统一的响应结果
- 不处理具体业务逻辑

#### 2. Service 层（服务层）
- 核心业务逻辑处理
- 事务管理
- 调用数据访问层
- 数据转换和处理

#### 3. Model 层（实体层）
- 对应数据库表结构
- 定义数据属性和关系
- 包含 getter/setter 方法

#### 4. DTO 层（数据传输对象）
- 接口请求参数封装
- 接口响应数据封装
- 避免直接暴露实体类
- 可以根据不同场景定制不同的DTO

#### 5. Common 层（通用层）
- 统一响应格式
- 工具类
- 常量定义
- 异常处理

## 🚀 快速开始

### 环境要求

- **JDK**: 11 或更高版本
- **Maven**: 3.6+ 
- **IDE**: IntelliJ IDEA（推荐）或 Eclipse

### 运行步骤

#### 方法一：使用 IntelliJ IDEA（推荐）

1. **打开项目**
   - 在 IntelliJ IDEA 中打开项目根目录

2. **等待 Maven 依赖下载**
   - IDEA 会自动识别 pom.xml 并下载依赖
   - 可以在右下角查看下载进度

3. **运行应用**
   - 找到 `src/main/java/org/example/Application.java`
   - 右键点击 `main` 方法
   - 选择 **"Run 'Application.main()'"**

4. **查看控制台输出**
   ```
   Tomcat started on port(s): 8080 (http) with context path ''
   Started Application in X.XXX seconds
   ===================================
   应用启动成功!
   注册接口: POST http://localhost:8080/api/user/register
   登录接口: POST http://localhost:8080/api/user/login
   ===================================
   ```

#### 方法二：使用命令行

```bash
# 进入项目目录
cd C:\Users\zhouxingui\IdeaProjects\SpringDemo

# 编译并运行
mvn spring-boot:run
```

#### 方法三：打包后运行

```bash
# 打包成可执行JAR
mvn clean package

# 运行JAR包
java -jar target/SpringDemo-1.0-SNAPSHOT.jar
```

## 📡 API 接口文档

### 基础信息

- **基础URL**: `http://localhost:8080/api/user`
- **请求格式**: JSON
- **响应格式**: JSON
- **字符编码**: UTF-8

### 通用响应格式

所有接口返回统一的 JSON 格式：

```json
{
    "code": 200,
    "message": "success",
    "data": {}
}
```

| 字段 | 类型 | 说明 |
|------|------|------|
| code | int | 状态码，200表示成功，其他表示失败 |
| message | string | 消息描述 |
| data | object | 返回的数据，可能为null |

---

### 1. 用户注册

**接口地址**: `POST /api/user/register`

**请求头**:
```
Content-Type: application/json
```

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name | string | 是 | 用户姓名 |
| email | string | 是 | 邮箱地址 |
| password | string | 是 | 密码 |
| phone | string | 否 | 手机号码 |

**请求示例**:
```json
{
    "name": "张三",
    "email": "zhangsan@example.com",
    "password": "123456",
    "phone": "13800138000"
}
```

**成功响应**:
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "id": "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8",
        "name": "张三",
        "email": "zhangsan@example.com",
        "phone": "13800138000",
        "address": null,
        "password": "123456"
    }
}
```

**失败响应**:
```json
{
    "code": 500,
    "message": "邮箱已被注册",
    "data": null
}
```

---

### 2. 用户登录

**接口地址**: `POST /api/user/login`

**请求头**:
```
Content-Type: application/json
```

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| email | string | 是 | 邮箱地址 |
| password | string | 是 | 密码 |

**请求示例**:
```json
{
    "email": "zhangsan@example.com",
    "password": "123456"
}
```

**成功响应**:
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "id": "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8",
        "name": "张三",
        "email": "zhangsan@example.com",
        "phone": "13800138000",
        "address": null,
        "password": "123456"
    }
}
```

**失败响应**:
```json
{
    "code": 500,
    "message": "用户不存在",
    "data": null
}
```

或

```json
{
    "code": 500,
    "message": "密码错误",
    "data": null
}
```

---

## 🧪 测试方法

### 方法一：使用 HTML 测试页面（推荐前端开发者）

项目提供了一个可视化的测试页面：

1. 确保后端服务已启动
2. 浏览器访问：`http://localhost:8080/test.html`
3. 填写表单并点击按钮测试

### 方法二：使用 Postman

1. 下载并安装 [Postman](https://www.postman.com/downloads/)
2. 创建新请求：
   - 方法：POST
   - URL：`http://localhost:8080/api/user/register`
   - Headers：添加 `Content-Type: application/json`
   - Body：选择 "raw" -> "JSON"，输入测试数据
3. 点击 Send 发送请求

### 方法三：使用 curl 命令

```bash
# 测试注册接口
curl -X POST http://localhost:8080/api/user/register \
-H "Content-Type: application/json" \
-d '{"name":"test","email":"test@example.com","password":"123456","phone":"13800138000"}'

# 测试登录接口
curl -X POST http://localhost:8080/api/user/login \
-H "Content-Type: application/json" \
-d '{"email":"test@example.com","password":"123456"}'
```

### 方法四：在前端项目中调用

```javascript
// 注册用户
async function registerUser() {
    try {
        const response = await fetch('http://localhost:8080/api/user/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: '张三',
                email: 'zhangsan@example.com',
                password: '123456',
                phone: '13800138000'
            })
        });
        
        const result = await response.json();
        console.log('注册结果:', result);
        
        if (result.code === 200) {
            alert('注册成功！');
        } else {
            alert('注册失败: ' + result.message);
        }
    } catch (error) {
        console.error('请求错误:', error);
        alert('网络错误，请检查后端服务是否启动');
    }
}

// 用户登录
async function loginUser() {
    try {
        const response = await fetch('http://localhost:8080/api/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: 'zhangsan@example.com',
                password: '123456'
            })
        });
        
        const result = await response.json();
        console.log('登录结果:', result);
        
        if (result.code === 200) {
            alert('登录成功！');
            // 保存用户信息到localStorage
            localStorage.setItem('userInfo', JSON.stringify(result.data));
        } else {
            alert('登录失败: ' + result.message);
        }
    } catch (error) {
        console.error('请求错误:', error);
        alert('网络错误，请检查后端服务是否启动');
    }
}
```

## ⚙️ 配置说明

### 修改端口号

如果 8080 端口被占用，可以在 `src/main/resources/application.properties` 中修改：

```properties
server.port=8081
```

### 跨域配置

已在 `UserController` 中配置允许跨域访问：

```java
@CrossOrigin(origins = "*")
```

如需限制特定域名：

```java
@CrossOrigin(origins = "http://localhost:3000")
```

## ❗ 重要注意事项

### 接口调用要点

1. ⚠️ **必须使用 POST 方法**，不能用 GET
2. ⚠️ **必须设置请求头**：`Content-Type: application/json`
3. ⚠️ **请求体必须是 JSON 格式**
4. ⚠️ **不要在浏览器地址栏直接访问 POST 接口**

### 当前版本的限制

- 💾 数据存储在内存中，重启应用后数据会丢失
- 🔐 密码未加密存储，仅用于演示目的
- 🎫 未实现 JWT 令牌认证
- 🌐 跨域设置为允许所有域名

### 生产环境建议

- ✅ 使用数据库持久化数据（MySQL、PostgreSQL等）
- ✅ 使用 BCrypt 加密密码
- ✅ 实现 JWT 或 Session 认证机制
- ✅ 限制跨域访问的具体域名
- ✅ 添加输入验证和 sanitization
- ✅ 实现日志记录和监控
- ✅ 添加速率限制防止滥用

## 🛠️ 常见问题

### 1. 端口被占用

**错误信息**: "Port 8080 was already in use"

**解决方案**:
```properties
# 在 application.properties 中修改端口
server.port=8081
```

### 2. Maven 依赖下载失败

**解决方案**:
```bash
mvn clean install -U
```

### 3. Java 版本不匹配

**检查 Java 版本**:
```bash
java -version
```

确保使用 Java 11 或更高版本。

### 4. 405 Method Not Allowed

**原因**: 使用了错误的 HTTP 方法（如用 GET 访问 POST 接口）

**解决方案**: 确保使用 POST 方法发送请求

### 5. 404 Not Found

**原因**: 接口路径错误或服务未启动

**解决方案**: 
- 检查 URL 是否正确
- 确认服务已成功启动
- 查看控制台是否有错误信息

## 📝 技术栈

- **Spring Boot 2.7.5** - 应用框架
- **Spring Web MVC** - Web 框架
- **Jackson** - JSON 处理
- **Maven** - 项目构建和依赖管理
- **Embedded Tomcat** - 嵌入式 Web 服务器

## 📄 许可证

本项目仅用于学习和演示目的。

## 👥 贡献

欢迎提交 Issue 和 Pull Request！

---

**祝开发愉快！** 🎉
