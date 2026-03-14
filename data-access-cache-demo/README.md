# 数据访问与缓存综合演示项目

## 项目简介

这是一个基于Spring Boot 3.0的数据访问与缓存综合演示项目，展示了企业级应用中数据访问层的最佳实践。项目集成了多种数据访问技术和缓存解决方案，为开发者提供了完整的技术参考。

## 技术栈

- **Spring Boot 3.0.0** - 应用框架
- **Spring Data JPA 3.0** - 关系型数据库ORM
- **Spring Data MongoDB** - 文档数据库操作
- **Spring Data Redis** - 分布式缓存
- **MyBatis 3.0.3** - 持久层框架
- **Memcached** - 内存缓存
- **MySQL 8.0** - 关系型数据库
- **MongoDB 4.4+** - 文档数据库
- **H2** - 测试数据库

## 项目特性

### 1. Spring Data JPA 3.0
- ✅ 实体映射与关系配置
- ✅ Repository接口与自定义查询
- ✅ 动态查询
- ✅ 多数据源配置
- ✅ 事务管理

### 2. MyBatis集成
- ✅ 注解方式Mapper开发
- ✅ 动态SQL支持
- ✅ 参数绑定与结果映射
- ✅ 分页查询支持

### 3. MongoDB文档操作
- ✅ 文档实体与Repository
- ✅ 聚合查询操作
- ✅ 索引优化配置
- ✅ 复杂查询支持

### 4. Redis缓存
- ✅ RedisTemplate配置
- ✅ 缓存注解支持
- ✅ 多种数据类型操作
- ✅ 缓存一致性保证

### 5. Memcached缓存
- ✅ MemcachedClient配置
- ✅ 基础CRUD操作
- ✅ 计数器功能
- ✅ 连接池管理

### 6. 测试覆盖
- ✅ 单元测试
- ✅ 集成测试
- ✅ 性能测试
- ✅ 异常处理测试

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- MongoDB 4.4+
- Redis 6.0+

### 安装步骤

1. 克隆项目
```bash
git clone <repository-url>
cd data-access-cache-demo
```

2. 配置数据库
```sql
-- 创建MySQL数据库
CREATE DATABASE primary_db;
CREATE DATABASE secondary_db;

-- 创建用户表
USE primary_db;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

3. 修改配置文件
编辑 `src/main/resources/application.properties`，配置数据库连接信息。

4. 构建项目
```bash
mvn clean package
```

5. 运行应用
```bash
mvn spring-boot:run
```

## API文档

### 用户管理API

| 方法 | 路径 | 描述 |
|------|--------|------|
| GET | /api/users | 获取所有用户 |
| GET | /api/users/{id} | 根据ID获取用户 |
| POST | /api/users | 创建新用户 |
| PUT | /api/users/{id} | 更新用户信息 |
| DELETE | /api/users/{id} | 删除用户 |
| GET | /api/users/username/{username} | 根据用户名查询 |
| POST | /api/users/search | 动态条件查询 |

### 产品管理API

| 方法 | 路径 | 描述 |
|------|--------|------|
| GET | /api/products | 获取所有产品 |
| GET | /api/products/{id} | 根据ID获取产品 |
| POST | /api/products | 创建新产品 |
| PUT | /api/products/{id} | 更新产品信息 |
| DELETE | /api/products/{id} | 删除产品 |
| GET | /api/products/category/{category} | 按分类查询 |
| GET | /api/products/price-range | 价格范围查询 |
| GET | /api/products/low-stock | 低库存产品 |
| GET | /api/products/aggregate | 聚合统计 |

## 测试

### 运行测试
```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=UserServiceTest

# 运行集成测试
mvn test -Dtest=IntegrationTest
```

### 测试覆盖
- ✅ 用户CRUD操作测试
- ✅ 动态查询功能测试
- ✅ MongoDB操作测试
- ✅ Redis缓存操作测试
- ✅ 集成测试验证
- ✅ 异常处理测试

## 项目结构

```
data-access-cache-demo/
├── src/main/java/com/example/demo/
│   ├── entity/                    # JPA实体类
│   ├── repository/                 # JPA Repository接口
│   ├── service/                    # 业务逻辑层
│   ├── controller/                 # REST API控制器
│   ├── config/                     # 配置类
│   ├── specification/              # JPA动态查询
│   ├── mapper/                     # MyBatis Mapper接口
│   ├── mongo/                      # MongoDB相关
│   ├── redis/                      # Redis相关
│   └── memcached/                  # Memcached相关
├── src/main/resources/
│   ├── application.properties         # 主配置文件
│   └── application-test.properties  # 测试配置
├── src/test/java/com/example/demo/
│   ├── UserServiceTest.java          # 用户服务单元测试
│   └── IntegrationTest.java          # 集成测试
├── pom.xml                        # Maven配置文件
└── 项目使用文档.md                # 详细使用文档
```

## 性能优化

### 数据库优化
- 合理使用索引
- 避免N+1查询问题
- 使用批量操作
- 配置连接池

### 缓存优化
- 合理设置过期时间
- 使用合适的缓存策略
- 实现缓存预热
- 监控缓存命中率

## 学习价值

通过本项目，你将学习到：

1. **Spring Data JPA 3.0新特性**
   - Java 17 Records支持
   - 改进的查询方法
   - 增强的缓存支持

2. **企业级数据访问架构**
   - 多数据源配置
   - 读写分离实现
   - 事务管理策略

3. **缓存技术实践**
   - Redis分布式缓存
   - Memcached内存缓存
   - 缓存一致性保证

4. **性能优化技巧**
   - 数据库查询优化
   - 缓存策略选择
   - 代码性能提升

## 贡献指南

欢迎提交Issue和Pull Request来改进这个项目。

## 许可证

本项目仅用于学习和演示目的。

## 联系方式

如有问题，请提交Issue或联系项目维护者。