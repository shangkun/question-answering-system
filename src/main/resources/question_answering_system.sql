/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50549
Source Host           : 127.0.0.1:3306
Source Database       : question_answering_system

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2018-07-27 18:26:03
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
  `knowledge_id` varchar(20) DEFAULT NULL COMMENT '知识id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='答案表';

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('540343114095656960', 'fghjidslhfdsoj', '100', '540343110668910592');

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
INSERT INTO `classification` VALUES ('540256092177825792', '银行', '银行', '2018-07-27 13:28:57', '538559372305891328', '0', '1');
INSERT INTO `classification` VALUES ('540272351078514688', '银行卡', '银行卡', '2018-07-27 13:29:13', '538559372305891328', '540256092177825792', '1');
INSERT INTO `classification` VALUES ('540294769230741504', '12321', 'dsa', '2018-07-27 13:53:25', '538559372305891328', '0', '1');
INSERT INTO `classification` VALUES ('540294890043473920', 'dsadsa', 'dsadasdsa', '2018-07-27 13:53:54', '538559372305891328', '540294769230741504', '1');

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
INSERT INTO `configuration` VALUES ('你好，请向我发问吧！', '0.8', '0.6', '0000000030', '0000000005', '0.7', '不好意思，我不知道这个问题~', '你还可能想了解以下问题', '你可能想问的是以下问题', '0000000010');

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
INSERT INTO `extension_question` VALUES ('540343110668910592', 'fdsfds', '540343110668910592');

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
INSERT INTO `knowledge` VALUES ('540343110668910592', 'fdsfjhudsifhds', '540256092177825792', '2018-07-27 18:20:50', '538559372305891328', null, '1');

-- ----------------------------
-- Table structure for lexicon
-- ----------------------------
DROP TABLE IF EXISTS `lexicon`;
CREATE TABLE `lexicon` (
  `id` varchar(20) NOT NULL,
  `topic` varchar(500) DEFAULT NULL COMMENT '词名称',
  `topic_set` varchar(4000) DEFAULT NULL COMMENT '词集合',
  `type` int(11) DEFAULT NULL COMMENT '词类型',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier_id` varchar(50) DEFAULT NULL COMMENT '修改人',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词库';

-- ----------------------------
-- Records of lexicon
-- ----------------------------
INSERT INTO `lexicon` VALUES ('539653590122233856', 'dsadsa', 'dsadsasa', '1', '2018-07-25 19:25:36', '538559372305891328', '1');
INSERT INTO `lexicon` VALUES ('539663670913794048', 'gdfgfd', 'gfdg', '1', '2018-07-25 20:05:40', '538559372305891328', '1');
INSERT INTO `lexicon` VALUES ('539668739960864768', 'dsaddsadsa', 'dsadsadsadsadsa', '1', '2018-07-25 20:25:48', '538559372305891328', '1');
INSERT INTO `lexicon` VALUES ('539672998114754560', '232', '232', '1', '2018-07-25 20:42:44', '538559372305891328', '1');

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
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='敏感词';

-- ----------------------------
-- Records of sensitive_words
-- ----------------------------
INSERT INTO `sensitive_words` VALUES ('539629571989831680', '暴恐', '福音会;中国教徒;统一教;观音法门;清海无上师;盘古;李洪志;志洪李;李宏志;轮功;法轮;轮法功;三去车仑;氵去车仑;发论工;法x功;法o功;法0功;法一轮一功;轮子功;车仑工力;法lun;fa轮;法lg;flg;fl功;falungong;大法弟子;大纪元;dajiyuan;明慧网;明慧周报;正见网;新唐人;伪火;退党;tuidang;退dang;超越红墙;自fen;真善忍;九评;9评;9ping;九ping;jiuping;藏字石;集体自杀;自sha;zi杀;suicide;titor;逢8必灾;逢八必灾;逢9必乱;逢九必乱;朱瑟里诺;根达亚文明;诺查丹玛斯;人类灭亡进程表;按照马雅历法;推背图;推bei图;济世灵文;诸世纪;电狗;电话定位器;电话拦截器;电话窃听;电话监;电话交友;电话追杀系统;电击枪;电鸡;电警棒;枪出售;枪的制;枪货到;枪决女犯;枪模;枪手;枪销售;枪械制;枪子弹;售步枪;售纯度;售单管;售弹簧刀;售防身;售狗子;售虎头;售火药;售假币;售健卫;售军用;售猎枪;售氯胺;售麻醉;售枪支;售热武;售三棱;售手枪;售五四;售一元硬;售子弹;售左轮;亚砷（酸）酐;亚砷酸钾;亚砷酸钠;亚硒酸;亚硒酸二钠;亚硒酸镁;亚硒酸钠;亚硒酸氢钠;亚硝酸乙酯;亚硝酰乙氧;氧化二丁基锡;氧化汞;氧化铊;氧化亚铊;氧氯化磷;原装弹;原子弹方法;原子弹清单;安眠酮;代血浆;普萘洛尔;呋塞米;西布曲明;testosterone;胰岛素样生长因子;促红细胞生成素;地西泮;尼可刹米;甲睾酮;adrenaline;erythropoietin;地奈德;莫达非尼;氯噻嗪;苯巴比妥;促性腺激素;泼尼松;麻黄草;雄烯二醇;地塞米松;tamoxifen;strychnine;androst;新型毒品;杜冷丁;兴奋剂;mdma;海洛因;海luo因;heroin;diamorphine;diacetylmorphine;鸦片;阿芙蓉;咖啡因;cocain;三唑仑;美沙酮;麻古;k粉;凯他敏;ketamine;冰毒;苯丙胺;cannabis;大麻;爱他死;氯胺酮;benzodiazepines;甲基安非他明;安非他命;吗啡', '2018-07-25 17:50:10', '538559372305891328', '1');
INSERT INTO `sensitive_words` VALUES ('539629818388414464', '反动', '腐败中国;三个呆婊;你办事我放心;社会主义灭亡;打倒中国;打倒共产党;打倒共产主义;打倒胡锦涛;打倒江泽民;打倒江主席;打倒李鹏;打倒罗干;打倒温家宝;打倒中共;打倒朱镕;抵制共产党;抵制共产主义;抵制胡锦涛;抵制江泽民;抵制江主席;抵制李鹏;抵制罗干;抵制温家宝;抵制中共;抵制朱镕基;灭亡中国;亡党亡国;粉碎四人帮;激流中国;特供;特贡;特共;zf大楼;殃视;贪污腐败;强制拆除;形式主义;政治风波;太子党;上海帮;北京帮;清华帮;红色贵族;权贵集团;河蟹社会;喝血社会;九风;9风;十七大;十7大;17da;九学;9学;四风;4风;双规;南街村;最淫官员;警匪;官匪;独夫民贼;官商勾结;城管暴力执法;强制捐款;毒豺;一党执政;一党专制;一党专政;专制政权;宪法法院;胡平;苏晓康;贺卫方;谭作人;焦国标;万润南;张志新;辛灝年;高勤荣;王炳章;高智晟;司马璐;刘晓竹;刘宾雁;魏京生;寻找林昭的灵魂;别梦成灰;谁是新中国;讨伐中宣部;异议人士;民运人士;启蒙派;选国家主席;民一主;min主;民竹;民珠;民猪;chinesedemocracy;大赦国际;国际特赦;da选;投公;公头;宪政;平反;党章;维权;昝爱宗;宪章;08宪;08xz;抿主;敏主;人拳;人木又;人quan;renquan;中国人权;中国新民党;群体事件;群体性事件;上中央;去中央;讨说法;请愿;请命;公开信;联名上书;万人大签名;万人骚动;截访;上访;shangfang;信访;访民;集合;集会;组织集体;静坐;静zuo;jing坐;示威;示wei;游行;you行;油行;游xing;youxing;官逼民反;反party;反共;抗议;亢议;抵制;低制;底制;di制;抵zhi;dizhi;boycott;血书;焚烧中国国旗;baoluan;流血冲突;出现暴动;发生暴动;引起暴动;baodong;灭共;杀毙;罢工;霸工;罢考;罢餐;霸餐;罢参;罢饭;罢吃;罢食;罢课;罢ke;霸课;ba课;罢教;罢学;罢运;网特;网评员;网络评论员;五毛党;五毛们;5毛党;戒严;jieyan;jie严;戒yan;8的平方事件;知道64;八九年;贰拾年;2o年;20和谐年;贰拾周年;六四;六河蟹四;六百度四;六和谐四;陆四;陆肆;198964;5月35;89年春夏之交;64惨案;64时期;64运动;4事件;四事件;北京风波;学潮;学chao;xuechao;学百度潮;门安天;天按门;坦克压大学生;民主女神;历史的伤口;高自联;北高联;血洗京城;四二六社论;王丹;柴玲;沈彤;封从德;王超华;王维林;吾尔开希;吾尔开西;侯德健;阎明复;方励之;蒋捷连;丁子霖;辛灏年;蒋彦永;严家其;陈一咨;中华局域网;党的喉舌;互联网审查;当局严密封锁;新闻封锁;封锁消息;爱国者同盟;关闭所有论坛;网络封锁;金盾工程;gfw;无界浏览;无界网络;自由门;何清涟;中国的陷阱;汪兆钧;记者无疆界;境外媒体;维基百科;纽约时报;bbc中文网;华盛顿邮报;世界日报;东森新闻网;东森电视;星岛日报;wikipedia;youtube;googleblogger;美国广播公司;英国金融时报;自由亚洲;自由时报;中国时报;反分裂;威胁论;左翼联盟;钓鱼岛;保钓组织;主权;弓单;火乍;木仓;石肖;核蛋;步qiang;bao炸;爆zha;baozha;zha药;zha弹;炸dan;炸yao;zhadan;zhayao;hmtd;三硝基甲苯;六氟化铀;炸药配方;弹药配方;炸弹配方;皮箱炸弹;火药配方;人体炸弹;人肉炸弹;解放军;兵力部署;军转;军事社;8341部队;第21集团军;七大军区;7大军区;北京军区;沈阳军区;济南军区;成都军区;广州军区;南京军区;兰州军区;颜色革命;规模冲突;塔利班;基地组织;恐怖分子;恐怖份子;三股势力;印尼屠华;印尼事件;蒋公纪念歌;马英九;mayingjiu;李天羽;苏贞昌;林文漪;陈水扁;陈s扁;陈随便;阿扁;a扁;告全国同胞书;台百度湾;台完;台wan;taiwan;台弯;湾台;台湾共和国;台军;台毒;台du;taidu;twdl;一中一台;打台湾;两岸战争;攻占台湾;支持台湾;进攻台湾;占领台湾;统一台湾;收复台湾;登陆台湾;解放台湾;解放tw;解决台湾;光复民国;台湾问题;台海问题;台海危机;台海统一;台海大战;台海战争;台海局势;入联;入耳关;中华联邦;国民党;x民党;民进党;青天白日;闹独立;duli;fenlie;日本万岁;小泽一郎;劣等民族;汉人;汉维;维汉;维吾;吾尔;热比娅;伊力哈木;疆独;东突厥斯坦解放组织;东突解放组织;蒙古分裂分子;列确;阿旺晋美;藏人;臧人;zang人;藏民;藏m;达赖;赖达;dalai;哒赖;dl喇嘛;丹增嘉措;打砸抢;西独;葬独;臧独;藏毒;藏du;zangdu;支持zd;藏暴乱;雪山狮子旗;拉萨;啦萨;啦沙;啦撒;拉sa;lasa;la萨;西藏;藏西;藏春阁;藏獨;藏独;藏独立;藏妇会;藏青会;藏字石;xizang;xi藏;x藏;西z;tibet;希葬;希藏;硒藏;稀藏;西脏;西奘;西葬;西臧;援藏;bjork;王千源;安拉;回教;回族;回回;回民;穆斯林;穆罕穆德;穆罕默德;默罕默德;伊斯兰;圣战组织;清真;清zhen;qingzhen;真主;阿拉伯;高丽棒子;韩国狗;满洲第三帝国;满狗;鞑子;江丑闻;江嫡系;江毒;江独裁;江蛤蟆;江核心;江黑心;江胡内斗;江祸心;江家帮;江绵恒;江派和胡派;江派人马;江泉集团;江人马;江三条腿;江氏集团;江氏家族;江氏政治局;江氏政治委员;江梳头;江太上;江戏子;江系人;江系人马;江宰民;江贼;江贼民;江主席;麻果丸;麻将透;麻醉弹;麻醉狗;麻醉枪;麻醉槍;麻醉药;麻醉藥;台独;台湾版假币;台湾独立;台湾国;台湾应该独立;台湾有权独立;天灭中共;中共帮凶;中共保命;中共裁;中共党文化;中共的血旗;中共的罪恶;中共帝国;中共独裁;中共封锁;中共封网;中共腐败;中共黑;中共黑帮;中共解体;中共近期权力斗争;中共恐惧;中共权力斗争;中共任用;中共退党;中共洗脑;中共邪教;中共邪毒素;中共政治游戏', '2018-07-25 17:51:09', '538559372305891328', '1');
INSERT INTO `sensitive_words` VALUES ('539630036693549056', '民生', '打人;拆迁;纠纷;盗窃;代药物毒品类：血浆;morphine;摇头丸;迷药;乖乖粉;narcotic;麻醉药;精神药品;专业代理;帮忙点一下;帮忙点下;请点击进入;详情请进入;私人侦探;私家侦探;针孔摄象;调查婚外情;信用卡提现;无抵押贷款;广告代理;原音铃声;借腹生子;找个妈妈;找个爸爸;代孕妈妈;代生孩子;腾讯客服电话;销售热线;免费订购热线;低价出售;款到发货;回复可见;连锁加盟;加盟连锁;免费二级域名;免费使用;免费索取;蚁力神;婴儿汤;售肾;刻章办;买小车;套牌车;玛雅网;电脑传讯;视频来源;下载速度;高清在线;全集在线;在线播放;txt下载;六位qq;6位qq;位的qq;个qb;送qb;用刀横向切腹;完全自杀手册;四海帮;足球投注;地下钱庄;中国复兴党;阿波罗网;曾道人;六合彩;改卷内幕;替考试;隐形耳机;出售答案;答an;da案;资金周转;救市;股市圈钱;崩盘;资金短缺;证监会;质押贷款;小额贷款;周小川;刘明康;尚福林;孔丹;汉芯造假;杨树宽;中印边界谈判结果;喂奶门;摸nai门;酒瓶门;脱裤门;75事件;乌鲁木齐;新疆骚乱;针刺;打针;食堂涨价;饭菜涨价;h1n1;瘟疫爆发;yangjia;y佳;yang佳;杨佳;杨j;袭警;杀警;武侯祠;川b26931;贺立旗;周正毅;px项目;骂四川;家l福;家le福;加了服;麦当劳被砸;豆腐渣;这不是天灾;龙小霞;震其国土;yuce;提前预测;地震预测;隐瞒地震;李四光预测;蟾蜍迁徙;地震来得更猛烈;八级地震毫无预报;踩踏事故;聂树斌;万里大造林;陈相贵;张丹红;尹方明;李树菲;王奉友;零八奥运艰;惨奥;奥晕;凹晕;懊运;懊孕;奥孕;奥你妈的运;反奥;628事件;weng安;wengan;翁安;瓮安事件;化工厂爆炸;讨回工资;代办发票;代办各;代办文;代办学;代办制;代辦;代表烦;代开发票;代開;代考;代理发票;代理票据;代您考;代讨债;代写毕;代写论文;代孕;代追债;考机构;考考邓;考联盟;考前答案;考前付;考前密卷;考前预测;考试,答案;考试,作弊器;考试包过;考试保;考试答案;考试机构;考试联盟;考试枪;考试作弊;考试作弊器;考研考中;考中答案;透视功能;透视镜;透视扑;透视器;透视眼睛;透视眼镜;透视药;透视仪;打死经过;打死人;打砸办公;打砸抢;安眠酮;代血浆;普萘洛尔;呋塞米;西布曲明;testosterone;胰岛素样生长因子;促红细胞生成素;地西泮;尼可刹米;甲睾酮;adrenaline;erythropoietin;地奈德;莫达非尼;氯噻嗪;苯巴比妥;促性腺激素;泼尼松;麻黄草;雄烯二醇;地塞米松;tamoxifen;strychnine;androst;新型毒品;杜冷丁;兴奋剂;mdma;海洛因;海luo因;heroin;diamorphine;diacetylmorphine;鸦片;阿芙蓉;咖啡因;cocain;美沙酮;麻古;k粉;凯他敏;ketamine;冰毒;苯丙胺;cannabis;大麻;爱他死;氯胺酮;benzodiazepines;甲基安非他明;安非他命;吗啡;kc短信;kc嘉年华;短信广告;短信群发;短信群发器;小6灵通;短信商务广告;段录定;无界浏览;无界浏览器;无界;无网界;无网界浏览;无帮国;kc提示;kc网站;up8新势力;白皮书;up新势力;移民;易达网络卡;安魂网;罢工;罢课;纽崔莱七折;手机复制;手机铃声;网关;神通加持法;全1球通;如6意通;清仓;灵动卡;答案卫星接收机;高薪养廉;考后付款;佳静安定片;航空母舰;航空售票;号码百事通;考前发放;成本价;诚信通手机商城;高利贷;联4通;黑庄;黑手党;黑车;联通贵宾卡;联总;联总这声传单;联总之声传单;高息贷款;高干子弟;恭喜你的号码;恭喜您的号码;高干子女;各个银行全称;各种发票;高官;高官互调;高官子女;喝一送一;卡号;复制;监听王;传单;旦科;钓鱼岛;钓鱼台;当官靠后台;党校安插亲信;传九促三;客户端非法字符;刻章;大麻树脂;大麻油;大法;大法弟子;dpp大法;fa lun;falu;发抡;发抡功;洗脑;下法轮;发轮;发伦;发伦功;发仑;发沦;发纶;发论;发论功;发论公;发正念;发囵;发愣;发瞟;罚抡;罚伦;罚仑;罚沦;罚纶;罚囵;筏抡;筏轮;筏伦;筏仑;筏沦;筏纶;筏论;筏囵;伐抡;伐轮;伐伦;伐仑;伐沦;伐论;伐囵;乏抡;乏轮;乏伦;乏仑;乏沦;乏纶;乏论;乏囵;阀抡;阀伦;阀仑;阀沦;阀纶;阀论;阀囵;法 轮 功;法*功;法.轮.功;法l功;法lun功;法功;法会;法抡;法抡功;法轮;法轮大法;法轮佛法;法轮功;法伦;法仑;法沦;法纶;法论;法十轮十功;法西斯;法院;法正;法谪;法谪功;法輪;法囵;法愣;珐.輪功;珐抡;珐轮;珐伦;珐仑;珐沦;五不;五不争鸣论坛;五出三进;五套功法;邝锦文;垡抡;垡轮;垡伦;垡仑;垡沦;垡纶;垡论;垡囵;茳澤民;荭志;闳志;闵维方;氵去;氵去车仑工力;转法轮;砝抡;砝轮;砝伦;砝仑;砝沦;砝纶;真、善、忍;真理教;真善美;真善忍;砝论;砝囵;泓志;屙民;珐纶;珐论;珐囵;falun;falundafa;fa轮;flg;弟子;地下教会;炼功;梦网洪志;轮大;抡功;轮功;伦功;摩门教;三水法轮;三唑仑;天皇;天怒;天葬;车臣;达赖;功法;讲法;基督;基督教;护法;回教;教徒;功友;大师;达赖喇嘛;虹志;鸿志;洪传;贯通两极法;光祖;洪吟;洪哲胜;洪志;宏法;观世音;宏志;弘志;古兰经;红志;车库;车仑;经文', '2018-07-25 17:52:01', '538559372305891328', '1');
INSERT INTO `sensitive_words` VALUES ('539630249256681472', '其他', '穴海;协警;纳米比亚;专业调查;有华龙;jq的来;电信路;第一夫人;党鞭;黄巨;荡尽天下;家元自称玉皇大帝;主席李世民;何祚庥;刘刚;不要沉默;后勤集团;食堂涨价;发国难财;浪漫邂逅;红满堂;张小洋;炸学校;子宫;叫晶晶的女孩;回派;社会黑暗;国之母;国母;国姆;东方微点;震惊全球;nowto;chengdu;徐明;六月飞雪;暴力虐待;暴力袭击;天府广场;粮荒;洗脑班;李愚蠢;中国猪;台湾猪;进化不完全的生命体;震死他们;10010;10086;10159;13423205670;13725516608;13875448369;15112886328;189;6-4tianwang;64;68170802;6a6.net;7.31;7.310;89-64cdjp;8945212;23条;259o;381929279;3p;4-jun;av;bj;cbd;cctv;cdma;dick;flg;focusc;gcd;gamemaster;ip17908;key_text;nmd;qqb;sm;soccer01.com;tmd;ultrasurf;bignews;bitch;boxun;chinaliberal;chinamz;chinesenewsnet;cnd;creaders;dafa;dajiyuan;df\'d;dfdz;die;dpp;freechina;freedom;freenet;fuck;fuck your mother;hongzhi;hrichina;huanet;hypermart.net;incest;jiangdongriji;l2590803027;lihongzhi;ma;making;minghui;minghuinews;nacb;na?ve;nmis;paper64;peacehall;piao;playboy;renminbao;renmingbao;rfa;safeweb;sex;shit;simple;svdc;taip;tibetalk;triangle;triangleboy;txwq.net;unixbox;ustibet;voa;voachinese;wangce;wstaiji;www;xinsheng;yuming;yy通讯录;zhengjian;zhengjianwang;zhenshanren;zhuanfalu;zhuanfalun;八九;八老', '2018-07-25 17:52:52', '538559372305891328', '1');
INSERT INTO `sensitive_words` VALUES ('539630390915104768', '色情', '成人dv;成人电;成人卡通;成人聊;成人片;成人视;成人图;成人文;成人小;贱人;装b;大sb;傻逼;傻b;煞逼;煞笔;刹笔;傻比;沙比;欠干;婊子养的;我日你;我操;我草;卧艹;卧槽;爆你菊;艹你;cao你;你他妈;真他妈;别他吗;草你吗;草你丫;操你妈;擦你妈;操你娘;操他妈;日你妈;干你妈;干你娘;娘西皮;狗操;狗草;狗杂种;狗日的;操你祖宗;操你全家;操你大爷;妈逼;你麻痹;麻痹的;妈了个逼;马勒;狗娘养;贱比;贱b;下贱;死全家;全家死光;全家不得好死;全家死绝;白痴;无耻;sb;杀b;你吗b;你妈的;婊子;贱货;人渣;混蛋;媚外;和弦;兼职;限量;铃声;男公关;火辣;精子;射精;诱奸;强奸;做爱;性爱;发生关系;按摩;快感;处男;猛男;少妇;屌;屁股;下体;内裤;浑圆;咪咪;发情;刺激;白嫩;粉嫩;兽性;风骚;呻吟;sm;阉割;高潮;裸露;不穿;一丝不挂;脱光;干你;干死;我干;裙中性运动;乱奸;乱伦类;乱伦小;伦理大;伦理电影;伦理毛;伦理片;裸聊;裸聊网;裸体写真;裸舞视;裸照;美女裸体;美女写真;美女上门;美艳少妇;妹按摩;妹上门;迷幻药;迷幻藥;迷昏口;迷昏药;迷昏藥;迷魂香;迷魂药;迷魂藥;迷奸粉;迷奸药;迷情粉;迷情水;迷情药;迷藥;谜奸药;骚妇;骚货;骚浪;骚嘴;色电影;色妹妹;色情图片;色情小说;色情影片;色情表演;色情电影;色情服务;色情片;色视频;色小说;性伴侣;性服务;性福情;性感少;性伙伴;性交视频;性交图片;性奴集中营;阴蒂;阴间来电;阴茎增大;阴茎助勃;阴毛;陰唇;陰道;陰戶;淫荡美女;淫荡视频;淫荡照片;淫乱;淫靡;淫魔舞;淫情女;淫肉;淫騷妹;淫兽;淫兽学;淫穴;morphine;摇头丸;迷药;乖乖粉;narcotic;麻醉药;精神药品;爱女人;爱液;按摩棒;拔出来;爆草;包二奶;暴干;暴奸;暴乳;爆乳;暴淫;屄;被操;被插;被干;逼奸;仓井空;插暴;操逼;操黑;操烂;肏你;肏死;操死;操我;厕奴;插比;插b;插逼;插进;插你;插我;插阴;潮吹;潮喷;成人电影;成人论坛;成人色情;成人网站;成人文学;成人小说;艳情小说;成人游戏;吃精;赤裸;抽插;扌由插;抽一插;春药;大波;大力抽送;大乳;荡妇;荡女;盗撮;多人轮;发浪;放尿;肥逼;粉穴;封面女郎;风月大陆;干死你;干穴;肛交;肛门;龟头;裹本;国产av;好嫩;豪乳;黑逼;后庭;后穴;虎骑;花花公子;换妻俱乐部;黄片;几吧;鸡吧;鸡巴;鸡奸;寂寞男;寂寞女;妓女;激情;集体淫;奸情;叫床;脚交;金鳞岂是池中物;金麟岂是池中物;精液;就去日;巨屌;菊花洞;菊门;巨奶;巨乳;菊穴;开苞;口爆;口活;口交;口射;口淫;裤袜;狂操;狂插;浪逼;浪妇;浪叫;浪女;狼友;聊性;流淫;铃木麻;凌辱;漏乳;露b;乱交;乱伦;轮暴;轮操;轮奸;裸陪;买春;美逼;美少妇;美乳;美腿;美穴;美幼;秘唇;迷奸;密穴;蜜穴;蜜液;摸奶;摸胸;母奸;奈美;奶子;男奴;内射;嫩逼;嫩女;嫩穴;捏弄;女优;炮友;砲友;喷精;屁眼;品香堂;前凸后翘;强jian;强暴;强奸处女;情趣用品;情色;拳交;全裸;群交;惹火身材;人妻;人兽;日逼;日烂;肉棒;肉逼;肉唇;肉洞;肉缝;肉棍;肉茎;肉具;揉乳;肉穴;肉欲;乳爆;乳房;乳沟;乳交;乳头;三级片;骚逼;骚比;骚女;骚水;骚穴;色逼;色界;色猫;色盟;色情网站;色区;色色;色诱;色欲;色b;少年阿宾;少修正;射爽;射颜;食精;释欲;兽奸;兽交;手淫;兽欲;熟妇;熟母;熟女;爽片;爽死我了;双臀;死逼;丝袜;丝诱;松岛枫;酥痒;汤加丽;套弄;体奸;体位;舔脚;舔阴;调教;偷欢;偷拍;推油;脱内裤;文做;我就色;无码;舞女;无修正;吸精;夏川纯;相奸;小逼;校鸡;小穴;小xue;写真;性感妖娆;性感诱惑;性虎;性饥渴;性技巧;性交;性奴;性虐;性息;性欲;胸推;穴口;学生妹;穴图;亚情;颜射;阳具;杨思敏;要射了;夜勤病栋;一本道;一夜欢;一夜情;一ye情;阴部;淫虫;阴唇;淫荡;阴道;淫电影;阴阜;淫妇;淫河;阴核;阴户;淫贱;淫叫;淫教师;阴茎;阴精;淫浪;淫媚;淫糜;淫魔;淫母;淫女;淫虐;淫妻;淫情;淫色;淫声浪语;淫兽学园;淫书;淫术炼金士;淫水;淫娃;淫威;淫亵;淫样;淫液;淫照;阴b;应召;幼交;幼男;幼女;欲火;欲女;玉女心经;玉蒲团;玉乳;欲仙欲死;玉穴;援交;原味内衣;援助交际;张筱雨;招鸡;招妓;中年美妇;抓胸;自拍;自慰;作爱;18禁;99bb;a4u;a4y;adult;amateur;anal;a片;fuck;gay片;g点;g片;hardcore;h动画;h动漫;incest;porn;secom;sexinsex;sm女王;xiao77;xing伴侣;tokyohot;yin荡', '2018-07-25 17:53:25', '538559372305891328', '1');
INSERT INTO `sensitive_words` VALUES ('539630593634205696', '贪腐', '腐败;gcd;共贪党;gongchandang;阿共;共一产一党;产党共;公产党;工产党;共c党;共x党;共铲;供产;共惨;供铲党;供铲谠;供铲裆;共残党;共残主义;共产主义的幽灵;拱铲;老共;中珙;中gong;gc党;贡挡;gong党;g产;狗产蛋;共残裆;恶党;邪党;共产专制;共产王朝;裆中央;土共;土g;共狗;g匪;共匪;仇共;共产党腐败;共产党的报应;共产党的末日;共产党专制;communistparty;症腐;政腐;政付;正府;政俯;政f;zhengfu;政zhi;挡中央;档中央;中国zf;中央zf;国wu院;中华帝国;gong和;大陆官方;北京政权;张曙;刘志军;买别墅;玩女人;贪20亿;许宗衡;贪财物;李启红;贪腐财富;落马;高官名单;陈希同;贪污;玩忽职守;陈良宇;受贿罪;滥用职权;有期徒刑;没收个人财产;成克杰;死刑;程维高;严重违纪;开除党籍;撤销职务;刘方仁;无期徒刑;倪献策;徇私舞弊;梁湘;以权谋私;撤职。;李嘉廷;死刑缓期;张国光;韩桂芝;宋平顺;自杀;黄瑶;双规;陈绍基;判处死刑;剥夺政治权利终身;没收个人全部财产;石兆彬;侯伍杰;王昭耀;剥夺政治权利;杜世成;沈图;叛逃美国;罗云光;起诉;张辛泰;李效时;边少斌;徐鹏航;违纪;收受股票;王乐毅;李纪周;郑光迪;田凤山。;邱晓华;郑筱萸;孙鹤龄;蓝田造假案;于幼军;留党察看;何洪达;朱志刚;杨汇泉;官僚主义;徐炳松;托乎提沙比尔;王宝森;经济犯罪;畏罪自杀。;陈水文;孟庆平;胡长清;朱川;许运鸿;丘广钟;刘知炳;丛福奎;王怀忠;巨额财产;来源不明罪;李达昌;刘长贵;王钟麓;阿曼哈吉;付晓光;自动辞;刘克田;吕德彬;刘维明;双开;刘志华;孙瑜;李堂堂;韩福才 青海;欧阳德 广东;韦泽芳 海南;铁英 北京;辛业江 海南;于飞 广东;姜殿武 河北;秦昌典 重庆;范广举 黑龙江;张凯广东;王厚宏海南;陈维席安徽;王有杰河南;王武龙江苏;米凤君吉林;宋勇辽宁;张家盟浙江;马烈孙宁夏;黄纪诚北京;常征贵州;王式惠重庆;周文吉;王庆录广西;潘广田山东;朱作勇甘肃;孙善武河南;宋晨光江西;梁春禄广西政协;鲁家善 中国交通;金德琴 中信;李大强 神华;吴文英 纺织;查克明 华能;朱小华光大;高严 国家电力;王雪冰;林孔兴;刘金宝;张恩照;陈同海;康日新;王益;张春江;洪清源;平义杰;李恩潮;孙小虹;陈忠;慕绥新;田凤岐;麦崇楷;柴王群;吴振汉;张秋阳;徐衍东;徐发 黑龙江;张宗海;丁鑫发;徐国健;李宝金;单平;段义和;荆福生;陈少勇;黄松有;皮黔生;王华元;王守业;刘连昆;孙晋美;邵松高;肖怀枢;刘广智 空军;姬胜德 总参;廖伯年 北京', '2018-07-25 17:54:14', '538559372305891328', '1');

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
INSERT INTO `user` VALUES ('538559372305891328', 'admin', 'admin', 'admin.1234', '538945468713926656', '2018-07-27 18:20:16', '1157317608@qq.com', '15810503656', '0:0:0:0:0:0:0:1', '2018-07-27 18:20:16', '0000000026', '1');
INSERT INTO `user` VALUES ('539229683674251264', 'shangkun', '尚坤', '12345678', '539269705655910400', '2018-07-24 19:38:50', '1157317608@qq.com', '15810503656', '0:0:0:0:0:0:0:1', '2018-07-24 18:09:53', '0000000001', '1');
