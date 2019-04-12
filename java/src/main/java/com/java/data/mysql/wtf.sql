/*
 Navicat Premium Data Transfer

 Source Server         : Root
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.83.21:3306
 Source Schema         : wtf

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 28/03/2019 17:07:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for TableSample
-- ----------------------------
DROP TABLE IF EXISTS `TableSample`;
CREATE TABLE `TableSample`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creat_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `we`(`title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Women
-- ----------------------------
DROP TABLE IF EXISTS `Women`;
CREATE TABLE `Women`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` bigint(20) NOT NULL,
  `author` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creat_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `title`(`title`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nav
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav`  (
  `nav_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `is_show_child` bit(1) NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nav_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`nav_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nav_item
-- ----------------------------
DROP TABLE IF EXISTS `nav_item`;
CREATE TABLE `nav_item`  (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_active` bit(1) NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nav_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nav_nav_items
-- ----------------------------
DROP TABLE IF EXISTS `nav_nav_items`;
CREATE TABLE `nav_nav_items`  (
  `nav_nav_id` bigint(20) NOT NULL,
  `nav_items_item_id` bigint(20) NOT NULL,
  PRIMARY KEY (`nav_nav_id`, `nav_items_item_id`) USING BTREE,
  UNIQUE INDEX `UK_p9p0ot95jg4tckrbb1hpx38n1`(`nav_items_item_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 2, '山东');
INSERT INTO `role` VALUES (2, 1, '北京');
INSERT INTO `role` VALUES (3, 4, '上海');
INSERT INTO `role` VALUES (4, 5, '广东');
INSERT INTO `role` VALUES (8, 1, '22');
INSERT INTO `role` VALUES (9, 2, '112');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apartment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '山东', 'May', '2018-12-19 10:18:51', '123456', '12', 1, '张三');
INSERT INTO `user` VALUES (2, '北京', 'may', '2018-12-19 10:40:07', '123456', '13', 2, '李四');
INSERT INTO `user` VALUES (3, '上海', 'Maybe', '2018-12-19 10:40:13', '123456', '43', 1, '王五');
INSERT INTO `user` VALUES (4, '山东', 'bamay', '2018-12-19 10:43:22', '123456', '32', 3, '赵六');
INSERT INTO `user` VALUES (5, '北京', 'whey', '2018-12-19 10:43:39', '123456', '434', 1, '李强');
INSERT INTO `user` VALUES (7, '广西', 'wome', '2019-03-21 01:37:49', '123456', '1', 2, '张强立');
INSERT INTO `user` VALUES (8, '南充', 'you', '2019-03-21 02:50:12', '123456', '323', 1, '这');
INSERT INTO `user` VALUES (9, '323', NULL, '2019-03-28 14:41:58', '123', '23', NULL, '323');

SET FOREIGN_KEY_CHECKS = 1;
