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
VALUES (1, 'SUB_AREA1');
INSERT INTO sub_area_(id_, name_)
VALUES (2, 'SUB_AREA2');

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (1, 'estudiante1@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (11, 'estudiante2@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (21, 'estudiante3@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (31, 'estudiante4@gmail.com', '12345678');

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (2, 'tutor1@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (12, 'tutor2@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (22, 'tutor3@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (32, 'tutor4@gmail.com', '12345678');

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (3, 'supervisor1@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (13, 'supervisor2@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (23, 'supervisor3@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (33, 'supervisor4@gmail.com', '12345678');

INSERT INTO siptis_user_(id_, email_, password_)
VALUES (4, 'docente1@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (14, 'docente2@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (24, 'docente3@gmail.com', '12345678');
INSERT INTO siptis_user_(id_, email_, password_)
VALUES (34, 'docente4@gmail.com', '12345678');

INSERT INTO project_(id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_,
                     total_defense_points_, modality_id_, state_id_)
VALUES (1, 'Libro1', 'Name1', 'Perfil1', '2-2023', 'Fase1', null, null, 1, 1);

INSERT INTO project_(id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_,
                     total_defense_points_, modality_id_, state_id_)
VALUES (2, 'Libro2', 'Name2', 'Perfil2', '2-2023', 'Fase1', null, null, 1, 1);
INSERT INTO project_(id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_,
                     total_defense_points_, modality_id_, state_id_)
VALUES (3, 'Libro3', 'Name3', 'Perfil3', '2-2023', 'Fase1', null, null, 1, 1);

INSERT INTO project_(id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_,
                     total_defense_points_, modality_id_, state_id_)
VALUES (4, 'Libro4', 'Name4', 'Perfil4', '2-2023', 'Fase1', null, null, 1, 1);


INSERT INTO project_student_ (id_, project_id_, user_id_)
VALUES (1, 1, 1);
INSERT INTO project_student_ (id_, project_id_, user_id_)
VALUES (2, 2, 11);
INSERT INTO project_student_ (id_, project_id_, user_id_)
VALUES (3, 3, 21);
INSERT INTO project_student_ (id_, project_id_, user_id_)
VALUES (4, 4, 31);

INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (1, false, false, 1, 2);
INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (2, true, true, 2, 12);
INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (3, true, true, 3, 22);
INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (4, true, true, 4, 32);

INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (1, false, false, 1, 3);
INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (2, true, true, 2, 13);
INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (3, true, true, 3, 23);
INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (4, true, true, 4, 33);

INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (1, false, false, 1, 4);
INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (2, true, true, 2, 14);
INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (3, true, true, 3, 24);
INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (4, true, true, 4, 34);

INSERT INTO project_area_ (project_id_, area_id_)
VALUES (1, 1);
INSERT INTO project_area_ (project_id_, area_id_)
VALUES (2, 1);
INSERT INTO project_area_ (project_id_, area_id_)
VALUES (3, 1);
INSERT INTO project_area_ (project_id_, area_id_)
VALUES (4, 1);

INSERT INTO project_sub_area_ (project_id_, sub_area_id_)
VALUES (1, 1);
INSERT INTO project_sub_area_ (project_id_, sub_area_id_)
VALUES (2, 1);
INSERT INTO project_sub_area_ (project_id_, sub_area_id_)
VALUES (3, 1);
INSERT INTO project_sub_area_ (project_id_, sub_area_id_)
VALUES (4, 1);


INSERT INTO place_to_defense_ (id_, capacity_, location_, name_)
VALUES (1, 1, 'location', 1);
INSERT INTO defense_(id_, date_, end_time_, start_time_, substitute_name_, place_to_defense_id_, project_id_)
VALUES (1, null, null, null, null, 1, 1)


