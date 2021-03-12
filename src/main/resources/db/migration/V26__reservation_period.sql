
alter table d_group_reservation_period add column goods_id int not null after group_id;
alter table d_group_reservation_period add index idex_goods_id(goods_id);