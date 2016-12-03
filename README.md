# spring-restful-authorization
基于Spring-Boot与Redis缓存机制的RESTful风格用户登录机制

首先非常感谢ScienJus的教程与源码，最初学习的Restful风格的token登录设计机制就是来源于他的文章。
### 传送门如下：
- ScienJus Restful Token登录 Github源码
`https://github.com/ScienJus/spring-restful-authorization/`
- RESTful登录设计（基于Spring及Redis的Token鉴权）
`http://www.scienjus.com/restful-token-authorization/`
- 封装库源码
`https://github.com/ScienJus/spring-authorization-manager`
- Demo Github源码
`https://github.com/ScienJus/spring-authorization-manager-demo/`

### 使用说明
1. 创建数据库和数据表User，表项为username,password,nickname
2. 配置并redis-server
3. 修改`src/main/resources/`下 Spring-Boot启动配置文件`application.properties`文件
4. 在项目根目录终端输入`mvc spring-boot:run` 则可以启动项目。ctrl + c终止进程。
5. 演示登录：利用postman发送POST tokens/请求，（请求参数）在username项输入admin、password项输入password，查看返回结果得到userId和token
6. 演示退出登录：利用postman发送DELETE tokens/请求，在Headers添加authorization（key），填写用userId和token以"_"拼接得到的字符串（value），如果返回码为200则成功。重复一次操作，返回码将变为401
