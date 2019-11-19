DROP TABLE t_customer;
CREATE TABLE t_customer(
    customerId VARCHAR(64) NOT NULL   COMMENT '用户Id' ,
    customerName VARCHAR(128) NOT NULL   COMMENT '用户名' ,
    email VARCHAR(128) NOT NULL   COMMENT '邮箱' ,
    status INT NOT NULL  DEFAULT 0 COMMENT '用户状态 0: 正常; 1: 锁定; 99: 删除' ,
    createTime DATETIME NOT NULL   COMMENT '创建时间' ,
    updateTime DATETIME NOT NULL   COMMENT '更新时间' ,
    PRIMARY KEY (customerId)
) COMMENT = '用户信息表';

ALTER TABLE t_customer ADD UNIQUE unique_customer_customerName(customerName);
ALTER TABLE t_customer COMMENT '用户信息表';
DROP TABLE t_login;
CREATE TABLE t_login(
    customerId VARCHAR(64) NOT NULL   COMMENT '用户Id' ,
    password VARCHAR(64) NOT NULL   COMMENT '用户密码' ,
    createTime DATETIME NOT NULL   COMMENT '创建时间' ,
    updateTime DATETIME NOT NULL   COMMENT '更新时间' ,
    PRIMARY KEY (customerId)
) COMMENT = '用户登录表 ';

ALTER TABLE t_login COMMENT '用户登录表';
DROP TABLE t_login_auth;
CREATE TABLE t_login_auth(
    customerId VARCHAR(64) NOT NULL   COMMENT '用户Id' ,
    token VARCHAR(64) NOT NULL   COMMENT '鉴权token' ,
    updateTime DATETIME NOT NULL   COMMENT '更新时间' 
) COMMENT = '用户登录认证表 ';

ALTER TABLE t_login_auth ADD UNIQUE unique_customerId_token(customerId,token);
ALTER TABLE t_login_auth COMMENT '用户登录认证表';