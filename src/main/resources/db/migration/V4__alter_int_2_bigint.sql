alter table d_bonus_plan modify column bonus_rate bigint(20);
alter table d_bonus_plan modify column cashback_amount bigint(20);

alter table d_bonus_wallet modify column sum_bonus bigint(20);
alter table d_bonus_wallet modify column pending_bonus bigint(20);
alter table d_bonus_wallet modify column used_bonus bigint(20);
alter table d_bonus_wallet modify column available_bonus bigint(20);

alter table d_bonus_wallet_detail modify column bonus_change bigint(20);
alter table d_bonus_wallet_detail modify column bef_sum_bonus bigint(20);
alter table d_bonus_wallet_detail modify column bef_pending_bonus bigint(20);
alter table d_bonus_wallet_detail modify column bef_available_bonus bigint(20);
alter table d_bonus_wallet_detail modify column bef_used_bonus bigint(20);
alter table d_bonus_wallet_detail modify column aft_sum_bonus bigint(20);
alter table d_bonus_wallet_detail modify column aft_pending_bonus bigint(20);
alter table d_bonus_wallet_detail modify column aft_available_bonus bigint(20);
alter table d_bonus_wallet_detail modify column aft_used_bonus bigint(20);
alter table d_bonus_wallet_detail modify column src_customer_money_pay bigint(20);
alter table d_bonus_wallet_detail modify column src_customer_money_available bigint(20);

alter table d_goods modify column sum_price bigint(20);

alter table d_goods_item modify column profit_rate bigint(20);
alter table d_goods_item modify column price bigint(20);

alter table d_order modify column goods_sum_price bigint(20);
alter table d_order modify column order_price bigint(20);

alter table d_order_item modify column goods_price bigint(20);

alter table d_order_pay modify column sum_money bigint(20);
alter table d_order_pay modify column actually_money bigint(20);

alter table d_order_pay_item modify column amount bigint(20);

alter table d_wallet modify column sum_money bigint(20);
alter table d_wallet modify column used_money bigint(20);
alter table d_wallet modify column available_money bigint(20);

alter table d_wallet_detail modify column money_change bigint(20);
alter table d_wallet_detail modify column bef_sum_money bigint(20);
alter table d_wallet_detail modify column bef_used_money bigint(20);
alter table d_wallet_detail modify column bef_available_money bigint(20);
alter table d_wallet_detail modify column aft_sum_money bigint(20);
alter table d_wallet_detail modify column aft_used_money bigint(20);
alter table d_wallet_detail modify column aft_available_money bigint(20);