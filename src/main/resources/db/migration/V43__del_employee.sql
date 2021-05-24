CREATE TABLE `d_employee_bk` (
                              `id` int(11) unsigned NOT NULL ,
                              `name` varchar(20) COLLATE utf8mb4_bin DEFAULT '',
                              `gender` int(11) NOT NULL DEFAULT '0',
                              `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT '',
                              `identify_no` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
                              `open_id` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
                              `session_key` varchar(40) COLLATE utf8mb4_bin DEFAULT '',
                              `serial_number` varchar(40) COLLATE utf8mb4_bin DEFAULT '',
                              `nick_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
                              `head_cover` varchar(200) COLLATE utf8mb4_bin DEFAULT '',
                              `group_id` int(11) NOT NULL,
                              `status` int(11) NOT NULL,
                              `join_date` date DEFAULT NULL,
                              `employee_level` int(11) NOT NULL DEFAULT '1',
                              `create_date` datetime NOT NULL,
                              `creator` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
                              `create_ip` varchar(20) COLLATE utf8mb4_bin NOT NULL,
                              `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `updater` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
                              `update_ip` varchar(20) COLLATE utf8mb4_bin DEFAULT ''
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

update ns_resources set func_ids = func_ids - 16 + 65536 where id = 402;
