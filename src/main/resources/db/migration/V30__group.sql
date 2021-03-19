alter table d_group add column reserve_days int after reserve_flag;
update d_group set reserve_days = 7;