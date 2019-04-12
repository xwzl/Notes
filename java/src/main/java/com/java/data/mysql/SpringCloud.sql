/*
 Navicat Premium Data Transfer

 Source Server         : Root
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.83.21:3306
 Source Schema         : SpringCloud

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 28/03/2019 17:09:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('12367', '123456', '157875196366160022', '皮蛋粥', 0.01, 2, 'http://xxx.com', '2018-10-07 14:51:38', '2018-10-07 14:51:38');
INSERT INTO `order_detail` VALUES ('1539148420948981012', '1539148410848994242', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-10-10 05:13:54', '2018-10-10 05:13:54');
INSERT INTO `order_detail` VALUES ('1539149451239345187', '1539149450921721576', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-10-10 05:30:54', '2018-10-10 05:30:54');
INSERT INTO `order_detail` VALUES ('1539534929554321644', '1539534927856463163', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-10-14 16:35:28', '2018-10-14 16:35:28');
INSERT INTO `order_detail` VALUES ('1539536434508359192', '1539536433976711824', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-10-14 17:00:33', '2018-10-14 17:00:33');
INSERT INTO `order_detail` VALUES ('1539536502669692484', '1539536501543328810', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-10-14 17:02:11', '2018-10-14 17:02:11');
INSERT INTO `order_detail` VALUES ('1545205680143260832', '1545205680104641294', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-19 15:48:01', '2018-12-19 15:48:01');
INSERT INTO `order_detail` VALUES ('1545205680253637652', '1545205680104641294', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-19 15:48:01', '2018-12-19 15:48:01');
INSERT INTO `order_detail` VALUES ('1545205682262584987', '1545205682251478731', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-19 15:48:02', '2018-12-19 15:48:02');
INSERT INTO `order_detail` VALUES ('1545205682264707718', '1545205682251478731', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-19 15:48:02', '2018-12-19 15:48:02');
INSERT INTO `order_detail` VALUES ('1545205682699213721', '1545205682677737777', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-19 15:48:03', '2018-12-19 15:48:03');
INSERT INTO `order_detail` VALUES ('1545205682701818097', '1545205682677737777', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-19 15:48:03', '2018-12-19 15:48:03');
INSERT INTO `order_detail` VALUES ('1545205683168495797', '1545205683155186246', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2018-12-19 15:48:03', '2018-12-19 15:48:03');
INSERT INTO `order_detail` VALUES ('1545205683171213959', '1545205683155186246', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2018-12-19 15:48:03', '2018-12-19 15:48:03');
INSERT INTO `order_detail` VALUES ('1547606704990830784', '1547606704902792023', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:06', '2019-01-16 10:45:06');
INSERT INTO `order_detail` VALUES ('1547606705414255713', '1547606704902792023', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:06', '2019-01-16 10:45:06');
INSERT INTO `order_detail` VALUES ('1547606712119526826', '1547606712106160254', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:12', '2019-01-16 10:45:12');
INSERT INTO `order_detail` VALUES ('1547606712121367243', '1547606712106160254', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:12', '2019-01-16 10:45:12');
INSERT INTO `order_detail` VALUES ('1547606714691659238', '1547606714678633435', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:15', '2019-01-16 10:45:15');
INSERT INTO `order_detail` VALUES ('1547606714694197731', '1547606714678633435', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:15', '2019-01-16 10:45:15');
INSERT INTO `order_detail` VALUES ('1547606716044634314', '1547606716033544383', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:16', '2019-01-16 10:45:16');
INSERT INTO `order_detail` VALUES ('1547606716050598461', '1547606716033544383', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:16', '2019-01-16 10:45:16');
INSERT INTO `order_detail` VALUES ('1547606717004310902', '1547606716994734901', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:17', '2019-01-16 10:45:17');
INSERT INTO `order_detail` VALUES ('1547606717006882385', '1547606716994734901', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:17', '2019-01-16 10:45:17');
INSERT INTO `order_detail` VALUES ('1547606717869964712', '1547606717857572270', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:18', '2019-01-16 10:45:18');
INSERT INTO `order_detail` VALUES ('1547606717871986293', '1547606717857572270', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:18', '2019-01-16 10:45:18');
INSERT INTO `order_detail` VALUES ('1547606718408396175', '1547606718398698664', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:18', '2019-01-16 10:45:18');
INSERT INTO `order_detail` VALUES ('1547606718411565397', '1547606718398698664', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:18', '2019-01-16 10:45:18');
INSERT INTO `order_detail` VALUES ('1547606718808455145', '1547606718795843741', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:19', '2019-01-16 10:45:19');
INSERT INTO `order_detail` VALUES ('1547606718812172930', '1547606718795843741', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:19', '2019-01-16 10:45:19');
INSERT INTO `order_detail` VALUES ('1547606719513617064', '1547606719500681114', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:19', '2019-01-16 10:45:19');
INSERT INTO `order_detail` VALUES ('1547606719516761032', '1547606719500681114', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:19', '2019-01-16 10:45:19');
INSERT INTO `order_detail` VALUES ('1547606719967862615', '1547606719957911612', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_detail` VALUES ('1547606719969628703', '1547606719957911612', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_detail` VALUES ('1547606720331606960', '1547606720320885267', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_detail` VALUES ('1547606720333952708', '1547606720320885267', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_detail` VALUES ('1547606720685351560', '1547606720677223929', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:21', '2019-01-16 10:45:21');
INSERT INTO `order_detail` VALUES ('1547606720687106525', '1547606720677223929', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:21', '2019-01-16 10:45:21');
INSERT INTO `order_detail` VALUES ('1547606721079114464', '1547606721071928293', '157875196366160022', '皮蛋粥', 0.01, 2, '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', '2019-01-16 10:45:21', '2019-01-16 10:45:21');
INSERT INTO `order_detail` VALUES ('1547606721082228073', '1547606721071928293', '164103465734242707', '蜜汁鸡翅', 0.02, 2, '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', '2019-01-16 10:45:21', '2019-01-16 10:45:21');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_buyer_openid`(`buyer_openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1234567', '师兄', '1886131241241', '慕课网总部', '1101110', 2.50, 0, 0, '2018-10-07 14:36:50', '2018-10-07 14:36:50');
INSERT INTO `order_master` VALUES ('1539148341785575865', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 0.02, 0, 0, '2018-10-10 05:12:59', '2018-10-10 05:12:59');
INSERT INTO `order_master` VALUES ('1539148410848994242', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 0.02, 0, 0, '2018-10-10 05:13:54', '2018-10-10 05:13:54');
INSERT INTO `order_master` VALUES ('1539149450921721576', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 0.02, 0, 0, '2018-10-10 05:30:54', '2018-10-10 05:30:54');
INSERT INTO `order_master` VALUES ('1539534927856463163', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 0.02, 0, 0, '2018-10-14 16:35:28', '2018-10-14 16:35:28');
INSERT INTO `order_master` VALUES ('1539536433976711824', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 0.02, 0, 0, '2018-10-14 17:00:33', '2018-10-14 17:00:33');
INSERT INTO `order_master` VALUES ('1539536501543328810', '张三', '18868822111', '慕课网总部', 'ew3euwhd7sjw9diwkq', 0.02, 0, 0, '2018-10-14 17:02:12', '2018-10-14 17:02:12');
INSERT INTO `order_master` VALUES ('1545205680104641294', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2018-12-19 15:48:01', '2018-12-19 15:48:01');
INSERT INTO `order_master` VALUES ('1545205682251478731', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2018-12-19 15:48:02', '2018-12-19 15:48:02');
INSERT INTO `order_master` VALUES ('1545205682677737777', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2018-12-19 15:48:03', '2018-12-19 15:48:03');
INSERT INTO `order_master` VALUES ('1545205683155186246', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2018-12-19 15:48:04', '2018-12-19 15:48:04');
INSERT INTO `order_master` VALUES ('1547606704902792023', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:06', '2019-01-16 10:45:06');
INSERT INTO `order_master` VALUES ('1547606712106160254', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:12', '2019-01-16 10:45:12');
INSERT INTO `order_master` VALUES ('1547606714678633435', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:15', '2019-01-16 10:45:15');
INSERT INTO `order_master` VALUES ('1547606716033544383', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:16', '2019-01-16 10:45:16');
INSERT INTO `order_master` VALUES ('1547606716994734901', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:17', '2019-01-16 10:45:17');
INSERT INTO `order_master` VALUES ('1547606717857572270', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:18', '2019-01-16 10:45:18');
INSERT INTO `order_master` VALUES ('1547606718398698664', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:18', '2019-01-16 10:45:18');
INSERT INTO `order_master` VALUES ('1547606718795843741', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:19', '2019-01-16 10:45:19');
INSERT INTO `order_master` VALUES ('1547606719500681114', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_master` VALUES ('1547606719957911612', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_master` VALUES ('1547606720320885267', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:20', '2019-01-16 10:45:20');
INSERT INTO `order_master` VALUES ('1547606720677223929', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:21', '2019-01-16 10:45:21');
INSERT INTO `order_master` VALUES ('1547606721071928293', '张三', '15280975199', '四川省成都市成华区', 'adfadds', 0.06, 0, 0, '2019-01-16 10:45:21', '2019-01-16 10:45:21');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `uqe_category_type`(`category_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '好吃的', 1, '2017-03-28 16:40:22', '2018-09-20 16:36:35');
INSERT INTO `product_category` VALUES (2, '好吃的', 22, '2017-03-14 17:38:46', '2017-11-26 23:39:40');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) NULL DEFAULT 0 COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('157875196366160022', '皮蛋粥', 0.01, 934, '好吃的皮蛋粥', '//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg', 0, 1, '2017-03-28 19:39:15', '2018-10-10 05:07:06');
INSERT INTO `product_info` VALUES ('157875227953464068', '慕斯蛋糕', 10.90, 200, '美味爽口', '//fuss10.elemecdn.com/9/93/91994e8456818dfe7b0bd95f10a50jpeg.jpeg', 1, 1, '2017-03-28 19:35:54', '2017-04-21 10:05:57');
INSERT INTO `product_info` VALUES ('164103465734242707', '蜜汁鸡翅', 0.02, 948, '好吃', '//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg', 0, 1, '2017-03-30 17:11:56', '2017-06-24 19:20:54');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '微信openid',
  `role` tinyint(1) NOT NULL COMMENT '1买家2卖家',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'xwz', '1', 'xyz', 2, '2018-12-19 15:44:03', '2018-12-19 15:47:51');
INSERT INTO `user_info` VALUES ('2', 'abc', '2', 'abc', 1, '2018-12-19 15:44:14', '2018-12-19 15:44:37');

SET FOREIGN_KEY_CHECKS = 1;
