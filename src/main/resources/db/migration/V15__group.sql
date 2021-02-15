alter table d_group modify column detail_img text;
alter table d_group add column video_cover_img varchar(200) after video;
alter table d_group add column video_list text after video_cover_img;
