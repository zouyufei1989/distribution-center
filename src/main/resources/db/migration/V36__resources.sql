INSERT INTO `ns_resources` (`memo`, `url`, `priority`, `level`, `name`, `id`, `parent_id`, `resorder`, `enable`, `icon`, `func_ids`)
VALUES
	(NULL, NULL, 0, 0, '预约管理', 700, 0, 7, 1, 'fa-book', NULL);
update ns_resources set parent_id = 700, id = 701,resorder=1 where id = 402;
update ns_resources set parent_id = 700, id = 702,resorder=2 where id = 508;
delete from ns_resources where id = 509;

update ns_rights set resource_id = 701 where resource_id = 402;
update ns_rights set resource_id = 702 where resource_id = 508;
delete from ns_rights where resource_id = 509;

delete from ns_roles_rights where not exists (select 1 from ns_rights where id = ns_roles_rights.right_id);
