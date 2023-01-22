/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : rate-demo

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 10/09/2022 17:00:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `rate` decimal(10, 1) NULL DEFAULT NULL COMMENT '评分',
  `foreign_id` int(11) NULL DEFAULT NULL COMMENT '业务模块的id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级评论id',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '回复对象',
  `createtime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '哈哈哈非常nice~', '张三', 1, 4.0, 1, NULL, NULL, '2022-09-10 16:29:02');
INSERT INTO `comment` VALUES (2, '3333333', '张三', 1, 2.0, 1, NULL, NULL, '2022-09-10 16:29:02');
INSERT INTO `comment` VALUES (3, '4.5', '张三', 1, 4.5, 1, NULL, NULL, '2022-09-10 16:29:02');
INSERT INTO `comment` VALUES (4, '2222333', '李四', 2, NULL, 1, 1, '张三', '2022-09-10 16:29:02');
INSERT INTO `comment` VALUES (5, '2233344', '李四', 2, NULL, 1, 1, '张三', '2022-09-10 16:29:02');
INSERT INTO `comment` VALUES (6, '33333344444', '张三', 1, 0.0, 1, NULL, NULL, '2022-09-10 16:29:02');
INSERT INTO `comment` VALUES (7, '我在回复李四了！', '李四', 2, NULL, 1, 1, '李四', '2022-09-10 16:39:36');
INSERT INTO `comment` VALUES (8, '我是老五，我在回复李四', '王五', 3, NULL, 1, 1, '李四', '2022-09-10 16:40:48');

SET FOREIGN_KEY_CHECKS = 1;
