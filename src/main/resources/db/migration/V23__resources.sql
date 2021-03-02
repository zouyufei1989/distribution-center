INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, '/statistics/operationStatistics', 0, 1, '运营数据统计', 1103, 1100, 3, 1, NULL, 0);

INSERT INTO `ns_rights` ( `resource_id`, `name`, `enable`, `code`)
VALUES
	( 1103, NULL, 1, NULL);
