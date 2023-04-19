-- Proyecto

INSERT INTO area(id, name) VALUES (1, 'Area1');

INSERT INTO area(id, name) VALUES (2, 'Area2');

INSERT INTO sub_area(id, name) VALUES (1, 'SubArea1');

INSERT INTO sub_area(id, name) VALUES (2, 'SubArea2');

INSERT INTO modality(id, name) VALUES (1, 'Adscripcion');


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (1, 'Libro1', 'Perfil1', 'Proyecto1', 'Fase1', 'ProyectoGrado1', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (1, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id)VALUES (2, 'Libro2', 'Perfil2', 'Proyecto2', 'Fase2', 'ProyectoGrado2', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (2, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (2, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (3, 'Libro3', 'Perfil3', 'Proyecto3', 'Fase3', 'ProyectoGrado3', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (3, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (3, 1);

--

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (4, 'Libro4', 'Perfil4', 'Proyecto4', 'Fase4', 'ProyectoGrado4', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (4, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (4, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (5, 'Libro5', 'Perfil5', 'Proyecto5', 'Fase5', 'ProyectoGrado5', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (5, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (5, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (6, 'Libro6', 'Perfil6', 'Proyecto6', 'Fase6', 'ProyectoGrado6', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (6, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (6, 1);


--

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (7, 'Libro7', 'Perfil7', 'Proyecto7', 'Fase7', 'ProyectoGrado7', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (7, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (7, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (8, 'Libro8', 'Perfil8', 'Proyecto8', 'Fase8', 'ProyectoGrado8', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (8, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (8, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (9, 'Libro9', 'Perfil9', 'Proyecto9', 'Fase9', 'ProyectoGrado9', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (9, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (9, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (10, 'Libro10', 'Perfil10', 'Proyecto10', 'Fase10', 'ProyectoGrado10', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (10, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (10, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (11, 'Libro11', 'Perfil11', 'Proyecto11', 'Fase11', 'ProyectoGrado11', null, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (11, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (11, 1);


INSERT INTO defense(id, defense_hour, place) VALUES (1, 'Hora1', 'Defensa1');

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (12, 'Libro12', 'Perfil12', 'Proyecto12', 'Fase12', 'ProyectoGrado12', 1, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (12, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (12, 1);


INSERT INTO defense(id, defense_hour, place) VALUES (2, 'Hora2', 'Defensa2');

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, defense_id, state_id, modality_id) VALUES (13, 'Libro13', 'Perfil13', 'Proyecto13', 'Fase13', 'ProyectoGrado13', 2, null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (13, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (13, 1);


--user
INSERT INTO siptis_user(id, email, password) VALUES (1, 'usuario1@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos1', 'Nombres1', 1);

INSERT INTO siptis_user(id, email, password) VALUES (2, 'usuario2@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (2, '2000-1-19', '1234568', '1000001', '12345671', 'Apellidos2', 'Nombres2', 2);

INSERT INTO siptis_user(id, email, password) VALUES (3, 'usuario3@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (3, '2000-1-19', '1234569', '1000002', '12345672', 'Apellidos3', 'Nombres3',  3);

INSERT INTO siptis_user(id, email, password) VALUES (4, 'usuario4@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (4, '2000-1-19', '1234570', '1000003', '12345673', 'Apellidos4', 'Nombres4', 4);

INSERT INTO siptis_user(id, email, password) VALUES (5, 'usuario5@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (5, '2000-1-19', '1234571', '1000004', '12345674', 'Apellidos5', 'Nombres5', 5);

INSERT INTO siptis_user(id, email, password) VALUES (6, 'usuario6@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (6, '2000-1-19', '1234572', '1000005', '12345675', 'Apellidos6', 'Nombres6', 6);

INSERT INTO siptis_user(id, email, password) VALUES (7, 'usuario7@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (7, '2000-1-19', '1234573', '1000006', '12345676', 'Apellidos7', 'Nombres7', 7);

INSERT INTO siptis_user(id, email, password) VALUES (8, 'usuario8@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (8, '2000-1-19', '1234574', '1000007', '12345677', 'Apellidos8', 'Nombres8', 8);

INSERT INTO siptis_user(id, email, password) VALUES (9, 'usuario9@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (9, '2000-1-19', '1234575', '1000008', '12345678', 'Apellidos9', 'Nombres9', 9);

INSERT INTO siptis_user(id, email, password) VALUES (10, 'usuario10@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (10,'2000-1-19', '1234576', '1000009', '12345679', 'Apellidos10', 'Nombres10', 10);

INSERT INTO siptis_user(id, email, password) VALUES (11, 'usuario11@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (11,'2000-1-19', '1234577', '1000010', '12345680', 'Apellidos11', 'Nombres11', 11);

INSERT INTO siptis_user(id, email, password) VALUES (12, 'usuario12@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (12,'2000-1-19', '1234578', '1000011', '12345681', 'Apellidos12', 'Nombres12', 12);

INSERT INTO siptis_user(id, email, password) VALUES (13, 'usuario13@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (13,'2000-1-19', '1234579', '1000012', '12345682', 'Apellidos13', 'Nombres13', 13);

INSERT INTO siptis_user(id, email, password) VALUES (14, 'usuario14@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (14,'2000-1-19', '1234580', '1000013', '12345683', 'Apellidos14', 'Nombres14', 14);

INSERT INTO siptis_user(id, email, password) VALUES (15, 'usuario15@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (15,'2000-1-19', '1234581', '1000014', '12345684', 'Apellidos15', 'Nombres15', 15);

INSERT INTO siptis_user(id, email, password) VALUES (16, 'usuario16@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (16,'2000-1-19', '1234582', '1000015', '12345685', 'Apellidos16', 'Nombres16', 16);

INSERT INTO siptis_user(id, email, password) VALUES (17, 'usuario17@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (17,'2000-1-19', '1234583', '1000016', '12345686', 'Apellidos17', 'Nombres17', 17);

INSERT INTO siptis_user(id, email, password) VALUES (18, 'usuario18@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (18,'2000-1-19', '1234584', '1000017', '12345687', 'Apellidos18', 'Nombres18', 18);

-- Estudiantes y revisores

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (1, false, false, 1, 1);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (2, false, true, 2, 1);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (3, true, false, 3, 1);


INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (1, false, false, 4, 2);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (2, false, true, 5, 2);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (3, true, false, 6, 2);


INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (1, false, false, 7, 3);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (2, false, true, 8, 3);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (3, true, false, 9, 3);


INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1, false, null, false, 10, 4);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (2, false, null, true, 11, 4);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (3, true, null, false, 12, 4);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (4, true, 100, false, 13, 4);


INSERT INTO project_student(id, user_id, project_id) VALUES (1, 5, 1);

INSERT INTO project_student(id, user_id, project_id) VALUES (2, 6, 2);

INSERT INTO project_student(id, user_id, project_id) VALUES (3, 7, 3);

INSERT INTO project_student(id, user_id, project_id) VALUES (4, 8, 4);

INSERT INTO project_student(id, user_id, project_id) VALUES (5, 9, 5);

INSERT INTO project_student(id, user_id, project_id) VALUES (6, 10, 6);

INSERT INTO project_student(id, user_id, project_id) VALUES (7, 11, 7);

INSERT INTO project_student(id, user_id, project_id) VALUES (8, 12, 8);

INSERT INTO project_student(id, user_id, project_id) VALUES (9, 13, 9);

INSERT INTO project_student(id, user_id, project_id) VALUES (10, 14, 10);

INSERT INTO project_student(id, user_id, project_id) VALUES (11, 15, 11);

INSERT INTO project_student(id, user_id, project_id) VALUES (12, 16, 12);

INSERT INTO project_student(id, user_id, project_id) VALUES (13, 17, 13);

// General Activities
INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (9, 'Actividad General 1', 'Descripcion 1', '2023-4-15');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (8, 'Actividad General 2', 'Descripcion 2', '2023-7-2');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (7, 'Actividad General 3', 'Descripcion 3', '2023-7-3');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (6, 'Actividad General 4', 'Descripcion 4', '2023-7-4');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (5, 'Actividad General 5', 'Descripcion 5', '2023-7-5');


INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (1, 'Actividad 1', 'Descripcion 1', '2023-8-1', 1);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (2, 'Actividad 2', 'Descripcion 2', '2023-8-2', 2);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (3, 'Actividad 3', 'Descripcion 3', '2023-8-3', 3);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (4, 'Actividad 4', 'Descripcion 4', '2023-8-4', 4);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (5, 'Actividad 5', 'Descripcion 5', '2023-8-5', 5);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (6, 'Actividad 6', 'Descripcion 6', '2023-8-6', 1);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (7, 'Actividad 7', 'Descripcion 7', '2023-8-7', 2);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (8, 'Actividad 8', 'Descripcion 8', '2023-8-8', 3);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (9, 'Actividad 9', 'Descripcion 9', '2023-8-9', 4);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (10, 'Actividad 10', 'Descripcion 10', '2023-8-10', 5);




