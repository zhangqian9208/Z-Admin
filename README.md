# Z-Admin后台管理轻量级项目模板V0.3

#### 演示地址
  - 线上演示地址：http://z-admin.findawayedu.com:5621/
  - 线上文档地址：http://z-admin.findawayedu.com:5621/doc.html

#### 更新日志
  - v0.3 将删除修改为逻辑删除，优化了自动填充更新人字段方法，缓存方案更改为Spring Cache，集成Knife4j框架
  - v0.2 将spring boot版本更新到2.4.5,mybatis plus版本更新到3.4.5
  - v0.1 第一版本提交

#### 交流QQ群
QQ群：867492532

#### 项目介绍
SpringBoot后台管理系统模板，前后端不分离的轻量级后台管理系统模板，包含用户管理、部门管理、模块管理、角色管理、异常日志捕获功能，前台页面权限可以控制到按钮，页面菜单可以收起，包含tabs页面。
主要适合小公司、小团队、个人等使用，一个后端人员，就可以进行二次开发。

#### 技术选型
- 后端技术：SpringBoot + mybatisPlus
- 前端技术：vue + element-ui +jQuery


#### 功能列表

- 用户管理：用于管理后台系统的用户，可进行增删改查，角色授权；
- 角色管理：不同模块的权限合集，通过角色给用户分配权限。
- 模块管理：用于配置系统菜单，可以定义请求地址与图标。
- 部门管理：通过不同的部门来管理和区分用户。
- 系统异常日志：用于监视系统运行的异常日志，可以自定义捕获位置。
- 文件上传：内置了阿里云OSS上传代码，可以开放使用。



#### 环境及插件要求

- 环境及插件
  - Jdk8+
  - Mysql5.5+
  - Maven
  - Lombok

- 导入项目
  - IntelliJ IDEA：Import Project -> Import Project from external model -> Maven

- 运行项目
  - 通过Java应用方式运行admin模块下的com.linln.admin.BootApplication文件
  - 数据库配置：根据sql语句建立数据库，修改配置中的数据库连接地址即可
  - 本地访问地址：http://localhost:18078
  - 线上演示地址：http://z-admin.findawayedu.com:5621/
  - 线上文档地址：http://z-admin.findawayedu.com:5621/doc.html
  - 默认帐号密码：zhangqian/123


#### 使用说明
待完善

#### 参与贡献

- 欢迎大家参与本项目贡献，可先加入QQ群进行沟通


#### 界面展示

- 登录页面
![输入图片说明](https://foruda.gitee.com/images/1660492654788230545/屏幕截图.png "屏幕截图.png")
- 部门管理
![输入图片说明](https://foruda.gitee.com/images/1660492659741164018/屏幕截图.png "屏幕截图.png")
- 角色授权
![输入图片说明](https://foruda.gitee.com/images/1660492664928185801/屏幕截图.png "屏幕截图.png")
- 用户授权
![输入图片说明](https://foruda.gitee.com/images/1660492669493490019/屏幕截图.png "屏幕截图.png")