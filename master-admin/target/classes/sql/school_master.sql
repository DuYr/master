/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : school_master

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 27/03/2021 00:27:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tp_absent
-- ----------------------------
DROP TABLE IF EXISTS `tp_absent`;
CREATE TABLE `tp_absent`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '班级id',
  `date` date NOT NULL COMMENT '日期',
  `full` tinyint(4) NOT NULL COMMENT '是否全勤',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_absent
-- ----------------------------
INSERT INTO `tp_absent` VALUES (24, 1841052, '2020-01-02', 1);
INSERT INTO `tp_absent` VALUES (25, 1841052, '2021-03-22', 0);
INSERT INTO `tp_absent` VALUES (26, 1841052, '2021-03-25', 0);
INSERT INTO `tp_absent` VALUES (27, 1841052, '2021-03-23', 0);

-- ----------------------------
-- Table structure for tp_absent_details
-- ----------------------------
DROP TABLE IF EXISTS `tp_absent_details`;
CREATE TABLE `tp_absent_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `date` date NOT NULL COMMENT '日期',
  `sid` int(11) NOT NULL COMMENT '学号',
  `course` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缺勤课程',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原因',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '缺勤详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_absent_details
-- ----------------------------
INSERT INTO `tp_absent_details` VALUES (79, '2021-03-25', 184105227, '1,2,3', '早退', '未备注', '2021-03-25 08:21:58');
INSERT INTO `tp_absent_details` VALUES (80, '2021-03-23', 184105227, '1,2,3', '早退', '未备注', '2021-03-25 08:22:07');
INSERT INTO `tp_absent_details` VALUES (81, '2021-03-22', 184105227, '1,2,3', '早退', '未备注', '2021-03-25 08:22:13');

-- ----------------------------
-- Table structure for tp_access_power
-- ----------------------------
DROP TABLE IF EXISTS `tp_access_power`;
CREATE TABLE `tp_access_power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'uri',
  `type` tinyint(2) NOT NULL COMMENT '访问所需类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_access_power
-- ----------------------------
INSERT INTO `tp_access_power` VALUES (1, '/admin/update', 1);
INSERT INTO `tp_access_power` VALUES (2, '/admin/add', 1);
INSERT INTO `tp_access_power` VALUES (3, '/admin/reset/**', 1);
INSERT INTO `tp_access_power` VALUES (4, '/admin/show', 1);
INSERT INTO `tp_access_power` VALUES (5, '/admin/delete/**', 1);
INSERT INTO `tp_access_power` VALUES (6, '/log/**', 1);

-- ----------------------------
-- Table structure for tp_admin
-- ----------------------------
DROP TABLE IF EXISTS `tp_admin`;
CREATE TABLE `tp_admin`  (
  `id` int(2) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `enable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否激活',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '未作说明' COMMENT '注释',
  `type` tinyint(1) NOT NULL DEFAULT 2 COMMENT '类型',
  `pid` int(11) NOT NULL COMMENT '相关班级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_admin
-- ----------------------------
INSERT INTO `tp_admin` VALUES (1, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', '2020-12-31 00:46:40', 1, '超级管理员', 1, 0);
INSERT INTO `tp_admin` VALUES (2, 'root', 'd7acaf13aad28c4b34fef50c6bb54ed9', '2020-12-31 00:46:48', 1, '普通管理员', 2, 0);

-- ----------------------------
-- Table structure for tp_class
-- ----------------------------
DROP TABLE IF EXISTS `tp_class`;
CREATE TABLE `tp_class`  (
  `pid` int(11) NOT NULL COMMENT '主键，班级id',
  `cid` int(25) NOT NULL COMMENT '学院id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_class
-- ----------------------------
INSERT INTO `tp_class` VALUES (1841052, 1011, '2019级计算机应用四班', '2021-03-26 08:14:37');

-- ----------------------------
-- Table structure for tp_college
-- ----------------------------
DROP TABLE IF EXISTS `tp_college`;
CREATE TABLE `tp_college`  (
  `cid` int(25) NOT NULL COMMENT '二级学院ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '二级学院名称',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日期',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_college
-- ----------------------------
INSERT INTO `tp_college` VALUES (1010, '医学', '2021-01-01 01:11:21');
INSERT INTO `tp_college` VALUES (1011, '工程', '2021-01-01 22:54:48');

-- ----------------------------
-- Table structure for tp_log_absent
-- ----------------------------
DROP TABLE IF EXISTS `tp_log_absent`;
CREATE TABLE `tp_log_absent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员名称',
  `behavior` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '行为',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '具体内容',
  `time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_log_absent
-- ----------------------------
INSERT INTO `tp_log_absent` VALUES (62, 'admin', 'add', '添加{学生 184105227的缺勤记录:时间:1,2,3,日期:2020-01-02,原因:早退,备注:未备注}', '2021-03-21 15:34:33');
INSERT INTO `tp_log_absent` VALUES (63, 'admin', 'delete', '删除{学生 184105227的缺勤记录:时间:1,2,3,日期:2020-01-02,原因:早退,备注:未备注}', '2021-03-21 15:35:10');
INSERT INTO `tp_log_absent` VALUES (64, 'admin', 'add', '添加{学生 184105227的缺勤记录:时间:1，2，3,日期:2021-03-25,原因:早退,备注:未备注}', '2021-03-25 08:21:58');
INSERT INTO `tp_log_absent` VALUES (65, 'admin', 'add', '添加{学生 184105227的缺勤记录:时间:1，2，3,日期:2021-03-23,原因:早退,备注:未备注}', '2021-03-25 08:22:07');
INSERT INTO `tp_log_absent` VALUES (66, 'admin', 'add', '添加{学生 184105227的缺勤记录:时间:1，2，3,日期:2021-03-22,原因:早退,备注:未备注}', '2021-03-25 08:22:13');

-- ----------------------------
-- Table structure for tp_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tp_login_log`;
CREATE TABLE `tp_login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户',
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '结果',
  `time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_login_log
-- ----------------------------
INSERT INTO `tp_login_log` VALUES (1, 'admin', '1', 'fail', '2020-11-24 09:51:34');
INSERT INTO `tp_login_log` VALUES (2, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'success', '2020-11-24 09:52:02');
INSERT INTO `tp_login_log` VALUES (3, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 01:15:45');
INSERT INTO `tp_login_log` VALUES (4, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 01:23:49');
INSERT INTO `tp_login_log` VALUES (5, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 01:26:43');
INSERT INTO `tp_login_log` VALUES (6, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 01:28:15');
INSERT INTO `tp_login_log` VALUES (7, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 01:30:06');
INSERT INTO `tp_login_log` VALUES (8, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 01:35:03');
INSERT INTO `tp_login_log` VALUES (9, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 08:11:41');
INSERT INTO `tp_login_log` VALUES (10, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 08:15:07');
INSERT INTO `tp_login_log` VALUES (11, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 08:24:26');
INSERT INTO `tp_login_log` VALUES (12, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 08:28:52');
INSERT INTO `tp_login_log` VALUES (13, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2020-12-31 08:41:12');
INSERT INTO `tp_login_log` VALUES (14, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-01 19:16:07');
INSERT INTO `tp_login_log` VALUES (15, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-01 21:34:05');
INSERT INTO `tp_login_log` VALUES (16, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-01 22:37:28');
INSERT INTO `tp_login_log` VALUES (17, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-02 15:54:11');
INSERT INTO `tp_login_log` VALUES (18, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-02 15:55:24');
INSERT INTO `tp_login_log` VALUES (19, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-02 16:05:27');
INSERT INTO `tp_login_log` VALUES (20, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-03 01:00:33');
INSERT INTO `tp_login_log` VALUES (21, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-04 23:59:31');
INSERT INTO `tp_login_log` VALUES (22, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-06 18:32:09');
INSERT INTO `tp_login_log` VALUES (23, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-26 11:33:59');
INSERT INTO `tp_login_log` VALUES (24, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-28 15:20:04');
INSERT INTO `tp_login_log` VALUES (25, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-28 16:35:18');
INSERT INTO `tp_login_log` VALUES (26, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-28 16:46:19');
INSERT INTO `tp_login_log` VALUES (27, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-29 21:00:06');
INSERT INTO `tp_login_log` VALUES (28, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-30 13:07:28');
INSERT INTO `tp_login_log` VALUES (29, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-30 13:08:24');
INSERT INTO `tp_login_log` VALUES (30, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-01-31 14:41:17');
INSERT INTO `tp_login_log` VALUES (31, 'root', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-02-01 19:34:10');
INSERT INTO `tp_login_log` VALUES (32, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-02-01 20:56:32');
INSERT INTO `tp_login_log` VALUES (33, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-02-01 21:10:21');
INSERT INTO `tp_login_log` VALUES (34, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-02-01 23:43:37');
INSERT INTO `tp_login_log` VALUES (35, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-08 13:10:01');
INSERT INTO `tp_login_log` VALUES (36, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-08 14:27:39');
INSERT INTO `tp_login_log` VALUES (37, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-10 12:00:42');
INSERT INTO `tp_login_log` VALUES (38, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-10 12:05:33');
INSERT INTO `tp_login_log` VALUES (39, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-12 14:02:22');
INSERT INTO `tp_login_log` VALUES (40, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-12 16:00:30');
INSERT INTO `tp_login_log` VALUES (41, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-13 15:52:47');
INSERT INTO `tp_login_log` VALUES (42, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-13 18:25:41');
INSERT INTO `tp_login_log` VALUES (43, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-16 16:29:42');
INSERT INTO `tp_login_log` VALUES (44, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-17 19:28:50');
INSERT INTO `tp_login_log` VALUES (45, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-17 19:33:22');
INSERT INTO `tp_login_log` VALUES (46, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-17 19:59:45');
INSERT INTO `tp_login_log` VALUES (47, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-18 11:15:45');
INSERT INTO `tp_login_log` VALUES (48, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-20 13:48:52');
INSERT INTO `tp_login_log` VALUES (49, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-21 14:51:50');
INSERT INTO `tp_login_log` VALUES (50, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-22 11:14:37');
INSERT INTO `tp_login_log` VALUES (51, 'admin', 'd7acaf13aad28c4b34fef50c6bb54ed9', 'success', '2021-03-25 08:20:11');

-- ----------------------------
-- Table structure for tp_students
-- ----------------------------
DROP TABLE IF EXISTS `tp_students`;
CREATE TABLE `tp_students`  (
  `sid` int(11) NOT NULL COMMENT '学号',
  `pid` int(11) NOT NULL COMMENT '班级',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tp_students
-- ----------------------------
INSERT INTO `tp_students` VALUES (184105227, 1841052, '吕想', '普通学生');
INSERT INTO `tp_students` VALUES (184105250, 1841052, '邹国清', '妈妈');
INSERT INTO `tp_students` VALUES (184105299, 1841052, '吕彤', '姐姐');

SET FOREIGN_KEY_CHECKS = 1;
