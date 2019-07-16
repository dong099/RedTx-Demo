SET FOREIGN_KEY_CHECKS=0;


create database if not exists `db_c` default charset utf8 collate utf8_general_ci;

use db_c;

-- ----------------------------
-- Table structure for user_roles
-- mybatis中数据库不能自动识别大写字母，如userName，它只认识user_name
-- ----------------------------
DROP TABLE IF EXISTS demo_user_roles;
CREATE TABLE  demo_user_roles (
  `role_name` varchar(255) NOT NULL PRIMARY KEY,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS demo_user;
CREATE TABLE demo_user(
oid_index    INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id      VARCHAR(50) NOT NULL UNIQUE COMMENT '授权唯一标识',
password     VARCHAR(64) NOT NULL COMMENT '授权密钥',
user_name    VARCHAR(100),
email        VARCHAR(100),
fk_role        varchar(255) NOT NULL,
user_status  char(1) NOT NULL DEFAULT '1' COMMENT '用户状态,1：正常，0：无效',
login_failed_cnt INT UNSIGNED DEFAULT 0,
latest_active_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
paswd_reset tinyint(1) NOT NULL DEFAULT '1',
last_paswd_changed timestamp NULL DEFAULT NULL,
version_id smallint(5) unsigned DEFAULT '0',
last_upd_by timestamp NULL,
INDEX (`fk_role`),
FOREIGN KEY (`fk_role`) REFERENCES `demo_user_roles` (`role_name`)
) ENGINE=InnoDB COMMENT='api平台用户信息表';;

 

insert into demo_user_roles values ('Super','Super User');
insert into demo_user_roles values ('Admin','Admin User');
insert into demo_user_roles values ('Operator','Operating User');
insert into demo_user_roles values ('Guest','Guest User');


INSERT INTO demo_user (oid_index,user_id,password,user_name,email,fk_role,user_status,login_failed_cnt) 
VALUES (1,'allan.zhou','5f4dcc3b5aa765d61d8327deb882cf99','Allan Zhou','email@139.com','Admin','1',0);


INSERT INTO demo_user (oid_index,user_id,password,user_name,email,fk_role,user_status,login_failed_cnt) 
VALUES (2,'lisi','5f4dcc3b5aa765d61d8327deb882cf99','lisi','email@139.com','Admin','1',0);


INSERT INTO demo_user (oid_index,user_id,password,user_name,email,fk_role,user_status,login_failed_cnt) 
VALUES (3,'wanger','5f4dcc3b5aa765d61d8327deb882cf99','wanger','email@139.com','Admin','1',0);


INSERT INTO demo_user (oid_index,user_id,password,user_name,email,fk_role,user_status,login_failed_cnt) 
VALUES (4,'wangsan','5f4dcc3b5aa765d61d8327deb882cf99','wangsan','email@139.com','Admin','1',0);


