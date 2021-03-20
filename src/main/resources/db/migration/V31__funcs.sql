update ns_funcs set btn_name='编辑时段' where fun_id = 2048;
update ns_resources set name=replace(name,' ','');
update ns_resources set func_ids = func_ids + 2048,name='预约时段管理' where id = 402;