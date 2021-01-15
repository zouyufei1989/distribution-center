DROP TABLE IF EXISTS `d_bonus_wallet`;
CREATE TABLE `d_bonus_wallet` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sum_bonus` bigint(20) NOT NULL,
  `distribute_bonus` bigint(20) NOT NULL,
  `used_bonus` bigint(20) NOT NULL,
  `available_bonus` bigint(20) NOT NULL,
  `last_distribution_date` datetime DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(40) DEFAULT NULL,
  `create_ip` varchar(20) NOT NULL,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updater` varchar(40) DEFAULT NULL,
  `update_ip` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_available_bonus` (`available_bonus`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS `d_bonus_wallet_detail`;

CREATE TABLE `d_bonus_wallet_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bonus_wallet_id` int(11) NOT NULL,
  `bonus_change` bigint(20) NOT NULL,
  `change_type` int(11) NOT NULL,
  `bef_sum_bonus` bigint(20) NOT NULL,
  `bef_distribute_bonus` bigint(20) NOT NULL,
  `bef_available_bonus` bigint(20) NOT NULL,
  `bef_used_bonus` bigint(20) NOT NULL,
  `aft_sum_bonus` bigint(20) NOT NULL,
  `aft_distribute_bonus` bigint(20) NOT NULL,
  `aft_available_bonus` bigint(20) NOT NULL,
  `aft_used_bonus` bigint(20) NOT NULL,
  `order_pay_item_id` int(11) DEFAULT NULL,
  `bonus_rate` bigint(20) DEFAULT NULL,
  `src_customer_money_pay` bigint(20) DEFAULT NULL,
  `src_customer_money_available` bigint(20) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(40) DEFAULT NULL,
  `create_ip` varchar(20) NOT NULL,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updater` varchar(40) DEFAULT NULL,
  `update_ip` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_bonus_wallet_id` (`bonus_wallet_id`),
  KEY `idx_order_pay_item_id` (`order_pay_item_id`),
  KEY `idx_change_type` (`change_type`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;