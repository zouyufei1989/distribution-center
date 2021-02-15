CREATE TABLE `d_group_reservation_period` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `start_time` varchar(20) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(20) NOT NULL,
  `create_ip` varchar(20) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updater` varchar(20) DEFAULT NULL,
  `update_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_group_id` (`group_id`),
  KEY `idx_start` (`start_time`),
  KEY `idx_end` (`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
