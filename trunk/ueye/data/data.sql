# MySQL-Front 5.1  (Build 3.57)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: cms
# ------------------------------------------------------
# Server version 5.0.22-community-nt

CREATE DATABASE `cms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cms`;

#
# Source for table ueye_account
#

CREATE TABLE `ueye_account` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `accountState` bit(1) NOT NULL,
  `email` varchar(255) default NULL,
  `expiryDate` date default NULL,
  `lockDate` date default NULL,
  `password` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `admin` bit(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_account
#
LOCK TABLES `ueye_account` WRITE;
/*!40000 ALTER TABLE `ueye_account` DISABLE KEYS */;

INSERT INTO `ueye_account` VALUES (1,NULL,NULL,b'0','rubys@vip.qq.com',NULL,NULL,'21232f297a57a5a743894a0e4a801fc3','administrator',b'1');
INSERT INTO `ueye_account` VALUES (2,'2010-05-31 23:42:46',NULL,b'0','rubys@vip.qq.com',NULL,NULL,'21232f297a57a5a743894a0e4a801fc3','admin',b'0');
INSERT INTO `ueye_account` VALUES (3,'2010-06-18 11:19:28',NULL,b'0','rubys@vip.qq.com',NULL,NULL,'21232f297a57a5a743894a0e4a801fc3','rubys',b'0');
INSERT INTO `ueye_account` VALUES (5,'2010-07-27 23:44:37',NULL,b'0','',NULL,NULL,'d41d8cd98f00b204e9800998ecf8427e','test',b'0');
INSERT INTO `ueye_account` VALUES (6,'2010-08-15 15:04:32',NULL,b'0','',NULL,NULL,'21232f297a57a5a743894a0e4a801fc3','First',b'0');
/*!40000 ALTER TABLE `ueye_account` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_accountrole
#

CREATE TABLE `ueye_accountrole` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `accountId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  `userGroupId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_accountrole
#
LOCK TABLES `ueye_accountrole` WRITE;
/*!40000 ALTER TABLE `ueye_accountrole` DISABLE KEYS */;

INSERT INTO `ueye_accountrole` VALUES (2,'2010-06-18 19:44:47',NULL,0,4,2);
INSERT INTO `ueye_accountrole` VALUES (5,'2010-07-27 23:47:19',NULL,0,1,3);
INSERT INTO `ueye_accountrole` VALUES (6,'2010-07-27 23:47:19',NULL,0,3,3);
INSERT INTO `ueye_accountrole` VALUES (8,'2010-07-27 23:47:19',NULL,0,2,3);
/*!40000 ALTER TABLE `ueye_accountrole` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_function
#

CREATE TABLE `ueye_function` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `functionCode` varchar(255) default NULL,
  `functionDesc` varchar(255) default NULL,
  `functionName` varchar(255) default NULL,
  `moduleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_function
#
LOCK TABLES `ueye_function` WRITE;
/*!40000 ALTER TABLE `ueye_function` DISABLE KEYS */;

INSERT INTO `ueye_function` VALUES (1,'2010-06-18 19:39:36',NULL,'insert','添加','添加',3);
INSERT INTO `ueye_function` VALUES (2,'2010-06-18 19:39:53',NULL,'update','修改','修改',3);
INSERT INTO `ueye_function` VALUES (3,'2010-06-18 19:40:04',NULL,'delete','删除','删除',3);
INSERT INTO `ueye_function` VALUES (4,'2010-06-25 16:53:02',NULL,'update','修改','修改',20);
INSERT INTO `ueye_function` VALUES (5,'2010-08-08 23:51:21',NULL,'insert','','添加',20);
/*!40000 ALTER TABLE `ueye_function` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_module
#

CREATE TABLE `ueye_module` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `expanded` bit(1) NOT NULL,
  `leaf` bit(1) NOT NULL,
  `width` int(11) NOT NULL,
  `action` varchar(255) default NULL,
  `icon` varchar(255) default NULL,
  `label` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `orderValue` int(11) NOT NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKDF6A226FD5FEFFC6` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_module
#
LOCK TABLES `ueye_module` WRITE;
/*!40000 ALTER TABLE `ueye_module` DISABLE KEYS */;

INSERT INTO `ueye_module` VALUES (1,'2010-05-31 23:35:24',NULL,b'0',b'0',0,'','folder_16_pad.gif','CMS',NULL,0,NULL);
INSERT INTO `ueye_module` VALUES (2,'2010-06-19 15:15:37',NULL,b'0',b'0',1,'','folder_16_pad.gif','系统管理',NULL,10,1);
INSERT INTO `ueye_module` VALUES (3,'2010-05-31 23:36:21',NULL,b'0',b'1',2,'account','folder_16_pad.gif','用户管理',NULL,0,2);
INSERT INTO `ueye_module` VALUES (4,'2010-05-31 23:38:13',NULL,b'0',b'1',2,'module','folder_16_pad.gif','模块管理',NULL,0,2);
INSERT INTO `ueye_module` VALUES (5,'2010-05-31 23:40:05',NULL,b'0',b'1',2,'role','folder_16_pad.gif','角色管理',NULL,0,2);
INSERT INTO `ueye_module` VALUES (17,'2010-06-18 19:30:08',NULL,b'0',b'1',2,'user-group','folder_16_pad.gif','用户组',NULL,0,2);
INSERT INTO `ueye_module` VALUES (18,'2010-06-18 19:38:38',NULL,b'0',b'1',2,'function','folder_16_pad.gif','功能管理',NULL,0,2);

/*!40000 ALTER TABLE `ueye_module` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_role
#

CREATE TABLE `ueye_role` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `roleName` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_role
#
LOCK TABLES `ueye_role` WRITE;
/*!40000 ALTER TABLE `ueye_role` DISABLE KEYS */;

INSERT INTO `ueye_role` VALUES (1,'2010-05-31 23:41:36',NULL,'超级管理员','超级管理员');
INSERT INTO `ueye_role` VALUES (2,'2010-05-31 23:41:46',NULL,'管理员','管理员');
INSERT INTO `ueye_role` VALUES (3,'2010-05-31 23:41:59',NULL,'普通用户','普通用户');
INSERT INTO `ueye_role` VALUES (4,'2010-06-18 19:43:14',NULL,'test','test');
/*!40000 ALTER TABLE `ueye_role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_rolefunction
#

CREATE TABLE `ueye_rolefunction` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `functionId` varchar(255) default NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_rolefunction
#
LOCK TABLES `ueye_rolefunction` WRITE;
/*!40000 ALTER TABLE `ueye_rolefunction` DISABLE KEYS */;

/*!40000 ALTER TABLE `ueye_rolefunction` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_rolemodule
#

CREATE TABLE `ueye_rolemodule` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `moduleId` varchar(255) default NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_rolemodule
#
LOCK TABLES `ueye_rolemodule` WRITE;
/*!40000 ALTER TABLE `ueye_rolemodule` DISABLE KEYS */;

INSERT INTO `ueye_rolemodule` VALUES (1,'2010-06-17 22:05:22',NULL,'2,3,4,5,12,13,15',3);
INSERT INTO `ueye_rolemodule` VALUES (3,'2010-06-23 13:57:14',NULL,'19,20,21',5);
INSERT INTO `ueye_rolemodule` VALUES (4,'2010-07-27 23:46:43',NULL,'19,20,21,22,24',4);
/*!40000 ALTER TABLE `ueye_rolemodule` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_usergroup
#

CREATE TABLE `ueye_usergroup` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `groupDesc` varchar(255) default NULL,
  `groupName` varchar(255) default NULL,
  `rolesId` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_usergroup
#
LOCK TABLES `ueye_usergroup` WRITE;
/*!40000 ALTER TABLE `ueye_usergroup` DISABLE KEYS */;

INSERT INTO `ueye_usergroup` VALUES (1,'2010-06-18 19:30:38',NULL,'管理员','管理员',NULL);
INSERT INTO `ueye_usergroup` VALUES (2,'2010-06-18 19:42:17',NULL,'test','test',NULL);
INSERT INTO `ueye_usergroup` VALUES (3,'2010-07-27 23:47:07',NULL,'userGroup','userGroup',NULL);
/*!40000 ALTER TABLE `ueye_usergroup` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table ueye_usertogroup
#

CREATE TABLE `ueye_usertogroup` (
  `id` bigint(20) NOT NULL,
  `created` datetime default NULL,
  `deleteFlag` varchar(255) default NULL,
  `account` bigint(20) NOT NULL,
  `userGroup` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table ueye_usertogroup
#
LOCK TABLES `ueye_usertogroup` WRITE;
/*!40000 ALTER TABLE `ueye_usertogroup` DISABLE KEYS */;

INSERT INTO `ueye_usertogroup` VALUES (1,'2010-06-18 19:45:47',NULL,3,2);
INSERT INTO `ueye_usertogroup` VALUES (3,'2010-07-27 23:45:14',NULL,5,1);
INSERT INTO `ueye_usertogroup` VALUES (4,'2010-07-27 23:45:14',NULL,5,2);
INSERT INTO `ueye_usertogroup` VALUES (5,'2010-07-27 23:47:30',NULL,1,3);
INSERT INTO `ueye_usertogroup` VALUES (7,'2010-07-27 23:47:30',NULL,3,3);
INSERT INTO `ueye_usertogroup` VALUES (8,'2010-07-27 23:47:30',NULL,5,3);
/*!40000 ALTER TABLE `ueye_usertogroup` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table ueye_module
#

ALTER TABLE `ueye_module`
ADD CONSTRAINT `FKDF6A226FD5FEFFC6` FOREIGN KEY (`parent_id`) REFERENCES `ueye_module` (`id`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
