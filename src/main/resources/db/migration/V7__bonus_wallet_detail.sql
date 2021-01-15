truncate table d_bonus_wallet_detail;
alter table d_bonus_wallet_detail add column batch_id varchar(40) not null after id;
alter table d_bonus_wallet_detail add index idx_batch_id (batch_id(10));