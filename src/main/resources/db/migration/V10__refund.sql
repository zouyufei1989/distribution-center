CREATE TABLE `d_order_refund` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `refund_amount` bigint(20) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(20) NOT NULL,
  `create_ip` varchar(20) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updater` varchar(20) DEFAULT NULL,
  `update_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
