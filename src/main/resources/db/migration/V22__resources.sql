INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, '/statistics/customerStatistics', 0, 1, '顾客统计', 1102, 1100, 2, 1, NULL, 0);


INSERT INTO `ns_rights` ( `resource_id`, `name`, `enable`, `code`)
VALUES
	( 1102, NULL, 1, NULL);
