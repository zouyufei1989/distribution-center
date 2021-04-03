CREATE TABLE `d_employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT '',
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT '',
  `open_id` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `session_key` varchar(40) COLLATE utf8mb4_bin DEFAULT '',
  `serial_number` varchar(40) COLLATE utf8mb4_bin DEFAULT '',
  `nick_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `head_cover` varchar(200) COLLATE utf8mb4_bin DEFAULT '',
  `group_id` INT(11) NOT NULL,
  `status` INT(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updater` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_ip` varchar(20) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_open_id` (`open_id`),
  UNIQUE KEY `idx_phone` (`phone`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;


CREATE TABLE `d_employee_customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `customer_group_id` int(11) DEFAULT NULL,
  `status` INT(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updater` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_ip` varchar(20) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_employee_customer` (`employee_id`,`customer_group_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;