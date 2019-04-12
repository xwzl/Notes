/*
 Navicat Premium Data Transfer

 Source Server         : Root
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.83.21:3306
 Source Schema         : property

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 28/03/2019 17:08:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for advice
-- ----------------------------
DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for advice_items
-- ----------------------------
DROP TABLE IF EXISTS `advice_items`;
CREATE TABLE `advice_items`  (
  `advice_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`advice_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_gfnxpolymbq1jhia3cc5gby71`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for affairs
-- ----------------------------
DROP TABLE IF EXISTS `affairs`;
CREATE TABLE `affairs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for affairs_items
-- ----------------------------
DROP TABLE IF EXISTS `affairs_items`;
CREATE TABLE `affairs_items`  (
  `affairs_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`affairs_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_h3pxe1pror2vagoe03bdgjywk`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for capital
-- ----------------------------
DROP TABLE IF EXISTS `capital`;
CREATE TABLE `capital`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for capital_items
-- ----------------------------
DROP TABLE IF EXISTS `capital_items`;
CREATE TABLE `capital_items`  (
  `capital_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`capital_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_fynmg3ww8vu7b6rcpxcg9fdxp`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cases_items
-- ----------------------------
DROP TABLE IF EXISTS `cases_items`;
CREATE TABLE `cases_items`  (
  `cases_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`cases_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_g57rs8sp2s8xgbx5qsvb0cpuw`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name_of_complainant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `object_of_complaint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for complaint_items
-- ----------------------------
DROP TABLE IF EXISTS `complaint_items`;
CREATE TABLE `complaint_items`  (
  `complaint_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`complaint_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_j7hpixv9xf4p9jl0r4kythqot`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for directories
-- ----------------------------
DROP TABLE IF EXISTS `directories`;
CREATE TABLE `directories`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for directories_items
-- ----------------------------
DROP TABLE IF EXISTS `directories_items`;
CREATE TABLE `directories_items`  (
  `directories_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`directories_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_51chj0mogturhykc6lo0bkcus`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for download
-- ----------------------------
DROP TABLE IF EXISTS `download`;
CREATE TABLE `download`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `file_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for download_items
-- ----------------------------
DROP TABLE IF EXISTS `download_items`;
CREATE TABLE `download_items`  (
  `download_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`download_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_8emtldpkx92iu952er3jhttko`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `l_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK2gude0sa3kovhrs6n51oid6rl`(`l_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manage_item
-- ----------------------------
DROP TABLE IF EXISTS `manage_item`;
CREATE TABLE `manage_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NULL DEFAULT NULL,
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manage_item
-- ----------------------------
INSERT INTO `manage_item` VALUES (1, 0, '详细');
INSERT INTO `manage_item` VALUES (2, 4, '附件管理');
INSERT INTO `manage_item` VALUES (3, 1, '提交');
INSERT INTO `manage_item` VALUES (4, 4, '删除');
INSERT INTO `manage_item` VALUES (5, 3, '审核');
INSERT INTO `manage_item` VALUES (6, 0, '详细');
INSERT INTO `manage_item` VALUES (7, 4, '附件管理');
INSERT INTO `manage_item` VALUES (8, 1, '提交');
INSERT INTO `manage_item` VALUES (9, 4, '删除');
INSERT INTO `manage_item` VALUES (10, 3, '审核');
INSERT INTO `manage_item` VALUES (11, 0, '详细');
INSERT INTO `manage_item` VALUES (12, 4, '附件管理');
INSERT INTO `manage_item` VALUES (13, 1, '提交');
INSERT INTO `manage_item` VALUES (14, 4, '删除');
INSERT INTO `manage_item` VALUES (15, 3, '审核');
INSERT INTO `manage_item` VALUES (16, 0, '详细');
INSERT INTO `manage_item` VALUES (17, 4, '附件管理');
INSERT INTO `manage_item` VALUES (18, 1, '提交');
INSERT INTO `manage_item` VALUES (19, 4, '删除');
INSERT INTO `manage_item` VALUES (20, 3, '审核');
INSERT INTO `manage_item` VALUES (21, 0, '详细');
INSERT INTO `manage_item` VALUES (22, 4, '附件管理');
INSERT INTO `manage_item` VALUES (23, 1, '提交');
INSERT INTO `manage_item` VALUES (24, 4, '删除');
INSERT INTO `manage_item` VALUES (25, 3, '审核');
INSERT INTO `manage_item` VALUES (26, 4, '附件管理');
INSERT INTO `manage_item` VALUES (27, 1, '提交');
INSERT INTO `manage_item` VALUES (28, 4, '删除');
INSERT INTO `manage_item` VALUES (29, 3, '审核');

-- ----------------------------
-- Table structure for nav
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `is_show_children` bit(1) NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nav
-- ----------------------------
INSERT INTO `nav` VALUES (1, NULL, b'1', NULL, '/newsHome', '最新动态');
INSERT INTO `nav` VALUES (2, NULL, b'0', NULL, '/manageAgency', '管理机构');
INSERT INTO `nav` VALUES (3, NULL, b'0', NULL, '/advice', '公示公告');
INSERT INTO `nav` VALUES (4, NULL, b'0', NULL, '/policy', '政策法规');
INSERT INTO `nav` VALUES (5, NULL, b'0', NULL, '/capital', '资金监管');
INSERT INTO `nav` VALUES (6, NULL, b'0', NULL, '/cases', '典型案例');
INSERT INTO `nav` VALUES (7, NULL, b'0', NULL, '/affairs', '办事指南');
INSERT INTO `nav` VALUES (8, NULL, b'0', NULL, '/download', '资料下载');
INSERT INTO `nav` VALUES (9, NULL, b'0', NULL, '/directories', '名录');
INSERT INTO `nav` VALUES (10, NULL, b'0', NULL, '/complaint', '投诉');
INSERT INTO `nav` VALUES (11, NULL, b'0', NULL, '/question', '问题解答');
INSERT INTO `nav` VALUES (12, NULL, b'0', NULL, '/log', '日志管理');

-- ----------------------------
-- Table structure for nav_item
-- ----------------------------
DROP TABLE IF EXISTS `nav_item`;
CREATE TABLE `nav_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nav_item
-- ----------------------------
INSERT INTO `nav_item` VALUES (1, './workDynamics', '和美一街');
INSERT INTO `nav_item` VALUES (2, './workDynamics', '工作态度');
INSERT INTO `nav_item` VALUES (3, './workReport', '工作报告');
INSERT INTO `nav_item` VALUES (4, './workReport', '胜利广场');
INSERT INTO `nav_item` VALUES (5, './workDynamics', '工作态度');
INSERT INTO `nav_item` VALUES (6, './workReport', '工作报告');
INSERT INTO `nav_item` VALUES (7, '/0', '公示公告');
INSERT INTO `nav_item` VALUES (8, '/0', '政策法规');
INSERT INTO `nav_item` VALUES (9, '/0', '资金监管');
INSERT INTO `nav_item` VALUES (10, '/0', '典型案例');
INSERT INTO `nav_item` VALUES (11, '/0', '办事指南');
INSERT INTO `nav_item` VALUES (12, '/0', '资料下载');
INSERT INTO `nav_item` VALUES (13, '/1', '审价单位名录');
INSERT INTO `nav_item` VALUES (14, '/0', '施工单位名录');
INSERT INTO `nav_item` VALUES (15, '/0', '投诉');
INSERT INTO `nav_item` VALUES (16, '/0', '问题解答');
INSERT INTO `nav_item` VALUES (17, '/0', '日志管理');

-- ----------------------------
-- Table structure for nav_nav_items
-- ----------------------------
DROP TABLE IF EXISTS `nav_nav_items`;
CREATE TABLE `nav_nav_items`  (
  `nav_id` int(11) NOT NULL,
  `nav_items_id` int(11) NOT NULL,
  PRIMARY KEY (`nav_id`, `nav_items_id`) USING BTREE,
  UNIQUE INDEX `UK_d5b2wtvx3c7l37td0t7cv4lil`(`nav_items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of nav_nav_items
-- ----------------------------
INSERT INTO `nav_nav_items` VALUES (1, 1);
INSERT INTO `nav_nav_items` VALUES (1, 2);
INSERT INTO `nav_nav_items` VALUES (1, 3);
INSERT INTO `nav_nav_items` VALUES (1, 4);
INSERT INTO `nav_nav_items` VALUES (2, 5);
INSERT INTO `nav_nav_items` VALUES (2, 6);
INSERT INTO `nav_nav_items` VALUES (3, 7);
INSERT INTO `nav_nav_items` VALUES (4, 8);
INSERT INTO `nav_nav_items` VALUES (5, 9);
INSERT INTO `nav_nav_items` VALUES (6, 10);
INSERT INTO `nav_nav_items` VALUES (7, 11);
INSERT INTO `nav_nav_items` VALUES (8, 12);
INSERT INTO `nav_nav_items` VALUES (9, 13);
INSERT INTO `nav_nav_items` VALUES (9, 14);
INSERT INTO `nav_nav_items` VALUES (10, 15);
INSERT INTO `nav_nav_items` VALUES (11, 16);
INSERT INTO `nav_nav_items` VALUES (12, 17);

-- ----------------------------
-- Table structure for policy
-- ----------------------------
DROP TABLE IF EXISTS `policy`;
CREATE TABLE `policy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for policy_items
-- ----------------------------
DROP TABLE IF EXISTS `policy_items`;
CREATE TABLE `policy_items`  (
  `policy_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`policy_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_2is2lpwx5sykdfq6f139o1oiy`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(19, 2) NULL DEFAULT NULL,
  `rating` decimal(19, 2) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_items
-- ----------------------------
DROP TABLE IF EXISTS `question_items`;
CREATE TABLE `question_items`  (
  `question_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`question_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_2uoujpwi1wb6w2cmxak21da38`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '1233222', '2019-01-16 11:21:27', '4QrcOUm6Wau+VuBX8g+IPg==', '15280975199', 'admin');

-- ----------------------------
-- Table structure for work_report
-- ----------------------------
DROP TABLE IF EXISTS `work_report`;
CREATE TABLE `work_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_report_items
-- ----------------------------
DROP TABLE IF EXISTS `work_report_items`;
CREATE TABLE `work_report_items`  (
  `work_report_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`work_report_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_kkx818ebllrsa4gnuh2rfmuw9`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for work_status
-- ----------------------------
DROP TABLE IF EXISTS `work_status`;
CREATE TABLE `work_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_status_detail_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5v2nv44og4obvxx1ea3nitjg5`(`work_status_detail_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_status
-- ----------------------------
INSERT INTO `work_status` VALUES (1, '2018-12-19 18:02:31', '未定', '你好', 1);
INSERT INTO `work_status` VALUES (2, '2018-12-19 18:02:31', '未定', 'Angular', 2);
INSERT INTO `work_status` VALUES (3, '2018-12-19 18:02:31', '未定', 'Nice', 3);
INSERT INTO `work_status` VALUES (4, '2018-12-19 18:02:31', '未定', 'Too', 4);
INSERT INTO `work_status` VALUES (5, '2018-12-19 18:02:31', '未定', 'Me', 5);
INSERT INTO `work_status` VALUES (6, '2018-12-19 18:02:31', '未定', 'You', 6);

-- ----------------------------
-- Table structure for work_status_detail
-- ----------------------------
DROP TABLE IF EXISTS `work_status_detail`;
CREATE TABLE `work_status_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_advantage` bit(1) NULL DEFAULT NULL,
  `news_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `out_time` datetime(0) NULL DEFAULT NULL,
  `owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `release_time` datetime(0) NULL DEFAULT NULL,
  `resources` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_status_detail
-- ----------------------------
INSERT INTO `work_status_detail` VALUES (1, '莫言', '我爱你', 'sdfd', b'1', '图书', '2018-12-19 18:02:31', 'moan', '2018-12-19 18:02:31', '小区', '天1');
INSERT INTO `work_status_detail` VALUES (2, '莫言', '我爱你', 'sdfd', b'1', '图书', '2018-12-19 18:02:31', 'moan', '2018-12-19 18:02:31', '小区', '天1');
INSERT INTO `work_status_detail` VALUES (3, '莫言', '我爱你', 'sdfd', b'1', '图书', '2018-12-19 18:02:31', 'moan', '2018-12-19 18:02:31', '小区', '天1');
INSERT INTO `work_status_detail` VALUES (4, '莫言', '我爱你', 'sdfd', b'1', '图书', '2018-12-19 18:02:31', 'moan', '2018-12-19 18:02:31', '小区', '天1');
INSERT INTO `work_status_detail` VALUES (5, '莫言', '我爱你', 'sdfd', b'1', '图书', '2018-12-19 18:02:31', 'moan', '2018-12-19 18:02:31', '小区', '天1');
INSERT INTO `work_status_detail` VALUES (6, '莫言', '我爱你', 'sdfd', b'1', '图书', '2018-12-19 18:02:31', 'moan', '2018-12-19 18:02:31', '小区', '天1');

-- ----------------------------
-- Table structure for work_status_items
-- ----------------------------
DROP TABLE IF EXISTS `work_status_items`;
CREATE TABLE `work_status_items`  (
  `work_status_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  PRIMARY KEY (`work_status_id`, `items_id`) USING BTREE,
  UNIQUE INDEX `UK_avr137q483atkvfr6tpg8p7ae`(`items_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of work_status_items
-- ----------------------------
INSERT INTO `work_status_items` VALUES (1, 1);
INSERT INTO `work_status_items` VALUES (1, 2);
INSERT INTO `work_status_items` VALUES (1, 3);
INSERT INTO `work_status_items` VALUES (1, 4);
INSERT INTO `work_status_items` VALUES (1, 5);
INSERT INTO `work_status_items` VALUES (2, 6);
INSERT INTO `work_status_items` VALUES (2, 7);
INSERT INTO `work_status_items` VALUES (2, 8);
INSERT INTO `work_status_items` VALUES (2, 9);
INSERT INTO `work_status_items` VALUES (2, 10);
INSERT INTO `work_status_items` VALUES (3, 11);
INSERT INTO `work_status_items` VALUES (3, 12);
INSERT INTO `work_status_items` VALUES (3, 13);
INSERT INTO `work_status_items` VALUES (3, 14);
INSERT INTO `work_status_items` VALUES (3, 15);
INSERT INTO `work_status_items` VALUES (4, 16);
INSERT INTO `work_status_items` VALUES (4, 17);
INSERT INTO `work_status_items` VALUES (4, 18);
INSERT INTO `work_status_items` VALUES (4, 19);
INSERT INTO `work_status_items` VALUES (4, 20);
INSERT INTO `work_status_items` VALUES (5, 21);
INSERT INTO `work_status_items` VALUES (5, 22);
INSERT INTO `work_status_items` VALUES (5, 23);
INSERT INTO `work_status_items` VALUES (5, 24);
INSERT INTO `work_status_items` VALUES (5, 25);
INSERT INTO `work_status_items` VALUES (6, 26);
INSERT INTO `work_status_items` VALUES (6, 27);
INSERT INTO `work_status_items` VALUES (6, 28);
INSERT INTO `work_status_items` VALUES (6, 29);

SET FOREIGN_KEY_CHECKS = 1;
