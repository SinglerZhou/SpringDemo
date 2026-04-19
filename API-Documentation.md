# SpringDemo API 接口文档

## 📋 文档说明

本文档提供了用户服务的所有API接口详细说明，适用于 iOS、Android、鸿蒙OS、Web 等所有前端平台。

- **版本**: v1.0
- **更新日期**: 2026-04-19
- **基础URL**: `http://localhost:8080` (开发环境)
- **协议**: HTTP/HTTPS
- **数据格式**: JSON
- **字符编码**: UTF-8

---

## 🔗 接口概览

| 接口名称 | 请求方法 | 接口路径 | 说明 |
|---------|---------|---------|------|
| 用户注册 | POST | `/api/user/register` | 新用户注册账号 |
| 用户登录 | POST | `/api/user/login` | 用户登录获取信息 |

---

## 📦 通用说明

### 1. 请求头（Headers）

所有接口都需要设置以下请求头：

```
Content-Type: application/json
Accept: application/json
```

### 2. 响应格式

所有接口返回统一的JSON格式：

```json
{
    "code": 200,
    "message": "success",
    "data": {}
}
```

**字段说明：**

| 字段 | 类型 | 说明 |
|------|------|------|
| code | Integer | 状态码，200表示成功，其他表示失败 |
| message | String | 响应消息描述 |
| data | Object | 响应数据，失败时为null |

### 3. 状态码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 500 | 业务逻辑错误或服务器错误 |

### 4. 跨域支持

已配置CORS跨域支持，允许所有域名访问：
```
Access-Control-Allow-Origin: *
```

---

## 📝 接口详情

### 1. 用户注册

#### 基本信息
- **接口地址**: `/api/user/register`
- **请求方法**: `POST`
- **Content-Type**: `application/json`
- **接口描述**: 新用户注册账号

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 | 示例 |
|--------|------|------|------|------|
| name | String | 是 | 用户姓名 | "张三" |
| email | String | 是 | 邮箱地址（唯一） | "zhangsan@example.com" |
| password | String | 是 | 密码（建议6-20位） | "123456" |
| phone | String | 否 | 手机号码 | "13800138000" |

#### 请求示例

```json
{
    "name": "张三",
    "email": "zhangsan@example.com",
    "password": "123456",
    "phone": "13800138000"
}
```

#### 成功响应

**HTTP Status: 200**

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "id": "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8",
        "name": "张三",
        "email": "zhangsan@example.com",
        "password": "123456",
        "phone": "13800138000",
        "address": null
    }
}
```

#### 失败响应

**场景1：邮箱已被注册**

```json
{
    "code": 500,
    "message": "邮箱已被注册",
    "data": null
}
```

**场景2：参数缺失或格式错误**

```json
{
    "code": 500,
    "message": "请求参数错误",
    "data": null
}
```

---

### 2. 用户登录

#### 基本信息
- **接口地址**: `/api/user/login`
- **请求方法**: `POST`
- **Content-Type**: `application/json`
- **接口描述**: 用户登录获取个人信息

#### 请求参数

| 参数名 | 类型 | 必填 | 说明 | 示例 |
|--------|------|------|------|------|
| email | String | 是 | 邮箱地址 | "zhangsan@example.com" |
| password | String | 是 | 密码 | "123456" |

#### 请求示例

```json
{
    "email": "zhangsan@example.com",
    "password": "123456"
}
```

#### 成功响应

**HTTP Status: 200**

```json
{
    "code": 200,
    "message": "success",
    "data": {
        "id": "a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8",
        "name": "张三",
        "email": "zhangsan@example.com",
        "password": "123456",
        "phone": "13800138000",
        "address": null
    }
}
```

#### 失败响应

**场景1：用户不存在**

```json
{
    "code": 500,
    "message": "用户不存在",
    "data": null
}
```

**场景2：密码错误**

```json
{
    "code": 500,
    "message": "密码错误",
    "data": null
}
```

---

## 💻 各平台调用示例

### Web / HTML (JavaScript)

#### 使用 Fetch API

```javascript
// 用户注册
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
        
        if (result.code === 200) {
            console.log('注册成功:', result.data);
            // 保存用户信息
            localStorage.setItem('userInfo', JSON.stringify(result.data));
        } else {
            console.error('注册失败:', result.message);
        }
    } catch (error) {
        console.error('网络错误:', error);
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
        
        if (result.code === 200) {
            console.log('登录成功:', result.data);
            // 保存用户信息和token
            localStorage.setItem('userInfo', JSON.stringify(result.data));
        } else {
            console.error('登录失败:', result.message);
        }
    } catch (error) {
        console.error('网络错误:', error);
    }
}
```

#### 使用 Axios

```javascript
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json'
    }
});

// 用户注册
export const registerUser = async (userData) => {
    try {
        const response = await apiClient.post('/api/user/register', userData);
        return response.data;
    } catch (error) {
        throw error;
    }
};

// 用户登录
export const loginUser = async (credentials) => {
    try {
        const response = await apiClient.post('/api/user/login', credentials);
        return response.data;
    } catch (error) {
        throw error;
    }
};
```

---

### iOS (Swift)

#### 使用 URLSession

```swift
import Foundation

class UserService {
    static let shared = UserService()
    private let baseURL = "http://localhost:8080"
    
    // 用户注册
    func register(name: String, email: String, password: String, phone: String?, completion: @escaping (Result<User, Error>) -> Void) {
        guard let url = URL(string: "\(baseURL)/api/user/register") else {
            completion(.failure(NSError(domain: "Invalid URL", code: -1, userInfo: nil)))
            return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        var body: [String: Any] = [
            "name": name,
            "email": email,
            "password": password
        ]
        if let phone = phone {
            body["phone"] = phone
        }
        
        request.httpBody = try? JSONSerialization.data(withJSONObject: body)
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                completion(.failure(error))
                return
            }
            
            guard let data = data else {
                completion(.failure(NSError(domain: "No data", code: -1, userInfo: nil)))
                return
            }
            
            do {
                let result = try JSONDecoder().decode(APIResponse<User>.self, from: data)
                if result.code == 200, let user = result.data {
                    completion(.success(user))
                } else {
                    completion(.failure(NSError(domain: result.message, code: result.code, userInfo: nil)))
                }
            } catch {
                completion(.failure(error))
            }
        }.resume()
    }
    
    // 用户登录
    func login(email: String, password: String, completion: @escaping (Result<User, Error>) -> Void) {
        guard let url = URL(string: "\(baseURL)/api/user/login") else {
            completion(.failure(NSError(domain: "Invalid URL", code: -1, userInfo: nil)))
            return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        let body: [String: Any] = [
            "email": email,
            "password": password
        ]
        
        request.httpBody = try? JSONSerialization.data(withJSONObject: body)
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                completion(.failure(error))
                return
            }
            
            guard let data = data else {
                completion(.failure(NSError(domain: "No data", code: -1, userInfo: nil)))
                return
            }
            
            do {
                let result = try JSONDecoder().decode(APIResponse<User>.self, from: data)
                if result.code == 200, let user = result.data {
                    completion(.success(user))
                } else {
                    completion(.failure(NSError(domain: result.message, code: result.code, userInfo: nil)))
                }
            } catch {
                completion(.failure(error))
            }
        }.resume()
    }
}

// 数据模型
struct APIResponse<T: Codable>: Codable {
    let code: Int
    let message: String
    let data: T?
}

struct User: Codable {
    let id: String
    let name: String
    let email: String
    let password: String
    let phone: String?
    let address: String?
}

// 使用示例
UserService.shared.register(
    name: "张三",
    email: "zhangsan@example.com",
    password: "123456",
    phone: "13800138000"
) { result in
    switch result {
    case .success(let user):
        print("注册成功: \(user.name)")
    case .failure(let error):
        print("注册失败: \(error.localizedDescription)")
    }
}
```

---

### Android (Kotlin)

#### 使用 Retrofit

```kotlin
// build.gradle 添加依赖
// implementation 'com.squareup.retrofit2:retrofit:2.9.0'
// implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
// implementation 'com.squareup.okhttp3:logging-interceptor:4.9.3'

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName

// API 接口定义
interface UserService {
    
    @POST("/api/user/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<User>
    
    @POST("/api/user/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<User>
}

// 数据类
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String? = null
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val phone: String?,
    val address: String?
)

// Retrofit 客户端
object ApiClient {
    private const val BASE_URL = "http://localhost:8080"
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val userService: UserService = retrofit.create(UserService::class.java)
}

// ViewModel 中使用
class LoginViewModel : ViewModel() {
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.userService.login(
                    LoginRequest(email, password)
                )
                
                if (response.code == 200 && response.data != null) {
                    // 登录成功
                    Log.d("Login", "登录成功: ${response.data.name}")
                    // 保存用户信息
                } else {
                    // 登录失败
                    Log.e("Login", "登录失败: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("Login", "网络错误: ${e.message}")
            }
        }
    }
    
    fun register(name: String, email: String, password: String, phone: String?) {
        viewModelScope.launch {
            try {
                val response = ApiClient.userService.register(
                    RegisterRequest(name, email, password, phone)
                )
                
                if (response.code == 200 && response.data != null) {
                    // 注册成功
                    Log.d("Register", "注册成功: ${response.data.name}")
                } else {
                    // 注册失败
                    Log.e("Register", "注册失败: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("Register", "网络错误: ${e.message}")
            }
        }
    }
}
```

#### 使用 OkHttp

```kotlin
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class UserServiceOkHttp {
    private val client = OkHttpClient()
    private val baseURL = "http://localhost:8080"
    
    fun register(name: String, email: String, password: String, phone: String?, callback: Callback) {
        val json = JSONObject()
        json.put("name", name)
        json.put("email", email)
        json.put("password", password)
        if (phone != null) {
            json.put("phone", phone)
        }
        
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            json.toString()
        )
        
        val request = Request.Builder()
            .url("$baseURL/api/user/register")
            .post(body)
            .build()
        
        client.newCall(request).enqueue(callback)
    }
    
    fun login(email: String, password: String, callback: Callback) {
        val json = JSONObject()
        json.put("email", email)
        json.put("password", password)
        
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            json.toString()
        )
        
        val request = Request.Builder()
            .url("$baseURL/api/user/login")
            .post(body)
            .build()
        
        client.newCall(request).enqueue(callback)
    }
}

// 使用示例
val userService = UserServiceOkHttp()

userService.login("zhangsan@example.com", "123456", object : Callback {
    override fun onFailure(call: Call, e: IOException) {
        Log.e("Login", "网络错误: ${e.message}")
    }
    
    override fun onResponse(call: Call, response: Response) {
        val responseBody = response.body?.string()
        Log.d("Login", "响应: $responseBody")
    }
})
```

---

### 鸿蒙OS (HarmonyOS - ArkTS)

```typescript
// 使用 http 模块
import http from '@ohos.net.http';

class UserService {
  private baseURL: string = 'http://localhost:8080';
  
  // 用户注册
  async register(name: string, email: string, password: string, phone?: string): Promise<any> {
    let httpRequest = http.createHttp();
    
    try {
      let response = await httpRequest.request(
        `${this.baseURL}/api/user/register`,
        {
          method: http.RequestMethod.POST,
          header: {
            'Content-Type': 'application/json'
          },
          extraData: JSON.stringify({
            name: name,
            email: email,
            password: password,
            phone: phone || ''
          })
        }
      );
      
      if (response.responseCode === 200) {
        let result = JSON.parse(response.result as string);
        if (result.code === 200) {
          console.info('注册成功:', result.data);
          return result.data;
        } else {
          console.error('注册失败:', result.message);
          throw new Error(result.message);
        }
      }
    } catch (error) {
      console.error('网络错误:', error);
      throw error;
    } finally {
      httpRequest.destroy();
    }
  }
  
  // 用户登录
  async login(email: string, password: string): Promise<any> {
    let httpRequest = http.createHttp();
    
    try {
      let response = await httpRequest.request(
        `${this.baseURL}/api/user/login`,
        {
          method: http.RequestMethod.POST,
          header: {
            'Content-Type': 'application/json'
          },
          extraData: JSON.stringify({
            email: email,
            password: password
          })
        }
      );
      
      if (response.responseCode === 200) {
        let result = JSON.parse(response.result as string);
        if (result.code === 200) {
          console.info('登录成功:', result.data);
          return result.data;
        } else {
          console.error('登录失败:', result.message);
          throw new Error(result.message);
        }
      }
    } catch (error) {
      console.error('网络错误:', error);
      throw error;
    } finally {
      httpRequest.destroy();
    }
  }
}

// 使用示例
const userService = new UserService();

// 注册
userService.register('张三', 'zhangsan@example.com', '123456', '13800138000')
  .then(user => {
    console.info('用户信息:', user);
  })
  .catch(error => {
    console.error('错误:', error.message);
  });

// 登录
userService.login('zhangsan@example.com', '123456')
  .then(user => {
    console.info('用户信息:', user);
  })
  .catch(error => {
    console.error('错误:', error.message);
  });
```

---

## ⚠️ 注意事项

### 1. 网络安全

- **开发环境**: 使用 HTTP (`http://localhost:8080`)
- **生产环境**: 必须使用 HTTPS，并配置有效的SSL证书
- **密码安全**: 当前版本密码明文传输，生产环境必须使用HTTPS加密

### 2. 错误处理

所有平台都应该实现完善的错误处理机制：

```javascript
// 示例：统一的错误处理
function handleError(error) {
    if (error.response) {
        // 服务器返回错误状态码
        console.error('服务器错误:', error.response.data.message);
    } else if (error.request) {
        // 请求发送但没有收到响应
        console.error('网络错误: 请检查网络连接');
    } else {
        // 其他错误
        console.error('请求错误:', error.message);
    }
}
```

### 3. 超时设置

建议设置合理的请求超时时间：

```javascript
// Web: 10秒超时
fetch(url, { 
    method: 'POST',
    signal: AbortSignal.timeout(10000)
})

// iOS: 30秒超时
let session = URLSession(configuration: {
    let config = URLSessionConfiguration.default
    config.timeoutIntervalForRequest = 30
    return config
}())

// Android: 30秒超时
val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()
```

### 4. 数据缓存

建议在客户端缓存用户信息：

```javascript
// Web: localStorage
localStorage.setItem('userInfo', JSON.stringify(userData));

// iOS: UserDefaults
UserDefaults.standard.set(userData, forKey: "userInfo")

// Android: SharedPreferences
sharedPreferences.edit().putString("userInfo", jsonData).apply()

// 鸿蒙: Preferences
preferences.put('userInfo', JSON.stringify(userData))
```

### 5. 当前版本限制

- ⚠️ 数据存储在内存中，重启服务后数据丢失
- ⚠️ 密码未加密存储和传输
- ⚠️ 无Token认证机制
- ⚠️ 无请求频率限制

### 6. 生产环境建议

- ✅ 使用数据库持久化数据（MySQL、PostgreSQL等）
- ✅ 使用HTTPS加密传输
- ✅ 使用BCrypt加密密码
- ✅ 实现JWT或OAuth2认证
- ✅ 添加请求速率限制
- ✅ 实现日志记录和监控
- ✅ 添加输入验证和 sanitization

---

## 🧪 测试工具

### Postman

导入以下集合进行测试：

```json
{
    "info": {
        "name": "SpringDemo API",
        "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
        {
            "name": "用户注册",
            "request": {
                "method": "POST",
                "header": [
                    {
                        "key": "Content-Type",
                        "value": "application/json"
                    }
                ],
                "body": {
                    "mode": "raw",
                    "raw": "{\n    \"name\": \"张三\",\n    \"email\": \"zhangsan@example.com\",\n    \"password\": \"123456\",\n    \"phone\": \"13800138000\"\n}"
                },
                "url": {
                    "raw": "http://localhost:8080/api/user/register",
                    "protocol": "http",
                    "host": ["localhost"],
                    "port": "8080",
                    "path": ["api", "user", "register"]
                }
            }
        },
        {
            "name": "用户登录",
            "request": {
                "method": "POST",
                "header": [
                    {
                        "key": "Content-Type",
                        "value": "application/json"
                    }
                ],
                "body": {
                    "mode": "raw",
                    "raw": "{\n    \"email\": \"zhangsan@example.com\",\n    \"password\": \"123456\"\n}"
                },
                "url": {
                    "raw": "http://localhost:8080/api/user/login",
                    "protocol": "http",
                    "host": ["localhost"],
                    "port": "8080",
                    "path": ["api", "user", "login"]
                }
            }
        }
    ]
}
```

### cURL 命令

```bash
# 用户注册
curl -X POST http://localhost:8080/api/user/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "张三",
    "email": "zhangsan@example.com",
    "password": "123456",
    "phone": "13800138000"
  }'

# 用户登录
curl -X POST http://localhost:8080/api/user/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "zhangsan@example.com",
    "password": "123456"
  }'
```

---

## 📞 技术支持

如有问题，请联系后端开发团队或查看项目README.md文件。

**项目地址**: [GitHub Repository](https://github.com/your-username/SpringDemo)

---

**文档版本**: v1.0  
**最后更新**: 2026-04-19  
**维护者**: 后端开发团队
