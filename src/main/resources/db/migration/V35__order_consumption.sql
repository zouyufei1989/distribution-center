alter table d_order_consumption add column reservation_id int after goods_id;
ALTER TABLE d_order_consumption ADD index idx_reservation_id(reservation_id);