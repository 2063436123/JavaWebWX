## 这是什么？

这是我们` JAVA`程序设计课要求实现的大作业，课题是任选的，我们两人开发了这款微信小程序-SCU约球，实现基础的发起活动，签到活动功能，由于是大一初学者:cat:所以只使用了最基础的技术：`Tomcat`和`WXML` `WXSS` `JS`的基础语法以及后端数据库`MySQL`的连接。这个程序也可以作为微信小程序+后端开发搭建快速入门的框架。

## 简介

代码分为两个部分，一个是微信小程序的发布代码，第二个是部署于服务器上的`Java`代码。

- 微信小程序文件及文件夹主要组成
    - `image` 存放图片
    - `pages` 各个界面
    - `util.js` 获取日期时间
    - `app.js` 每次进入小程序时调用
    - `app.json` 注册每个页面
- Java代码组成
    - WX package 所有的`Servlet`代码
        - 三个工具类`HTTPUtil`，`Json`,`Token`
        - 多个`Servlet`与微信小程序的request请求分别对应
    - com.pedro package 里的代码
        - entity 实体类
        - repository 数据库操作类
        - test 测试数据库功能
        - utils 使用连接池的类
    
## Demo

