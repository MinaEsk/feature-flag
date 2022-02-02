drop table if exists feature CASCADE
drop table if exists user CASCADE
drop table if exists user_feature CASCADE
create table feature (id bigint not null, name varchar(255), primary key (id))
create table user (id bigint not null, name varchar(255), primary key (id))
create table user_feature (user_id bigint not null, feature_id bigint not null)
alter table user_feature add constraint FKmx3eju7c4621kk85qokruanh9 foreign key (feature_id) references feature
alter table user_feature add constraint FK23lcvj47ptgxgw3vci4916sh foreign key (user_id) references user