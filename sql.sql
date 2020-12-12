alter table d_customer add column create_date datetime not null;
alter table d_customer add column creator varchar(20) not null;
alter table d_customer add column create_ip varchar(20) not null;
alter table d_customer add column update_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
alter table d_customer add column updater varchar(20) ;
alter table d_customer add column update_ip varchar(20) ;
