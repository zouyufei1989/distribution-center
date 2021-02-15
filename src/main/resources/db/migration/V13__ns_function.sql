INSERT INTO `ns_funcs` (`fun_id`, `btn_name`, `btn_class`, `btn_id`, `status`, `modal_name`, `remark`, `create_date`)
VALUES
	(1024*2, '预约时间段', 'btn-info', 'btn_reservation_period', 1, NULL, NULL, now());

update ns_resources set func_ids = func_ids + 1024*2 where id = 401;
