# jeecg 微服务部署

参考:

- [微服务部署](http://doc.jeecg.com/2186816)
- [微服务Docker镜像制作 3.1+](http://doc.jeecg.com/2656147)
- [单体切换微服务New](http://doc.jeecg.com/2704725)
- [Cent7 MySQL 的安装](https://blog.csdn.net/miaodichiyou/article/details/99289160)
- [How to Grant All Privileges on a Database in MySQL](https://chartio.com/resources/tutorials/how-to-grant-all-privileges-on-a-database-in-mysql/)

## 环境准备

### 数据库安装

- 安装 mysql
    - 直接在 centos 上安装
        - 安装客户端和服务器: `yum -y install mariadb  mariadb-devel  mariadb-server`。(由于MySQL在CentOS7中收费了，所以已经不支持MySQL了，取而代之在CentOS7内部集成了mariadb)
        - 启动 mysql 服务: `systemctl start mariadb.service`
        - 设置开机启动: `systemctl enable mariadb.service`
        - 设置管理员密码
        - 为 root 用户提供外网访问的能力(不仅仅是本机)
            1. 通过 `mysql -u root -p` 登录到本机
            2. 侦听所有 IP 的 3306 端口，而非仅为 localhost
                - 仅为 root 设置: `GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '12345678';` 
                - 创建新的账号上并设置: `GRANT ALL PRIVILEGES ON *.* TO 'fff'@'%' IDENTIFIED BY '12345678';`
                - 查看授权: `SHOW GRANTS FOR 'fff'@'%';`
                - 如果要删除新账号: `DROP USER 'fff'@'%';`
            3. 刷新权限: `FLUSH PRIVILEGES;`
    - 通过 docker 或 k8s 安装
- 安装 redis
    - 直接在 centos 上安装
        - 添加EPEL仓库: `yum install epel-release`
        - 更新yum源: `yum update`，经过测试，这一步可以跳过
        - 安装软件: `yum -y install redis`
        - 配置 */etc/redis.conf*，注释其中的 `bind 127.0.0.1`，使其可以被其他机子访问
        - 启动 redis 服务: `systemctl start redis`
        - 设置开机启动 `systemctl enable redis`

### 数据库初始化




