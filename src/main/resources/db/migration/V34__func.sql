INSERT INTO `ns_funcs` (`fun_id`, `btn_name`, `btn_class`, `btn_id`, `status`, `modal_name`, `remark`, `create_date`)
VALUES
	(2048*2, '日历模式', 'btn-info', 'btn_toggle_type', 1, NULL, NULL, '2021-03-20 09:24:59');

update ns_resources set func_ids = func_ids + 2048*2 where id = 508;