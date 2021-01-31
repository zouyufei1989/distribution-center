alter table d_bonus_plan drop column cashback_first;
alter table d_customer_group add column cashback_first int default 0 after customer_id;