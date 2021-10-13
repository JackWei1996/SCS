# 校园卡管理系统

## 介绍
        目前的各大院校几乎都有卡在使用，广大师生在得益于智能卡带来的方便的同时，也需要有人管理和服务，但是不可能所有的信息都要人工去查询去管理，所以我们需要一个校园卡自助服务系统，智能管理，方便高效。针对大学学生日常生活和学习管理情况的实地调查加自己的亲身体验，了解了目前应用关于大学的校园一卡通管理系统的应用情况，并充分体会到该模式相对于人工管理模式的简单、高效。
        基于以上情况，目的是设计一个模拟大学校园一卡通的自助服务系统，在设计过程中，加深对校园一卡通的了解，增强对数据库知识的理解及SQL语言的实际应用，训练设计开发数据库的能力。


## 数据库
  本数据库采用Access 2016版本

## 所需环境
1.  TextPad
2.  Access 2016
3.  JDK1.7


## 安装教程
详细软件安装可以到**相关博客**进行查看

1.  将`src`文件下载到本地，然后用TextPad打开
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105112_e8798880_1590078.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105208_7cbf7674_1590078.png "屏幕截图.png")
不知道怎么配置javac的可以参考[TextPad安装环境配置](https://jackwei.blog.csdn.net/article/details/86914950)环境配置那一节
如果出现：

```
G:\Jack\java\κ�ƺ�java�γ����\src\Main.java:899: ����: ����GBK�Ĳ���ӳ���ַ�
						JOptionPane.showMessageDialog(null, "充�?�成�?");
						                                       ^
100 ������
```

需要配置一下在javac里面配置如下：
` -encoding UTF-8 `
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105620_48f03ab7_1590078.png "屏幕截图.png")
然后在javac一下：
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105722_71896fce_1590078.png "屏幕截图.png")

2.  运行
编译通过就可以运行了
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105800_54232cb1_1590078.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105845_a5f0d592_1590078.png "屏幕截图.png")

## 运行截图

1.  首页
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105938_18d901e2_1590078.png "屏幕截图.png")
2.  银行服务
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/105950_73350465_1590078.png "屏幕截图.png")
3.  查询银行余额
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110205_42db42ed_1590078.png "屏幕截图.png")
4.  银行转账
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110221_51707ba2_1590078.png "屏幕截图.png")
5.  自购电费
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110232_bc1273e3_1590078.png "屏幕截图.png")
6.  自购水费
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110252_a0c3454a_1590078.png "屏幕截图.png")
7.  自助服务
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110303_faa2386e_1590078.png "屏幕截图.png")
8.  信息查询
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110318_6e142cd0_1590078.png "屏幕截图.png")
9.  校园卡挂失
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110325_69173f04_1590078.png "屏幕截图.png")
10.  修改密码
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110344_ca2c51d5_1590078.png "屏幕截图.png")
11.  取原卡金额
![输入图片说明](https://images.gitee.com/uploads/images/2021/1013/110352_695817ad_1590078.png "屏幕截图.png")



## 相关博客

1.  [Microsoft Access 2016安装教程](https://blog.csdn.net/WeiHao0240/article/details/120672363)
2.  [Java使用ODBC连接Access数据库](https://blog.csdn.net/WeiHao0240/article/details/120727203)
3.  [TextPad安装环境配置](https://jackwei.blog.csdn.net/article/details/86914950)
