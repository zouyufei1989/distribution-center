INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, '/groupReservationPeriod/index', 0, 1, '预约时间段', 402, 400, 2 , 1, NULL, 0);

INSERT INTO `ns_rights` ( `resource_id`, `name`, `enable`, `code`)
VALUES
	( 402, NULL, 1, NULL);

alter table d_group add column reserve_flag int not null default 0 after status ;

