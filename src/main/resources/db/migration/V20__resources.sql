INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, '/packageConsumption/index', 0, 1, ' 项目消耗记录', 507, 500, 7, 1, NULL, 0);

INSERT INTO `ns_rights` ( `resource_id`, `name`, `enable`, `code`)
VALUES
	( 507, NULL, 1, NULL);
