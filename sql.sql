alter table d_customer add column create_date datetime not null;
alter table d_customer add column creator varchar(20) not null;
alter table d_customer add column create_ip varchar(20) not null;
alter table d_customer add column update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
alter table d_customer add column updater varchar(20) ;
alter table d_customer add column update_ip varchar(20) ;



truncate d_order;
truncate d_order_item;
truncate d_order_pay;
truncate d_order_pay_item;
truncate `d_order_consumption`;
truncate `d_order_item_consumption`;
truncate `d_wallet_detail`;
truncate `d_bonus_wallet_detail`;
update d_wallet set sum_money=0, used_money=0,available_money=0;
update d_bonus_wallet set sum_bonus=0,pending_bonus=0,used_bonus=0,available_bonus=0

############################add index###########################
alter table d_activity_claim_record add index idx_claim_customer_group_id(claim_customer_group_id);
alter table d_activity_claim_record add index idx_claim_open_id(claim_open_id);
alter table d_activity_claim_record add index idx_activity_assign_id(activity_assign_id);
alter table d_customer add index idx_open_id(open_id);
alter table d_customer add index idx_phone(phone);
alter table d_customer add index idx_name(name);
alter table d_customer_group add index idx_customer_id(customer_id);
alter table d_customer_group add index idx_group_id(group_id);
alter table d_customer_group add index idx_bonus_plan_id(bonus_plan_id);
alter table d_customer_group add index idx_bonus_wallet_id(bonus_wallet_id);
alter table d_customer_group add index idx_wallet_id(wallet_id);
alter table d_customer_group add index idx_serial_number(serial_number);
alter table d_customer_group add index idx_type(type);
alter table d_customer_group add index idx_parent_id(parent_id);
alter table d_assign_activity add index idx_create_date(create_date);
alter table d_assign_activity add index idx_goods_name(goods_name);
alter table d_assign_activity add index idx_group_id(group_id);
alter table d_assign_activity_item add index idx_goods_id(goods_id);
alter table d_assign_activity_item add index idx_customer_group_id(customer_group_id);
alter table d_bonus_plan add index idx_group_id(group_id);
alter table d_bonus_wallet add index idx_pending_bonus(pending_bonus);
alter table d_bonus_wallet_detail add index idx_bonus_wallet_id(bonus_wallet_id);
alter table d_bonus_wallet_detail add index idx_order_pay_item_id(order_pay_item_id);
alter table d_bonus_wallet_detail add index idx_change_type(change_type);
alter table d_bonus_wallet_detail add index idx_create_date(create_date);
alter table d_order add index idx_status(status);
alter table d_order add index idx_goods_type_id(goods_type_id);
alter table d_order add index idx_customer_group_id(customer_group_id);
alter table d_order add index idx_goods_id(goods_id);
alter table d_order add index idx_batch_id(batch_id);
alter table d_order_item add index idx_order_id(order_id);
alter table d_order_pay add index idx_order_id(order_id);
alter table d_order_pay add index idx_pay_type(pay_type);
alter table d_order_pay_item add index idx_order_pay_id(order_pay_id);
alter table d_order_item_consumption add index idx_order_consumption_id(order_consumption_id);
alter table d_order_item_consumption add index idx_order_item_id(order_item_id);
alter table d_order_consumption add index idx_order_id(order_id);
alter table d_goods add index idx_group_id(group_id);
alter table d_goods add index idx_type(type);
alter table d_goods add index idx_name(name);
alter table d_goods add index idx_status(status);
alter table d_goods add index idx_serial_number(serial_number);
alter table d_goods_item add index idx_goods_tag_id(goods_tag_id);
alter table d_goods_item add index idx_goods_id(goods_id);
alter table d_goods_tag add index idx_group_id(group_id);
alter table d_goods_tag add index idx_name(name);
alter table d_wallet_detail add index idx_wallet_id(wallet_id);
alter table d_wallet_detail add index idx_change_type(change_type);
alter table d_wallet_detail add index idx_create_date(create_date);








