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
