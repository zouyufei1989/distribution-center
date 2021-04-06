alter table d_employee_customer add column inherit_chain varchar(2000) after status;
alter table d_employee_customer add column parent_id int after status;
alter table d_employee_customer add column parent_employee_id int after status;