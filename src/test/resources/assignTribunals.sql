create sequence activity_seq start with 1 increment by 50;
create sequence area_seq start with 1 increment by 50;
create sequence defense_seq start with 1 increment by 50;
create sequence document_seq start with 1 increment by 50;
create sequence general_activity_seq start with 1 increment by 50;
create sequence modality_seq start with 1 increment by 50;
create sequence place_to_defense_seq start with 1 increment by 50;
create sequence presentation_seq start with 1 increment by 50;
create sequence project_seq start with 1 increment by 50;
create sequence project_student_seq start with 1 increment by 50;
create sequence project_supervisor_seq start with 1 increment by 50;
create sequence project_teacher_seq start with 1 increment by 50;
create sequence project_tribunal_seq start with 1 increment by 50;
create sequence project_tutor_seq start with 1 increment by 50;
create sequence review_seq start with 1 increment by 50;
create sequence role_seq start with 1 increment by 50;
create sequence schedule_seq start with 1 increment by 50;
create sequence siptis_user_seq start with 1 increment by 50;
create sequence state_seq start with 1 increment by 50;
create sequence sub_area_seq start with 1 increment by 50;
create sequence user_area_seq start with 1 increment by 50;
create sequence user_career_seq start with 1 increment by 50;
create sequence user_information_seq start with 1 increment by 50;
create table activity (id bigint not null, activity_date timestamp(6), activity_description varchar(255), activity_name varchar(255), project_id bigint not null, primary key (id));
create table area (id bigint not null, name varchar(255), primary key (id));
create table defense (id bigint not null, date timestamp(6), place_to_defense_id bigint, project_id bigint, primary key (id));
create table document (id bigint not null, description varchar(255), path varchar(255), type varchar(255), user_id bigint not null, primary key (id));
create table general_activity (id bigint not null, activity_date timestamp(6), activity_description varchar(255), activity_name varchar(255), primary key (id));
create table modality (id bigint not null, name varchar(255), primary key (id));
create table place_to_defense (id bigint not null, capacity integer, location varchar(255), name varchar(255), primary key (id));
create table presentation (id bigint not null, blue_book_path varchar(255), date timestamp(6), phase varchar(255), project_path varchar(255), reviewed boolean, project_id bigint not null, primary key (id));
create table project (id bigint not null, blue_book_path varchar(255), name varchar(255), perfil_path varchar(255), phase varchar(255), project_path varchar(255), modality_id bigint not null, state_id bigint, primary key (id));
create table project_area (project_id bigint not null, area_id bigint not null);
create table project_student (id bigint not null, project_id bigint not null, user_id bigint not null, primary key (id));
create table project_sub_area (project_id bigint not null, sub_area_id bigint not null);
create table project_supervisor (id bigint not null, accepted boolean, reviewed boolean, project_id bigint not null, user_id bigint not null, primary key (id));
create table project_teacher (id bigint not null, accepted boolean, reviewed boolean, project_id bigint not null, user_id bigint not null, primary key (id));
create table project_tribunal (id bigint not null, accepted boolean, defense_points float(53), reviewed boolean, project_id bigint not null, user_id bigint not null, primary key (id));
create table project_tutor (id bigint not null, accepted boolean, reviewed boolean, project_id bigint not null, user_id bigint not null, primary key (id));
create table review (id bigint not null, commentary varchar(255), date timestamp(6), document_path varchar(255), presentation_id bigint not null, user_id bigint not null, primary key (id));
create table role (id integer not null, name varchar(255), primary key (id));
create table schedule (id bigint not null, days varchar(255), hour_finish varchar(255), hour_start varchar(255), user_id bigint not null, primary key (id));
create table siptis_user (id bigint not null, email varchar(255), password varchar(255), primary key (id));
create table siptis_user_area (siptisuser_id bigint not null, area_id bigint not null);
create table siptis_user_career (siptisuser_id bigint not null, career_id bigint not null, primary key (siptisuser_id, career_id));
create table siptis_user_role (siptis_user_id bigint not null, role_id integer not null, primary key (siptis_user_id, role_id));
create table state (id bigint not null, name varchar(255), primary key (id));
create table sub_area (id bigint not null, name varchar(255), primary key (id));
create table user_area (id bigint not null, name varchar(255), primary key (id));
create table user_career (id bigint not null, name varchar(255), primary key (id));
create table user_information (id bigint not null, birth_date timestamp(6), cel_number varchar(255), ci varchar(255), codsis varchar(255), lastnames varchar(255), names varchar(255), user_id bigint, primary key (id));
alter table if exists activity add constraint FKnhet5ajl85sdn6l77obdgh47s foreign key (project_id) references project;
alter table if exists defense add constraint FKrorlgp1jnvjkmsf121o7ha645 foreign key (place_to_defense_id) references place_to_defense;
alter table if exists defense add constraint FK88auhvb4tfyd7hba7q1m0d0wx foreign key (project_id) references project;
alter table if exists document add constraint FK7iadwrg8rxcn2o6jpboa8gmm5 foreign key (user_id) references siptis_user;
alter table if exists presentation add constraint FKedd9862jjq377ce3laaquoymq foreign key (project_id) references project;
alter table if exists project add constraint FKognp3vx6c8kg3w3tx424hjny0 foreign key (modality_id) references modality;
alter table if exists project add constraint FKn9hn5axyk6qcevc2d67ohv8db foreign key (state_id) references state;
alter table if exists project_area add constraint FKbf6m2u4tapd9cenngqjyqc774 foreign key (area_id) references area;
alter table if exists project_area add constraint FKuw9yqw4yc2bghoi3nb3qxw0e foreign key (project_id) references project;
alter table if exists project_student add constraint FKrwrqi8lnwc20jdsl0di7595dt foreign key (project_id) references project;
alter table if exists project_student add constraint FKsr6ayravxleck77y16nxc9hq7 foreign key (user_id) references siptis_user;
alter table if exists project_sub_area add constraint FKn987ecr1cjl2cd625wlkrl1hv foreign key (sub_area_id) references sub_area;
alter table if exists project_sub_area add constraint FKb02p9kv0d117pbhphnlpcj8cv foreign key (project_id) references project;
alter table if exists project_supervisor add constraint FK1fv4lhlb9f048yx8rtudjy5w3 foreign key (project_id) references project;
alter table if exists project_supervisor add constraint FK9i9nn7f4iocx48fundh4c2ikq foreign key (user_id) references siptis_user;
alter table if exists project_teacher add constraint FKhka5y8y8fkufxvf7iduixf2vb foreign key (project_id) references project;
alter table if exists project_teacher add constraint FKsxtvvvnws7q0hyqvx4lfkv9gb foreign key (user_id) references siptis_user;
alter table if exists project_tribunal add constraint FKm82glcu5tgddlq3dqrdi5q4io foreign key (project_id) references project;
alter table if exists project_tribunal add constraint FK9by12k964r2ru8lj4p3r02wxe foreign key (user_id) references siptis_user;
alter table if exists project_tutor add constraint FKchr5idf92534nn79x6yxs6hgj foreign key (project_id) references project;
alter table if exists project_tutor add constraint FKe4qb8lso714h4lnuo3d4kxh2h foreign key (user_id) references siptis_user;
alter table if exists review add constraint FKng15mm7iap0r2cf1eqb8u1yfb foreign key (presentation_id) references presentation;
alter table if exists review add constraint FKc3ndxb09di2tyht70qchnfqqj foreign key (user_id) references siptis_user;
alter table if exists schedule add constraint FK2j68aoetawruc5axkipyli0k5 foreign key (user_id) references siptis_user;
alter table if exists siptis_user_area add constraint FKj8146brt9hq15ohri23ojenf9 foreign key (area_id) references user_area;
alter table if exists siptis_user_area add constraint FKnal79kjpbqnpa8h431j2d7ako foreign key (siptisuser_id) references siptis_user;
alter table if exists siptis_user_career add constraint FK5nk88k1ymq9fm6xijx8mbw55a foreign key (career_id) references user_career;
alter table if exists siptis_user_career add constraint FKsp6t1u23c0lmsho5qdwp4tkpi foreign key (siptisuser_id) references siptis_user;
alter table if exists siptis_user_role add constraint FKiwsuqrw1v47hbh2453bn546po foreign key (role_id) references role;
alter table if exists siptis_user_role add constraint FKfn4apbnherrxxyd1o40wu9lnv foreign key (siptis_user_id) references siptis_user;
alter table if exists user_information add constraint FKldmpd0ymsok0iwbltr85tc5fq foreign key (user_id) references siptis_user;


INSERT INTO area(id, name) VALUES (1, 'Area1');

INSERT INTO area(id, name) VALUES (2, 'Area2');

INSERT INTO sub_area(id, name) VALUES (1, 'SubArea1');

INSERT INTO sub_area(id, name) VALUES (2, 'SubArea2');

INSERT INTO modality(id, name) VALUES (1, 'Adscripcion');


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name,  state_id, modality_id) VALUES (1, 'Libro1', 'Perfil1', 'Proyecto1', 'Fase1', 'ProyectoGrado1', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (1, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1, 1);



INSERT INTO role(id, name) VALUES (1, 'ADMIN');
INSERT INTO role(id, name) VALUES (2, 'TRIBUNAL');
INSERT INTO role(id, name) VALUES (3, 'TUTOR');
INSERT INTO role(id, name) VALUES (4, 'TEACHER');
INSERT INTO role(id, name) VALUES (5, 'SUPERVISOR');



INSERT INTO siptis_user(id, email, password) VALUES (1, 'usuario1@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (1, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos1', 'Nombres1',  1);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1, 2);


INSERT INTO siptis_user(id, email, password) VALUES (5, 'usuario5@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (5, '2000-1-19', '1234571', '1000004', '12345674', 'Apellidos5', 'Nombres5',  5);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (5, 2);


INSERT INTO siptis_user(id, email, password) VALUES (9, 'usuario9@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (9, '2000-1-19', '1234575', '1000008', '12345678', 'Apellidos9', 'Nombres9',  9);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (9, 2);