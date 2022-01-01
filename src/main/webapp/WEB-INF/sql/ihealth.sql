/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : ihealth

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 23/11/2021 21:09:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '13566320197', '张三');
INSERT INTO `admin` VALUES (2, '19121542079', '林明恺');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '科室id',
  `dep_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '科室名',
  `hospital_id` int(11) NOT NULL COMMENT '所属医院',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_hospital`(`hospital_id`) USING BTREE,
  CONSTRAINT `fk_hospital` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '耳鼻喉科', 1);

-- ----------------------------
-- Table structure for doctors
-- ----------------------------
DROP TABLE IF EXISTS `doctors`;
CREATE TABLE `doctors`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `dPhone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '医生电话号码',
  `dName` varchar(15) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '医生姓名',
  `dSex` varchar(5) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '医生性别',
  `dBirth` date NULL DEFAULT NULL COMMENT '出生日期',
  `dDepartment` int(4) NOT NULL COMMENT '科室',
  `dCareer` varchar(15) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '职称',
  `dDescription` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '概况',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`dPhone`) USING BTREE,
  INDEX `department_id_fk`(`dDepartment`) USING BTREE,
  CONSTRAINT `department_id_fk` FOREIGN KEY (`dDepartment`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of doctors
-- ----------------------------
INSERT INTO `doctors` VALUES (1, '13801757332', '李四', '女', '1990-02-05', 1, '主任医师', NULL);
INSERT INTO `doctors` VALUES (2, '13816635987', '王五', '男', '1983-05-29', 1, '主治医师', NULL);

-- ----------------------------
-- Table structure for expert
-- ----------------------------
DROP TABLE IF EXISTS `expert`;
CREATE TABLE `expert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expert_id` int(11) NOT NULL COMMENT '专家id',
  `total` int(4) NOT NULL DEFAULT 0 COMMENT '总量',
  `remain` int(4) NOT NULL DEFAULT 0 COMMENT '余量',
  `regist_date` date NOT NULL COMMENT '挂号日期',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '挂号时间段',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `expert_id`(`expert_id`) USING BTREE,
  CONSTRAINT `expert_ibfk_1` FOREIGN KEY (`expert_id`) REFERENCES `doctors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of expert
-- ----------------------------
INSERT INTO `expert` VALUES (1, 1, 10, 10, '2021-12-10', '8:30-9:30');

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital`  (
  `hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '医院序号',
  `hname` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医院名',
  `hphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hospital
-- ----------------------------
INSERT INTO `hospital` VALUES (1, '上海市第六人民医院', '64993215');

-- ----------------------------
-- Table structure for normal
-- ----------------------------
DROP TABLE IF EXISTS `normal`;
CREATE TABLE `normal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `normal_id` int(11) NOT NULL,
  `total` int(11) NOT NULL DEFAULT 0,
  `remain` int(11) NOT NULL DEFAULT 0,
  `regist_date` date NOT NULL,
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `normal_id`(`normal_id`) USING BTREE,
  CONSTRAINT `normal_ibfk_1` FOREIGN KEY (`normal_id`) REFERENCES `doctors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of normal
-- ----------------------------
INSERT INTO `normal` VALUES (1, 2, 10, 10, '2021-12-16', '8:30-9:30');

-- ----------------------------
-- Table structure for operate_record
-- ----------------------------
DROP TABLE IF EXISTS `operate_record`;
CREATE TABLE `operate_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of operate_record
-- ----------------------------
INSERT INTO `operate_record` VALUES (1, '19121542079', '127.0.0.1', '2021-11-23 13:43:43', 'login');
INSERT INTO `operate_record` VALUES (2, '19121542079', '127.0.0.1', '2021-11-23 13:44:02', 'logout');
INSERT INTO `operate_record` VALUES (3, '19121544395', '127.0.0.1', '2021-11-23 13:53:49', 'regist');
INSERT INTO `operate_record` VALUES (4, '13566320197', '127.0.0.1', '2021-11-23 13:56:38', 'login');
INSERT INTO `operate_record` VALUES (5, '19121542079', '127.0.0.1', '2021-11-23 18:37:39', 'login');
INSERT INTO `operate_record` VALUES (6, '19121542079', '127.0.0.1', '2021-11-23 19:05:08', 'logout');
INSERT INTO `operate_record` VALUES (7, '13566320197', '127.0.0.1', '2021-11-23 19:06:03', 'login');
INSERT INTO `operate_record` VALUES (8, '13566320197', '127.0.0.1', '2021-11-23 21:06:25', 'logout');
INSERT INTO `operate_record` VALUES (9, '19121842279', '127.0.0.1', '2021-11-23 21:06:43', 'login');

-- ----------------------------
-- Table structure for patients
-- ----------------------------
DROP TABLE IF EXISTS `patients`;
CREATE TABLE `patients`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '患者id',
  `pPhone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '患者电话号码',
  `pName` varchar(15) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '患者姓名',
  `pSex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `pBirth` date NULL DEFAULT NULL COMMENT '出生日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`pPhone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of patients
-- ----------------------------
INSERT INTO `patients` VALUES (1, '19121842279', '林星', '男', '2001-03-30');
INSERT INTO `patients` VALUES (2, '19121544395', '武浩宇', NULL, NULL);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `pid` int(4) NOT NULL COMMENT '患者序号',
  `did` int(4) NOT NULL COMMENT '医生序号',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `cost` decimal(10, 2) NOT NULL COMMENT '挂号费',
  `register_date` date NOT NULL COMMENT '挂号日期',
  `department` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '挂号科室',
  `hospital` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '挂号医院',
  PRIMARY KEY (`pid`, `did`) USING BTREE,
  INDEX `did`(`did`) USING BTREE,
  CONSTRAINT `record_ibfk_2` FOREIGN KEY (`did`) REFERENCES `doctors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `patients` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for registerion
-- ----------------------------
DROP TABLE IF EXISTS `registerion`;
CREATE TABLE `registerion`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `dep_id` int(4) NOT NULL COMMENT '科室',
  `normal_cost` decimal(10, 2) NOT NULL COMMENT '普通门诊费用',
  `expert_cost` decimal(10, 2) NOT NULL COMMENT '专家门诊费用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_depid`(`dep_id`) USING BTREE,
  CONSTRAINT `fk_depid` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of registerion
-- ----------------------------
INSERT INTO `registerion` VALUES (1, 1, 15.00, 40.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的电话号码',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的密码',
  `type` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL COMMENT '类别：患者/医生/管理员',
  PRIMARY KEY (`id`, `phone`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '19121542079', '12345678', 'admin');
INSERT INTO `user` VALUES (2, '19121544395', '123456789', 'patient');
INSERT INTO `user` VALUES (3, '13566320197', '12345678', 'admin');
INSERT INTO `user` VALUES (4, '19121842279', '12345678', 'patient');

-- ----------------------------
-- View structure for expert_regist_info
-- ----------------------------
DROP VIEW IF EXISTS `expert_regist_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `expert_regist_info` AS select `hname` hospital,`dep_name` department,`dName` doctor,`dSex` sex,`dCareer` title,`total`,`remain`,`regist_date`,`time`,`dDescription`
from expert 
left join doctors on expert_id=doctors.id
left join department on doctors.id=department.id
left join hospital on hospital_id=hospital.hid ;

-- ----------------------------
-- View structure for record_view
-- ----------------------------
DROP VIEW IF EXISTS `record_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `record_view` AS SELECT a.id,a.username,a.ip,a.time,a.type operate,b.type FROM operate_record a
left join user b on a.username=b.phone ;

-- ----------------------------
-- Procedure structure for add_user
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_user`;
delimiter ;;
CREATE PROCEDURE `add_user`(in phone varchar(20),in password varchar(20),in type varchar(20),in username varchar(15),out count int)
begin
		insert into user (phone,password,type) values (phone,password,type);
		SELECT ROW_COUNT() into count;
		if type='patient' then
			insert into patients (pName,pPhone) values (username,phone);
		elseif type='doctor' then
			insert into doctors (dName,dPhone) values (username,phone);
		elseif type='admin' then
			insert into admin (name,username) values (username,phone);
		end if;
		SELECT ROW_COUNT() into count;
		
	end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
