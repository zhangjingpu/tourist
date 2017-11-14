/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : tourist

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-07-23 21:13:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `di_table`
-- ----------------------------
DROP TABLE IF EXISTS `di_table`;
CREATE TABLE `di_table` (
  `id` int(225) NOT NULL COMMENT '天气主键',
  `Celsius` double(100,3) NOT NULL COMMENT '摄氏度',
  `Fahrenheit` double(100,3) NOT NULL COMMENT '华氏度',
  `relative_humidity` double(100,0) NOT NULL COMMENT '想对湿度 %',
  `time_hour` int(20) DEFAULT NULL COMMENT '时间(h)',
  `time_date` date DEFAULT NULL COMMENT '日期',
  `DI` double(100,0) DEFAULT NULL COMMENT '天气舒适指数',
  `del_flag` int(2) DEFAULT NULL,
  `type_weather` int(10) DEFAULT NULL COMMENT '感觉程度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of di_table
-- ----------------------------

-- ----------------------------
-- Table structure for `park`
-- ----------------------------
DROP TABLE IF EXISTS `park`;
CREATE TABLE `park` (
  `id` int(225) NOT NULL COMMENT '停车场主键',
  `parking_name` varchar(20) DEFAULT NULL COMMENT '停车场名字',
  `scenic_id` int(255) DEFAULT NULL COMMENT '景区ID',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建者',
  `updator` varchar(20) DEFAULT '' COMMENT '更新者',
  `money` int(5) DEFAULT NULL COMMENT '停车费',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of park
-- ----------------------------

-- ----------------------------
-- Table structure for `parking_car`
-- ----------------------------
DROP TABLE IF EXISTS `parking_car`;
CREATE TABLE `parking_car` (
  `id` int(255) NOT NULL,
  `license_car` varchar(10) NOT NULL COMMENT '汽车牌照',
  `enter_time` date NOT NULL COMMENT '进入时间',
  `leave_time` date DEFAULT NULL COMMENT '离开时间',
  `car_type` int(2) NOT NULL COMMENT '汽车类型',
  `park_id` int(225) NOT NULL COMMENT '停车场ID',
  `paymoney` int(20) DEFAULT NULL COMMENT '停车费',
  `del_flag` int(2) NOT NULL COMMENT '删除标记',
  `science_id` int(225) NOT NULL COMMENT '景区id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parking_car
-- ----------------------------

-- ----------------------------
-- Table structure for `scenicspot`
-- ----------------------------
DROP TABLE IF EXISTS `scenicspot`;
CREATE TABLE `scenicspot` (
  `id` int(255) NOT NULL,
  `scenicname` varchar(100) DEFAULT NULL COMMENT '景区名字',
  `address` varchar(255) DEFAULT NULL COMMENT '景区地址',
  `telephone` varchar(15) DEFAULT NULL COMMENT '景区电话',
  `max_people` int(20) DEFAULT NULL COMMENT '最大人流量',
  `max_car` int(20) DEFAULT NULL COMMENT '最大车流量',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建者',
  `status` int(2) DEFAULT NULL,
  `updator` varchar(20) DEFAULT NULL,
  `del_flag` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scenicspot
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `account` varchar(255) DEFAULT NULL COMMENT '账户',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `phone` varchar(15) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `email` varchar(25) DEFAULT NULL COMMENT '邮箱',
  `creat_time` date DEFAULT NULL COMMENT '创建时间 ',
  `updator` varchar(20) DEFAULT NULL COMMENT '更新者',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建者',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for `tourist`
-- ----------------------------
DROP TABLE IF EXISTS `tourist`;
CREATE TABLE `tourist` (
  `id` int(255) DEFAULT NULL COMMENT '游客主键',
  `tourist_type` int(3) DEFAULT NULL COMMENT '游客类型',
  `science_id` int(3) DEFAULT NULL,
  `enter_time` date DEFAULT NULL COMMENT '进入时间',
  `leave_time` date DEFAULT NULL COMMENT '离开时间',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标记'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tourist
-- ----------------------------
