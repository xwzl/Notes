/*
 Navicat Premium Data Transfer

 Source Server         : Root
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.83.21:3306
 Source Schema         : bln_warehouse

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 28/03/2019 17:08:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bln_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `bln_warehouse`;
CREATE TABLE `bln_warehouse`  (
  `id` int(32) NOT NULL,
  `goodsType` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `detailed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品明细',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `warehouseCoding` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位编码',
  `warehouseName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位名称',
  `storageLocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '储存地点',
  `transferInL` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调入物流区域',
  `transferInW` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调入库位',
  `house_in_state` int(4) NULL DEFAULT NULL COMMENT '入库状态',
  `transferInS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调入存储地点',
  `house_in_date` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `amount` int(10) NULL DEFAULT NULL COMMENT '数量（入库总数）',
  `logisticsArea` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流区域',
  `number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号',
  `salesOrderNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销售订单号',
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单类型',
  `contractNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同号',
  `logisticsCompany` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流公司',
  `volume` double(20, 0) NOT NULL COMMENT '体积',
  `weight` double(20, 0) NULL DEFAULT NULL COMMENT '重量',
  `customer` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `customerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户地址',
  `finalCustomer` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终客户',
  `finalCustomerAddress` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终客户地址',
  `deliveryNumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发货编号',
  `sender` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货人',
  `deliveryDate` datetime(0) NULL DEFAULT NULL,
  `receiver` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提货人',
  `bag_Type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包装物类型',
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '承运商',
  `packageAmount` int(10) NULL DEFAULT NULL COMMENT '包装数量',
  `deliver_amount` int(10) NULL DEFAULT NULL COMMENT '发货数量',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包装备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for loginnum
-- ----------------------------
DROP TABLE IF EXISTS `loginnum`;
CREATE TABLE `loginnum`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_time` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_login_num` int(11) NULL DEFAULT NULL,
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 246 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loginnum
-- ----------------------------
INSERT INTO `loginnum` VALUES (2, '总部', '2018-08-10 14:35:33', '候江波', '005861', 23, NULL);
INSERT INTO `loginnum` VALUES (3, '总部', '2018-08-10 16:12:32', '候江波阿三', '005861', 24, NULL);
INSERT INTO `loginnum` VALUES (4, '总部', '2018-08-10 17:19:38', '候江波', '005861', 25, NULL);
INSERT INTO `loginnum` VALUES (5, '总部', '2018-08-13 08:58:21', '候江波', '005861', 26, NULL);
INSERT INTO `loginnum` VALUES (6, '总部', '2018-08-13 09:26:50', '候江波', '005861', 27, NULL);
INSERT INTO `loginnum` VALUES (7, '总部', '2018-08-13 11:41:31', '候江波', '005861', 28, NULL);
INSERT INTO `loginnum` VALUES (8, '总部', '2018-08-13 11:41:31', '候江波', '005861', 29, NULL);
INSERT INTO `loginnum` VALUES (9, '总部', '2018-08-13 11:43:21', '候江波', '005861', 30, NULL);
INSERT INTO `loginnum` VALUES (10, '总部', '2018-08-13 11:46:07', '候江波', '005861', 31, NULL);
INSERT INTO `loginnum` VALUES (11, '总部', '2018-08-13 11:48:08', '候江波', '005861', 32, NULL);
INSERT INTO `loginnum` VALUES (12, '总部', '2018-08-13 12:06:26', '候江波', '005861', 33, NULL);
INSERT INTO `loginnum` VALUES (13, '总部', '2018-08-13 14:11:55', '候江波', '005861', 34, NULL);
INSERT INTO `loginnum` VALUES (14, '总部', '2018-08-13 14:54:36', '候江波', '005861', 35, NULL);
INSERT INTO `loginnum` VALUES (15, '总部', '2018-08-13 14:55:00', '候江波', '005861', 36, NULL);
INSERT INTO `loginnum` VALUES (16, '总部', '2018-08-13 14:55:57', '候江波', '005861', 37, NULL);
INSERT INTO `loginnum` VALUES (17, '总部', '2018-08-13 15:06:36', '候江波', '005861', 38, NULL);
INSERT INTO `loginnum` VALUES (18, '总部', '2018-08-13 15:34:42', '候江波', '005861', 39, NULL);
INSERT INTO `loginnum` VALUES (19, '总部', '2018-08-13 16:41:58', '候江波', '005861', 40, NULL);
INSERT INTO `loginnum` VALUES (20, '总部', '2018-08-13 16:41:58', '候江波', '005861', 41, NULL);
INSERT INTO `loginnum` VALUES (21, '总部', '2018-08-13 16:42:59', '候江波', '005861', 42, NULL);
INSERT INTO `loginnum` VALUES (22, '总部', '2018-08-14 09:01:50', '候江波', '005861', 43, NULL);
INSERT INTO `loginnum` VALUES (23, '总部', '2018-08-14 09:03:20', '候江波', '005861', 44, NULL);
INSERT INTO `loginnum` VALUES (24, '总部', '2018-08-14 09:12:31', '候江波', '005861', 45, NULL);
INSERT INTO `loginnum` VALUES (25, '总部', '2018-08-14 09:27:14', '候江波', '005861', 46, NULL);
INSERT INTO `loginnum` VALUES (26, '总部', '2018-08-14 10:08:36', '候江波', '005861', 47, NULL);
INSERT INTO `loginnum` VALUES (27, '总部', '2018-08-14 10:10:40', '候江波', '005861', 48, NULL);
INSERT INTO `loginnum` VALUES (28, '总部', '2018-08-14 10:12:37', '候江波', '005861', 49, NULL);
INSERT INTO `loginnum` VALUES (29, '总部', '2018-08-14 10:14:05', '候江波', '005861', 50, NULL);
INSERT INTO `loginnum` VALUES (30, '总部', '2018-08-14 10:15:15', '候江波', '005861', 51, NULL);
INSERT INTO `loginnum` VALUES (31, '总部', '2018-08-14 10:16:03', '候江波', '005861', 52, NULL);
INSERT INTO `loginnum` VALUES (32, '总部', '2018-08-14 10:16:41', '候江波', '005861', 53, NULL);
INSERT INTO `loginnum` VALUES (33, '总部', '2018-08-14 10:16:41', '候江波', '005861', 54, NULL);
INSERT INTO `loginnum` VALUES (34, '总部', '2018-08-14 10:18:09', '候江波', '005861', 55, NULL);
INSERT INTO `loginnum` VALUES (35, '总部', '2018-08-14 10:21:43', '候江波', '005861', 56, NULL);
INSERT INTO `loginnum` VALUES (36, '总部', '2018-08-14 10:23:05', '候江波', '005861', 57, NULL);
INSERT INTO `loginnum` VALUES (37, '总部', '2018-08-14 10:23:45', '候江波', '005861', 58, NULL);
INSERT INTO `loginnum` VALUES (38, '总部', '2018-08-14 10:31:24', '候江波', '005861', 59, NULL);
INSERT INTO `loginnum` VALUES (39, '总部', '2018-08-14 10:37:14', '候江波', '005861', 60, NULL);
INSERT INTO `loginnum` VALUES (40, '总部', '2018-08-14 10:44:20', '候江波', '005861', 61, '169.254.36.221');
INSERT INTO `loginnum` VALUES (41, '总部', '2018-08-14 10:47:06', '候江波', '005861', 62, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (42, '总部', '2018-08-14 10:47:49', '候江波', '005861', 63, '169.254.36.221');
INSERT INTO `loginnum` VALUES (43, '总部', '2018-08-14 10:51:47', '候江波', '005861', 64, '192.168.0.122');
INSERT INTO `loginnum` VALUES (44, '总部', '2018-08-14 10:55:43', '候江波', '005861', 65, '169.254.36.221');
INSERT INTO `loginnum` VALUES (45, '总部', '2018-08-14 10:56:02', '候江波', '005861', 66, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (46, '总部', '2018-08-14 10:58:00', '候江波', '005861', 67, NULL);
INSERT INTO `loginnum` VALUES (47, '总部', '2018-08-14 11:16:08', '候江波', '005861', 68, NULL);
INSERT INTO `loginnum` VALUES (48, '总部', '2018-08-14 11:17:48', '候江波', '005861', 69, NULL);
INSERT INTO `loginnum` VALUES (49, '总部', '2018-08-14 11:17:48', '候江波', '005861', 69, NULL);
INSERT INTO `loginnum` VALUES (50, '总部', '2018-08-14 11:20:09', '候江波', '005861', 70, '192.168.0.122');
INSERT INTO `loginnum` VALUES (51, '总部', '2018-08-14 11:20:09', '候江波', '005861', 70, '192.168.0.122');
INSERT INTO `loginnum` VALUES (52, '总部', '2018-08-14 11:22:39', '候江波', '005861', 71, '192.168.0.116');
INSERT INTO `loginnum` VALUES (53, '总部', '2018-08-14 11:37:15', '候江波', '005861', 72, NULL);
INSERT INTO `loginnum` VALUES (54, '总部', '2018-08-14 11:49:38', '候江波', '005861', 73, NULL);
INSERT INTO `loginnum` VALUES (55, '总部', '2018-08-14 11:56:17', '候江波', '005861', 74, NULL);
INSERT INTO `loginnum` VALUES (56, '总部', '2018-08-14 12:11:58', '候江波', '005861', 75, NULL);
INSERT INTO `loginnum` VALUES (57, '总部', '2018-08-14 13:59:18', '候江波', '005861', 76, NULL);
INSERT INTO `loginnum` VALUES (58, '总部', '2018-08-14 14:01:25', '候江波', '005861', 77, NULL);
INSERT INTO `loginnum` VALUES (59, '总部', '2018-08-14 14:02:41', '候江波', '005861', 78, NULL);
INSERT INTO `loginnum` VALUES (60, '总部', '2018-08-14 14:15:42', '候江波', '005861', 79, NULL);
INSERT INTO `loginnum` VALUES (61, '总部', '2018-08-14 14:40:57', '候江波', '005861', 80, NULL);
INSERT INTO `loginnum` VALUES (62, '总部', '2018-08-14 15:55:18', '候江波', '005861', 81, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (63, '总部', '2018-08-14 15:58:49', '候江波', '005861', 82, NULL);
INSERT INTO `loginnum` VALUES (64, '总部', '2018-08-14 15:59:18', '候江波', '005861', 83, NULL);
INSERT INTO `loginnum` VALUES (65, '总部', '2018-08-14 16:12:26', '候江波', '005861', 84, NULL);
INSERT INTO `loginnum` VALUES (66, '总部', '2018-08-14 16:40:09', '候江波', '00586', 85, NULL);
INSERT INTO `loginnum` VALUES (67, '总部', '2018-08-14 16:45:00', '候江波', '005861', 86, NULL);
INSERT INTO `loginnum` VALUES (68, '总部', '2018-08-14 16:45:00', '候江波', '005861', 87, NULL);
INSERT INTO `loginnum` VALUES (69, '总部', '2018-08-14 16:51:18', '候江波', '005861', 88, NULL);
INSERT INTO `loginnum` VALUES (70, '总部', '2018-08-14 17:00:31', '候江波', '005861', 89, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (71, '总部', '2018-08-14 17:09:01', '候江波', '005861', 90, NULL);
INSERT INTO `loginnum` VALUES (72, '总部', '2018-08-14 17:12:23', '候江波', '005861', 91, '192.168.0.122');
INSERT INTO `loginnum` VALUES (73, '长沙', '2018-08-14 17:17:35', '4、、44、', '227、5、', 1, '192.168.0.122');
INSERT INTO `loginnum` VALUES (74, '总部', '2018-08-14 17:20:35', '候江波', '005861', 92, NULL);
INSERT INTO `loginnum` VALUES (75, '总部', '2018-08-14 17:30:29', '候江波', '005861', 93, NULL);
INSERT INTO `loginnum` VALUES (76, '总部', '2018-08-14 17:32:31', '候江波', '005861', 94, NULL);
INSERT INTO `loginnum` VALUES (77, '总部', '2018-08-14 17:35:08', '候江波', '005861', 95, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (78, '总部', '2018-08-14 17:38:16', '候江波', '005861', 96, NULL);
INSERT INTO `loginnum` VALUES (79, '总部', '2018-08-14 19:09:22', '候江波', '005861', 97, NULL);
INSERT INTO `loginnum` VALUES (80, '总部', '2018-08-14 19:09:38', '候江波', '005861', 98, NULL);
INSERT INTO `loginnum` VALUES (81, '总部', '2018-08-14 20:00:22', '候江波', '005861', 99, '127.0.0.1');
INSERT INTO `loginnum` VALUES (82, '总部', '2018-08-14 20:07:51', '候江波', '005861', 103, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (83, '总部', '2018-08-14 20:23:05', '候江波', '005861', 104, NULL);
INSERT INTO `loginnum` VALUES (84, '总部', '2018-08-14 20:29:33', '候江波', '005861', 105, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (85, '总部', '2018-08-15 09:07:49', '候江波', '005861', 106, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (86, '总部', '2018-08-15 09:13:51', '候江波', '005861', 107, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (87, '总部', '2018-08-15 09:46:36', '候江波', '005861', 108, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (88, '总部', '2018-08-15 09:48:30', '候江波', '005861', 109, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (89, '总部', '2018-08-15 10:03:39', '候江波', '005861', 110, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (90, '总部', '2018-08-15 10:04:20', '候江波', '005861', 111, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (91, '总部', '2018-08-15 10:04:51', '候江波', '005861', 112, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (92, '总部', '2018-08-15 10:07:11', '候江波', '005861', 113, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (93, '总部', '2018-08-15 10:06:45', '候江波', '005861', 114, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (94, '总部', '2018-08-15 10:13:22', '候江波', '005861', 115, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (95, '总部', '2018-08-15 10:20:27', '候江波', '005861', 116, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (96, '总部', '2018-08-15 10:21:26', '候江波', '005861', 117, NULL);
INSERT INTO `loginnum` VALUES (97, '总部', '2018-08-15 10:23:59', '候江波', '005861', 118, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (98, '总部', '2018-08-15 10:23:00', '候江波', '005861', 119, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (99, '总部', '2018-08-15 10:29:04', '候江波', '005861', 120, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (100, '总部', '2018-08-15 10:37:22', '候江波', '005861', 121, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (101, '总部', '2018-08-15 10:54:23', '候江波', '005861', 122, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (102, '总部', '2018-08-15 10:55:11', '候江波', '005861', 123, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (103, '总部', '2018-08-15 10:56:23', '候江波', '005861', 124, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (104, '总部', '2018-08-15 11:06:16', '候江波', '005861', 125, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (105, '总部', '2018-08-15 11:10:21', '候江波', '005861', 126, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (106, '总部', '2018-08-15 11:12:46', '候江波', '005861', 127, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (107, '总部', '2018-08-15 11:12:47', '候江波', '005861', 128, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (108, '总部', '2018-08-15 11:18:11', '候江波', '005861', 129, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (109, '总部', '2018-08-15 11:18:56', '候江波', '005861', 130, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (110, '总部', '2018-08-15 11:52:04', '候江波', '005861', 131, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (111, '总部', '2018-08-15 11:52:04', '候江波', '005861', 132, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (112, '总部', '2018-08-15 12:07:21', '候江波', '005861', 133, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (113, '总部', '2018-08-15 12:12:17', '候江波', '005861', 134, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (114, '总部', '2018-08-15 12:20:08', '候江波', '005861', 135, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (115, '总部', '2018-08-15 12:28:01', '候江波', '005861', 136, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (116, '总部', '2018-08-15 14:01:12', '候江波', '005861', 137, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (117, '总部', '2018-08-15 14:09:47', '候江波', '005861', 138, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (118, '总部', '2018-08-16 09:02:03', '候江波', '005861', 139, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (119, '总部', '2018-08-16 09:02:08', '候江波', '005861', 140, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (120, '总部', '2018-08-16 09:05:30', '候江波', '005861', 141, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (121, '总部', '2018-08-16 09:33:12', '候江波', '005861', 142, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (122, '总部', '2018-08-16 09:43:32', '候江波', '005861', 143, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (123, '总部', '2018-08-16 10:04:54', '候江波', '005861', 144, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (124, '总部', '2018-08-16 10:11:43', '候江波', '005861', 145, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (125, '总部', '2018-08-16 10:14:45', '候江波', '005861', 146, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (126, '总部', '2018-08-16 10:15:02', '候江波', '005861', 147, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (127, '总部', '2018-08-16 10:16:29', '候江波', '005861', 148, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (128, '总部', '2018-08-16 10:26:22', '候江波', '005861', 149, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (129, '总部', '2018-08-16 10:37:07', '候江波', '005861', 150, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (130, '总部', '2018-08-16 10:38:36', '候江波', '005861', 151, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (131, '总部', '2018-08-16 10:40:46', '候江波', '005861', 152, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (132, '总部', '2018-08-16 11:41:45', '候江波', '005861', 153, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (133, '总部', '2018-08-16 11:46:53', '候江波', '005861', 154, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (134, '总部', '2018-08-16 11:57:41', '候江波', '005861', 155, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (135, '总部', '2018-08-16 12:00:18', '候江波', '005861', 156, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (136, '总部', '2018-08-16 12:03:31', '候江波', '005861', 157, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (137, '总部', '2018-08-16 12:03:31', '候江波', '005861', 157, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (138, '总部', '2018-08-16 12:14:13', '候江波', '005861', 158, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (139, '总部', '2018-08-16 12:13:52', '候江波', '005861', 159, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (140, '总部', '2018-08-16 12:17:11', '候江波', '005861', 160, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (141, '总部', '2018-08-16 12:19:28', '候江波', '005861', 161, '192.168.0.106');
INSERT INTO `loginnum` VALUES (142, '总部', '2018-08-16 12:20:05', '候江波', '005861', 162, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (143, '总部', '2018-08-16 12:22:25', '候江波', '005861', 163, '127.0.0.1');
INSERT INTO `loginnum` VALUES (144, '总部', '2018-08-16 14:01:20', '候江波', '005861', 164, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (145, '总部', '2018-08-16 14:10:17', '候江波', '005861', 165, '127.0.0.1');
INSERT INTO `loginnum` VALUES (146, '总部', '2018-08-16 14:18:54', '候江波', '005861', 166, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (147, '总部', '2018-08-16 15:21:02', '候江波', '005861', 167, '127.0.0.1');
INSERT INTO `loginnum` VALUES (148, '总部', '2018-08-16 15:21:36', '候江波', '005861', 168, '127.0.0.1');
INSERT INTO `loginnum` VALUES (149, '总部', '2018-08-16 15:41:34', '李江', 'lw008', 1, '127.0.0.1');
INSERT INTO `loginnum` VALUES (150, '总部', '2018-08-16 15:44:03', '候江波', '005861', 169, '127.0.0.1');
INSERT INTO `loginnum` VALUES (151, '总部', '2018-08-16 15:54:53', '候江波', '005861', 170, '127.0.0.1');
INSERT INTO `loginnum` VALUES (152, '总部', '2018-08-16 16:52:38', '候江波', '005861', 171, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (153, '总部', '2018-08-16 16:57:07', '候江波', '005861', 172, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (154, '总部', '2018-08-16 16:59:08', '候江波', '005861', 173, '127.0.0.1');
INSERT INTO `loginnum` VALUES (155, '总部', '2018-08-16 17:01:58', '候江波', '005861', 174, '127.0.0.1');
INSERT INTO `loginnum` VALUES (156, '总部', '2018-08-16 17:03:14', '候江波', '005861', 175, '127.0.0.1');
INSERT INTO `loginnum` VALUES (157, '总部', '2018-08-16 17:02:35', '候江波', '005861', 176, '127.0.0.1');
INSERT INTO `loginnum` VALUES (158, '总部', '2018-08-16 17:09:59', '候江波', '005861', 177, '127.0.0.1');
INSERT INTO `loginnum` VALUES (159, '总部', '2018-08-16 17:10:34', '候江波', '005861', 178, '127.0.0.1');
INSERT INTO `loginnum` VALUES (160, '总部', '2018-08-16 17:15:48', '候江波', '005861', 179, '127.0.0.1');
INSERT INTO `loginnum` VALUES (161, '总部', '2018-08-16 17:41:10', '候江波', '005861', 180, '127.0.0.1');
INSERT INTO `loginnum` VALUES (162, '总部', '2018-08-16 17:57:15', '候江波', '005861', 181, '0:0:0:0:0:0:0:1');
INSERT INTO `loginnum` VALUES (163, '总部', '2018-08-16 18:03:57', '候江波', '005861', 182, '127.0.0.1');
INSERT INTO `loginnum` VALUES (164, '总部', '2018-08-16 18:36:02', '候江波', '005861', 183, '127.0.0.1');
INSERT INTO `loginnum` VALUES (165, '总部', '2018-08-16 18:39:35', '候江波', '005861', 184, '127.0.0.1');
INSERT INTO `loginnum` VALUES (166, '合肥', '2018-08-16 18:48:17', '朱亚军', 'LW022', 1, '127.0.0.1');
INSERT INTO `loginnum` VALUES (167, '总部', '2018-08-16 18:48:37', '候江波', '005861', 185, '127.0.0.1');
INSERT INTO `loginnum` VALUES (168, '合肥', '2018-08-16 18:53:07', '朱亚军', 'LW022', 2, '127.0.0.1');
INSERT INTO `loginnum` VALUES (169, '总部', '2018-08-16 18:59:39', '候江波', '005861', 186, '127.0.0.1');
INSERT INTO `loginnum` VALUES (170, '总部', '2018-08-16 19:01:54', '候江波', '005861', 187, '127.0.0.1');
INSERT INTO `loginnum` VALUES (171, '总部', '2018-08-16 19:07:02', '候江波', '005861', 188, '127.0.0.1');
INSERT INTO `loginnum` VALUES (172, '总部', '2018-08-16 19:06:59', '候江波', '005861', 189, '127.0.0.1');
INSERT INTO `loginnum` VALUES (173, '总部', '2018-08-16 19:10:12', '候江波', '005861', 190, '127.0.0.1');
INSERT INTO `loginnum` VALUES (174, '总部', '2018-08-16 19:27:40', '候江波', '005861', 191, '127.0.0.1');
INSERT INTO `loginnum` VALUES (175, '总部', '2018-08-16 19:31:59', '候江波', '005861', 192, '127.0.0.1');
INSERT INTO `loginnum` VALUES (176, '总部', '2018-08-16 19:34:35', '候江波', '005861', 193, '127.0.0.1');
INSERT INTO `loginnum` VALUES (177, '总部', '2018-08-16 19:39:52', '候江波', '005861', 194, '192.168.0.106');
INSERT INTO `loginnum` VALUES (178, '总部', '2018-08-16 20:03:57', '候江波', '005861', 195, '127.0.0.1');
INSERT INTO `loginnum` VALUES (179, '合肥', '2018-08-16 20:03:55', '朱亚军', 'LW022', 3, '127.0.0.1');
INSERT INTO `loginnum` VALUES (180, '总部', '2018-08-16 20:04:34', '候江波', '005861', 196, '127.0.0.1');
INSERT INTO `loginnum` VALUES (181, '合肥', '2018-08-16 20:06:44', '朱亚军', 'LW022', 4, '127.0.0.1');
INSERT INTO `loginnum` VALUES (182, '总部', '2018-08-17 09:02:23', '候江波', '005861', 197, '127.0.0.1');
INSERT INTO `loginnum` VALUES (183, '总部', '2018-08-17 09:02:52', '候江波', '005861', 198, '127.0.0.1');
INSERT INTO `loginnum` VALUES (184, '总部', '2018-08-17 09:11:30', '候江波', '005861', 199, '127.0.0.1');
INSERT INTO `loginnum` VALUES (185, '总部', '2018-08-17 09:09:02', '候江波', '005861', 200, '127.0.0.1');
INSERT INTO `loginnum` VALUES (186, '总部', '2018-08-17 09:09:03', '候江波', '005861', 201, '127.0.0.1');
INSERT INTO `loginnum` VALUES (187, '总部', '2018-08-17 09:20:55', '候江波', '005861', 202, '127.0.0.1');
INSERT INTO `loginnum` VALUES (188, '总部', '2018-08-17 09:25:12', '候江波', '005861', 203, '127.0.0.1');
INSERT INTO `loginnum` VALUES (189, '总部', '2018-08-17 09:28:43', '候江波', '005861', 204, '127.0.0.1');
INSERT INTO `loginnum` VALUES (190, '总部', '2018-08-17 09:31:01', '候江波', '005861', 205, '127.0.0.1');
INSERT INTO `loginnum` VALUES (191, '总部', '2018-08-17 09:38:05', '候江波', '005861', 206, '127.0.0.1');
INSERT INTO `loginnum` VALUES (192, '总部', '2018-08-17 09:51:51', '候江波', '005861', 207, '127.0.0.1');
INSERT INTO `loginnum` VALUES (193, '总部', '2018-08-17 10:11:45', '候江波', '005861', 208, '127.0.0.1');
INSERT INTO `loginnum` VALUES (194, '总部', '2018-08-17 10:20:41', '候江波', '005861', 209, '127.0.0.1');
INSERT INTO `loginnum` VALUES (195, '总部', '2018-08-17 10:24:21', '候江波', '005861', 210, '127.0.0.1');
INSERT INTO `loginnum` VALUES (196, '总部', '2018-08-17 10:45:44', '候江波', '005861', 211, '127.0.0.1');
INSERT INTO `loginnum` VALUES (197, '总部', '2018-08-17 11:02:14', '候江波', '005861', 1, '127.0.0.1');
INSERT INTO `loginnum` VALUES (198, '总部', '2018-08-17 11:01:05', '候江波', '005861', 2, '127.0.0.1');
INSERT INTO `loginnum` VALUES (199, '总部', '2018-08-17 11:01:56', '候江波', '005861', 3, '127.0.0.1');
INSERT INTO `loginnum` VALUES (200, '总部', '2018-08-17 11:32:00', '候江波', '005861', 4, '127.0.0.1');
INSERT INTO `loginnum` VALUES (201, '总部', '2018-08-17 11:49:25', '候江波', '005861', 5, '127.0.0.1');
INSERT INTO `loginnum` VALUES (202, '总部', '2018-08-17 11:52:52', '候江波', '005861', 6, '127.0.0.1');
INSERT INTO `loginnum` VALUES (203, '总部', '2018-08-17 12:05:33', '候江波', '005861', 7, '127.0.0.1');
INSERT INTO `loginnum` VALUES (204, '总部', '2018-08-17 14:03:41', '候江波', '005861', 8, '127.0.0.1');
INSERT INTO `loginnum` VALUES (205, '总部', '2018-08-17 14:46:12', '候江波', '005861', 9, '127.0.0.1');
INSERT INTO `loginnum` VALUES (206, '总部', '2018-08-17 16:07:20', '候江波', '005861', 10, '127.0.0.1');
INSERT INTO `loginnum` VALUES (207, '总部', '2018-08-17 16:52:21', '候江波', '005861', 11, '127.0.0.1');
INSERT INTO `loginnum` VALUES (208, '总部', '2018-08-17 17:18:08', '候江波154841', '005861', 12, '127.0.0.1');
INSERT INTO `loginnum` VALUES (209, '总部', '2018-08-17 17:37:33', '候江波154841', '005861', 13, '127.0.0.1');
INSERT INTO `loginnum` VALUES (210, '总部', '2018-08-17 17:39:08', '候江波154841', '005861', 14, '127.0.0.1');
INSERT INTO `loginnum` VALUES (211, '总部', '2018-08-17 17:44:22', '候江波154841', '005861', 15, '127.0.0.1');
INSERT INTO `loginnum` VALUES (212, '总部', '2018-08-17 17:48:29', '候江波154841', '005861', 16, '127.0.0.1');
INSERT INTO `loginnum` VALUES (213, '总部', '2018-08-20 09:03:50', '候江波154841', '005861', 17, '127.0.0.1');
INSERT INTO `loginnum` VALUES (214, '总部', '2018-08-20 09:06:33', '候江波154841', '005861', 18, '127.0.0.1');
INSERT INTO `loginnum` VALUES (215, '总部', '2018-08-20 09:12:17', '候江波154841', '005861', 19, '127.0.0.1');
INSERT INTO `loginnum` VALUES (216, '总部', '2018-08-20 09:26:30', '候江波154841', '005861', 20, '127.0.0.1');
INSERT INTO `loginnum` VALUES (217, '总部', '2018-08-20 09:49:26', '候江波154841', '005861', 21, '127.0.0.1');
INSERT INTO `loginnum` VALUES (218, '总部', '2018-08-20 11:10:21', '候江波154841', '005861', 22, '127.0.0.1');
INSERT INTO `loginnum` VALUES (219, '总部', '2018-08-20 12:22:22', '候江波154841', '005861', 23, '127.0.0.1');
INSERT INTO `loginnum` VALUES (220, '总部', '2018-08-20 12:23:05', '候江波154841', '005861', 24, '127.0.0.1');
INSERT INTO `loginnum` VALUES (221, '总部', '2018-08-20 14:01:47', '候江波154841', '005861', 25, '127.0.0.1');
INSERT INTO `loginnum` VALUES (222, '总部', '2018-08-20 14:03:11', '候江波154841', '005861', 26, '127.0.0.1');
INSERT INTO `loginnum` VALUES (223, '总部', '2018-08-20 14:57:55', '候江波154841', '005861', 27, '127.0.0.1');
INSERT INTO `loginnum` VALUES (224, '总部', '2018-08-20 15:39:33', '候江波154841', '005861', 28, '127.0.0.1');
INSERT INTO `loginnum` VALUES (225, '总部', '2018-08-20 15:39:34', '候江波154841', '005861', 29, '127.0.0.1');
INSERT INTO `loginnum` VALUES (226, '总部', '2018-08-20 16:02:46', '候江波', '005861', 2, '127.0.0.1');
INSERT INTO `loginnum` VALUES (227, '总部', '2018-08-20 16:07:41', '候江波', '005861', 3, '127.0.0.1');
INSERT INTO `loginnum` VALUES (228, '总部', '2018-08-20 16:10:36', '候江波', '005861', 4, '127.0.0.1');
INSERT INTO `loginnum` VALUES (229, '总部', '2018-08-20 16:22:46', '候江波', '005861', 5, '127.0.0.1');
INSERT INTO `loginnum` VALUES (230, '总部', '2018-08-21 08:47:50', '候江波', '005861', 6, '127.0.0.1');
INSERT INTO `loginnum` VALUES (231, '总部', '2018-08-21 09:39:10', '候江波', '005861', 7, '127.0.0.1');
INSERT INTO `loginnum` VALUES (232, '总部', '2018-08-21 11:03:11', '候江波', '005861', 8, '127.0.0.1');
INSERT INTO `loginnum` VALUES (233, '总部', '2018-08-27 10:59:48', '候江波', '005861', 9, '127.0.0.1');
INSERT INTO `loginnum` VALUES (234, '总部', '2018-09-05 11:03:22', '候江波', '005861', 10, '127.0.0.1');
INSERT INTO `loginnum` VALUES (235, '总部', '2018-09-05 11:42:26', '候江波', '005861', 11, '127.0.0.1');
INSERT INTO `loginnum` VALUES (236, '总部', '2018-09-05 12:00:09', '候江波', '005861', 12, '127.0.0.1');
INSERT INTO `loginnum` VALUES (237, '福州', '2018-09-05 12:01:08', '陈莉', 'lw004', 2, '127.0.0.1');
INSERT INTO `loginnum` VALUES (238, '总部', '2018-09-05 12:01:59', '候江波', '005861', 13, '127.0.0.1');
INSERT INTO `loginnum` VALUES (239, '总部', '2018-09-05 12:30:56', '候江波', '005861', 14, '127.0.0.1');
INSERT INTO `loginnum` VALUES (240, '总部', '2018-09-05 14:02:35', '候江波', '005861', 15, '127.0.0.1');
INSERT INTO `loginnum` VALUES (241, '总部', '2018-09-05 14:36:38', '候江波', '005861', 16, '127.0.0.1');
INSERT INTO `loginnum` VALUES (242, '总部', '2018-09-05 16:27:08', '候江波', '005861', 17, '127.0.0.1');
INSERT INTO `loginnum` VALUES (243, '总部', '2018-09-06 13:02:08', '候江波', '005861', 18, '127.0.0.1');
INSERT INTO `loginnum` VALUES (244, '总部', '2018-09-07 11:25:43', '候江波', '005861', 19, '127.0.0.1');
INSERT INTO `loginnum` VALUES (245, '总部', '2018-09-07 15:48:54', '候江波', '005861', 20, '127.0.0.1');
INSERT INTO `loginnum` VALUES (246, '总部', '2018-09-07 18:25:58', '候江波', '005861', 21, '127.0.0.1');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shelves` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` int(11) NULL DEFAULT NULL,
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sop22dxm7gtufflufnsbg8cd6`(`manager`) USING BTREE,
  CONSTRAINT `FK_sop22dxm7gtufflufnsbg8cd6` FOREIGN KEY (`manager`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, '十陵', '一号仓库22dddd', '10001', '北京基地', NULL, 'A区');
INSERT INTO `warehouse` VALUES (3, '十陵aeasda', '一号仓库', '100012', 'B01A', NULL, 'B区');
INSERT INTO `warehouse` VALUES (4, '十陵', '一号仓库', '1000122', 'C01A', NULL, 'C区');
INSERT INTO `warehouse` VALUES (5, '西河', '二号仓库', '10002', 'E01E', NULL, 'E区');
INSERT INTO `warehouse` VALUES (6, '西河', '二号仓库', '10002', 'F01F', NULL, 'F区');
INSERT INTO `warehouse` VALUES (7, '西河', '二号仓库', '10002', 'G01A', NULL, 'G区');
INSERT INTO `warehouse` VALUES (8, '洛带', '三号仓库', '10003', 'H01A', NULL, 'H区');
INSERT INTO `warehouse` VALUES (9, '洛带', '三号仓库', '10003', 'I01A', NULL, 'I区');
INSERT INTO `warehouse` VALUES (10, '洛带', '三号仓库', '10003', 'J01A', NULL, 'J区');

-- ----------------------------
-- Table structure for wms_application_operation
-- ----------------------------
DROP TABLE IF EXISTS `wms_application_operation`;
CREATE TABLE `wms_application_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bach` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `batch_num` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `days` int(11) NULL DEFAULT NULL,
  `high_remind` int(11) NULL DEFAULT NULL,
  `is_pallet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_shift_park` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `low_remind` int(11) NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `costType_id` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `customer` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  `number` tinyblob NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `detailed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bagoodsTypech` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salesOrderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `storageLocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_jgkh48fch479443gfcj7jxs6o`(`costType_id`) USING BTREE,
  INDEX `FK_2a3actww91ptsoa88j2b831uf`(`create_user`) USING BTREE,
  INDEX `FK_jus2ayejcy08g3jn2mqc2wkms`(`customer`) USING BTREE,
  INDEX `FK_2xe0wr1iakucy4ven1wpgcpmr`(`goods`) USING BTREE,
  INDEX `FK_crt1t9518a0mlyn21wd9mnfjx`(`update_user`) USING BTREE,
  INDEX `FK_68ho972j937nvoul3ooqc7r1g`(`warehouse`) USING BTREE,
  CONSTRAINT `FK_2a3actww91ptsoa88j2b831uf` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_2xe0wr1iakucy4ven1wpgcpmr` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_68ho972j937nvoul3ooqc7r1g` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_crt1t9518a0mlyn21wd9mnfjx` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_jgkh48fch479443gfcj7jxs6o` FOREIGN KEY (`costType_id`) REFERENCES `wms_cost_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_jus2ayejcy08g3jn2mqc2wkms` FOREIGN KEY (`customer`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_barcode
-- ----------------------------
DROP TABLE IF EXISTS `wms_barcode`;
CREATE TABLE `wms_barcode`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barcode_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_8yhpu9dc3qncd0fsgjop3tcum`(`goods_id`) USING BTREE,
  CONSTRAINT `FK_8yhpu9dc3qncd0fsgjop3tcum` FOREIGN KEY (`goods_id`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_carrier
-- ----------------------------
DROP TABLE IF EXISTS `wms_carrier`;
CREATE TABLE `wms_carrier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carriers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `dcWarehouseT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_carrier
-- ----------------------------
INSERT INTO `wms_carrier` VALUES (1, '康程物流', '长沙DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (2, '三志物流', '济南DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (3, '上海益递物流', '杭州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (4, '中路物流', '南昌DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (5, '豪骏物流', '合肥DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (6, '中兴圣物流', '福州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (7, '吉顺隆黑龙江线', '哈尔滨DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (8, '同鑫物流', '广州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (9, '捷安物流', '广州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (10, '万达物流', '包头DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (11, '金云山西线', '太原DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (12, '灵鹤江苏线', '南京DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (13, '高快物流', '昆明DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (14, '君意通物流', '武汉DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (15, '盛世前程物流', '成都基地', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (16, '晨速物流', '成都基地', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (17, '大田物流', '成都基地', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (18, '吉顺隆辽宁线', '沈阳DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (20, 'DC自提', '长沙DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (21, 'DC自提', '济南DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (22, 'DC自提', '杭州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (23, 'DC自提', '南昌DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (24, 'DC自提', '合肥DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (25, 'DC自提', '福州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (26, 'DC自提', '哈尔滨DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (27, 'DC自提', '广州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (28, 'DC自提', '广州DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (29, 'DC自提', '包头DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (30, 'DC自提', '太原DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (31, 'DC自提', '南京DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (32, 'DC自提', '昆明DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (33, 'DC自提', '武汉DC', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (34, 'DC自提', '成都基地', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (35, 'DC自提', '成都基地', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (36, 'DC自提', '成都基地', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier` VALUES (37, 'DC自提', '沈阳DC', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_carrier_copy
-- ----------------------------
DROP TABLE IF EXISTS `wms_carrier_copy`;
CREATE TABLE `wms_carrier_copy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carriers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `dcWarehouseT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_carrier_copy
-- ----------------------------
INSERT INTO `wms_carrier_copy` VALUES (1, '万达物流', '包头', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (2, 'DC自提', '包头', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (3, '盛世前程物流', '成都', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (4, '晨速物流', '成都', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (5, '大田物流', '成都', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (6, 'DC自提', '成都', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (7, '中兴圣物流', '福州', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (8, 'DC自提', '福州', '', NULL, '', '', '2018-07-30 19:39:05', NULL);
INSERT INTO `wms_carrier_copy` VALUES (9, '同鑫物流', '广州', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (10, '捷安物流', '广州', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (11, 'DC自提', '广州', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (12, '吉顺隆黑龙江线', '哈尔滨', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (13, 'DC自提', '哈尔滨', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (14, '上海益递物流', '杭州', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (15, 'DC自提', '杭州', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (16, '豪骏物流', '合肥', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (17, 'DC自提', '合肥', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (18, '三志物流', '济南', '宗庆东', NULL, '', '18764110693', '2018-08-02 16:00:26', NULL);
INSERT INTO `wms_carrier_copy` VALUES (20, 'DC自提', '济南', '宋毅', NULL, '', '15964101587', '2018-08-02 16:00:01', NULL);
INSERT INTO `wms_carrier_copy` VALUES (21, '高快物流', '昆明', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (22, 'DC自提', '昆明', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (23, '中路物流', '南昌', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (24, 'DC自提', '南昌', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (25, '灵鹤江苏线', '南京', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (26, 'DC自提', '南京', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (27, '吉顺隆辽宁线', '沈阳', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (28, 'DC自提', '沈阳', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (29, '金云山西线', '太原', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (30, 'DC自提', '太原', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (31, '君意通物流', '武汉', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (32, 'DC自提', '武汉', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (33, '康程物流', '长沙', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (34, 'DC自提', '长沙', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_carrier_copy` VALUES (35, '瀛海物流', '杭州', '', '2018-08-03 11:26:19', '', '', '2018-08-06 15:56:29', NULL);

-- ----------------------------
-- Table structure for wms_carry_record
-- ----------------------------
DROP TABLE IF EXISTS `wms_carry_record`;
CREATE TABLE `wms_carry_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `nianyueri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_category
-- ----------------------------
DROP TABLE IF EXISTS `wms_category`;
CREATE TABLE `wms_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_category
-- ----------------------------
INSERT INTO `wms_category` VALUES (1, '1', '1');

-- ----------------------------
-- Table structure for wms_cost_agent
-- ----------------------------
DROP TABLE IF EXISTS `wms_cost_agent`;
CREATE TABLE `wms_cost_agent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abAgent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cusName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shipVia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2495 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_cost_agent
-- ----------------------------
INSERT INTO `wms_cost_agent` VALUES (1909, 'CEZ.BLN', '郴州-刘彬彬', NULL, NULL, '13975588988', '湖南省郴州市燕泉广场六栋218号', '康程物流', '刘彬彬', NULL, '货站自提', '郴州', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1910, 'CHD', '常德', NULL, NULL, '18229606817', '湖南省常德市武陵区火车站建材市场', '康程物流', '李蓉', NULL, '送货', '常德', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1911, 'HEY.YY', '衡阳雅园', NULL, NULL, '0734-8586990/13367345507', '衡阳市华源市场B区8栋201', '康程物流', '李青松', NULL, '货站自提', '衡阳', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1912, 'HHA', '怀化', NULL, NULL, '13034876280/ 0745-2693888', '怀化河西舞水二桥 桥头  博洛尼整体家居', '康程物流', '罗安琼', NULL, '货站自提', '怀化', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1913, 'JIS', '吉首', NULL, NULL, '17707436007', '湖南省吉首市老爹家居建材广场', '康程物流', '曾远坤', NULL, '货站自提', '吉首', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1914, 'ZJJ', '张家界', NULL, NULL, '13337241010', '湖南省张家界市永定区建材市场D栋二楼博洛尼橱柜店', '康程物流', '陈武', NULL, '货站自提', '张家界', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1915, 'ANH', '安化', NULL, NULL, '13973676042', '湖南省益阳市安化县东坪镇南区金桥建材家居城101-103号门面龙鑫装饰', '康程物流', '李应中', NULL, '货站自提', '安化', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1916, 'XIT.BLN', '湘潭-王琦', NULL, NULL, '18673231815', '湖南省湘潭市岳塘区红旗商贸城', '康程物流', '唐英', NULL, '货站自提', '湘潭', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1917, 'LDI.BLN', '娄底-黄丽梅', NULL, NULL, '18824309009', '湖南省娄底市长表居民点长青名苑小区院内', '康程物流', '肖花', NULL, '货站自提', '娄底', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1918, 'YIY', '益阳博洛尼', NULL, NULL, '18890517800', '湖南省益阳市赫山区城际干道汽车工业园斜对过顺德城建材B区2楼博洛尼门店', '康程物流', '刘玉', NULL, '送货', '益阳', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1919, 'LIX.BLN', '澧县-张雨晴', NULL, NULL, '13148995008', '湖南省澧县富贵世家精品建材城一楼05-06号', '康程物流', '王奎', NULL, '货站自提', '澧县', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1920, 'ZZO.BLN', '株洲-马艳红', NULL, NULL, '18807484013', '长沙市开福区中青路1199号C4栋最右侧', 'DC自提', '李春望', NULL, 'DC自提', '株洲', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1921, 'CSA', '长沙', NULL, NULL, '18807484013', '长沙市开福区中青路1199号C4栋最右侧', 'DC自提', '李春望', NULL, 'DC自提', '长沙', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1922, 'YUY.BLN', '岳阳博洛尼', NULL, NULL, '13973065767', '湖南省岳阳市开发区康王经济园', 'DC自提', '易想舟', NULL, 'DC自提', '岳阳', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1923, 'ZUZ', '株州', NULL, NULL, '', '', '康程物流', '', NULL, '货站自提', '株州', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1924, 'LDI', '娄底', NULL, NULL, '18824309009', '娄底市娄星区新合作红星美凯龙商场一楼博洛尼体验店 ', '康程物流', '肖花', NULL, '货站自提', '娄底', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1925, 'CEZ', '郴州', NULL, NULL, '13975588988', '湖南省郴州市燕泉广场六栋218号', '康程物流', '刘彬彬', NULL, '货站自提', '郴州', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1926, 'XIT', '湘潭', NULL, NULL, '', '', '康程物流', '', NULL, '货站自提', '湘潭', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1927, 'ZZO', '株洲', NULL, NULL, '13873159699', '湖南省株洲市红星美凯龙橱柜区', '康程物流', '徐颖', NULL, '货站自提', '株洲', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1928, 'SHD', '邵东', NULL, NULL, '15607396888', '湖南省邵东县开发区公园路5幢101-102', '康程物流', '申晓祥', NULL, '送货', '邵东', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1929, 'TAJ', '桃江', NULL, NULL, '', '', '康程物流', '', NULL, '货站自提', '桃江', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1930, 'SAY', '邵阳', NULL, NULL, '', '', '康程物流', '', NULL, '送货', '邵阳', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1931, 'LIX', '澧县', NULL, NULL, '13520199983', '湖南省常德市澧县澧州大道富贵世家精品建材城博洛尼店一楼05 06号门面', '康程物流', '谭钢', NULL, '送货', '澧县', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1932, 'HYA', '衡阳-文湘钊', NULL, NULL, '13367345507', '衡阳市华源市场B区8栋201', '康程物流', '李青松 ', NULL, '货站自提', '衡阳', '湖南', NULL);
INSERT INTO `wms_cost_agent` VALUES (1933, 'TRE', '铜仁－吴晓锋', NULL, NULL, '18985339577', '贵州省铜仁市万山区西南建材市场13栋1-5号', 'DC自提', '吴晓锋', NULL, 'DC自提', '铜仁', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (1934, 'TRE.BLN', '铜仁-博洛尼', NULL, NULL, '18985339577', '贵州省铜仁市松桃苗族自治县甘龙镇英庄村吴溪组', 'DC自提', '吴晓锋', NULL, 'DC自提', '铜仁', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (1935, 'DYG.HRD', '东营鸿瑞达', NULL, NULL, '15275642289', '', '三志物流', '许鹏程', NULL, '送货', '东营', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1936, 'JIN.ZHX', '济南中信', NULL, NULL, '15315316509', '博洛尼济南DC仓库', 'DC自提', '王云', NULL, 'DC自提', '济南', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1937, 'JNG', '济宁', NULL, NULL, '18653791953', '山东济宁市凌云南路', '三志物流', '路新军', NULL, '送货', '济宁', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1938, 'LAI', '莱州', NULL, NULL, '18364405959', '山东省莱州市云峰北路诚佑集成家居（云峰家居世界商场对面）', '三志物流', '刘东', NULL, '货站自提', '莱州', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1939, 'PDU', '平度', NULL, NULL, '18953268081', '山东平度红旗路12号', '三志物流', '马双磊', NULL, '货站自提', '平度', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1940, 'RIZ', '日照', NULL, NULL, '139696610770532-80970337', '日照市黄海三路与海滨五路交界处火车站广场 永大家居建材城', '三志物流', '秦女士', NULL, '货站自提', '日照', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1941, 'RUS', '乳山', NULL, NULL, '139631857830631-6953369', '山东省乳山市久久发商贸城雅宝国际家居南门1号', '三志物流', '阎勇', NULL, '货站自提', '乳山', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1942, 'TAN', '泰安', NULL, NULL, '13563800096/13405387988', '山东泰安市迎胜路七里装饰材料精品城后面居民楼', 'DC自提', '王桂枝李总', NULL, 'DC自提', '泰安', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1943, 'TSD.BLN', '青岛博洛尼', NULL, NULL, '13668896590', '山东青岛市崂山区中韩街道孙家下庄129号，（辽阳东路孙家下庄路品往南150米，如家手工饺子馆旁）博洛尼仓库', '三志物流', '马璐璐', NULL, '送货', '青岛', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1944, 'TYB', '烟台3', NULL, NULL, '', '', '三志物流', '', NULL, '货站自提', '烟台', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1945, 'WEH', '威海', NULL, NULL, '18663145581', '威海市海滨南路望海名居2-2  博洛尼家居体验馆', '三志物流', '李潇潇', NULL, '送货', '威海', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1946, 'ZAO', '枣庄', NULL, NULL, '1.59668E+21', '山东省枣庄市佳美装饰广场B座12-13号', '三志物流', '蒋丽丽', NULL, '货站自提', '枣庄', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1947, 'ZCH', '邹城', NULL, NULL, '15263799901  13562748607', '山东省邹城市太平西路666号科宝博洛尼展厅', 'DC自提', '殷 飞 王洪鑫', NULL, 'DC自提', '邹城', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1948, 'ZHC', '诸城', NULL, NULL, '13721941888', '山东省诸城市欧美尔国际家居装饰城', '三志物流', '王海英', NULL, '货站自提', '诸城', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1949, 'ZBO', '淄博博洛尼', NULL, NULL, '13969336968', '淄博市张店区人民东路和东四路交叉口东南杜科工业园', '三志物流', '董曙波', NULL, '送货', '淄博', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1950, 'BZH', '滨州', NULL, NULL, '17362009998', '山东省滨州市滨城区黄河十二路渤海二路豪德商贸城南街75号博洛尼', '三志物流', '赵伟', NULL, '货站自提', '滨州', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1951, 'QIZ', '青州', NULL, NULL, '15863695066', '山东省 青州市范公亭路 泰丰购物广场', '三志物流', '窦淑芬', NULL, '货站自提', '青州', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1952, 'YNT', '烟台2', NULL, NULL, '', '', '三志物流', '', NULL, '货站自提', '烟台', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1953, 'LAIS', '莱州市', NULL, NULL, '18364405959', '山东省莱州市云峰北路诚佑集成家居（云峰家居世界商场对面）', '三志物流', '刘东', NULL, '货站自提', '莱州', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1954, 'WHA', '威海市', NULL, NULL, '18660312999', '威海市海滨南路望海名居2-2  博洛尼家居体验馆', '三志物流', '张驰', NULL, '送货', '威海', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1955, 'LWU', '莱芜', NULL, NULL, '15606344999', '山东莱芜市莱城区大桥路徐家河沿街楼博洛尼整体厨房', '三志物流', '秦光庆', NULL, '送货', '莱芜', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1956, 'HEZ', '荷泽', NULL, NULL, '18605301733   15264082563  13869739531', '菏泽市人民路亿丰时代广场银座家居一楼', '三志物流', '秦跃鹏、马兴', NULL, '货站自提', '荷泽', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1957, 'WEF', '潍坊', NULL, NULL, '18910018184', '山东省潍坊市高新区红星美凯龙三层', '三志物流', '段建涛', NULL, '货站自提', '潍坊', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1958, 'DEZ', '德州', NULL, NULL, '18605346218', '德州市德城区解放中大道789号', 'DC自提', '何勇', NULL, 'DC自提', '德州', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1959, 'HZE', '菏泽', NULL, NULL, '13295308589', '菏泽市人民路亿丰时代广场银座家居一楼', '三志物流', '曹存记', NULL, '货站自提', '菏泽', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1960, 'BOX', '博兴', NULL, NULL, '13173288333', '山东省博兴县乐安大街篮球馆对面', '三志物流', '戴波涛', NULL, '货站自提', '博兴', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1961, 'LYI.BLN', '临沂博洛尼', NULL, NULL, '17753865909', '临沂市兰山区临西十一路与水田路交汇东100米', '三志物流', '唐秀富', NULL, '货站自提', '临沂', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1962, 'DGY', '东营', NULL, NULL, '15263803278', '山东省东营市东营区耿井村', '三志物流', '姜竹军', NULL, '货站自提', '东营', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1963, 'JMO', '即墨', NULL, NULL, '15898823179', '山东省即墨海博家居3层313号', '三志物流', '吴涵', NULL, '货站自提', '即墨', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1964, 'LIC', '聊城', NULL, NULL, '15063597222', '山东聊城市东昌路与庐山路交叉口东北角', 'DC自提', '任家坤', NULL, 'DC自提', '聊城', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1965, 'JNA', '济南博洛尼', NULL, NULL, '', '博洛尼济南DC仓库', 'DC自提', '', NULL, 'DC自提', '济南', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1966, 'LYI.YH', '临沂银河', NULL, NULL, '15065923360', '山东省临沂市红星美凯龙建材馆一楼A8039', '三志物流', '肖娜', NULL, '送货', '临沂', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1967, 'PEL', '蓬莱', NULL, NULL, '', '', '三志物流', '', NULL, '送货', '蓬莱', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1968, 'YNT.BLN', '烟台博洛尼', NULL, NULL, '13012571806', '山东烟台市芝罘区区河路26号金盾防火设备有限公司', '三志物流', '李树涛', NULL, '货站自提', '烟台', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1969, 'BZH.BLN', '滨州博洛尼', NULL, NULL, '13605439078', '山东省滨州市滨城区黄河十二路渤海二路豪德商贸城南街75号博洛尼', '三志物流', '赵伟', NULL, '货站自提', '滨州', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1970, 'ROC', '荣成', NULL, NULL, '13721939666', '山东荣成市观海路居然之家博隆店正南门', '三志物流', '张凉', NULL, '货站自提', '荣成', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1971, 'WEF.BLN', '潍坊博洛尼', NULL, NULL, '‭15653680452‬', '潍坊市寒亭区通亭街百汇路东加油站路南', '三志物流', '管秀超', NULL, '货站自提', '潍坊', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1972, 'LKO', '龙口', NULL, NULL, '18954558989', '山东省龙口市龙族风景35号楼博洛尼', '三志物流', '孙允伟', NULL, '货站自提', '龙口', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1973, 'ZBO.BLN', '淄博', NULL, NULL, '13070643416', '淄博市张店区人民东路和东四路交叉口东南杜科工业园', '三志物流', '刘叔勇', NULL, '货站自提', '淄博', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1974, 'YUC', '郓城－周广庆', NULL, NULL, '18454048777/18063295888', '郓城县利民街南段御景苑售楼处北', '三志物流', '周广庆', NULL, '货站自提', '郓城', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1975, 'JMO.BLN', '即墨－宋永泽', NULL, NULL, '', '', '三志物流', '', NULL, '货站自提', '即墨', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (1976, 'HIN', '海宁', NULL, NULL, '13379584503', '浙江省海宁市缔艺家居广场二层', '上海益递物流', '钱存才', NULL, '货站自提', '海宁', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1977, 'JAX', '嘉兴', NULL, NULL, '0573-82763377/13396832556', '浙江省嘉兴市南湖区凌公塘路与07省道交叉亚太花园', '上海益递物流', '王波', NULL, '送货', '嘉兴', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1978, 'JHA', '金华', NULL, NULL, '15841277588', '浙江省金华市金东区毛草山村（环城南路上海财经学院旁）', '上海益递物流', '刘彬', NULL, '送货', '金华', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1979, 'LQG', '乐清', NULL, NULL, '13757771104', '浙江省乐清市南岸村永康路', '上海益递物流', '方紫宇', NULL, '送货', '乐清', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1980, 'LSI', '丽水市', NULL, NULL, '15925751833', '浙江省丽水市紫玉路236号', '上海益递物流', '张伟屯', NULL, '送货', '丽水', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1981, 'QUZ', '衢州', NULL, NULL, '18969490730', '浙江省 衢州市 柯城区 花园街道 广汇建材市场大门口博洛尼整体厨房', '上海益递物流', '刘腾宗', NULL, '货站自提', '衢州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1982, 'RAN', '瑞安', NULL, NULL, '13967070912', '瑞安市莘阳大道稠州银行对面（熙望车业隔壁）', '上海益递物流', '吴水根', NULL, '送货', '瑞安', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1983, 'SHX', '绍兴', NULL, NULL, '18205859898', '浙江省 上虞区杭甬高速公路上虞出口处东首进市场右边第2家店', '上海益递物流', '陈权', NULL, '送货', '绍兴', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1984, 'SYU', '上虞', NULL, NULL, '13858451988', '上虞老石狮甲板城一号', '上海益递物流', '陈总', NULL, '送货', '上虞', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1985, 'TZH.JT', '台州九泰', NULL, NULL, '15888655252', '浙江省台州市椒江区洪家红星美凯龙四楼D8018博洛尼整体厨房', '上海益递物流', '汪靖阳', NULL, '送货', '台州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1986, 'WEL', '温岭', NULL, NULL, '13336761313', '浙江省温岭市阳光大道22-24号', '上海益递物流', '颜芳芳', NULL, '送货', '温岭', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1987, 'WEZ', '温州', NULL, NULL, '13695805386**0577-88900321', '温州市蛟凤北路101号', '上海益递物流', '张泽华', NULL, '送货', '温州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1988, 'XSH', '象山', NULL, NULL, '13567894187', '浙江省象山市商业建材城', '上海益递物流', '杜炜', NULL, '货站自提', '象山', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1989, 'YUH', '玉环', NULL, NULL, '13355860981/13362662292', '浙江省玉环县双港路37博洛尼整体厨房旗舰店', '上海益递物流', '黄友财/程锋', NULL, '送货', '玉环', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1990, 'YWU', '义乌', NULL, NULL, '15267976111', '义乌市西城路1779号家具市场三区31号门三楼K31－35', '上海益递物流', '朱雪彬', NULL, '货站自提', '义乌', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1991, 'ZJS', '诸暨博洛尼', NULL, NULL, '18767558862', '浙江省.绍兴市.诸暨市.暨阳街道.暨南路28号“港龙国际食品城”内7-107', '上海益递物流', '黄向娟', NULL, '送货', '诸暨', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1992, 'LIH', '临海', NULL, NULL, '13606689341', '临海市靖江路75号', 'DC自提', '徐浩敏', NULL, 'DC自提', '临海', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1993, 'DYG', '东阳', NULL, NULL, '13335918708', '浙江省东阳市汉宁东路152—8号', '上海益递物流', '张青青', NULL, '送货', '东阳', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1994, 'YKG', '永康', NULL, NULL, '13777536250', '浙江省永康市溪心路65-67号', '上海益递物流', '陈华莹', NULL, '送货', '永康', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1995, 'TLU', '桐庐', NULL, NULL, '18989877470', '桐庐迎春南路29号亿融财富广场一楼', '上海益递物流', '程立强', NULL, '货站自提', '桐庐', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1996, 'NGB', '宁波', NULL, NULL, '13780069971', '浙江省宁波市鄞州区邱隘镇袁家', '上海益递物流', '赵刚', NULL, '送货', '宁波', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1997, 'NJI', '安吉', NULL, NULL, '18042272966', '浙江省安吉县浒溪佳苑12-3号', '上海益递物流', '章姐', NULL, '送货', '安吉', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1998, 'HUZ', '湖州', NULL, NULL, '137-7175-7128 /135 8723 9987', '浙江省湖州市亿丰物流仓储基地 港口路799号', '上海益递物流', '谢康旭', NULL, '送货', '湖州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (1999, 'CAN', '苍南', NULL, NULL, '13819795816', '浙江省苍南县灵溪镇山海小区2-17栋', '上海益递物流', '陈岳用', NULL, '送货', '苍南', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2000, 'CIX', '慈溪', NULL, NULL, '18858525880', '慈溪红星美凯龙三楼博洛尼', '上海益递物流', '陈丽君', NULL, '送货', '慈溪', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2001, '台州1', '台州1', NULL, NULL, '', '', '上海益递物流', '', NULL, '送货', '台州1', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2002, 'LIS', '丽水', NULL, NULL, '15925751833', '浙江省丽水市紫玉路236号', '上海益递物流', '张伟屯', NULL, '送货', '丽水', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2003, 'RUA', '瑞安博洛尼', NULL, NULL, '13967070912', '瑞安市莘阳大道稠州银行对面（熙望车业隔壁）', '上海益递物流', '吴水根', NULL, '送货', '瑞安', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2004, 'WNL', '温岭博洛尼', NULL, NULL, '', '', '上海益递物流', '', NULL, '送货', '温岭', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2005, 'PIY', '平阳', NULL, NULL, '13819795816', '浙江省苍南县灵溪镇上江小区1-5幢', '上海益递物流', '陈岳用', NULL, '送货', '平阳', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2006, 'PUJ', '浦江', NULL, NULL, '13575928096', '浙江省浦江县中山南路205号（惠家厨卫）', '上海益递物流', '寿丽艳', NULL, '送货', '浦江', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2007, 'FYG', '富阳', NULL, NULL, '15088668797', '浙江省富阳市金平路112一6号博洛尼整体家居', '上海益递物流', '狄昭伦', NULL, '送货', '富阳', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2008, 'TLU.BLN', '桐庐博洛尼', NULL, NULL, '18989877470', '桐庐迎春南路29号亿融财富广场一楼', '上海益递物流', '程立强', NULL, '货站自提', '桐庐', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2009, 'TXG', '桐乡', NULL, NULL, '', '', '上海益递物流', '', NULL, '送货', '桐乡', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2010, 'NGB.BLN', '宁波博洛尼', NULL, NULL, '13780069971', '浙江省宁波市鄞州区邱隘镇袁家', '上海益递物流', '赵刚', NULL, '送货', '宁波', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2011, 'TGL', '桐庐-奚伟冰', NULL, NULL, '18989877470', '桐庐迎春南路29号亿融财富广场一楼', '上海益递物流', '程立强', NULL, '货站自提', '桐庐', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2012, 'YKG.BLN', '永康－东城科洛', NULL, NULL, '13777536250', '浙江省永康市溪心路65-67号', '上海益递物流', '陈华莹', NULL, '送货', '永康', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2013, 'WNL.BLN', '温岭－颜芳芳', NULL, NULL, '13336761313', '浙江省温岭市阳光大道22-24号', '上海益递物流', '颜芳芳', NULL, '送货', '温岭', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2014, 'SHX.BLN', '绍兴-王翱', NULL, NULL, '18205859898', '浙江省 上虞区杭甬高速公路上虞出口处东首进市场右边第2家店', '上海益递物流', '陈权', NULL, '送货', '绍兴', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2015, 'ZOS', '舟山－于建霞', NULL, NULL, '13375800166', '浙江舟山定海区临城街道海天大道1188号红星美凯龙三楼。', '上海益递物流', '于建霞', NULL, '货站自提', '舟山', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2016, 'JAS.BLN', '嘉善－陈仙强', NULL, NULL, '13396832556', '嘉兴市南湖区07省道与凌公塘路交汇处亚太花园内', '上海益递物流', '王博', NULL, '送货', '嘉善', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2017, 'JHA.BLN', '金华-戈亚', NULL, NULL, '15841277588', '浙江省金华市金东区毛草山村（环城南路上海财经学院旁）', '上海益递物流', '刘彬', NULL, '送货', '金华', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2018, 'YYA', '余姚-杨永成', NULL, NULL, '18858017970', '浙江省余姚市阳明街道丰山路398号红星美凯龙', '上海益递物流', '毛工', NULL, '送货', '余姚', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2019, 'DYG.BLN', '东阳-董建锋', NULL, NULL, '13335918708', '浙江省东阳市汉宁东路152—8号', '上海益递物流', '张青青', NULL, '货站自提', '东阳', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2020, 'CHX', '长兴', NULL, NULL, '13362086208', '浙江省长兴市太湖中路308号王山新村', '上海益递物流', '潘笑宇', NULL, '货站自提', '长兴', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2021, 'LXI', '兰溪博洛尼', NULL, NULL, '15215878777', '浙江省兰溪市永顺路110-118号', '上海益递物流', '周总', NULL, '货站自提', '兰溪', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2022, 'LXI.BLN', '兰溪-黄雅芳', NULL, NULL, '15215878777', '浙江省兰溪市永顺路110-118号', '上海益递物流', '周总', NULL, '货站自提', '兰溪', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2023, 'YJA', '永嘉博洛尼', NULL, NULL, '13777799121', '浙江省永嘉县瓯北镇新华路金汇景园7幢105-107号店面', '上海益递物流', '余士深', NULL, '货站自提', '永嘉', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2024, 'JSH', '江山', NULL, NULL, '13867009229', '浙江省江山市东岳路6', '上海益递物流', '王慧', NULL, '货站自提', '江山', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2025, 'JSN', '嘉善县', NULL, NULL, '0573-8276337713396832556', '嘉兴市南湖区凌公塘东路亚太花园内', '上海益递物流', '王波', NULL, '送货', '嘉善', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2026, 'ZJI', '诸暨', NULL, NULL, '18767558862', '浙江省.绍兴市.诸暨市.暨阳街道.暨南路28号“港龙国际食品城”内7-107', '上海益递物流', '黄向娟', NULL, '送货', '诸暨', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2027, 'HZH.BLN', '杭州博洛尼', NULL, NULL, '18106586719', '浙江省杭州市滨江区第六空间国际建材馆c1-05', '上海益递物流', '狄鑫杰', NULL, '货站自提', '杭州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2028, 'SGZ', '嵊州', NULL, NULL, '13754351820', '浙江省嵊州市信源国际家居橱柜区', '上海益递物流', '李永军', NULL, '货站自提', '嵊州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2029, 'HZO', '杭州市', NULL, NULL, '18106586719', '浙江省杭州市滨江区第六空间国际建材馆c1-05', '上海益递物流', '狄鑫杰', NULL, '送货', '杭州', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2030, 'HIN.BLN', '海宁博洛尼', NULL, NULL, '13379584503', '浙江省海宁市缔艺家居广场二层', '上海益递物流', '钱存才', NULL, '货站自提', '海宁', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2031, 'JAS', '嘉善－曹佳奇', NULL, NULL, '13384841184', '包头市友谊大街现代城54号底店', '上海益递物流', '田旭东', NULL, '货站自提', '嘉善', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2032, 'CHA', '淳安-朱剑', NULL, NULL, '18966486852', '浙江省杭州市淳安县千岛湖镇马路村木格装饰', '上海益递物流', '朱剑', NULL, '货站自提', '淳安', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2033, 'ADZ', '嘉兴奥迪', NULL, NULL, '13705733384', '浙江省嘉兴市中环南六亚太园区（嘉兴汽车商贸园东侧）', '上海益递物流', '徐金良', NULL, '货站自提', '嘉兴', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2034, 'FEC', '丰城', NULL, NULL, '137070019630795-6290733', '江西省丰城市金马装饰城二期四栋十七号', '中路物流', '蔡丽军', NULL, '送货', '丰城', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2035, 'GAN', '高安', NULL, NULL, '139795798500795-5250986', '高安市赤土板路叠翠家园14-16号', '中路物流', '冷闽', NULL, '送货', '高安', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2036, 'GZO', '赣州', NULL, NULL, '1,330,706,961,718,160,000,000', '江西省赣州市章贡区长征大道2号博洛尼家居店', '中路物流', '肖跃平，何小春', NULL, '货站自提', '赣州', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2037, 'JUJ', '九江', NULL, NULL, '18170270666', '江西省九江市浔南大道8号红星美凯龙全球家居生活广场二楼 博洛尼体验馆', '中路物流', '周光辉', NULL, '送货', '九江', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2038, 'SHR.BSH', '上饶百盛', NULL, NULL, '15870916658，0793-8271333', '江西省上饶市紫阳华庭5栋5-9号', '中路物流', '苏涛', NULL, '送货', '上饶', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2039, 'XGO', '兴国', NULL, NULL, '13766304188', '江西省赣州市兴国县将军大道南段B23栋建材精品广场', '中路物流', '曾以仁', NULL, '货站自提', '兴国', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2040, 'NCH', '南昌', NULL, NULL, '18611951044', '南昌市西湖区灌婴路599号象湖2008 603室', '中路物流', '王硕仁', NULL, '送货', '南昌', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2041, 'YIC', '宜春', NULL, NULL, '13767532333', '江西省宜春市秀江中路85-9号', '中路物流', '彭晓琴', NULL, '货站自提', '宜春', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2042, 'NCS', '南昌市', NULL, NULL, '18770023989', '南昌市西湖区灌婴路599号象湖2008   603室', '中路物流', '徐明初', NULL, '送货', '南昌', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2043, 'FZH', '抚州', NULL, NULL, '15397948886', '江西省抚州市玉若大道廷伸段华福公寓（市财政局旁）', '中路物流', '曾华兴', NULL, '送货', '抚州', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2044, 'SHR', '上饶博洛尼', NULL, NULL, '15870916658，0793-8271333', '江西省上饶市信州区上饶东高速路口江西远泉花木博园', '中路物流', '苏涛', NULL, '送货', '上饶', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2045, 'GZH', '赣州博洛尼', NULL, NULL, '13879757211', '江西省赣州市章贡区长征大道2号博洛尼家居店', '中路物流', '邓旭兰', NULL, '货站自提', '赣州', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2046, 'NCS.NCX', '南昌县', NULL, NULL, '18070034489', '南昌市南昌县振兴路777号月星国际家居建材广场', 'DC自提', '江志强', NULL, 'DC自提', '南昌', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2047, 'NCS.BLN', '南昌博洛尼', NULL, NULL, '13697913815', '江西省南昌市南昌县小兰开发区金沙大道1388号', 'DC自提', '李文强', NULL, 'DC自提', '南昌', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2048, 'JUJ.BLN', '九江博洛尼', NULL, NULL, '18170270666', '江西省九江市庐山区五里村九星公路旁西洋垄农民公寓11栋1楼', '中路物流', '周光辉', NULL, '送货', '九江', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2049, 'JIA', '吉安博洛尼', NULL, NULL, '18607062130', '江西省吉安市吉州区复兴路凯旋世纪城A栋', 'DC自提', '周群', NULL, 'DC自提', '吉安', '江西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2050, 'HEF.DG', '合肥博洛尼', NULL, NULL, '18155139221', '合肥市蜀山产业园井岗路与振兴路交口电商园一期三栋', 'DC自提', '朱月琴', NULL, 'DC自提', '合肥', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2051, 'MAS', '马鞍山', NULL, NULL, '15551777377', '马鞍山市花山区笔架山路1166号博浪科技（沙发厂）', '豪骏物流', '鲍辉', NULL, '货站自提', '马鞍山', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2052, 'WHU', '芜湖', NULL, NULL, '15555330101', '安徽省芜湖市中南建材城11号', '豪骏物流', '唐中祥', NULL, '送货', '芜湖', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2053, 'FYN', '阜阳', NULL, NULL, '13966806722', '阜阳市气象大厦名牌装建材城', '豪骏物流', '李亚飞', NULL, '货站自提', '阜阳', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2054, 'TIC', '天长', NULL, NULL, '18815501888', '安徽省滁洲市天长市平安路千秋水都斜对面广厦商贸城博洛尼整体厨房', '豪骏物流', '董大银', NULL, '货站自提', '天长', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2055, 'HAB', '淮北博洛尼', NULL, NULL, '18756100568', '安徽省淮北市杜集区淮海东路与龙山路交叉口，红星美凯龙', '豪骏物流', '刘乃军', NULL, '送货', '淮北', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2056, 'LAN', '六安', NULL, NULL, '18956401900', '安徽省六安市红星美凯龙二层', '豪骏物流', '程浩', NULL, '送货', '六安', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2057, 'HUS', '黄山', NULL, NULL, '15856670344', '安徽省黄山市屯溪区展望家居广场A区1号', '豪骏物流', '徐银花', NULL, '货站自提', '黄山', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2058, 'QIY', '青阳', NULL, NULL, '', '', '豪骏物流', '', NULL, '送货', '青阳', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2059, 'TAH', '太和-杨智', NULL, NULL, '18895522713', '安徽省太和县城关镇国泰路望和新世界E座1楼易美装饰有限公司', '豪骏物流', '王梦宇', NULL, '货站自提', '太和', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2060, 'TNL', '铜陵-杨宜兰', NULL, NULL, '', '', '豪骏物流', '', NULL, '货站自提', '铜陵', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2061, 'LQN', '临泉-粱东梅', NULL, NULL, '', '', '豪骏物流', '', NULL, '货站自提', '临泉', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2062, 'FUZ.BLN', '福州博洛尼', NULL, NULL, '13696862072', '仓山区叶厦路198号盛辉配载中心3楼', '恒运宏远', '卢丽虹', NULL, '送货', '福州', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2063, 'JJG.BLN', '晋江博洛尼', NULL, NULL, '18959880288', '福建石狮宝盖科技园宝科路82号', '中兴圣物流', '蔡蓓芬', NULL, '送货', '晋江', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2064, 'LCH', '龙岩连城', NULL, NULL, '189590868880597－8188888', '连城县洪山电信局附楼', '中兴圣物流', '邹力生', NULL, '货站自提', '龙岩', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2065, 'LYN', '龙岩', NULL, NULL, '13950862956', '福建省龙岩市新罗区闽西交易城红星美凯龙', '中兴圣物流', '黄微霞', NULL, '货站自提', '龙岩', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2066, 'NDE', '宁德', NULL, NULL, '18650566228', '福建省宁德市东侨区闽东中路28号盈丰佳园6栋101-106号', '中兴圣物流', '孙永文', NULL, '送货', '宁德', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2067, 'PUT.JLD', '莆田嘉利达', NULL, NULL, '136-6692-8614', '福建省莆田市荔城区石顶小镇', '中兴圣物流', '陈曙杰', NULL, '送货', '莆田', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2068, 'QZH', '泉州', NULL, NULL, '18965665222', '泉州市丰泽区安吉南路887号', '中兴圣物流', '陈明山', NULL, '送货', '泉州', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2069, 'SAM', '三明沙县', NULL, NULL, '13365059316', '福建省沙县鸿辉园8-9栋店面', '中兴圣物流', '陈芳', NULL, '货站自提', '三明', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2070, 'SHI.BLN', '石狮博洛尼', NULL, NULL, '18959880288', '石狮征达广场C区44－48号', '中兴圣物流', '蔡蓓芬', NULL, '送货', '石狮', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2071, 'SMS', '三明1', NULL, NULL, '18350807360', '福建省三明市三元区长安路红星美凯龙A-8039博洛尼橱柜', '中兴圣物流', '陈佳', NULL, '货站自提', '三明', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2072, 'XIA', '厦门', NULL, NULL, '18859288352/05925593630', '厦门市湖里区钟宅村', '中兴圣物流', '蔡珊萍', NULL, '货站自提', '厦门', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2073, 'FUQ', '福清', NULL, NULL, '0591-85166569', '福清市元洪路诚丰世纪园6号-202博洛尼整体家居馆', '中兴圣物流', '林国清', NULL, '货站自提', '福清', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2074, 'LJG', '连江', NULL, NULL, '13799337708     13459148438', '福建省连江县文笔西路金凤名都8号10-12号店', '中兴圣物流', '雷文银、雷武略', NULL, '送货', '连江', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2075, 'MHO', '闽侯', NULL, NULL, '13107623388', '福建省闽侯县海峡国际家居城3楼113号', '中兴圣物流', '赵金龙', NULL, '货站自提', '闽侯', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2076, 'SAX', '沙县', NULL, NULL, '18950980189', '福建省沙县鸿辉园8-9栋店面', '中兴圣物流', '陈芳', NULL, '货站自提', '沙县', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2077, 'FAN', '福安市博洛尼', NULL, NULL, '15160703476', '福建省福安市棠兴路375号', '中兴圣物流', '连雄安', NULL, '货站自提', '福安', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2078, 'CHL', '长乐', NULL, NULL, '1.39503E+21', '福建省长乐市鹤上镇福北路长乐海峡建材家居城2号楼2层41、42', '中兴圣物流', '翟运文', NULL, '货站自提', '长乐', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2079, 'PTN', '平潭', NULL, NULL, '15280000279', '福建省平潭县潭城镇东湖庄206号9幢3单元', '中兴圣物流', '郑仟泉', NULL, '送货', '平潭', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2080, 'NDE.BLN', '宁德博洛尼', NULL, NULL, '', '', '中兴圣物流', '', NULL, '送货', '宁德', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2081, 'FUQ.BLN', '福清博洛尼', NULL, NULL, '15159606060', '福清市广益建材家居市场11号楼一层品格吊顶', '中兴圣物流', '叶子', NULL, '货站自提', '福清', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2082, 'XIA.BLN', '厦门－李俊伟', NULL, NULL, '18206069718', '厦门市湖里区钟宅五缘湾红星美凯龙', '中兴圣物流', '李智盛', NULL, '货站自提', '厦门', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2083, 'FAN.BLN', '福安－合家欢', NULL, NULL, '15160703476', '福建省福安市棠兴路375号', '中兴圣物流', '连雄安', NULL, '货站自提', '福安', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2084, 'MHO.BLN', '闽侯-张斌', NULL, NULL, '13696862072', '福州博洛尼库房', '中兴圣物流', '卢丽虹', NULL, '送货', '闽侯', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2085, 'ZAZ', '漳州-陈旭', NULL, NULL, '', '', '恒运宏远', '', NULL, '货站自提', '漳州', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2086, 'DAQ', '大庆', NULL, NULL, '13555558361', '黑龙江省大庆市萨尔图区中九路南', 'DC自提', '雷润阳', NULL, 'DC自提', '大庆', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2087, 'FLE', '富拉尔基', NULL, NULL, '0452-687311118845200588', '黑龙江省齐齐哈尔市富拉尔基区龙瀚嘉园小区3号楼7号门市', '吉顺隆黑龙江线', '曹阳', NULL, '货站自提', '齐齐哈尔', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2088, 'HRB', '哈尔滨', NULL, NULL, '18686795700李秀荣', '哈尔滨市道外区先锋路先锋路18号新库房2号', 'DC自提', '李秀荣', NULL, 'DC自提', '哈尔滨', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2089, 'JMS', '佳木斯', NULL, NULL, '0', '黑龙江省佳木斯市新谊街千禧大厦西50米广州陶瓷', '吉顺隆黑龙江线', '韦林', NULL, '货站自提', '佳木斯', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2090, 'JXI', '鸡西', NULL, NULL, '18346707017', '园林小区2号楼15 16号门市科宝博洛尼整体家居', '吉顺隆黑龙江线', '于桐', NULL, '货站自提', '鸡西', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2091, 'MUD', '牡丹江', NULL, NULL, '15046332352', '牡丹江市西安区西一条路76号 七星街与景福街之间    博洛尼整体厨房', '吉顺隆黑龙江线', '孙继桓', NULL, '送货', '牡丹江', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2092, 'QQH', '齐齐哈尔', NULL, NULL, '18746255757', '齐齐哈尔市龙沙区龙沙小区36号楼', '吉顺隆黑龙江线', '王微', NULL, '送货', '齐齐哈尔', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2093, 'SHZ', '尚志', NULL, NULL, '15146897345', '黑龙江省尚志市', '吉顺隆黑龙江线', '王志强', NULL, '货站自提', '尚志', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2094, 'SYS', '双鸭山', NULL, NULL, '13895862555', '黑龙江双鸭山市鸿苑小区B座', '吉顺隆黑龙江线', '赵大强', NULL, '货站自提', '双鸭山', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2095, 'YCN', '伊春', NULL, NULL, '18645879791', '黑龙江省伊春市伊春区东步行街', '吉顺隆黑龙江线', '杨越', NULL, '送货', '伊春', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2096, 'SUIH', '绥化博洛尼', NULL, NULL, '133599100580455--8317777', '黑龙江省绥化市北林区信合小区西侧27号商服boloni', '吉顺隆黑龙江线', '赵盈盈', NULL, '送货', '绥化', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2097, 'SFH', '绥芬河', NULL, NULL, '15271617777132068657280453-3969953', '黑龙江绥芬河市长江路88号迎泽建材场', '吉顺隆黑龙江线', '王永海', NULL, '货站自提', '绥芬河', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2098, 'ZHD', '肇东', NULL, NULL, '13766939583', '哈尔滨货站自提', 'DC自提', '金杰', NULL, 'DC自提', '肇东', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2099, 'MUD.BLN', '牡丹江－森大木业', NULL, NULL, '15046332352', '牡丹江市西安区西一条路76号 七星街与景福街之间    博洛尼整体厨房', '吉顺隆黑龙江线', '孙继桓', NULL, '送货', '牡丹江', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2100, 'QQH.BLN', '齐齐哈尔-何大伟', NULL, NULL, '18746255757', '齐齐哈尔市龙沙区龙沙小区36号楼', '吉顺隆黑龙江线', '王薇', NULL, '送货', '齐齐哈尔', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2101, 'SHC', '双城－宋洁淼', NULL, NULL, '18746188188', '黑龙江省哈尔滨市博洛尼DC库', 'DC自提', '刘志忠', NULL, 'DC自提', '双城', '黑龙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2102, 'CZH', '潮州', NULL, NULL, '13652809112', '潮州市潮州大道博洛尼馆', '捷安物流', '徐丽华', NULL, '货站自提', '潮州', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2103, 'DGN.DL', '东莞市大朗', NULL, NULL, '13537506836', '深圳市罗湖区清水河金祥都市花园1栋201', '捷安物流', '张敏', NULL, '送货', '东莞', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2104, 'DGU', '东莞', NULL, NULL, '18664042661', '东莞市东城区主山小塘坣塘西街三巷7号', '捷安物流', '李韶华', NULL, '送货', '东莞', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2105, 'MMG', '茂名', NULL, NULL, '18826492587', '广东省茂名市电白区滨海大道99号（怡景湾对面）', '捷安物流', '黄志雄', NULL, '送货', '茂名', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2106, 'MMG.HY', '茂名海誉', NULL, NULL, '18312615573', '广东省茂名市电白区滨海大道99号（怡景湾对面）', '捷安物流', '颜兆木', NULL, '送货', '茂名', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2107, 'SHT.HX', '汕头汇轩', NULL, NULL, '13794121883', '广东省汕头市龙湖区内充公新安街二巷1号', 'DC自提', '赖安琪', NULL, 'DC自提', '汕头', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2108, 'ZQG', '肇庆', NULL, NULL, '13556504719', '广东省肇庆市古塔北路18号', '捷安物流', '陈伟研', NULL, '货站自提', '肇庆', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2109, 'ZQGL', '肇庆-林丽诗', NULL, NULL, '15089653128', '广东省肇庆市叠翠南路逸雅苑首层商铺至六之七', '捷安物流', '林丽诗', NULL, '货站自提', '肇庆', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2110, 'JEY', '揭阳', NULL, NULL, '159-9259-9972   13417154195', '揭阳市榕城区临江北路西创鸿公馆一层1至4号', 'DC自提', '陈蓉. 周树彬', NULL, 'DC自提', '揭阳', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2111, 'SEZ.DJD', '深圳多加多', NULL, NULL, '15012976661  13430848856', '深圳市南山区西丽大勘商业街268号三楼博洛尼家居', '捷安物流', '李光阳 张秋云', NULL, '送货', '深圳', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2112, 'GUZ', '广州分公司', NULL, NULL, '13424038877', '广州市海珠区新洲东路新洲大堤48号', '捷安物流', '邓旭昌', NULL, '送货', '广州', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2113, 'ZUH.XYD', '珠海新远大', NULL, NULL, '15916319356', '广东省珠海市前山三台石路家居世界城科宝博洛尼', '捷安物流', '夏生', NULL, '送货', '珠海', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2114, 'SEZ', '深圳博洛尼家居用品有限公司', NULL, NULL, '134189316060755-61193050', '深圳市南山区西丽镇塘朗工业B区54栋北座4楼', '捷安物流', '韦秋震', NULL, '货站自提', '深圳', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2115, 'ZJG', '湛江市', NULL, NULL, '13729111038', '广东省湛江市开发区明园路3号海滨御景豪庭5号商铺', '捷安物流', '程伟章', NULL, '送货', '湛江', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2116, 'MAM', '茂名市', NULL, NULL, '18312615573', '广东省茂名市电白区滨海大道99号（怡景湾对面）', '捷安物流', '颜兆木', NULL, '送货', '茂名', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2117, 'SEZ.LG', '龙岗', NULL, NULL, '13802552803', '深圳市龙岗区国安居家居建材3层311号', '捷安物流', '郭鹏', NULL, '货站自提', '龙岗', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2118, 'HUI', '惠州', NULL, NULL, '13539242045', '广东省惠州市惠城区演达二路万饰城A馆2楼', '捷安物流', '肖永铧', NULL, '送货', '惠州', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2119, 'SEZ.LH', '龙华', NULL, NULL, '13923475282', '广东省深圳市龙华新区清祥路1号宝能科技园乐康家居A3018', '捷安物流', '刘涛', NULL, '货站自提', '龙华', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2120, 'YDE', '英德', NULL, NULL, '18576323937', '广东省英德市碧峰华府南门66号', '捷安物流', '黄美琼', NULL, '货站自提', '英德', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2121, 'ZSH', '中山', NULL, NULL, '15800110666/13715590758', '广东省中山市西区街道木围仓街8号之四仓', '捷安物流', '杨艳/黄生', NULL, '送货', '中山', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2122, 'FSH', '佛山', NULL, NULL, '13682211440', '广东省佛山市禅城区朝安南路恒福新城首层p60 号', 'DC自提', '黄志林', NULL, 'DC自提', '佛山', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2123, 'YJG', '阳江', NULL, NULL, '13802679223', '阳江市西平北路上东新城49幢13、14号铺', '捷安物流', '梁宏展', NULL, '货站自提', '阳江', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2124, 'GUZ.PYNS', '番禺南沙', NULL, NULL, '13424038877', '广东省广州市番禺区石碁镇海涌路京珠新村二街33号', '捷安物流', '邓旭昌', NULL, '送货', '番禺', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2125, 'SEZ.NS', '南山', NULL, NULL, '13923475282', '广东省深圳市罗湖区太白路3033号君逸华府2栋30C', '捷安物流', '刘涛', NULL, '货站自提', '南山', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2126, 'SDE', '顺德', NULL, NULL, '', '', '捷安物流', '', NULL, '送货', '顺德', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2127, 'GUZ.BLN', '广州博洛尼', NULL, NULL, '18501119584', '广州市番禺区南村镇罗边村市新路段27号', '捷安物流', '尚大水', NULL, '货站自提', '广州', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2128, 'SDE.BLN', '顺德－李昭伟', NULL, NULL, '13420068267', '佛山市顺德区勒流街道连杜大道天任工业园区五号门', '捷安物流', '祝上键', NULL, '送货', '顺德', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2129, 'SEZ.BLN', '深圳－鹏博乐', NULL, NULL, '13823769761', '深圳', 'DC自提', '陈君', NULL, 'DC自提', '深圳', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2130, 'PUN', '普宁－科盛装饰', NULL, NULL, '13543963712', '广东省普宁市物流站点自提', '捷安物流', '张俊耀', NULL, '货站自提', '普宁', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2131, 'GUZ.KC', '广州科创', NULL, NULL, '13660423993', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '江慧红', NULL, '送货', '广州', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2132, 'GUZ.HCL', '广州-合创力', NULL, NULL, '13660423993', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '江慧红', NULL, '送货', '广州', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2133, 'DGA', '东莞-刘军伟', NULL, NULL, '15039946799', '东莞东城街道石井中心村3巷20号', '捷安物流', '夏德华', NULL, '送货', '东莞', '广东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2134, 'HAK.KD', '海口凯蒂', NULL, NULL, '0898-65315739', '海口好饰家家居文化广场', 'DC自提', '柳宏业', NULL, 'DC自提', '海口', '海南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2135, 'SYA', '三亚', NULL, NULL, '13697581822', '三亚市吉阳区迎宾路', '捷安物流', '贺术林', NULL, '送货', '三亚', '海南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2136, 'QHI', '琼海－王辉', NULL, NULL, '13580594054', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', '捷安物流', '李强辉', NULL, '送货', '琼海', '海南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2137, 'GUG', '贵港', NULL, NULL, '13471900444', '广西贵港市港北区观天下家居建材广场', '捷安物流', '陈景彤', NULL, '送货', '贵港', '广西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2138, 'BOT', '包头博各尼', NULL, NULL, '13384841184', '包头市友谊大街现代城54号底店', 'DC自提', '田旭东', NULL, 'DC自提', '包头', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2139, 'EDS', '鄂尔多斯', NULL, NULL, '15047745447', '内蒙古鄂尔多斯市东胜区天骄南路居然之家三楼南区1-3-070', 'DC自提', '巴图', NULL, 'DC自提', '鄂尔多斯', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2140, 'HUH.ZHW', '呼市众维', NULL, NULL, '13948434636  0471-2850243', '呼市海西路元各建材城A区81号', 'DC自提', '何浩', NULL, 'DC自提', '呼和浩特', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2141, 'HUH', '呼和浩特', NULL, NULL, '13948434636  0417-2850243', '呼市海西路元各建材城A区81号', 'DC自提', '何浩', NULL, 'DC自提', '呼和浩特', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2142, 'WHI', '乌海', NULL, NULL, '18247325252', '内蒙古乌海市海渤湾区恩河锦苑1期2单元201室', 'DC自提', '吕维', NULL, 'DC自提', '乌海', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2143, 'BYZ.BLN', '巴彦淖尔-高宇', NULL, NULL, '18647829717', '内蒙巴彦淖尔市临河区红星美凯龙东100米', 'DC自提', '任建国', NULL, 'DC自提', '巴彦淖尔', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2144, 'CHZ', '长治', NULL, NULL, '15235529091', '山西省长治市狮王瓷砖仓库', '金云山西线', '郭鑫', NULL, '送货', '长治', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2145, 'DTG.HM', '大同豪美', NULL, NULL, '18634522297 18634522291', '大同市御河南路11号', '金云山西线', '周树兰', NULL, '货站自提', '大同', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2146, 'JNZ', '晋中', NULL, NULL, '13068035450', '太原仓库', 'DC自提', '张艳萍', NULL, 'DC自提', '晋中', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2147, 'JUC', '晋城', NULL, NULL, '15303563990', '山西省晋城市居然之家', '金云山西线', '程海云', NULL, '货站自提', '晋城', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2148, 'LFN.FH', '临汾丰华', NULL, NULL, '13233119835', '临汾货站自提', '金云山西线', '秦李元', NULL, '货站自提', '临汾', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2149, 'TAY.MH', '太原明慧', NULL, NULL, '18534571188', '太原市万柏林区和平南路光华街43号', 'DC自提', '孙伟', NULL, 'DC自提', '太原', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2150, 'YUNC', '运城', NULL, NULL, '13133393099', '山西省运城市盐湖区禹都经历开发区居然之家四层', '金云山西线', '禹强', NULL, '货站自提', '运城', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2151, 'SZH', '朔州', NULL, NULL, '13911894584', '山西省朔州市佳禾枫景小区38号楼1门', '金云山西线', '武峰', NULL, '货站自提', '朔州', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2152, 'JCE', '晋城博洛尼', NULL, NULL, '18235627739', '山西省晋城市城区东田石村', '金云山西线', '牛向南', NULL, '货站自提', '晋城', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2153, 'LUL', '吕梁', NULL, NULL, '13485426946', '山西省吕梁市居然之家负一层博洛尼', '金云山西线', '高文浩', NULL, '货站自提', '吕梁', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2154, 'XIZ', '忻州', NULL, NULL, '18835009678', '山西省忻州市新欣三利建材城2楼', '金云山西线', '王浩丞', NULL, '货站自提', '忻州', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2155, 'WNX', '闻喜', NULL, NULL, '', '', '金云山西线', '', NULL, '送货', '闻喜', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2156, 'XAY', '孝义', NULL, NULL, '15803589635', '山西省孝义市府前街美居家园一层109号', '金云山西线', '高龙龙', NULL, '货站自提', '孝义', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2157, 'JXU', '介休', NULL, NULL, '15235852305', '太原DC库', 'DC自提', '鲍经理', NULL, 'DC自提', '介休', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2158, 'HAR', '怀仁', NULL, NULL, '', '', '金云山西线', '', NULL, '送货', '怀仁', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2159, 'HOZ', '霍州', NULL, NULL, '13753587406', '山西省霍州市桥西街漪汾路天主大教堂北50米', '金云山西线', '刘伟栋', NULL, '货站自提', '霍州', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2160, 'HMA', '侯马', NULL, NULL, '18935043266', '山西省侯马市文明路684号建邦向阳苑商铺9号14宅', '金云山西线', '牛丰刚', NULL, '送货', '侯马', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2161, 'XAY.BLN', '孝义-张文伶', NULL, NULL, '15803589635', '山西省孝义市府前街美居家园一层109号', '金云山西线', '高龙龙', NULL, '货站自提', '孝义', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2162, 'HOZ.BLN', '霍州-李素兰', NULL, NULL, '', '', '金云山西线', '', NULL, '货站自提', '霍州', '山西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2163, 'CZH.BN', '常州宝尼', NULL, NULL, '0519-88888608', '江苏省常州市天宁区青龙西路宏胜停车场内', 'DC自提', '薛栋', NULL, 'DC自提', '常州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2164, 'DAF', '大丰', NULL, NULL, '15151088446', '江苏省大丰市明星国际家居城A幢A2010室', '灵鹤江苏线', '陈金晶', NULL, '货站自提', '大丰', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2165, 'DTI', '东台', NULL, NULL, '13962099918', '江苏省东台市明星国际家具城', '灵鹤江苏线', '王志松', NULL, '货站自提', '东台', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2166, 'DYG.HB', '丹阳博洛尼', NULL, NULL, '15050870607', '江苏省丹阳市开发区迎宾大道148号', '灵鹤江苏线', '彭晨', NULL, '货站自提', '丹阳', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2167, 'HAN', '淮安', NULL, NULL, '13615143707', '江苏省淮安市开发区西安路18号—10福星建材有限公司', '灵鹤江苏线', '毛耀', NULL, '货站自提', '淮安', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2168, 'HMN', '海门', NULL, NULL, '13801467478', '江苏省海门市金牛广场建材馆A-28号', '灵鹤江苏线', '顾红星', NULL, '货站自提', '海门', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2169, 'JDU', '江都', NULL, NULL, '13196457035', '江苏省江都市江都润家国际装饰城二层科宝博洛尼', '灵鹤江苏线', '竺厚宝', NULL, '送货', '江都', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2170, 'JNJ', '靖江', NULL, NULL, '', '', 'DC自提', '', NULL, 'DC自提', '靖江', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2171, 'JYAN', '姜堰', NULL, NULL, '18952617798', '', '灵鹤江苏线', '何语林', NULL, '货站自提', '姜堰', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2172, 'JYN', '江阴', NULL, NULL, '18001520087', '江苏省江阴市夏东路5号博洛尼仓库', '灵鹤江苏线', '王鼎', NULL, '送货', '江阴', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2173, 'LIY', '溧阳', NULL, NULL, '15295196777', '江苏省溧阳市歌岐路55号永恒汽修厂内', '灵鹤江苏线', '陆春华', NULL, '送货', '溧阳', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2174, 'LYG', '连云港', NULL, NULL, '13675206678', '江苏省连云港市解放东路（丁字路西）浦东钢材城B区11号宝莱公司', 'DC自提', '孙滔', NULL, 'DC自提', '连云港', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2175, 'NTG.GT', '南通谷泰', NULL, NULL, '13776919607', '江苏省南通市钟秀路108号汇隆新城B2座504室', 'DC自提', '冒洁', NULL, 'DC自提', '南通', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2176, 'QID', '启东', NULL, NULL, '13606285833', '江苏省启东市台湾装饰街217-219号', 'DC自提', '陈丹', NULL, 'DC自提', '启东', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2177, 'SUZ', '苏州', NULL, NULL, '15862527187', '江苏省苏州市工业园区唐庄路88号', '灵鹤江苏线', '吴进军', NULL, '送货', '苏州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2178, 'TZO', '泰州', NULL, NULL, '17315667888', '江苏省泰州市海陵区双墩村', '灵鹤江苏线', '陈青松', NULL, '货站自提', '泰州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2179, 'XHA', '兴化', NULL, NULL, '13775748018', '江苏省兴化市江浙商业广场A区临街A－020－021', '灵鹤江苏线', '卢正华', NULL, '送货', '兴化', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2180, 'XYI', '新沂市', NULL, NULL, '13815333003，0516－88839669', '江苏省新沂市国际商贸城三期A栋A-1', '灵鹤江苏线', '陈北京', NULL, '货站自提', '新沂', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2181, 'XZH', '徐州', NULL, NULL, '18914807002', '江苏省徐州市云龙区升辉装饰城二层博洛尼橱柜', 'DC自提', '刘超峰', NULL, 'DC自提', '徐州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2182, 'YAC.XM', '盐城新茂', NULL, NULL, '15366553737', '江苏省盐城市亭湖区开放大道南路113号盐阜板业内', '灵鹤江苏线', '宋志英', NULL, '送货', '盐城', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2183, 'YAZ', '扬中', NULL, NULL, '13952902975', '江苏省扬中市明珠大道29-1629-17号', 'DC自提', '吴义昔', NULL, 'DC自提', '扬中', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2184, 'YIX', '宜兴', NULL, NULL, '13327928988', '江苏省宜兴市宜城街道阳泉西路188号红星美凯龙', '灵鹤江苏线', '陈小良', NULL, '送货', '宜兴', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2185, 'ZHJ', '镇江', NULL, NULL, '13914554398', '江苏省镇江市丁卯谷阳路盛飞仓储', 'DC自提', '闫鲁娜', NULL, 'DC自提', '镇江', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2186, 'ZJG.JCH', '张家港金城', NULL, NULL, '13773279307/051256991632', '江苏省张家港市塘市西塘公路南侧 亿顺电动科技车间', '灵鹤江苏线', '何晓燕', NULL, '送货', '张家港', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2187, 'SUQ', '宿迁', NULL, NULL, '15261237518', '江苏省宿迁市义务国际商贸城精品街商场30021、30022', '灵鹤江苏线', '王克森', NULL, '送货', '宿迁', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2188, 'RUG', '如皋', NULL, NULL, '13584633433', '江苏省如皋市益寿南路89号', '灵鹤江苏线', '李秀萍', NULL, '送货', '如皋', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2189, 'PIZ', '邳州', NULL, NULL, '13852238958', '江苏省邳州市5公里飞龙酒店北侧永兴汽贸3楼博洛尼体验馆', '灵鹤江苏线', '宋禹', NULL, '送货', '邳州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2190, 'NKG', '南京博洛尼', NULL, NULL, '13770608781，025-86433440', '江苏省南京市高桥门十字河路128号', '灵鹤江苏线', '张正国', NULL, '送货', '南京', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2191, 'JTA', '金坛', NULL, NULL, '13813517588', '江苏省金坛市丹阳门装饰材料市场春风西路1--21', '灵鹤江苏线', '姚渊', NULL, '送货', '金坛', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2192, 'YAG', '扬州博洛尼', NULL, NULL, '13358121661', '江苏省扬州市邗江区开发西路234号长宏铝业东北角', '灵鹤江苏线', '郭倩瑶', NULL, '送货', '扬州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2193, 'WXI', '无锡博洛尼失效', NULL, NULL, '18751553877', '无锡市，锡山区，东亭镇，团结路41号林芝山阳厂旁', '灵鹤江苏线', '曾华锋', NULL, '货站自提', '无锡', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2194, 'JIH', '建湖县', NULL, NULL, '13815558970', '江苏省建湖县五洲国际商贸城1号楼1栋欧神诺瓷砖', '灵鹤江苏线', '刘俊荣', NULL, '送货', '建湖', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2195, 'WUX', '无锡博洛尼', NULL, NULL, '15862527187', '江苏省苏州市工业园区唐庄路88号', '灵鹤江苏线', '吴进军', NULL, '送货', '无锡', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2196, 'FEX', '丰县', NULL, NULL, '18914807002', '江苏省丰县御景园第S1幢1单元121-122号', 'DC自提', '刘超峰', NULL, 'DC自提', '丰县', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2197, 'SQN', '宿迁博洛尼', NULL, NULL, '15261237518', '江苏省宿迁市义务国际商贸城精品街商场30021、30022', '灵鹤江苏线', '王克森', NULL, '送货', '宿迁', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2198, 'KSH', '昆山', NULL, NULL, '15862527187', '江苏省苏州市工业园区唐庄路88号', '灵鹤江苏线', '吴进军', NULL, '送货', '昆山', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2199, 'HIA', '海安', NULL, NULL, '18205056086', '江苏省海安县黄海大道西86号怡景湾家居广场一楼博洛尼', '灵鹤江苏线', '陆雯遐', NULL, '送货', '海安', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2200, 'WUJ', '吴江', NULL, NULL, '15862527187', '江苏省苏州市工业园区唐庄路88号', '灵鹤江苏线', '吴进军', NULL, '送货', '吴江', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2201, 'JYA', '姜堰博洛尼', NULL, NULL, '18952617798', '江苏省泰州市姜堰区人民路301号与中兴路交汇处', '灵鹤江苏线', '何语林', NULL, '送货', '姜堰', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2202, 'PIX', '沛县', NULL, NULL, '', '', '', '', NULL, '', '沛县', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2203, 'CZH.BLN', '常州博洛尼', NULL, NULL, '0519-88888608', '江苏省常州市天宁区青龙街道亚新村委宏胜停车场内', 'DC自提', '薛栋', NULL, 'DC自提', '常州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2204, 'GCH', '高淳', NULL, NULL, '18651868656', '江苏省南京市高淳区北岭路88-19号', '灵鹤江苏线', '夏崇龙', NULL, '货站自提', '高淳', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2205, 'JNJ.BLN', '靖江博洛尼', NULL, NULL, '18001520087', '江苏省江阴市夏东路5号博洛尼仓库', '灵鹤江苏线', '王鼎', NULL, '货站自提', '江阴', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2206, 'JUR', '句容', NULL, NULL, '13501006369', '江苏省句容市宝华开发区80号青松物流院内', 'DC自提', '胡大祥', NULL, 'DC自提', '句容', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2207, 'GYU', '赣榆', NULL, NULL, '', '', '灵鹤江苏线', '', NULL, '货站自提', '赣榆', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2208, 'TAC', '太仓', NULL, NULL, '18121551982', '江苏省太仓市城厢镇太丰社区花墙工业小区仓储', '灵鹤江苏线', '于河泳', NULL, '送货', '太仓', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2209, 'LIY.BLN', '溧阳－宏洲建材', NULL, NULL, '15295196777', '江苏省常州市溧阳市溧城镇兴业路3号 （尚客优快捷酒店对面）', '灵鹤江苏线', '陆春华', NULL, '送货', '溧阳', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2210, 'RUD', '如东－顾杰', NULL, NULL, '18994206789', '江苏省如东县外环东路优美佳建材城 博洛尼仓库', '灵鹤江苏线', '林杏', NULL, '送货', '如东', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2211, 'HAM', '海门－博洛尼', NULL, NULL, '13814645088', '江苏省海门市解放东路金牛建材馆二楼A-28博洛尼店', '灵鹤江苏线', '陈东平', NULL, '货站自提', '海门', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2212, 'XYI.BLN', '新沂－康维装饰', NULL, NULL, '13815333003，0516－88839669', '江苏省新沂市国际商贸城三期A栋A-1', '灵鹤江苏线', '陈北京', NULL, '货站自提', '新沂', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2213, 'XZH.BLN', '徐州-刘超峰', NULL, NULL, '18914807002', '江苏省丰县御景园第S1幢1单元121-122号', 'DC自提', '刘超峰', NULL, 'DC自提', '徐州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2214, 'LSU', '溧水-蒋学飞', NULL, NULL, '13912999999', '江苏溧水', 'DC自提', '蒋学飞', NULL, 'DC自提', '溧水', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2215, 'LYG.BLN', '连云港-程洋', NULL, NULL, '13905132826', '江苏省连云港市海州区兴隆装饰城2号路4楼博洛尼整体厨房', '灵鹤江苏线', '程洋', NULL, '货站自提', '连云港', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2216, 'TZO.BLN', '泰州-陈青松', NULL, NULL, '17315667888', '江苏省泰州市海陵区双墩村', '灵鹤江苏线', '陈青松', NULL, '货站自提', '泰州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2217, 'DAF.BLN', '大丰-陈金晶', NULL, NULL, '15151088446', '江苏省大丰市明星国际家居城A幢A2010室', '灵鹤江苏线', '陈金晶', NULL, '货站自提', '大丰', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2218, 'WUJ', '吴江', NULL, NULL, '15862527187', '江苏省苏州市工业园区唐庄路88号', '', '吴进军', NULL, '', '苏州', '江苏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2219, 'DLI.DXS', '大理德鑫盛', NULL, NULL, '13988558799', '大理市下关镇建设东路100号', '高快物流', '李绪川', NULL, '货站自提', '大理', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2220, 'KMG', '昆明', NULL, NULL, '13708497779', '昆明市五华区核桃箐路13号', '高快物流', '麦江', NULL, '送货', '昆明', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2221, 'QUJ', '曲靖', NULL, NULL, '15287834299', '曲靖红星美凯龙三楼', '高快物流', '钱宝兴', NULL, '货站自提', '曲靖', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2222, 'TCG', '腾冲', NULL, NULL, '0875－513798813187512299', '腾冲镇天城社区 叠园小区56号', '高快物流', '寸玉', NULL, '货站自提', '腾冲', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2223, 'YUX.YHX', '玉溪玉洪轩', NULL, NULL, '0877-202897813608772200', '云南省玉溪市红塔区珊瑚路8-4号', '高快物流', '李文兰', NULL, '货站自提', '玉溪', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2224, 'BOS', '保山', NULL, NULL, '157 7034 7827', '云南省保山市隆阳区海棠路奥新三期乐佳装饰有限公司', '高快物流', '杨有跃', NULL, '货站自提', '保山', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2225, 'PUE', '普洱', NULL, NULL, '137 5900 1205', '云南省普洱市大昆曼建材家具城建材区10幢1-2号', '高快物流', '蓝总', NULL, '货站自提', '普洱', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2226, 'XWI', '宣威', NULL, NULL, '', '', '高快物流', '', NULL, '送货', '宣威', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2227, 'JHO', '景洪', NULL, NULL, '183-0889-0723', '云南省景洪市西双版纳旅游度假区国际家居商场16柜7-8号', '高快物流', '先路', NULL, '货站自提', '景洪', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2228, 'MLA', '勐腊', NULL, NULL, '18869105666', '云南省西双版纳州勐腊县森翔家居商贸城1-1-3', '高快物流', '李鹏', NULL, '货站自提', '勐腊', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2229, 'MZI', '蒙自', NULL, NULL, '18287367296', '蒙自滇南大商汇J16-17', '高快物流', '余坦橙', NULL, '货站自提', '蒙自', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2230, 'ZHT', '昭通', NULL, NULL, '18388096986或者13108512199', '云南省昭通市昭阳区中汇建材馆A108', '高快物流', '刘师', NULL, '货站自提', '昭通', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2231, 'JHO.BLN', '景洪-王龙', NULL, NULL, '183-0889-0723', '云南省景洪市西双版纳旅游度假区国际家居商场16柜7-8号', '高快物流', '先路', NULL, '货站自提', '景洪', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2232, 'WEX', '威信-胡光位', NULL, NULL, '', '', '高快物流', '', NULL, '货站自提', '威信', '云南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2233, 'ANL', '安陆', NULL, NULL, '18607296281', '湖北省安陆市碧涡路148号', 'DC自提', '雷茉英', NULL, 'DC自提', '安陆', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2234, 'HSH', '黄石', NULL, NULL, '13886450098', '红星美凯龙三楼中厅 博洛尼', '君意通物流', '张振钢', NULL, '货站自提', '黄石', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2235, 'HUG', '黄冈', NULL, NULL, '13477660433', '黄冈市黄州区中环路博洛尼店（九环假日酒店对面）', '君意通物流', '范翔', NULL, '货站自提', '黄冈', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2236, 'JZO', '荆州', NULL, NULL, '17763099588', '湖北省荆州市318国道银湖时代工业园', '君意通物流', '唐亚强', NULL, '送货', '荆州', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2237, 'MAC', '麻城', NULL, NULL, '13469948459', '湖北省麻城市工业路66号', '君意通物流', '冯旗', NULL, '货站自提', '麻城', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2238, 'SHY', '十堰', NULL, NULL, '13477991575', '十堰北京路柳林春晓居然之家3层1015号', '君意通物流', '王丽娜', NULL, '货站自提', '十堰', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2239, 'TIM', '天门', NULL, NULL, '18972189376', '湖北省天门市官路家居广场1楼A8  科宝博洛尼整体厨房', '君意通物流', '李经理', NULL, '货站自提', '天门', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2240, 'WUH', '武汉', NULL, NULL, '13545655542', '武汉市洪山区东湖风景区落雁路红大新木门厂院内', '君意通物流', '张松华', NULL, '送货', '武汉', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2241, 'XFA', '襄樊', NULL, NULL, '18372287379', '湖北襄樊市前进路9号天丽国际家居建材博览中心5层A31-33', '君意通物流', '马爱国', NULL, '货站自提', '襄樊', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2242, 'XGN', '孝感', NULL, NULL, '0712-2466669，13545461102/13477715988', '湖北京省孝感市万佳时尚装饰广场', 'DC自提', '李兵李么子', NULL, '送货', '孝感', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2243, 'XTO', '仙桃', NULL, NULL, '135 4598 3698', '湖北省仙桃市德政园北门（市疾控中心对面）博洛尼整体家居', '君意通物流', '许师傅', NULL, '货站自提', '仙桃', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2244, 'YCH', '宜昌', NULL, NULL, '13227263266', '宜昌市城东大道东山花园底商', '君意通物流', '向陈', NULL, '货站自提', '宜昌', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2245, 'LAC', '利川', NULL, NULL, '18883258300', '湖北省利川市土产小区18栋1层', '君意通物流', '秦娟', NULL, '货站自提', '利川', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2246, 'DYE', '大冶', NULL, NULL, '15997122949', '湖北省大冶市罗家桥大道欧蓓莎国际商城B馆二层博洛尼体验店', '君意通物流', '石敬', NULL, '货站自提', '大冶', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2247, 'JMN', '荆门', NULL, NULL, '13607266660', '湖北省荆门市东宝区建设街30', '君意通物流', '孟凡城', NULL, '货站自提', '荆门', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2248, 'JRFZ', '景润丰泽园林设计（北京）有限公司', NULL, NULL, '18607296281', '湖北省安陆市碧涡路148号', 'DC自提', '雷茉英', NULL, 'DC自提', '安陆', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2249, 'ZHX', '钟祥', NULL, NULL, '', '', '君意通物流', '', NULL, '送货', '钟祥', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2250, 'JLI', '监利', NULL, NULL, '', '', '君意通物流', '', NULL, '送货', '监利', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2251, 'XUC', '宣城', NULL, NULL, '18607296281', '湖北省安陆市碧涡路148号', 'DC自提', '雷茉英', NULL, 'DC自提', '宣城', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2252, 'JAN', '吉安', NULL, NULL, '18607296281', '湖北省安陆市碧涡路148号', 'DC自提', '雷茉英', NULL, 'DC自提', '吉安', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2253, 'ESH', '恩施', NULL, NULL, '15335815553', '湖北省恩施市金桂大道红星美凯龙', '君意通物流', '商守超', NULL, '货站自提', '恩施', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2254, 'XNI', '咸宁', NULL, NULL, '13807248837', '湖北省咸宁市咸安区贺胜路天成家居一期二楼博洛尼', 'DC自提', '刘文浩', NULL, 'DC自提', '咸宁', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2255, 'SIZ', '随州', NULL, NULL, '13986447438', '湖北省随州市迎宾大道与季梁大道交汇口红星国际广场大一层6栋18—25号', 'DC自提', '颜漫', NULL, '货站自提', '随州', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2256, 'JLI.BLN', '监利博洛尼', NULL, NULL, '18609179688', '宝鸡市金台区东风路国艺家居建材城二楼CG-12', '君意通物流', '赵春强', NULL, '货站自提', '监利', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2257, 'QIJ', '潜江－黄向阳', NULL, NULL, '13617295999', '湖北省潜江市光彩建材城二期一栋', '君意通物流', '黄向阳', NULL, '货站自提', '潜江', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2258, 'HHC.BLN', '汉川-刘小明', NULL, NULL, '17771201966', '湖北省,孝感市,汉川市欧亚达仙女大道469-31号', 'DC自提', '刘小明', NULL, 'DC自提', '汉川', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2259, 'GUY', '贵阳', NULL, NULL, '13658513327', '贵阳市南明区致富路科飞物流园', '晨速物流', '陈功', NULL, '货站自提', '贵阳', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2260, 'LPS', '六盘水', NULL, NULL, '13885861730', '六盘水市恒维装饰精品城H1-203', '晨速物流', '杨小波', NULL, '货站自提', '六盘水', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2261, 'ZUY', '遵义', NULL, NULL, '18285288837   0851-----27622262', '贵州省遵义市红花岗区中华路浩鑫商贸城5栋2-6号', '晨速物流', '廖孝酬', NULL, '货站自提', '遵义', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2262, 'BIJ', '毕节', NULL, NULL, '18212590491', '贵州省毕节市居然之家1栋3-045号', '大田物流', '李孟飞', NULL, '货站自提', '毕节', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2263, 'XIY', '兴义', NULL, NULL, '18785968258', '贵州省兴义市金州体育城D栋2单元3号', '大田物流', '陶黎', NULL, '货站自提', '兴义', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2264, 'KLI', '凯里', NULL, NULL, '13398551661', '贵州省凯里市居然之家', '晨速物流', '杨亮', NULL, '货站自提', '凯里', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2265, 'QXI', '黔西', NULL, NULL, '18608572835', '贵阳站点自提', '晨速物流', '吴闻', NULL, '货站自提', '黔西', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2266, 'DYU', '都匀', NULL, NULL, '18198658777', '贵州省都匀市经济开发区', '大田物流', '孟达', NULL, '货站自提', '都匀', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2267, 'ZAN', '正安', NULL, NULL, '', '', '晨速物流', '', NULL, '货站自提', '正安', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2268, 'ASH', '安顺', NULL, NULL, '15805836557', '贵州省安顺市西秀区龙青路尚东逸品boloni体验店', '大田物流', '吴超', NULL, '货站自提', '安顺', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2269, 'WAN', '瓮安', NULL, NULL, '18084499315', '贵州省瓮安县麒龙摩尔城A4栋1-4号门面', '晨速物流', '雷兆骥', NULL, '货站自提', '瓮安', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2270, 'REH.BLN', '仁怀博洛尼', NULL, NULL, '18685299888', '贵州省仁怀市国酒大道109号', '大田物流', '曹义', NULL, '货站自提', '仁怀', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2271, 'MET', '湄潭', NULL, NULL, '18685255777', '贵州省遵义市湄潭县茶城大道太子湾建材市场负二层166-167', '大田物流', '彭成豪', NULL, '货站自提', '湄潭', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2272, 'TRE', '铜仁－吴晓锋', NULL, NULL, '18985339577', '贵州省铜仁市万山区西南建材市场13栋1-5号', 'DC自提', '吴晓锋', NULL, 'DC自提', '铜仁', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2273, 'XIS.BLN', '习水－冯秀国', NULL, NULL, '13688529107', '习水站点自提', '晨速物流', '杨丽娜', NULL, '货站自提', '习水', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2274, 'TRE.BLN', '铜仁-博洛尼', NULL, NULL, '18985339577', '贵州省铜仁市松桃苗族自治县甘龙镇英庄村吴溪组', 'DC自提', '吴晓锋', NULL, 'DC自提', '铜仁', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2275, 'BIJ.BLN', '毕节-罗运光', NULL, NULL, '18212590491', '贵州省毕节市居然之家1栋3-045号', '大田物流', '李孟飞', NULL, '货站自提', '毕节', '贵州', NULL);
INSERT INTO `wms_cost_agent` VALUES (2276, 'BAZ', '巴中1', NULL, NULL, '13882411397', '巴中 新时代小区5号楼科宝橱柜', '大田物流', '邓兵益', NULL, '货站自提', '巴中', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2277, 'CDU', '成都', NULL, NULL, '13088029955', '成都市簇桥乡三河村三组302号', 'DC自提', '杨勇', NULL, 'DC自提', '成都', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2278, 'DEY', '德阳', NULL, NULL, '18608116811', '四川省德阳市旌阳区居然之家1号中庭1-3-003', '大田物流', '王洁琼', NULL, '货站自提', '德阳', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2279, 'DJY', '都江堰', NULL, NULL, '18683578377', '成都货站自提', '大田物流', '蔡学斌', NULL, '货站自提', '都江堰', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2280, 'MYA.CHQ', '绵阳城区', NULL, NULL, '18990109728/0816 2302898', '四川绵阳涪城区安昌西路金阳公寓二单元四楼', '盛世前程物流', '任蓉', NULL, '货站自提', '绵阳', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2281, 'NAC', '南充', NULL, NULL, '13096147799', '南充市高坪区红星美凯龙', '盛世前程物流', '吕东', NULL, '货站自提', '南充', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2282, 'PZH', '攀枝花', NULL, NULL, '0812--2613777  18982313377', '攀枝花市美华龙装饰家居广场一楼93号', '大田物流', '刘大革', NULL, '货站自提', '攀枝花', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2283, 'XIC', '西昌', NULL, NULL, '15181524099', '四川省西昌市航天大道美地亚建材城二楼十一号', '大田物流', '郑杰', NULL, '送货', '西昌', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2284, 'YIB', '宜宾', NULL, NULL, '0831-2380989    18989207789', '四川省宜宾市南岸西区海韵家美建材城三楼二号', '大田物流', '徐园惠', NULL, '货站自提', '宜宾', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2285, 'ZGO', '自贡', NULL, NULL, '13778571911/08138285967', '四川省自贡市马吃水川南建材市场博洛尼整体厨房', '盛世前程物流', '老杨', NULL, '货站自提', '自贡', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2286, 'MSH', '眉山', NULL, NULL, '18980364871', '四川省眉山市东坡区义乌商贸城2A A1号', '大田物流', '姚心怡', NULL, '货站自提', '眉山', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2287, 'SNG', '遂宁', NULL, NULL, '18096326399   座机：0825-2625699', '四川省遂宁市船山区开善路293号永逸家居一楼科宝博洛尼', '大田物流', '文小庆', NULL, '货站自提', '遂宁', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2288, 'LSH', '乐山市', NULL, NULL, '(028-8501932081296863)15928139364', '成都市武侯区簇桥沈家桥村二组407号', 'DC自提', '肖华均', NULL, 'DC自提', '乐山', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2289, 'LZS', '泸州市', NULL, NULL, '17781876899', '四川省泸州市龙马潭区西南商贸城（兴业银行旁） 646000', '大田物流', '梁春', NULL, '货站自提', '泸州', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2290, 'YBN', '宜宾1', NULL, NULL, '13649031170', '四川省宜宾市南岸西区海韵家美建材城三楼二号', '大田物流', '吴勇刚', NULL, '货站自提', '宜宾', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2291, 'NEJ', '内江博洛尼', NULL, NULL, '13551538168', '四川省内江市红星美凯龙', '盛世前程物流', '罗良海', NULL, '货站自提', '内江', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2292, 'CDDJS', '成都大觉寺', NULL, NULL, '(028-8501932081296863)15928139364', '成都市武侯区簇桥 文盛路5号', 'DC自提', '肖华均', NULL, 'DC自提', '成都', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2293, 'GUA', '广安', NULL, NULL, '13668028054', '四川省广安市华美立家广场', '大田物流', '苏斌', NULL, '货站自提', '广安', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2294, 'MES', '眉山博洛尼', NULL, NULL, '18980364871', '四川省眉山市东坡区义乌商贸城2A A1号', '大田物流', '姚心怡', NULL, '货站自提', '眉山', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2295, 'QIL', '邛崃', NULL, NULL, '', '', '大田物流', '', NULL, '送货', '邛崃', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2296, 'PEZ', '彭州', NULL, NULL, '', '', '大田物流', '', NULL, '送货', '彭州', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2297, 'DJY.BLN', '都江堰博洛尼', NULL, NULL, '', '', '大田物流', '', NULL, '送货', '都江堰', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2298, 'GAY', '广元', NULL, NULL, '13881281339', '四川省广元市利州区国贸广场居然之家博洛尼体验店', '大田物流', '王华丽', NULL, '货站自提', '广元', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2299, 'LZS.BLN', '泸州博洛尼', NULL, NULL, '17781876899', '四川省泸州市龙马潭区蜀泸大道西南商贸城9区2楼27-33号（西南医科大学对面）', '大田物流', '梁春（成都DC）', NULL, '货站自提', '泸州', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2300, 'GYA', '广元-华尔美', NULL, NULL, '13881281339', '四川省广元市利州区国贸广场居然之家博洛尼体验店', '大田物流', '王华丽', NULL, '货站自提', '广元', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2301, 'GUH', '广汉-蔡春', NULL, NULL, '', '', '大田物流', '', NULL, '货站自提', '广汉', '四川', NULL);
INSERT INTO `wms_cost_agent` VALUES (2302, 'CHQ.BLN', '重庆宝洛尼', NULL, NULL, '133 3025 0592‬（送货前一定要提前联系好）', '重庆市南岸区鸡冠石镇和平村新湾社一号库房第二层', '晨速物流', '曾旭刚', NULL, '送货', '重庆', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2303, 'CHS', '长寿', NULL, NULL, '13983967235/15823181447/13983967235', '重庆市长寿区桃花大道9号3-1-1', '晨速物流', '张华英', NULL, '货站自提', '长寿', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2304, 'RCH', '荣昌', NULL, NULL, '13500347400', '重庆荣昌县昌元滨河西里90号12栋2单元19-6', '晨速物流', '李先海', NULL, '货站自提', '荣昌', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2305, 'CHQ.BSH', '璧山', NULL, NULL, '18523418500', '重庆市璧山区大宏鼎家居建材城2层', '晨速物流', '李润梅', NULL, '货站自提', '璧山', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2306, 'CHQ.YCH', '永川', NULL, NULL, '13330334133', '重庆市永川区汇龙大道腾龙装饰城一楼32号', '晨速物流', '张林城', NULL, '货站自提', '永川', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2307, 'CHQ.WZH', '万州', NULL, NULL, '17318286510', '重庆市万州区中天广场五楼博洛尼', '大田物流', '李晋宇', NULL, '货站自提', '万州', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2308, 'CQI', '重庆博洛尼', NULL, NULL, '133 3025 0592‬（送货前一定要提前联系好）', '重庆市南岸区鸡冠石镇和平村新湾社一号库房第二层', '晨速物流', '曾旭刚', NULL, '送货', '重庆', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2309, 'CHQ.JAJ', '江津-李国', NULL, NULL, '18883297898', '重庆市江津明月香山国际建材城', '晨速物流', '李国', NULL, '货站自提', '江津', '重庆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2310, 'LAS', '拉萨-蔡玉红', NULL, NULL, '13618983588', '西藏拉萨市经济开发区B区拉萨建材交易中心25号', '大田物流', '叶瑞锋', NULL, '送货', '拉萨', '西藏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2311, 'DLN', '大连市', NULL, NULL, '13889406632', '辽宁省大连市甘井子区西北路872号', '吉顺隆辽宁线', '尹贻军', NULL, '送货', '大连', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2312, 'ANS', '鞍山', NULL, NULL, '13504125093', '辽宁省鞍山市铁东区巴黎花园南门博洛尼家居体验馆', '吉顺隆辽宁线', '张晓倩', NULL, '货站自提', '鞍山', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2313, 'DAN', '丹东', NULL, NULL, '13941555169', '丹东市元宝区朝凤大街8号', '吉顺隆辽宁线', '谷洁', NULL, '货站自提', '丹东', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2314, 'DBS', '调兵山', NULL, NULL, '13384209666', '辽宁省铁岭市柴河街南段盛峰嘉苑A区3号楼10号门市电话:13384209666', '吉顺隆辽宁线', '尚巍', NULL, '货站自提', '调兵山', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2315, 'FSU', '抚顺', NULL, NULL, '13488845257', '辽宁省抚顺市顺城区龚家村', '吉顺隆辽宁线', '关威', NULL, '送货', '抚顺', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2316, 'FXB', '阜新', NULL, NULL, '18941805858', '辽宁省阜新市海州区滨河路11号滨河大厦', '吉顺隆辽宁线', '', NULL, '货站自提', '阜新', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2317, 'HIC', '海城', NULL, NULL, '138897793070412-5573322转86', '辽宁省海城市中街普金园西六', '吉顺隆辽宁线', '江国军', NULL, '货站自提', '海城', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2318, 'HLD', '葫芦岛', NULL, NULL, '1389780571115566687886 ，0429-2293229', '葫芦岛市龙港区锦葫路中段（阳光家居西行100米）', 'DC自提', '汪赢', NULL, 'DC自提', '葫芦岛', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2319, 'JZH.JD', '锦州精典', NULL, NULL, '15174240702', '锦州市太和区凌南东里宝地城C区42-33、42-34号', '吉顺隆辽宁线', '郭兰玲', NULL, '货站自提', '锦州', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2320, 'LYA', '辽阳', NULL, NULL, '13384190037', '辽宁省辽阳市 文圣路131号-1', 'DC自提', '于孝心', NULL, 'DC自提', '辽阳', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2321, 'PJN', '盘锦', NULL, NULL, '13065267766', '盘锦市兴隆台区乔家汇美家居广场圣象地板收', '吉顺隆辽宁线', '赵媛13065267766', NULL, '货站自提', '盘锦', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2322, 'SYG', '沈阳', NULL, NULL, '13079772915', '中国,辽宁省,沈阳市,浑南区营城子大街9号南大甸子社区', 'DC自提', '迟爱群', NULL, 'DC自提', '沈阳', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2323, 'TEL', '铁岭', NULL, NULL, '15841024494', '辽宁省铁岭市柴河街南段盛峰嘉苑A区3号楼10号门市电话:13384209666', '吉顺隆辽宁线', '张彦辉', NULL, '货站自提', '铁岭', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2324, 'YKO', '营口', NULL, NULL, '13030796777', '辽宁省营口市光华路北八号甲11号门市', '吉顺隆辽宁线', '刘云波', NULL, '货站自提', '营口', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2325, 'JZH', '锦州', NULL, NULL, '15174240702', '锦州市太和区凌南东里宝地城C区42-33、42-34号', '吉顺隆辽宁线', '郭兰玲', NULL, '货站自提', '锦州', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2326, 'DAL', '大连', NULL, NULL, '13889406632', '大连市沙河口区石桥街10-2号', '吉顺隆辽宁线', '尹贻军', NULL, '送货', '大连', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2327, 'FXI', '阜新博洛尼', NULL, NULL, '13795010203', '辽宁省阜新市细河区民族街20-20楼4号', '吉顺隆辽宁线', '杨斌', NULL, '货站自提', '阜新', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2328, 'PLD', '普兰店', NULL, NULL, '13940986787', '辽宁省普兰店市颐安家居一楼', '吉顺隆辽宁线', '宋建军', NULL, '货站自提', '普兰店', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2329, 'SEY', '沈阳博洛尼', NULL, NULL, '13079772915', '中国,辽宁省,沈阳市,浑南区营城子大街9号南大甸子社区', 'DC自提', '迟爱群', NULL, 'DC自提', '沈阳', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2330, '大连1', '大连1', NULL, NULL, '13889406632', '大连市沙河口区石桥街10-2号', '吉顺隆辽宁线', '尹贻军', NULL, '送货', '大连', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2331, 'YIK', '营口博洛尼', NULL, NULL, '13030796777', '辽宁省营口市光华路北八号甲11号门市', '吉顺隆辽宁线', '刘云波', NULL, '货站自提', '营口', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2332, 'FXI.BLN', '阜新市', NULL, NULL, '137 9501 0203', '辽宁省阜新市细河区民族街20-20楼4号', '吉顺隆辽宁线', '杨斌', NULL, '货站自提', '阜新', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2333, 'ANS.BLN', '鞍山博洛尼', NULL, NULL, '13904204435', '辽宁省鞍山市铁东区巴黎花园南门', '吉顺隆辽宁线', '闫冬雪', NULL, '货站自提', '鞍山', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2334, 'LYA.BLN', '辽阳博洛尼', NULL, NULL, '13332346661', '辽宁省辽阳市文圣区天福路36号居然之家', 'DC自提', '马莉 13332346661', NULL, 'DC自提', '辽阳', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2335, 'BYQ', '鲅鱼圈-冷艳', NULL, NULL, '13940765766', '辽宁省营口鲅鱼圈日月大道红星美凯龙3楼博洛尼收', '吉顺隆辽宁线', '单建铭', NULL, '货站自提', '鲅鱼圈', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2336, 'XNC', '兴城-卜海彬', NULL, NULL, '18642955858', '辽宁省葫芦岛市中旺之家', 'DC自提', '卜海燕', NULL, 'DC自提', '兴城', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2337, 'BEX', '本溪-刘一', NULL, NULL, '', '', '吉顺隆辽宁线', '', NULL, '货站自提', '本溪', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2338, 'PJN.BLN', '盘锦-杨斌', NULL, NULL, '13795010203', '辽宁省盘锦市兴隆台区居然之家一号馆四楼博洛尼', '吉顺隆辽宁线', '杨斌', NULL, '货站自提', '盘锦', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2339, 'HUD', '葫芦岛-赵雪', NULL, NULL, '', '', '吉顺隆辽宁线', '', NULL, '货站自提', '葫芦岛', '辽宁', NULL);
INSERT INTO `wms_cost_agent` VALUES (2340, 'JIL.YT', '吉林艺腾', NULL, NULL, '15044262365', '吉林市昌邑区解放东路昌邑公安分局斜对面', '吉顺隆辽宁线', '丁丁', NULL, '货站自提', '吉林', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2341, 'CHU', '长春', NULL, NULL, '81686611 18686361809', '吉林省长春市南关区卫星花园13栋', '', '于长林', NULL, '', '长春', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2342, 'YJI', '延吉', NULL, NULL, '13844364380/15585582979', '吉林省延吉市河南欧尚家居二楼博洛尼', '吉顺隆辽宁线', '段丽茹/孙艳民', NULL, '货站自提', '延吉', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2343, 'SOY', '松原', NULL, NULL, '15584533888', '吉林省松原市宁江区单家围子大东北农机市场库房', '吉顺隆辽宁线', '侯艳龙', NULL, '货站自提', '松原', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2344, 'DHA', '敦化', NULL, NULL, '', '', '吉顺隆辽宁线', '', NULL, '送货', '敦化', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2345, 'HUC', '珲春', NULL, NULL, '13620715755', '吉林省珲春市河南西街', '吉顺隆辽宁线', '颜玉祥', NULL, '货站自提', '珲春', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2346, 'JIL.BLN', '吉林－天瑞祥', NULL, NULL, '15044262365', '吉林市昌邑区解放东路吕邑公安分局斜对面', '吉顺隆辽宁线', '丁丁', NULL, '货站自提', '吉林', '吉林', NULL);
INSERT INTO `wms_cost_agent` VALUES (2347, 'PGU', '平谷－简家生活', NULL, NULL, '13911387717', '北京市平谷区千里马环岛南150米路东', '', '王雅娟', NULL, '', '平谷', '北京', NULL);
INSERT INTO `wms_cost_agent` VALUES (2348, 'LZH', '兰州', NULL, NULL, '18693115511', '甘肃省兰州市城关区雁东路红星美凯龙博洛尼整体家居', '', '翁有朋', NULL, '', '兰州', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2349, 'TSH', '天水', NULL, NULL, '1590290820', '天水市天宝建材市场2楼电梯口位置', '', '孟飞', NULL, '', '天水', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2350, 'LZH.WKD', '兰州维克多', NULL, NULL, '13893393304，0931-8488280', '甘肃省兰州市城关区滨河东路270号一、二层', '', '郝跃敏', NULL, '', '兰州', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2351, 'JQS', '酒泉', NULL, NULL, '155937178850937-6985850', '甘肃省酒泉市玉门石油基地百合园50－1－602收货地址：酒泉富康家世界4号楼1－3铺', '', '万玉娟', NULL, '', '酒泉', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2352, 'ZHY', '张掖', NULL, NULL, '18793699266/17793621961', '甘肃省张掖市南二环路金三角商业广场6号2F-202', '', '张晓利', NULL, '', '张掖', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2353, 'QYG', '庆阳', NULL, NULL, '18609341082', '甘肃省庆阳市西峰区西环路高速南出口向北100米', '', '付晓伟', NULL, '', '庆阳', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2354, 'TIS', '天水博洛尼', NULL, NULL, '18793859336', '甘肃天水麦积桥南家居建材城12号馆', '', '郑晓翔', NULL, '', '天水', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2355, 'JYG.BLN', '嘉峪关－方龙', NULL, NULL, '15193497199', '甘肃省嘉峪关市居然之家', '', '方龙', NULL, '', '嘉峪关', '甘肃', NULL);
INSERT INTO `wms_cost_agent` VALUES (2356, 'BAO', '保定', NULL, NULL, '13931237292和677999', '三丰路鑫丰市场C区113号.东易日盛理想家居建材超市', '', '杨冲', NULL, '', '保定', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2357, 'CDE.KB', '承德科博', NULL, NULL, '13313153494', '河北省承德市双滦区红星美凯龙二层南门', '', '鲍宇', NULL, '', '承德', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2358, 'CZH.WF', '沧州万福', NULL, NULL, '18632768851', '沧州市朝阳北路颐合家居底商博洛尼厨房旗舰店', '', '刘欣', NULL, '', '沧州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2359, 'CZO', '沧州', NULL, NULL, '1.86328E+21', '沧州市朝阳北路颐合家居底商', '', '刘欣', NULL, '', '沧州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2360, 'DZH', '定州', NULL, NULL, '13932233769', '定州市博陵街南段', '', '李海珍', NULL, '', '定州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2361, 'GBD', '高碑店', NULL, NULL, '15630221955', '保定高碑店市107国道海龙家居建材城三楼博洛尼橱柜', '', '娄永力', NULL, '', '高碑店', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2362, 'HAD', '邯郸', NULL, NULL, '0310-2038884', '邯郸市人民路国贸大厦A座13层', '', '赵小慧', NULL, '', '邯郸', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2363, 'HAD.LNN', '邯郸洛尼尼', NULL, NULL, '0310-2038884', '邯郸市人民路国贸大厦A座13层', '', '赵小慧', NULL, '', '邯郸', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2364, 'HHB', '黄骅', NULL, NULL, '15803376277', '河北省黄骅是建设大街北端黄骅燃气公司（景泰小区对过）', '', '孙宇', NULL, '', '黄骅', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2365, 'HSI', '衡水', NULL, NULL, '13403189548/0318-8519262', '衡水市桃城区和平路前进街青杨树工业园内金羽服装厂院内', '', '李红玲', NULL, '', '衡水', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2366, 'LAF', '廊坊', NULL, NULL, '0316-2238107', '河北省廊坊市广阳道锦绣家园地产大厦A座A号', '', '高先生', NULL, '', '廊坊', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2367, 'QAN', '迁安', NULL, NULL, '13754450287', '河北省迁安市张各庄商业区18号', '', '刘志林', NULL, '', '迁安', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2368, 'QHD', '秦皇岛', NULL, NULL, '13513348568', '秦皇岛民族路69号科宝博洛尼整体家居', '', '毛坤龙', NULL, '', '秦皇岛', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2369, 'REQ', '任丘', NULL, NULL, '13931787628', '河北省任丘市西环建材市场华油灯具城16-17号', '', '崔卫东', NULL, '', '任丘', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2370, 'SJZ', '石家庄', NULL, NULL, '0311-86105559', '石家庄市槐安路98-1号东岗怡园底商', '', '陈主管', NULL, '', '石家庄', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2371, 'TGS.YSJ', '唐山雅世家', NULL, NULL, '0315-2323775  15531599572', '唐山西外环马驹桥北', '', '牛小硕', NULL, '', '唐山', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2372, 'XJI', '辛集', NULL, NULL, '0', '辛集市金鹿家具城1-18号', '', '0', NULL, '', '辛集', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2373, 'XTA', '邢台', NULL, NULL, '15030955115', '河北省邢台市太行路董村仓库', '', '杜红磊', NULL, '', '邢台', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2374, 'XUH', '宣化', NULL, NULL, '18131308902（0313-7968801）', '宣化区政府东侧华耐建材家居广场负一楼', '', '梁凯', NULL, '', '宣化', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2375, 'ZHA', '遵化', NULL, NULL, '0315-6097555/13383050536', '遵化市新法院西行200米路南', '', '杨丽敏', NULL, '', '遵化', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2376, 'ZJK', '张家口', NULL, NULL, '18931315971', '张家口高薪区纬三路华耐家居负一楼', '', '王涛', NULL, '', '张家口', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2377, 'ZZH', '涿州', NULL, NULL, '13400449713', '河北省涿州市华阳中路198号', '', '孙磊', NULL, '', '涿州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2378, 'TAS', '唐山1', NULL, NULL, '0315-2323775  15531599572', '唐山西外环马驹桥北', '', '牛小硕', NULL, '', '唐山', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2379, 'CFD', '曹妃甸', NULL, NULL, '18632550672', '河北省唐山市曹妃甸区创业大街213号（龙凤园底商）', '', '赵威', NULL, '', '曹妃甸', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2380, 'ZHK', '张家口博洛尼', NULL, NULL, '18931315971', '张家口高薪区纬三路华耐家居负一楼', '', '王涛', NULL, '', '张家口', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2381, 'BAD', '保定市', NULL, NULL, '13931237292', '三丰路鑫丰市场C区113号.东易日盛理想家居建材超市', '', '陈娇', NULL, '', '保定', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2382, 'LUX', '滦县', NULL, NULL, '18931560090/18931538048', '河北省唐山市滦县新城县医院北门对面', '', '王总', NULL, '', '滦县', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2383, 'XNT', '邢台市', NULL, NULL, '15030955115', '河北省邢台市太行路董村仓库', '', '杜红磊', NULL, '', '邢台', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2384, '衡水1', '衡水1', NULL, NULL, '111', '111', '', '111', NULL, '', '衡水', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2385, 'BAU', '霸州', NULL, NULL, '18033665222', '河北省霸州市建设西道路南', '', '李广保', NULL, '', '霸州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2386, 'ZOZ', '涿州博洛尼', NULL, NULL, '18633388018', '河北保定市涿州市华阳路212号', '', '宋士平', NULL, '', '涿州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2387, 'HLA', '怀来', NULL, NULL, '13811833330', '河北省怀来县沙城镇府前西街理想上城底商', '', '裴彦峰', NULL, '', '怀来', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2388, 'GUN', '固安', NULL, NULL, '', '', '', '400020', NULL, '', '固安', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2389, 'KCH', '宽城', NULL, NULL, '18832446880', '河北省承德市宽城金山街鼎点装饰', '', '刘智方', NULL, '', '宽城', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2390, 'HHU', '黄骅博洛尼', NULL, NULL, '0317-5312340/15932702586', '河北省黄骅市建设大街北端黄骅燃气公司（景泰小区对过）', '', '张小洋', NULL, '', '黄骅', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2391, 'CXN', '磁县', NULL, NULL, '', '', '', '', NULL, '', '磁县', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2392, 'DIZ', '定州博洛尼', NULL, NULL, '王志国13373126885', '', '', '王志国', NULL, '', '定州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2393, 'ZHU', '遵化博洛尼', NULL, NULL, '0315-6097555/13383050536', '遵化市新法院西行200米路南', '', '杨丽敏', NULL, '', '遵化', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2394, 'RQU', '任丘博洛尼', NULL, NULL, '', '', '', '', NULL, '', '任丘', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2395, 'XHE', '香河', NULL, NULL, '15613664888', '河北香河新华大街西段12号荣事达橱柜', '', '刘辉', NULL, '', '香河', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2396, 'QAN.BLN', '迁安博洛尼', NULL, NULL, '', '', '', '', NULL, '', '迁安', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2397, 'XUH.BLN', '宣化博洛尼', NULL, NULL, '（0313-7968801）18131308902', '张家口高薪区纬三路华耐家居负一楼', '', '梁凯', NULL, '', '宣化', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2398, 'HAD.BLN', '邯郸博洛尼', NULL, NULL, '13722980525', '邯郸市复兴区前进大街先锋路先锋苑小区3#6', '', '李颜', NULL, '', '邯郸', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2399, 'CZO.BLN', '沧州博洛尼', NULL, NULL, '15512799970', '沧州市运河区兰亭苑12-2-501', '', '赵晨霞', NULL, '', '沧州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2400, 'DCH', '大厂', NULL, NULL, '', '大厂', '', '', NULL, '', '大厂', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2401, 'CZS', '沧州市', NULL, NULL, '15175700281', '沧州', '', '于琴', NULL, '', '沧州', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2402, 'LAF.BLN', '廊坊－孙雪', NULL, NULL, '13384841184', '包头市友谊大街现代城54号底店', '', '田旭东', NULL, '', '廊坊', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2403, 'YQN', '永清－纪泽坤', NULL, NULL, '13777536250', '永康市溪心路65-67号', '', '陈华莹', NULL, '', '永清', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2404, 'CFD.BLN', '曹妃甸-暖盈', NULL, NULL, '18632550672', '河北省唐山市曹妃甸区创业大街213号（龙凤园底商）', '', '赵威', NULL, '', '曹妃甸', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2405, 'XOA', '雄安-康建宏', NULL, NULL, '13803339336', '河北省雄安新区雄县将台路179号门店', '', '张伊琳', NULL, '', '雄安', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2406, 'YQI', '永清-秦景波', NULL, NULL, '', '', '', '', NULL, '', '永清', '河北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2407, 'ZHZ', '郑州', NULL, NULL, '13931179022', '河南省郑州市商都路红星美凯龙四层', '', '陈荣杰', NULL, '', '郑州', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2408, 'ANY.CHY', '安阳超亚', NULL, NULL, '15603721209', '河南省安阳市文峰大道东段449号博洛尼整体厨房', '', '董鹏飞', NULL, '', '安阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2409, 'CHY', '长垣', NULL, NULL, '13782529981', '河南省长垣县亿隆商业街60号', '', '常洪涛', NULL, '', '长垣', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2410, 'JIZ', '焦作', NULL, NULL, '186391400670391—2289900', '焦作市人民路398号科宝博洛尼', '', '郭瑞娟', NULL, '', '焦作', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2411, 'LOY.QY', '洛阳企予', NULL, NULL, '0379-65996008-13803882936', '河南省洛阳市洛龙区新区博物馆水磨村', '', '刘玉珍', NULL, '', '洛阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2412, 'NYA.KB', '南阳博洛尼', NULL, NULL, '18272797582', '南阳市卧龙区红星美凯龙C座3楼', '', '张梦丽', NULL, '', '南阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2413, 'PDS.BLN', '平顶山博洛尼', NULL, NULL, '13782488999', '平顶山市建设路西段西段老师专对面', '', '夏永红', NULL, '', '平顶山', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2414, 'PUY', '濮阳', NULL, NULL, '156039372280393-8205658', '河南濮阳中厚油田测井公司', '', '张晋阳', NULL, '', '濮阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2415, 'SHQ', '商丘', NULL, NULL, '13569337568', '河南省商丘市睢阳区神火大道与香君路交叉口向东200米路北', '', '陈巍', NULL, '', '商丘', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2416, 'SMX', '三门峡', NULL, NULL, '13639868026   0398-2829933', '河南省三门峡市湖滨区河堤东段金三角建材港B馆12 13号', '', '扬巍', NULL, '', '三门峡', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2417, 'XIX', '新乡', NULL, NULL, '139037385470373-5056218', '新乡市饮马口居然之家店', '', '蒋曜宇', NULL, '', '新乡', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2418, 'XYA', '信阳', NULL, NULL, '152 3767 1903', '河南省信阳市建材港29号楼（）', '', '黄琳', NULL, '', '信阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2419, 'HEB', '鹤壁', NULL, NULL, '13849215701', '河南省鹤壁市淇滨区黎阳路与泰山路交叉口向北100米路西百运佳公司院内宜佳仓库', '', '张学玲', NULL, '', '鹤壁', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2420, 'CHG', '长葛', NULL, NULL, '15837463050', '河南省长葛市全景大厦A座', '', '梁鹃', NULL, '', '长葛', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2421, 'KIF', '开封博洛尼', NULL, NULL, '15093645998', '河南省开封市开发区御都国际营业房', '', '朱真真', NULL, '', '开封', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2422, 'XCH', '许昌', NULL, NULL, '18039998341', '河南省许昌市文峰南路，亚欧建材城', '', '毛晓丽', NULL, '', '许昌', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2423, 'LHE', '漯河', NULL, NULL, '13273051110', '河南漯河市舟山路与辽河路交叉口北20米', '', '陈凯楠', NULL, '', '漯河', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2424, 'BAF', '宝丰', NULL, NULL, '', '', '', '', NULL, '', '宝丰', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2425, 'ZHZ.BLN', '郑州博洛尼', NULL, NULL, '18625550738', '金水区大刑屯14号楼5单元1楼东户', '', '董战胜', NULL, '', '郑州', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2426, 'ZZS', '郑州市', NULL, NULL, '0371-68086207/15617050971', '河南省郑州市郑开大道省委党校向北恒泽物流园内', '', '王玉杰', NULL, '', '郑州', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2427, 'JIZ.BLN', '焦作－史广亮', NULL, NULL, '13384841184', '包头市友谊大街现代城54号底店', '', '田旭东', NULL, '', '焦作', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2428, 'LOY.BLN', '洛阳－彩丽家居', NULL, NULL, '18,736,375,122,037,900,000,000', '河南省洛阳市洛龙区新区博物馆水磨村', '', '秦培真', NULL, '', '洛阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2429, 'LIZ', '林州-王松', NULL, NULL, '1', '1', '', '王松', NULL, '', '林州', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2430, 'XIX.BLN', '新乡-姚鸿铭', NULL, NULL, '', '', '', '', NULL, '', '新乡', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2431, 'SQI', '商丘-李颖', NULL, NULL, '', '', '', '', NULL, '', '商丘', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2432, 'AYA', '安阳-王佳佳', NULL, NULL, '', '', '', '', NULL, '', '安阳', '河南', NULL);
INSERT INTO `wms_cost_agent` VALUES (2433, 'HHU.BLN', '黄骅-李福祥', NULL, NULL, '', '', '', '', NULL, '', '黄骅', '湖北', NULL);
INSERT INTO `wms_cost_agent` VALUES (2434, 'ULC', '乌兰察布', NULL, NULL, '13948486565', '', '', '智全胜', NULL, '', '乌兰察布', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2435, 'XLT', '锡林浩特', NULL, NULL, '13804747662', '内蒙古锡林浩特市美家美户家居建材广场二期大厅一楼', '', '刘文戟', NULL, '', '锡林浩特', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2436, 'TLO', '通辽', NULL, NULL, '13847578817', '内蒙古通辽市创业大道安华建材城', '', '张鹏', NULL, '', '通辽', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2437, 'HLE', '海拉尔', NULL, NULL, '15049536888', '内蒙古呼伦贝尔市海拉尔国贸家居门市博洛尼橱柜', '', '李海丹', NULL, '', '海拉尔', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2438, 'ZLT', '扎兰屯', NULL, NULL, '15247085555 13947015557', '内蒙古呼伦贝尔市扎兰屯市文化小区东门', '', '郝敬源', NULL, '', '扎兰屯', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2439, 'WLT', '乌兰浩特', NULL, NULL, '1.86117E+21', '内蒙古乌兰浩特市北物流江南综合楼3号', '', '洪兴福', NULL, '', '乌兰浩特', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2440, 'HLR', '海拉尔博洛尼', NULL, NULL, '15049536888', '内蒙古呼伦贝尔市海拉尔国贸家居门市博洛尼橱柜', '', '李海丹', NULL, '', '海拉尔', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2441, 'CHF', '赤峰', NULL, NULL, '13404833179', '内蒙古赤峰市新城区临潢大街天宇大厦', '', '张晓慧13404833179', NULL, '', '赤峰', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2442, 'TLO.BLN', '通辽博洛尼', NULL, NULL, '13847578817', '内蒙古通辽市创业大道安华建材城', '', '张鹏', NULL, '', '通辽', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2443, 'ULC.BLN', '乌兰察布博洛尼', NULL, NULL, '13948486565', '内蒙古乌兰察布集宁区红星美凯龙', '', '智全胜', NULL, '', '乌兰察布', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2444, 'ZLT.BLN', '扎兰屯-郝敬源', NULL, NULL, '15247085555 13947015557', '内蒙古呼伦贝尔市扎兰屯市文化小区东门', '', '郝敬源', NULL, '', '扎兰屯', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2445, 'TOL', '通辽－肖茂林', NULL, NULL, '13847578817', '内蒙古通辽市创业大道安华建材城', '', '张鹏', NULL, '', '通辽', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2446, 'MZL', '满洲里-王若馨', NULL, NULL, '', '', '', '', NULL, '', '满洲里', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2447, 'WNQ', '翁牛特旗-宋国卫', NULL, NULL, '', '', '', '', NULL, '', '翁牛特旗', '内蒙古', NULL);
INSERT INTO `wms_cost_agent` VALUES (2448, 'YCU.KB', '银川科宝', NULL, NULL, '18995180778、17795056563', '银川市新华东街319号建发家世界科宝博洛尼厨卫家具有限公司', '', '吴木兰', NULL, '', '银川', '宁夏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2449, 'YCU.BLN', '银川-方军霞', NULL, NULL, '18995180778', '宁夏银川市兴庆区新华东街月星家居广场二楼博洛尼', '', '吴木兰', NULL, '', '银川', '宁夏', NULL);
INSERT INTO `wms_cost_agent` VALUES (2450, 'XNG', '西宁', NULL, NULL, '186971403990971-4380787', '青海省西宁市城西区西川南路48号居然之家万佳海湖店', '', '林斌', NULL, '', '西宁', '青海', NULL);
INSERT INTO `wms_cost_agent` VALUES (2451, 'XIN', '西宁1', NULL, NULL, '186971403990971-4380787', '青海省西宁市城西区西川南路48号居然之家万佳海湖店', '', '林斌', NULL, '', '西宁', '青海', NULL);
INSERT INTO `wms_cost_agent` VALUES (2452, 'XAN', '西安', NULL, NULL, '15202936680、029-83664332', '西安市未央区石化大道华南物流市场斜对面裕川农业A-南4号', '', '林胜强', NULL, '', '西安', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2453, 'XINING', '西宁2', NULL, NULL, '186971403990971-4380787', '青海省西宁市城西区西川南路48号居然之家万佳海湖店', '', '林斌', NULL, '', '西宁', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2454, 'YUL', '榆林', NULL, NULL, '0912-3596356', '榆林市西部三辰家具建材博览中心橱柜区1-B020', '', '王峰', NULL, '', '榆林', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2455, 'YAN', '延安', NULL, NULL, '1.30981E+21', '延安市宝塔区百米大道圣凯龙家具城', '', '拓霖霖', NULL, '', '延安', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2456, 'SHM', '神木', NULL, NULL, '153092255550912-8019563', '陕西榆林神木建材城一楼博洛尼整体厨房', '', '王峰', NULL, '', '神木', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2457, 'WIN', '渭南', NULL, NULL, '15191837666', '陕西省渭南市荣发建材城', '', '张博', NULL, '', '渭南', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2458, 'ANK', '安康', NULL, NULL, '15309152599', '安康高新区天贸城', '', '陈晨', NULL, '', '安康', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2459, 'HAZ', '汉中', NULL, NULL, '18612782293', '陕西省汉中市汉台区西环路百家汇商城', '', '李荣', NULL, '', '汉中', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2460, 'XYG', '咸阳', NULL, NULL, '18191065555', '陕西省咸阳市居然之家二层博洛尼', '', '李金山', NULL, '', '咸阳', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2461, 'YAL', '杨凌', NULL, NULL, '', '', '', '', NULL, '', '杨凌', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2462, 'XUY', '旬阳', NULL, NULL, '', '', '', '', NULL, '', '旬阳', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2463, 'ZIY', '紫阳', NULL, NULL, '', '', '', '', NULL, '', '紫阳', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2464, 'HCH', '韩城', NULL, NULL, '', '', '', '', NULL, '', '韩城', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2465, 'SLO', '商洛', NULL, NULL, '15389238818/17709187880', '商洛市商鞅大道居然之家店', '', '杨洋/李珍妮', NULL, '', '商洛', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2466, 'SHM.BLN', '神木－奥伟', NULL, NULL, '', '', '', '', NULL, '', '神木', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2467, 'BAJ', '宝鸡-苗利强', NULL, NULL, '15319274444', '陕西省宝鸡市金台区金台大道居然之家', '', '苗利强', NULL, '', '宝鸡', '陕西', NULL);
INSERT INTO `wms_cost_agent` VALUES (2468, 'SHB', '上海博洛尼', NULL, NULL, '15801798283', '上海市奉贤区金汇镇大叶公路5001号4号楼1楼', '', '何秋菊', NULL, '', '上海', '上海', NULL);
INSERT INTO `wms_cost_agent` VALUES (2469, 'TJG', '天津', NULL, NULL, '13920766961', '天津东丽区于明庄跃进路', '', '刘库管', NULL, '', '天津', '天津', NULL);
INSERT INTO `wms_cost_agent` VALUES (2470, 'HGU', '汉沽', NULL, NULL, '', '', '', '', NULL, '', '汉沽', '天津', NULL);
INSERT INTO `wms_cost_agent` VALUES (2471, 'KEL', '库尔勒', NULL, NULL, '13809934685', '库尔勒华凌市场一层博洛尼整体厨房', '', '仲楠', NULL, '', '库尔勒', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2472, 'SZI', '石河子', NULL, NULL, '15209931273', '石河子开发区新世纪建材城博洛尼整体厨房', '', '田果', NULL, '', '石河子', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2473, 'TUN', '奎屯', NULL, NULL, '李超18997728222', '新疆奎屯市瑞明万佳建材市场11栋1A08号', '', '李超18997728222', NULL, '', '奎屯', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2474, 'UMQ.TY', '乌市天盈', NULL, NULL, '0991-66127771869081817213120404843', '七道湾工业园', '', '狄昭伦', NULL, '', '乌鲁木齐', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2475, 'UMQ', '乌鲁木齐', NULL, NULL, '13579418516', '新疆维吾尔自治区乌鲁木齐市中科路', '', '孙永辉', NULL, '', '乌鲁木齐', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2476, 'WUL.JZ', '乌鲁木齐家装', NULL, NULL, '18690216666', '新疆乌鲁木齐克拉玛依东街392号深圳城2621', '', '仲楠', NULL, '', '乌鲁木齐', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2477, 'TCH', '塔城', NULL, NULL, '15719928881', '新疆塔城市华宝国际A座107博洛尼整体橱柜', '', '吉宇鹏', NULL, '', '塔城', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2478, 'YIG', '伊宁', NULL, NULL, '13899934030', '伊宁市开发区巴彦岱', '', '湛文胤', NULL, '', '伊宁', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2479, 'SIH', '石河子博洛尼', NULL, NULL, '18119270745', '新疆石河子市步行街建材城', '', '张亚龙', NULL, '', '石河子', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2480, 'WUL', '乌鲁木齐博洛尼', NULL, NULL, '13579418516', '新疆维吾尔自治区乌鲁木齐市中科路', '', '孙永辉', NULL, '', '乌鲁木齐', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2481, 'KLY', '克拉玛依博洛尼', NULL, NULL, '18899191230', '新疆克拉玛依市胜利路国际家居建材城居然之家3号楼326号', '', '耿俊超', NULL, '', '克拉玛依', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2482, 'CHJ', '昌吉', NULL, NULL, '', '', '', '', NULL, '', '昌吉', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2483, 'KRL', '库尔勒博洛尼', NULL, NULL, '', '', '', '', NULL, '', '库尔勒', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2484, 'HMI', '哈密市', NULL, NULL, '15699022699', '新疆哈密市红星美凯龙商场', '', '张永明', NULL, '', '哈密', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2485, 'KUT', '奎屯博洛尼', NULL, NULL, '李超18997728222', '新疆奎屯市瑞明万佳建材市场11栋1A08号', '', '李超18997728222', NULL, '', '奎屯', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2486, 'AKS', '阿克苏－王润', NULL, NULL, '13384841184', '包头市友谊大街现代城54号底店', '万达物流', '田旭东', NULL, '1', '阿克苏', '新疆', NULL);
INSERT INTO `wms_cost_agent` VALUES (2487, '', '', NULL, NULL, '', '', '三志物流', '', NULL, '1', '济南', '山东省', NULL);
INSERT INTO `wms_cost_agent` VALUES (2488, ' PTN', '平潭-郑仟泉', NULL, NULL, '15280000279', '福建平潭西航路111-5', 'DC自提', '郑仟泉', NULL, '1', '平潭', '福建', NULL);
INSERT INTO `wms_cost_agent` VALUES (2489, 'RUA.BLN', '瑞安-李光平', NULL, NULL, '13967070912', '浙江省瑞安市莘阳大道稠州银行对面（熙望车业隔壁）', '上海益递物流', '吴水根', NULL, '送货', '瑞安', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2490, 'TNX', '桐乡-李雪建', NULL, NULL, '13867325317', '浙江省桐乡市梧桐街道凤鸣装饰材料市场（桐乡市凤鸣商城8幢）', '上海益递物流', '李雪建', NULL, '货站自提', '桐乡', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2491, 'TIT', '天台-裴桥', NULL, NULL, '13575836683', '浙江省台州市天台县始丰街道天台山西路268号', '上海益递物流', '裴桥', NULL, '货站自提', '天台', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2492, 'HZE.BLN', '菏泽-曹存记', NULL, NULL, '13295308589', '菏泽市人民路亿丰时代广场银座家居一楼', '三志物流', '曹存记', NULL, '2', '菏泽', '山东', NULL);
INSERT INTO `wms_cost_agent` VALUES (2493, 'CHA', '淳安-朱剑', NULL, NULL, '18966486852', '浙江省杭州市淳安县千岛湖镇马路村木格装饰', '上海益递物流', '朱剑', NULL, '货站自提', '淳安', '浙江', NULL);
INSERT INTO `wms_cost_agent` VALUES (2494, 'LQN', '临泉-粱东梅', NULL, NULL, '18956732286', '安徽省阜阳市临泉县辉隆大市场26幢113号', 'DC自提', '粱东梅', NULL, '2', '临泉', '安徽', NULL);
INSERT INTO `wms_cost_agent` VALUES (2495, 'TNL', '铜陵-杨宜兰 ', NULL, NULL, '18156207699', '安徽铜陵市天山大道红星美凯龙', 'DC自提', '杨宜兰', NULL, '2', '铜陵', '安徽', NULL);

-- ----------------------------
-- Table structure for wms_cost_carrier
-- ----------------------------
DROP TABLE IF EXISTS `wms_cost_carrier`;
CREATE TABLE `wms_cost_carrier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `outCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_cost_type
-- ----------------------------
DROP TABLE IF EXISTS `wms_cost_type`;
CREATE TABLE `wms_cost_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_cost_type
-- ----------------------------
INSERT INTO `wms_cost_type` VALUES (2, 'BED', '2017-12-28 10:28:49', 'B类', '按箱数和天数收费', '2018-01-30 12:14:12', '0.2元/箱/天', 0.2);
INSERT INTO `wms_cost_type` VALUES (3, 'M3PD', '2018-01-27 12:24:48', 'C类', '按照立方米和天数收费', '2018-06-29 18:29:51', '1.1元/立方米/天', 1.1);
INSERT INTO `wms_cost_type` VALUES (4, 'TPD', '2018-01-27 12:27:01', 'D类', '按照吨位和天数收费', '2018-01-30 12:14:52', '1.5元/吨/天', 1.5);

-- ----------------------------
-- Table structure for wms_customer
-- ----------------------------
DROP TABLE IF EXISTS `wms_customer`;
CREATE TABLE `wms_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(255) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `department` int(11) NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_man_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_man_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_man_tell` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `delivery_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_man_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rent_area` double NULL DEFAULT NULL,
  `costType_id` int(11) NULL DEFAULT NULL,
  `accept_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accept_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accept_man_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `customer_abb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `final_customer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `final_customer_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `final_customer_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_7gq0i07g3177kks1ncou5y6mm`(`department`) USING BTREE,
  INDEX `FK_k5vcqx8tyjuou5cf79gqtx2p0`(`costType_id`) USING BTREE,
  CONSTRAINT `FK_7gq0i07g3177kks1ncou5y6mm` FOREIGN KEY (`department`) REFERENCES `wms_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_customer
-- ----------------------------
INSERT INTO `wms_customer` VALUES (1, '10003', '4QrcOUm6Wau+VuBX8g+IPg==', '李长华', '1828454870314', 'uYCkjO8JM7bzs8S3PRosnA==', 1, NULL, NULL, 7, '成都诚骏科技', '成都市武侯区江园路18号', '周雨欣', '15254780123', '成都市龙泉驿区来龙村60号', '成都市龙泉驿区来龙村60号', '241578@qq.com', NULL, '成都市武侯区江园路18号', '龙潭立交成致路38号', '诚骏科技有限公司', 100, 2, 'adress', 'man', 'phone', 'agent', 'number', 'customer', 'finalc', 'finalca', 'finalcp', 'province');
INSERT INTO `wms_customer` VALUES (2, '10004', 'ICy5YqxZB1uWSwcVLSNLcA==', '刘庆云', '15252782143', NULL, 1, '2017-12-25 16:05:35', '2017-12-29 09:33:46', 7, '龙江物流有限公司', '124651354@qq.com', '舒玥', '15245741245', '成都市双流向吉路', '成都市双流向吉路', '785555113@qq.com', NULL, '锦江区万源路12号', '锦江区人民路18号', '沁园科技有限公司', 0, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_customer` VALUES (3, '10005', 'ICy5YqxZB1uWSwcVLSNLcA==', '周智宇', '1125411', NULL, 0, '2017-12-26 16:10:46', '2017-12-29 09:34:59', 7, '隆鑫物流有限公司', '建材路45号', '何乔美', '15424514578', '成华区人民公园89号', '成华区人民公园88号', '4522114@qq.com', NULL, '建材路45号', '建材路45号', '宁武酒楼', 0, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_department
-- ----------------------------
DROP TABLE IF EXISTS `wms_department`;
CREATE TABLE `wms_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_koc7o23146nhn39sbvprwg24g`(`parent`) USING BTREE,
  INDEX `FK_6rxa13mni7hec3cvhwf6xc5rh`(`create_user`) USING BTREE,
  INDEX `FK_jhl3vyesk3e01k0kkkco6aerk`(`update_user`) USING BTREE,
  CONSTRAINT `FK_6rxa13mni7hec3cvhwf6xc5rh` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `FK_jhl3vyesk3e01k0kkkco6aerk` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_koc7o23146nhn39sbvprwg24g` FOREIGN KEY (`parent`) REFERENCES `wms_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_department
-- ----------------------------
INSERT INTO `wms_department` VALUES (1, 'CD001', 0, '成都', NULL, '成都基地管理员', '2018-07-03 10:12:21', '2018-07-03 10:12:23', 2, 1);
INSERT INTO `wms_department` VALUES (3, 'XN001', 0, '虚拟', NULL, '虚拟基地管理员', '2018-07-03 10:14:21', '2018-07-03 10:14:23', 2, NULL);
INSERT INTO `wms_department` VALUES (4, 'CQ001', 0, '重庆', NULL, '重庆基地管理员', '2018-07-03 10:15:35', '2018-07-03 10:15:40', 2, 1);
INSERT INTO `wms_department` VALUES (5, 'CS001', 0, '长沙', NULL, '长沙基地管理员', '2018-07-03 10:16:31', '2018-07-03 10:16:40', 2, 1);
INSERT INTO `wms_department` VALUES (6, 'KM001', 0, '昆明', NULL, '昆明基地管理员', '2018-07-03 10:17:00', '2018-07-03 10:17:42', 2, NULL);
INSERT INTO `wms_department` VALUES (7, 'JN001', 0, '济南', NULL, '济南基地管理员', '2018-07-03 10:18:29', '2018-07-03 10:18:32', 2, NULL);
INSERT INTO `wms_department` VALUES (8, 'BJ001', 0, '北京', NULL, '北京基地管理员', '2018-07-03 10:08:44', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (9, 'GZ001', 0, '广州', NULL, '广州基地管理员', '2018-07-03 10:10:33', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (10, 'NJ001', 0, '南京', NULL, '南京基地管理员', '2018-07-03 10:19:32', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (11, 'FZ001', 0, '福州', NULL, '福州基地管理员', '2018-07-03 10:20:50', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (12, 'WH001', 0, '武汉', NULL, '武汉基地管理员', '2018-07-03 10:21:44', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (13, 'HZ001', 0, '杭州', NULL, '杭州基地管理员', '2018-07-03 10:22:30', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (14, 'TY001', 0, '太原', NULL, '太原基地管理员', '2018-07-03 10:23:22', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (15, 'BT001', 0, '包头', NULL, '包头基地管理员', '2018-07-03 10:25:20', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (16, 'NC001', 0, '南昌', NULL, '南昌基地管理员', '2018-07-03 10:26:36', NULL, 2, NULL);
INSERT INTO `wms_department` VALUES (17, 'ADMIN002', 1, '总部', NULL, '总部数据导入', '2018-07-03 15:54:16', NULL, 1, 1);

-- ----------------------------
-- Table structure for wms_fee_style
-- ----------------------------
DROP TABLE IF EXISTS `wms_fee_style`;
CREATE TABLE `wms_fee_style`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `costType_id` int(11) NULL DEFAULT NULL,
  `days` int(11) NULL DEFAULT NULL,
  `rent_num` double NULL DEFAULT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_8k8mu2krtlqfpi1jxtfgoks81`(`customer_id`) USING BTREE,
  CONSTRAINT `FK_8k8mu2krtlqfpi1jxtfgoks81` FOREIGN KEY (`customer_id`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_goods
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods`;
CREATE TABLE `wms_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NULL DEFAULT NULL,
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `borrow_time_limit` int(11) NULL DEFAULT NULL,
  `examine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `measurement_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `rfid_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `manufacturer` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  `high_remind` int(11) NULL DEFAULT NULL,
  `low_remind` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `pallet_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_in` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_out` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sku_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit_volume` double NULL DEFAULT NULL,
  `unit_weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_nub2sfnoykhimc8fpplk5r5ak`(`category`) USING BTREE,
  INDEX `FK_g7hfoj7es9vkjxloq28keofvw`(`manufacturer`) USING BTREE,
  INDEX `FK_h6w8fnm85cip8tfv2elyv33g4`(`warehouse`) USING BTREE,
  INDEX `FK_ffqf93b8bbjplsu4ub3ol6byu`(`create_user`) USING BTREE,
  INDEX `FK_75rdcupoq5u3ir36y0binq4wu`(`update_user`) USING BTREE,
  CONSTRAINT `FK_75rdcupoq5u3ir36y0binq4wu` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ffqf93b8bbjplsu4ub3ol6byu` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods
-- ----------------------------
INSERT INTO `wms_goods` VALUES (1, 30, '789654524', 0, '', '一种沐浴露', '箱', 'Dove多芬', 0, '4E0FA9475BE4BACB0824', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2018-02-27 17:41:36', NULL, 1, '4', NULL, NULL, NULL, 2.5, 2);
INSERT INTO `wms_goods` VALUES (2, 1, '789654525', 0, '', '一种沐浴露', '箱', 'Johnson强生婴儿', 59.9, '4A85B86038B6528B3536', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '4', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (3, 28, '789654526', 0, '', '一种沐浴露', '箱', 'LUX力士', 0, '4C499F820C788F7F77FD', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2018-02-27 14:23:14', NULL, 1, '5', NULL, NULL, NULL, 2, 3);
INSERT INTO `wms_goods` VALUES (4, 34, '789654527', 0, '', '一种沐浴露', '箱', 'Safeguard舒肤佳', 59.9, '4176B05EC3B6018415F3', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '6', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (5, 108, '789654528', 0, '', '一种沐浴露', '箱', 'OLAY玉兰油', 59.9, '4C2A961E6F5CE79C1BEB', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '3', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (6, -12, '789654529', 0, '', '一种沐浴露', '箱', '六神', 59.9, '4576AEDF6E2AFAE5B15E', '10瓶/箱', '1', NULL, NULL, 8, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '2', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (7, 24, '789654530', 0, '', '一种沐浴露', '箱', 'NIVEA妮维雅', 59.9, '434BB0F3BF042245FCC4', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '4', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (8, 0, '789654531', 0, '', '一种沐浴露', '箱', 'LYNX凌仕', 59.9, '4124B0F598D91062127A', '10瓶/箱', '1', NULL, NULL, 10, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '3', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (9, 26, '789654532', 0, '', '一种沐浴露', '箱', '威露士Walch', 59.9, '4920AB46597AB0B0D09D', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '3', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (10, 9, '789654533', 0, '', '一种沐浴露', '箱', '阿迪达斯Adidas', 59.9, '4D308B35D764EA7CE941', '10瓶/箱', '1', NULL, NULL, 9, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '2', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (11, 0, '789654534', 0, '', '一种饮料', '板', '哇哈哈', 1.2, '4905A87338559BB7318D', '4罐/板', '1', NULL, NULL, 1, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '5', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (33, 0, '2018011444B94FB2900D', 0, NULL, '好吃', '箱', '苹果', 0, '2018011444B94FB2900D', '10千克/箱', '1', NULL, NULL, 3, 100000000, 1, '2018-01-20 14:27:15', NULL, 1, NULL, '1', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (34, 0, '2018017B7C894DA4B1E8', 0, NULL, '一种白酒', '箱', '瓦岗包谷酒', 0, '2018017B7C894DA4B1E8', '10瓶/箱', '1', NULL, NULL, 7, 100000000, 0, '2018-01-30 11:44:15', NULL, 1, NULL, '4', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods` VALUES (35, 0, '2018029329E44EEFA2F0', 0, NULL, '不错', '箱', '红花郎', 0, '2018029329E44EEFA2F0', '20瓶/箱', '1', NULL, NULL, 9, 100000000, 0, '2018-02-08 09:14:33', NULL, 1, NULL, '1', NULL, NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for wms_goods_application
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_application`;
CREATE TABLE `wms_goods_application`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_goods_borrow
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_borrow`;
CREATE TABLE `wms_goods_borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `borrow_amount` int(11) NULL DEFAULT NULL,
  `borrow_op_date` datetime(0) NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `return_op_date` datetime(0) NULL DEFAULT NULL,
  `borrow_op_user` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_69nm7e7prsnqewuu40n7amc5l`(`borrow_op_user`) USING BTREE,
  INDEX `FK_7c32sxdyj90abl0hv370ex8av`(`goods`) USING BTREE,
  CONSTRAINT `FK_69nm7e7prsnqewuu40n7amc5l` FOREIGN KEY (`borrow_op_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_7c32sxdyj90abl0hv370ex8av` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_goods_copy
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_copy`;
CREATE TABLE `wms_goods_copy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NULL DEFAULT NULL,
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `borrow_time_limit` int(11) NULL DEFAULT NULL,
  `examine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `measurement_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `rfid_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `manufacturer` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  `high_remind` int(11) NULL DEFAULT NULL,
  `low_remind` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `pallet_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_in` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_out` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sku_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit_volume` double NULL DEFAULT NULL,
  `unit_weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_nub2sfnoykhimc8fpplk5r5ak`(`category`) USING BTREE,
  INDEX `FK_g7hfoj7es9vkjxloq28keofvw`(`manufacturer`) USING BTREE,
  INDEX `FK_h6w8fnm85cip8tfv2elyv33g4`(`warehouse`) USING BTREE,
  INDEX `FK_ffqf93b8bbjplsu4ub3ol6byu`(`create_user`) USING BTREE,
  INDEX `FK_75rdcupoq5u3ir36y0binq4wu`(`update_user`) USING BTREE,
  CONSTRAINT `wms_goods_copy_ibfk_1` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_goods_copy_ibfk_2` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods_copy
-- ----------------------------
INSERT INTO `wms_goods_copy` VALUES (1, 30, '789654524', 0, '', '一种沐浴露', '箱', 'Dove多芬', 0, '4E0FA9475BE4BACB0824', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2018-02-27 17:41:36', NULL, 1, '4', NULL, NULL, NULL, 2.5, 2);
INSERT INTO `wms_goods_copy` VALUES (2, 1, '789654525', 0, '', '一种沐浴露', '箱', 'Johnson强生婴儿', 59.9, '4A85B86038B6528B3536', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '4', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (3, 28, '789654526', 0, '', '一种沐浴露', '箱', 'LUX力士', 0, '4C499F820C788F7F77FD', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2018-02-27 14:23:14', NULL, 1, '5', NULL, NULL, NULL, 2, 3);
INSERT INTO `wms_goods_copy` VALUES (4, 34, '789654527', 0, '', '一种沐浴露', '箱', 'Safeguard舒肤佳', 59.9, '4176B05EC3B6018415F3', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '6', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (5, 108, '789654528', 0, '', '一种沐浴露', '箱', 'OLAY玉兰油', 59.9, '4C2A961E6F5CE79C1BEB', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '3', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (6, -12, '789654529', 0, '', '一种沐浴露', '箱', '六神', 59.9, '4576AEDF6E2AFAE5B15E', '10瓶/箱', '1', NULL, NULL, 8, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '2', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (7, 24, '789654530', 0, '', '一种沐浴露', '箱', 'NIVEA妮维雅', 59.9, '434BB0F3BF042245FCC4', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '4', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (8, 0, '789654531', 0, '', '一种沐浴露', '箱', 'LYNX凌仕', 59.9, '4124B0F598D91062127A', '10瓶/箱', '1', NULL, NULL, 10, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '3', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (9, 26, '789654532', 0, '', '一种沐浴露', '箱', '威露士Walch', 59.9, '4920AB46597AB0B0D09D', '10瓶/箱', '1', NULL, NULL, NULL, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '3', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (10, 9, '789654533', 0, '', '一种沐浴露', '箱', '阿迪达斯Adidas', 59.9, '4D308B35D764EA7CE941', '10瓶/箱', '1', NULL, NULL, 9, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '2', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (11, 0, '789654534', 0, '', '一种饮料', '板', '哇哈哈', 1.2, '4905A87338559BB7318D', '4罐/板', '1', NULL, NULL, 1, 0, 0, NULL, '2017-11-07 11:31:01', NULL, 1, '5', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (33, 0, '2018011444B94FB2900D', 0, NULL, '好吃', '箱', '苹果', 0, '2018011444B94FB2900D', '10千克/箱', '1', NULL, NULL, 3, 100000000, 1, '2018-01-20 14:27:15', NULL, 1, NULL, '1', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (34, 0, '2018017B7C894DA4B1E8', 0, NULL, '一种白酒', '箱', '瓦岗包谷酒', 0, '2018017B7C894DA4B1E8', '10瓶/箱', '1', NULL, NULL, 7, 100000000, 0, '2018-01-30 11:44:15', NULL, 1, NULL, '4', NULL, NULL, NULL, 0, 0);
INSERT INTO `wms_goods_copy` VALUES (35, 0, '2018029329E44EEFA2F0', 0, NULL, '不错', '箱', '红花郎', 0, '2018029329E44EEFA2F0', '20瓶/箱', '1', NULL, NULL, 9, 100000000, 0, '2018-02-08 09:14:33', NULL, 1, NULL, '1', NULL, NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for wms_goods_in_storage
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_in_storage`;
CREATE TABLE `wms_goods_in_storage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `costTypeId` int(11) NULL DEFAULT NULL,
  `customer_orderNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_op_date` datetime(0) NULL DEFAULT NULL,
  `in_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_pallet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `warehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `warehouseCoding` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `where_from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apply_in_user` int(11) NULL DEFAULT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  `in_op_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_enc8nvhsdraf1x3f33kcp3w3i`(`apply_in_user`) USING BTREE,
  INDEX `FK_oulh8iw6qc1acdvev7f1p03ja`(`customer_id`) USING BTREE,
  INDEX `FK_iikorrciuxhd1n5fuwgg2kyxe`(`in_op_user`) USING BTREE,
  CONSTRAINT `FK_enc8nvhsdraf1x3f33kcp3w3i` FOREIGN KEY (`apply_in_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_iikorrciuxhd1n5fuwgg2kyxe` FOREIGN KEY (`in_op_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_oulh8iw6qc1acdvev7f1p03ja` FOREIGN KEY (`customer_id`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods_in_storage
-- ----------------------------
INSERT INTO `wms_goods_in_storage` VALUES (1, 'CHQ.BLN', 3, 'C021A-X07366156S01', NULL, NULL, '成都基地', '2018-07-05 00:00:00', 'BJ1805160057', '重庆宝洛尼', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '正单', '拿破仑床踏_D面料(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (2, 'LPS', 17, 'C021A-R04501611D01', NULL, NULL, '成都基地', '2018-07-06 00:00:00', 'BJ1805160057', '六盘水', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '正单', '内门通用制造成品1(10)', NULL, 2, 2.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (3, 'BAZ', 7, 'C021A-X10932071S01', NULL, NULL, '成都基地', '2018-07-07 00:00:00', 'BJ1805160057', '巴中1', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '正单', '阿卡迪亚床_150_D面料(10)', NULL, 2, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (4, 'CDU', 13, 'C021A-N10917241D01', NULL, NULL, '成都基地', '2018-07-08 00:00:00', 'BJ1805160057', '成都', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '正单', '内门通用制造成品1(10)', NULL, 2, 2.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (5, 'DEY', 1, 'C021A-X10572731S01-SP1', NULL, NULL, '成都基地', '2018-07-09 00:00:00', 'BJ1805160057', '德阳', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '索赔反补单', '图尔B床_180_C面料(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (6, 'DJY', 1, 'C021A-X01094231S01-SP2', NULL, NULL, '成都基地', '2018-07-10 00:00:00', 'BJ1805160057', '都江堰', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '索赔反补单', '亨特床_180_P级(3.15全屋定制开年惠20170301)(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (7, 'LES', 3, 'C021A-R03303881D02', NULL, NULL, '成都基地', '2018-07-11 00:00:00', 'BJ1805160057', '乐山', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '正单', '内门通用制造成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (8, 'MYA.CHQ', 42, 'C021A-S10258471F01', NULL, NULL, '成都基地', '2018-07-12 00:00:00', 'BJ1805160057', '绵阳城区', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 4.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (9, 'NAC', 9, 'C021A-N10931931K01', NULL, NULL, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (10, 'PZH', 2, 'C021A-H10878171F02', NULL, NULL, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (11, 'XIC', 4, 'C021A-X10862001S01', NULL, NULL, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (12, 'YIB', 1, 'C021A-S03081261F03', NULL, NULL, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (13, 'ZGO', 6, 'C021A-X10257461S01', NULL, NULL, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (14, 'MSH', 11, 'C021A-R10836981K01', NULL, NULL, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (15, 'SNG', 62, 'C021A-S10796921F01', NULL, NULL, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 6.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (16, 'LSH', 8, 'C021A-R07188621D01', NULL, NULL, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', '2018-08-17 11:16:46', NULL, '0', NULL, '正单', '内门通用制造成品1(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (17, 'LZS', 20, 'C021A-N00951171D01', NULL, NULL, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', NULL, '0', NULL, '正单', '内门通用制造成品1(10)', NULL, 2, 3.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (18, 'CDU.DHB', 1, 'C021A-T09206731W02', NULL, NULL, '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (19, 'ZUY', 7, 'C021A-N00014311F06', NULL, NULL, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (20, 'YBN', 2, 'C021A-S06359901F04', NULL, NULL, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (21, 'NEJ', 1, 'C021A-P07139621D01-SP1', NULL, NULL, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', NULL, '0', NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (22, 'BIJ', 2, 'C021A-S07241571F03', NULL, NULL, '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (23, 'CHQ.BSH', 14, 'C021A-N04501611W01', NULL, NULL, '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (24, 'XIY', 1, 'C021A-X72416501E01-SP1', NULL, NULL, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', NULL, '0', NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (25, 'KLI', 19, 'C021A-N08973241W01', NULL, NULL, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (26, 'CHQ.YCH', 23, 'C021A-S10743171F01', NULL, NULL, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 2.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (27, 'CHQ.WZH', 1, 'C021A-R10817911F01-SP1', NULL, NULL, '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', NULL, '0', NULL, '索赔反补单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (28, 'REH', 19, 'C021A-N06138701K01', NULL, NULL, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (29, 'CDDJS', 6, 'C021A-S09263411K01', NULL, NULL, '长沙DC', '2018-08-02 00:00:00', 'BJ1805160057', '成都大觉寺', '灵鹤直发', '箱体板类', '2018-08-17 11:16:34', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (30, 'QXI', 1, 'C021A-T07083731K01', NULL, NULL, '长沙DC', '2018-08-03 00:00:00', 'BJ1805160057', '黔西', '灵鹤直发', '箱体板类', '2018-08-17 11:16:34', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (31, 'GUA', 1, 'C021A-T07244071K01', NULL, NULL, '广州DC', '2018-08-04 00:00:00', 'BJ1805160057', '广安', '灵鹤直发', '箱体板类', '2018-08-17 11:16:34', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (32, 'CHQ.JJN', 2, 'C021A-R06744901K01', NULL, NULL, '广州DC', '2018-08-05 00:00:00', 'BJ1805160057', '江津', '灵鹤直发', '箱体板类', '2018-08-17 11:16:34', NULL, '0', NULL, '正单', '博洛尼厨用龙头K12(70)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (33, 'DYU', 7, 'C021A-X10836981S01', NULL, NULL, '广州DC', '2018-08-06 00:00:00', 'BJ1805160057', '都匀', '灵鹤直发', '箱体板类', '2018-08-17 11:16:34', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (34, 'MES', 5, 'C021A-S10934401W01', NULL, NULL, '广州DC', '2018-08-07 00:00:00', 'BJ1805160057', '眉山博洛尼', '灵鹤直发', '箱体板类', '2018-08-17 11:16:33', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (35, 'XIS', 48, 'C021A-H10796151F01', NULL, NULL, '广州DC', '2018-08-08 00:00:00', 'BJ1805160057', '习水', '灵鹤直发', '箱体板类', '2018-08-17 11:16:33', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 5.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (36, 'CHQ.DZU', 2, 'C021A-S04026991F01', NULL, NULL, '广州DC', '2018-08-09 00:00:00', 'BJ1805160057', '大足', '灵鹤直发', '箱体板类', '2018-08-17 11:16:33', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (37, 'WAN', 5, 'C021A-S08049051W03', NULL, NULL, '广州DC', '2018-08-10 00:00:00', 'BJ1805160057', '瓮安', '灵鹤直发', '箱体板类', '2018-08-17 11:16:33', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (38, 'QIL', 1, 'C412A-X05530601S01', NULL, NULL, '广州DC', '2018-08-11 00:00:00', 'BJ1803230025', '邛崃', '吉顺隆物流', '箱体板类', '2018-08-17 11:16:33', NULL, '0', NULL, '正单', '圣保罗扶手餐椅D67深棕色(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (39, 'DJY.BLN', 2, 'C412A-T05530601K01', NULL, NULL, '广州DC', '2018-08-12 00:00:00', 'BJ1803230025', '都江堰博洛尼', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '正单', '活力套装刀具(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (40, 'GAY', 1, 'Z412A01H-180002-A1', NULL, NULL, '广州DC', '2018-08-13 00:00:00', 'BJ1803230025', '广元', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '正单', '圣保罗扶手餐椅D67深棕色_1件1包_1_1_1', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (41, 'CQI', 1, 'C412-11K-169004-A1-YB-SP2', NULL, NULL, '南京DC', '2018-08-14 00:00:00', 'BJ1803230025', '重庆博洛尼', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '索赔反补单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (42, 'LZS.BLN', 1, 'C412-11K-169003-A1-SP2', NULL, NULL, '南京DC', '2018-08-15 00:00:00', 'BJ1803230025', '泸州博洛尼', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '索赔反补单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (43, 'REH.BLN', 1, 'C412-11W-170033-A1-SP1', NULL, NULL, '南京DC', '2018-08-16 00:00:00', 'BJ1803230025', '仁怀博洛尼', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '索赔反补单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (44, 'MET', 1, 'C412-11K-169003-A1-SP3', NULL, NULL, '南京DC', '2018-08-17 00:00:00', 'BJ1803230025', '湄潭', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '索赔反补单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (45, 'XIS.BLN', 1, 'C412-11K-169004-A1-YB-SP1', NULL, NULL, '南京DC', '2018-08-18 00:00:00', 'BJ1803230025', '习水－冯秀国', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '索赔反补单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (46, 'GYA', 5, 'C520-11W-170122-A1', NULL, NULL, '南京DC', '2018-08-19 00:00:00', 'BJ1803200014', '广元-华尔美', '灵鹤调拨', '箱体板类', '2018-08-17 11:09:58', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (47, 'CHQ.JAJ', 10, 'C520-11F-170126-A1', NULL, NULL, '南京DC', '2018-08-20 00:00:00', 'BJ1803200014', '江津-李国', '灵鹤调拨', '箱体板类', '2018-08-17 11:09:57', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (48, 'BIJ.BLN', 2, 'C520-11F-170129-A1', NULL, NULL, '南京DC', '2018-08-21 00:00:00', 'BJ1803200014', '毕节-罗运光', '灵鹤调拨', '箱体板类', '2018-08-17 11:09:57', NULL, '0', NULL, '正单', '家具生产通用成品2(10)', NULL, 2, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (49, 'GUH', 13, 'C520-11K-170122-A1', NULL, NULL, '南京DC', '2018-08-22 00:00:00', 'BJ1803200014', '广汉-蔡春', '灵鹤调拨', '箱体板类', '2018-07-16 18:09:54', NULL, '0', NULL, '正单', '厨卫生产通用成品1(10)', NULL, 2, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_in_storage` VALUES (51, 'ANH', 1, '013413540', NULL, NULL, '重庆DC', '2018-08-20 09:34:47', '013413540', '湖南省益阳市安化县东坪镇南区金桥建材家居城101-103号门面龙鑫装饰', '康程物流', '正品', '2018-08-20 09:35:26', NULL, '0', NULL, '正单', '正品', NULL, 2, 0.30, NULL, '001', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_goods_in_storage_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_in_storage_detail`;
CREATE TABLE `wms_goods_in_storage_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `add_amount` int(11) NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `goods_in_storage` int(11) NULL DEFAULT NULL,
  `stock_id` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  `sum_volume` double NULL DEFAULT NULL,
  `sum_weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_3v0858l4gboy48jd3jd2yj1or`(`goods`) USING BTREE,
  INDEX `FK_ei3sosdaxmeli7ctvn039swu0`(`warehouse`) USING BTREE,
  INDEX `FK_tng4tfocbmtrssxgeqvxpqoi2`(`goods_in_storage`) USING BTREE,
  INDEX `FK_t27wms50gvho078hgjhj8piek`(`stock_id`) USING BTREE,
  CONSTRAINT `FK_3v0858l4gboy48jd3jd2yj1or` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ei3sosdaxmeli7ctvn039swu0` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods_in_storage_detail
-- ----------------------------
INSERT INTO `wms_goods_in_storage_detail` VALUES (11, 10, 0, '多芬入库10件', 2, 1, 12, 13, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (12, 10, 0, '六神入库10箱', 2, 6, 13, 16, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (13, 2, 0, '多芬入库2箱', 1, 1, 14, NULL, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (14, 10, 0, '', 1, 1, 15, NULL, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (15, 10, 0, '', 1, 3, 15, NULL, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (16, 20, 0, '瓦岗包谷酒入库20吨', 2, 34, 16, 19, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (17, 0, 0, '', 1, NULL, 17, NULL, 3, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (18, 10, 0, '', 1, 1, 18, NULL, 6, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (19, 20, 0, '生活用品', 2, 2, 19, 21, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (20, 15, 0, '生活用品', 2, 4, 19, 22, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (21, 2, 0, '一批茶叶', 2, 5, 20, 24, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (22, 10, 0, '苹果', 2, 33, 21, 23, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (23, 11, 0, '', 2, 8, 22, 25, 6, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (24, 10, 0, '', 1, 1, 23, NULL, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (25, 20, 0, '', 2, 3, 24, 29, 5, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (26, 10, 0, '', 2, 3, 25, 28, 1, 20, 30);
INSERT INTO `wms_goods_in_storage_detail` VALUES (27, 5, 0, '', 2, 3, 26, 30, 1, 10, 15);
INSERT INTO `wms_goods_in_storage_detail` VALUES (28, 10, 0, '从新都区入库10箱威露士', 2, 9, 27, 31, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (29, 50, 0, '从四川德阳入库红花郎50箱', 2, 35, 28, 59, 10, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (30, 50, 0, '从新都区入库50箱娃哈哈', 2, 11, 29, 63, 10, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (31, 50, 0, '从武侯区入库50箱阿迪达斯', 2, 10, 30, 69, 4, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (32, 1, 0, '', 1, 2, 31, NULL, 1, 0, 0);
INSERT INTO `wms_goods_in_storage_detail` VALUES (33, 1, 0, '', 1, 3, 32, NULL, 1, 2, 3);
INSERT INTO `wms_goods_in_storage_detail` VALUES (34, 2, 0, '', 1, 1, 33, NULL, 1, 5, 4);

-- ----------------------------
-- Table structure for wms_goods_out_storage
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_out_storage`;
CREATE TABLE `wms_goods_out_storage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accept_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `arrive_date` datetime(0) NULL DEFAULT NULL,
  `business_date` datetime(0) NULL DEFAULT NULL,
  `car_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cusName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detailed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `driver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ke_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_amount` int(11) NULL DEFAULT NULL,
  `out_date` datetime(0) NULL DEFAULT NULL,
  `out_op_date` datetime(0) NULL DEFAULT NULL,
  `out_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bpickup_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salesOrderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_date` datetime(0) NULL DEFAULT NULL,
  `send_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shipVia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `warehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` decimal(19, 2) NULL DEFAULT NULL,
  `where_go` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apply_out_user` int(11) NULL DEFAULT NULL,
  `customer` int(11) NULL DEFAULT NULL,
  `out_op_user` int(11) NULL DEFAULT NULL,
  `printNum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_qrhoh29blxi4pu3gqp3ffx1kl`(`apply_out_user`) USING BTREE,
  INDEX `FK_pf2016fp2w6lqotwadt7oft2e`(`customer`) USING BTREE,
  INDEX `FK_6r6943ypifgwpvaj0tvdlskwg`(`out_op_user`) USING BTREE,
  CONSTRAINT `FK_6r6943ypifgwpvaj0tvdlskwg` FOREIGN KEY (`out_op_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_pf2016fp2w6lqotwadt7oft2e` FOREIGN KEY (`customer`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_qrhoh29blxi4pu3gqp3ffx1kl` FOREIGN KEY (`apply_out_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods_out_storage
-- ----------------------------
INSERT INTO `wms_goods_out_storage` VALUES (5, NULL, NULL, 'CDU', 7, NULL, NULL, NULL, '吉顺隆黑龙江线', NULL, 'C028A-N08574741D01', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300346', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808090001', '正单', NULL, NULL, '2018-08-17 11:33:47', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (6, NULL, NULL, 'CHQ.BSH', 2, NULL, NULL, NULL, '晨速物流', NULL, 'C023B-N06757091F01', '璧山', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', NULL, NULL, '重庆市璧山区大宏鼎家居建材城2层', 'DC自提', '生产包装', NULL, 'ADCK201808100001', '正单', NULL, NULL, '2018-08-17 11:33:47', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (18, NULL, NULL, 'CDU', 1, NULL, NULL, NULL, '万达物流', NULL, 'C028A-X10914751S01', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '库存品包装', NULL, 'ADCK201808160003', '正单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (19, NULL, NULL, 'CDU', 4, NULL, NULL, NULL, '万达物流', NULL, 'C028A-X11189321S01', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '库存品包装', NULL, 'ADCK201808160003', '正单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1.50, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (20, NULL, NULL, 'CDU', 2, NULL, NULL, NULL, '万达物流', NULL, 'C028A-N10942951F02-SP1', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300346', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808160003', '索赔反补单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (21, NULL, NULL, 'CDU', 27, NULL, NULL, NULL, '万达物流', NULL, 'C028A-N10678422K01', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808160003', '正单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1.15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (22, NULL, NULL, 'CDU', 3, NULL, NULL, NULL, '万达物流', NULL, 'C028A-N07496871D01', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808160003', '正单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (24, NULL, NULL, 'CDU', 31, NULL, NULL, NULL, '万达物流', NULL, 'C028A-N03905771D02', '成都', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808160003', '正单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 3.70, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (25, NULL, NULL, 'CHQ.WZH', 15, NULL, NULL, NULL, '万达物流', NULL, 'C023C-P11265291K01', '万州', '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', NULL, NULL, '重庆万州区万川大道302号附1号6-1', 'DC自提', '生产包装', NULL, 'ADCK201808160004', '正单', NULL, NULL, '2018-08-20 09:42:38', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 0.55, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (26, NULL, NULL, 'DJY.BLN', 2, NULL, NULL, NULL, '大田物流', NULL, 'C412A-T05530601K01', '都江堰博洛尼', '广州DC', '2018-08-12 00:00:00', 'BJ1803230025', NULL, NULL, '都江堰博洛尼', '吉顺隆物流', '箱体板类', NULL, 'HDO20180817570093', '正单', NULL, NULL, '2018-08-20 09:44:08', NULL, '活力套装刀具(10)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (28, NULL, NULL, 'CAN', 27, NULL, NULL, NULL, '上海益递物流', NULL, 'C577C-M11147191F04', NULL, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', NULL, NULL, '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', NULL, 'ADCK201808140001', '正单', NULL, NULL, '2018-08-17 17:39:46', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 2.18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_goods_out_storage` VALUES (29, NULL, NULL, 'CDU', 16, NULL, NULL, NULL, 'DC自提', NULL, 'C028A-P03905772F12', NULL, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300346', NULL, NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808090002', '正单', NULL, NULL, '2018-08-17 17:39:46', NULL, '箱体板类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1.60, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_goods_out_storage_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_out_storage_detail`;
CREATE TABLE `wms_goods_out_storage_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `outCustom` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remove_amount` int(11) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `goods_out_storage` int(11) NULL DEFAULT NULL,
  `stock_id` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  `sum_volume` double NULL DEFAULT NULL,
  `sum_weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_fhd36yjusm0jrh0x8esud9ti2`(`goods`) USING BTREE,
  INDEX `FK_mv7k76n3hhx4s73dvcxqnw73x`(`stock_id`) USING BTREE,
  INDEX `FK_ngv5dbwg6nmpj7hr1cg704ww1`(`warehouse`) USING BTREE,
  INDEX `FK_ygjk15n43jws1d2d7b58fbqi`(`goods_out_storage`) USING BTREE,
  CONSTRAINT `FK_fhd36yjusm0jrh0x8esud9ti2` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ngv5dbwg6nmpj7hr1cg704ww1` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods_out_storage_detail
-- ----------------------------
INSERT INTO `wms_goods_out_storage_detail` VALUES (2, '0', '', 5, 1, 6, 1, NULL, 10, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (3, '0', '', 5, 1, 6, 2, NULL, 6, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (4, '0', '', 10, 1, 34, 2, NULL, 6, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (5, '0', '过年了，出库喝酒内部聚餐', 3, 1, 34, 3, NULL, 1, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (6, '1', '', 1, 1, 1, 4, NULL, 1, 2.5, 2);
INSERT INTO `wms_goods_out_storage_detail` VALUES (7, '1', '出库30箱到龙泉驿', 30, 2, 35, 5, NULL, 9, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (8, '1', '出库20箱到新都区', 20, 2, 35, 6, NULL, 9, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (9, '1', '3箱运往龙泉驿', 3, 2, 1, 7, NULL, 1, 7.5, 6);
INSERT INTO `wms_goods_out_storage_detail` VALUES (10, '1', '22箱运往成都龙泉驿', 22, 2, 1, 8, NULL, 5, 55, 44);
INSERT INTO `wms_goods_out_storage_detail` VALUES (11, '1', '运往成都龙泉驿', 10, 2, 1, 9, NULL, 5, 25, 20);
INSERT INTO `wms_goods_out_storage_detail` VALUES (12, '1', '5箱运往成都龙泉驿', 5, 2, 1, 10, NULL, 5, 12.5, 10);
INSERT INTO `wms_goods_out_storage_detail` VALUES (13, '1', '运往北京', 10, 2, 10, 11, NULL, 7, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (14, '1', '运往北京', 10, 2, 10, 12, NULL, 10, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (15, '1', '', 10, 2, 10, 13, NULL, 7, 0, 0);
INSERT INTO `wms_goods_out_storage_detail` VALUES (16, '1', '', 10, 2, 10, 14, NULL, 10, 0, 0);

-- ----------------------------
-- Table structure for wms_goods_send
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_send`;
CREATE TABLE `wms_goods_send`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cost_delivery` double NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `examine_op_date` datetime(0) NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `productName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receive_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receive_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receive_man_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_begin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_date` datetime(0) NULL DEFAULT NULL,
  `send_department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_linkman` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `transfer_op_date` datetime(0) NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `examine_op_user` int(11) NULL DEFAULT NULL,
  `transfer_op_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_feq6nuuyrqswj6db7rn0hkaju`(`examine_op_user`) USING BTREE,
  INDEX `FK_4t4ognu4vxe3wjbcenkrgpfk2`(`transfer_op_user`) USING BTREE,
  CONSTRAINT `FK_4t4ognu4vxe3wjbcenkrgpfk2` FOREIGN KEY (`transfer_op_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_feq6nuuyrqswj6db7rn0hkaju` FOREIGN KEY (`examine_op_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goods_send
-- ----------------------------
INSERT INTO `wms_goods_send` VALUES (1, 'CDU', NULL, 7, '吉顺隆黑龙江线', 'C028A-N08574741D01', 0, '成都', '2018-07-30 00:00:00', NULL, '翡翠城四期8-1-3001', 'DC自提', '生产包装', 'ADCK201808090001', '正单', NULL, '箱体板类', 0, '橱柜', '成都市簇桥乡三河村三组302号', '杨勇', '13088029955', NULL, NULL, NULL, '2018-08-17 11:34:29', NULL, NULL, NULL, NULL, 'RDC自提', 'ADFY201808170001', '15184407711', 1, NULL, 1.20, NULL, NULL);
INSERT INTO `wms_goods_send` VALUES (2, 'CHQ.BSH', NULL, 2, '晨速物流', 'C023B-N06757091F01', 0, '成都', '2018-07-30 00:00:00', NULL, '重庆市璧山区大宏鼎家居建材城2层', 'DC自提', '生产包装', 'ADCK201808100001', '正单', NULL, '箱体板类', 0, '橱柜', '重庆市璧山区大宏鼎家居建材城2层', '李润梅', '18523418500', NULL, NULL, NULL, '2018-08-17 11:34:29', NULL, NULL, NULL, NULL, '货站自提', 'ADFY201808170002', '15184407711', 1, NULL, 0.20, NULL, NULL);
INSERT INTO `wms_goods_send` VALUES (3, 'CAN', NULL, 27, '上海益递物流', 'C577C-M11147191F04', 0, '杭州', '2018-07-31 00:00:00', NULL, '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', 'ADCK201808140001', '正单', NULL, '箱体板类', 0, '橱柜', '浙江省苍南县灵溪镇山海小区2-17栋', '陈岳用', '13819795816', NULL, NULL, NULL, '2018-08-20 10:08:23', NULL, NULL, NULL, NULL, '送货', 'ADFY201808200001', '15184407711', 1, NULL, 2.18, NULL, NULL);

-- ----------------------------
-- Table structure for wms_goods_transfer_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_transfer_detail`;
CREATE TABLE `wms_goods_transfer_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `transfer_amount` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `goods_transfer` int(11) NULL DEFAULT NULL,
  `new_warehouse` int(11) NULL DEFAULT NULL,
  `old_warehouse` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_qniejk177j36975eke2ft9ul`(`goods`) USING BTREE,
  INDEX `FK_6v5oridafh8lfwww2ixsrptsp`(`goods_transfer`) USING BTREE,
  INDEX `FK_ey78y1cbre4cgcey735v29v5b`(`new_warehouse`) USING BTREE,
  INDEX `FK_stgeyfex0nrn713wevgc2yxup`(`old_warehouse`) USING BTREE,
  CONSTRAINT `FK_ey78y1cbre4cgcey735v29v5b` FOREIGN KEY (`new_warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_qniejk177j36975eke2ft9ul` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stgeyfex0nrn713wevgc2yxup` FOREIGN KEY (`old_warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_goods_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_warehouse`;
CREATE TABLE `wms_goods_warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_h237cqvgfg4vw3ul0w78s4ipe`(`goods`) USING BTREE,
  INDEX `FK_c9x4gue7nykbwuipnr1kk3dee`(`warehouse`) USING BTREE,
  CONSTRAINT `FK_c9x4gue7nykbwuipnr1kk3dee` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_h237cqvgfg4vw3ul0w78s4ipe` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_goodstype
-- ----------------------------
DROP TABLE IF EXISTS `wms_goodstype`;
CREATE TABLE `wms_goodstype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodstype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_goodstype
-- ----------------------------
INSERT INTO `wms_goodstype` VALUES (1, '2018-08-20 11:13:55', '', '箱体板类', '');
INSERT INTO `wms_goodstype` VALUES (2, '2018-08-20 14:27:15', '', '库存品包装', '');
INSERT INTO `wms_goodstype` VALUES (3, '2018-08-20 14:27:53', '', '生产包装', '');

-- ----------------------------
-- Table structure for wms_in_detail_stock_pallet
-- ----------------------------
DROP TABLE IF EXISTS `wms_in_detail_stock_pallet`;
CREATE TABLE `wms_in_detail_stock_pallet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_pallet` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `p_capacity` int(11) NULL DEFAULT NULL,
  `p_overplus` int(11) NULL DEFAULT NULL,
  `p_upodate_date` datetime(0) NULL DEFAULT NULL,
  `pallet_id` int(11) NULL DEFAULT NULL,
  `pallet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pallet_rfid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_storage_detail` int(11) NULL DEFAULT NULL,
  `p_goods` int(11) NULL DEFAULT NULL,
  `p_upodate_user` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_bmrclxxgbyu1yucyvh6qmvwdu`(`in_storage_detail`) USING BTREE,
  INDEX `FK_m9gow6ojgufao4ebqoeu4sm3u`(`p_goods`) USING BTREE,
  INDEX `FK_r3gdpre6ps78x3jx47vd8aeof`(`p_upodate_user`) USING BTREE,
  INDEX `FK_oqeeof0m74x8nt11q0lydgpxl`(`stock`) USING BTREE,
  CONSTRAINT `FK_bmrclxxgbyu1yucyvh6qmvwdu` FOREIGN KEY (`in_storage_detail`) REFERENCES `wms_goods_in_storage_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_m9gow6ojgufao4ebqoeu4sm3u` FOREIGN KEY (`p_goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_r3gdpre6ps78x3jx47vd8aeof` FOREIGN KEY (`p_upodate_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_inventory
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory`;
CREATE TABLE `wms_inventory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inventory_date` datetime(0) NULL DEFAULT NULL,
  `is_pallet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `pallet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_9jjw51lera85oh2cmcibmqli`(`goods`) USING BTREE,
  CONSTRAINT `FK_9jjw51lera85oh2cmcibmqli` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `wms_manufacturer`;
CREATE TABLE `wms_manufacturer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_message
-- ----------------------------
DROP TABLE IF EXISTS `wms_message`;
CREATE TABLE `wms_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `btAryData` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btAryTranData` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btCheck` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btCmd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btDataLen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btPacketType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btReadId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `orderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_module
-- ----------------------------
DROP TABLE IF EXISTS `wms_module`;
CREATE TABLE `wms_module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `op_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent` int(11) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_toeneekv37mcs1rlmhdejq288`(`create_user`) USING BTREE,
  INDEX `FK_tp9mpkibunlsbam1kjn1dl5vs`(`update_user`) USING BTREE,
  INDEX `FK_4rvh7qp8cv5yvxsya8r82h7lv`(`parent`) USING BTREE,
  CONSTRAINT `FK_toeneekv37mcs1rlmhdejq288` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tp9mpkibunlsbam1kjn1dl5vs` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_module
-- ----------------------------
INSERT INTO `wms_module` VALUES (1, '2017-10-23 09:58:29', '0', '入库管理', NULL, '1', '2017-10-23 09:58:44', NULL, 1, NULL, '', NULL, '/images/icon/in.png', NULL, NULL);
INSERT INTO `wms_module` VALUES (2, '2017-10-23 09:59:49', '0', '出库管理', NULL, '2', '2017-10-23 09:59:59', NULL, 1, NULL, NULL, NULL, '/images/icon/out.png', NULL, NULL);
INSERT INTO `wms_module` VALUES (3, '2017-10-23 10:00:12', '0', '库存管理', NULL, '3', NULL, NULL, 1, NULL, NULL, NULL, '/images/icon/warehouse.png', NULL, NULL);
INSERT INTO `wms_module` VALUES (4, '2017-10-23 10:00:44', '0', '托运发货', NULL, '4', NULL, NULL, 1, NULL, NULL, NULL, '/images/icon/transfer1.png', NULL, NULL);
INSERT INTO `wms_module` VALUES (5, '2017-10-23 10:02:08', '0', '个人中心', '管理员', '5', NULL, NULL, 1, NULL, NULL, NULL, '/images/icon/person.png', NULL, NULL);
INSERT INTO `wms_module` VALUES (6, '2017-10-23 10:02:25', '0', '系统设置', '', NULL, '2018-07-04 13:06:02', '', 1, 1, NULL, NULL, '/images/icon/setting.png', NULL, '');
INSERT INTO `wms_module` VALUES (7, '2017-10-23 10:03:03', '1', '填单入库', '', NULL, '2018-01-27 16:12:08', '/goodsIn/toApply', 1, 1, NULL, NULL, NULL, 1, '');
INSERT INTO `wms_module` VALUES (8, '2017-10-23 10:03:07', '1', '入库列表', NULL, NULL, NULL, '/goodsIn/toList', 1, NULL, 'goodsIn', 'view', NULL, 1, NULL);
INSERT INTO `wms_module` VALUES (10, '2017-10-24 20:45:46', '1', '填单出库', '', NULL, '2018-01-27 16:12:08', '/goodsOut/toApply', 1, 1, NULL, NULL, NULL, 2, '页面跳转');
INSERT INTO `wms_module` VALUES (14, '2017-10-24 20:52:28', '1', '出库列表', '', NULL, NULL, '/goodsOut/toList', 1, NULL, NULL, NULL, NULL, 2, '页面跳转');
INSERT INTO `wms_module` VALUES (16, '2017-10-24 20:52:28', '1', '库存清单', '屏蔽掉', NULL, '2017-11-03 10:27:23', '/stock/toList', 1, 1, NULL, NULL, NULL, 3, '页面跳转');
INSERT INTO `wms_module` VALUES (17, '2017-10-24 20:52:28', '1', '填单发货', '', NULL, '2018-02-01 15:24:46', '/goodsSend/toApply', 1, 1, NULL, NULL, NULL, 4, '页面跳转');
INSERT INTO `wms_module` VALUES (18, '2017-10-24 20:52:28', '1', '发货列表', '', NULL, NULL, '/goodsSend/toList', 1, NULL, NULL, NULL, NULL, 4, '页面跳转');
INSERT INTO `wms_module` VALUES (20, '2017-10-25 09:05:11', '1', '个人资料', '管理员', NULL, NULL, '/user/toPersonalInfo', 1, NULL, NULL, NULL, NULL, 5, '页面跳转');
INSERT INTO `wms_module` VALUES (21, '2017-10-25 09:05:11', '1', '修改密码', '管理员', NULL, NULL, '/user/toEditPass', 1, NULL, NULL, NULL, NULL, 5, '页面跳转');
INSERT INTO `wms_module` VALUES (22, '2017-10-25 09:05:11', '1', '员工管理', '', NULL, '2017-10-31 14:52:26', '/user/toList', 1, 1, NULL, NULL, NULL, 6, '页面跳转');
INSERT INTO `wms_module` VALUES (23, '2017-10-25 09:05:11', '1', '角色管理', '', NULL, NULL, '/system/role/toList', 1, NULL, NULL, NULL, NULL, 6, '页面跳转');
INSERT INTO `wms_module` VALUES (24, '2017-10-25 09:05:11', '1', 'DC仓库管理', '', NULL, '2017-10-25 09:05:11', '/system/department/toList', 1, 1, NULL, NULL, NULL, 6, '页面跳转');
INSERT INTO `wms_module` VALUES (25, '2017-10-25 09:05:11', '1', '模块管理', '', NULL, NULL, '/system/model/toList', 1, NULL, NULL, NULL, NULL, 6, '页面跳转');
INSERT INTO `wms_module` VALUES (27, '2017-10-25 09:05:11', '1', '参数管理', '', NULL, '2017-11-01 16:24:52', '/system/params/toList', 1, 1, NULL, NULL, NULL, 6, '页面跳转');
INSERT INTO `wms_module` VALUES (28, '2017-11-01 14:06:37', '0', '基本资料', NULL, '7', NULL, NULL, 1, NULL, NULL, NULL, '/images/icon/other.png', NULL, NULL);
INSERT INTO `wms_module` VALUES (36, '2017-12-20 09:22:27', '1', '客户管理', '客户信息', NULL, '2018-08-20 09:03:29', '/customer/toList', 1, 1, NULL, NULL, NULL, 28, '页面跳转');
INSERT INTO `wms_module` VALUES (44, '2017-12-27 16:13:25', '1', '承运商', '结算方式', NULL, '2018-07-03 18:02:21', '/carrierType/toList', 1, 1, NULL, NULL, NULL, 28, '页面跳转');
INSERT INTO `wms_module` VALUES (50, '2018-07-27 20:09:49', '1', '代理商管理', '客户', NULL, '2018-07-10 20:10:18', '/agentType/toList', 1, NULL, NULL, NULL, NULL, 28, '页面跳转');
INSERT INTO `wms_module` VALUES (51, '2018-08-17 11:01:48', '1', '订单类型管理', '类型添加', NULL, '2018-08-20 09:03:29', '/orderType/toList', 1, 1, NULL, NULL, NULL, 28, '页面跳转');
INSERT INTO `wms_module` VALUES (52, '2018-08-17 14:40:31', '1', '登录统计', '登录统计', NULL, NULL, '/system/loginNum', 1, NULL, NULL, NULL, NULL, 6, '页面跳转');
INSERT INTO `wms_module` VALUES (53, '2018-08-20 09:03:29', '1', '包装物类型管理', '包装物类型管理', NULL, NULL, '/goodsType/toList', 1, NULL, NULL, NULL, NULL, 28, '页面跳转');

-- ----------------------------
-- Table structure for wms_module_role
-- ----------------------------
DROP TABLE IF EXISTS `wms_module_role`;
CREATE TABLE `wms_module_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `module` int(11) NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_i1ol6n6drhhubf2002yodnry3`(`create_user`) USING BTREE,
  INDEX `FK_a8jtxqeon6e8wfud6iqu7958l`(`module`) USING BTREE,
  INDEX `FK_5dwyoiugdgbkcll0ffvanxode`(`role`) USING BTREE,
  INDEX `FK_sqcpgxpsdevbvbk5ch8shq8k8`(`update_user`) USING BTREE,
  CONSTRAINT `FK_5dwyoiugdgbkcll0ffvanxode` FOREIGN KEY (`role`) REFERENCES `wms_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_i1ol6n6drhhubf2002yodnry3` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_sqcpgxpsdevbvbk5ch8shq8k8` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5121 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_module_role
-- ----------------------------
INSERT INTO `wms_module_role` VALUES (4617, '2018-07-12 15:27:47', '', NULL, 1, 1, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4618, '2018-07-12 15:27:47', '', NULL, 1, 8, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4619, '2018-07-12 15:27:47', '', NULL, 1, 2, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4620, '2018-07-12 15:27:47', '', NULL, 1, 10, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4621, '2018-07-12 15:27:47', '', NULL, 1, 14, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4622, '2018-07-12 15:27:47', '', NULL, 1, 3, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4623, '2018-07-12 15:27:47', '', NULL, 1, 16, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4624, '2018-07-12 15:27:47', '', NULL, 1, 4, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4625, '2018-07-12 15:27:47', '', NULL, 1, 17, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4626, '2018-07-12 15:27:47', '', NULL, 1, 18, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4627, '2018-07-12 15:27:47', '', NULL, 1, 5, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4628, '2018-07-12 15:27:47', '', NULL, 1, 20, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4629, '2018-07-12 15:27:47', '', NULL, 1, 21, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4630, '2018-07-12 15:27:47', '', NULL, 1, 28, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4631, '2018-07-12 15:27:47', '', NULL, 1, 44, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4632, '2018-07-12 15:27:47', '', NULL, 1, 50, 10, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4633, '2018-07-12 15:27:47', '', NULL, 1, 1, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4634, '2018-07-12 15:27:47', '', NULL, 1, 8, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4635, '2018-07-12 15:27:47', '', NULL, 1, 2, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4636, '2018-07-12 15:27:47', '', NULL, 1, 10, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4637, '2018-07-12 15:27:47', '', NULL, 1, 14, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4638, '2018-07-12 15:27:47', '', NULL, 1, 3, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4639, '2018-07-12 15:27:47', '', NULL, 1, 16, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4640, '2018-07-12 15:27:47', '', NULL, 1, 4, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4641, '2018-07-12 15:27:47', '', NULL, 1, 17, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4642, '2018-07-12 15:27:47', '', NULL, 1, 18, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4643, '2018-07-12 15:27:47', '', NULL, 1, 5, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4644, '2018-07-12 15:27:47', '', NULL, 1, 20, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4645, '2018-07-12 15:27:47', '', NULL, 1, 21, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4646, '2018-07-12 15:27:47', '', NULL, 1, 28, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4647, '2018-07-12 15:27:47', '', NULL, 1, 44, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4648, '2018-07-12 15:27:47', '', NULL, 1, 50, 11, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4649, '2018-07-12 15:27:47', '', NULL, 1, 1, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4650, '2018-07-12 15:27:47', '', NULL, 1, 8, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4651, '2018-07-12 15:27:47', '', NULL, 1, 2, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4652, '2018-07-12 15:27:47', '', NULL, 1, 10, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4653, '2018-07-12 15:27:47', '', NULL, 1, 14, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4654, '2018-07-12 15:27:47', '', NULL, 1, 3, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4655, '2018-07-12 15:27:47', '', NULL, 1, 16, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4656, '2018-07-12 15:27:47', '', NULL, 1, 4, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4657, '2018-07-12 15:27:47', '', NULL, 1, 17, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4658, '2018-07-12 15:27:47', '', NULL, 1, 18, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4659, '2018-07-12 15:27:47', '', NULL, 1, 5, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4660, '2018-07-12 15:27:47', '', NULL, 1, 20, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4661, '2018-07-12 15:27:47', '', NULL, 1, 21, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4662, '2018-07-12 15:27:47', '', NULL, 1, 28, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4663, '2018-07-12 15:27:47', '', NULL, 1, 44, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4664, '2018-07-12 15:27:47', '', NULL, 1, 50, 12, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4665, '2018-07-12 15:27:47', '', NULL, 1, 1, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4666, '2018-07-12 15:27:47', '', NULL, 1, 8, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4667, '2018-07-12 15:27:47', '', NULL, 1, 2, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4668, '2018-07-12 15:27:47', '', NULL, 1, 10, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4669, '2018-07-12 15:27:47', '', NULL, 1, 14, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4670, '2018-07-12 15:27:47', '', NULL, 1, 3, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4671, '2018-07-12 15:27:47', '', NULL, 1, 16, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4672, '2018-07-12 15:27:47', '', NULL, 1, 4, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4673, '2018-07-12 15:27:47', '', NULL, 1, 17, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4674, '2018-07-12 15:27:47', '', NULL, 1, 18, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4675, '2018-07-12 15:27:47', '', NULL, 1, 5, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4676, '2018-07-12 15:27:47', '', NULL, 1, 20, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4677, '2018-07-12 15:27:47', '', NULL, 1, 21, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4678, '2018-07-12 15:27:47', '', NULL, 1, 28, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4679, '2018-07-12 15:27:47', '', NULL, 1, 44, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4680, '2018-07-12 15:27:47', '', NULL, 1, 50, 13, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4681, '2018-07-12 15:27:47', '', NULL, 1, 1, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4682, '2018-07-12 15:27:47', '', NULL, 1, 8, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4683, '2018-07-12 15:27:47', '', NULL, 1, 2, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4684, '2018-07-12 15:27:47', '', NULL, 1, 10, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4685, '2018-07-12 15:27:47', '', NULL, 1, 14, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4686, '2018-07-12 15:27:47', '', NULL, 1, 3, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4687, '2018-07-12 15:27:47', '', NULL, 1, 16, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4688, '2018-07-12 15:27:47', '', NULL, 1, 4, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4689, '2018-07-12 15:27:47', '', NULL, 1, 17, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4690, '2018-07-12 15:27:47', '', NULL, 1, 18, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4691, '2018-07-12 15:27:47', '', NULL, 1, 5, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4692, '2018-07-12 15:27:47', '', NULL, 1, 20, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4693, '2018-07-12 15:27:47', '', NULL, 1, 21, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4694, '2018-07-12 15:27:47', '', NULL, 1, 28, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4695, '2018-07-12 15:27:47', '', NULL, 1, 44, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4696, '2018-07-12 15:27:47', '', NULL, 1, 50, 14, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4697, '2018-07-12 15:27:47', '', NULL, 1, 1, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4698, '2018-07-12 15:27:47', '', NULL, 1, 8, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4699, '2018-07-12 15:27:47', '', NULL, 1, 2, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4700, '2018-07-12 15:27:47', '', NULL, 1, 10, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4701, '2018-07-12 15:27:47', '', NULL, 1, 14, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4702, '2018-07-12 15:27:47', '', NULL, 1, 3, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4703, '2018-07-12 15:27:47', '', NULL, 1, 16, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4704, '2018-07-12 15:27:47', '', NULL, 1, 4, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4705, '2018-07-12 15:27:47', '', NULL, 1, 17, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4706, '2018-07-12 15:27:47', '', NULL, 1, 18, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4707, '2018-07-12 15:27:47', '', NULL, 1, 5, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4708, '2018-07-12 15:27:47', '', NULL, 1, 20, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4709, '2018-07-12 15:27:47', '', NULL, 1, 21, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4710, '2018-07-12 15:27:47', '', NULL, 1, 28, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4711, '2018-07-12 15:27:47', '', NULL, 1, 44, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4712, '2018-07-12 15:27:47', '', NULL, 1, 50, 15, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4737, '2018-07-12 15:27:47', '', NULL, 1, 1, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4738, '2018-07-12 15:27:47', '', NULL, 1, 8, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4739, '2018-07-12 15:27:47', '', NULL, 1, 2, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4740, '2018-07-12 15:27:47', '', NULL, 1, 10, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4741, '2018-07-12 15:27:47', '', NULL, 1, 14, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4742, '2018-07-12 15:27:47', '', NULL, 1, 3, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4743, '2018-07-12 15:27:47', '', NULL, 1, 16, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4744, '2018-07-12 15:27:47', '', NULL, 1, 4, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4745, '2018-07-12 15:27:47', '', NULL, 1, 17, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4746, '2018-07-12 15:27:47', '', NULL, 1, 18, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4747, '2018-07-12 15:27:47', '', NULL, 1, 5, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4748, '2018-07-12 15:27:47', '', NULL, 1, 20, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4749, '2018-07-12 15:27:47', '', NULL, 1, 21, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4750, '2018-07-12 15:27:47', '', NULL, 1, 28, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4751, '2018-07-12 15:27:47', '', NULL, 1, 44, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4752, '2018-07-12 15:27:47', '', NULL, 1, 50, 16, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4753, '2018-07-12 15:27:47', '', NULL, 1, 1, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4754, '2018-07-12 15:27:47', '', NULL, 1, 8, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4755, '2018-07-12 15:27:47', '', NULL, 1, 2, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4756, '2018-07-12 15:27:47', '', NULL, 1, 10, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4757, '2018-07-12 15:27:47', '', NULL, 1, 14, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4758, '2018-07-12 15:27:47', '', NULL, 1, 3, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4759, '2018-07-12 15:27:47', '', NULL, 1, 16, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4760, '2018-07-12 15:27:47', '', NULL, 1, 4, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4761, '2018-07-12 15:27:47', '', NULL, 1, 17, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4762, '2018-07-12 15:27:47', '', NULL, 1, 18, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4763, '2018-07-12 15:27:47', '', NULL, 1, 5, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4764, '2018-07-12 15:27:47', '', NULL, 1, 20, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4765, '2018-07-12 15:27:47', '', NULL, 1, 21, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4766, '2018-07-12 15:27:47', '', NULL, 1, 28, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4767, '2018-07-12 15:27:47', '', NULL, 1, 44, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4768, '2018-07-12 15:27:47', '', NULL, 1, 50, 17, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4769, '2018-07-12 15:27:47', '', NULL, 1, 1, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4770, '2018-07-12 15:27:47', '', NULL, 1, 8, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4771, '2018-07-12 15:27:47', '', NULL, 1, 2, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4772, '2018-07-12 15:27:47', '', NULL, 1, 10, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4773, '2018-07-12 15:27:47', '', NULL, 1, 14, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4774, '2018-07-12 15:27:47', '', NULL, 1, 3, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4775, '2018-07-12 15:27:47', '', NULL, 1, 16, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4776, '2018-07-12 15:27:47', '', NULL, 1, 4, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4777, '2018-07-12 15:27:47', '', NULL, 1, 17, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4778, '2018-07-12 15:27:47', '', NULL, 1, 18, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4779, '2018-07-12 15:27:47', '', NULL, 1, 5, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4780, '2018-07-12 15:27:47', '', NULL, 1, 20, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4781, '2018-07-12 15:27:47', '', NULL, 1, 21, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4782, '2018-07-12 15:27:47', '', NULL, 1, 28, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4783, '2018-07-12 15:27:47', '', NULL, 1, 44, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4784, '2018-07-12 15:27:47', '', NULL, 1, 50, 18, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4785, '2018-07-12 15:27:47', '', NULL, 1, 1, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4786, '2018-07-12 15:27:47', '', NULL, 1, 8, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4787, '2018-07-12 15:27:47', '', NULL, 1, 2, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4788, '2018-07-12 15:27:47', '', NULL, 1, 10, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4789, '2018-07-12 15:27:47', '', NULL, 1, 14, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4790, '2018-07-12 15:27:47', '', NULL, 1, 3, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4791, '2018-07-12 15:27:47', '', NULL, 1, 16, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4792, '2018-07-12 15:27:47', '', NULL, 1, 4, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4793, '2018-07-12 15:27:47', '', NULL, 1, 17, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4794, '2018-07-12 15:27:47', '', NULL, 1, 18, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4795, '2018-07-12 15:27:47', '', NULL, 1, 5, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4796, '2018-07-12 15:27:47', '', NULL, 1, 20, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4797, '2018-07-12 15:27:47', '', NULL, 1, 21, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4798, '2018-07-12 15:27:47', '', NULL, 1, 28, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4799, '2018-07-12 15:27:47', '', NULL, 1, 44, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4800, '2018-07-12 15:27:47', '', NULL, 1, 50, 19, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4801, '2018-07-12 15:27:47', '', NULL, 1, 1, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4802, '2018-07-12 15:27:47', '', NULL, 1, 8, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4803, '2018-07-12 15:27:47', '', NULL, 1, 2, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4804, '2018-07-12 15:27:47', '', NULL, 1, 10, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4805, '2018-07-12 15:27:47', '', NULL, 1, 14, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4806, '2018-07-12 15:27:47', '', NULL, 1, 3, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4807, '2018-07-12 15:27:47', '', NULL, 1, 16, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4808, '2018-07-12 15:27:47', '', NULL, 1, 4, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4809, '2018-07-12 15:27:47', '', NULL, 1, 17, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4810, '2018-07-12 15:27:47', '', NULL, 1, 18, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4811, '2018-07-12 15:27:47', '', NULL, 1, 5, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4812, '2018-07-12 15:27:47', '', NULL, 1, 20, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4813, '2018-07-12 15:27:47', '', NULL, 1, 21, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4814, '2018-07-12 15:27:47', '', NULL, 1, 28, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4815, '2018-07-12 15:27:47', '', NULL, 1, 44, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4816, '2018-07-12 15:27:47', '', NULL, 1, 50, 20, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4817, '2018-07-12 15:27:47', '', NULL, 1, 1, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4818, '2018-07-12 15:27:47', '', NULL, 1, 8, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4819, '2018-07-12 15:27:47', '', NULL, 1, 2, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4820, '2018-07-12 15:27:47', '', NULL, 1, 10, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4821, '2018-07-12 15:27:47', '', NULL, 1, 14, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4822, '2018-07-12 15:27:47', '', NULL, 1, 3, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4823, '2018-07-12 15:27:47', '', NULL, 1, 16, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4824, '2018-07-12 15:27:47', '', NULL, 1, 4, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4825, '2018-07-12 15:27:47', '', NULL, 1, 17, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4826, '2018-07-12 15:27:47', '', NULL, 1, 18, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4827, '2018-07-12 15:27:47', '', NULL, 1, 5, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4828, '2018-07-12 15:27:47', '', NULL, 1, 20, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4829, '2018-07-12 15:27:47', '', NULL, 1, 21, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4830, '2018-07-12 15:27:47', '', NULL, 1, 28, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4831, '2018-07-12 15:27:47', '', NULL, 1, 44, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4832, '2018-07-12 15:27:47', '', NULL, 1, 50, 21, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4833, '2018-07-12 15:27:47', '', NULL, 1, 1, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4834, '2018-07-12 15:27:47', '', NULL, 1, 8, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4835, '2018-07-12 15:27:47', '', NULL, 1, 2, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4836, '2018-07-12 15:27:47', '', NULL, 1, 10, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4837, '2018-07-12 15:27:47', '', NULL, 1, 14, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4838, '2018-07-12 15:27:47', '', NULL, 1, 3, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4839, '2018-07-12 15:27:47', '', NULL, 1, 16, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4840, '2018-07-12 15:27:47', '', NULL, 1, 4, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4841, '2018-07-12 15:27:47', '', NULL, 1, 17, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4842, '2018-07-12 15:27:47', '', NULL, 1, 18, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4843, '2018-07-12 15:27:47', '', NULL, 1, 5, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4844, '2018-07-12 15:27:47', '', NULL, 1, 20, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4845, '2018-07-12 15:27:47', '', NULL, 1, 21, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4846, '2018-07-12 15:27:47', '', NULL, 1, 28, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4847, '2018-07-12 15:27:47', '', NULL, 1, 44, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4848, '2018-07-12 15:27:47', '', NULL, 1, 50, 22, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4914, '2018-07-16 10:59:18', '', NULL, 1, 1, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4915, '2018-07-16 10:59:18', '', NULL, 1, 8, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4916, '2018-07-16 10:59:18', '', NULL, 1, 2, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4917, '2018-07-16 10:59:18', '', NULL, 1, 10, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4918, '2018-07-16 10:59:18', '', NULL, 1, 14, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4919, '2018-07-16 10:59:18', '', NULL, 1, 3, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4920, '2018-07-16 10:59:18', '', NULL, 1, 16, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4921, '2018-07-16 10:59:18', '', NULL, 1, 4, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4922, '2018-07-16 10:59:18', '', NULL, 1, 17, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4923, '2018-07-16 10:59:18', '', NULL, 1, 18, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4924, '2018-07-16 10:59:18', '', NULL, 1, 5, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4925, '2018-07-16 10:59:18', '', NULL, 1, 20, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4926, '2018-07-16 10:59:18', '', NULL, 1, 21, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4927, '2018-07-16 10:59:18', '', NULL, 1, 28, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4928, '2018-07-16 10:59:18', '', NULL, 1, 44, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4929, '2018-07-16 10:59:18', '', NULL, 1, 50, 6, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4946, '2018-07-16 10:59:18', '', NULL, 1, 1, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4947, '2018-07-16 10:59:18', '', NULL, 1, 7, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4948, '2018-07-16 10:59:18', '', NULL, 1, 8, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4949, '2018-07-16 10:59:18', '', NULL, 1, 2, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4950, '2018-07-16 10:59:18', '', NULL, 1, 10, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4951, '2018-07-16 10:59:18', '', NULL, 1, 14, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4952, '2018-07-16 10:59:18', '', NULL, 1, 3, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4953, '2018-07-16 10:59:18', '', NULL, 1, 16, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4954, '2018-07-16 10:59:18', '', NULL, 1, 4, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4955, '2018-07-16 10:59:18', '', NULL, 1, 17, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4956, '2018-07-16 10:59:18', '', NULL, 1, 18, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4957, '2018-07-16 10:59:18', '', NULL, 1, 5, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4958, '2018-07-16 10:59:18', '', NULL, 1, 20, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4959, '2018-07-16 10:59:18', '', NULL, 1, 21, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4960, '2018-07-16 10:59:18', '', NULL, 1, 6, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4961, '2018-07-16 10:59:18', '', NULL, 1, 22, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4962, '2018-07-16 10:59:18', '', NULL, 1, 23, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4963, '2018-07-16 10:59:18', '', NULL, 1, 24, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4964, '2018-07-16 10:59:18', '', NULL, 1, 25, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4965, '2018-07-16 10:59:18', '', NULL, 1, 27, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4966, '2018-07-16 10:59:18', '', NULL, 1, 36, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4967, '2018-07-16 10:59:18', '', NULL, 1, 28, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4968, '2018-07-16 10:59:18', '', NULL, 1, 44, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4969, '2018-07-16 10:59:18', '', NULL, 1, 50, 4, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4987, '2018-07-16 10:59:18', '', NULL, 1, 1, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4988, '2018-07-16 10:59:18', '', NULL, 1, 8, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4989, '2018-07-16 10:59:18', '', NULL, 1, 2, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4990, '2018-07-16 10:59:18', '', NULL, 1, 10, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4991, '2018-07-16 10:59:18', '', NULL, 1, 14, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4992, '2018-07-16 10:59:18', '', NULL, 1, 3, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4993, '2018-07-16 10:59:18', '', NULL, 1, 16, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4994, '2018-07-16 10:59:18', '', NULL, 1, 4, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4995, '2018-07-16 10:59:18', '', NULL, 1, 17, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4996, '2018-07-16 10:59:18', '', NULL, 1, 18, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4997, '2018-07-16 10:59:18', '', NULL, 1, 5, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4998, '2018-07-16 10:59:18', '', NULL, 1, 20, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (4999, '2018-07-16 10:59:18', '', NULL, 1, 21, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5000, '2018-07-16 10:59:18', '', NULL, 1, 28, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5001, '2018-07-16 10:59:18', '', NULL, 1, 44, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5002, '2018-07-16 10:59:18', '', NULL, 1, 50, 5, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5003, '2018-07-16 10:59:18', '', NULL, 1, 1, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5004, '2018-07-16 10:59:18', '', NULL, 1, 8, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5005, '2018-07-16 10:59:18', '', NULL, 1, 2, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5006, '2018-07-16 10:59:18', '', NULL, 1, 10, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5007, '2018-07-16 10:59:18', '', NULL, 1, 14, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5008, '2018-07-16 10:59:18', '', NULL, 1, 3, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5009, '2018-07-16 10:59:18', '', NULL, 1, 16, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5010, '2018-07-16 10:59:18', '', NULL, 1, 4, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5011, '2018-07-16 10:59:18', '', NULL, 1, 17, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5012, '2018-07-16 10:59:18', '', NULL, 1, 18, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5013, '2018-07-16 10:59:18', '', NULL, 1, 5, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5014, '2018-07-16 10:59:18', '', NULL, 1, 20, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5015, '2018-07-16 10:59:18', '', NULL, 1, 21, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5016, '2018-07-16 10:59:18', '', NULL, 1, 28, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5017, '2018-07-16 10:59:18', '', NULL, 1, 44, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5018, '2018-07-16 10:59:18', '', NULL, 1, 50, 9, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5095, '2018-08-20 09:03:29', '', NULL, 1, 1, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5096, '2018-08-20 09:03:29', '', NULL, 1, 7, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5097, '2018-08-20 09:03:29', '', NULL, 1, 8, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5098, '2018-08-20 09:03:29', '', NULL, 1, 2, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5099, '2018-08-20 09:03:29', '', NULL, 1, 10, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5100, '2018-08-20 09:03:29', '', NULL, 1, 14, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5101, '2018-08-20 09:03:29', '', NULL, 1, 3, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5102, '2018-08-20 09:03:29', '', NULL, 1, 16, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5103, '2018-08-20 09:03:29', '', NULL, 1, 4, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5104, '2018-08-20 09:03:29', '', NULL, 1, 17, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5105, '2018-08-20 09:03:29', '', NULL, 1, 18, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5106, '2018-08-20 09:03:29', '', NULL, 1, 5, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5107, '2018-08-20 09:03:29', '', NULL, 1, 20, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5108, '2018-08-20 09:03:29', '', NULL, 1, 21, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5109, '2018-08-20 09:03:29', '', NULL, 1, 6, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5110, '2018-08-20 09:03:29', '', NULL, 1, 22, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5111, '2018-08-20 09:03:29', '', NULL, 1, 23, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5112, '2018-08-20 09:03:29', '', NULL, 1, 24, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5113, '2018-08-20 09:03:29', '', NULL, 1, 25, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5114, '2018-08-20 09:03:29', '', NULL, 1, 27, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5115, '2018-08-20 09:03:29', '', NULL, 1, 52, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5116, '2018-08-20 09:03:29', '', NULL, 1, 28, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5117, '2018-08-20 09:03:29', '', NULL, 1, 36, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5118, '2018-08-20 09:03:29', '', NULL, 1, 44, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5119, '2018-08-20 09:03:29', '', NULL, 1, 50, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5120, '2018-08-20 09:03:29', '', NULL, 1, 51, 2, NULL, 1);
INSERT INTO `wms_module_role` VALUES (5121, '2018-08-20 09:03:29', '', NULL, 1, 53, 2, NULL, 1);

-- ----------------------------
-- Table structure for wms_ordertype
-- ----------------------------
DROP TABLE IF EXISTS `wms_ordertype`;
CREATE TABLE `wms_ordertype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `ordertype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_ordertype
-- ----------------------------
INSERT INTO `wms_ordertype` VALUES (19, '2018-08-20 09:16:54', '正品', '', '');
INSERT INTO `wms_ordertype` VALUES (20, '2018-08-20 14:26:39', '索赔反补单', '', '');

-- ----------------------------
-- Table structure for wms_out_detail_stock_pallet
-- ----------------------------
DROP TABLE IF EXISTS `wms_out_detail_stock_pallet`;
CREATE TABLE `wms_out_detail_stock_pallet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_pallet` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `pallet_id` int(11) NULL DEFAULT NULL,
  `pallet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pallet_rfid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_storage_detail` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_9gbqlup7ekem5cnly1c94lnoh`(`out_storage_detail`) USING BTREE,
  INDEX `FK_qy38iy5lwsfknkop0ee0nghpr`(`stock`) USING BTREE,
  CONSTRAINT `FK_9gbqlup7ekem5cnly1c94lnoh` FOREIGN KEY (`out_storage_detail`) REFERENCES `wms_goods_out_storage_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_out_goods_from_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_out_goods_from_stock`;
CREATE TABLE `wms_out_goods_from_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `out_detail` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_1i42lb8m9y78ogutgc51udxxj`(`create_user`) USING BTREE,
  INDEX `FK_321rx63n8en7wl5kkfvrim9py`(`out_detail`) USING BTREE,
  INDEX `FK_8vff2e9p43nh2ckd2m0g9996j`(`stock`) USING BTREE,
  CONSTRAINT `FK_1i42lb8m9y78ogutgc51udxxj` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_321rx63n8en7wl5kkfvrim9py` FOREIGN KEY (`out_detail`) REFERENCES `wms_goods_out_storage_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_out_pallet_from_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_out_pallet_from_stock`;
CREATE TABLE `wms_out_pallet_from_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `out_num` int(11) NULL DEFAULT NULL,
  `pallet_id` int(11) NULL DEFAULT NULL,
  `pallet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pallet_rfid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_storage_detail` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_al79bfnbx0ojmcj7lylttd9aa`(`out_storage_detail`) USING BTREE,
  INDEX `FK_rq242btydr57fnojy5j2v8yvn`(`stock`) USING BTREE,
  CONSTRAINT `FK_al79bfnbx0ojmcj7lylttd9aa` FOREIGN KEY (`out_storage_detail`) REFERENCES `wms_goods_out_storage_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_pallet
-- ----------------------------
DROP TABLE IF EXISTS `wms_pallet`;
CREATE TABLE `wms_pallet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `in_or_out_identify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `overplus` int(11) NULL DEFAULT NULL,
  `rfid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scan_identify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock_id` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tl9ni9wi7jw426stea1ywet0p`(`create_user`) USING BTREE,
  INDEX `FK_bbwwi93pcb0mx485gtipegad4`(`goods`) USING BTREE,
  INDEX `FK_ftlqs238nmkbdw8tdachgcun5`(`update_user`) USING BTREE,
  CONSTRAINT `FK_bbwwi93pcb0mx485gtipegad4` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ftlqs238nmkbdw8tdachgcun5` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tl9ni9wi7jw426stea1ywet0p` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_pallet_in_log
-- ----------------------------
DROP TABLE IF EXISTS `wms_pallet_in_log`;
CREATE TABLE `wms_pallet_in_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pallet` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_1qrgdr9tmnqcgwpp86tp6dll2`(`pallet`) USING BTREE,
  CONSTRAINT `FK_1qrgdr9tmnqcgwpp86tp6dll2` FOREIGN KEY (`pallet`) REFERENCES `wms_pallet` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_params
-- ----------------------------
DROP TABLE IF EXISTS `wms_params`;
CREATE TABLE `wms_params`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `deleteable` int(11) NULL DEFAULT NULL,
  `desciption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tafdx5r1lfnyoomdv37xit2k6`(`create_user`) USING BTREE,
  INDEX `FK_oyukadveidyd6sh0ix08f9kls`(`update_user`) USING BTREE,
  CONSTRAINT `FK_oyukadveidyd6sh0ix08f9kls` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_tafdx5r1lfnyoomdv37xit2k6` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_params
-- ----------------------------
INSERT INTO `wms_params` VALUES (2, 'String', 'InStorageWay', '2017-11-06 10:33:22', 0, '入库方式', '单独入库', '单独入库', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (3, 'String', 'InStorageWay', '2017-11-06 10:33:22', 0, '入库方式', '栈板入库', '栈板入库', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (7, 'String', 'MeasurementUnit', '2017-11-06 14:52:15', 1, '按吨存储', '吨', '吨', '2018-01-27 12:10:32', 1, 1);
INSERT INTO `wms_params` VALUES (16, 'String', 'RFID IP', '2017-12-15 16:53:29', 0, 'RFID读写器IP', 'RFID读写器IP', '192.168.1.178', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (17, 'String', 'RFID IP', '2017-12-15 16:53:29', 0, 'RFID读写器IP', 'RFID读写器IP', '192.168.0.178', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (18, 'float', 'MeasurementUnit', '2018-01-25 16:04:07', 0, '按平方存储', '平方米', 'm^2', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (19, 'float', 'MeasurementUnit', '2018-01-25 16:04:07', 0, '按照m³存储', '立方米', 'm^3', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (20, 'int', 'MeasurementUnit', '2018-02-03 17:22:17', 0, '按箱存储', '箱', '箱', NULL, 1, NULL);
INSERT INTO `wms_params` VALUES (24, 'String', 'AutoCreateCode', '2018-08-17 11:01:48', 1, '生成单号字母', '单号生成规则', 'HDO', '2018-08-17 14:40:31', 1, 1);

-- ----------------------------
-- Table structure for wms_role
-- ----------------------------
DROP TABLE IF EXISTS `wms_role`;
CREATE TABLE `wms_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_2ohsh37kwdpdulv56noiv4slx`(`create_user`) USING BTREE,
  INDEX `FK_rf1ahlt9ahx9m70n9jcxuof2f`(`update_user`) USING BTREE,
  CONSTRAINT `FK_2ohsh37kwdpdulv56noiv4slx` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rf1ahlt9ahx9m70n9jcxuof2f` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_role
-- ----------------------------
INSERT INTO `wms_role` VALUES (2, 'HeadQuarters', '2017-10-23 15:13:56', '总部', '总部导入数据和查看所有DC仓库数据', '2018-07-04 16:10:03', 1, 1);
INSERT INTO `wms_role` VALUES (4, 'SuperAdmin', '2017-10-31 14:09:45', '超级管理员', '超级管理员能查看并更改所有数据', '2018-07-04 10:22:57', 1, 1);
INSERT INTO `wms_role` VALUES (5, 'CDAdmin', '2018-07-04 14:56:00', '成都基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-04 16:07:10', 1, NULL);
INSERT INTO `wms_role` VALUES (6, 'BJAdmin', '2018-07-04 11:27:09', '北京基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:27', 1, NULL);
INSERT INTO `wms_role` VALUES (9, 'WJAdmin', '2018-07-04 16:05:30', '吴江基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-04 16:05:30', 1, 1);
INSERT INTO `wms_role` VALUES (10, 'CQAdmin', '2018-07-04 16:05:30', '重庆基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-04 16:05:30', 1, 1);
INSERT INTO `wms_role` VALUES (11, 'XNAdmin', '2018-07-04 16:12:56', '虚拟基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:22:59', NULL, NULL);
INSERT INTO `wms_role` VALUES (12, 'CSAdmin', '2018-07-04 16:13:48', '长沙基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:02', 1, NULL);
INSERT INTO `wms_role` VALUES (13, 'KMAdmin', '2018-07-04 16:14:33', '昆明基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:05', 1, NULL);
INSERT INTO `wms_role` VALUES (14, 'JNAdmin', '2018-07-04 16:15:28', '济南基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:09', 1, NULL);
INSERT INTO `wms_role` VALUES (15, 'GZAdmin', '2018-07-05 10:20:11', '广州基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:12', NULL, NULL);
INSERT INTO `wms_role` VALUES (16, 'NJAdmin', '2018-07-05 10:20:15', '南京基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:16', NULL, NULL);
INSERT INTO `wms_role` VALUES (17, 'FZAdmin', '2018-07-05 10:20:21', '福州基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:33', NULL, NULL);
INSERT INTO `wms_role` VALUES (18, 'WHAdmin', '2018-07-05 10:20:26', '武汉基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:37', NULL, NULL);
INSERT INTO `wms_role` VALUES (19, 'HZAdmin', '2018-07-05 10:20:31', '杭州基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:42', NULL, NULL);
INSERT INTO `wms_role` VALUES (20, 'TYAdmin', '2018-07-05 10:20:35', '太原基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:44', NULL, NULL);
INSERT INTO `wms_role` VALUES (21, 'BTAdmin', '2018-07-05 10:20:39', '包头基地管路员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:48', NULL, NULL);
INSERT INTO `wms_role` VALUES (22, 'NCAdmin', '2018-07-05 10:20:42', '南昌基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:51', NULL, NULL);

-- ----------------------------
-- Table structure for wms_role_copy
-- ----------------------------
DROP TABLE IF EXISTS `wms_role_copy`;
CREATE TABLE `wms_role_copy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_2ohsh37kwdpdulv56noiv4slx`(`create_user`) USING BTREE,
  INDEX `FK_rf1ahlt9ahx9m70n9jcxuof2f`(`update_user`) USING BTREE,
  CONSTRAINT `wms_role_copy_ibfk_1` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_role_copy_ibfk_2` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_role_copy
-- ----------------------------
INSERT INTO `wms_role_copy` VALUES (2, 'HeadQuarters', '2017-10-23 15:13:56', '总部', '总部导入数据和查看所有DC仓库数据', '2018-07-04 16:10:03', 1, 1);
INSERT INTO `wms_role_copy` VALUES (4, 'SuperAdmin', '2017-10-31 14:09:45', '超级管理员', '超级管理员能查看并更改所有数据', '2018-07-04 10:22:57', 1, 1);
INSERT INTO `wms_role_copy` VALUES (5, 'CDAdmin', '2018-07-04 14:56:00', '成都基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-04 16:07:10', 1, NULL);
INSERT INTO `wms_role_copy` VALUES (6, 'BJAdmin', '2018-07-04 11:27:09', '北京基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:27', 1, NULL);
INSERT INTO `wms_role_copy` VALUES (9, 'WJAdmin', '2018-07-04 16:05:30', '吴江基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-04 16:05:30', 1, 1);
INSERT INTO `wms_role_copy` VALUES (10, 'CQAdmin', '2018-07-04 16:05:30', '重庆基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-04 16:05:30', 1, 1);
INSERT INTO `wms_role_copy` VALUES (11, 'XNAdmin', '2018-07-04 16:12:56', '虚拟基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:22:59', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (12, 'CSAdmin', '2018-07-04 16:13:48', '长沙基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:02', 1, NULL);
INSERT INTO `wms_role_copy` VALUES (13, 'KMAdmin', '2018-07-04 16:14:33', '昆明基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:05', 1, NULL);
INSERT INTO `wms_role_copy` VALUES (14, 'JNAdmin', '2018-07-04 16:15:28', '济南基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:09', 1, NULL);
INSERT INTO `wms_role_copy` VALUES (15, 'GZAdmin', '2018-07-05 10:20:11', '广州基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:12', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (16, 'NJAdmin', '2018-07-05 10:20:15', '南京基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:16', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (17, 'FZAdmin', '2018-07-05 10:20:21', '福州基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:33', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (18, 'WHAdmin', '2018-07-05 10:20:26', '武汉基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:37', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (19, 'HZAdmin', '2018-07-05 10:20:31', '杭州基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:42', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (20, 'TYAdmin', '2018-07-05 10:20:35', '太原基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:44', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (21, 'BTAdmin', '2018-07-05 10:20:39', '包头基地管路员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:48', NULL, NULL);
INSERT INTO `wms_role_copy` VALUES (22, 'NCAdmin', '2018-07-05 10:20:42', '南昌基地管理员', '仓库管理员只能查看本地DC仓库数据', '2018-07-05 10:23:51', NULL, NULL);

-- ----------------------------
-- Table structure for wms_role_department
-- ----------------------------
DROP TABLE IF EXISTS `wms_role_department`;
CREATE TABLE `wms_role_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `department` int(11) NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_ecso2vucvjv4yg0vsp3sb6021`(`create_user`) USING BTREE,
  INDEX `FK_etwte7jrihb3282eqgw1pbyim`(`department`) USING BTREE,
  INDEX `FK_fj129g42fbtjbygi7k4p7v0ls`(`role`) USING BTREE,
  INDEX `FK_n2j9xoikmmxjlkpt64tqntm9c`(`update_user`) USING BTREE,
  CONSTRAINT `FK_ecso2vucvjv4yg0vsp3sb6021` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_etwte7jrihb3282eqgw1pbyim` FOREIGN KEY (`department`) REFERENCES `wms_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_fj129g42fbtjbygi7k4p7v0ls` FOREIGN KEY (`role`) REFERENCES `wms_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_n2j9xoikmmxjlkpt64tqntm9c` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_role_department
-- ----------------------------
INSERT INTO `wms_role_department` VALUES (55, '2018-07-05 10:35:34', '', NULL, 1, 3, 11, NULL);
INSERT INTO `wms_role_department` VALUES (56, '2018-07-05 10:35:34', '', NULL, 1, 4, 10, NULL);
INSERT INTO `wms_role_department` VALUES (59, '2018-07-05 10:35:34', '', NULL, 1, 6, 13, NULL);
INSERT INTO `wms_role_department` VALUES (60, '2018-07-05 10:35:34', '', NULL, 1, 7, 14, NULL);
INSERT INTO `wms_role_department` VALUES (61, '2018-07-05 10:35:34', '', NULL, 1, 8, 6, NULL);
INSERT INTO `wms_role_department` VALUES (62, '2018-07-05 10:35:34', '', NULL, 1, 9, 15, NULL);
INSERT INTO `wms_role_department` VALUES (63, '2018-07-05 10:35:34', '', NULL, 1, 10, 16, NULL);
INSERT INTO `wms_role_department` VALUES (64, '2018-07-05 10:35:34', '', NULL, 1, 11, 17, NULL);
INSERT INTO `wms_role_department` VALUES (65, '2018-07-05 10:35:34', '', NULL, 1, 12, 18, NULL);
INSERT INTO `wms_role_department` VALUES (66, '2018-07-05 10:35:34', '', NULL, 1, 13, 9, NULL);
INSERT INTO `wms_role_department` VALUES (67, '2018-07-05 10:35:34', '', NULL, 1, 13, 19, NULL);
INSERT INTO `wms_role_department` VALUES (68, '2018-07-05 10:35:34', '', NULL, 1, 14, 20, NULL);
INSERT INTO `wms_role_department` VALUES (69, '2018-07-05 10:35:34', '', NULL, 1, 15, 21, NULL);
INSERT INTO `wms_role_department` VALUES (70, '2018-07-05 10:35:34', '', NULL, 1, 16, 22, NULL);
INSERT INTO `wms_role_department` VALUES (71, '2018-07-10 11:03:22', '', NULL, 1, 5, 12, NULL);
INSERT INTO `wms_role_department` VALUES (72, '2018-07-10 11:03:22', '', NULL, 2, 1, 5, NULL);
INSERT INTO `wms_role_department` VALUES (73, '2018-07-10 20:49:14', '', NULL, 1, 0, 2, NULL);
INSERT INTO `wms_role_department` VALUES (74, '2018-07-10 20:49:14', '', NULL, 1, 0, 4, NULL);
INSERT INTO `wms_role_department` VALUES (75, '2018-07-10 20:49:14', '', NULL, 1, 0, 10, NULL);

-- ----------------------------
-- Table structure for wms_s_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_s_stock`;
CREATE TABLE `wms_s_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `bach` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `batch_num` int(11) NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `days` int(11) NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `high_remind` int(11) NULL DEFAULT NULL,
  `in_op_date` datetime(0) NULL DEFAULT NULL,
  `is_pallet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_shift_park` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `low_remind` int(11) NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `costType_id` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `customer` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_dabr28cvejbxgfucrvhx6l4mw`(`costType_id`) USING BTREE,
  INDEX `FK_mk7dikr4d22hl2baur02mrn5c`(`create_user`) USING BTREE,
  INDEX `FK_1vcrmpyki0e8lfj9nawvnf2g`(`customer`) USING BTREE,
  INDEX `FK_qa1ocwtwwp5taaa8d74i9py5c`(`goods`) USING BTREE,
  INDEX `FK_jx4rqcei1k73rhk19ayxclrrx`(`update_user`) USING BTREE,
  INDEX `FK_lqk7sns05lh95itcvrw70q655`(`warehouse`) USING BTREE,
  CONSTRAINT `FK_1vcrmpyki0e8lfj9nawvnf2g` FOREIGN KEY (`customer`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_dabr28cvejbxgfucrvhx6l4mw` FOREIGN KEY (`costType_id`) REFERENCES `wms_cost_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_jx4rqcei1k73rhk19ayxclrrx` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_lqk7sns05lh95itcvrw70q655` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_mk7dikr4d22hl2baur02mrn5c` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_qa1ocwtwwp5taaa8d74i9py5c` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_settlement
-- ----------------------------
DROP TABLE IF EXISTS `wms_settlement`;
CREATE TABLE `wms_settlement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `already_apmoney` double NULL DEFAULT NULL,
  `ap_money` double NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `days` int(11) NULL DEFAULT NULL,
  `settlement_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nian_yue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_kd0mlnukg3qr7dicntk5m1ish`(`customer_id`) USING BTREE,
  CONSTRAINT `FK_kd0mlnukg3qr7dicntk5m1ish` FOREIGN KEY (`customer_id`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock`;
CREATE TABLE `wms_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `bach` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `batch_num` int(11) NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `days` int(11) NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `high_remind` int(11) NULL DEFAULT NULL,
  `in_op_date` datetime(0) NULL DEFAULT NULL,
  `is_pallet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_shift_park` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `low_remind` int(11) NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `costType_id` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `customer` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_eksnr1lraal3rvenop55worrd`(`costType_id`) USING BTREE,
  INDEX `FK_i2joygq8xyeuhiu31uo6or6pg`(`create_user`) USING BTREE,
  INDEX `FK_51t0r6fg4ato5ypoes20f3y10`(`customer`) USING BTREE,
  INDEX `FK_5m5kpgicfj2otghlgxnis3k36`(`goods`) USING BTREE,
  INDEX `FK_gck3jyv201ejn20utph0xx1qn`(`update_user`) USING BTREE,
  INDEX `FK_dii39i2m4yl407vrwa07wgrbw`(`warehouse`) USING BTREE,
  CONSTRAINT `FK_51t0r6fg4ato5ypoes20f3y10` FOREIGN KEY (`customer`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_5m5kpgicfj2otghlgxnis3k36` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_dii39i2m4yl407vrwa07wgrbw` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_eksnr1lraal3rvenop55worrd` FOREIGN KEY (`costType_id`) REFERENCES `wms_cost_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_gck3jyv201ejn20utph0xx1qn` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_i2joygq8xyeuhiu31uo6or6pg` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_stock
-- ----------------------------
INSERT INTO `wms_stock` VALUES (20, 'CHQ.WZH', NULL, 5, NULL, 0, '晨速物流', 'C023C-X09203711S01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '重庆万州区万川大道302号附1号6-1', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (22, 'QIL', NULL, 1, NULL, 0, '大田物流', 'C412A-X05530601S01', NULL, 0, '广州DC', '2018-08-11 00:00:00', 'BJ1803230025', '邛崃', '吉顺隆物流', '箱体板类', 100000000, '2018-08-17 11:16:33', '0', '1', 0, NULL, '正单', '圣保罗扶手餐椅D67深棕色(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (23, 'WAN', NULL, 5, NULL, 0, '晨速物流', 'C021A-S08049051W03', NULL, 0, '广州DC', '2018-08-10 00:00:00', 'BJ1805160057', '瓮安', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:33', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (25, 'XIS', NULL, 48, NULL, 0, '晨速物流', 'C021A-H10796151F01', NULL, 0, '广州DC', '2018-08-08 00:00:00', 'BJ1805160057', '习水', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:33', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 5.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (26, 'MES', NULL, 5, NULL, 0, '大田物流', 'C021A-S10934401W01', NULL, 0, '广州DC', '2018-08-07 00:00:00', 'BJ1805160057', '眉山博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:33', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (27, 'DYU', NULL, 7, NULL, 0, '晨速物流', 'C021A-X10836981S01', NULL, 0, '广州DC', '2018-08-06 00:00:00', 'BJ1805160057', '都匀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:34', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (28, 'CHQ.JJN', NULL, 2, NULL, 0, '大田物流', 'C021A-R06744901K01', NULL, 0, '广州DC', '2018-08-05 00:00:00', 'BJ1805160057', '江津', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:34', '0', '1', 0, NULL, '正单', '博洛尼厨用龙头K12(70)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (29, 'GUA', NULL, 1, NULL, 0, '大田物流', 'C021A-T07244071K01', NULL, 0, '广州DC', '2018-08-04 00:00:00', 'BJ1805160057', '广安', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:34', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (30, 'QXI', NULL, 1, NULL, 0, '晨速物流', 'C021A-T07083731K01', NULL, 0, '长沙DC', '2018-08-03 00:00:00', 'BJ1805160057', '黔西', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:34', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (32, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (34, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (35, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (36, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (38, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (39, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (40, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (42, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (45, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (46, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (47, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:38', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (48, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (51, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (52, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (53, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (54, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (57, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (58, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (59, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (60, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (61, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (62, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (64, 'REH', NULL, 19, NULL, 0, '晨速物流', 'C021A-N06138701K01', NULL, 0, '长沙DC', '2018-08-01 00:00:00', 'BJ1805160057', '仁怀', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (66, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (67, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (68, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (70, 'CHQ.WZH', NULL, 1, NULL, 0, '晨速物流', 'C021A-R10817911F01-SP1', NULL, 0, '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (71, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (73, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (74, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (75, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (76, 'CHQ.YCH', NULL, 23, NULL, 0, '晨速物流', 'C021A-S10743171F01', NULL, 0, '长沙DC', '2018-07-30 00:00:00', 'BJ1805160057', '永川', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (77, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (80, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (81, 'KLI', NULL, 19, NULL, 0, '晨速物流', 'C021A-N08973241W01', NULL, 0, '长沙DC', '2018-07-29 00:00:00', 'BJ1805160057', '凯里', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (82, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (83, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (85, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (86, 'XIY', NULL, 1, NULL, 0, '晨速物流', 'C021A-X72416501E01-SP1', NULL, 0, '长沙DC', '2018-07-28 00:00:00', 'BJ1805160057', '兴义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:39', '0', '1', 0, NULL, '索赔反补单', '餐椅F180_1N(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (88, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (89, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (92, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (93, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (95, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (96, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (97, 'NEJ', NULL, 1, NULL, 0, '盛世前程', 'C021A-P07139621D01-SP1', NULL, 0, '长沙DC', '2018-07-25 00:00:00', 'BJ1805160057', '内江博洛尼', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '索赔反补单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (98, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (99, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C021A-S06359901F04', NULL, 0, '重庆DC', '2018-07-24 00:00:00', 'BJ1805160057', '宜宾1', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (100, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (101, 'ZUY', NULL, 7, NULL, 0, '晨速物流', 'C021A-N00014311F06', NULL, 0, '重庆DC', '2018-07-23 00:00:00', 'BJ1805160057', '遵义', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:40', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (103, 'LZS', NULL, 20, NULL, 0, '大田物流', 'C021A-N00951171D01', NULL, 0, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:44', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (104, 'LSH', NULL, 8, NULL, 0, '新博通美达-成都仓', 'C021A-R07188621D01', NULL, 0, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (106, 'SNG', NULL, 62, NULL, 0, '大田物流', 'C021A-S10796921F01', NULL, 0, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 6.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (107, 'LZS', NULL, 20, NULL, 0, '大田物流', 'C021A-N00951171D01', NULL, 0, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (108, 'MSH', NULL, 11, NULL, 0, '大田物流', 'C021A-R10836981K01', NULL, 0, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (110, 'LSH', NULL, 8, NULL, 0, '新博通美达-成都仓', 'C021A-R07188621D01', NULL, 0, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (111, 'ZGO', NULL, 6, NULL, 0, '盛世前程', 'C021A-X10257461S01', NULL, 0, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (112, 'LZS', NULL, 20, NULL, 0, '大田物流', 'C021A-N00951171D01', NULL, 0, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (113, 'SNG', NULL, 62, NULL, 0, '大田物流', 'C021A-S10796921F01', NULL, 0, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 6.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (115, 'YIB', NULL, 1, NULL, 0, '大田物流', 'C021A-S03081261F03', NULL, 0, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (116, 'MSH', NULL, 11, NULL, 0, '大田物流', 'C021A-R10836981K01', NULL, 0, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (117, 'LZS', NULL, 20, NULL, 0, '大田物流', 'C021A-N00951171D01', NULL, 0, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (118, 'LSH', NULL, 8, NULL, 0, '新博通美达-成都仓', 'C021A-R07188621D01', NULL, 0, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (119, 'XIC', NULL, 4, NULL, 0, '大田物流', 'C021A-X10862001S01', NULL, 0, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (121, 'ZGO', NULL, 6, NULL, 0, '盛世前程', 'C021A-X10257461S01', NULL, 0, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (122, 'LSH', NULL, 8, NULL, 0, '新博通美达-成都仓', 'C021A-R07188621D01', NULL, 0, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (123, 'SNG', NULL, 62, NULL, 0, '大田物流', 'C021A-S10796921F01', NULL, 0, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 6.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (124, 'PZH', NULL, 2, NULL, 0, '大田物流', 'C021A-H10878171F02', NULL, 0, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (125, 'LZS', NULL, 20, NULL, 0, '大田物流', 'C021A-N00951171D01', NULL, 0, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (126, 'YIB', NULL, 1, NULL, 0, '大田物流', 'C021A-S03081261F03', NULL, 0, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (127, 'NAC', NULL, 9, NULL, 0, '盛世前程', 'C021A-N10931931K01', NULL, 0, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (128, 'MSH', NULL, 11, NULL, 0, '大田物流', 'C021A-R10836981K01', NULL, 0, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (129, 'SNG', NULL, 62, NULL, 0, '大田物流', 'C021A-S10796921F01', NULL, 0, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 6.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (131, 'LSH', NULL, 8, NULL, 0, '新博通美达-成都仓', 'C021A-R07188621D01', NULL, 0, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (132, 'XIC', NULL, 4, NULL, 0, '大田物流', 'C021A-X10862001S01', NULL, 0, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (133, 'ZGO', NULL, 6, NULL, 0, '盛世前程', 'C021A-X10257461S01', NULL, 0, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (134, 'MSH', NULL, 11, NULL, 0, '大田物流', 'C021A-R10836981K01', NULL, 0, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (135, 'LZS', NULL, 20, NULL, 0, '大田物流', 'C021A-N00951171D01', NULL, 0, '重庆DC', '2018-07-21 00:00:00', 'BJ1805160057', '泸州市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (136, 'SNG', NULL, 62, NULL, 0, '大田物流', 'C021A-S10796921F01', NULL, 0, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:45', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 6.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (137, 'ZGO', NULL, 6, NULL, 0, '盛世前程', 'C021A-X10257461S01', NULL, 0, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (138, 'PZH', NULL, 2, NULL, 0, '大田物流', 'C021A-H10878171F02', NULL, 0, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (139, 'YIB', NULL, 1, NULL, 0, '大田物流', 'C021A-S03081261F03', NULL, 0, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (140, 'LSH', NULL, 8, NULL, 0, '新博通美达-成都仓', 'C021A-R07188621D01', NULL, 0, '重庆DC', '2018-07-20 00:00:00', 'BJ1805160057', '乐山市', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (141, 'MSH', NULL, 11, NULL, 0, '大田物流', 'C021A-R10836981K01', NULL, 0, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (142, 'YIB', NULL, 1, NULL, 0, '大田物流', 'C021A-S03081261F03', NULL, 0, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (143, 'NAC', NULL, 9, NULL, 0, '盛世前程', 'C021A-N10931931K01', NULL, 0, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (144, 'XIC', NULL, 4, NULL, 0, '大田物流', 'C021A-X10862001S01', NULL, 0, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (145, 'SNG', NULL, 62, NULL, 0, '大田物流', 'C021A-S10796921F01', NULL, 0, '重庆DC', '2018-07-19 00:00:00', 'BJ1805160057', '遂宁', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 6.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (146, 'ZGO', NULL, 6, NULL, 0, '盛世前程', 'C021A-X10257461S01', NULL, 0, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (147, 'XIC', NULL, 4, NULL, 0, '大田物流', 'C021A-X10862001S01', NULL, 0, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (148, 'PZH', NULL, 2, NULL, 0, '大田物流', 'C021A-H10878171F02', NULL, 0, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (149, 'MSH', NULL, 11, NULL, 0, '大田物流', 'C021A-R10836981K01', NULL, 0, '重庆DC', '2018-07-18 00:00:00', 'BJ1805160057', '眉山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (150, 'YIB', NULL, 1, NULL, 0, '大田物流', 'C021A-S03081261F03', NULL, 0, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (151, 'PZH', NULL, 2, NULL, 0, '大田物流', 'C021A-H10878171F02', NULL, 0, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (152, 'NAC', NULL, 9, NULL, 0, '盛世前程', 'C021A-N10931931K01', NULL, 0, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (153, 'ZGO', NULL, 6, NULL, 0, '盛世前程', 'C021A-X10257461S01', NULL, 0, '重庆DC', '2018-07-17 00:00:00', 'BJ1805160057', '自贡', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (154, 'XIC', NULL, 4, NULL, 0, '大田物流', 'C021A-X10862001S01', NULL, 0, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (155, 'NAC', NULL, 9, NULL, 0, '盛世前程', 'C021A-N10931931K01', NULL, 0, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (156, 'PZH', NULL, 2, NULL, 0, '大田物流', 'C021A-H10878171F02', NULL, 0, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (157, 'YIB', NULL, 1, NULL, 0, '大田物流', 'C021A-S03081261F03', NULL, 0, '重庆DC', '2018-07-16 00:00:00', 'BJ1805160057', '宜宾', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (158, 'NAC', NULL, 9, NULL, 0, '盛世前程', 'C021A-N10931931K01', NULL, 0, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (159, 'XIC', NULL, 4, NULL, 0, '大田物流', 'C021A-X10862001S01', NULL, 0, '重庆DC', '2018-07-15 00:00:00', 'BJ1805160057', '西昌', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (160, 'PZH', NULL, 2, NULL, 0, '大田物流', 'C021A-H10878171F02', NULL, 0, '成都基地', '2018-07-14 00:00:00', 'BJ1805160057', '攀枝花', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (161, 'NAC', NULL, 9, NULL, 0, '盛世前程', 'C021A-N10931931K01', NULL, 0, '成都基地', '2018-07-13 00:00:00', 'BJ1805160057', '南充', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:16:46', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (162, 'MYA.CHQ', NULL, 42, NULL, 0, '盛世前程', 'C021A-S10258471F01', NULL, 0, '成都基地', '2018-07-12 00:00:00', 'BJ1805160057', '绵阳城区', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:10', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 4.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (163, 'LES', NULL, 3, NULL, 0, '{盛世前程物流', 'C021A-R03303881D02', NULL, 0, '成都基地', '2018-07-11 00:00:00', 'BJ1805160057', '乐山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:10', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (164, 'DJY', NULL, 1, NULL, 0, '大田物流', 'C021A-X01094231S01-SP2', NULL, 0, '成都基地', '2018-07-10 00:00:00', 'BJ1805160057', '都江堰', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:10', '0', '1', 0, NULL, '索赔反补单', '亨特床_180_P级(3.15全屋定制开年惠20170301)(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (165, 'DEY', NULL, 1, NULL, 0, '大田物流', 'C021A-X10572731S01-SP1', NULL, 0, '成都基地', '2018-07-09 00:00:00', 'BJ1805160057', '德阳', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:10', '0', '1', 0, NULL, '索赔反补单', '图尔B床_180_C面料(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (166, 'MYA.CHQ', NULL, 42, NULL, 0, '盛世前程', 'C021A-S10258471F01', NULL, 0, '成都基地', '2018-07-12 00:00:00', 'BJ1805160057', '绵阳城区', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 4.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (168, 'LES', NULL, 3, NULL, 0, '{盛世前程物流', 'C021A-R03303881D02', NULL, 0, '成都基地', '2018-07-11 00:00:00', 'BJ1805160057', '乐山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (170, 'DJY', NULL, 1, NULL, 0, '大田物流', 'C021A-X01094231S01-SP2', NULL, 0, '成都基地', '2018-07-10 00:00:00', 'BJ1805160057', '都江堰', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '索赔反补单', '亨特床_180_P级(3.15全屋定制开年惠20170301)(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (171, 'LPS', NULL, 17, NULL, 0, '大田物流', 'C021A-R04501611D01', NULL, 0, '成都基地', '2018-07-06 00:00:00', 'BJ1805160057', '六盘水', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (172, 'MYA.CHQ', NULL, 42, NULL, 0, '盛世前程', 'C021A-S10258471F01', NULL, 0, '成都基地', '2018-07-12 00:00:00', 'BJ1805160057', '绵阳城区', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '家具生产通用成品2(10)', NULL, '1', NULL, 4.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (173, 'DEY', NULL, 1, NULL, 0, '大田物流', 'C021A-X10572731S01-SP1', NULL, 0, '成都基地', '2018-07-09 00:00:00', 'BJ1805160057', '德阳', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '索赔反补单', '图尔B床_180_C面料(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (175, 'LES', NULL, 3, NULL, 0, '{盛世前程物流', 'C021A-R03303881D02', NULL, 0, '成都基地', '2018-07-11 00:00:00', 'BJ1805160057', '乐山', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (177, 'DJY', NULL, 1, NULL, 0, '大田物流', 'C021A-X01094231S01-SP2', NULL, 0, '成都基地', '2018-07-10 00:00:00', 'BJ1805160057', '都江堰', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '索赔反补单', '亨特床_180_P级(3.15全屋定制开年惠20170301)(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (179, 'DEY', NULL, 1, NULL, 0, '大田物流', 'C021A-X10572731S01-SP1', NULL, 0, '成都基地', '2018-07-09 00:00:00', 'BJ1805160057', '德阳', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '索赔反补单', '图尔B床_180_C面料(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (180, 'LPS', NULL, 17, NULL, 0, '大田物流', 'C021A-R04501611D01', NULL, 0, '成都基地', '2018-07-06 00:00:00', 'BJ1805160057', '六盘水', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (184, 'LPS', NULL, 17, NULL, 0, '大田物流', 'C021A-R04501611D01', NULL, 0, '成都基地', '2018-07-06 00:00:00', 'BJ1805160057', '六盘水', '灵鹤直发', '箱体板类', 100000000, '2018-08-17 11:17:11', '0', '1', 0, NULL, '正单', '内门通用制造成品1(10)', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (185, 'ANH', NULL, 1, NULL, 0, '康程物流', '013413540', NULL, 0, '重庆DC', '2018-08-20 09:34:47', '013413540', '湖南省益阳市安化县东坪镇南区金桥建材家居城101-103号门面龙鑫装饰', '康程物流', '正品', 100000000, '2018-08-20 09:35:26', '0', '1', 0, NULL, '正单', '正品', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (186, 'CHQ.YCH', NULL, 2, NULL, 0, '晨速物流', 'C023A-X00028981S02', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', '重庆市永川区胜利北路8号', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:35', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (187, 'CHQ.WZH', NULL, 1, NULL, 0, '晨速物流', 'C023C-C07462391K01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '重庆万州区万川大道302号附1号6-1', 'DC自提', '生产包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (188, 'CHQ.WZH', NULL, 3, NULL, 0, '晨速物流', 'C023C-X11063811S01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '重庆万州区万川大道302号附1号6-1', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (189, 'CHQ.YCH', NULL, 2, NULL, 0, '晨速物流', 'C023A-S10985921K03', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', '重庆市永川区胜利北路8号', 'DC自提', '生产包装', 100000000, '2018-08-01 08:59:35', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (190, 'CQI', NULL, 20, NULL, 0, '晨速物流', 'C023E-S07820351W01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', '江苏省江阴市临港街道新港花苑141号502室', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:04', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (191, 'CSA', NULL, 12, NULL, 0, 'DC自提', 'C731A-N08903331F04', NULL, 0, '长沙', '2018-07-31 00:00:00', 'BZFHC0181807300235', '银杏家园', '吉顺湖南线', '生产包装', 100000000, '2018-08-02 11:57:12', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (192, 'CQI', NULL, 8, NULL, 0, '晨速物流', 'C023E-A09331711F06', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', '江苏省江阴市临港街道新港花苑141号502室', 'DC自提', '生产包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (193, 'CDU', NULL, 8, NULL, 0, '三志物流', 'C028A-X11125541S01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:58:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (194, 'CHQ.WZH', NULL, 8, NULL, 0, '晨速物流', 'C023C-X11064011S01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300347', '重庆万州区万川大道302号附1号6-1', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (195, 'GUH', NULL, 13, NULL, 0, '大田物流', 'C520-11K-170122-A1', NULL, 0, '南京DC', '2018-08-22 00:00:00', 'BJ1803200014', '广汉-蔡春', '灵鹤调拨', '箱体板类', 100000000, '2018-07-16 18:09:54', '0', '1', 0, NULL, '正单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (196, 'CHQ.WZH', NULL, 3, NULL, 0, '晨速物流', 'C023C-X11063811S02', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '重庆万州区万川大道302号附1号6-1', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (197, 'CAN', NULL, 1, NULL, 0, '三志物流', 'C577C-T11016601K02', NULL, 0, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省苍南县灵溪镇上江小区1-5幢', '瀛海世纪物流', '库存品包装', 100000000, '2018-08-02 15:54:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (198, 'CQI', NULL, 1, NULL, 0, '晨速物流', 'C412-11K-169004-A1-YB-SP2', NULL, 0, '南京DC', '2018-08-14 00:00:00', 'BJ1803230025', '重庆博洛尼', '吉顺隆物流', '箱体板类', 100000000, '2018-08-17 11:09:58', '0', '1', 0, NULL, '索赔反补单', '厨卫生产通用成品1(10)', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (199, 'GAY', NULL, 1, NULL, 0, '大田物流', 'Z412A01H-180002-A1', NULL, 0, '广州DC', '2018-08-13 00:00:00', 'BJ1803230025', '广元', '吉顺隆物流', '箱体板类', 100000000, '2018-08-17 11:09:58', '0', '1', 0, NULL, '正单', '圣保罗扶手餐椅D67深棕色_1件1包_1_1_1', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (200, 'CHQ.WZH', NULL, 3, NULL, 0, '晨速物流', 'C023C-X11064011S01', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '重庆万州区万川大道302号附1号6-1', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock` VALUES (201, 'CDU', NULL, 20, NULL, 0, NULL, 'C028A-N07228311F04', NULL, 0, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '生产包装', 100000000, '2018-08-20 09:44:16', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.10, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_stock_copy
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock_copy`;
CREATE TABLE `wms_stock_copy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `bach` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `batch_num` int(11) NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `days` int(11) NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `high_remind` int(11) NULL DEFAULT NULL,
  `in_op_date` datetime(0) NULL DEFAULT NULL,
  `is_pallet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_shift_park` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `low_remind` int(11) NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `costType_id` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `customer` int(11) NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_eksnr1lraal3rvenop55worrd`(`costType_id`) USING BTREE,
  INDEX `FK_i2joygq8xyeuhiu31uo6or6pg`(`create_user`) USING BTREE,
  INDEX `FK_51t0r6fg4ato5ypoes20f3y10`(`customer`) USING BTREE,
  INDEX `FK_5m5kpgicfj2otghlgxnis3k36`(`goods`) USING BTREE,
  INDEX `FK_gck3jyv201ejn20utph0xx1qn`(`update_user`) USING BTREE,
  INDEX `FK_dii39i2m4yl407vrwa07wgrbw`(`warehouse`) USING BTREE,
  CONSTRAINT `wms_stock_copy_ibfk_1` FOREIGN KEY (`customer`) REFERENCES `wms_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_stock_copy_ibfk_2` FOREIGN KEY (`goods`) REFERENCES `wms_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_stock_copy_ibfk_3` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_stock_copy_ibfk_4` FOREIGN KEY (`costType_id`) REFERENCES `wms_cost_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_stock_copy_ibfk_5` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `wms_stock_copy_ibfk_6` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2973 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_stock_copy
-- ----------------------------
INSERT INTO `wms_stock_copy` VALUES (966, 'QZH', NULL, 1, NULL, 0, '中兴圣物流', 'C595B-S07099431K02', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '生产包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (967, 'QZH', NULL, 1, NULL, 0, '中兴圣物流', 'C595B-S10798701K02', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '生产包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (968, 'QZH', NULL, 1, NULL, 0, '中兴圣物流', 'C595B-U10878981K01-B1', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '生产包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (969, 'QZH', NULL, 2, NULL, 0, '中兴圣物流', 'C595B-S10798701K02', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '生产包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (970, 'QZH', NULL, 1, NULL, 0, '中兴圣物流', 'C595B-U08709051K01', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '生产包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (971, 'QZH', NULL, 2, NULL, 0, '中兴圣物流', 'C595B-U09354371K01', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '生产包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (972, 'QZH', NULL, 3, NULL, 0, '中兴圣物流', 'C595B-P05856791K14', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300223', '泉州丰泽区喜盈门建材市场一楼1306博洛尼展厅', '中兴圣物流', '库存品包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (973, 'PIT', NULL, 1, NULL, 0, '中兴圣物流', 'C591C-T03971451K01', NULL, 8, '福州', '2018-06-30 00:00:00', 'BZFHC0181806300029', '福建省平潭县潭城镇东湖庄206号9幢3单元', '中兴圣物流', '库存品包装', 100000000, '2018-07-29 15:09:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (974, 'ZSH', NULL, 49, NULL, 0, '捷安物流', 'C760-10K-170702-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300213', '广东省中山市西区街道木围仓街17号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.44, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (975, 'ZSH', NULL, 33, NULL, 0, '捷安物流', 'C760-10F-170036-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300213', '广东省中山市西区街道木围仓街16号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (976, 'ZSH', NULL, 24, NULL, 0, '捷安物流', 'C760-10F-170040-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300214', '广东省中山市西区街道木围仓街15号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.38, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (977, 'ZSH', NULL, 22, NULL, 0, '捷安物流', 'C760-10F-170035-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300214', '广东省中山市西区街道木围仓街14号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.28, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (978, 'ZSH', NULL, 12, NULL, 0, '捷安物流', 'C760-10F-170038-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300213', '广东省中山市西区街道木围仓街13号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (979, 'ZSH', NULL, 12, NULL, 0, '捷安物流', 'C760-10F-170033-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300214', '广东省中山市西区街道木围仓街12号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.97, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (980, 'ZSH', NULL, 11, NULL, 0, '捷安物流', 'C760-10F-170031-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300214', '广东省中山市西区街道木围仓街11号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.23, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (981, 'ZSH', NULL, 11, NULL, 0, '捷安物流', 'C760-10F-170032-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300214', '广东省中山市西区街道木围仓街10号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (982, 'ZSH', NULL, 10, NULL, 0, '捷安物流', 'C760-10F-170041-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300213', '广东省中山市西区街道木围仓街9号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (983, 'ZSH', NULL, 6, NULL, 0, '捷安物流', 'C760-10F-170034-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300214', '广东省中山市西区街道木围仓街8号之四仓', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (984, 'GUZ.KC', NULL, 75, NULL, 0, '同鑫物流', 'C020-31R-179001-M-HC', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路166号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.75, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (985, 'GUZ.KC', NULL, 1, NULL, 0, '同鑫物流', 'C020-41D-170512-A1-SP1-N', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路165号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (986, 'GUZ.KC', NULL, 1, NULL, 0, '同鑫物流', 'C020-41F-170999-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路164号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (987, 'GUZ.HCL', NULL, 36, NULL, 0, '同鑫物流', 'C020A-R08249262K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路163号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.86, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (988, 'GUZ.HCL', NULL, 16, NULL, 0, '同鑫物流', 'C020A-M10846881F01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300207', '广州市白云区太和镇黄庄北路162号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.43, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (989, 'GUZ.HCL', NULL, 15, NULL, 0, '同鑫物流', 'C020A-N08249263F03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300210', '广州市白云区太和镇黄庄北路161号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.41, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (990, 'GUZ.HCL', NULL, 12, NULL, 0, '同鑫物流', 'C020A-N08249263F34', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300210', '广州市白云区太和镇黄庄北路160号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.81, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (991, 'GUZ.HCL', NULL, 10, NULL, 0, '同鑫物流', 'C020A-X09860721S04', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路159号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (992, 'GUZ.HCL', NULL, 7, NULL, 0, '同鑫物流', 'C020A-M10846881F02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300207', '广州市白云区太和镇黄庄北路158号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.67, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (993, 'GUZ.HCL', NULL, 6, NULL, 0, '同鑫物流', 'C020A-T08249263K26', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路157号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.81, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (994, 'GUZ.HCL', NULL, 6, NULL, 0, '同鑫物流', 'C020A-M10846881F03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300207', '广州市白云区太和镇黄庄北路156号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.44, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (995, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-X08249263S09', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路155号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (996, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-X10846881S01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300208', '广州市白云区太和镇黄庄北路154号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (997, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-N10846881D02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300208', '广州市白云区太和镇黄庄北路153号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (998, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020-51K-179010-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路152号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (999, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-T08249263K29', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路151号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.66, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1000, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-X08249263S07', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路150号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1001, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-T08249263K15', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路149号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.57, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1002, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-M10846881F04', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300207', '广州市白云区太和镇黄庄北路148号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1003, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-T10905761K02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路147号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1004, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-P10916111D01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路146号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1005, 'GUZ.HCL', NULL, 3, NULL, 0, '同鑫物流', 'C020A-X08249263S36', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路145号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1006, 'GUZ.HCL', NULL, 3, NULL, 0, '同鑫物流', 'C020A-T08249263K28', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路144号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.42, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1007, 'GUZ.HCL', NULL, 3, NULL, 0, '同鑫物流', 'C020A-X08249263S18', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路143号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1008, 'GUZ.HCL', NULL, 3, NULL, 0, '同鑫物流', 'C020A-T08249263K19', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路142号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.55, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1009, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-T08249263K23', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路141号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1010, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-T08249263K19', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路140号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.55, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1011, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-X08249263S02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路139号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1013, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-X08249263S29', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路137号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1014, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-R08249262K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300206', '广州市白云区太和镇黄庄北路136号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1015, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T08249263F49', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300210', '广州市白云区太和镇黄庄北路135号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1016, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-X11027841S01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路134号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1017, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K05', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路133号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.46, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1018, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K04', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路132号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.46, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1019, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路131号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.46, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1020, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路130号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.46, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1021, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路129号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.46, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1022, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K09', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路128号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1023, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K04', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路127号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1024, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路126号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1025, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路125号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1026, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路124号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1027, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721K05', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路123号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1028, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020-51D-179028-A1-SP1-N', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路122号沙太', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1029, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020-51K-170995-A1', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300211', '广州市白云区太和镇黄庄北路121号沙太', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1032, 'DGA', '东莞-刘军伟', 39, NULL, 0, '同鑫物流', 'C769B-S10899001F01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300166', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 3.99, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1033, 'DGA', '东莞-刘军伟', 37, NULL, 0, '同鑫物流', 'C769B-L10942961K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300184', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:48', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1034, 'DGA', '东莞-刘军伟', 37, NULL, 0, '同鑫物流', 'C769B-S11079441K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300194', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.83, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1035, 'DGA', '东莞-刘军伟', 29, NULL, 0, '同鑫物流', 'C769B-S10899001K03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300167', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1036, 'DGA', '东莞-刘军伟', 26, NULL, 0, '同鑫物流', 'C769B-S11059451K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300198', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1037, 'DGA', '东莞-刘军伟', 16, NULL, 0, '同鑫物流', 'C769B-U11079441K02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300194', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1038, 'DGA', '东莞-刘军伟', 13, NULL, 0, '同鑫物流', 'C769B-U11079441F04', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300195', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1039, 'DGA', '东莞-刘军伟', 12, NULL, 0, '同鑫物流', 'C769B-U11079441F03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300195', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1040, 'DGA', '东莞-刘军伟', 11, NULL, 0, '同鑫物流', 'C769B-S10899001F03', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300167', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1041, 'DGA', '东莞-刘军伟', 10, NULL, 0, '同鑫物流', 'C769B-S10899001F02', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300166', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, 'ADCK201807310007', '正单', '箱体板类', NULL, '1', NULL, 0.81, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1042, 'DGA', NULL, 4, NULL, 0, '同鑫物流', 'C769B-S10899001K05', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300167', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1043, 'DGA', NULL, 3, NULL, 0, '同鑫物流', 'C769B-S10899001K04', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300167', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1044, 'DGA', NULL, 2, NULL, 0, '同鑫物流', 'C769B-S11059451F01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300198', '东莞', '恒运宏远物流', '生产包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1045, 'DGA', NULL, 1, NULL, 0, '同鑫物流', 'C769B-L10942961K01', NULL, 8, '广州', '2018-06-30 00:00:00', 'BZFHC0181806300184', '东莞', '恒运宏远物流', '库存品包装', 100000000, '2018-07-29 16:18:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1046, 'XIC', NULL, 1, NULL, 0, '大田物流', 'Z834-11H-170003-Z3', NULL, 7, '成都', '2018-01-04 00:00:00', 'BSD2018-01-0420', '西昌市二中', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '增单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1047, 'NAC', NULL, 1, NULL, 0, '盛世前程物流', 'C817A-X03905831S09', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290157', '蓝光19栋1单元14-4', '盛世前程', '库存品包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1048, 'NAC', NULL, 5, NULL, 0, '盛世前程物流', 'C817A-S03905831F18', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290157', '蓝光19栋1单元14-4', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1049, 'NAC', NULL, 14, NULL, 0, '盛世前程物流', 'C817A-V03905831D27', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290157', '蓝光19栋1单元14-4', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1050, 'NAC', NULL, 6, NULL, 0, '盛世前程物流', 'C817A-N03905831W01', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290157', '蓝光19栋1单元14-4', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1052, 'MES', NULL, 7, NULL, 0, '大田物流', 'C028B-X03976583S15', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.85, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1054, 'MES', NULL, 12, NULL, 0, '大田物流', 'C028B-S11079081F01', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1055, 'MES', NULL, 13, NULL, 0, '大田物流', 'C028B-S11079081F02', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1056, 'MES', NULL, 16, NULL, 0, '大田物流', 'C028B-S11176351K01', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.82, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1057, 'MES', NULL, 11, NULL, 0, '大田物流', 'C028B-S11079081D02', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1059, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-T11146401K03', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1060, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-T11146401K02', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.48, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1061, 'MES', NULL, 9, NULL, 0, '大田物流', 'Z028B01H-180014-A1', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 7.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1062, 'MES', NULL, 7, NULL, 0, '大田物流', 'Z028B01H-180013-A1', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 5.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1063, 'MES', NULL, 2, NULL, 0, '大田物流', 'Z028B01H-180013-A1', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 1.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1064, 'MES', NULL, 4, NULL, 0, '大田物流', 'Z028B01H-180014-A1', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1065, 'MES', NULL, 5, NULL, 0, '大田物流', 'Z028B01H-180013-A1', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:09', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 4.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1066, 'MES', NULL, 7, NULL, 0, '大田物流', 'Z028B01H-180013-A1', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290160', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 5.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1067, 'MES', NULL, 6, NULL, 0, '大田物流', 'C028B-S11086241F13', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290161', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1068, 'MES', NULL, 7, NULL, 0, '大田物流', 'C028B-S11086241F12', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290161', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.67, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1069, 'MES', NULL, 6, NULL, 0, '大田物流', 'C028B-S11086241F09', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290161', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.43, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1070, 'MES', NULL, 6, NULL, 0, '大田物流', 'C028B-N11086241F05', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290161', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.33, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1071, 'MES', NULL, 9, NULL, 0, '大田物流', 'C028B-S11086241F04', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290161', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1072, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-T03905811K17', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1073, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-X03905811S09', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1074, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-T03905811K17', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1075, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-T03905811K17', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1078, 'YBN', NULL, 5, NULL, 0, '大田物流', 'C831A-T03905811K16', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1079, 'YBN', NULL, 5, NULL, 0, '大田物流', 'C831A-T03905811K16', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1080, 'YBN', NULL, 5, NULL, 0, '大田物流', 'C831A-T03905811K16', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1081, 'YBN', NULL, 15, NULL, 0, '大田物流', 'C831A-T03905811K09', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1082, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C831A-X03905811S05', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1083, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-X03905811S06', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1084, 'YBN', NULL, 7, NULL, 0, '大田物流', 'C831A-X03905811S05', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1085, 'YBN', NULL, 3, NULL, 0, '大田物流', 'C831A-T03905811D06', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1086, 'YBN', NULL, 4, NULL, 0, '大田物流', 'C831A-X03905811S04', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1087, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C831A-X03905811S06', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1088, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C831A-X03905811S08', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1089, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-T03905811K03', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1090, 'YBN', NULL, 13, NULL, 0, '大田物流', 'C831A-T03905811K03', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1091, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-T03905811K07', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1092, 'YBN', NULL, 1, NULL, 0, '大田物流', 'C831A-T03905811K08', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806290198', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1093, 'XIC', NULL, 1, NULL, 0, '大田物流', 'Z834-11H-170003-Z3', NULL, 7, '成都', '2018-06-29 00:00:00', 'BZFHC0181806290199', '西昌市二中', '盛世前程', '库存品包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '增单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1094, 'MES', NULL, 30, NULL, 0, '大田物流', 'C028B-N11086241F02', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300090', '四川省眉山市东城区碧华林17栋2单元302室', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1095, 'MES', NULL, 3, NULL, 0, '大田物流', 'C028B-N09022191F04', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300090', '四川省眉山市东城区碧华林17栋2单元302室', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.26, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1096, 'MES', NULL, 9, NULL, 0, '大田物流', 'C028B-N11086241F07', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300090', '四川省眉山市东城区碧华林17栋2单元302室', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.88, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1097, 'MES', NULL, 11, NULL, 0, '大田物流', 'C028B-S11079081F04', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300091', '四川省眉山市东城区碧华林17栋2单元302室', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1098, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-T11178631D03', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300091', '四川省眉山市东城区碧华林17栋2单元302室', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1099, 'YBN', NULL, 3, NULL, 0, '大田物流', 'C831A-T03905811K04', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300095', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '盛世前程', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1100, 'MES', NULL, 20, NULL, 0, '大田物流', 'C028B-T11214741K01', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300231', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 5.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1101, 'MES', NULL, 1, NULL, 0, '大田物流', 'C028B-T11146401K02', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300231', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1103, 'MES', NULL, 2, NULL, 0, '大田物流', 'C028B-S10834181F03', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300231', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1104, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-S11086241F11', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300231', '四川省眉山市东城区碧华林17栋2单元302室', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1105, 'YBN', NULL, 2, NULL, 0, '大田物流', 'C831A-V03905811D08', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300231', '宜宾市翠屏区金沙江大道博美正和装饰城216、232号', '大田物流', '生产包装', 100000000, '2018-07-30 10:57:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1106, 'LAS', NULL, 5, NULL, 0, '大田物流', 'C891A-T07731871K04', NULL, 7, '成都', '2018-06-30 00:00:00', 'BZFHC0181806300231', '西藏省拉萨建材交易中心25号商铺', '大田物流', '库存品包装', 100000000, '2018-07-30 10:57:36', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1108, 'PIZ', NULL, 1, NULL, 0, '灵鹤江苏线', 'C516B-T00027621K02', NULL, 7, '南京', '2018-06-30 00:00:00', 'BZFHC0181806300081', '江苏省邳州市5公里飞龙酒店北侧永兴汽贸3楼博洛尼体验馆', '灵鹤江苏线', '生产包装', 100000000, '2018-07-30 11:19:01', '0', '1', 0, NULL, '正单', '2018年渠道成品台面样块刀型套包', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1109, 'JIH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C515B-T00006291K02', NULL, 7, '南京', '2018-06-30 00:00:00', 'BZFHC0181806300133 ', '江苏省建湖县五洲国际商贸城1号楼1栋欧神诺瓷砖', '灵鹤江苏线', '样块', 100000000, '2018-07-30 11:19:01', '0', '1', 0, NULL, '正单', '2018年渠道成品台面样块刀型套包', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1110, 'JDU', NULL, 2, NULL, 0, '灵鹤江苏线', 'C514-11K-178002-A1-JZ', NULL, 7, '南京', '2018-06-30 00:00:00', 'BZFHC0181806300135 ', '江苏省江都市江都润家国际装饰城二层科宝博洛尼', '灵鹤江苏线', '样块', 100000000, '2018-07-30 11:19:01', '0', '1', 0, NULL, '正单', '2017年新增样块套包004套件', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1112, 'ZSH', NULL, 3, NULL, 0, '捷安物流', 'C760A-S08080811F01-SP1', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300270', '广东省中山市西区街道木围仓街8号之四仓', 'DC自提', '生产包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.24, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1113, 'ZJJ', NULL, 1, NULL, 0, '康程物流', 'C744A-M11208891K01', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300272', '湖南省张家界市永定区建材市场D栋二楼博洛尼橱柜店', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1114, 'YDE', NULL, 1, NULL, 0, '捷安物流', 'C763A-T03905271K02', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '广东省英德市碧峰华府南门66号', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1116, 'SHR', NULL, 1, NULL, 0, '中路物流', 'C793A-S10990701K02', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300271', '江西省上饶市信州区上饶东高速路口江西远泉花木博园', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1117, 'SDE.BLN', NULL, 4, NULL, 0, '捷安物流', 'C757B-T11166691K02', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '佛山市顺德区勒流街道连杜大道天任工业园区五号门', 'DC自提', '生产包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1118, 'SDE.BLN', NULL, 1, NULL, 0, '捷安物流', 'C757B-N11008131K01', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '佛山市顺德区勒流街道连杜大道天任工业园区五号门', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1119, 'SDE.BLN', NULL, 2, NULL, 0, '捷安物流', 'C757B-S09704471F07', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '佛山市顺德区勒流街道连杜大道天任工业园区五号门', 'DC自提', '生产包装', 100000000, '2018-07-31 14:54:58', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1121, 'QHI', NULL, 1, NULL, 0, '捷安物流', 'C898-41D-179025-A1-BN-SP1-N', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 14:58:53', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1122, 'QHI', NULL, 4, NULL, 0, '捷安物流', 'Z898C01H-180005-A1', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:59:00', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1123, 'QHI', NULL, 2, NULL, 0, '捷安物流', 'C898-41D-179018-A1-BN-SP1-N', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 14:59:16', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1124, 'ZJG.JCH', NULL, 2, NULL, 0, '灵鹤江苏线', 'C512A-R11140041W05', NULL, 6, '南京', '2018-06-30 00:00:00', '', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 14:59:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1128, 'ANH', NULL, 1, NULL, 0, '康程物流', 'C737B-S11053551K01', NULL, 6, '长沙', '2018-06-30 00:00:00', 'BZFHC0181807300239', '江西南昌', 'DC自提', '生产包装', 100000000, '2018-07-31 14:59:41', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1129, 'YIY', NULL, 2, NULL, 0, '康程物流', 'C737A-S11261701K02', NULL, 6, '长沙', '2018-06-30 00:00:00', 'BZFHC0181807300239', '浙江省温岭市万昌中路483号', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:59:41', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.13, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1130, 'YIY', NULL, 27, NULL, 0, '康程物流', 'C737A-S11261701K02', NULL, 6, '长沙', '2018-06-30 00:00:00', 'BZFHC0181807300239', '浙江省温岭市万昌中路483号', 'DC自提', '生产包装', 100000000, '2018-07-31 14:59:41', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1136, 'SMS', NULL, 2, NULL, 0, '中兴圣物流', 'C598B-S11105791K01', NULL, 6, '福州', '2018-06-30 00:00:00', 'BZFHC0181807300321', '福建省三明市金色阳光建材城', 'DC自提', '生产包装', 100000000, '2018-07-31 14:59:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1137, 'QHI', NULL, 8, NULL, 0, '捷安物流', 'Z898C01H-180005-A1', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '库存品包装', 100000000, '2018-07-31 14:59:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1138, 'SMS', NULL, 2, NULL, 0, '中兴圣物流', 'C598B-S11105791F03', NULL, 6, '福州', '2018-06-30 00:00:00', 'BZFHC0181807300300', '福建省三明市金色阳光建材城', 'DC自提', '生产包装', 100000000, '2018-07-31 14:59:57', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1139, 'QZH', NULL, 2, NULL, 0, '中兴圣物流', 'C595B-S09920751F11', NULL, 6, '福州', '2018-06-30 00:00:00', 'BZFHC0181807300300', '泉州丰泽区喜盈门建材家居一楼1306博洛尼展厅', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1140, 'ZJG.JCH', NULL, 10, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:03', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1141, 'ZJG.JCH', NULL, 2, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:06', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1144, 'ZJG.JCH', NULL, 4, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:09', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 3.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1145, 'ZJG.JCH', NULL, 9, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:12', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 7.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1146, 'ZJG.JCH', NULL, 2, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:15', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 1.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1147, 'ZJG.JCH', NULL, 13, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:18', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 10.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1150, 'ZJG.JCH', NULL, 40, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:21', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 3.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1151, 'ZJG.JCH', NULL, 14, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:23', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 11.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1152, 'PUT.JLD', NULL, 9, NULL, 0, '中兴圣物流', 'C594A-S11103791F06', NULL, 6, '福州', '2018-06-30 00:00:00', 'BZFHC0181807300300', '莆田万辉国际城17802', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1153, 'ZJG.JCH', NULL, 11, NULL, 0, '灵鹤江苏线', 'Z512A02H-180005-A1', NULL, 6, '南京', '2018-06-30 00:00:00', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:27', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 2.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1154, 'PUT.JLD', NULL, 12, NULL, 0, '中兴圣物流', 'C594A-S11103791F05', NULL, 6, '福州', '2018-06-30 00:00:00', 'BZFHC0181807300300', '莆田万辉国际城17802', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:30', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.06, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1155, 'PUT.JLD', NULL, 9, NULL, 0, '中兴圣物流', 'C594A-S11103791F04', NULL, 6, '福州', '2018-06-30 00:00:00', 'BZFHC0181807300300', '莆田万辉国际城17802', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.66, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1156, 'QHI', NULL, 7, NULL, 0, '捷安物流', 'C898C-T11056621W03', NULL, 6, '广州', '2018-06-30 00:00:00', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1157, 'PUT.JLD', NULL, 11, NULL, 0, '中兴圣物流', 'C594A-S11103791F03', NULL, 6, '福州', '2018-07-31 16:27:23', 'BZFHC0181807300300', '莆田万辉国际城17802', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1158, 'PUT.JLD', NULL, 4, NULL, 0, '中兴圣物流', 'C594A-S11103791F02', NULL, 6, '福州', '2018-07-31 16:27:23', 'BZFHC0181807300300', '莆田万辉国际城17802', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1160, 'PUT.JLD', NULL, 3, NULL, 0, '中兴圣物流', 'C594A-N11103791K01', NULL, 6, '福州', '2018-07-31 16:27:23', 'BZFHC0181807300330', '莆田万辉国际城17802', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:00:47', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.17, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1162, 'PUT.JLD', NULL, 9, NULL, 0, '中兴圣物流', 'C594A-N11103791F01', NULL, 6, '福州', '2018-07-31 16:27:23', 'BZFHC0181807300300', '莆田万辉国际城17802', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1164, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C520-11W-160066-Z1-B1', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:00:56', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1170, 'QHI', NULL, 1, NULL, 0, '捷安物流', 'C898C-T11056621W04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1172, 'QHI', NULL, 6, NULL, 0, '捷安物流', 'C898C-T11056621W04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1174, 'FEX', NULL, 1, NULL, 0, 'DC自提', 'C516A-T11071341K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300245', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:01:14', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.09, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1175, 'FEX', NULL, 9, NULL, 0, 'DC自提', 'C516A-S11200981F03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300246', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.61, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1176, 'QHI', NULL, 2, NULL, 0, '捷安物流', 'C898C-T11056621W04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:01:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1177, 'FEX', NULL, 9, NULL, 0, 'DC自提', 'C516A-S11200981F02', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300246', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:19', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.72, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1178, 'QHI', NULL, 2, NULL, 0, '捷安物流', 'C898C-T11056621W03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:01:21', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1179, 'FEX', NULL, 8, NULL, 0, 'DC自提', 'C516A-S11200981F01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300246', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.67, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1180, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-N11256811W01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300245', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:01:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1181, 'QHI', NULL, 2, NULL, 0, '捷安物流', 'C898C-S11056621W02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:01:27', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1182, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-N10816651D03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300245', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1183, 'QHI', NULL, 8, NULL, 0, '捷安物流', 'C898C-S11056621W02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.33, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1184, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-I08733841F03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300245', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1185, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-A03962092F01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300245', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1190, 'QHI', NULL, 5, NULL, 0, '捷安物流', 'C898C-S11056621W01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 15:01:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1193, 'QHI', NULL, 1, NULL, 0, '捷安物流', 'C898-41K-179004-A1-BN-SP1', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '生产包装', 100000000, '2018-07-31 15:02:04', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1199, 'QHI', NULL, 5, NULL, 0, '捷安物流', 'C898C-T06241801K05', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '琼海市嘉积镇银海路（大印经典花园）1幢三楼西侧', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:02:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1230, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-X11188101S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:05:03', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1231, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-S10998671W01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:05:11', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1232, 'GUZ.HCL', NULL, 6, NULL, 0, '同鑫物流', 'C020A-S11027841F08', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:05:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1238, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-X11188101S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:05:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 4.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1241, 'GUZ.HCL', NULL, 56, NULL, 0, '同鑫物流', 'C020A-S11027841F01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:05:37', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 5.57, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1245, 'GUZ.HCL', NULL, 26, NULL, 0, '同鑫物流', 'C020A-S11251121K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:05:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1252, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-N10994161K02-B1', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:05:58', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1254, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-S11061491K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:01', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1255, 'ZJG.JCH', NULL, 2, NULL, 0, '灵鹤江苏线', 'C512A-T10933431K04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:01', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1256, 'ZJG.JCH', NULL, 139, NULL, 0, '灵鹤江苏线', 'C512A-T00026261K15', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:04', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 8.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1257, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-M09293561W01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:08', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1258, 'ZJG.JCH', NULL, 10, NULL, 0, '灵鹤江苏线', 'C512A-S11231301K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:08', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1259, 'ZJG.JCH', NULL, 12, NULL, 0, '灵鹤江苏线', 'C512A-S11231301F06', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300274', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:10', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.96, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1260, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-X09860721S05', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:12', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1261, 'ZJG.JCH', NULL, 8, NULL, 0, '灵鹤江苏线', 'C512A-S11231301F04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300274', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:16', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.66, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1262, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-X10986231S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1263, 'ZJG.JCH', NULL, 13, NULL, 0, '灵鹤江苏线', 'C512A-S11231301F03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300274', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1264, 'ZJG.JCH', NULL, 12, NULL, 0, '灵鹤江苏线', 'C512A-S11231301F01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300274', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:21', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1265, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-X09860721S05', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1266, 'ZJG.JCH', NULL, 26, NULL, 0, '灵鹤江苏线', 'C512A-S11212411K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1267, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-M09293561W01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1268, 'ZJG.JCH', NULL, 23, NULL, 0, '灵鹤江苏线', 'C512A-S11189401K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1269, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-N10905761K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:33', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1270, 'ZJG.JCH', NULL, 25, NULL, 0, '灵鹤江苏线', 'C512A-S10950371F14', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300312', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:36', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1271, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-T10998671K02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:36', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1272, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-B09293561K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1273, 'ZJG.JCH', NULL, 3, NULL, 0, '灵鹤江苏线', 'C512A-S10801571W04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:43', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1274, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-T10864411K02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:46', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1275, 'ZJG.JCH', NULL, 7, NULL, 0, '灵鹤江苏线', 'C512A-R11140041W03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:46', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.31, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1276, 'ZJG.JCH', NULL, 6, NULL, 0, '灵鹤江苏线', 'C512A-R11140041K04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1277, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-X10967331S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1278, 'ZJG.JCH', NULL, 2, NULL, 0, '灵鹤江苏线', 'C512A-R11140041K04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.78, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1279, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-T10997781K02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:06:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1280, 'ZJG.JCH', NULL, 7, NULL, 0, '灵鹤江苏线', 'C512A-R11140041K02', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:06:57', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1281, 'ZJG.JCH', NULL, 9, NULL, 0, '灵鹤江苏线', 'C512A-R11140041F08', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300274', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:00', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.79, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1282, 'ZJG.JCH', NULL, 31, NULL, 0, '灵鹤江苏线', 'C512A-R11140041F04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:03', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1283, 'ZJG.JCH', NULL, 14, NULL, 0, '灵鹤江苏线', 'C512A-R10933431F06', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.36, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1286, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-X10846881S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:07:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1287, 'ZJG.JCH', NULL, 44, NULL, 0, '灵鹤江苏线', 'C512A-R10933431F04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300274', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 4.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1288, 'ZJG.JCH', NULL, 10, NULL, 0, '灵鹤江苏线', 'C512A-R10933431F03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1289, 'ZJG.JCH', NULL, 22, NULL, 0, '灵鹤江苏线', 'C512A-R10933431F01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1290, 'ZJG.JCH', NULL, 12, NULL, 0, '灵鹤江苏线', 'C512A-R10933431D02', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1291, 'ZJG.JCH', NULL, 19, NULL, 0, '灵鹤江苏线', 'C512A-R10933431D01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:39', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1292, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-N10994161K02-B2', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:39', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1293, 'ZJG.JCH', NULL, 8, NULL, 0, '灵鹤江苏线', 'C512A-N10950371K04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1294, 'ZJG.JCH', NULL, 18, NULL, 0, '灵鹤江苏线', 'C512A-N10933431K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300224', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', 'DC自提', '生产包装', 100000000, '2018-07-31 15:07:45', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1299, 'ZHJ', NULL, 3, NULL, 0, 'DC自提', 'C511C-T11165241K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300277', '冠城国际3幢105室', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:07:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.41, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1300, 'ZHJ', NULL, 1, NULL, 0, 'DC自提', 'C511C-S09847481K03', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300277', '冠城国际3幢105室', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1301, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-X11188101S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1302, 'ZHJ', NULL, 7, NULL, 0, 'DC自提', 'C511C-N11043871W01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300277', '冠城国际3幢105室', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1303, 'ZHJ', NULL, 1, NULL, 0, 'DC自提', 'C511C-M11135501W02', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300277', '冠城国际3幢105室', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:08', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1304, 'GUZ.HCL', NULL, 3, NULL, 0, '同鑫物流', 'C020A-N11188101K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:08:08', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.48, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1305, 'YAZ', NULL, 6, NULL, 0, 'DC自提', 'C511B-N00027421D04', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300266', '扬中市明珠大道29-16、29-17号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:10', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1306, 'GUZ.HCL', NULL, 3, NULL, 0, '同鑫物流', 'C020A-S11027841K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:14', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1307, 'YIX', NULL, 1, NULL, 0, '灵鹤江苏线', 'C510B-R00024161K01', NULL, 6, '南京', '2018-07-31 16:27:23', 'BZFHC0181807300253', '江苏省宜兴市红星美凯龙2楼博洛尼橱柜江苏省宜兴市巷头东路128号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1309, 'GUZ.HCL', NULL, 4, NULL, 0, '同鑫物流', 'C020A-X10846881S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:08:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1312, 'GUZ.HCL', NULL, 18, NULL, 0, '同鑫物流', 'C020A-G11106611K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:08:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.85, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1314, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-X11188101S02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:08:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1315, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-A08249263K31', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:08:36', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1316, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T10905761K03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:08:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.75, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1317, 'GUZ.HCL', NULL, 5, NULL, 0, '同鑫物流', 'C020A-X10846881S01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:08:56', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1318, 'GUZ.HCL', NULL, 10, NULL, 0, '同鑫物流', 'C020A-S11087461F05', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:02', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.76, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1319, 'GUZ.HCL', NULL, 13, NULL, 0, '同鑫物流', 'C020A-S11087461F06', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:08', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1320, 'GUZ.HCL', NULL, 20, NULL, 0, '同鑫物流', 'C020A-N11205981F03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:12', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.68, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1321, 'JXU', NULL, 20, NULL, 0, 'DC自提', 'Z354B01H-180035-A1', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300285', '山西省介休市弘盛昌建材城一层A区003', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:14', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1322, 'GUZ.HCL', NULL, 8, NULL, 0, '同鑫物流', 'C020A-S10986231F01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1323, 'GUZ.HCL', NULL, 10, NULL, 0, '同鑫物流', 'C020A-S10998671F03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1324, 'GUZ.HCL', NULL, 20, NULL, 0, '同鑫物流', 'C020A-S11087461F04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.54, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1325, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-S10986231F02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1326, 'GUZ.HCL', NULL, 9, NULL, 0, '同鑫物流', 'C020A-S11205981F04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:35', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.58, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1327, 'GUZ.HCL', NULL, 18, NULL, 0, '同鑫物流', 'C020A-S09779831F03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.34, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1328, 'JXU', NULL, 5, NULL, 0, 'DC自提', 'Z354B01H-180035-A1', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300285', '山西省介休市弘盛昌建材城一层A区003', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:09:53', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1329, 'GUZ.HCL', NULL, 15, NULL, 0, '同鑫物流', 'C020A-S09779831F02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1330, 'GUZ.HCL', NULL, 2, NULL, 0, '同鑫物流', 'C020A-S10967331F05-SP1', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:09:59', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1331, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'Z354B01H-180035-A1', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300285', '山西省介休市弘盛昌建材城一层A区003', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:10:00', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1332, 'GUZ.HCL', NULL, 8, NULL, 0, '同鑫物流', 'C020A-S10864411F01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:03', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1334, 'GUZ.HCL', NULL, 10, NULL, 0, '同鑫物流', 'C020A-S10998671F02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.51, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1336, 'GUZ.HCL', NULL, 9, NULL, 0, '同鑫物流', 'C020A-S11027841F06', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:14', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.72, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1337, 'GUZ.HCL', NULL, 12, NULL, 0, '同鑫物流', 'C020A-N11205981F01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:18', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.86, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1340, 'GUZ.HCL', NULL, 13, NULL, 0, '同鑫物流', 'C020A-S10821961F01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1342, 'GUZ.HCL', NULL, 20, NULL, 0, '同鑫物流', 'C020A-S11027841F04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.68, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1343, 'GUZ.HCL', NULL, 25, NULL, 0, '同鑫物流', 'C020A-S11027841F03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:30', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1345, 'GUZ.HCL', NULL, 1, NULL, 0, '同鑫物流', 'C020A-T09860721F04', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1347, 'GUZ.HCL', NULL, 8, NULL, 0, '同鑫物流', 'C020A-B11027841F02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300270', '广州市白云区太和镇黄庄北路121号沙太', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.53, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1349, 'GUG', NULL, 2, NULL, 0, '捷安物流', 'C775A-S11057801K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300267', '广西贵港市港北区观天下家居建材广场', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:10:43', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.13, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1352, 'LUL', NULL, 24, NULL, 0, '金云山西线', 'C358A-S11067651K01', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300356', '山西省吕梁市', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.95, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1354, 'LFN.FH', NULL, 2, NULL, 0, '金云山西线', 'C357B-M09236551F10', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300355', '滨河湾9-西-4层西', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:53', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1357, 'DGA', NULL, 13, NULL, 0, '同鑫物流', 'C769B-U11079441F01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300217', '东莞', 'DC自提', '生产包装', 100000000, '2018-07-31 15:10:57', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.96, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1359, 'DGA', NULL, 15, NULL, 0, '同鑫物流', 'C769B-U11079441F02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300217', '东莞', 'DC自提', '生产包装', 100000000, '2018-07-31 15:11:01', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1362, 'DGA', NULL, 2, NULL, 0, '同鑫物流', 'C769B-S10908161K03', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '生产包装', 100000000, '2018-07-31 15:11:14', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1368, 'DGA', NULL, 53, NULL, 0, '同鑫物流', 'C769B-U11079441F05', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '生产包装', 100000000, '2018-07-31 15:11:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 5.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1372, 'DGA', NULL, 11, NULL, 0, '同鑫物流', 'C769B-U11059451K02', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '生产包装', 100000000, '2018-07-31 15:11:47', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1373, 'DGA', NULL, 1, NULL, 0, '同鑫物流', 'C769B-S11079441K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:11:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1374, 'DGA', NULL, 1, NULL, 0, '同鑫物流', 'C769B-S11059451K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:11:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1375, 'DGA', NULL, 3, NULL, 0, '同鑫物流', 'C769B-S11059451K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:11:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.43, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1376, 'DGA', NULL, 3, NULL, 0, '同鑫物流', 'C769B-S11079441K01', NULL, 6, '广州', '2018-07-31 16:27:23', 'BZFHC0181807300218', '东莞', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:12:03', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.43, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1377, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-S11121471K01', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300285', '山西省介休市弘盛昌建材城一层A区003', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:12:11', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1378, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-R11143591K02', NULL, 6, '太原', '2018-07-31 16:27:23', 'BZFHC0181807300285', '山西省介休市弘盛昌建材城一层A区003', 'DC自提', '库存品包装', 100000000, '2018-07-31 15:12:14', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1384, 'HHC.BLN', NULL, 20, NULL, 0, 'DC自提', 'C712C-N11078871F02', NULL, 6, '武汉', '2018-07-31 16:27:23', 'BZFHC0181807290160', '湖北省,孝感市,汉川市欧亚达仙女大道469-31号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:21:21', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.96, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1385, 'HHC.BLN', NULL, 13, NULL, 0, 'DC自提', 'C712C-N11078871F08', NULL, 6, '武汉', '2018-07-31 16:27:23', 'BZFHC0181807290160', '湖北省,孝感市,汉川市欧亚达仙女大道469-31号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:21:36', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1389, 'HHC.BLN', NULL, 19, NULL, 0, 'DC自提', 'C712C-N11078871K01', NULL, 6, '武汉', '2018-07-31 16:27:23', 'BZFHC0181807290160', '湖北省,孝感市,汉川市欧亚达仙女大道469-31号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:55:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1390, 'HHC.BLN', NULL, 4, NULL, 0, 'DC自提', 'C712C-N11078871F06', NULL, 6, '武汉', '2018-07-31 16:27:23', 'BZFHC0181807290160', '湖北省,孝感市,汉川市欧亚达仙女大道469-31号', 'DC自提', '生产包装', 100000000, '2018-07-31 15:55:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1391, 'ASH', '安顺', 2, NULL, 0, '晨速物流', 'C853A-S11256121F01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '贵州省安顺市西秀区东关办事处东关材二组65号', 'DC自提', '生产包装', 100000000, '2018-07-31 16:12:44', '0', '1', 0, 'ADCK201808020525', '正单', '箱体板类', NULL, '1', NULL, 0.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1392, 'MES', NULL, 19, NULL, 0, '大田物流', 'C028B-S11079081F03', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:12:44', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.51, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1393, 'MES', NULL, 13, NULL, 0, '大田物流', 'C028B-N11086241F01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1395, 'MES', NULL, 12, NULL, 0, '大田物流', 'C028B-S11207421F05', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1396, 'MES', NULL, 11, NULL, 0, '大田物流', 'C028B-S11207421F01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1398, 'MES', NULL, 13, NULL, 0, '大田物流', 'C028B-S11207421F02', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1399, 'MES', NULL, 21, NULL, 0, '大田物流', 'C028B-S11207421F03', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.68, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1400, 'MES', NULL, 5, NULL, 0, '大田物流', 'C028B-R11178631F01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1401, 'ASH', NULL, 2, NULL, 0, '晨速物流', 'C853A-S08429881F01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '贵州省安顺市西秀区东关办事处东关材二组65号', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1402, 'MES', NULL, 11, NULL, 0, '大田物流', 'C028B-S11207421F04', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:13:23', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.86, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1403, 'ASH', NULL, 2, NULL, 0, '晨速物流', 'C853A-S11223161F01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280089', '贵州省安顺市西秀区东关办事处东关材二组65号', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1404, 'MES', NULL, 2, NULL, 0, '大田物流', 'C028B-S11214701K01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1407, 'PZH', NULL, 1, NULL, 0, '大田物流', 'C812A-T00006331K03', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '攀枝花市仁和区同乐世界内负二楼C8026、8027、8008号', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1408, 'ASH', NULL, 5, NULL, 0, '晨速物流', 'C853A-T04034241K07', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '贵州省安顺市西秀区东关办事处东关材二组65号', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1409, 'MES', NULL, 38, NULL, 0, '大田物流', 'C028B-N11178631K01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.86, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1410, 'MES', NULL, 1, NULL, 0, '大田物流', 'C028B-B11146401F02', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1411, 'MES', NULL, 27, NULL, 0, '大田物流', 'C028B-B11146401K01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1412, 'MES', NULL, 7, NULL, 0, '大田物流', 'C028B-S11086241W04', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1413, 'MES', NULL, 11, NULL, 0, '大田物流', 'C028B-S11079081D01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1417, 'MES', NULL, 5, NULL, 0, '大田物流', 'C028B-T11147211K02', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.58, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1419, 'ASH', '安顺', 1, NULL, 0, '晨速物流', 'C853A-T04034241K12', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '贵州省安顺市西秀区东关办事处东关材二组65号', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, 'CDCK201808010001', '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1420, 'MES', NULL, 5, NULL, 0, '大田物流', 'C028B-N11086241W06', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1421, 'MES', NULL, 16, NULL, 0, '大田物流', 'C028B-B11146401K04', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1422, 'MES', NULL, 8, NULL, 0, '大田物流', 'C028B-S11086241W03', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1423, 'MES', NULL, 5, NULL, 0, '大田物流', 'C028B-S11079081D04', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1424, 'MES', NULL, 1, NULL, 0, '大田物流', 'C028B-X11147671S01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1429, 'MES', NULL, 16, NULL, 0, '大田物流', 'C028B-N09022191F03', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1432, 'MES', NULL, 10, NULL, 0, '大田物流', 'C028B-N11086241W02', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:29:34', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1433, 'MES', NULL, 11, NULL, 0, '大田物流', 'C028B-P11086241F10', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:30:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1435, 'GUA', NULL, 2, NULL, 0, '大田物流', 'C826A-X11066851S01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省广安市广安区广宁南路425号2幢1单元201号', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:30:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1438, 'MES', NULL, 15, NULL, 0, '大田物流', 'C028B-U10971131K01', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:30:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1439, 'MES', NULL, 5, NULL, 0, '大田物流', 'C028B-T11146401K05', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:30:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1440, 'MES', NULL, 6, NULL, 0, '大田物流', 'C028B-N11146401W02', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-07-31 16:30:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1442, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-T10971131K02', NULL, 6, '成都', '2018-07-31 16:27:23', 'BZFHC0181807280090', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:30:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1448, 'ANS.BLN', NULL, 5, NULL, 0, '吉顺隆辽宁线', 'C412A-X04064922S09', NULL, 6, '沈阳', '2018-07-31 16:27:23', 'BZFHC0181807300306', '辽宁省鞍山市铁东区安乐街39栋', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:32:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1449, 'ANS.BLN', NULL, 1, NULL, 0, '吉顺隆辽宁线', 'C412A-T04064922K06', NULL, 6, '沈阳', '2018-07-31 16:27:23', 'BZFHC0181807300306', '辽宁省鞍山市铁东区安乐街39栋', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:32:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1450, 'ANS.BLN', NULL, 1, NULL, 0, '吉顺隆辽宁线', 'C412A-T04064922K06', NULL, 6, '沈阳', '2018-07-31 16:27:23', 'BZFHC0181807300306', '辽宁省鞍山市铁东区安乐街39栋', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:32:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1451, 'ANS.BLN', '鞍山博洛尼', 5, NULL, 0, '吉顺隆辽宁线', 'C412A-T04064922K05', NULL, 6, '沈阳', '2018-07-31 16:27:23', 'BZFHC0181807300306', '辽宁省鞍山市铁东区安乐街39栋', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:32:50', '0', '1', 0, 'ADCK201808020537', '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1452, 'ANS.BLN', NULL, 5, NULL, 0, '吉顺隆辽宁线', 'C412A-T04064922K05', NULL, 6, '沈阳', '2018-07-31 16:27:23', 'BZFHC0181807300306', '辽宁省鞍山市铁东区安乐街39栋', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:32:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1453, 'ANS.BLN', NULL, 5, NULL, 0, '吉顺隆辽宁线', 'C412A-T04064922K05', NULL, 6, '沈阳', '2018-07-31 16:27:23', 'BZFHC0181807300306', '辽宁省鞍山市铁东区安乐街39栋', 'DC自提', '库存品包装', 100000000, '2018-07-31 16:32:50', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1456, 'HHC.BLN', NULL, 14, NULL, 0, 'DC自提', 'C712C-S11247371K01', NULL, 6, '武汉', '2018-06-30 00:00:00', 'BZFHC0181807290160', '湖北省,孝感市,汉川市欧亚达仙女大道469-31号', 'DC自提', '生产包装', 100000000, '2018-07-31 16:49:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.62, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1479, 'ZSH', NULL, 8, NULL, 0, '捷安物流', 'C760A-X11103141S01', NULL, 6, '广州', '2018-07-30 00:00:00', 'BZFHC0181807300267', '广东省中山市西区街道木围仓街8号之四仓', 'DC自提', '库存品包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1480, 'ZSH', NULL, 23, NULL, 0, '捷安物流', 'C760A-S11166731F04', NULL, 6, '广州', '2018-07-31 00:00:00', 'BZFHC0181807300270', '广东省中山市西区街道木围仓街8号之四仓', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.85, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1481, 'ZJG', NULL, 29, NULL, 0, '捷安物流', 'C759A-N11065811K01', NULL, 6, '广州', '2018-07-30 00:00:00', 'BZFHC0181807300267', '广东省湛江市开发区明园路3号海滨御景豪庭5号商铺', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1482, 'ZJG', NULL, 1, NULL, 0, '捷安物流', 'C759A-B10936151K01-B1', NULL, 6, '广州', '2018-07-30 00:00:00', 'BZFHC0181807300267', '广东省湛江市开发区明园路3号海滨御景豪庭5号商铺', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1483, 'SYA', NULL, 18, NULL, 0, '捷安物流', 'C898B-S11159441F01', NULL, 6, '广州', '2018-07-31 00:00:00', 'BZFHC0181807300270', '海南省陵水县英州镇新坡村老粮所', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1484, 'SYA', NULL, 8, NULL, 0, '捷安物流', 'C898B-S04537261F01', NULL, 6, '广州', '2018-07-31 00:00:00', 'BZFHC0181807300270', '海南省陵水县英州镇新坡村老粮所', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.46, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1489, 'HAK.KD', NULL, 6, NULL, 0, 'DC自提', 'Z898A02H-180007-A1', NULL, 6, '广州', '2018-07-30 00:00:00', 'BZFHC0181807300267', '海口好饰家家居文化广场', 'DC自提', '库存品包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 1.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1490, 'HAK.KD', NULL, 2, NULL, 0, 'DC自提', 'C898A-R08310861F05', NULL, 6, '广州', '2018-07-30 00:00:00', 'BZFHC0181807300270', '海口好饰家家居文化广场', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1491, 'HAK.KD', NULL, 2, NULL, 0, 'DC自提', 'C898A-S10866831F01-SP1', NULL, 6, '广州', '2018-07-30 00:00:00', 'BZFHC0181807300270', '海口好饰家家居文化广场', 'DC自提', '生产包装', 100000000, '2018-07-31 17:21:59', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.13, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1517, 'HEF.DG', NULL, 1, NULL, 0, 'DC自提', 'C551A-U09718861K03', NULL, 6, '合肥', '2018-07-30 00:00:00', 'BZFHC0181807300295', '安徽省合肥市蜀山区潜山路新华国际广场B座705', 'DC自提', '生产包装', 100000000, '2018-07-31 17:24:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1519, 'HEF.DG', NULL, 3, NULL, 0, 'DC自提', 'C551A-S09967111F10', NULL, 6, '合肥', '2018-07-30 00:00:00', 'BZFHC0181807300295', '安徽省合肥市蜀山区潜山路新华国际广场B座705', 'DC自提', '生产包装', 100000000, '2018-07-31 17:24:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1522, 'HEF.DG', NULL, 6, NULL, 0, 'DC自提', 'C551A-R11086951F05', NULL, 6, '合肥', '2018-07-30 00:00:00', 'BZFHC0181807300295', '安徽省合肥市蜀山区潜山路新华国际广场B座705', 'DC自提', '生产包装', 100000000, '2018-07-31 17:24:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1525, 'HEF.DG', NULL, 14, NULL, 0, 'DC自提', 'C551A-N11104801F01', NULL, 6, '合肥', '2018-07-30 00:00:00', 'BZFHC0181807300295', '安徽省合肥市蜀山区潜山路新华国际广场B座705', 'DC自提', '生产包装', 100000000, '2018-07-31 17:24:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1527, 'HEF.DG', NULL, 1, NULL, 0, 'DC自提', 'C551A-C10939821W01', NULL, 6, '合肥', '2018-07-30 00:00:00', 'BZFHC0181807300295', '安徽省合肥市蜀山区潜山路新华国际广场B座705', 'DC自提', '生产包装', 100000000, '2018-07-31 17:24:42', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1564, 'XNI', NULL, 1, NULL, 0, 'DC自提', 'C715-11K-175002-A1-XB-TB1', NULL, 6, '武汉', '2018-07-30 00:00:00', 'BZFHC0181807300286', '湖北省咸宁市天成家居二楼A209', 'DC自提', '库存品包装', 100000000, '2018-07-31 17:46:25', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1575, 'HHC.BLN', NULL, 22, NULL, 0, 'DC自提', 'C712C-N11078871F07', NULL, 6, '武汉', '2018-07-30 00:00:00', 'BZFHC0181807300288', '汉川欧亚达商贸城8号楼134-135-1-3', 'DC自提', '生产包装', 100000000, '2018-07-31 17:46:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1579, 'ANL', '安陆', 1, NULL, 0, 'DC自提', 'C712A-I09327521K01', NULL, 6, '武汉', '2018-07-30 00:00:00', 'BZFHC0181807300284', '湖北省安陆市碧涢路148号', 'DC自提', '生产包装', 100000000, '2018-07-31 17:46:25', '0', '1', 0, 'ADCK201808020535', '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1580, 'XFA', NULL, 4, NULL, 0, '君意通物流', 'C710A-S11078411D05', NULL, 6, '武汉', '2018-07-30 00:00:00', 'BZFHC0181807300288', '湖北襄樊市前进路9号天丽国际家居建材博览中心5层A31-33', 'DC自提', '生产包装', 100000000, '2018-07-31 17:46:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1600, 'YIY', NULL, 1, NULL, 0, '康程物流', 'C737-11D-160002-A1-SP2-N', NULL, 6, '长沙', '2018-07-30 00:00:00', 'BZFHC0181807300313', '浙江省温岭市万昌中路483号', 'DC自提', '生产包装', 100000000, '2018-07-31 23:47:03', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1641, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-N11086241W07', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:04', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1674, 'DEY', NULL, 1, NULL, 0, '大田物流', 'C838A-T05248221K02', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省成都市武侯区永盛南街8号4栋3单元6号', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:58:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1675, 'DEY', NULL, 2, NULL, 0, '大田物流', 'C838-11F-160053-A1-BS-B1', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省成都市武侯区永盛南街8号4栋3单元6号', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:25', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1676, 'DEY', NULL, 6, NULL, 0, '大田物流', 'C838-11F-160026-A1-B2', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300347', '四川省成都市武侯区永盛南街8号4栋3单元6号', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:25', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1700, 'LZS.BLN', NULL, 4, NULL, 0, '大田物流', 'C830A-S08069991K04', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300347', '四川省泸州市泸县牛滩镇上横街46号3单元6室', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:43', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1704, 'GUA', NULL, 8, NULL, 0, '大田物流', 'C826A-D11078021F02', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300347', '四川省广安市广安区广宁南路425号2幢1单元201号', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:43', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.80, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1717, 'PZH', NULL, 1, NULL, 0, '大田物流', 'C812A-M10858581K01-SP1', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '攀枝花市仁和区同乐世界内负二楼C8026、8027、8008号', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:48', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1718, 'MES', NULL, 10, NULL, 0, '大田物流', 'C028B-X11141161S01', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:58:48', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 6.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1719, 'MES', NULL, 16, NULL, 0, '大田物流', 'C028B-U11185791K01', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.75, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1721, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-T11147701K01', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:58:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1722, 'MES', NULL, 2, NULL, 0, '大田物流', 'C028B-S11207421F06', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300228', '四川省眉山市东城区碧华林17栋2单元302室', 'DC自提', '生产包装', 100000000, '2018-08-01 08:58:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1794, 'CHQ.YCH', NULL, 2, NULL, 0, '晨速物流', 'C023A-X00028981S02', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', '重庆市永川区胜利北路8号', 'DC自提', '库存品包装', 100000000, '2018-08-01 08:59:35', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1795, 'CHQ.YCH', NULL, 2, NULL, 0, '晨速物流', 'C023A-S10985921K03', NULL, 5, '成都', '2018-07-30 00:00:00', 'BZFHC0181807300229', '重庆市永川区胜利北路8号', 'DC自提', '生产包装', 100000000, '2018-08-01 08:59:35', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1805, 'RUA', NULL, 2, NULL, 0, '上海益递物流', 'C577-21D-170032-A1-SP1-N', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省瑞安市瑞祥新区罗阳大道一品一楼商业区104室', '瀛海世纪物流', '生产包装', 100000000, '2018-08-01 11:03:02', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1832, 'CAN', NULL, 27, NULL, 0, '上海益递物流', 'C577C-M11147191F04', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', 100000000, '2018-08-01 11:05:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1835, 'CAN', NULL, 8, NULL, 0, '上海益递物流', 'C577C-M11147191F05', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', 100000000, '2018-08-01 11:05:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.58, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1837, 'JSH', NULL, 6, NULL, 0, '上海益递物流', 'C570A-S07682691F01', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300247', '浙江省江山市北泉街191号', '益递物流有限公司', '生产包装', 100000000, '2018-08-01 11:05:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.27, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1838, 'LXI.BLN', NULL, 5, NULL, 0, '上海益递物流', 'C579C-S09239611F18', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300247', '浙江省兰溪市永顺路110-118号', '益递物流有限公司', '生产包装', 100000000, '2018-08-01 11:05:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.27, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1839, 'LXI.BLN', NULL, 8, NULL, 0, '上海益递物流', 'C579C-S09239611F19', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300247', '浙江省兰溪市永顺路110-118号', '益递物流有限公司', '生产包装', 100000000, '2018-08-01 11:05:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.48, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1848, 'LXI.BLN', NULL, 12, NULL, 0, '上海益递物流', 'C579C-V10945311K01', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300248', '浙江省兰溪市永顺路110-118号', '益递物流有限公司', '生产包装', 100000000, '2018-08-01 11:05:40', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1860, 'SHX.BLN', NULL, 11, NULL, 0, '上海益递物流', 'C575C-M11212471F03', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', '浙江省绍兴市越城区斜桥弄48号2室', '吉顺浙江线', '生产包装', 100000000, '2018-08-01 11:05:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.77, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1866, 'SHX.BLN', NULL, 3, NULL, 0, '上海益递物流', 'C575C-S10950661F06', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', '浙江省绍兴市越城区斜桥弄48号2室', '吉顺浙江线', '生产包装', 100000000, '2018-08-01 11:05:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1870, 'SHX.BLN', NULL, 11, NULL, 0, '上海益递物流', 'C575C-M11212471F06', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', '浙江省绍兴市越城区斜桥弄48号2室', '吉顺浙江线', '生产包装', 100000000, '2018-08-01 11:05:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.67, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1874, 'WEZ', NULL, 2, NULL, 0, '上海益递物流', 'C577A-S05351671F06', NULL, 5, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300294', '华府5-802', '吉顺浙江线', '生产包装', 100000000, '2018-08-01 11:05:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1897, 'TSD.BLN', NULL, 1, NULL, 0, '三志物流', 'C532A-T00027911K06', NULL, 5, '济南', '2018-07-31 00:00:00', 'BZFHC0181807300302', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-01 12:06:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1905, 'TSD.BLN', NULL, 1, NULL, 0, '三志物流', 'C532A-T00027911K06', NULL, 5, '济南', '2018-07-31 00:00:00', 'BZFHC0181807300302', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-01 12:06:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1916, 'DGY', NULL, 2, NULL, 0, '三志物流', 'C546A-S08580701W01', NULL, 5, '济南', '2018-07-31 00:00:00', 'BZFHC0181807300302', '山东省东营市东营区泰西巷8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-01 12:07:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1917, 'TSD.BLN', NULL, 1, NULL, 0, '三志物流', 'C532A-T00027911K06', NULL, 5, '济南', '2018-07-31 00:00:00', 'BZFHC0181807300302', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-01 12:08:04', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1956, 'XFA', NULL, 14, NULL, 0, '君意通物流', 'C710A-S10802331F05', NULL, 4, '武汉', '2018-07-31 00:00:00', 'BZFHC0181807300305', '湖北襄樊市前进路9号天丽国际家居建材博览中心5层A31-33', '吉顺湖北线', '生产包装', 100000000, '2018-08-02 07:55:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1991, 'ZHJ', NULL, 8, NULL, 0, 'DC自提', 'C511-11K-170038-A1-SP1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300343', '冠城国际3幢105室', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1992, 'ZHJ', NULL, 1, NULL, 0, 'DC自提', 'C511C-S10986641K04', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300343', '冠城国际3幢105室', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1993, 'ZHJ', NULL, 3, NULL, 0, 'DC自提', 'C511C-S11301471K02', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300343', '冠城国际3幢105室', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (1995, 'YAZ', NULL, 25, NULL, 0, 'DC自提', 'C511B-S00027421F05', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300341', '扬中市明珠大道29-16、29-17号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2009, 'XYI.BLN', NULL, 22, NULL, 0, '灵鹤江苏线', 'C516E-S06934961F12', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300339', '江苏省新沂市新安镇大桥西路19号国际商贸城三期1号楼01、02', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2010, 'XYI.BLN', NULL, 2, NULL, 0, '灵鹤江苏线', 'C516E-X06934961S18', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300339', '江苏省新沂市新安镇大桥西路19号国际商贸城三期1号楼01、02', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2011, 'XYI.BLN', NULL, 1, NULL, 0, '灵鹤江苏线', 'C516E-X06934961S22', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300339', '江苏省新沂市新安镇大桥西路19号国际商贸城三期1号楼01、02', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2026, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-X00006492S06', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300334', '南通市濠西路尚德城邦D座102室', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2027, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-X10695091S01', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300334', '南通市濠西路尚德城邦D座102室', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2043, 'NKG', NULL, 6, NULL, 0, '灵鹤江苏线', 'Z025A03H-180122-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2046, 'NKG', NULL, 4, NULL, 0, '灵鹤江苏线', 'Z025A04H-180116-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2047, 'NKG', NULL, 12, NULL, 0, '灵鹤江苏线', 'Z025A04H-180116-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2050, 'NKG', NULL, 8, NULL, 0, '灵鹤江苏线', 'Z025A04H-180120-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.65, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2051, 'NKG', NULL, 4, NULL, 0, '灵鹤江苏线', 'Z025A04H-180123-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2052, 'NKG', NULL, 11, NULL, 0, '灵鹤江苏线', 'Z025A05H-180118-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2053, 'NKG', NULL, 9, NULL, 0, '灵鹤江苏线', 'Z025A05H-180118-A1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300333', '阅城国际悠然园29-1101', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.35, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2057, 'LSU', NULL, 2, NULL, 0, 'DC自提', 'C025C-A10950721D02', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300329', '南京市江宁区禄口街道张桥村北庄98-1号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2058, 'LSU', NULL, 2, NULL, 0, 'DC自提', 'C025C-A10950721F01-SP1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300329', '南京市江宁区禄口街道张桥村北庄98-1号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2059, 'LSU', NULL, 2, NULL, 0, 'DC自提', 'C025C-A10950721F02-SP1', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300329', '南京市江宁区禄口街道张桥村北庄98-1号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2060, 'LSU', NULL, 1, NULL, 0, 'DC自提', 'C025C-A10950721K03', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300329', '南京市江宁区禄口街道张桥村北庄98-1号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2078, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-S08733841F07', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300323', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:16', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2079, 'FEX', NULL, 1, NULL, 0, 'DC自提', 'C516A-T00006311K04', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300323', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '库存品包装', 100000000, '2018-08-02 14:08:16', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2080, 'FEX', NULL, 8, NULL, 0, 'DC自提', 'C516A-V07113811K04', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300323', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:16', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2081, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-V08733841K03', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300323', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:16', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2082, 'FEX', NULL, 3, NULL, 0, 'DC自提', 'Z516-41H-170067-A1-B4', NULL, 4, '南京', '2018-07-30 00:00:00', 'BZFHC0181807300323', '江苏省徐州市丰县御景园第S1幢1单元121-122号', 'DC自提', '生产包装', 100000000, '2018-08-02 14:08:16', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.35, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2096, 'WEZ', NULL, 5, NULL, 0, '上海益递物流', 'C577A-N00020121D01', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '华府5-802', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2098, 'SHX.BLN', NULL, 5, NULL, 0, '上海益递物流', 'C575C-V11230061K01', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省绍兴市越城区斜桥弄48号2室', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2099, 'SHX.BLN', NULL, 4, NULL, 0, '上海益递物流', 'C575C-P11027101D01', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省绍兴市越城区斜桥弄48号2室', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2100, 'RUA', NULL, 25, NULL, 0, '上海益递物流', 'C577D-N07350901F01', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省瑞安市瑞祥新区罗阳大道一品一楼商业区104室', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2107, 'LXI.BLN', NULL, 3, NULL, 0, '上海益递物流', 'C579C-X09239611S01', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省兰溪市永顺路110-118号', '瀛海世纪物流', '库存品包装', 100000000, '2018-08-02 15:54:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2126, 'JAS.BLN', NULL, 2, NULL, 0, '上海益递物流', 'C573C-R11216991D01', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '嘉善县晋阳东路23号', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:17', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2127, 'JAS.BLN', NULL, 4, NULL, 0, '上海益递物流', 'C573C-S10906931F04', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '嘉善县晋阳东路23号', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2139, 'HIN.BLN', NULL, 1, NULL, 0, '上海益递物流', 'C573B-T11031571F03', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省海宁市缔艺家居广场二层', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2140, 'HIN.BLN', NULL, 1, NULL, 0, '上海益递物流', 'C573B-T06139221D04', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省海宁市缔艺家居广场二层', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2144, 'CAN', NULL, 2, NULL, 0, '上海益递物流', 'C577C-M11147191F08', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省苍南县灵溪镇上江小区1-5幢', '瀛海世纪物流', '生产包装', 100000000, '2018-08-02 15:54:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2145, 'CAN', NULL, 1, NULL, 0, '上海益递物流', 'C577C-T11016601K02', NULL, 4, '杭州', '2018-07-31 00:00:00', 'BZFHC0181807300275', '浙江省苍南县灵溪镇上江小区1-5幢', '瀛海世纪物流', '库存品包装', 100000000, '2018-08-02 15:54:26', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2209, 'LZS.BLN', NULL, 1, NULL, 0, '大田物流', 'C830A-T04459312K03', NULL, 3, '成都', '2018-07-31 00:00:00', 'BZFHC0181807310010', '四川省泸州市泸县牛滩镇上横街46号3单元6室', '新博通美达-成都仓', '库存品包装', 100000000, '2018-08-03 09:12:33', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2210, 'LZS.BLN', NULL, 1, NULL, 0, '大田物流', 'C830A-T04459312K03', NULL, 3, '成都', '2018-07-31 00:00:00', 'BZFHC0181807310010', '四川省泸州市泸县牛滩镇上横街46号3单元6室', '新博通美达-成都仓', '库存品包装', 100000000, '2018-08-03 09:12:33', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2211, 'LZS.BLN', NULL, 1, NULL, 0, '大田物流', 'C830A-T04459312K03', NULL, 3, '成都', '2018-07-31 00:00:00', 'BZFHC0181807310010', '四川省泸州市泸县牛滩镇上横街46号3单元6室', '新博通美达-成都仓', '库存品包装', 100000000, '2018-08-03 09:12:33', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2242, 'JUJ.BLN', NULL, 20, NULL, 0, '中路物流', 'C792A-S11197291K01', NULL, 3, '南昌', '2018-08-01 00:00:00', 'BZFHC0181807310187', '江西省九江市禧徕乐国际家居生活广场二层', 'DC自提', '生产包装', 100000000, '2018-08-03 09:39:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2243, 'JUJ.BLN', NULL, 4, NULL, 0, '中路物流', 'C792A-S11044531K04', NULL, 3, '南昌', '2018-08-01 00:00:00', 'BZFHC0181807310187', '江西省九江市禧徕乐国际家居生活广场二层', 'DC自提', '生产包装', 100000000, '2018-08-03 09:39:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2252, 'JUJ.BLN', NULL, 7, NULL, 0, '中路物流', 'C792A-M10966951F01-SP1', NULL, 3, '南昌', '2018-07-31 00:00:00', 'BZFHC0181807300231', '江西省九江市禧徕乐国际家居生活广场二层', '吉顺江西线', '生产包装', 100000000, '2018-08-03 09:39:28', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.53, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2257, 'YIK', NULL, 3, NULL, 0, '吉顺隆辽宁线', 'C417A-T06390281K02', NULL, 3, '沈阳', '2018-08-02 00:00:00', 'BZFHC0181808010154', '辽宁省营口市老边区路南镇居然之家5-037', '吉顺隆辽宁线', '库存品包装', 100000000, '2018-08-03 09:42:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2278, 'FXI.BLN', NULL, 3, NULL, 0, '吉顺隆辽宁线', 'C418A-M09499431K02', NULL, 3, '沈阳', '2018-08-02 00:00:00', 'BZFHC0181808010154', '辽宁省阜新市细河区民族街20-20楼4号', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-03 09:42:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2279, 'FSU', NULL, 11, NULL, 0, '吉顺隆辽宁线', 'C413A-S11018041F05', NULL, 3, '沈阳', '2018-08-02 00:00:00', 'BZFHC0181808010154', '辽宁省抚顺市顺城区区政府对面', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-03 09:42:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2280, 'FSU', NULL, 9, NULL, 0, '吉顺隆辽宁线', 'C413A-T11117931K02', NULL, 3, '沈阳', '2018-08-02 00:00:00', 'BZFHC0181808010154', '辽宁省抚顺市顺城区区政府对面', '吉顺隆辽宁线', '库存品包装', 100000000, '2018-08-03 09:42:54', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2310, 'ANS.BLN', NULL, 11, NULL, 0, '吉顺隆辽宁线', 'C412A-N11042661D01', NULL, 3, '沈阳', '2018-08-02 00:00:00', 'BZFHC0181808010154', '辽宁省鞍山市铁东区安乐街39栋', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-03 09:43:04', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2319, 'QZH', NULL, 1, NULL, 0, '中兴圣物流', 'C595B-S10861451K01-SP1', NULL, 3, '福州', '2018-07-31 00:00:00', 'BZFHC0181807300282', '泉州丰泽区喜盈门建材家居一楼1306博洛尼展厅', '恒运宏远物流', '生产包装', 100000000, '2018-08-03 11:41:29', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2320, 'QZH', NULL, 2, NULL, 0, '中兴圣物流', 'C595B-S10861931F07-SP1', NULL, 3, '福州', '2018-07-31 00:00:00', 'BZFHC0181807300282', '泉州丰泽区喜盈门建材家居一楼1306博洛尼展厅', '恒运宏远物流', '生产包装', 100000000, '2018-08-03 11:41:29', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2321, 'QZH', NULL, 1, NULL, 0, '中兴圣物流', 'C595B-M09920751K02-SP1', NULL, 3, '福州', '2018-07-31 00:00:00', 'BZFHC0181807300282', '泉州丰泽区喜盈门建材家居一楼1306博洛尼展厅', '恒运宏远物流', '生产包装', 100000000, '2018-08-03 11:41:29', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2325, 'PUT.JLD', NULL, 16, NULL, 0, '中兴圣物流', 'C594A-S10907121K01', NULL, 3, '福州', '2018-07-31 00:00:00', 'BZFHC0181807300282', '莆田万辉国际城17802', '恒运宏远物流', '生产包装', 100000000, '2018-08-03 11:41:29', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.75, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2340, 'CHL', NULL, 2, NULL, 0, '中兴圣物流', 'C591A-V07478911K01-B1', NULL, 3, '福州', '2018-07-31 00:00:00', 'BZFHC0181807300282', '广西省桂林市灌阳县水车乡修睦村蔡家洞屯056（通讯地址）', '恒运宏远物流', '生产包装', 100000000, '2018-08-03 11:41:30', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2341, 'CHL', NULL, 1, NULL, 0, '中兴圣物流', 'C591A-S10134791K01-B1', NULL, 3, '福州', '2018-07-31 00:00:00', 'BZFHC0181807300282', '广西省桂林市灌阳县水车乡修睦村蔡家洞屯056（通讯地址）', '恒运宏远物流', '生产包装', 100000000, '2018-08-03 11:41:30', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2355, 'HUH.ZHW', NULL, 5, NULL, 0, 'DC自提', 'C471A-S10677161K10', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.06, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2356, 'HUH.ZHW', NULL, 3, NULL, 0, 'DC自提', 'C471A-S10677161K13', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2357, 'HUH.ZHW', NULL, 2, NULL, 0, 'DC自提', 'C471A-C11145111K01', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '库存品包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.11, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2358, 'HUH.ZHW', NULL, 5, NULL, 0, 'DC自提', 'C471A-T10677161K11', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.06, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2359, 'HUH.ZHW', NULL, 1, NULL, 0, 'DC自提', 'C471A-T10900021F03', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2360, 'HUH.ZHW', NULL, 4, NULL, 0, 'DC自提', 'C471A-S09569221K04', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2361, 'HUH.ZHW', NULL, 2, NULL, 0, 'DC自提', 'C471A-T10677161F21', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2362, 'HUH.ZHW', NULL, 4, NULL, 0, 'DC自提', 'C471A-B11145111F03', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2363, 'HUH.ZHW', NULL, 29, NULL, 0, 'DC自提', 'C471A-S09086501K01', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2364, 'HUH.ZHW', NULL, 6, NULL, 0, 'DC自提', 'C471A-T10677161K12', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '呼和浩特太阳城', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2365, 'EDS', NULL, 1, NULL, 0, 'DC自提', 'C477A-S10928391F01-SP1', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '东胜区鄂托克西街10号', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2366, 'EDS', NULL, 1, NULL, 0, 'DC自提', 'C477A-N11006011K01-B2', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '东胜区鄂托克西街10号', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2367, 'EDS', NULL, 20, NULL, 0, 'DC自提', 'C477A-B11164131K01', NULL, 3, '包头', '2018-08-01 00:00:00', 'BZFHC0181807310179', '东胜区鄂托克西街10号', 'DC自提', '生产包装', 100000000, '2018-08-03 13:43:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2406, 'YIY', NULL, 5, NULL, 0, '康程物流', 'C737A-X08686001S02', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '浙江省温岭市万昌中路483号', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2407, 'YIY', NULL, 2, NULL, 0, '康程物流', 'C737-11D-170010-A1-SP1-N', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '浙江省温岭市万昌中路483号', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2408, 'YIY', NULL, 25, NULL, 0, '康程物流', 'C737A-S10964551K01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '浙江省温岭市万昌中路483号', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2409, 'XIT.BLN', NULL, 1, NULL, 0, '康程物流', 'C731C-T06138753K01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省湘潭市岳塘区书院路42号2栋19号', 'DC自提', '库存品包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2410, 'XIT.BLN', NULL, 1, NULL, 0, '康程物流', 'C731C-T06138753K01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省湘潭市岳塘区书院路42号2栋19号', 'DC自提', '库存品包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2411, 'XIT.BLN', NULL, 1, NULL, 0, '康程物流', 'C731C-T06138753K01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省湘潭市岳塘区书院路42号2栋19号', 'DC自提', '库存品包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2418, 'JIS', NULL, 1, NULL, 0, '康程物流', 'C743A-N10710571K02-B1', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省吉首市民族家俱厂宿舍', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2420, 'HHA', NULL, 19, NULL, 0, '康程物流', 'C745A-K09961471K01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省怀化河西金都陶瓷广场二楼', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.90, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2421, 'HHA', NULL, 2, NULL, 0, '康程物流', 'C745A-T04185321K04', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省怀化河西金都陶瓷广场二楼', 'DC自提', '库存品包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2422, 'HHA', NULL, 9, NULL, 0, '康程物流', 'C745A-A06635121K01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省怀化河西金都陶瓷广场二楼', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2427, 'CHD', NULL, 21, NULL, 0, '康程物流', 'C736A-S11294601D02', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省常德市武陵区火车站建材市场', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2428, 'CHD', NULL, 1, NULL, 0, '康程物流', 'C736A-T10878511K07', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省常德市武陵区火车站建材市场', 'DC自提', '库存品包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2430, 'CHD', NULL, 1, NULL, 0, '康程物流', 'C736A-C10862971D01', NULL, 3, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省常德市武陵区火车站建材市场', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2432, 'HUH.ZHW', NULL, 13, NULL, 0, 'DC自提', 'C471A-S11074941F03', NULL, 3, '包头', '2018-07-31 00:00:00', 'BZFHC0181807310016', '呼和浩特太阳城', '吉顺内蒙线', '生产包装', 100000000, '2018-08-03 17:01:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.98, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2433, 'BOT', NULL, 3, NULL, 0, 'DC自提', 'C472A-S11067001F06', NULL, 3, '包头', '2018-07-31 00:00:00', 'BZFHC0181807310016', '包头稀土高新区友谊大街54号现代城13-105号', '吉顺内蒙线', '生产包装', 100000000, '2018-08-03 17:01:59', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.34, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2454, 'LFN.FH', NULL, 3, NULL, 0, '金云山西线', 'C357B-S09236531F12', NULL, 3, '太原', '2018-08-01 00:00:00', 'BZFHC0181807310022', '滨河湾9-西-4层西', 'DC自提', '生产包装', 100000000, '2018-08-03 17:53:46', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2458, 'HMA', NULL, 5, NULL, 0, '金云山西线', 'C357C-S11063651F01', NULL, 3, '太原', '2018-08-01 00:00:00', 'BZFHC0181807310022', '山西省侯马市建邦向阳苑商铺9号14宅', 'DC自提', '生产包装', 100000000, '2018-08-03 17:53:46', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2459, 'HMA', NULL, 2, NULL, 0, '金云山西线', 'C357C-S07885991F05', NULL, 3, '太原', '2018-08-01 00:00:00', 'BZFHC0181807310022', '山西省侯马市建邦向阳苑商铺9号14宅', 'DC自提', '生产包装', 100000000, '2018-08-03 17:53:46', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2460, 'HMA', NULL, 2, NULL, 0, '金云山西线', 'C357C-S10663541F04', NULL, 3, '太原', '2018-08-01 00:00:00', 'BZFHC0181807310022', '山西省侯马市建邦向阳苑商铺9号14宅', 'DC自提', '生产包装', 100000000, '2018-08-03 17:53:46', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2475, 'YKG.BLN', NULL, 2, NULL, 0, '上海益递物流', 'C579D-S10946571F01', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '永康市溪心路65-67号', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:44:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2480, 'SHX.BLN', NULL, 13, NULL, 0, '上海益递物流', 'C575C-S11027101F04', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省绍兴市越城区斜桥弄48号2室', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:44:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.09, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2481, 'SHX.BLN', NULL, 8, NULL, 0, '上海益递物流', 'C575C-M11212471F05', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省绍兴市越城区斜桥弄48号2室', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:44:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.96, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2482, 'SHX.BLN', NULL, 17, NULL, 0, '上海益递物流', 'C575C-M11212471F04', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省绍兴市越城区斜桥弄48号2室', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:44:22', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.37, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2488, 'CAN', NULL, 19, NULL, 0, '上海益递物流', 'C577C-S11116631F02', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.54, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2489, 'CAN', NULL, 2, NULL, 0, '上海益递物流', 'C577C-S10915331F13-B1', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2490, 'CAN', NULL, 2, NULL, 0, '上海益递物流', 'C577C-S08389571F02-B1', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.14, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2491, 'HIN.BLN', NULL, 7, NULL, 0, '上海益递物流', 'C573B-S07806331F02', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省海宁市缔艺家居广场二层', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.42, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2492, 'HUZ', NULL, 11, NULL, 0, '上海益递物流', 'C572C-S11165861F02', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省湖州市红星美凯龙二楼C8002-6', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.96, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2493, 'HUZ', NULL, 21, NULL, 0, '上海益递物流', 'C572C-S09331261F08', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '浙江省湖州市红星美凯龙二楼C8002-6', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.34, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2503, 'JAS.BLN', NULL, 7, NULL, 0, '上海益递物流', 'C573C-N10843521F11', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '嘉善县晋阳东路23号', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.48, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2504, 'JAX', NULL, 4, NULL, 0, '上海益递物流', 'C573A-S09607741F02', NULL, 2, '杭州', '2018-08-03 00:00:00', 'BZFHC0181808030006', '嘉兴市秀洲区中山大道1568号', '吉顺浙江线', '生产包装', 100000000, '2018-08-04 11:49:09', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2515, 'LUL', NULL, 2, NULL, 0, '金云山西线', 'C358A-S11178901K02', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省吕梁市', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.13, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2516, 'LFN.FH', NULL, 1, NULL, 0, '金云山西线', 'C357-11K-170040-A1-TB14', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '滨河湾9-西-4层西', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2517, 'LFN.FH', NULL, 3, NULL, 0, '金云山西线', 'C357B-T10983851K02', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '滨河湾9-西-4层西', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2518, 'LFN.FH', NULL, 3, NULL, 0, '金云山西线', 'C357B-N11087071K02', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '滨河湾9-西-4层西', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2519, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-T04006491K05', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2520, 'JXU', NULL, 3, NULL, 0, 'DC自提', 'C354B-T11005571K02', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2521, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-S11121531K01', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2522, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-T04006491K05', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2523, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-T11005571K03', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2524, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-S11125991D02', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '生产包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2525, 'JXU', NULL, 1, NULL, 0, 'DC自提', 'C354B-T04006491K05', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2526, 'JXU', NULL, 17, NULL, 0, 'DC自提', 'C354B-S11143481F01', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省介休市弘盛昌建材城一层A区003', '金云山西线', '生产包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2538, 'HOZ.BLN', NULL, 1, NULL, 0, '金云山西线', 'C357D-T11243141K04', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省霍州市辛置镇辛置村12198', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2540, 'HMA', NULL, 2, NULL, 0, '金云山西线', 'C357C-T07484101K03', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省侯马市建邦向阳苑商铺9号14宅', '金云山西线', '生产包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2541, 'HMA', NULL, 1, NULL, 0, '金云山西线', 'C357C-T04557961K05', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省侯马市建邦向阳苑商铺9号14宅', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2542, 'HMA', NULL, 1, NULL, 0, '金云山西线', 'C357C-T04557961K05', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省侯马市建邦向阳苑商铺9号14宅', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2543, 'HMA', NULL, 1, NULL, 0, '金云山西线', 'C357C-T04557961K05', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省侯马市建邦向阳苑商铺9号14宅', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2544, 'HMA', NULL, 2, NULL, 0, '金云山西线', 'C357C-N11002041W01', NULL, 2, '太原', '2018-08-03 00:00:00', 'BZFHC0181808020167', '山西省侯马市建邦向阳苑商铺9号14宅', '金云山西线', '库存品包装', 100000000, '2018-08-04 13:12:07', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2554, 'HEF.DG', NULL, 5, NULL, 0, 'DC自提', 'C551A-S11165231F05', NULL, 2, '合肥', '2018-08-03 00:00:00', 'BZFHC0181808020136', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '吉顺安徽线', '生产包装', 100000000, '2018-08-04 15:01:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.42, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2555, 'HEF.DG', NULL, 11, NULL, 0, 'DC自提', 'C551A-U10808051F02', NULL, 2, '合肥', '2018-08-03 00:00:00', 'BZFHC0181808020136', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '吉顺安徽线', '生产包装', 100000000, '2018-08-04 15:01:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2556, 'HEF.DG', NULL, 1, NULL, 0, 'DC自提', 'C551A-N11104801F01', NULL, 2, '合肥', '2018-08-03 00:00:00', 'BZFHC0181808020136', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '吉顺安徽线', '生产包装', 100000000, '2018-08-04 15:01:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2557, 'HEF.DG', NULL, 2, NULL, 0, 'DC自提', 'C551A-R11086951F05', NULL, 2, '合肥', '2018-08-03 00:00:00', 'BZFHC0181808020136', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '吉顺安徽线', '生产包装', 100000000, '2018-08-04 15:01:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.16, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2558, 'HEF.DG', NULL, 3, NULL, 0, 'DC自提', 'C551A-N11104801F02', NULL, 2, '合肥', '2018-08-03 00:00:00', 'BZFHC0181808020136', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '吉顺安徽线', '生产包装', 100000000, '2018-08-04 15:01:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.13, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2559, 'HEF.DG', NULL, 13, NULL, 0, 'DC自提', 'C551A-S11205561F04', NULL, 2, '合肥', '2018-08-03 00:00:00', 'BZFHC0181808020136', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '吉顺安徽线', '生产包装', 100000000, '2018-08-04 15:01:52', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.77, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2560, 'ZUH.XYD', NULL, 2, NULL, 0, '捷安物流', 'C756A-S11307901F04', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省珠海市香洲区前山世邦国际家居建材馆2层', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2561, 'ZSH', NULL, 10, NULL, 0, '捷安物流', 'C760A-S11303911F01', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.72, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2562, 'ZSH', NULL, 14, NULL, 0, '捷安物流', 'C760A-S11292791F03', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2563, 'ZSH', NULL, 14, NULL, 0, '捷安物流', 'C760A-S11292791F04', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2564, 'ZSH', NULL, 9, NULL, 0, '捷安物流', 'C760A-S11303911F03', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.58, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2565, 'ZSH', NULL, 9, NULL, 0, '捷安物流', 'C760A-S08081911F04', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2566, 'ZSH', NULL, 8, NULL, 0, '捷安物流', 'C760A-S11303911F06', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.56, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2567, 'ZSH', NULL, 16, NULL, 0, '捷安物流', 'C760A-S11303911F05', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省中山市', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.26, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2568, 'SYA', NULL, 13, NULL, 0, '捷安物流', 'C898B-S04537261F02', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '海南省三亚市解放三路金宝大厦A07号', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2570, 'HUI', NULL, 17, NULL, 0, '捷安物流', 'C752A-S05170661F07', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省惠州市惠城区万饰城A馆2楼扶梯口', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2571, 'HUI', NULL, 2, NULL, 0, '捷安物流', 'C752A-S10861181F06', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省惠州市惠城区万饰城A馆2楼扶梯口', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2572, 'HUI', NULL, 7, NULL, 0, '捷安物流', 'C752A-S10804051F02', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广东省惠州市惠城区万饰城A馆2楼扶梯口', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.55, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2573, 'GUZ.HCL', NULL, 20, NULL, 0, '同鑫物流', 'C020A-S11087461F03', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广州市越秀区广州大道中富力东山新天地1506', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.78, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2574, 'GUZ.HCL', NULL, 15, NULL, 0, '同鑫物流', 'C020A-N11205981F02', NULL, 2, '广州', '2018-08-02 00:00:00', 'BZFHC0181808010153', '广州市越秀区广州大道中富力东山新天地1506', '吉顺广东线', '生产包装', 100000000, '2018-08-04 17:00:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.32, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2576, 'ZJG.JCH', NULL, 5, NULL, 0, '灵鹤江苏线', 'C512A-R11140041F11', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.29, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2592, 'RUD', NULL, 4, NULL, 0, '灵鹤江苏线', 'C513E-S11281481F03', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '江苏省如东县掘港镇外环东路优美佳建材城二楼', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.24, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2593, 'NTG.GT', NULL, 12, NULL, 0, 'DC自提', 'C513A-S11156081F02', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '南通市濠西路尚德城邦D座102室', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.81, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2594, 'NTG.GT', NULL, 6, NULL, 0, 'DC自提', 'C513A-N11116401F03', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '南通市濠西路尚德城邦D座102室', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.27, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2595, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513-11F-170130-A1-B1', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '南通市濠西路尚德城邦D座102室', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2596, 'NTG.GT', NULL, 4, NULL, 0, 'DC自提', 'C513A-S08553371F04', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '南通市濠西路尚德城邦D座102室', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2597, 'NTG.GT', NULL, 15, NULL, 0, 'DC自提', 'C513A-S11156081F01', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '南通市濠西路尚德城邦D座102室', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2601, 'FEX', NULL, 2, NULL, 0, 'DC自提', 'C516A-S08733841F08', NULL, 2, '南京', '2018-08-03 00:00:00', 'BZFHC0181808020164', '江苏省徐州市丰县御景园第S1幢1单元121-122号', '吉顺江苏线', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2607, 'ZJG.JCH', NULL, 2, NULL, 0, '灵鹤江苏线', 'C512A-S10812371F05', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2608, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C512A-T09880901F11', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2609, 'ZJG.JCH', NULL, 4, NULL, 0, '灵鹤江苏线', 'C512A-R11045671K01-B1', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2610, 'ZJG.JCH', NULL, 21, NULL, 0, '灵鹤江苏线', 'C512A-D11231671F01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2611, 'ZJG.JCH', NULL, 3, NULL, 0, '灵鹤江苏线', 'C512A-B09052721K02', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.06, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2612, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C512A-T10933431F12', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2613, 'ZJG.JCH', NULL, 13, NULL, 0, '灵鹤江苏线', 'C512A-R11140041F10', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.25, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2614, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C512A-T00006252K04', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2615, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C512A-S05928371K02', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2616, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C512A-T00006252K04', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2617, 'ZJG.JCH', NULL, 1, NULL, 0, '灵鹤江苏线', 'C512A-T00006252K04', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省张家港市九洲装饰城东首保意建材城博洛尼', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2618, 'ZHJ', NULL, 28, NULL, 0, 'DC自提', 'C511C-S11149511K01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '冠城国际3幢105室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2619, 'ZHJ', NULL, 22, NULL, 0, 'DC自提', 'C511C-S11155411K01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '冠城国际3幢105室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2620, 'ZHJ', NULL, 1, NULL, 0, 'DC自提', 'C511-11W-170016-A1-SP1', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '冠城国际3幢105室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2621, 'YAZ', NULL, 1, NULL, 0, 'DC自提', 'C511B-V07056831K02-B1', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '扬中市明珠大道29-16、29-17号', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2630, 'XYI.BLN', NULL, 2, NULL, 0, '灵鹤江苏线', 'C516E-T06934961D08', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省新沂市新安镇大桥西路19号国际商贸城三期1号楼01、02', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2641, 'RUG', NULL, 5, NULL, 0, '灵鹤江苏线', 'C513C-B11174851D01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省如皋市福寿西路89号', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2642, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-X00006492S12', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010156', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2643, 'NTG.GT', NULL, 2, NULL, 0, 'DC自提', 'C513A-X00006492S04', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010156', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2644, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-X00006492S11', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010156', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2645, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513-11E-160001-A1-B1', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010156', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2646, 'NTG.GT', NULL, 3, NULL, 0, 'DC自提', 'C513A-X00006492S04', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010156', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2647, 'NTG.GT', NULL, 2, NULL, 0, 'DC自提', 'C513A-X00006492S04', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010156', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2648, 'NTG.GT', NULL, 16, NULL, 0, 'DC自提', 'C513A-N06440671W03', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.71, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2649, 'NTG.GT', NULL, 2, NULL, 0, 'DC自提', 'C513A-R10817931D06', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.13, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2650, 'NTG.GT', NULL, 5, NULL, 0, 'DC自提', 'C513A-N06440671W01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.23, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2651, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-G11043901K01-B1', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2652, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-T00006492K07', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2653, 'NTG.GT', NULL, 11, NULL, 0, 'DC自提', 'C513A-N11074461W01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2654, 'NTG.GT', NULL, 11, NULL, 0, 'DC自提', 'C513A-N06440671W02', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2655, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-T00006492K07', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2656, 'NTG.GT', NULL, 1, NULL, 0, 'DC自提', 'C513A-T00006492K07', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '南通市濠西路尚德城邦D座102室', '灵鹤调拨', '库存品包装', 100000000, '2018-08-04 17:31:05', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2671, 'JYN', NULL, 11, NULL, 0, 'DC自提', 'C510A-R11024131F14', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2672, 'JYN', NULL, 7, NULL, 0, 'DC自提', 'C510A-B11191301K01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2673, 'JYN', NULL, 31, NULL, 0, 'DC自提', 'C510A-U11023931K01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2674, 'JYN', NULL, 1, NULL, 0, 'DC自提', 'C510A-N08155581K01', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.04, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2675, 'JYN', NULL, 26, NULL, 0, 'DC自提', 'C510A-S11024131F10', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:06', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2676, 'JYN', NULL, 9, NULL, 0, 'DC自提', 'C510A-N08162171F05', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.85, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2677, 'JYN', NULL, 9, NULL, 0, 'DC自提', 'C510A-R11024131F12', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2678, 'JYN', NULL, 10, NULL, 0, 'DC自提', 'C510A-N11173341K03', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '金宸国际9号楼', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.42, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2679, 'JUR', NULL, 6, NULL, 0, 'DC自提', 'C511D-N10996451F08', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省句容市华阳镇凌家园1号/江苏省句容市华阳东路晴园公寓C26楼026-028号', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.52, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2681, 'JNJ.BLN', NULL, 5, NULL, 0, 'DC自提', 'C523B-S11142891K02', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省金坛市指前镇芦家村委袁家村85号/江苏省靖江市红星美凯龙二层', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2682, 'JNJ.BLN', NULL, 2, NULL, 0, 'DC自提', 'C523B-M10818071F05', NULL, 2, '南京', '2018-08-02 00:00:00', 'BZFHC0181808010155', '江苏省金坛市指前镇芦家村委袁家村85号/江苏省靖江市红星美凯龙二层', '灵鹤调拨', '生产包装', 100000000, '2018-08-04 17:31:13', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2696, 'SMS', NULL, 8, NULL, 0, '中兴圣物流', 'C598B-S11105791F01', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '福建省三明市金色阳光建材城', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.96, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2697, 'SMS', NULL, 2, NULL, 0, '中兴圣物流', 'C598B-S09916611F10', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '福建省三明市金色阳光建材城', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.14, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2698, 'SMS', NULL, 15, NULL, 0, '中兴圣物流', 'C598B-S11105791F02', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '福建省三明市金色阳光建材城', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2699, 'PUT.JLD', NULL, 6, NULL, 0, '中兴圣物流', 'C594A-N11103791F07', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '莆田万辉国际城17802', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.49, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2700, 'PUT.JLD', NULL, 16, NULL, 0, '中兴圣物流', 'C594A-N11150361F05', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '莆田万辉国际城17802', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.26, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2704, 'FUZ.BLN', NULL, 2, NULL, 0, '中兴圣物流', 'C591D-S08313441F02', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '御屏玉景123别墅', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2705, 'FUZ.BLN', NULL, 1, NULL, 0, '中兴圣物流', 'C591D-N08916051F01-SP1', NULL, 1, '福州', '2018-08-02 00:00:00', 'BZFHC0181807310173', '御屏玉景123别墅', '吉顺福建线', '生产包装', 100000000, '2018-08-05 08:08:51', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.05, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2710, 'XIY', NULL, 10, NULL, 0, '晨速物流', 'C859A-S09037481F01', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '贵州省兴义市金州体育城D栋2单元3号', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2711, 'XIY', NULL, 13, NULL, 0, '晨速物流', 'C859A-S10696671F04', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '贵州省兴义市金州体育城D栋2单元3号', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.08, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2712, 'XIY', NULL, 6, NULL, 0, '晨速物流', 'C859A-S08880871F01', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '贵州省兴义市金州体育城D栋2单元3号', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.44, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2713, 'REH.BLN', NULL, 7, NULL, 0, '晨速物流', 'C852B-S11161991F02', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '贵州省仁怀市中枢街道办事处青杠园社区茅台路组/仁怀市居然之家丁-1027摊位', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.53, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2714, 'NAC', NULL, 2, NULL, 0, '盛世前程物流', 'C817A-S10947011F06', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '蓝光19栋1单元14-4', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2715, 'NAC', NULL, 10, NULL, 0, '盛世前程物流', 'C817A-S11314191F02', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '蓝光19栋1单元14-4', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.84, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2717, 'MES', NULL, 4, NULL, 0, '大田物流', 'C028B-S11141071F06', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '四川省眉山市东城区碧华林17栋2单元302室', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:24', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.24, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2725, 'CHQ.YCH', NULL, 2, NULL, 0, '晨速物流', 'C023A-S10845171F13', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '重庆市永川区胜利北路8号', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2726, 'CHQ.WZH', NULL, 12, NULL, 0, '晨速物流', 'C023C-S11265291F06', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '重庆万州区万川大道302号附1号6-1', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.68, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2727, 'CHQ.WZH', NULL, 8, NULL, 0, '晨速物流', 'C023C-S11265291F10', NULL, 1, '成都', '2018-07-31 00:00:00', 'BZFHC0181807300276', '重庆万州区万川大道302号附1号6-1', '吉顺四川线', '生产包装', 100000000, '2018-08-05 10:28:25', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.66, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2745, 'XNI', NULL, 1, NULL, 0, 'DC自提', 'C715A-S11042451K01-B1', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2746, 'XNI', NULL, 4, NULL, 0, 'DC自提', 'C715A-N10973771F03', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2747, 'XNI', NULL, 1, NULL, 0, 'DC自提', 'C715A-T05031861K05', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '库存品包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2748, 'XNI', NULL, 1, NULL, 0, 'DC自提', 'C715A-T05031861K05', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '库存品包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2749, 'XNI', NULL, 1, NULL, 0, 'DC自提', 'C715A-X05031861S05', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '库存品包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2750, 'XNI', NULL, 21, NULL, 0, 'DC自提', 'C715A-S11148401K01', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2751, 'XNI', NULL, 1, NULL, 0, 'DC自提', 'C715A-T05031861K05', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省咸宁市天成家居二楼A209', '恒运宏远湖北线', '库存品包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2752, 'XGN', NULL, 9, NULL, 0, '君意通物流', 'C712B-X04007861S03', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '鸿博学府2栋东单元1706', '恒运宏远湖北线', '库存品包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.00, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2753, 'XGN', NULL, 3, NULL, 0, '君意通物流', 'C712B-X04007861S04', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '鸿博学府2栋东单元1706', '恒运宏远湖北线', '库存品包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.60, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2754, 'XFA', NULL, 1, NULL, 0, '君意通物流', 'C710A-M10839971F04', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北襄樊市前进路9号天丽国际家居建材博览中心5层A31-33', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2755, 'XFA', NULL, 7, NULL, 0, '君意通物流', 'C710A-S10802331W02', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北襄樊市前进路9号天丽国际家居建材博览中心5层A31-33', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2756, 'XFA', NULL, 14, NULL, 0, '君意通物流', 'C710A-S11099051K01', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北襄樊市前进路9号天丽国际家居建材博览中心5层A31-33', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2778, 'DYE', NULL, 1, NULL, 0, '君意通物流', 'C714B-S11156971K02', NULL, 1, '武汉', '2018-08-03 00:00:00', 'BZFHC0181808020152', '湖北省大冶市欧蓓莎国际家居馆橱柜区', '恒运宏远湖北线', '生产包装', 100000000, '2018-08-05 11:07:55', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2790, 'WEF.BLN', NULL, 2, NULL, 0, '三志物流', 'C536-41K-170015-A1-B1', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省潍坊市寒亭区通亭街3100号红星美凯龙三楼C8089、C8090博洛尼', '中安伟业物流', '库存品包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.14, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2791, 'TSD.BLN', NULL, 6, NULL, 0, '三志物流', 'C532A-S08262721D01', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省青岛市市北区台湾街8号', '中安伟业物流', '生产包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2792, 'TSD.BLN', NULL, 2, NULL, 0, '三志物流', 'C532A-T11162991K02', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2793, 'TSD.BLN', NULL, 3, NULL, 0, '三志物流', 'C532A-T11335971K03', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.16, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2794, 'TSD.BLN', NULL, 4, NULL, 0, '三志物流', 'C532A-T11288401K02', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.42, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2795, 'TSD.BLN', NULL, 3, NULL, 0, '三志物流', 'C532A-T11173311K02', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.18, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2796, 'TSD.BLN', NULL, 3, NULL, 0, '三志物流', 'C532A-T11276221K02', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省青岛市市北区台湾街8号', '中安伟业物流', '库存品包装', 100000000, '2018-08-05 18:37:31', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2817, 'JNG', NULL, 1, NULL, 0, '三志物流', 'C537A-S10953641K03', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省济宁市金宇路红星美凯龙南门对面', '中安伟业物流', '生产包装', 100000000, '2018-08-05 18:37:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.02, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2818, 'JNG', NULL, 5, NULL, 0, '三志物流', 'C537A-N04226072W01', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省济宁市金宇路红星美凯龙南门对面', '中安伟业物流', '生产包装', 100000000, '2018-08-05 18:37:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.23, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2824, 'DGY', NULL, 1, NULL, 0, '三志物流', 'C546A-N11032931K01', NULL, 1, '济南', '2018-08-04 00:00:00', 'BZFHC0181808030144', '山东省东营市东营区泰西巷8号', '中安伟业物流', '生产包装', 100000000, '2018-08-05 18:37:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2827, 'TSD.BLN', NULL, 3, NULL, 0, '三志物流', 'C532A-S11176431F03', NULL, 0, '济南', '2018-08-03 00:00:00', 'BZFHC0181808020155', '山东省青岛市市北区台湾街8号', '吉顺山东线', '生产包装', 100000000, '2018-08-06 08:19:51', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2842, 'BYQ', NULL, 2, NULL, 0, '吉顺隆辽宁线', 'C417B-S10929031F02-B1', NULL, 0, '沈阳', '2018-08-05 00:00:00', 'BZFHC0181808040136', '辽宁省鲅鱼圈红星美凯龙', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-06 09:48:10', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2843, 'ANS.BLN', NULL, 32, NULL, 0, '吉顺隆辽宁线', 'C412A-S11042661F03', NULL, 0, '沈阳', '2018-08-05 00:00:00', 'BZFHC0181808040136', '辽宁省鞍山市铁东区安乐街39栋', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-06 09:48:10', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 3.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2846, 'YIK', NULL, 3, NULL, 0, '吉顺隆辽宁线', 'C417A-D08809691F01', NULL, 0, '沈阳', '2018-08-05 00:00:00', 'BZFHC0181808040136', '辽宁省营口市老边区路南镇居然之家5-037', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-06 09:48:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2858, 'LYA.BLN', NULL, 1, NULL, 0, 'DC自提', 'C419A-S11081851F05', NULL, 0, '沈阳', '2018-08-05 00:00:00', 'BZFHC0181808040136', '辽宁省辽阳市欧亚达家居', '吉顺隆辽宁线', '生产包装', 100000000, '2018-08-06 09:48:28', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2898, 'YCN', NULL, 1, NULL, 0, '吉顺隆黑龙江线', 'C458-11K-170607-A1-SP1', NULL, 0, '哈尔滨', '2018-08-04 00:00:00', 'BZFHC0181808030151', '南郡A区7号楼301', '吉顺隆黑龙江线', '生产包装', 100000000, '2018-08-06 10:07:38', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2899, 'YCN', NULL, 6, NULL, 0, '吉顺隆黑龙江线', 'C458A-M11020811F02', NULL, 0, '哈尔滨', '2018-08-04 00:00:00', 'BZFHC0181808030151', '南郡A区7号楼301', '吉顺隆黑龙江线', '生产包装', 100000000, '2018-08-06 10:07:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2907, 'QQH.BLN', NULL, 2, NULL, 0, '吉顺隆黑龙江线', 'C452A-T11339781K01', NULL, 0, '哈尔滨', '2018-08-04 00:00:00', 'BZFHC0181808030151', '黑龙江省齐齐哈尔市龙沙区二轻建材城1号二楼', '吉顺隆黑龙江线', '库存品包装', 100000000, '2018-08-06 10:07:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.14, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2908, 'QQH.BLN', NULL, 26, NULL, 0, '吉顺隆黑龙江线', 'C452A-M11066661K01', NULL, 0, '哈尔滨', '2018-08-04 00:00:00', 'BZFHC0181808030151', '黑龙江省齐齐哈尔市龙沙区二轻建材城1号二楼', '吉顺隆黑龙江线', '生产包装', 100000000, '2018-08-06 10:07:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.20, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2909, 'QQH.BLN', NULL, 3, NULL, 0, '吉顺隆黑龙江线', 'C452A-T11322831K02', NULL, 0, '哈尔滨', '2018-08-04 00:00:00', 'BZFHC0181808030151', '黑龙江省齐齐哈尔市龙沙区二轻建材城1号二楼', '吉顺隆黑龙江线', '库存品包装', 100000000, '2018-08-06 10:07:38', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2923, 'WHU', NULL, 1, NULL, 0, '豪骏物流', 'C553A-T05410521D01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省芜湖市中南建材城11号', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2924, 'WHU', NULL, 19, NULL, 0, '豪骏物流', 'C553A-R11038381D01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省芜湖市中南建材城11号', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2925, 'WHU', NULL, 1, NULL, 0, '豪骏物流', 'C553A-S11041781K01-SP1', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省芜湖市中南建材城11号', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2926, 'WHU', NULL, 2, NULL, 0, '豪骏物流', 'C553A-S11022141F07', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省芜湖市中南建材城11号', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2927, 'MAS', NULL, 1, NULL, 0, '豪骏物流', 'C555-19F-170035-A1-B1', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '索赔反补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2928, 'MAS', NULL, 1, NULL, 0, '豪骏物流', 'C555A-M11210081W01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2929, 'MAS', NULL, 1, NULL, 0, '豪骏物流', 'C555A-M11210081W03', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2930, 'MAS', NULL, 5, NULL, 0, '豪骏物流', 'C555A-M11210081W03', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.23, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2931, 'MAS', NULL, 28, NULL, 0, '豪骏物流', 'C555A-R11136331D01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 2.50, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2932, 'MAS', NULL, 8, NULL, 0, '豪骏物流', 'C555A-M11210081W02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2933, 'MAS', NULL, 3, NULL, 0, '豪骏物流', 'C555A-S05082181K05', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2934, 'MAS', NULL, 1, NULL, 0, '豪骏物流', 'C555A-R11136331F03-TB1', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2935, 'MAS', NULL, 9, NULL, 0, '豪骏物流', 'C555A-M11210081W01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2936, 'MAS', NULL, 9, NULL, 0, '豪骏物流', 'C555A-M11210081K03', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2937, 'MAS', NULL, 18, NULL, 0, '豪骏物流', 'C555A-S10922531K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.75, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2938, 'MAS', NULL, 1, NULL, 0, '豪骏物流', 'C555A-M11210081W02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省马鞍山江东大道1480号红星美凯龙马鞍山商场3楼', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2939, 'LQN', NULL, 1, NULL, 0, '豪骏物流', 'C558B-T11018471K05', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省临泉县城关镇前进二巷16号1户', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2940, 'LQN', NULL, 1, NULL, 0, '豪骏物流', 'C558B-T11018471K07', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省临泉县城关镇前进二巷16号1户', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2941, 'LQN', NULL, 1, NULL, 0, '豪骏物流', 'C558B-T11018471K04', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省临泉县城关镇前进二巷16号1户', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2942, 'LQN', NULL, 1, NULL, 0, '豪骏物流', 'C558B-T11243231K02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省临泉县城关镇前进二巷16号1户', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2943, 'LAN', NULL, 1, NULL, 0, '豪骏物流', 'C564A-T03920621K05', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2944, 'LAN', NULL, 2, NULL, 0, '豪骏物流', 'C564A-U11304491K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2945, 'LAN', NULL, 1, NULL, 0, '豪骏物流', 'C564A-T03920621K05', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2946, 'LAN', NULL, 1, NULL, 0, '豪骏物流', 'C564A-T03920621K05', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2947, 'LAN', NULL, 5, NULL, 0, '豪骏物流', 'C564A-T11067281K02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.78, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2948, 'LAN', NULL, 3, NULL, 0, '豪骏物流', 'C564A-T11336371K02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.21, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2949, 'LAN', NULL, 4, NULL, 0, '豪骏物流', 'C564A-M08068441K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省六安市丽水康城小区', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2950, 'HUS', NULL, 4, NULL, 0, '豪骏物流', 'C559A-P10883341D01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省黄山市长干路老板电器', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2951, 'HEF.DG', NULL, 2, NULL, 0, 'DC自提', 'C551A-S11165231K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2952, 'HEF.DG', NULL, 2, NULL, 0, 'DC自提', 'C551A-N11076091K02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.07, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2953, 'HEF.DG', NULL, 1, NULL, 0, 'DC自提', 'C551A-N11076091K02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2954, 'HEF.DG', NULL, 21, NULL, 0, 'DC自提', 'C551A-S11165231F03', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2955, 'HEF.DG', NULL, 26, NULL, 0, 'DC自提', 'C551A-R09967111K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.40, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2956, 'HEF.DG', NULL, 1, NULL, 0, 'DC自提', 'C551A-S11165231K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2957, 'HEF.DG', NULL, 13, NULL, 0, 'DC自提', 'C551A-R10977301F12', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2958, 'HEF.DG', NULL, 12, NULL, 0, 'DC自提', 'C551A-S09967111F01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.15, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2959, 'HEF.DG', NULL, 6, NULL, 0, 'DC自提', 'C551A-S11165231W01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.27, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2960, 'HEF.DG', NULL, 23, NULL, 0, 'DC自提', 'C551A-S09967111K04', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 1.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2961, 'HEF.DG', NULL, 8, NULL, 0, 'DC自提', 'C551A-S09967111F06', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.70, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2962, 'HEF.DG', NULL, 4, NULL, 0, 'DC自提', 'C551A-N11104801F02', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.30, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2963, 'HEF.DG', NULL, 2, NULL, 0, 'DC自提', 'C551A-N09718861F12', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.10, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2964, 'HEF.DG', NULL, 2, NULL, 0, 'DC自提', 'C551-11W-170002-Z1-B1', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省合肥市蜀山区潜山路新华国际广场B座705', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '补单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2965, 'HAB', NULL, 3, NULL, 0, '豪骏物流', 'C561A-S11053111K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '安徽省淮北市杜集区淮海东路与龙山路交叉口，红星美凯龙', '恒运宏远物流', '库存品包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.21, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2966, 'FYN', NULL, 2, NULL, 0, '豪骏物流', 'C558A-T07238881K05', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '阜阳市气象大厦名牌装建材城', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.03, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2967, 'FYN', NULL, 8, NULL, 0, '豪骏物流', 'C558A-P11205771K01', NULL, 0, '合肥', '2018-08-05 00:00:00', 'BZFHC0181808040125', '阜阳市气象大厦名牌装建材城', '恒运宏远物流', '生产包装', 100000000, '2018-08-06 11:10:15', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.45, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `wms_stock_copy` VALUES (2973, 'CHD', NULL, 1, NULL, 0, '康程物流', 'C736A-S11047111K02', NULL, 0, '长沙', '2018-08-01 00:00:00', 'BZFHC0181807310188', '湖南省常德市武陵区火车站建材市场', 'DC自提', '生产包装', 100000000, '2018-08-03 14:23:32', '0', '1', 0, NULL, '正单', '箱体板类', NULL, '1', NULL, 0.01, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_stock_report
-- ----------------------------
DROP TABLE IF EXISTS `wms_stock_report`;
CREATE TABLE `wms_stock_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nain_month_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_stock_report
-- ----------------------------
INSERT INTO `wms_stock_report` VALUES (7, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wms_system_log
-- ----------------------------
DROP TABLE IF EXISTS `wms_system_log`;
CREATE TABLE `wms_system_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operate_date` datetime(0) NULL DEFAULT NULL,
  `operate_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_o0111d103p5xeypkjj249wybu`(`operate_user`) USING BTREE,
  CONSTRAINT `wms_system_log_ibfk_1` FOREIGN KEY (`operate_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_temp_stock
-- ----------------------------
DROP TABLE IF EXISTS `wms_temp_stock`;
CREATE TABLE `wms_temp_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NULL DEFAULT NULL,
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `examine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `measurement_unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `manufacturer` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  `warehouse` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_nmpsd2temckv2wlr0j9nw5mr2`(`category`) USING BTREE,
  INDEX `FK_mk283vr8p2dnflx9ai5femra7`(`create_user`) USING BTREE,
  INDEX `FK_kfx35gt15vlyy3vn78nwm9it6`(`manufacturer`) USING BTREE,
  INDEX `FK_62a5vjf8hjlmjh2nm39fpavle`(`update_user`) USING BTREE,
  INDEX `FK_921kkulqc7qvsd9v2bf4qcxjo`(`warehouse`) USING BTREE,
  CONSTRAINT `FK_62a5vjf8hjlmjh2nm39fpavle` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_921kkulqc7qvsd9v2bf4qcxjo` FOREIGN KEY (`warehouse`) REFERENCES `warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_kfx35gt15vlyy3vn78nwm9it6` FOREIGN KEY (`manufacturer`) REFERENCES `wms_manufacturer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_mk283vr8p2dnflx9ai5femra7` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_nmpsd2temckv2wlr0j9nw5mr2` FOREIGN KEY (`category`) REFERENCES `wms_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_temporary
-- ----------------------------
DROP TABLE IF EXISTS `wms_temporary`;
CREATE TABLE `wms_temporary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_op_date` datetime(0) NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stockId` int(11) NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_temporary
-- ----------------------------
INSERT INTO `wms_temporary` VALUES (93, 'BIJ.BLN', NULL, 2, '晨速物流', 'C520-11F-170129-A1', '南京DC', '2018-08-21 00:00:00', 'BJ1803200014', '毕节-罗运光', '灵鹤调拨', '箱体板类', '2018-08-17 11:09:57', 'HDO20180817570093', '正单', '家具生产通用成品2(10)', 2, 0.00);
INSERT INTO `wms_temporary` VALUES (94, 'CHQ.JAJ', NULL, 10, '大田物流', 'C520-11F-170126-A1', '南京DC', '2018-08-20 00:00:00', 'BJ1803200014', '江津-李国', '灵鹤调拨', '箱体板类', '2018-08-17 11:09:57', 'HDO20180817570093', '正单', '家具生产通用成品2(10)', 3, 1.00);
INSERT INTO `wms_temporary` VALUES (98, 'GYA', NULL, 5, '大田物流', 'C520-11W-170122-A1', '南京DC', '2018-08-19 00:00:00', 'BJ1803200014', '广元-华尔美', '灵鹤调拨', '箱体板类', '2018-08-17 11:09:57', 'HDO20180817570093', '正单', '厨卫生产通用成品1(10)', 4, 0.00);
INSERT INTO `wms_temporary` VALUES (99, 'LZS.BLN', NULL, 1, '大田物流', 'C412-11K-169003-A1-SP2', '南京DC', '2018-08-15 00:00:00', 'BJ1803230025', '泸州博洛尼', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', 'HDO20180817570093', '索赔反补单', '厨卫生产通用成品1(10)', 8, 0.00);
INSERT INTO `wms_temporary` VALUES (100, 'MET', NULL, 1, '晨速物流', 'C412-11K-169003-A1-SP3', '南京DC', '2018-08-17 00:00:00', 'BJ1803230025', '湄潭', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', 'HDO20180817570093', '索赔反补单', '厨卫生产通用成品1(10)', 6, 0.00);
INSERT INTO `wms_temporary` VALUES (101, 'REH.BLN', NULL, 1, '晨速物流', 'C412-11W-170033-A1-SP1', '南京DC', '2018-08-16 00:00:00', 'BJ1803230025', '仁怀博洛尼', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', 'HDO20180817570093', '索赔反补单', '厨卫生产通用成品1(10)', 7, 0.00);
INSERT INTO `wms_temporary` VALUES (102, 'XIS.BLN', NULL, 1, '晨速物流', 'C412-11K-169004-A1-YB-SP1', '南京DC', '2018-08-18 00:00:00', 'BJ1803230025', '习水－冯秀国', '吉顺隆物流', '箱体板类', '2018-08-17 11:09:58', 'HDO20180817570093', '索赔反补单', '厨卫生产通用成品1(10)', 5, 0.00);
INSERT INTO `wms_temporary` VALUES (103, 'BAZ', NULL, 7, '大田物流', 'C021A-X10932071S01', '成都基地', '2018-07-07 00:00:00', 'BJ1805160057', '巴中1', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817511778', '正单', '阿卡迪亚床_150_D面料(10)', 169, 3.00);
INSERT INTO `wms_temporary` VALUES (104, 'BAZ', NULL, 7, '大田物流', 'C021A-X10932071S01', '成都基地', '2018-07-07 00:00:00', 'BJ1805160057', '巴中1', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817511778', '正单', '阿卡迪亚床_150_D面料(10)', 178, 3.00);
INSERT INTO `wms_temporary` VALUES (105, 'BAZ', NULL, 7, '大田物流', 'C021A-X10932071S01', '成都基地', '2018-07-07 00:00:00', 'BJ1805160057', '巴中1', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817511778', '正单', '阿卡迪亚床_150_D面料(10)', 183, 3.00);
INSERT INTO `wms_temporary` VALUES (106, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:38', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 44, 0.00);
INSERT INTO `wms_temporary` VALUES (107, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 55, 0.00);
INSERT INTO `wms_temporary` VALUES (108, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 69, 0.00);
INSERT INTO `wms_temporary` VALUES (109, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 78, 0.00);
INSERT INTO `wms_temporary` VALUES (110, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 84, 0.00);
INSERT INTO `wms_temporary` VALUES (111, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 91, 0.00);
INSERT INTO `wms_temporary` VALUES (112, 'BIJ', NULL, 2, '晨速物流', 'C021A-S07241571F03', '长沙DC', '2018-07-26 00:00:00', 'BJ1805160057', '毕节', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', 'HDO20180817511778', '正单', '家具生产通用成品2(10)', 94, 0.00);
INSERT INTO `wms_temporary` VALUES (113, 'CDDJS', NULL, 6, '新博通美达-成都仓', 'C021A-S09263411K01', '长沙DC', '2018-08-02 00:00:00', 'BJ1805160057', '成都大觉寺', '灵鹤直发', '箱体板类', '2018-08-17 11:16:34', 'HDO20180817329079', '正单', '厨卫生产通用成品1(10)', 31, 0.00);
INSERT INTO `wms_temporary` VALUES (114, 'CDU', NULL, 8, '万达物流', 'C028A-X10843801S01', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', '2018-08-01 08:58:54', 'HDO20180817329079', '正单', '箱体板类', 12, 3.60);
INSERT INTO `wms_temporary` VALUES (115, 'CDU', NULL, 3, 'DC自提', 'C028A-X10843781S01', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', '2018-08-01 08:58:54', 'HDO20180817329079', '正单', '箱体板类', 13, 1.50);
INSERT INTO `wms_temporary` VALUES (116, 'CDU', NULL, 2, 'DC自提', 'C028A-X10241411S01', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', '2018-08-01 08:58:58', 'HDO20180817329079', '正单', '箱体板类', 14, 0.60);
INSERT INTO `wms_temporary` VALUES (117, 'CDU', NULL, 6, '万达物流', 'C028A-X09025251S01', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '生产包装', '2018-08-01 08:58:59', 'HDO20180817329079', '正单', '箱体板类', 15, 0.57);
INSERT INTO `wms_temporary` VALUES (118, 'CDU', NULL, 2, '万达物流', 'C028A-X09025251S01', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300346', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', '2018-08-01 08:58:59', 'HDO20180817329079', '正单', '箱体板类', 16, 0.50);
INSERT INTO `wms_temporary` VALUES (119, 'CDU', NULL, 4, '万达物流', 'C028A-X07228361S01', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300346', '翡翠城四期8-1-3001', 'DC自提', '生产包装', '2018-08-01 08:58:59', 'HDO20180817329079', '正单', '箱体板类', 17, 0.30);
INSERT INTO `wms_temporary` VALUES (120, 'CDU', NULL, 2, 'DC自提', 'C028A-X03905772S04', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '生产包装', '2018-08-01 08:58:59', 'HDO20180817329079', '正单', '箱体板类', 18, 0.20);
INSERT INTO `wms_temporary` VALUES (121, 'CDU', NULL, 2, 'DC自提', 'C028A-X03905771S08', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', '2018-08-01 08:58:59', 'HDO20180817329079', '正单', '箱体板类', 19, 0.40);
INSERT INTO `wms_temporary` VALUES (122, 'CDU', NULL, 5, NULL, 'C028A-S10239242W02', '成都', '2018-07-30 00:00:00', 'BZFHC0181807300225', '翡翠城四期8-1-3001', 'DC自提', '生产包装', '2018-08-17 11:13:57', 'HDO20180817329079', '正单', '箱体板类', 21, 0.20);
INSERT INTO `wms_temporary` VALUES (123, 'CDU', NULL, 13, '新博通美达-成都仓', 'C021A-N10917241D01', '成都基地', '2018-07-08 00:00:00', 'BJ1805160057', '成都', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817957363', '正单', '内门通用制造成品1(10)', 167, 2.00);
INSERT INTO `wms_temporary` VALUES (124, 'CDU', NULL, 13, '新博通美达-成都仓', 'C021A-N10917241D01', '成都基地', '2018-07-08 00:00:00', 'BJ1805160057', '成都', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817957363', '正单', '内门通用制造成品1(10)', 176, 2.00);
INSERT INTO `wms_temporary` VALUES (125, 'CDU', NULL, 13, '新博通美达-成都仓', 'C021A-N10917241D01', '成都基地', '2018-07-08 00:00:00', 'BJ1805160057', '成都', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817957363', '正单', '内门通用制造成品1(10)', 181, 2.00);
INSERT INTO `wms_temporary` VALUES (126, 'CDU.DHB', NULL, 1, '新博通美达-成都仓', 'C021A-T09206731W02', '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:44', 'HDO20180817957363', '正单', '厨卫生产通用成品1(10)', 102, 0.00);
INSERT INTO `wms_temporary` VALUES (127, 'CDU.DHB', NULL, 1, '新博通美达-成都仓', 'C021A-T09206731W02', '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', 'HDO20180817957363', '正单', '厨卫生产通用成品1(10)', 105, 0.00);
INSERT INTO `wms_temporary` VALUES (128, 'CDU.DHB', NULL, 1, '新博通美达-成都仓', 'C021A-T09206731W02', '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', 'HDO20180817957363', '正单', '厨卫生产通用成品1(10)', 109, 0.00);
INSERT INTO `wms_temporary` VALUES (129, 'CDU.DHB', NULL, 1, '新博通美达-成都仓', 'C021A-T09206731W02', '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', 'HDO20180817957363', '正单', '厨卫生产通用成品1(10)', 114, 0.00);
INSERT INTO `wms_temporary` VALUES (130, 'CDU.DHB', NULL, 1, '新博通美达-成都仓', 'C021A-T09206731W02', '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', 'HDO20180817957363', '正单', '厨卫生产通用成品1(10)', 120, 0.00);
INSERT INTO `wms_temporary` VALUES (131, 'CDU.DHB', NULL, 1, '新博通美达-成都仓', 'C021A-T09206731W02', '重庆DC', '2018-07-22 00:00:00', 'BJ1805160057', '成都段惠波', '灵鹤直发', '箱体板类', '2018-08-17 11:16:45', 'HDO20180817957363', '正单', '厨卫生产通用成品1(10)', 130, 0.00);
INSERT INTO `wms_temporary` VALUES (132, 'CHQ.BLN', NULL, 3, '晨速物流', 'C021A-X07366156S01', '成都基地', '2018-07-05 00:00:00', 'BJ1805160057', '重庆宝洛尼', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817957363', '正单', '拿破仑床踏_D面料(10)', 174, 0.00);
INSERT INTO `wms_temporary` VALUES (133, 'CHQ.BLN', NULL, 3, '晨速物流', 'C021A-X07366156S01', '成都基地', '2018-07-05 00:00:00', 'BJ1805160057', '重庆宝洛尼', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817992796', '正单', '拿破仑床踏_D面料(10)', 182, 0.00);
INSERT INTO `wms_temporary` VALUES (134, 'CHQ.BLN', NULL, 3, '晨速物流', 'C021A-X07366156S01', '成都基地', '2018-07-05 00:00:00', 'BJ1805160057', '重庆宝洛尼', '灵鹤直发', '箱体板类', '2018-08-17 11:17:11', 'HDO20180817992796', '正单', '拿破仑床踏_D面料(10)', 185, 0.00);
INSERT INTO `wms_temporary` VALUES (135, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:38', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 41, 1.00);
INSERT INTO `wms_temporary` VALUES (136, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 50, 1.00);
INSERT INTO `wms_temporary` VALUES (137, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 63, 1.00);
INSERT INTO `wms_temporary` VALUES (138, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 72, 1.00);
INSERT INTO `wms_temporary` VALUES (139, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 79, 1.00);
INSERT INTO `wms_temporary` VALUES (140, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 87, 1.00);
INSERT INTO `wms_temporary` VALUES (141, 'CHQ.BSH', NULL, 14, '晨速物流', 'C021A-N04501611W01', '长沙DC', '2018-07-27 00:00:00', 'BJ1805160057', '璧山', '灵鹤直发', '箱体板类', '2018-08-17 11:16:40', 'HDO20180817992796', '正单', '厨卫生产通用成品1(10)', 90, 1.00);
INSERT INTO `wms_temporary` VALUES (142, 'CHQ.DZU', NULL, 2, '晨速物流', 'C021A-S04026991F01', '广州DC', '2018-08-09 00:00:00', 'BJ1805160057', '大足', '灵鹤直发', '箱体板类', '2018-08-17 11:16:33', 'HDO20180817992796', '正单', '家具生产通用成品2(10)', 24, 0.00);
INSERT INTO `wms_temporary` VALUES (143, 'CHQ.WZH', NULL, 1, '晨速物流', 'C021A-R10817911F01-SP1', '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:38', 'HDO20180817767750', '索赔反补单', '家具生产通用成品2(10)', 43, 0.00);
INSERT INTO `wms_temporary` VALUES (144, 'CHQ.WZH', NULL, 1, '晨速物流', 'C021A-R10817911F01-SP1', '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:38', 'HDO20180817767750', '索赔反补单', '家具生产通用成品2(10)', 37, 0.00);
INSERT INTO `wms_temporary` VALUES (145, 'CHQ.WZH', NULL, 1, '晨速物流', 'C021A-R10817911F01-SP1', '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:38', 'HDO20180817767750', '索赔反补单', '家具生产通用成品2(10)', 33, 0.00);
INSERT INTO `wms_temporary` VALUES (146, 'CHQ.WZH', NULL, 1, '晨速物流', 'C021A-R10817911F01-SP1', '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817362791', '索赔反补单', '家具生产通用成品2(10)', 49, 0.00);
INSERT INTO `wms_temporary` VALUES (147, 'CHQ.WZH', NULL, 1, '晨速物流', 'C021A-R10817911F01-SP1', '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817362791', '索赔反补单', '家具生产通用成品2(10)', 65, 0.00);
INSERT INTO `wms_temporary` VALUES (148, 'CHQ.WZH', NULL, 1, '晨速物流', 'C021A-R10817911F01-SP1', '长沙DC', '2018-07-31 00:00:00', 'BJ1805160057', '万州', '灵鹤直发', '箱体板类', '2018-08-17 11:16:39', 'HDO20180817362791', '索赔反补单', '家具生产通用成品2(10)', 56, 0.00);

-- ----------------------------
-- Table structure for wms_temporary_send
-- ----------------------------
DROP TABLE IF EXISTS `wms_temporary_send`;
CREATE TABLE `wms_temporary_send`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `carrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contractNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cusName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deliverDate` datetime(0) NULL DEFAULT NULL,
  `finalCustomerAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsInCarrier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_op_date` datetime(0) NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outId` int(11) NULL DEFAULT NULL,
  `packingMaterial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shipVia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `volume` decimal(19, 2) NULL DEFAULT NULL,
  `deliverNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_op_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_temporary_send
-- ----------------------------
INSERT INTO `wms_temporary_send` VALUES (2, '包头市友谊大街现代城54号底店', 'BOT', NULL, 28, 'DC自提', '田旭东', 'C472A-S11159561F07', NULL, '包头', '2018-08-01 00:00:00', '包头稀土高新区友谊大街54号现代城13-105号', 'DC自提', '生产包装', NULL, 'ADCK201808160001', '正单', 12, '箱体板类', 'ADFY201809050002', 'DC自提', '13384841184', 2.30, 'BZFHC0181807310179', '2018-08-17 17:39:46');
INSERT INTO `wms_temporary_send` VALUES (3, '浙江省苍南县灵溪镇山海小区2-17栋', 'CAN', NULL, 8, '上海益递物流', '陈岳用', 'C577C-M11147191F05', NULL, '杭州', '2018-07-31 00:00:00', '浙江省苍南县灵溪镇上江小区1-5幢', '吉顺浙江线', '生产包装', NULL, 'ADCK201808160002', '正单', 13, '箱体板类', 'ADFY201809050003', '送货', '13819795816', 0.58, 'BZFHC0181807300294', '2018-08-17 17:39:46');
INSERT INTO `wms_temporary_send` VALUES (4, '浙江省苍南县灵溪镇山海小区2-17栋', 'CAN', NULL, 2, '上海益递物流', '陈岳用', 'C577C-M11147191F08', NULL, '杭州', '2018-07-31 00:00:00', '浙江省苍南县灵溪镇上江小区1-5幢', '瀛海世纪物流', '生产包装', NULL, 'ADCK201808160002', '正单', 27, '箱体板类', 'ADFY201809050003', '送货', '13819795816', 0.20, 'BZFHC0181807300275', '2018-08-17 17:39:46');
INSERT INTO `wms_temporary_send` VALUES (5, '成都市簇桥乡三河村三组302号', 'CDU', NULL, 22, '吉顺隆黑龙江线', '杨勇', 'C028A-N11052631F03', NULL, '成都', '2018-07-30 00:00:00', '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808090002', '正单', 1, '箱体板类', 'ADFY201809050004', 'DC自提', '13088029955', 2.20, 'BZFHC0181807300346', '2018-08-17 11:33:47');
INSERT INTO `wms_temporary_send` VALUES (6, '成都市簇桥乡三河村三组302号', 'CDU', NULL, 11, 'DC自提', '杨勇', 'C028A-N10992931D01', NULL, '成都', '2018-07-30 00:00:00', '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808090002', '正单', 2, '箱体板类', 'ADFY201809050004', 'DC自提', '13088029955', 1.10, 'BZFHC0181807300225', '2018-08-17 11:33:47');
INSERT INTO `wms_temporary_send` VALUES (7, '成都市簇桥乡三河村三组302号', 'CDU', NULL, 2, 'DC自提', '杨勇', 'C028A-P07228361F02', NULL, '成都', '2018-07-30 00:00:00', '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808090002', '正单', 7, '箱体板类', 'ADFY201809050004', 'DC自提', '13088029955', 0.10, 'BZFHC0181807300346', '2018-08-17 17:39:46');
INSERT INTO `wms_temporary_send` VALUES (8, '成都市簇桥乡三河村三组302号', 'CDU', NULL, 1, 'DC自提', '杨勇', 'C028A-N11231701K01', NULL, '成都', '2018-07-30 00:00:00', '翡翠城四期8-1-3001', 'DC自提', '库存品包装', NULL, 'ADCK201808090002', '正单', 8, '箱体板类', 'ADFY201809050004', 'DC自提', '13088029955', 0.26, 'BZFHC0181807300225', '2018-08-17 17:39:46');
INSERT INTO `wms_temporary_send` VALUES (9, '成都市簇桥乡三河村三组302号', 'CDU', NULL, 20, 'DC自提', '杨勇', 'C028A-N11035551D01', NULL, '成都', '2018-07-30 00:00:00', '翡翠城四期8-1-3001', 'DC自提', '生产包装', NULL, 'ADCK201808090002', '正单', 10, '箱体板类', 'ADFY201809050004', 'DC自提', '13088029955', 2.60, 'BZFHC0181807300346', '2018-08-17 17:39:46');

-- ----------------------------
-- Table structure for wms_tool
-- ----------------------------
DROP TABLE IF EXISTS `wms_tool`;
CREATE TABLE `wms_tool`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purpose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `year_check_date` datetime(0) NULL DEFAULT NULL,
  `create_user` int(11) NULL DEFAULT NULL,
  `update_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_lowc7bjy8yvbn3gf77gkcphgc`(`create_user`) USING BTREE,
  INDEX `FK_deq1mdgi435ckal8yuxllvkv`(`update_user`) USING BTREE,
  CONSTRAINT `FK_deq1mdgi435ckal8yuxllvkv` FOREIGN KEY (`update_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_lowc7bjy8yvbn3gf77gkcphgc` FOREIGN KEY (`create_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wms_user
-- ----------------------------
DROP TABLE IF EXISTS `wms_user`;
CREATE TABLE `wms_user`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `month_login_num` int(11) NULL DEFAULT NULL,
  `this_month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_login_num` int(11) NULL DEFAULT NULL,
  `department` int(11) NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dcWarehouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_7q1t52vgendifia6dwjcldlf4`(`department`) USING BTREE,
  CONSTRAINT `FK_7q1t52vgendifia6dwjcldlf4` FOREIGN KEY (`department`) REFERENCES `wms_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wms_user
-- ----------------------------
INSERT INTO `wms_user` VALUES (1, '候江波', '005861', '4QrcOUm6Wau+VuBX8g+IPg==', '15184407711', 1, NULL, NULL, 21, 0, 'ut79QRmJQ/p2qVtxQfm3LA==', '总部');
INSERT INTO `wms_user` VALUES (2, '李江', 'lw008', '4QrcOUm6Wau+VuBX8g+IPg==', '17775898915', 1, NULL, NULL, 1, 5, NULL, '长沙');
INSERT INTO `wms_user` VALUES (3, '宋毅', 'lw108', '4QrcOUm6Wau+VuBX8g+IPg==', '15964101587', 1, NULL, NULL, 1, 7, NULL, '济南');
INSERT INTO `wms_user` VALUES (4, '赵家良', 'LW102', '4QrcOUm6Wau+VuBX8g+IPg==', '13567103676', 1, NULL, NULL, 1, 13, NULL, '杭州');
INSERT INTO `wms_user` VALUES (5, '周健', 'LW110', '4QrcOUm6Wau+VuBX8g+IPg==', '18107835168', 1, NULL, NULL, 1, 13, NULL, '杭州');
INSERT INTO `wms_user` VALUES (6, '邵贤海', 'lw010', '4QrcOUm6Wau+VuBX8g+IPg==', '18070052932', 1, NULL, NULL, 1, 16, NULL, '南昌');
INSERT INTO `wms_user` VALUES (8, '陈莉', 'lw004', '4QrcOUm6Wau+VuBX8g+IPg==', '15659026156', 1, NULL, NULL, 2, 11, NULL, '福州');
INSERT INTO `wms_user` VALUES (9, '杨帆', 'lw012', '4QrcOUm6Wau+VuBX8g+IPg==', '15246784154', 1, NULL, NULL, 1, 0, NULL, '哈尔滨');
INSERT INTO `wms_user` VALUES (11, '刘斌', 'LW020', '4QrcOUm6Wau+VuBX8g+IPg==', '18664712312', 1, NULL, NULL, 1, 9, NULL, '广州');
INSERT INTO `wms_user` VALUES (12, '李强', 'lw014', '4QrcOUm6Wau+VuBX8g+IPg==', '18612485981', 0, NULL, NULL, 1, 15, NULL, '包头');
INSERT INTO `wms_user` VALUES (13, '李超', 'lw013', '4QrcOUm6Wau+VuBX8g+IPg==', '18534616220', 1, NULL, NULL, 1, 14, NULL, '太原');
INSERT INTO `wms_user` VALUES (14, '吴炜中', 'LW002', '4QrcOUm6Wau+VuBX8g+IPg==', '13776678557', 1, NULL, NULL, 1, 10, NULL, '南京');
INSERT INTO `wms_user` VALUES (15, '张弘寅', 'LW009', '4QrcOUm6Wau+VuBX8g+IPg==', '13698754113', 1, NULL, NULL, 1, 6, NULL, '昆明');
INSERT INTO `wms_user` VALUES (16, '雷詹', 'LW011', '4QrcOUm6Wau+VuBX8g+IPg==', '15586486296/13681637467', 1, NULL, NULL, 1, 12, NULL, '武汉');
INSERT INTO `wms_user` VALUES (17, '陈仁洪', '022143', '4QrcOUm6Wau+VuBX8g+IPg==', '18180938522', 1, NULL, NULL, 1, 1, NULL, '成都');
INSERT INTO `wms_user` VALUES (18, '杨正茂', 'LW005', '4QrcOUm6Wau+VuBX8g+IPg==', '18908035568', 1, NULL, NULL, 1, 1, NULL, '成都');
INSERT INTO `wms_user` VALUES (19, '李晓乙', 'lw015', '4QrcOUm6Wau+VuBX8g+IPg==', '18802469769', 1, NULL, NULL, 1, 0, NULL, '沈阳');

-- ----------------------------
-- Table structure for wms_user_message
-- ----------------------------
DROP TABLE IF EXISTS `wms_user_message`;
CREATE TABLE `wms_user_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg_date` datetime(0) NULL DEFAULT NULL,
  `msg_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msg_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `received_user` int(11) NULL DEFAULT NULL,
  `sended_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_donqhk900y0g1b4umgjhdk2j3`(`received_user`) USING BTREE,
  INDEX `FK_k0jay87jb4g8s1aaid837qtj0`(`sended_user`) USING BTREE,
  CONSTRAINT `FK_donqhk900y0g1b4umgjhdk2j3` FOREIGN KEY (`received_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_k0jay87jb4g8s1aaid837qtj0` FOREIGN KEY (`sended_user`) REFERENCES `wms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
