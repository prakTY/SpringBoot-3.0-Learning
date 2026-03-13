# SpringBoot-3.0-Learning

朱天宇的SpringBoot 3.0框架学习项目，分为五个阶段

## 项目介绍

本项目是SpringBoot 3.0的学习项目，包含以下五个学习阶段：

- 第一阶段：SpringBoot3.0基础入门
- 第二阶段：Web开发进阶
- 第三阶段：数据访问与缓存
- 第四阶段：高级特性与云原生
- 第五阶段：综合项目实战

## 项目结构

```
SpringBoot3.0学习/
├── demo/                          # 示例项目
│   ├── src/                       # 源代码
│   ├── target/                    # 编译输出
│   ├── Dockerfile                 # Docker配置
│   ├── run.bat                    # Windows启动脚本
│   └── 项目使用文档.md            # 项目使用文档
├── 第一阶段-SpringBoot3.0基础入门.md
├── 第二阶段-Web开发进阶.md
├── 第三阶段-数据访问与缓存.md
├── 第四阶段-高级特性与云原生.md
├── 第五阶段-综合项目实战.md
├── Git开发常用命令大全.md
└── README.md
```

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.5.0+
- Docker（可选）

### 运行项目

```bash
# 进入demo目录
cd demo

# 方式1：使用Maven运行
mvn spring-boot:run

# 方式2：使用Windows脚本
.\run.bat start

# 方式3：使用Docker
docker build -t spring-boot-demo:latest .
docker run -d -p 8080:8080 spring-boot-demo:latest
```

## 学习资源

详细的阶段学习内容请参考：
- [第一阶段-SpringBoot3.0基础入门](第一阶段-SpringBoot3.0基础入门.md)
- [第二阶段-Web开发进阶](第二阶段-Web开发进阶.md)
- [第三阶段-数据访问与缓存](第三阶段-数据访问与缓存.md)
- [第四阶段-高级特性与云原生](第四阶段-高级特性与云原生.md)
- [第五阶段-综合项目实战](第五阶段-综合项目实战.md)

## 贡献

欢迎提交Issue和Pull Request！

## 许可证

MIT License