/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 127.0.0.1:3306
 Source Schema         : ptadmui

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 22/03/2019 15:26:34
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_employee
-- ----------------------------
DROP TABLE IF EXISTS `demo_employee`;
CREATE TABLE `demo_employee`  (
  `employee_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `base` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作地点',
  `age` int(11) NOT NULL COMMENT '年龄',
  `hire_date` date NOT NULL COMMENT '入职时间',
  `salary` decimal(10, 0) NOT NULL COMMENT '年薪',
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'datatables员工演示数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_employee
-- ----------------------------
INSERT INTO `demo_employee` VALUES (1, '李霞', '系统架构师', '北京', 61, '2011-04-25', 320800);
INSERT INTO `demo_employee` VALUES (2, '杜重治', '会计', '上海', 63, '2011-07-25', 170750);
INSERT INTO `demo_employee` VALUES (3, '陈锋', '初级开发者', '深圳', 66, '2009-01-12', 86000);
INSERT INTO `demo_employee` VALUES (4, '郑伯宁', '高级JavaScript开发者', '北京', 22, '2012-03-29', 433060);
INSERT INTO `demo_employee` VALUES (5, '施华军', '会计', '上海', 33, '2008-11-28', 162700);
INSERT INTO `demo_employee` VALUES (6, '吴书振', '集成专家', '南京', 61, '2012-12-03', 372000);
INSERT INTO `demo_employee` VALUES (7, '张宁', '销售代表', '深圳', 59, '2012-08-06', 137500);
INSERT INTO `demo_employee` VALUES (8, '马世波', '集成专家', '上海', 55, '2010-10-14', 327900);
INSERT INTO `demo_employee` VALUES (9, '张章', 'Javascript开发者', '深圳', 39, '2009-09-15', 205500);
INSERT INTO `demo_employee` VALUES (10, '张竹影', '软件工程师', '北京', 23, '2008-12-15', 103600);
INSERT INTO `demo_employee` VALUES (11, '韩庆福', '办公室主管', '广州', 30, '2008-12-19', 90560);
INSERT INTO `demo_employee` VALUES (12, '刘勇', '客户服务', '北京', 22, '2013-03-03', 342000);
INSERT INTO `demo_employee` VALUES (13, '张忆湫', '大区经理', '深圳', 36, '2008-10-16', 470600);
INSERT INTO `demo_employee` VALUES (14, '尚志兴', '高级营销设计师', '广州', 43, '2013-12-18', 313500);
INSERT INTO `demo_employee` VALUES (15, '杜若芳', '大区经理', '广州', 19, '2010-03-17', 385750);
INSERT INTO `demo_employee` VALUES (16, '杨乔松', '营销设计师', '广州', 66, '2012-11-27', 198500);
INSERT INTO `demo_employee` VALUES (17, '闫跃进', 'CEO', '南京', 64, '2010-06-09', 725000);
INSERT INTO `demo_employee` VALUES (18, '孙凯', '系统管理员', '南京', 59, '2009-04-10', 237500);
INSERT INTO `demo_employee` VALUES (19, '赖祥校', '软件工程师', '广州', 41, '2012-10-13', 132000);
INSERT INTO `demo_employee` VALUES (20, '郭晖', '人事主管', '北京', 35, '2012-09-26', 217500);
INSERT INTO `demo_employee` VALUES (21, '贺光明', '技术主管', '南京', 30, '2011-09-03', 345000);
INSERT INTO `demo_employee` VALUES (22, '邓小燕', 'CMO', '南京', 40, '2009-06-25', 675000);
INSERT INTO `demo_employee` VALUES (23, '白莉惠', '售前支持', '南京', 21, '2011-12-12', 106450);
INSERT INTO `demo_employee` VALUES (24, '杨海霞', '销售代表', '成都', 23, '2010-09-20', 85600);
INSERT INTO `demo_employee` VALUES (25, '利旭日', 'CEO', '广州', 47, '2009-10-09', 1200000);
INSERT INTO `demo_employee` VALUES (26, '范永胜', '开发者', '北京', 42, '2010-12-22', 92575);
INSERT INTO `demo_employee` VALUES (27, '于怀斌', '大区经理', '苏州', 28, '2010-11-14', 357650);
INSERT INTO `demo_employee` VALUES (28, '赵淑娜', '软件工程师', '深圳', 28, '2011-06-07', 206850);
INSERT INTO `demo_employee` VALUES (29, '张淑杰', 'COO', '深圳', 48, '2010-03-11', 850000);
INSERT INTO `demo_employee` VALUES (30, '陈俊军', '区域销售', '上海', 20, '2011-08-15', 163000);
INSERT INTO `demo_employee` VALUES (31, '郭增杰', '集成专家', '成都', 37, '2011-06-02', 95400);
INSERT INTO `demo_employee` VALUES (32, '林云', '开发者', '广州', 53, '2009-10-22', 114500);
INSERT INTO `demo_employee` VALUES (33, '郭述龙', '技术作者', '广州', 27, '2011-05-07', 145000);
INSERT INTO `demo_employee` VALUES (34, '杨军', '团队主管', '深圳', 22, '2008-10-26', 235500);
INSERT INTO `demo_employee` VALUES (35, '张海龙', '售后支持', '北京', 46, '2011-03-09', 324050);
INSERT INTO `demo_employee` VALUES (36, '耿静', '营销设计师', '深圳', 47, '2009-12-09', 85675);
INSERT INTO `demo_employee` VALUES (37, '刘晋荣', '办公室主管', '深圳', 51, '2008-12-16', 164500);
INSERT INTO `demo_employee` VALUES (38, '孙宁', '秘书', '深圳', 41, '2010-02-12', 109850);
INSERT INTO `demo_employee` VALUES (39, '邢洪锐', '财务总监', '深圳', 62, '2009-02-14', 452500);
INSERT INTO `demo_employee` VALUES (40, '陈云云', '办公室主管', '广州', 37, '2008-12-11', 136200);
INSERT INTO `demo_employee` VALUES (41, '张禄', '主任', '南京', 65, '2008-09-26', 645750);
INSERT INTO `demo_employee` VALUES (42, '王增凤', '技术支持工程师', '苏州', 64, '2011-02-03', 234500);
INSERT INTO `demo_employee` VALUES (43, '沈捷', '软件工程师', '广州', 38, '2011-05-03', 163500);
INSERT INTO `demo_employee` VALUES (44, '汪化言', '技术支持工程师', '上海', 37, '2009-08-19', 139575);
INSERT INTO `demo_employee` VALUES (45, '杨正机', '开发者', '南京', 61, '2013-08-13', 985400);
INSERT INTO `demo_employee` VALUES (46, '戴向军', '技术支持工程师', '深圳', 47, '2009-07-07', 87500);
INSERT INTO `demo_employee` VALUES (47, '王延芳', '数据分析工程师', '苏州', 64, '2012-02-09', 138575);
INSERT INTO `demo_employee` VALUES (48, '沈健', '软件工程师', '南京', 63, '2010-01-14', 125250);
INSERT INTO `demo_employee` VALUES (49, '刘伟峰', '软件工程师', '深圳', 56, '2012-06-01', 115000);
INSERT INTO `demo_employee` VALUES (50, '陆先生', '初级Javascript开发者', '北京', 43, '2013-02-01', 75650);
INSERT INTO `demo_employee` VALUES (51, '纪虹羽', '销售代表', '南京', 46, '2011-12-06', 145600);
INSERT INTO `demo_employee` VALUES (52, '肖艳', '大区经理', '广州', 47, '2011-03-21', 356250);
INSERT INTO `demo_employee` VALUES (53, '沈国金', '系统管理员', '广州', 21, '2009-02-27', 103500);
INSERT INTO `demo_employee` VALUES (54, '王燕峰', '开发者', '深圳', 30, '2010-07-14', 86500);
INSERT INTO `demo_employee` VALUES (55, '徐若琳', '大区经理', '北京', 51, '2008-11-13', 183000);
INSERT INTO `demo_employee` VALUES (56, '徐成业', 'Javascript开发者', '苏州', 29, '2011-06-27', 183000);
INSERT INTO `demo_employee` VALUES (57, '李岸白', '客户服务', '南京', 27, '2011-01-25', 112000);

-- ----------------------------
-- Table structure for demo_entry
-- ----------------------------
DROP TABLE IF EXISTS `demo_entry`;
CREATE TABLE `demo_entry`  (
  `id` bigint(32) NOT NULL,
  `entry_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `entry_price` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '示例条目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_entry
-- ----------------------------
INSERT INTO `demo_entry` VALUES (1, '条目1', '￥1');
INSERT INTO `demo_entry` VALUES (2, '条目2', '￥2');
INSERT INTO `demo_entry` VALUES (3, '条目3', '￥3');
INSERT INTO `demo_entry` VALUES (4, '条目4', '￥4');
INSERT INTO `demo_entry` VALUES (5, '条目5', '￥5');
INSERT INTO `demo_entry` VALUES (6, '条目6', '￥6');
INSERT INTO `demo_entry` VALUES (7, '条目7', '￥7');
INSERT INTO `demo_entry` VALUES (8, '条目8', '￥8');
INSERT INTO `demo_entry` VALUES (9, '条目9', '￥9');
INSERT INTO `demo_entry` VALUES (10, '条目10', '￥10');
INSERT INTO `demo_entry` VALUES (11, '条目11', '￥11');
INSERT INTO `demo_entry` VALUES (12, '条目12', '￥12');
INSERT INTO `demo_entry` VALUES (13, '条目13', '￥13');
INSERT INTO `demo_entry` VALUES (14, '条目14', '￥14');
INSERT INTO `demo_entry` VALUES (15, '条目15', '￥15');
INSERT INTO `demo_entry` VALUES (16, '条目16', '￥16');
INSERT INTO `demo_entry` VALUES (17, '条目17', '￥17');
INSERT INTO `demo_entry` VALUES (18, '条目18', '￥18');
INSERT INTO `demo_entry` VALUES (19, '条目19', '￥19');
INSERT INTO `demo_entry` VALUES (20, '条目20', '￥20');
INSERT INTO `demo_entry` VALUES (21, '条目21', '￥21');
INSERT INTO `demo_entry` VALUES (22, '条目22', '￥22');
INSERT INTO `demo_entry` VALUES (23, '条目23', '￥23');
INSERT INTO `demo_entry` VALUES (24, '条目24', '￥24');
INSERT INTO `demo_entry` VALUES (25, '条目25', '￥25');
INSERT INTO `demo_entry` VALUES (26, '条目26', '￥26');
INSERT INTO `demo_entry` VALUES (27, '条目27', '￥27');
INSERT INTO `demo_entry` VALUES (28, '条目28', '￥28');
INSERT INTO `demo_entry` VALUES (29, '条目29', '￥29');
INSERT INTO `demo_entry` VALUES (30, '条目30', '￥30');
INSERT INTO `demo_entry` VALUES (31, '条目31', '￥31');
INSERT INTO `demo_entry` VALUES (32, '条目32', '￥32');
INSERT INTO `demo_entry` VALUES (33, '条目33', '￥33');
INSERT INTO `demo_entry` VALUES (34, '条目34', '￥34');
INSERT INTO `demo_entry` VALUES (35, '条目35', '￥35');
INSERT INTO `demo_entry` VALUES (36, '条目36', '￥36');
INSERT INTO `demo_entry` VALUES (37, '条目37', '￥37');
INSERT INTO `demo_entry` VALUES (38, '条目38', '￥38');
INSERT INTO `demo_entry` VALUES (39, '条目39', '￥39');
INSERT INTO `demo_entry` VALUES (40, '条目40', '￥40');
INSERT INTO `demo_entry` VALUES (41, '条目41', '￥41');
INSERT INTO `demo_entry` VALUES (42, '条目42', '￥42');
INSERT INTO `demo_entry` VALUES (43, '条目43', '￥43');
INSERT INTO `demo_entry` VALUES (44, '条目44', '￥44');
INSERT INTO `demo_entry` VALUES (45, '条目45', '￥45');
INSERT INTO `demo_entry` VALUES (46, '条目46', '￥46');
INSERT INTO `demo_entry` VALUES (47, '条目47', '￥47');
INSERT INTO `demo_entry` VALUES (48, '条目48', '￥48');
INSERT INTO `demo_entry` VALUES (49, '条目49', '￥49');
INSERT INTO `demo_entry` VALUES (50, '条目50', '￥50');
INSERT INTO `demo_entry` VALUES (51, '条目51', '￥51');
INSERT INTO `demo_entry` VALUES (52, '条目52', '￥52');
INSERT INTO `demo_entry` VALUES (53, '条目53', '￥53');
INSERT INTO `demo_entry` VALUES (54, '条目54', '￥54');
INSERT INTO `demo_entry` VALUES (55, '条目55', '￥55');
INSERT INTO `demo_entry` VALUES (56, '条目56', '￥56');
INSERT INTO `demo_entry` VALUES (57, '条目57', '￥57');
INSERT INTO `demo_entry` VALUES (58, '条目58', '￥58');
INSERT INTO `demo_entry` VALUES (59, '条目59', '￥59');
INSERT INTO `demo_entry` VALUES (60, '条目60', '￥60');
INSERT INTO `demo_entry` VALUES (61, '条目61', '￥61');
INSERT INTO `demo_entry` VALUES (62, '条目62', '￥62');
INSERT INTO `demo_entry` VALUES (63, '条目63', '￥63');
INSERT INTO `demo_entry` VALUES (64, '条目64', '￥64');
INSERT INTO `demo_entry` VALUES (65, '条目65', '￥65');
INSERT INTO `demo_entry` VALUES (66, '条目66', '￥66');
INSERT INTO `demo_entry` VALUES (67, '条目67', '￥67');
INSERT INTO `demo_entry` VALUES (68, '条目68', '￥68');
INSERT INTO `demo_entry` VALUES (69, '条目69', '￥69');
INSERT INTO `demo_entry` VALUES (70, '条目70', '￥70');
INSERT INTO `demo_entry` VALUES (71, '条目71', '￥71');
INSERT INTO `demo_entry` VALUES (72, '条目72', '￥72');
INSERT INTO `demo_entry` VALUES (73, '条目73', '￥73');
INSERT INTO `demo_entry` VALUES (74, '条目74', '￥74');
INSERT INTO `demo_entry` VALUES (75, '条目75', '￥75');
INSERT INTO `demo_entry` VALUES (76, '条目76', '￥76');
INSERT INTO `demo_entry` VALUES (77, '条目77', '￥77');
INSERT INTO `demo_entry` VALUES (78, '条目78', '￥78');
INSERT INTO `demo_entry` VALUES (79, '条目79', '￥79');
INSERT INTO `demo_entry` VALUES (80, '条目80', '￥80');
INSERT INTO `demo_entry` VALUES (81, '条目81', '￥81');
INSERT INTO `demo_entry` VALUES (82, '条目82', '￥82');
INSERT INTO `demo_entry` VALUES (83, '条目83', '￥83');
INSERT INTO `demo_entry` VALUES (84, '条目84', '￥84');
INSERT INTO `demo_entry` VALUES (85, '条目85', '￥85');
INSERT INTO `demo_entry` VALUES (86, '条目86', '￥86');
INSERT INTO `demo_entry` VALUES (87, '条目87', '￥87');
INSERT INTO `demo_entry` VALUES (88, '条目88', '￥88');
INSERT INTO `demo_entry` VALUES (89, '条目89', '￥89');
INSERT INTO `demo_entry` VALUES (90, '条目90', '￥90');
INSERT INTO `demo_entry` VALUES (91, '条目91', '￥91');
INSERT INTO `demo_entry` VALUES (92, '条目92', '￥92');
INSERT INTO `demo_entry` VALUES (93, '条目93', '￥93');
INSERT INTO `demo_entry` VALUES (94, '条目94', '￥94');
INSERT INTO `demo_entry` VALUES (95, '条目95', '￥95');
INSERT INTO `demo_entry` VALUES (96, '条目96', '￥96');
INSERT INTO `demo_entry` VALUES (97, '条目97', '￥97');
INSERT INTO `demo_entry` VALUES (98, '条目98', '￥98');
INSERT INTO `demo_entry` VALUES (99, '条目99', '￥99');
INSERT INTO `demo_entry` VALUES (100, '条目100', '￥100');
INSERT INTO `demo_entry` VALUES (101, '条目101', '￥101');
INSERT INTO `demo_entry` VALUES (102, '条目102', '￥102');
INSERT INTO `demo_entry` VALUES (103, '条目103', '￥103');
INSERT INTO `demo_entry` VALUES (104, '条目104', '￥104');
INSERT INTO `demo_entry` VALUES (105, '条目105', '￥105');
INSERT INTO `demo_entry` VALUES (106, '条目106', '￥106');
INSERT INTO `demo_entry` VALUES (107, '条目107', '￥107');
INSERT INTO `demo_entry` VALUES (108, '条目108', '￥108');
INSERT INTO `demo_entry` VALUES (109, '条目109', '￥109');
INSERT INTO `demo_entry` VALUES (110, '条目110', '￥110');
INSERT INTO `demo_entry` VALUES (111, '条目111', '￥111');
INSERT INTO `demo_entry` VALUES (112, '条目112', '￥112');
INSERT INTO `demo_entry` VALUES (113, '条目113', '￥113');
INSERT INTO `demo_entry` VALUES (114, '条目114', '￥114');
INSERT INTO `demo_entry` VALUES (115, '条目115', '￥115');
INSERT INTO `demo_entry` VALUES (116, '条目116', '￥116');
INSERT INTO `demo_entry` VALUES (117, '条目117', '￥117');
INSERT INTO `demo_entry` VALUES (118, '条目118', '￥118');
INSERT INTO `demo_entry` VALUES (119, '条目119', '￥119');
INSERT INTO `demo_entry` VALUES (120, '条目120', '￥120');
INSERT INTO `demo_entry` VALUES (121, '条目121', '￥121');
INSERT INTO `demo_entry` VALUES (122, '条目122', '￥122');
INSERT INTO `demo_entry` VALUES (123, '条目123', '￥123');
INSERT INTO `demo_entry` VALUES (124, '条目124', '￥124');
INSERT INTO `demo_entry` VALUES (125, '条目125', '￥125');
INSERT INTO `demo_entry` VALUES (126, '条目126', '￥126');
INSERT INTO `demo_entry` VALUES (127, '条目127', '￥127');
INSERT INTO `demo_entry` VALUES (128, '条目128', '￥128');
INSERT INTO `demo_entry` VALUES (129, '条目129', '￥129');
INSERT INTO `demo_entry` VALUES (130, '条目130', '￥130');
INSERT INTO `demo_entry` VALUES (131, '条目131', '￥131');
INSERT INTO `demo_entry` VALUES (132, '条目132', '￥132');
INSERT INTO `demo_entry` VALUES (133, '条目133', '￥133');
INSERT INTO `demo_entry` VALUES (134, '条目134', '￥134');
INSERT INTO `demo_entry` VALUES (135, '条目135', '￥135');
INSERT INTO `demo_entry` VALUES (136, '条目136', '￥136');
INSERT INTO `demo_entry` VALUES (137, '条目137', '￥137');
INSERT INTO `demo_entry` VALUES (138, '条目138', '￥138');
INSERT INTO `demo_entry` VALUES (139, '条目139', '￥139');
INSERT INTO `demo_entry` VALUES (140, '条目140', '￥140');
INSERT INTO `demo_entry` VALUES (141, '条目141', '￥141');
INSERT INTO `demo_entry` VALUES (142, '条目142', '￥142');
INSERT INTO `demo_entry` VALUES (143, '条目143', '￥143');
INSERT INTO `demo_entry` VALUES (144, '条目144', '￥144');
INSERT INTO `demo_entry` VALUES (145, '条目145', '￥145');
INSERT INTO `demo_entry` VALUES (146, '条目146', '￥146');
INSERT INTO `demo_entry` VALUES (147, '条目147', '￥147');
INSERT INTO `demo_entry` VALUES (148, '条目148', '￥148');
INSERT INTO `demo_entry` VALUES (149, '条目149', '￥149');
INSERT INTO `demo_entry` VALUES (150, '条目150', '￥150');
INSERT INTO `demo_entry` VALUES (151, '条目151', '￥151');
INSERT INTO `demo_entry` VALUES (152, '条目152', '￥152');
INSERT INTO `demo_entry` VALUES (153, '条目153', '￥153');
INSERT INTO `demo_entry` VALUES (154, '条目154', '￥154');
INSERT INTO `demo_entry` VALUES (155, '条目155', '￥155');
INSERT INTO `demo_entry` VALUES (156, '条目156', '￥156');
INSERT INTO `demo_entry` VALUES (157, '条目157', '￥157');
INSERT INTO `demo_entry` VALUES (158, '条目158', '￥158');
INSERT INTO `demo_entry` VALUES (159, '条目159', '￥159');
INSERT INTO `demo_entry` VALUES (160, '条目160', '￥160');
INSERT INTO `demo_entry` VALUES (161, '条目161', '￥161');
INSERT INTO `demo_entry` VALUES (162, '条目162', '￥162');
INSERT INTO `demo_entry` VALUES (163, '条目163', '￥163');
INSERT INTO `demo_entry` VALUES (164, '条目164', '￥164');
INSERT INTO `demo_entry` VALUES (165, '条目165', '￥165');
INSERT INTO `demo_entry` VALUES (166, '条目166', '￥166');
INSERT INTO `demo_entry` VALUES (167, '条目167', '￥167');
INSERT INTO `demo_entry` VALUES (168, '条目168', '￥168');
INSERT INTO `demo_entry` VALUES (169, '条目169', '￥169');
INSERT INTO `demo_entry` VALUES (170, '条目170', '￥170');
INSERT INTO `demo_entry` VALUES (171, '条目171', '￥171');
INSERT INTO `demo_entry` VALUES (172, '条目172', '￥172');
INSERT INTO `demo_entry` VALUES (173, '条目173', '￥173');
INSERT INTO `demo_entry` VALUES (174, '条目174', '￥174');
INSERT INTO `demo_entry` VALUES (175, '条目175', '￥175');
INSERT INTO `demo_entry` VALUES (176, '条目176', '￥176');
INSERT INTO `demo_entry` VALUES (177, '条目177', '￥177');
INSERT INTO `demo_entry` VALUES (178, '条目178', '￥178');
INSERT INTO `demo_entry` VALUES (179, '条目179', '￥179');
INSERT INTO `demo_entry` VALUES (180, '条目180', '￥180');
INSERT INTO `demo_entry` VALUES (181, '条目181', '￥181');
INSERT INTO `demo_entry` VALUES (182, '条目182', '￥182');
INSERT INTO `demo_entry` VALUES (183, '条目183', '￥183');
INSERT INTO `demo_entry` VALUES (184, '条目184', '￥184');
INSERT INTO `demo_entry` VALUES (185, '条目185', '￥185');
INSERT INTO `demo_entry` VALUES (186, '条目186', '￥186');
INSERT INTO `demo_entry` VALUES (187, '条目187', '￥187');
INSERT INTO `demo_entry` VALUES (188, '条目188', '￥188');
INSERT INTO `demo_entry` VALUES (189, '条目189', '￥189');
INSERT INTO `demo_entry` VALUES (190, '条目190', '￥190');
INSERT INTO `demo_entry` VALUES (191, '条目191', '￥191');
INSERT INTO `demo_entry` VALUES (192, '条目192', '￥192');
INSERT INTO `demo_entry` VALUES (193, '条目193', '￥193');
INSERT INTO `demo_entry` VALUES (194, '条目194', '￥194');
INSERT INTO `demo_entry` VALUES (195, '条目195', '￥195');
INSERT INTO `demo_entry` VALUES (196, '条目196', '￥196');
INSERT INTO `demo_entry` VALUES (197, '条目197', '￥197');
INSERT INTO `demo_entry` VALUES (198, '条目198', '￥198');
INSERT INTO `demo_entry` VALUES (199, '条目199', '￥199');

-- ----------------------------
-- Table structure for sys_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `sys_blacklist`;
CREATE TABLE `sys_blacklist`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '黑名单id',
  `ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP地址',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE,
  INDEX `FK_BLACKLIST_ID`(`create_user`) USING BTREE,
  CONSTRAINT `FK_BLACKLIST_ID` FOREIGN KEY (`create_user`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '黑名单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_display_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_display_setting`;
CREATE TABLE `sys_display_setting`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '显示设置id',
  `navigation_color` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '导航条颜色',
  `across_flag` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '通栏显示',
  `menu_theme` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单主题',
  `menu_display` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单显示',
  `menu_txt_icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收起显示图标或文字',
  `theme_color` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主题颜色',
  `global_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '系统全局设置',
  `default_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '系统默认设置',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统显示设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_display_setting
-- ----------------------------
INSERT INTO `sys_display_setting` VALUES ('1', 'bg-blue-600', 'navbar-inverse', 'site-menubar-dark', 'site-menubar-unfold', 'site-menubar-keep', 'primary', b'0', b'1', '', '2018-09-24 16:00:36');
INSERT INTO `sys_display_setting` VALUES ('15531322202432028285', '', 'navbar-inverse', 'site-menubar-dark', 'site-menubar-unfold', 'site-menubar-keep', 'teal', b'0', b'0', '10010101', '2019-03-22 11:04:02');
INSERT INTO `sys_display_setting` VALUES ('2', 'bg-blue-600', 'navbar-inverse', 'site-menubar-dark', 'site-menubar-unfold', 'site-menubar-keep', 'primary', b'1', b'0', '', '2017-12-21 17:51:18');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志ID',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志路径',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志类型',
  `params` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operate_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE,
  INDEX `Index_CreateUser`(`create_user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('15406401977257444203', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=xcdq rememberMe=on ]', '10010101', '2018-10-27 19:36:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15529879904930593762', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=ngdu ]', '10010101', '2019-03-19 17:33:10', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530648034647163793', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=shkz ]', '10010101', '2019-03-20 14:53:23', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530653000303976851', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=djy6 ]', '10010101', '2019-03-20 15:01:40', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530653189668491255', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=pvsd ]', '10010101', '2019-03-20 15:01:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530725668565375494', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=aakk ]', '10010101', '2019-03-20 17:02:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530728810945318304', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=6bdn ]', '10010101', '2019-03-20 17:08:01', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530730182877634143', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=55uc ]', '10010101', '2019-03-20 17:10:18', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530731010136745722', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=mhdf ]', '10010101', '2019-03-20 17:11:41', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530733094301384209', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=k68a ]', '10010101', '2019-03-20 17:15:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530733340052142585', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=q2sr ]', '10010101', '2019-03-20 17:15:34', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530733530867425567', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=ga57 ]', '10010101', '2019-03-20 17:15:53', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530734065434442220', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=9zuz ]', '10010101', '2019-03-20 17:16:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530735636124703919', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=kyy8 ]', '10010101', '2019-03-20 17:19:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530737299084736365', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=pukr ]', '10010101', '2019-03-20 17:22:10', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530737801030949978', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=gwvq ]', '10010101', '2019-03-20 17:23:00', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530744194228483993', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=4j6q ]', '10010101', '2019-03-20 17:33:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530744553234644767', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=g2vr ]', '10010101', '2019-03-20 17:34:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530744823215941582', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=r2wi ]', '10010101', '2019-03-20 17:34:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530750816055288595', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=fqpb ]', '10010101', '2019-03-20 17:44:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530752499860972307', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=ecvy ]', '10010101', '2019-03-20 17:47:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15530755886736972698', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=msz2 ]', '10010101', '2019-03-20 17:53:09', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('15531316863396749235', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=d2ax ]', '10010101', '2019-03-21 09:28:06', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15531318763178931847', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=7ppt ]', '10010101', '2019-03-21 09:31:16', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15531334987972798871', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=xaze ]', '10010101', '2019-03-21 09:58:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532187432821603089', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=9ezz ]', '10010101', '2019-03-22 09:39:03', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532238174257706289', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=n6zn ]', '10010101', '2019-03-22 11:03:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532272711323626266', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=gamp ]', '10010101', '2019-03-22 12:01:11', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532281460889641007', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=ubwr ]', '10010101', '2019-03-22 12:15:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532283642825747859', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=vykr ]', '10010101', '2019-03-22 12:19:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532284315974923285', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=tnbc ]', '10010101', '2019-03-22 12:20:32', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532284522533599080', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=jsmg ]', '10010101', '2019-03-22 12:20:52', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532290093399385351', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=k8xg ]', '10010101', '2019-03-22 12:30:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532292103674668144', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=kk5y ]', '10010101', '2019-03-22 12:33:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532372181452462113', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=yqzn ]', '10010101', '2019-03-22 14:46:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532375013664435418', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=eyqf ]', '10010101', '2019-03-22 14:51:41', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532375267361698489', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=kafe ]', '10010101', '2019-03-22 14:52:07', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532375401194282687', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=3hca ]', '10010101', '2019-03-22 14:52:20', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_log` VALUES ('15532376560481137764', '/loginValidate', '登录', '[userName=admin password=e10adc3949ba59abbe56e057f20f883e verifyCode=qfmw ]', '10010101', '2019-03-22 14:54:16', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_log_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_config`;
CREATE TABLE `sys_log_config`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志配置表',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志路径',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志类型',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否已删除 0：否，1：是',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_config
-- ----------------------------
INSERT INTO `sys_log_config` VALUES ('1001', '/loginValidate', '登录', b'0', '登录系统', '2018-09-07 15:57:37', '10010101', '2018-09-28 15:06:33', '10010101');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父菜单id',
  `layer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单层级，例如：0101010101',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE,
  INDEX `Index_ParentId`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表，parent_id标识父子关系，一级菜单parent_id为0，最多支持5级菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '07', 'wb-settings', '', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('10', '显示设置', '8', '07010501', '', '/system/settings/display', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('11', '日志设置', '8', '07010502', '', '/system/settings/log', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('11112', '按钮', '83', '01010202', '', '/components/basic/buttons', '1', '2017-01-05 13:58:59', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11113', '矢量图标', '83', '01010203', '', '/components/basic/icons', '1', '2017-01-05 13:58:59', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11114', '下拉菜单', '83', '01010204', '', '/components/basic/dropdowns', '1', '2017-01-05 13:58:59', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11115', '列表组', '83', '01010205', '', '/components/basic/list', '1', '2017-01-05 13:58:59', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11116', '气泡提示 & 弹出框', '83', '01010206', '', '/components/basic/tooltip-popover', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11117', '模态框', '83', '01010207', '', '/components/basic/modals', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11118', '选项卡 & 折叠面板', '83', '01010208', '', '/components/basic/tabs-accordions', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11119', '图片', '83', '01010209', '', '/components/basic/images', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11120', '徽章 & 标签', '83', '01010210', '', '/components/basic/badges', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11122', '轮播组件', '83', '01010211', '', '/components/basic/carousel', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11123', '排版', '83', '01010212', '', '/components/basic/typography', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11124', '配色方案', '83', '01010214', '', '/components/basic/colors', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11125', '辅助类', '83', '01010215', '', '/components/basic/utilities', '1', '2017-01-05 14:17:07', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11126', '进阶', '72', '010103', 'fa-cubes', '', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11127', '新手指引', '11126', '01010301', '', '/components/advanced/tour', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11128', '动画', '11126', '01010302', '', '/components/advanced/animation', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11130', '灯箱', '11126', '01010303', '', '/components/advanced/lightbox', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11131', '滚动条', '11126', '01010304', '', '/components/advanced/scrollbar', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11132', '评分', '11126', '01010305', '', '/components/advanced/rating', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11133', '右键菜单', '11126', '01010306', '', '/components/advanced/context-menu', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11134', 'Alertify弹框', '11126', '01010307', '', '/components/advanced/alertify', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11135', '瀑布流布局', '11126', '01010308', '', '/components/advanced/masonry', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11136', '树形控件', '11126', '01010309', '', '', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11137', 'JsTree', '11136', '0101030901', '', '/components/advanced/tree/jstree', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11138', 'Treeview', '11136', '0101030902', '', '/components/advanced/tree/treeview', '1', '2017-01-05 14:26:31', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11139', 'Toaster 通知', '11126', '01010310', '', '/components/advanced/toastr', '1', '2017-01-05 14:26:32', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11143', '拖动排序', '11126', '01010311', '', '/components/advanced/sortable-nestable', '1', '2017-01-05 14:26:32', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11144', '弹层组件', '11126', '01010312', '', '/components/advanced/bootbox-sweetalert', '1', '2017-01-05 14:26:32', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11145', '组件', '72', '010104', 'fa-plug', '', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11146', '警告框', '11145', '01010401', '', '/components/structure/alerts', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11147', '彩带', '11145', '01010402', '', '/components/structure/ribbon', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11148', '价目表', '11145', '01010403', '', '/components/structure/pricing-tables', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11149', '遮罩', '11145', '01010404', '', '/components/structure/overlay', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11150', '覆盖层', '11145', '01010405', '', '/components/structure/cover', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11151', '时间轴', '11145', '01010406', '', '', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11152', '简单时间轴', '11151', '0101040601', '', '/components/structure/timeline/simple', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11153', '图标时间轴', '11151', '0101040602', '', '/components/structure/timeline/icons', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11154', '步骤条', '11145', '01010407', '', '/components/structure/step', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11155', '评论', '11145', '01010408', '', '/components/structure/comments', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11156', '媒体', '11145', '01010409', '', '/components/structure/media', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11157', '聊天窗口', '11145', '01010410', '', '/components/structure/chat', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11158', '客户评价', '11145', '01010411', '', '/components/structure/testimonials', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11159', '导航', '11145', '01010412', '', '/components/structure/nav', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11160', '导航条', '11145', '01010413', '', '/components/structure/navbars', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11161', '引用', '11145', '01010414', '', '/components/structure/blockquotes', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11162', '分页', '11145', '01010415', '', '/components/structure/pagination', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11163', '包屑导航', '11145', '01010416', '', '/components/structure/breadcrumbs', '1', '2017-01-05 16:47:01', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11164', '卡片', '72', '010105', 'fa-puzzle-piece', '', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11165', '统计', '11164', '01010501', '', '/components/cards/statistics', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11166', '数据', '11164', '01010502', '', '/components/cards/data', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11167', '博客', '11164', '01010503', '', '/components/cards/blog', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11168', '图表', '11164', '01010504', '', '/components/cards/chart', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11169', '社交', '11164', '01010505', '', '/components/cards/social', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11170', '天气', '11164', '01010506', '', '/components/cards/weather', '1', '2017-01-05 16:52:52', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11171', '页面示例', '11236', '0201', '', '', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11172', '首页示例', '11171', '020101', 'fa-home', '', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11173', '首页示例 01', '11172', '02010101', '', '/pages/home/index-v1', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11174', '首页示例 02', '11172', '02010102', '', '/pages/home/index-v2', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11175', '电商网站型', '11172', '02010103', '', '/pages/home/ecommerce', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11176', '数据统计型', '11172', '02010104', '', '/pages/home/analytics', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11177', '团队协作型', '11172', '02010105', '', '/pages/home/team', '1', '2017-01-05 16:56:58', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11178', '常用页面', '11171', '020102', 'fa-files-o', '', '1', '2017-01-05 17:12:52', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11179', '空白页面', '11178', '02010201', '', '/pages/general/blank', '1', '2017-01-05 17:12:52', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11180', '帮助文档', '11178', '02010202', '', '/pages/general/faq', '1', '2017-01-05 17:12:52', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11183', '相册', '11178', '02010203', '', '/pages/general/gallery-grid', '1', '2017-01-05 17:12:52', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11187', '搜索', '11178', '02010204', '', '/pages/general/search-result', '1', '2017-01-05 17:12:52', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11188', '客户列表', '11178', '02010205', '', '/pages/general/user', '1', '2017-01-05 17:12:52', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11189', '邮件模板', '11178', '02010206', '', '/pages/general/email', '1', '2017-01-05 17:12:52', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11191', '错误页面', '11178', '02010208', '', '/errors', '1', '2017-01-05 17:12:52', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11192', '团队协作', '11171', '020103', 'fa-group', '', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11193', '日历', '11192', '02010301', '', '/pages/team/calendar', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11194', '笔记', '11192', '02010302', '', '/pages/team/notebook', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11195', '任务看板', '11192', '02010303', '', '/pages/team/taskboard', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11196', '知识库', '11192', '02010304', '', '', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11197', '列表', '11196', '0201030401', '', '/pages/team/documents/articles', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11198', '分类', '11196', '0201030402', '', '/pages/team/documents/categories', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11199', '详情', '11196', '0201030403', '', '/pages/team/documents/article', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11200', '论坛', '11192', '02010305', '', '/pages/team/forum', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11201', '即时通讯', '11192', '02010306', '', '/pages/team/message', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11202', '邮箱', '11192', '02010307', '', '/pages/team/mailbox', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11203', '文件管理', '11192', '02010308', '', '/pages/team/media', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11204', '项目', '11192', '02010309', '', '', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11205', '项目列表 01', '11204', '0201030901', '', '/pages/team/projects', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11206', '项目列表 02', '11204', '0201030902', '', '/pages/team/work', '1', '2017-01-05 17:21:15', '1', '2017-03-23 14:27:35');
INSERT INTO `sys_menu` VALUES ('11230', '表格示例', '0', '03', 'wb-table', NULL, '1', '2017-01-08 16:32:50', '1', '2017-03-23 15:00:29');
INSERT INTO `sys_menu` VALUES ('11231', '表格示例', '11230', '0301', '', '', '1', '2017-01-08 16:39:28', '1', '2017-03-27 11:30:12');
INSERT INTO `sys_menu` VALUES ('11232', '基本表格', '11231', '030101', 'fa-table', '/tables/basic/index', '1', '2017-01-08 16:39:28', '1', '2017-03-27 11:30:12');
INSERT INTO `sys_menu` VALUES ('11233', 'DataTables', '11231', '030103', 'fa-list-alt', '', '1', '2017-01-08 16:39:28', '1', '2017-03-27 11:31:06');
INSERT INTO `sys_menu` VALUES ('11234', '选项', '11255', '03010401', NULL, '/tables/bootstrap-table/options/boolean-options', '1', '2018-09-18 17:34:30', '1', '2018-09-18 17:34:34');
INSERT INTO `sys_menu` VALUES ('11235', '方法', '11255', '03010403', NULL, '/tables/bootstrap-table/methods/getOptions', '1', '2018-09-18 17:35:46', '1', '2018-09-18 17:35:50');
INSERT INTO `sys_menu` VALUES ('11236', '页面示例', '0', '02', 'wb-desktop', NULL, '1', '2017-03-23 14:28:20', '1', '2017-03-23 14:59:46');
INSERT INTO `sys_menu` VALUES ('11239', '表单示例', '0', '04', 'wb-order', NULL, '1', '2017-03-23 15:00:24', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11240', '统计图表', '0', '05', 'wb-pie-chart', NULL, '1', '2017-03-23 15:00:57', '10010101', '2018-10-18 04:46:30');
INSERT INTO `sys_menu` VALUES ('11241', '菜单示例', '0', '06', 'fa-bars', NULL, '1', '2017-03-23 15:01:11', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11242', '两栏式布局', '73', '01010106', '', '/components/layouts/two-columns', '1', '2017-03-24 18:20:09', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11243', '进度条', '83', '01010213', '', '/components/basic/progress-bars', '1', '2017-03-24 18:20:09', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('11244', '菜单演示 01', '11241', '0601', '', '', '1', '2017-03-25 15:54:42', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11245', '三级菜单 002', '11294', '0601010302', '', '/examples/menu', '1', '2017-03-25 15:54:42', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11246', '一级菜单', '11244', '060101', 'fa-bars', '', '1', '2017-03-25 15:54:42', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11247', '菜单演示 02', '11241', '0602', '', '', '1', '2017-03-25 15:54:42', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11249', '代码编辑器', '11178', '02010207', '', '/pages/general/code-editor', '1', '2017-03-27 10:56:45', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11250', '网站维护中', '11178', '02010210', '', '/errors/maintenance', '1', '2017-03-27 10:56:45', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11251', '登录超时', '11178', '02010211', '', '/locked', '1', '2017-03-27 10:56:45', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11252', '没有权限', '11178', '02010209', '', '/errors/noauth', '1', '2017-03-27 11:10:15', '1', '2017-04-25 00:50:39');
INSERT INTO `sys_menu` VALUES ('11254', '树形表格', '11231', '030102', 'fa-bars', '/tables/treegrid/index', '1', '2017-03-27 11:31:06', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11255', 'BootstrapTable', '11231', '030104', 'fa-list-alt', '', '1', '2018-09-18 17:33:28', '1', '2018-09-18 17:33:34');
INSERT INTO `sys_menu` VALUES ('11256', '基本初始化', '11233', '03010301', '', '/tables/data-tables/basic-init/zero-configuration', '1', '2017-03-27 13:47:29', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11257', '高级初始化', '11233', '03010302', '', '/tables/data-tables/advanced-init/events-live', '1', '2017-03-27 13:47:29', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11258', '数据源', '11233', '03010303', '', '/tables/data-tables/data-sources/dom', '1', '2017-03-27 13:49:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11259', 'API', '11233', '03010304', '', '/tables/data-tables/api/add-row', '1', '2017-03-27 13:49:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11260', 'AJAX', '11233', '03010305', '', '/tables/data-tables/ajax/simple', '1', '2017-03-27 13:49:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11261', '服务器模式', '11233', '03010306', '', '/tables/data-tables/server-side/simple', '1', '2017-03-27 13:49:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11262', '插件', '11233', '03010307', '', '/tables/data-tables/plug-ins/api', '1', '2017-03-27 13:49:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11263', '其他', '11233', '03010308', '', '/tables/data-tables/others/fixed-header', '1', '2017-03-27 13:49:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11265', '表单相关', '11239', '0401', '', '', '1', '2017-03-27 14:07:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11266', '基本表单', '11265', '040101', 'fa-circle-o', '/forms/general', '1', '2017-03-27 14:07:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11267', '质感风格', '11265', '040102', 'fa-google', '/forms/material', '1', '2017-03-27 14:07:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11268', '高级表单元素', '11265', '040103', 'fa-toggle-off', '', '1', '2017-03-27 14:07:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11269', '表单布局', '11265', '040104', 'fa-indent', '/forms/layouts', '1', '2017-03-27 14:11:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11270', '表单向导', '11265', '040105', 'fa-arrow-right', '/forms/wizard', '1', '2017-03-27 14:11:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11271', '表单验证', '11265', '040106', 'fa-check-square-o', '/forms/validation', '1', '2017-03-27 14:11:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11272', '格式预设', '11265', '040107', 'fa-at', '/forms/masks', '1', '2017-03-27 14:11:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11274', '编辑器', '11265', '040109', 'fa-list-alt', '', '1', '2017-03-27 14:15:34', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11275', '文本编辑器', '11274', '04010901', '', '/forms/editor/summernote', '1', '2017-03-27 14:15:34', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11276', 'Markdown编辑器', '11274', '04010902', '', '/forms/editor/markdown', '1', '2017-03-27 14:15:34', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11277', '代码编辑器', '11274', '04010903', '', '/forms/editor/ace', '1', '2017-03-27 14:15:34', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11278', '图片裁剪', '11265', '040110', 'fa-cut', '/forms/image-cropping', '1', '2017-03-27 14:17:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11279', '文件上传', '11265', '040111', 'fa-cloud-upload', '/forms/dropify', '1', '2017-03-27 14:17:53', '1', '2017-03-28 17:16:23');
INSERT INTO `sys_menu` VALUES ('11280', '统计图表', '11240', '0501', '', '', '1', '2017-03-27 14:21:44', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11281', '简单图表', '11280', '050101', 'fa-pie-chart', '', '1', '2017-03-27 14:25:08', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11282', 'Chart.js', '11281', '05010101', '', '/charts/chartjs', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11283', 'Gauges', '11281', '05010102', '', '/charts/gauges', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11284', 'Flot', '11281', '05010103', '', '/charts/flot', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11285', 'Peity', '11281', '05010104', '', '/charts/peity', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11286', 'Sparkline', '11281', '05010105', '', '/charts/sparkline', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11287', 'Morris', '11281', '05010106', '', '/charts/morris', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11288', 'Chartist', '11281', '05010107', '', '/charts/chartist', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11289', 'Rickshaw', '11281', '05010108', '', '/charts/rickshaw', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11290', 'C3', '11281', '05010109', '', '/charts/c3', '1', '2017-03-27 14:25:09', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11291', '百度 Echarts', '11280', '050102', 'fa-area-chart', '/charts/echarts', '1', '2017-03-27 14:25:55', '1', '2017-03-28 17:18:48');
INSERT INTO `sys_menu` VALUES ('11292', '三级菜单 001', '11294', '0601010301', '', '/examples/menu', '1', '2017-03-28 16:23:08', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11294', '二级菜单 03', '11246', '06010103', '', '', '1', '2017-03-28 16:47:43', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11295', '独立菜单', '11244', '060102', 'fa-bars', '/examples/menu', '1', '2017-03-28 16:50:15', '1', '2017-03-28 17:01:59');
INSERT INTO `sys_menu` VALUES ('11296', '二级菜单 01', '11246', '06010101', '', '/examples/menu', '1', '2017-03-28 17:01:59', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11297', '二级菜单 02', '11246', '06010102', '', '/examples/menu', '1', '2017-03-28 17:01:59', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11298', '三级菜单 003', '11294', '0601010303', '', '/examples/menu', '1', '2017-03-28 17:01:59', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('11299', '独立菜单', '11247', '060201', 'fa-bars', '/examples/menu', '1', '2017-03-28 17:01:59', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('12', '账户信息', '3', '070201', 'fa-key', '/system/account', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('2', '系统信息', '1', '0701', '', '', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('21268', '下拉选择', '11268', '04010301', NULL, '/forms/advanced/select', '1', '2018-09-18 17:40:24', '1', '2018-09-18 17:40:26');
INSERT INTO `sys_menu` VALUES ('21269', '标签输入', '11268', '04010303', NULL, '/forms/advanced/tags-input', '1', '2018-09-18 17:41:42', '1', '2018-09-18 17:41:46');
INSERT INTO `sys_menu` VALUES ('21270', '日期时间', '11268', '04010305', NULL, '/forms/advanced/date-time', '1', '2018-09-18 17:42:34', '1', '2018-09-18 17:42:36');
INSERT INTO `sys_menu` VALUES ('21271', '复选框&单选框', '11268', '04010307', NULL, '/forms/advanced/checkbox-radio', '1', '2018-09-18 17:43:53', '1', '2018-09-18 17:43:57');
INSERT INTO `sys_menu` VALUES ('21272', '颜色&图标', '11268', '04010309', NULL, '/forms/advanced/color-icon', '1', '2018-09-18 17:44:45', '1', '2018-09-18 17:44:48');
INSERT INTO `sys_menu` VALUES ('21273', '其他', '11268', '04010311', NULL, '/forms/advanced/other', '1', '2018-09-18 17:45:42', '1', '2018-09-18 17:45:45');
INSERT INTO `sys_menu` VALUES ('21299', '扩展', '11255', '03010405', NULL, '/tables/bootstrap-table/extensions/export', '1', '2018-09-18 17:36:24', '1', '2018-09-18 17:36:28');
INSERT INTO `sys_menu` VALUES ('22', 'UI 示例', '0', '01', 'wb-library', NULL, '1', '2016-12-18 16:06:18', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('2998', '权限管理', '2', '070104', 'fa-user-secret', '/system/permission', '1', '2018-09-26 13:24:18', '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('2999', '角色管理', '2', '070103', 'fa-sliders', '/system/role', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('3', '我的账户', '1', '0702', '', '', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '2', '070101', 'fa-bars', '/system/menu', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('5', '组织机构', '2', '070102', 'fa-street-view', '/system/organization', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('6', '日志信息', '2', '070106', 'fa-file-text-o', '/system/log', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('7', '黑名单', '2', '070107', 'fa-shield', '/system/blacklist', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('72', 'UI 示例', '22', '0101', '', '', '1', '2017-01-02 17:58:41', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('73', '布局', '72', '010101', 'fa-laptop', '', '1', '2017-01-02 17:58:41', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('74', '栅格', '73', '01010101', '', '', '1', '2017-01-02 17:58:41', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('75', '基本栅格', '74', '0101010101', '', '/components/layouts/grids', '1', '2017-01-02 17:58:41', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('76', '布局栅格', '74', '0101010102', '', '/components/layouts/layout-grid', '1', '2017-01-02 17:58:41', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('77', '标题栏', '73', '01010102', '', '', '1', '2017-01-02 20:27:14', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('78', '普通', '77', '0101010201', '', '/components/layouts/headers', '1', '2017-01-02 20:27:14', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('79', '带边框', '77', '0101010202', '', '/components/layouts/bordered-header', '1', '2017-01-02 20:27:14', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('8', '系统设置', '2', '070105', 'fa-gear', '', NULL, NULL, '10010101', '2018-10-19 02:43:52');
INSERT INTO `sys_menu` VALUES ('80', '面板动画', '73', '01010103', '', '/components/layouts/panel-transition', '1', '2017-01-02 20:27:14', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('81', '盒式布局', '73', '01010104', '', '/components/layouts/boxed', '1', '2017-01-02 20:27:14', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('82', '侧边栏', '73', '01010105', '', '', '1', '2017-01-02 20:27:14', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('822', '左侧普通', '82', '0101010501', '', '/components/layouts/aside/left-static', '1', '2018-09-18 17:20:50', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('823', '左侧浮动', '82', '0101010502', '', '/components/layouts/aside/left-fixed', '1', '2018-09-18 17:21:30', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('824', '右侧普通', '82', '0101010503', '', '/components/layouts/aside/right-static', '1', '2018-09-18 17:22:11', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('825', '右侧浮动', '82', '0101010504', '', '/components/layouts/aside/right-fixed', '1', '2018-09-18 17:22:45', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('83', '基本', '72', '010102', 'fa-cube', '', '1', '2017-01-05 12:41:09', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('84', '面板', '83', '01010201', '', '', '1', '2017-01-05 12:41:09', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('85', '面板结构', '84', '0101020101', '', '/components/basic/panel/structure', '1', '2017-01-05 12:41:09', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('86', '面板操作', '84', '0101020102', '', '/components/basic/panel/actions', '1', '2017-01-05 12:41:09', '10010101', '2018-10-19 02:47:03');
INSERT INTO `sys_menu` VALUES ('87', '面板组件', '84', '0101020103', '', '/components/basic/panel/portlets', '1', '2017-01-05 12:41:09', '10010101', '2018-10-19 02:47:03');

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '消息类型 1：系统消息',
  `send_time` datetime(0) NOT NULL COMMENT '发送时间',
  `read_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已读',
  `read_time` datetime(0) NULL DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE,
  INDEX `Index_UserId`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作Id，主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `oper_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作标志',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作对应的请求地址',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属菜单ID',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除 1：已删除，0：未删除',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_o_1`(`oper_type`) USING BTREE,
  INDEX `Index_MenuId`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES ('100001', '保存', '保存显示设置', NULL, NULL, '10', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('100003', '恢复默认', '恢复默认', NULL, NULL, '10', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('110001', '新增', '新增日志设置', NULL, NULL, '11', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('110003', '编辑', '编辑日志设置', NULL, NULL, '11', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('110005', '删除', '删除日志设置', NULL, NULL, '11', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299801', '新增权限', '新增权限', NULL, NULL, '2998', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299803', '编辑', '编辑权限', NULL, NULL, '2998', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299805', '删除', '删除权限', NULL, NULL, '2998', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299807', '保存权限', '保存权限', NULL, NULL, '2998', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299901', '添加分组', '新增分组', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299903', '编辑分组', '编辑分组', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299905', '删除分组', '删除分组', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299907', '添加角色', '添加角色', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299909', '编辑角色', '编辑角色', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299911', '删除角色', '删除角色', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299913', '添加成员', '添加成员', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('299915', '移除成员', '移除成员', NULL, NULL, '2999', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('400001', '添加新菜单', '新增菜单', NULL, NULL, '4', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('400003', '编辑', '编辑菜单', NULL, NULL, '4', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('400005', '删除', '删除菜单', NULL, NULL, '4', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('400007', '全部保存', '保存菜单', NULL, NULL, '4', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('400009', '添加菜单', '新增子菜单', NULL, NULL, '4', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('400011', '新增菜单', '新增子菜单', NULL, NULL, '4', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500001', '增加部门', '新增机构', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500003', '修改名称', '修改名称', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500005', '删除', '删除机构', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500007', '保存', '保存机构', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500009', '新增成员', '机构新增成员', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500011', '禁用成员', '禁用成员', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500013', '修改部门', '修改部门', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500015', '添加子部门', '添加子部门', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500017', '移除成员', '移除成员', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500019', '编辑成员', '编辑成员', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('500021', '删除成员', '删除成员', NULL, NULL, '5', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('700001', '新增', '新增黑名单', NULL, NULL, '7', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_operation` VALUES ('700003', '删除', '删除黑名单', NULL, NULL, '7', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_org_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_user`;
CREATE TABLE `sys_org_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `org_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构ID',
  PRIMARY KEY (`user_id`, `org_id`) USING BTREE,
  UNIQUE INDEX `UK_PK`(`user_id`, `org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org_user
-- ----------------------------
INSERT INTO `sys_org_user` VALUES ('10010101', '100101');
INSERT INTO `sys_org_user` VALUES ('10010102', '100101');
INSERT INTO `sys_org_user` VALUES ('10010103', '100101');

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父类ID',
  `org_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织机构编码',
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织机构名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0：未删除，1：已删除',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人ID',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人ID',
  `modify_date` datetime(0) NOT NULL COMMENT '修改时间',
  UNIQUE INDEX `PK_SysOrganization_Id`(`id`) USING BTREE,
  INDEX `Index_parentId`(`parent_id`) USING BTREE,
  INDEX `Index_OrgCode`(`org_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1001', '0', '1001', '上海国望科技有限公司', '根节点', b'0', '1', '2018-08-24 14:50:19', '10010101', '2018-10-18 07:53:41');
INSERT INTO `sys_organization` VALUES ('100101', '1001', '100101', '总经办', '总经办', b'0', '1', '2018-08-24 14:51:05', '10010101', '2018-10-18 07:53:54');
INSERT INTO `sys_organization` VALUES ('100102100102100102', '1001', '100102100102100102', '董事会办公室', '董事会办公室', b'0', '1', '2018-10-12 15:49:25', '10010101', '2018-10-18 07:53:59');
INSERT INTO `sys_organization` VALUES ('15398492530957894288', '1001', '15398492530957894288', '人力资源中心', '', b'0', '10010101', '2018-10-18 07:54:13', '10010101', '2018-10-18 07:54:13');
INSERT INTO `sys_organization` VALUES ('15398492599754232159', '1001', '15398492599754232159', '营销中心', '', b'0', '10010101', '2018-10-18 07:54:19', '10010101', '2018-10-18 07:54:19');
INSERT INTO `sys_organization` VALUES ('15398492701313902229', '15398492599754232159', '15398492701313902229', '华东区', '', b'1', '10010101', '2018-10-18 07:54:30', '10010101', '2018-10-18 07:55:21');
INSERT INTO `sys_organization` VALUES ('15398492747234004979', '15398492599754232159', '15398492747234004979', '华北区', '', b'1', '10010101', '2018-10-18 07:54:34', '10010101', '2018-10-18 07:55:23');
INSERT INTO `sys_organization` VALUES ('15398492815446687772', '15398492599754232159', '15398492815446687772', '东北区', '', b'1', '10010101', '2018-10-18 07:54:41', '10010101', '2018-10-18 07:55:27');
INSERT INTO `sys_organization` VALUES ('15398492895988745421', '15398492599754232159', '15398492895988745421', '西北区', '', b'1', '10010101', '2018-10-18 07:54:49', '10010101', '2018-10-18 07:55:31');
INSERT INTO `sys_organization` VALUES ('15398492970626246335', '15398492599754232159', '15398492970626246335', '市场部', '', b'0', '10010101', '2018-10-18 07:54:57', '10010101', '2018-10-18 07:55:40');
INSERT INTO `sys_organization` VALUES ('15398493019377159584', '15398492599754232159', '15398493019377159584', '公关部', '', b'0', '10010101', '2018-10-18 07:55:01', '10010101', '2018-10-18 07:55:47');
INSERT INTO `sys_organization` VALUES ('15398493109510838342', '15398492599754232159', '15398493109510838342', '技术支持部', '', b'0', '10010101', '2018-10-18 07:55:10', '10010101', '2018-10-18 07:55:59');
INSERT INTO `sys_organization` VALUES ('15398493979369319074', '1001', '15398493979369319074', '财务中心', '', b'0', '10010101', '2018-10-18 07:56:37', '10010101', '2018-10-18 07:56:46');
INSERT INTO `sys_organization` VALUES ('15398494161426057697', '1001', '15398494161426057697', '研发中心', '', b'0', '10010101', '2018-10-18 07:56:56', '10010101', '2018-10-18 07:56:56');
INSERT INTO `sys_organization` VALUES ('15398494214084855706', '1001', '15398494214084855706', '运营中心', '', b'0', '10010101', '2018-10-18 07:57:01', '10010101', '2018-10-18 07:57:01');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限Id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除 1：已删除，0：未删除',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1000000001', '超级系统管理', '超级系统管理权限', b'0', '1001', '2018-09-26 10:59:22', '1001', '2018-09-26 10:59:24');
INSERT INTO `sys_permission` VALUES ('1000000002', '一般人员', '一般人员权限', b'0', '1001', '2018-09-28 07:38:27', '1001', '2018-09-28 07:39:21');

-- ----------------------------
-- Table structure for sys_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_menu`;
CREATE TABLE `sys_permission_menu`  (
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`permission_id`, `menu_id`) USING BTREE,
  UNIQUE INDEX `PK_SYS_PERMISSION_MENU`(`permission_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_menu
-- ----------------------------
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '1');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '10');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11112');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11113');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11114');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11115');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11116');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11117');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11118');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11119');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11120');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11122');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11123');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11124');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11125');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11126');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11127');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11128');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11130');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11131');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11132');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11133');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11134');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11135');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11136');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11137');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11138');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11139');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11143');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11144');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11145');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11146');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11147');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11148');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11149');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11150');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11151');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11152');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11153');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11154');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11155');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11156');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11157');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11158');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11159');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11160');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11161');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11162');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11163');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11164');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11165');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11166');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11167');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11168');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11169');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11170');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11171');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11172');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11173');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11174');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11175');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11176');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11177');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11178');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11179');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11180');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11183');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11187');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11188');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11189');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11191');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11192');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11193');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11194');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11195');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11196');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11197');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11198');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11199');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11200');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11201');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11202');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11203');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11204');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11205');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11206');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11230');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11231');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11232');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11233');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11234');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11235');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11236');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11239');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11240');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11241');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11242');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11243');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11244');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11245');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11246');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11247');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11249');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11250');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11251');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11252');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11254');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11255');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11256');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11257');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11258');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11259');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11260');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11261');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11262');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11263');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11265');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11266');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11267');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11268');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11269');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11270');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11271');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11272');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11274');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11275');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11276');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11277');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11278');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11279');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11280');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11281');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11282');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11283');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11284');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11285');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11286');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11287');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11288');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11289');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11290');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11291');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11292');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11294');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11295');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11296');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11297');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11298');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '11299');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '12');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '2');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21268');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21269');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21270');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21271');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21272');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21273');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '21299');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '22');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '2998');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '2999');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '3');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '4');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '5');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '6');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '7');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '72');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '73');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '74');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '75');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '76');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '77');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '78');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '79');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '8');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '80');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '81');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '82');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '822');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '823');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '824');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '825');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '83');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '84');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '85');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '86');
INSERT INTO `sys_permission_menu` VALUES ('1000000001', '87');

-- ----------------------------
-- Table structure for sys_permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_operation`;
CREATE TABLE `sys_permission_operation`  (
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  `operation_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作ID',
  PRIMARY KEY (`permission_id`, `operation_id`) USING BTREE,
  INDEX `po_fk_1`(`operation_id`) USING BTREE,
  INDEX `po_fk_2`(`permission_id`) USING BTREE,
  CONSTRAINT `po_fk_1` FOREIGN KEY (`operation_id`) REFERENCES `sys_operation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `po_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限操作关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_operation
-- ----------------------------
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '100001');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '100003');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '110001');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '110003');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '110005');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299801');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299803');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299805');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299807');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299901');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299903');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299905');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299907');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299909');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299911');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299913');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '299915');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '400001');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '400003');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '400005');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '400007');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '400009');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '400011');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500001');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500003');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500005');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500007');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500009');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500011');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500013');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500015');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500017');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500019');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '500021');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '700001');
INSERT INTO `sys_permission_operation` VALUES ('1000000001', '700003');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `role_group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色组ID',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `role_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '角色类型 1：功能角色，2：数据角色',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除 1：已删除，0:未删除',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE,
  INDEX `Index_RoleGroupId`(`role_group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('100101', '1001', '系统管理员', 1, '系统管理员', b'0', '1', '2018-06-28 11:09:24', '10010101', '2017-04-08 11:12:41');
INSERT INTO `sys_role` VALUES ('100102', '1001', '一般人员', 1, '一般操作人员', b'0', '10010101', '2018-10-18 10:12:49', '10010101', '2018-10-18 10:12:51');
INSERT INTO `sys_role` VALUES ('15398497336676438337', '3003', '华东地区', 1, NULL, b'0', '10010101', '2018-10-18 08:02:13', '10010101', '2018-10-18 08:02:13');
INSERT INTO `sys_role` VALUES ('15398497511764198001', '3003', '华南地区', 1, NULL, b'0', '10010101', '2018-10-18 08:02:31', '10010101', '2018-10-18 08:02:31');
INSERT INTO `sys_role` VALUES ('15398497622659907692', '3003', '华中地区', 1, NULL, b'0', '10010101', '2018-10-18 08:02:42', '10010101', '2018-10-18 08:02:42');
INSERT INTO `sys_role` VALUES ('15398497773087418575', '3003', '华北地区', 1, NULL, b'0', '10010101', '2018-10-18 08:02:57', '10010101', '2018-10-18 08:02:57');
INSERT INTO `sys_role` VALUES ('15398497903953483628', '3003', '西北地区', 1, NULL, b'0', '10010101', '2018-10-18 08:03:10', '10010101', '2018-10-18 08:03:10');
INSERT INTO `sys_role` VALUES ('15398498977528993347', '3003', '西南地区', 1, NULL, b'0', '10010101', '2018-10-18 08:04:57', '10010101', '2018-10-18 08:04:57');
INSERT INTO `sys_role` VALUES ('15398499153181352514', '3003', '东北地区', 1, NULL, b'0', '10010101', '2018-10-18 08:05:15', '10010101', '2018-10-18 08:05:15');
INSERT INTO `sys_role` VALUES ('15398499246580261398', '3003', '港澳台地区', 1, NULL, b'0', '10010101', '2018-10-18 08:05:24', '10010101', '2018-10-18 08:05:24');
INSERT INTO `sys_role` VALUES ('15398500166582765360', '2002', '普通员工', 1, NULL, b'0', '10010101', '2018-10-18 08:06:56', '10010101', '2018-10-18 08:06:56');
INSERT INTO `sys_role` VALUES ('15398500367661764244', '2002', '中层管理', 1, NULL, b'0', '10010101', '2018-10-18 08:07:16', '10010101', '2018-10-18 08:07:16');
INSERT INTO `sys_role` VALUES ('15398500445928806626', '2002', '高层管理', 1, NULL, b'0', '10010101', '2018-10-18 08:07:24', '10010101', '2018-10-18 08:07:24');
INSERT INTO `sys_role` VALUES ('15398501294489779640', '15398501098413248448', '经理', 1, NULL, b'1', '10010101', '2018-10-18 08:08:49', '10010101', '2018-10-18 08:09:04');
INSERT INTO `sys_role` VALUES ('15398501688720378766', '15398501098413248448', '员工', 1, NULL, b'0', '10010101', '2018-10-18 08:09:28', '10010101', '2018-10-18 08:09:28');
INSERT INTO `sys_role` VALUES ('15398501777779429419', '15398501098413248448', '经理', 1, NULL, b'0', '10010101', '2018-10-18 08:09:37', '10010101', '2018-10-18 08:09:37');
INSERT INTO `sys_role` VALUES ('15398501981290013155', '15398501098413248448', '主任', 1, NULL, b'0', '10010101', '2018-10-18 08:09:58', '10010101', '2018-10-18 08:09:58');
INSERT INTO `sys_role` VALUES ('15398502081900163928', '15398501098413248448', '部长', 1, NULL, b'0', '10010101', '2018-10-18 08:10:08', '10010101', '2018-10-18 08:10:08');
INSERT INTO `sys_role` VALUES ('15398502158933039793', '15398501098413248448', '总监', 1, NULL, b'0', '10010101', '2018-10-18 08:10:15', '10010101', '2018-10-18 08:10:15');

-- ----------------------------
-- Table structure for sys_role_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_data`;
CREATE TABLE `sys_role_data`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `data_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据ID',
  `data_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据类型；ORG:机构ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_group`;
CREATE TABLE `sys_role_group`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `group_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL,
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_group
-- ----------------------------
INSERT INTO `sys_role_group` VALUES ('1001', '默认', b'0', '1001', '2018-08-17 10:58:11', '1001', '2018-08-17 10:58:11');
INSERT INTO `sys_role_group` VALUES ('2002', '岗位', b'0', '1001', '2018-08-17 10:58:11', '1001', '2018-08-17 10:58:11');
INSERT INTO `sys_role_group` VALUES ('3003', '区域', b'0', '1001', '2018-08-17 10:58:11', '1001', '2018-10-13 02:07:58');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色Id',
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rp_fk_2`(`permission_id`) USING BTREE,
  INDEX `rp_fk_1`(`role_id`) USING BTREE,
  CONSTRAINT `rp_fk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rp_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '100101', '1000000001');
INSERT INTO `sys_role_permission` VALUES ('2', '100102', '1000000002');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `login_count` bigint(12) NOT NULL DEFAULT 0 COMMENT '登录次数',
  `status` tinyint(30) NOT NULL DEFAULT 0 COMMENT '状态 0：正常，1：停用',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录IP',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '是否删除 0：未删除，1：已删除',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `job_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `position` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `mobile` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别 1：男，2：女',
  `telephone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机号',
  `phone_ext` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分机号',
  `executive_mode` bit(1) NULL DEFAULT b'0' COMMENT '高管模式 1：是，0：否',
  `user_type` tinyint(3) NULL DEFAULT 2 COMMENT '身份类型 1：部门主管，2：普通成员',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PK_ID`(`id`) USING BTREE,
  INDEX `unq_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10010101', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 849, 0, '2019-03-22 14:54:16', '0:0:0:0:0:0:0:1', b'0', '10010101', '2018-05-09 10:06:13', '10010101', '2018-10-19 06:29:56', '系统管理员', '10010101', '100402', 'service@admui.com', '15921759040', 1, '4001502080', '325', b'0', 2, '系统管理员');
INSERT INTO `sys_user` VALUES ('10010102', 'xiaxuan@admui_demo', 'e10adc3949ba59abbe56e057f20f883e', 0, 0, NULL, NULL, b'0', '10010101', '2018-10-18 10:16:51', '10010101', '2018-10-18 10:16:58', '夏瑄', '10010102', '100402', 'xiaxuan@admui.com', '15921759040', 2, '4001502080', '123', b'0', 2, '一般用户');
INSERT INTO `sys_user` VALUES ('10010103', 'zhangzhiyuan@admui_demo', 'e10adc3949ba59abbe56e057f20f883e', 0, 0, NULL, NULL, b'0', '10010101', '2018-10-18 10:17:35', '10010101', '2018-10-18 10:17:42', '张致远', '10010103', '100402', 'zhangzhiyuan@admui.com', '15921759040', 1, '4001502080', '345', b'0', 2, '一般用户');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户Id,联合主键',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色Id，联合主键',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `ur_fk_2`(`role_id`) USING BTREE,
  INDEX `ur_fk_1`(`user_id`) USING BTREE,
  CONSTRAINT `ur_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ur_fk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('10010101', '100101');
INSERT INTO `sys_user_role` VALUES ('10010102', '100102');
INSERT INTO `sys_user_role` VALUES ('10010103', '100102');

-- ----------------------------
-- View structure for view_user_org_auth
-- ----------------------------
DROP VIEW IF EXISTS `view_user_org_auth`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_user_org_auth` AS select `sys_role_data`.`id` AS `id`,`sys_user_role`.`user_id` AS `user_id`,`sys_role_data`.`data_id` AS `org_id` from ((`sys_user_role` join `sys_role` on(((`sys_role`.`id` = `sys_user_role`.`role_id`) and (`sys_role`.`is_delete` = 0)))) join `sys_role_data` on(((`sys_role_data`.`role_id` = `sys_user_role`.`role_id`) and (`sys_role_data`.`data_type` = 'ORG'))));

-- ----------------------------
-- Function structure for getChildIdList
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildIdList`;
delimiter ;;
CREATE FUNCTION `getChildIdList`(rootId varchar(32))
 RETURNS varchar(16000) CHARSET utf8
BEGIN
DECLARE str varchar(16000);
DECLARE cid varchar(16000);
SET str=rootId;
SET cid=rootId;
WHILE cid is not null DO
		SET str = concat(str,',',cid);
		SELECT group_concat(id) INTO cid FROM sys_organization where FIND_IN_SET(sys_organization.parent_id,cid)>0;
END WHILE;
RETURN str;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for getParentIdList
-- ----------------------------
DROP FUNCTION IF EXISTS `getParentIdList`;
delimiter ;;
CREATE FUNCTION `getParentIdList`(rootId varchar(32))
 RETURNS varchar(16000) CHARSET utf8
BEGIN
DECLARE fid varchar(32) default '';
DECLARE str varchar(16000) default rootId;
WHILE rootId is not null do
	SET fid =(SELECT sys_organization.parent_id FROM sys_organization WHERE sys_organization.id=rootId);
	IF fid is not null THEN
		SET str = concat(str, ',', fid);
		SET rootId = fid;
	ELSE
		SET rootId = fid;
	END IF;
END WHILE;
return str;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;