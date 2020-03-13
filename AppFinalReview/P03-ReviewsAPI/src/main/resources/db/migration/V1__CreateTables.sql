create table product(id integer auto_increment, price integer, name varchar(255), about varchar(255),primary key (id));
create table review (id integer auto_increment, header varchar(255), content varchar(255), product_id integer, primary key (id));
create table comment (id integer auto_increment, header varchar(255), content varchar(255), review_id integer, primary key (id));

alter table review add constraint review_product_fk foreign key (product_id) references product (id);
alter table comment add constraint comment_review_fk foreign key (review_id) references review (id);
