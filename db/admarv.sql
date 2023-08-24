DROP TABLE IF EXISTS `campaigns_insights`;
CREATE TABLE `campaigns_insights` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `account_currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `action_values` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `actions` int DEFAULT NULL,
  `activity_recency` int DEFAULT NULL,
  `ad_click_actions` int DEFAULT NULL,
  `ad_format_asset` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `ad_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `ad_impression_actions` int DEFAULT NULL,
  `ad_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `adset_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `adset_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `age_targeting` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `attribution_setting` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `auction_bid` double DEFAULT NULL,
  `auction_competitiveness` double DEFAULT NULL,
  `auction_max_competitor_bid` double DEFAULT NULL,
  `body_asset` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `buying_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `campaign_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `campaign_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `canvas_avg_view_percent` double DEFAULT NULL,
  `canvas_avg_view_time` double DEFAULT NULL,
  `catalog_segment_actions` int DEFAULT NULL,
  `catalog_segment_value` double DEFAULT NULL,
  `catalog_segment_value_mobile_purchase_roas` double DEFAULT NULL,
  `catalog_segment_value_omni_purchase_roas` double DEFAULT NULL,
  `catalog_segment_value_website_purchase_roas` double DEFAULT NULL,
  `clicks` int DEFAULT NULL,
  `coarse_conversion_value` double DEFAULT NULL,
  `comparison_node` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `conversion_values` double DEFAULT NULL,
  `conversions` int DEFAULT NULL,
  `converted_product_quantity` int DEFAULT NULL,
  `converted_product_value` double DEFAULT NULL,
  `cost_per_15_sec_video_view` double DEFAULT NULL,
  `cost_per_2_sec_continuous_video_view` double DEFAULT NULL,
  `cost_per_action_type` double DEFAULT NULL,
  `cost_per_ad_click` double DEFAULT NULL,
  `cost_per_conversion` double DEFAULT NULL,
  `cost_per_dda_countby_convs` double DEFAULT NULL,
  `cost_per_inline_link_click` double DEFAULT NULL,
  `cost_per_inline_post_engagement` double DEFAULT NULL,
  `cost_per_one_thousand_ad_impression` double DEFAULT NULL,
  `cost_per_outbound_click` double DEFAULT NULL,
  `cost_per_thruplay` double DEFAULT NULL,
  `cost_per_unique_action_type` double DEFAULT NULL,
  `cost_per_unique_click` double DEFAULT NULL,
  `cost_per_unique_conversion` double DEFAULT NULL,
  `cost_per_unique_inline_link_click` double DEFAULT NULL,
  `cost_per_unique_outbound_click` double DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `cpc` double DEFAULT NULL,
  `cpm` double DEFAULT NULL,
  `cpp` double DEFAULT NULL,
  `created_time` bigint DEFAULT NULL,
  `ctr` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `lead_gen`;
CREATE TABLE `lead_gen` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `lead_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘ID',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '采购姓名ID',
  `cntct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '采购邮箱ID',
  `crte_tm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘创建时间',
  `crte_dt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘创建日期',
  `lead_stat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘状态',
  `regn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家编码',
  `flwp_stat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘跟踪来源',
  `rsrc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据来源',
  `form_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表单ID',
  `page_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公共主页ID',
  `owner_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人ID',
  `owner_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人姓名',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘所属账户人ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘所属账户人名称',
  `lead_auality` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘质量',
  `campaign_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告系列ID',
  `ad_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告ID',
  `adset_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告组ID',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='询盘单表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录账号',
  `real_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `email` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电话',
  `org_code` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录会话的机构编码',
  `status` tinyint(1) DEFAULT NULL COMMENT '性别(1-正常,2-冻结)',
  `third_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方登录的唯一标识',
  `third_type` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '第三方类型',
  `telephone` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '座机号',
  `fb_token` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'facebook access token',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_user_name` (`user_name`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_phone` (`phone`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_email` (`email`) USING BTREE,
  KEY `idx_user_name` (`user_name`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键id',
  `role_name` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色编码',
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` int(10) DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_role_role_code` (`role_code`) USING BTREE,
  KEY `idx_sr_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键id',
  `user_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色id',
  `tenant_id` int(10) DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sur_user_id` (`user_id`) USING BTREE,
  KEY `idx_sur_role_id` (`role_id`) USING BTREE,
  KEY `idx_sur_user_role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

DROP TABLE IF EXISTS `user_auths`;
CREATE TABLE `user_auths` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户id',
  `pltfrm` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户平台授权信息表';

-- ----------------------------
-- Table structure for sysuser_fb_bind
-- ----------------------------
DROP TABLE IF EXISTS `sysuser_fb_bind`;
CREATE TABLE `sysuser_fb_bind` (
  `user_id`         varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户id',
  `user_name`       varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色id',
  `page_id`         varchar(32) DEFAULT NULL COMMENT '公共主页id',
  `ad_account_id`   varchar(32) DEFAULT NULL COMMENT '广告账户id',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='sass用户FB公共主页和广告账户绑定表';

-- ----------------------------
-- Table structure for ads_info
-- ----------------------------
DROP TABLE IF EXISTS `ads_info`;
CREATE TABLE `ads_info` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `ads_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告id',
    `ads_name` VARCHAR(255) comment '广告名称',
    `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='广告信息'; 

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '询盘ID',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '采购客户姓名',
  `cntct` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '采购客户邮箱',
  `owner_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户归档收录人',
  `owner_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户归档收录人姓名',
  `position` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '职位',
  `fixed_phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '固定电话',
  `whatsapp` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'WhatsApp',
  `facebook` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Facebook',
  `customer_quality` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户质量',
  `country` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家',
  `gender` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phone_number` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `skype` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Skype',
  `twitter` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Twitter',
  `customer_status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户状态',
  `customer_source` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户来源',
  `other_remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其它备注',
  `assigned_to` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '归属人',
  `last_contact_time` datetime DEFAULT NULL COMMENT '最后联系时间',
  `company_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司名称',
  `industry` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属行业',
  `linkedin` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LinkedIn',
  `company_email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司邮箱',
  `website` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '网站',
  `address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `company_size` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业规模',
  `product_range` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属产品',
  `business_scope` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '经营范围',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='归档客户表';

-- ----------------------------
-- Table structure for whatsapp_msg
-- ----------------------------
DROP TABLE IF EXISTS `whatsapp_msg`;
CREATE TABLE `whatsapp_msg` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `from` varchar(32) DEFAULT NULL COMMENT '消息来源号',
    `to` VARCHAR(255) DEFAULT NULL COMMENT '发送目的号',
    `msg` TEXT(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户',
    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='聊天记录消息表'

-- ----------------------------
-- Table structure for whatsapp_contact
-- ----------------------------
DROP TABLE IF EXISTS `whatsapp_contact`;
CREATE TABLE `whatsapp_contact` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `whats_app` VARCHAR(255) DEFAULT NULL COMMENT 'WhatsApp号',
    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='whatsapp联系人表'

-- ----------------------------
-- Table structure for campaign_info
-- ----------------------------
DROP TABLE IF EXISTS `campaign_info`;
CREATE TABLE `campaign_info` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
	`ad_account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户ID',
    `campaign_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告系列ID',
    `daily_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '每日预算金额',
    `name` VARCHAR(255) comment '广告系列名称',
    `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告信息状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='广告系列信息';

-- ----------------------------
-- Table structure for adset_info
-- ----------------------------
DROP TABLE IF EXISTS `adset_info`;
CREATE TABLE `adset_info` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `ad_account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户ID',
	`adset_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告组ID',
    `daily_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '每日预算金额',
    `name` VARCHAR(255) comment '广告系列名称',
    `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告信息状态',
    `campaign_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告系列ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='广告组信息';

-- ----------------------------
-- Table structure for ad_info
-- ----------------------------
DROP TABLE IF EXISTS `ad_info`;
CREATE TABLE `ad_info` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `ad_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告ID',
    `daily_budget` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '每日预算金额',
    `name` VARCHAR(255) comment '广告系列名称',
    `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告状态',
    `campaign_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告系列ID',
    `adset_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告组ID',
    `ad_account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户ID',
    `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='广告信息';

-- ----------------------------
-- Table structure for adaccount_page_info
-- ----------------------------
DROP TABLE IF EXISTS `adaccount_page_info`;
CREATE TABLE `adaccount_page_info` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
	`ad_account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户ID',
    `ad_account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户名称',
    `page_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公共主页ID',
    `page_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公共主页名称',
	`user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
	`del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='账户和公共主页信息';

-- ----------------------------
-- Table structure for adaccount_detail
-- ----------------------------
DROP TABLE IF EXISTS `adaccount_detail`;
CREATE TABLE `adaccount_detail` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
	`account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户ID',
    `amount_spent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户花费',
    `balance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账余额',
    `currency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '币种',
	`account_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户状态',
	`spend_cap` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '广告账户金额上限',
	`user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
	`del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4  COMMENT='AD账户详情';

-- ----------------------------
-- Table structure for email_info
-- ----------------------------
DROP TABLE IF EXISTS `email_info`;
CREATE TABLE `email_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `auth_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '授权码',
  `platform` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件平台',
  `email_index` int DEFAULT 0 COMMENT '邮件索引位置',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='邮箱信息';

-- ----------------------------
-- Table structure for gpt_msg
-- ----------------------------
DROP TABLE IF EXISTS `gpt_msg`;
CREATE TABLE `gpt_msg` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `conversation_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '回话ID',
  `msg` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '聊天消息',
  `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除状态(0-正常,1-已删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'system' COMMENT '更新人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='gpt聊天消息';

-- ----------------------------
-- Table structure for `fb_posts`
-- ----------------------------
DROP TABLE IF EXISTS `fb_posts`;
CREATE TABLE `fb_posts` (
    `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增ID',
    `post_id` INT COMMENT '贴文ID',
    `post_content` TEXT COMMENT '贴文内容',
    `post_type` VARCHAR(255) COMMENT '贴文类型',
    `page_name` VARCHAR(255) COMMENT '公共主页',
    `publish_type` VARCHAR(255) COMMENT '发布类型',
    `post_status` VARCHAR(255) COMMENT '贴文状态',
    `publish_time` DATETIME COMMENT '发布时间',
    `action_taken` VARCHAR(255) COMMENT '操作',
    `del_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'system' COMMENT '创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'system' COMMENT '更新人',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- ----------------------------
-- Table structure for `email_msg`
-- ----------------------------
DROP TABLE IF EXISTS `email_msg`;
CREATE TABLE `email_msg` (
    `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增ID',
    `email` VARCHAR(255) COMMENT '邮件',
    `subject` VARCHAR(255) COMMENT '主题',
    `from` VARCHAR(255) COMMENT '来源',
	`text` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件内容',
	`address_array` TEXT COMMENT '地址列表',
    `description` TEXT COMMENT '描述',
    `disposition` TEXT COMMENT '排列',
    `file_name` VARCHAR(255) COMMENT '文件名称',
    `received_date` DATETIME COMMENT '接收时间',
    `sent_date` DATETIME COMMENT '发送时间',
    `replay_to_array` TEXT COMMENT '回复人',
    `del_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常,1-已删除)',
    `create_by` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'system' COMMENT '创建人',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'system' COMMENT '更新人',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);