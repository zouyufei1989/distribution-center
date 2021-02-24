INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, '/statistics/shareHolderStatistics', 0, 1, '股东统计', 1101, 1100, 1, 1, NULL, 0);


INSERT INTO `ns_rights` ( `resource_id`, `name`, `enable`, `code`)
VALUES
	( 1101, NULL, 1, NULL);
