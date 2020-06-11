
# 一.社团管理系统

## 1.介绍
基于前后端分离开发方式，使用Spring Boot、vue等技术开发社团管理系统。系统功能如下图所示。![](系统说明/系统功能.png)

## 2.系统设计
系统设计说明

1. 后端使用Spring boot、mybatis、redis、mybatisPlus，系统架构如下图所示

![](系统说明/系统架构.png)

## 3. 后端开发简介

* 系统后端多模块开发，后端码云地址：<https://gitee.com/fengsam618/association>

* 模块Association-generation支持代码生成，基于MyBatis-Plus代码生成器，运行该模块main方法Generator，能够生成Dao层、service层通用代码，可以提高后端开发效率。
* association-core 提供一些常用工具类，spring全局异常配置、定义后端rest接口返回json数据格式、redis工具类配置，springboot跨域处理。
* 模块Association-API调用模块Association-service，提供前台访问RESTful接口。
* 模块Association-admin调用模块Association-service，提供后台访问RESTful接口。

 后端开发工具idea，系统maven多模块，如下图。

![](系统说明/后端多模块开发.png)

## 4. 前端简介

* 前台页面单独开发一个工程vue-club，所有前台页面代码都写在vue-club中.

  ![](系统说明/club前台项目目录结构.png)

* 后台页面单独开发一个工程club-admin，所有前台页面代码都写在club-admin中.

![](系统说明/后台clu-admin.png)

## 5. 系统运行效果

![](运行效果图/club.gif)

* 系统登录页面

![](运行效果图/登录页面.png)

* 前台活动列表页面

![](运行效果图/活动列表.png)

+ 前台社团检索页面页面

![](运行效果图/社团检索页面.png)

* 后台新闻列表页面

![](运行效果图/新闻列表.gif)

* 后台社团列表页面

![](运行效果图/社团列表.png)



## 6. 系统启动

* assosiation-clubt提供后台访问的restful接口。

6.1. 安装系统软件依赖。后端需要安装java、maven、mysql、redis。
6.2. 启动检查数据库MySQL、Redis是否启动，执行根目录sql脚本。
6.3. 从github（）下载代码，修改mysql、redis数据库连接用户名、密码。
6.4. 进入项目根目录，在cmd窗口执行mvn package，将所有模块打包成jar，可以参考deployment文件夹
6.5.启动后端。进入assosiation-rest、assosiation-admin两个模块，assosiation-rest提供前台访问的restful接口， assosiation-admin提供后台访问的restful接口。
6.6. 启动前端。进入club/club-admin根目录，打开cmd窗口，输入命令npm run dev运行前台两个项目vue-club、club-admin。（编译很慢，需要提前编译）

## 7. 浏览器访问

1. http://127.0.0.1:80/>   本地运行，系统前台访问页面
2. http://127.0.0.1:8080/>   本地运行，系统后台访问页面
3. http://127.0.0.1:8081/club/api/swagger-ui.html>   前台接口访问文档
4. http://127.0.0.1:8082/club/admin/swagger-ui.html>  后台接口访问文档





##  8.系统设计

1. 系统模块设计及技术选择。

* 后端使用maven构建五个模块模块，使用Spring Boot 、springMVC、mybatis、mybatisPlus、pageHelper、Redis等技术开发。使用 swagger UI生成后端接口API文档，可以直接在浏览器浏览。
* 前端使用Vue、Vue router、vue Axios、Element UI等技术开发。

2. 开发方式前后端分离。

* 前后代码端耦合度低，可以单独部署测试，有利于后期维护。

3. 数据库持久层，使用MySQL存储数据，采用Redis缓存数据。

  * Redis基于内存非关系数据库，读写速度非常快，提高系统数据读写速速。

4. 系统登录，采用动态token验证身份，后端所有操作，必须提供token才能访问。

* 用户输入正确的用户名、密码，请求登录接口，后端会返回一个随机的token（64位uuid），同时系统后端将uuid最为key，值为用户ID，存储Redis数据库中，同时设置键数据库有效时间40分钟。后端有一个拦截器拦截用户所有请求，会根据前台传入token去redis数据库查找，只有token正确，才能在redis查找到用户id，系统才放行。

5. 使用nginx作为文件服务器

 * 配置nginx，将一个请求url（比喻：localhost：8013/fileSever）与指定文件夹映射，系统后端将文件上传该文件夹目录下,前端浏览localhost：8013/fileSever/filePath就可以访问该文件夹。



## 9.建议nginx部署测试

1. maven将后端接口打包成jar，java -jar可以运行后端springBoot jar包，打包后端部署测试，提供前台访问页面接口。

2. 本地nginx部署，启动测试，http://127.0.0.1:8011/>   前台访问页面，页面目前只适配电脑，

   http://127.0.0.1:8012/>   后台访问页面，页面目前只适配电脑





## 10. 备注

* 项目码云地址（建议国内访问）：https://gitee.com/fengsam618/association

* 项目github地址 ：https://github.com/fengsam6/association
* 文件上传位置，由后端配置。前端访问文件路径，开发时候可以vue 接口代理映射，部署可以使用nginx代理映射。
* 如果觉得写得不错，可以给个start。



