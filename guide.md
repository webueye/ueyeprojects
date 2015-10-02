# 说明 #

基于SSH的动态树+部分权限管理


# 详细说明 #

数据库操作的两种方式：

一、
  * 建立数据库
  * 修改 resource 目录下的配置文件 **applicationContext.xml**
  * 候改为相应的数据库名以及登陆该数据库的用户名和密码
  * 导入 data.sql 中的数据

<font color='red'><b>注：</b></font>
  * 如果在没用脚本导入数据情况下
  * 需要在数据中添加用户名，密码为MD5加密后的值
  * 手动添加模块时（登陆后才可以操作），需要添加两次才能显示，原因：
    1. 第一次添加的模块为根目录，里面的数据没有要求，是不显示的
    1. 第二次添加的模块才会显示在列表中
  * 手动添加模块的URL ： ip:port/contextPath/module

二、
  * JDK 要求1.6，如是1.5环境，则需要把 common-dbcp1.4（1.6的） 换成1.3的（1.4-1.5）
  * tomcat 要求是 6.0 的

<font color='red'>
如有问题请到 <a href='http://code.google.com/p/ueyeprojects/issues/list'>Issues</a> 提出或发邮件：ofbizs@gmail.com ，谢谢配合。 </font>