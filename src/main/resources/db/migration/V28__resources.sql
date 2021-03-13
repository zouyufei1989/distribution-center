INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, '/reservation/index', 0, 1, ' 预约管理', 508, 500,8, 1, NULL, 0);

INSERT INTO `ns_rights` ( `resource_id`, `name`, `enable`, `code`)
VALUES
	( 508, NULL, 1, NULL);
