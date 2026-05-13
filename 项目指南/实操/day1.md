# 博客后端项目 - Day1 环境搭建与初始化记录
> 项目技术栈：SpringBoot + Spring Data JPA + Hibernate + MySQL 8.0

---

## 📋 今日目标
1.  完成 Java 后端开发全环境搭建
2.  安装并配置 MySQL 8.0 + DataGrip（个人非商业版）
3.  配置项目数据库连接，实现 JPA 自动建表
4.  完成项目初始化、启动验证与代码提交

---

## 1. 开发环境准备
### 1.1 必备软件清单
| 软件 | 版本 | 用途 |
| :--- | :--- | :--- |
| JDK | 1.8+ | Java 运行环境 |
| Maven | 3.6+ | 项目构建与依赖管理 |
| Git | 任意稳定版 | 版本控制工具 |
| MySQL | 8.0.x | 项目数据库服务 |
| DataGrip | 个人非商业版 | 数据库可视化管理工具 |
| Trae IDE | 最新版 | 项目开发与调试工具 |

### 1.2 环境验证（命令行执行）
```bash
# 验证 JDK
java -version

# 验证 Maven
mvn -v

# 验证 Git
git --version

# 验证 MySQL
mysql -V
```
所有命令均正常输出版本信息，环境配置完成 ✅

---

## 2. MySQL 安装与配置
### 2.1 MySQL 安装
- 安装版本：MySQL 8.0.x
- 端口配置：默认 `3306`
- 身份验证方式：MySQL 8.0 默认认证插件
- `root` 用户密码：已设置并记录

### 2.2 DataGrip 连接配置
1.  打开 DataGrip → `文件` → `新建` → `数据源` → `MySQL`
2.  连接参数配置：
    - Host: `localhost`
    - Port: `3306`
    - User: `root`
    - Password: 安装时设置的 root 密码
3.  自动下载 MySQL 官方驱动
4.  点击 `Test Connection`，显示 `Succeeded` 表示连接成功 ✅

### 2.3 创建项目专用数据库
```sql
CREATE DATABASE blog_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
- 数据库名：`blog_db`
- 字符集：`utf8mb4`（支持全 emoji 与多语言）
- 说明：仅创建空数据库，表结构由 JPA 自动生成

---

## 3. GitHub 与项目初始化
### 3.1 项目 Fork
1.  访问原项目仓库：https://github.com/J0rthan/blog_backend
2.  点击右上角 `Fork`，将项目复制到个人 GitHub 账号
3.  获得个人仓库地址：`https://github.com/你的用户名/blog_backend`

### 3.2 Trae 配置与项目克隆
1.  配置 Git 全局身份：
    ```bash
    git config --global user.name "你的GitHub用户名"
    git config --global user.email "你的GitHub注册邮箱"
    ```
2.  Trae 中选择「从URL克隆」，粘贴个人仓库地址
3.  完成项目克隆并信任项目目录

---

## 4. SpringBoot 项目配置
### 4.1 核心配置文件 `application.properties`
```properties
# 数据库连接配置
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=你的MySQL root密码
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate 配置
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 4.2 关键配置说明
| 配置项 | 作用 |
| :--- | :--- |
| `spring.jpa.hibernate.ddl-auto=update` | 开发阶段自动根据实体类创建/更新表结构，不删除数据 |
| `spring.jpa.show-sql=true` | 控制台打印 Hibernate 生成的 SQL 语句，方便调试 |
| `MySQL8Dialect` | 指定 MySQL 8 方言，适配数据库语法 |

---

## 5. 项目启动与验证
### 5.1 启动项目
- 启动类：`BlogApplication.java`
- 启动结果：控制台输出 `Started BlogApplication in ... seconds`，无报错 ✅

### 5.2 JPA 自动建表验证
- Hibernate 自动在 `blog_db` 库中生成表结构：
  - `user`：用户信息表
  - `post`：博客文章表
  - `comment`：博客评论表
- 在 DataGrip 中刷新数据库，可查看自动生成的表结构 ✅

### 5.3 测试接口开发与验证
1.  新增测试接口：`GET /api/system/test`
    ```java
    @RestController
    @RequestMapping("/api/system")
    public class SystemController {
        @GetMapping("/test")
        public Result test() {
            return Result.success("服务启动成功，环境配置正常");
        }
    }
    ```
2.  访问测试：浏览器打开 `http://localhost:8080/api/system/test`，返回标准 JSON 响应 ✅

---

## 6. 代码提交与推送
1.  提交信息：
    ```bash
    feat: 项目初始化，配置MySQL数据库，JPA自动建表配置完成
    ```
2.  推送至个人 GitHub 仓库，提交记录同步成功 ✅

---

## 7. 今日核心知识点
1.  **JPA 与 Hibernate 关系**：JPA 是 Java 持久化规范，Hibernate 是其主流实现，提供 ORM 映射与自动建表能力
2.  **ORM 思想**：Java 实体类通过 `@Entity` 注解映射数据库表，无需手动编写建表 SQL
3.  **`ddl-auto` 配置**：开发阶段使用 `update`，自动同步实体类与表结构，兼顾开发效率与数据安全
4.  **SpringBoot 分层架构**：Controller → Service → Repository → Entity，职责清晰，便于维护

---

## 8. 完成清单（全部 ✅）
- [x] 所有开发软件安装完成并验证
- [x] MySQL 8.0 安装配置完成，root 密码已记录
- [x] DataGrip 成功连接 MySQL，创建 `blog_db` 数据库
- [x] 项目 Fork 至个人 GitHub，Trae 克隆与 Git 配置完成
- [x] 项目数据库配置修改完成，连接信息正确
- [x] 项目启动成功，无报错
- [x] JPA 自动建表功能正常，表结构生成完成
- [x] 测试接口开发完成，访问验证通过
- [x] 代码提交并推送至个人 GitHub 仓库
