INSERT INTO state_(id_, name_)
VALUES (1, 'STATE1');
INSERT INTO state_(id_, name_)
VALUES (2, 'STATE2');
INSERT INTO state_(id_, name_)
VALUES (3, 'STATE3');

INSERT INTO area_(id_, name_)
VALUES (1, 'AREA1');
INSERT INTO area_(id_, name_)
VALUES (2, 'AREA2');

INSERT INTO sub_area_(id_, name_)
VALUES (1, 'SUB AREA1');
INSERT INTO sub_area_(id_, name_)
VALUES (2, 'SUB AREA2');

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (1, 'estudiante1@gmail.com', '12345678');

insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1, null, '704-459-0990', 912601, 200547236, 'lastname 1', 'name 1', 1);


INSERT INTO siptis_user_(id_, email_, password_)
VALUES (2, 'estudiante2@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (2, null, '704-459-0990', 912602, 200547237, 'lastname 2', 'name 2', 2);


INSERT INTO siptis_user_(id_, email_, password_)
VALUES (3, 'tutor1@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (3, null, '704-459-0990', 912603, 200547237, 'lastname 3', 'name 3', 3);

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (4, 'tutor2@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (4, null, '704-459-0990', 912604, 200547237, 'lastname 4', 'name 4', 4);


INSERT INTO siptis_user_(id_, email_, password_)
VALUES (5, 'supervisor1@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (5, null, '704-459-0990', 9125504, 200543237, 'lastname 5', 'name 5', 5);

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (6, 'supervisor2@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (6, null, '704-459-0990', 9125304, 200247237, 'lastname 6', 'name 6', 6);

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (7, 'docente1@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (7, null, '704-459-0990', 9112604, 20054707, 'lastname 7', 'name 7', 7);

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (8, 'docente2@gmail.com', '12345678');
insert into user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (8, null, '704-459-0990', 9122604, 2047237, 'lastname 8', 'name 8', 8);

--INSERT INTO semester_information_(id_, end_date_, in_progress_, period_, start_date_) VALUES (1, '2023-10-22', true, '2-2023', '2023-06-22')

INSERT INTO project_(id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_,
                        total_defense_points_, modality_id_, state_id_)
VALUES (1, 'Libro1', 'Name1', 'Perfil1', '2-2023', 'Fase1', null, null, 1, 1);

INSERT INTO project_(id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_,
                     total_defense_points_, modality_id_, state_id_)
VALUES (2, 'Libro2', 'Name2', 'Perfil2', '2-2023', 'Fase1', null, null, 1, 1);

INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1, null, 'ACTIVITY 1', 'ACTIVITY 1', 1);
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (2, null, 'ACTIVITY 2', 'ACTIVITY 2', 2);

INSERT INTO project_student_ (id_, project_id_, user_id_) VALUES (1, 1, 1);
INSERT INTO project_student_ (id_, project_id_, user_id_) VALUES (2, 2, 2);

INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (1, false, false, 1, 3);
INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (2, true , true , 2, 4);

INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (1, false, false, 1, 5);
INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (2, true , true , 2, 6);

INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (1, false, false, 1, 7);
INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (2, true , true , 2, 8);