alter table d_employee add column employee_level int default 1 not null after status;
alter table d_employee add column gender int default 0 not null after name;
alter table d_employee add column identity_no varchar(20) after phone;
alter table d_employee add column join_date date after status;

INSERT INTO `s_seq` (`prefix`, `max_cnt_per_day`, `val`, `date`, `desc`)
VALUES
	('EM', 3, 1, '210303', '员工');

INSERT INTO `ns_funcs` (`fun_id`, `btn_name`, `btn_class`, `btn_id`, `status`, `modal_name`, `remark`, `create_date`)
VALUES
	(4096*2, '绑定股东', 'btn-info', 'btn_bind_shareholder', 1, NULL, NULL, now()),
	(4096*2*2, '迁移股东', 'btn-danger', 'btn_transfer_shareholder', 1, NULL, NULL, now()),
	(4096*2*2*2, '关系概览', 'btn-warning', 'btn_preview', 1, NULL, NULL, now());

update ns_resources set func_ids = func_ids + 4096 * (2+4+8) where id = 402;