INSERT INTO `d_schedule_config` ( `full_class`, `year`, `month`, `day`, `weekend`, `hour`, `minute`, `comment`, `params`, `create_date`, `update_date`)
VALUES
	( 'com.money.custom.task.ReserveOvertimeTask', '*', '*', '*', NULL, '*', '*', '', 'minutesOver=10', now(), NULL);
