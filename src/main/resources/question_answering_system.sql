/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50549
Source Host           : 127.0.0.1:3306
Source Database       : question_answering_system

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-07-24 18:50:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for access_token
-- ----------------------------
DROP TABLE IF EXISTS `access_token`;
CREATE TABLE `access_token` (
  `id` varchar(20) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '令牌名称',
  `token` varchar(50) DEFAULT NULL COMMENT '访问令牌',
  `modifier_id` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问令牌表';

-- ----------------------------
-- Records of access_token
-- ----------------------------

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` varchar(20) NOT NULL,
  `content` varchar(2000) DEFAULT NULL COMMENT '答案',
  `channel_id` int(10) DEFAULT NULL COMMENT '渠道id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='答案表';

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `description` varchar(500) DEFAULT NULL COMMENT '分类描述',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `p_id` varchar(20) NOT NULL COMMENT '父级id',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- ----------------------------
-- Records of classification
-- ----------------------------

-- ----------------------------
-- Table structure for configuration
-- ----------------------------
DROP TABLE IF EXISTS `configuration`;
CREATE TABLE `configuration` (
  `welcome` varchar(500) NOT NULL COMMENT '欢迎语',
  `threshold_upper_limit` double NOT NULL COMMENT '上限阈值',
  `threshold_lower_limit` double NOT NULL COMMENT '下限阈值',
  `timeout` int(10) unsigned zerofill NOT NULL COMMENT '超时时间',
  `recommend_question_number` int(10) unsigned zerofill NOT NULL COMMENT '推荐问题数量',
  `greeting_threshold_upper_limit` double NOT NULL COMMENT '寒暄上限阈值',
  `unknown` varchar(500) NOT NULL COMMENT '未知问题回复',
  `has_answer_and_recommend` varchar(500) NOT NULL COMMENT '有答案且有推荐问的回复',
  `has_recommend` varchar(500) NOT NULL COMMENT '仅有推荐问的回复',
  `hot_question_limit` int(10) unsigned zerofill NOT NULL COMMENT '热点问题限制个数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问答配置表';

-- ----------------------------
-- Records of configuration
-- ----------------------------

-- ----------------------------
-- Table structure for extension_question
-- ----------------------------
DROP TABLE IF EXISTS `extension_question`;
CREATE TABLE `extension_question` (
  `id` varchar(20) NOT NULL,
  `title` varchar(500) DEFAULT NULL COMMENT '扩展问题',
  `knowledge_id` varchar(20) DEFAULT NULL COMMENT '知识id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='扩展问题表';

-- ----------------------------
-- Records of extension_question
-- ----------------------------

-- ----------------------------
-- Table structure for greeting
-- ----------------------------
DROP TABLE IF EXISTS `greeting`;
CREATE TABLE `greeting` (
  `id` varchar(20) NOT NULL,
  `title` varchar(300) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='寒暄表';

-- ----------------------------
-- Records of greeting
-- ----------------------------

-- ----------------------------
-- Table structure for greeting_answer
-- ----------------------------
DROP TABLE IF EXISTS `greeting_answer`;
CREATE TABLE `greeting_answer` (
  `id` varchar(20) NOT NULL,
  `answer` varchar(2000) DEFAULT NULL COMMENT '寒暄回答',
  `channel_id` int(10) DEFAULT NULL COMMENT '渠道id',
  `greeting_id` varchar(20) DEFAULT NULL COMMENT '寒暄id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='寒暄答案表';

-- ----------------------------
-- Records of greeting_answer
-- ----------------------------

-- ----------------------------
-- Table structure for greeting_extension_question
-- ----------------------------
DROP TABLE IF EXISTS `greeting_extension_question`;
CREATE TABLE `greeting_extension_question` (
  `id` varchar(20) NOT NULL,
  `title` varchar(300) DEFAULT NULL COMMENT '寒暄扩展问',
  `greeting_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='寒暄扩展问表';

-- ----------------------------
-- Records of greeting_extension_question
-- ----------------------------

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `id` varchar(20) NOT NULL,
  `title` varchar(300) DEFAULT NULL COMMENT '知识标题',
  `classification_id` varchar(20) DEFAULT NULL COMMENT '分类id',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `teacher_id` varchar(50) DEFAULT NULL COMMENT '教师id',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识表';

-- ----------------------------
-- Records of knowledge
-- ----------------------------

-- ----------------------------
-- Table structure for lexicon
-- ----------------------------
DROP TABLE IF EXISTS `lexicon`;
CREATE TABLE `lexicon` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '词名称',
  `type` int(11) DEFAULT NULL COMMENT '词类型',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifyer_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词库';

-- ----------------------------
-- Records of lexicon
-- ----------------------------

-- ----------------------------
-- Table structure for log_qa
-- ----------------------------
DROP TABLE IF EXISTS `log_qa`;
CREATE TABLE `log_qa` (
  `id` varchar(20) NOT NULL,
  `question` varchar(500) DEFAULT NULL COMMENT '用户问题',
  `channel_id` int(10) DEFAULT NULL COMMENT '渠道id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `session_id` varchar(20) DEFAULT NULL COMMENT '回话id',
  `request_time` timestamp NULL DEFAULT NULL COMMENT '请求时间',
  `knowledge_id` varchar(20) DEFAULT NULL COMMENT '知识id',
  `classification_id` varchar(20) DEFAULT NULL COMMENT '分类id',
  `answer` varchar(3000) DEFAULT NULL COMMENT '答案',
  `response_type` int(10) DEFAULT NULL COMMENT '回复类型',
  `response_time` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `access_token` varchar(100) DEFAULT NULL COMMENT '访问令牌',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问答日志';

-- ----------------------------
-- Records of log_qa
-- ----------------------------

-- ----------------------------
-- Table structure for log_qa_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `log_qa_evaluate`;
CREATE TABLE `log_qa_evaluate` (
  `id` varchar(20) NOT NULL,
  `qa_id` varchar(20) DEFAULT NULL COMMENT '问答记录id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `evaluate` int(11) DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问答评价表';

-- ----------------------------
-- Records of log_qa_evaluate
-- ----------------------------

-- ----------------------------
-- Table structure for log_qa_recommend
-- ----------------------------
DROP TABLE IF EXISTS `log_qa_recommend`;
CREATE TABLE `log_qa_recommend` (
  `id` varchar(20) NOT NULL,
  `qa_id` varchar(20) DEFAULT NULL COMMENT '问答id',
  `session_id` varchar(20) DEFAULT NULL COMMENT '会话id',
  `knowledge_id` varchar(20) DEFAULT NULL COMMENT '知识id',
  `knowledge_title` varchar(300) DEFAULT NULL COMMENT '知识标题',
  `sequence` int(10) DEFAULT NULL COMMENT '推荐问排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问答推荐表';

-- ----------------------------
-- Records of log_qa_recommend
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '接口地址',
  `description` varchar(200) DEFAULT NULL COMMENT '菜单描述',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('0', '公用接口', 'common/', '公用开放权限接口', '1');
INSERT INTO `menu` VALUES ('1', '管理员管理', 'admin/', '包含管理员列表与角色管理', '1');
INSERT INTO `menu` VALUES ('2', '系统配置', 'config/', '包含问答配置与敏感词管理', '1');
INSERT INTO `menu` VALUES ('3', '知识管理', 'knowledge/', '包含词库、分类管理、知识管理、寒暄知识管理', '1');
INSERT INTO `menu` VALUES ('4', '统计分析', 'statistics/', null, '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(1) DEFAULT NULL COMMENT '角色状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('538945468713926656', '超级管理员', '超级管理员', '2018-07-23 20:34:24', '1');
INSERT INTO `role` VALUES ('539269705655910400', '知识加工测试员', '知识加工测试员', '2018-07-24 18:00:11', '1');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` varchar(20) NOT NULL,
  `role_id` varchar(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('538945468713926657', '538945468713926656', '0');
INSERT INTO `role_menu` VALUES ('538945468713926658', '538945468713926656', '1');
INSERT INTO `role_menu` VALUES ('538945468713926659', '538945468713926656', '2');
INSERT INTO `role_menu` VALUES ('538945468713926660', '538945468713926656', '3');
INSERT INTO `role_menu` VALUES ('538945468713926661', '538945468713926656', '4');
INSERT INTO `role_menu` VALUES ('539270350584676352', '539269705655910400', '2');
INSERT INTO `role_menu` VALUES ('539270350584676353', '539269705655910400', '0');
INSERT INTO `role_menu` VALUES ('539270350584676354', '539269705655910400', '3');

-- ----------------------------
-- Table structure for sensitive_words
-- ----------------------------
DROP TABLE IF EXISTS `sensitive_words`;
CREATE TABLE `sensitive_words` (
  `id` varchar(20) NOT NULL,
  `topic` varchar(500) DEFAULT NULL COMMENT '主题',
  `topic_set` varchar(4000) DEFAULT NULL COMMENT '主题集合',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='敏感词';

-- ----------------------------
-- Records of sensitive_words
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `account` varchar(100) NOT NULL COMMENT '账户',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role_id` varchar(20) DEFAULT NULL COMMENT '角色id',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后一次登陆ip',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后一次登陆时间',
  `login_count` int(10) unsigned zerofill NOT NULL COMMENT '登陆次数',
  `status` int(1) unsigned zerofill NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('538559372305891328', 'admin', 'admin', 'admin.1234', '538945468713926656', '2018-07-24 17:53:35', '1157317608@qq.com', '15810503656', '0:0:0:0:0:0:0:1', '2018-07-24 17:53:35', '0000000015', '1');
INSERT INTO `user` VALUES ('539229683674251264', 'shangkun', '尚坤', '12345678', '539269705655910400', '2018-07-24 18:09:53', '1157317608@qq.com', '15810503656', '0:0:0:0:0:0:0:1', '2018-07-24 18:09:53', '0000000001', '1');
