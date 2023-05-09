-- Proyecto

INSERT INTO place_to_defense(id, capacity, location, name) VALUES (1, 20, 'Departamento informatica-sistemas', 'Auditorio');
INSERT INTO place_to_defense(id, capacity, location, name) VALUES (2, 200, 'Edificio nuevo', 'Auditorio');
INSERT INTO place_to_defense(id, capacity, location, name) VALUES (3, 50, 'Edificio nuevo', 'Aula 692B');

INSERT INTO area(id, name) VALUES (1, 'Area1');

INSERT INTO area(id, name) VALUES (2, 'Area2');

INSERT INTO sub_area(id, name) VALUES (1, 'SubArea1');

INSERT INTO sub_area(id, name) VALUES (2, 'SubArea2');

INSERT INTO modality(id, name) VALUES (1, 'Adscripcion');


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name,  state_id, modality_id) VALUES (1, 'Libro1', 'Perfil1', 'Proyecto1', 'Fase1', 'ProyectoGrado1',  null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (1, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id)VALUES (2, 'Libro2', 'Perfil2', 'Proyecto2', 'Fase2', 'ProyectoGrado2', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (2, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (2, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (3, 'Libro3', 'Perfil3', 'Proyecto3', 'Fase3', 'ProyectoGrado3', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (3, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (3, 1);

--

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (4, 'Libro4', 'Perfil4', 'Proyecto4', 'Fase4', 'ProyectoGrado4', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (4, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (4, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (5, 'Libro5', 'Perfil5', 'Proyecto5', 'Fase5', 'ProyectoGrado5', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (5, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (5, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (6, 'Libro6', 'Perfil6', 'Proyecto6', 'Fase6', 'ProyectoGrado6', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (6, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (6, 1);


--

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (7, 'Libro7', 'Perfil7', 'Proyecto7', 'Fase7', 'ProyectoGrado7', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (7, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (7, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (8, 'Libro8', 'Perfil8', 'Proyecto8', 'Fase8', 'ProyectoGrado8', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (8, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (8, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (9, 'Libro9', 'Perfil9', 'Proyecto9', 'Fase9', 'ProyectoGrado9', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (9, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (9, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (10, 'Libro10', 'Perfil10', 'Proyecto10', 'Fase10', 'ProyectoGrado10', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (10, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (10, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (11, 'Libro11', 'Perfil11', 'Proyecto11', 'Fase11', 'ProyectoGrado11', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (11, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (11, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (12, 'Libro12', 'Perfil12', 'Proyecto12', 'Fase12', 'ProyectoGrado12',null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (12, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (12, 1);

INSERT INTO defense(id, date, place_to_defense_id, project_id) VALUES (1, '2000-1-19', 1, 12);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (13, 'Libro13', 'Perfil13', 'Proyecto13', 'Fase13', 'ProyectoGrado13', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (13, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (13, 1);

INSERT INTO defense(id, date, place_to_defense_id, project_id) VALUES (2, '2000-1-19', 2, 13);

--user
INSERT INTO role(id, name) VALUES (1, 'ADMIN');
INSERT INTO role(id, name) VALUES (2, 'TRIBUNAL');
INSERT INTO role(id, name) VALUES (3, 'TUTOR');
INSERT INTO role(id, name) VALUES (4, 'TEACHER');
INSERT INTO role(id, name) VALUES (5, 'SUPERVISOR');
INSERT INTO role(id, name) VALUES (6, 'STUDENT');

INSERT INTO siptis_user(id, email, password) VALUES (1, 'usuario1@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (1, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos1', 'Nombres1',  1);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1, 2);

INSERT INTO siptis_user(id, email, password) VALUES (2, 'usuario2@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (2, '2000-1-19', '1234568', '1000001', '12345671', 'Apellidos2', 'Nombres2',  2);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (2, 3);

INSERT INTO siptis_user(id, email, password) VALUES (3, 'usuario3@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (3, '2000-1-19', '1234569', '1000002', '12345672', 'Apellidos3', 'Nombres3', 3);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (3, 4);


INSERT INTO siptis_user(id, email, password) VALUES (4, 'usuario4@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (4, '2000-1-19', '1234570', '1000003', '12345673', 'Apellidos4', 'Nombres4',  4);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (4, 2);


INSERT INTO siptis_user(id, email, password) VALUES (5, 'usuario5@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (5, '2000-1-19', '1234571', '1000004', '12345674', 'Apellidos5', 'Nombres5',  5);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (5, 2);


INSERT INTO siptis_user(id, email, password) VALUES (6, 'usuario6@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (6, '2000-1-19', '1234572', '1000005', '12345675', 'Apellidos6', 'Nombres6',  6);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (6, 3);


INSERT INTO siptis_user(id, email, password) VALUES (7, 'usuario7@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (7, '2000-1-19', '1234573', '1000006', '12345676', 'Apellidos7', 'Nombres7',  7);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (7, 4);


INSERT INTO siptis_user(id, email, password) VALUES (8, 'usuario8@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (8, '2000-1-19', '1234574', '1000007', '12345677', 'Apellidos8', 'Nombres8',  8);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (8, 5);


INSERT INTO siptis_user(id, email, password) VALUES (9, 'usuario9@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (9, '2000-1-19', '1234575', '1000008', '12345678', 'Apellidos9', 'Nombres9',  9);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (9, 2);


INSERT INTO siptis_user(id, email, password) VALUES (10, 'usuario10@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (10,'2000-1-19', '1234576', '1000009', '12345679', 'Apellidos10', 'Nombres10',  10);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (10, 3);


INSERT INTO siptis_user(id, email, password) VALUES (11, 'usuario11@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (11,'2000-1-19', '1234577', '1000010', '12345680', 'Apellidos11', 'Nombres11',  11);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (11, 4);


INSERT INTO siptis_user(id, email, password) VALUES (12, 'usuario12@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (12,'2000-1-19', '1234578', '1000011', '12345681', 'Apellidos12', 'Nombres12', 12);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (12, 5);


INSERT INTO siptis_user(id, email, password) VALUES (13, 'usuario13@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (13,'2000-1-19', '1234579', '1000012', '12345682', 'Apellidos13', 'Nombres13', 13);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (13, 2);


INSERT INTO siptis_user(id, email, password) VALUES (14, 'usuario14@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (14,'2000-1-19', '1234580', '1000013', '12345683', 'Apellidos14', 'Nombres14', 14);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (14, 3);


INSERT INTO siptis_user(id, email, password) VALUES (15, 'usuario15@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (15,'2000-1-19', '1234581', '1000014', '12345684', 'Apellidos15', 'Nombres15', 15);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (15, 4);


INSERT INTO siptis_user(id, email, password) VALUES (16, 'usuario16@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (16,'2000-1-19', '1234582', '1000015', '12345685', 'Apellidos16', 'Nombres16', 16);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (16, 5);


INSERT INTO siptis_user(id, email, password) VALUES (17, 'usuario17@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (17,'2000-1-19', '1234583', '1000016', '12345686', 'Apellidos17', 'Nombres17', 17);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (17, 2);


INSERT INTO siptis_user(id, email, password) VALUES (18, 'usuario18@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (18,'2000-1-19', '1234584', '1000017', '12345687', 'Apellidos18', 'Nombres18', 18);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (18, 3);

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



// review projects

INSERT INTO siptis_user(id, email, password) VALUES (30, 'usuarioReview@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (30, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosReview', 'NombresReview', 30);

INSERT INTO siptis_user(id, email, password) VALUES (31, 'estudiante1@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (31, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosEstudiante1', 'NombresEstudiante1', 31);

INSERT INTO siptis_user(id, email, password) VALUES (32, 'estudiante2@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (32, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosEstudiante2', 'NombresEstudiante2', 32);



INSERT INTO modality(id, name) VALUES (10, 'Modalidad');

INSERT INTO area(id, name) VALUES (10, 'AreaReview');

INSERT INTO sub_area(id, name) VALUES (10, 'SubAreaReview');

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (30, 'LibroReview1', null, 'ProyectoReview1', 'FaseReview1', 'ProyectoGradoReview1', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (30, 10);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (30, 10);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (30, false, false, 30, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (30, 31, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (31, 32, 30);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (31, 'LibroReview2', null, 'ProyectoReview2', 'FaseReview2', 'ProyectoGradoReview2', null, 1);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (1, 'blueBookPath1', 'phase1', 'projectPath1', false, 31, '2022-1-1');

INSERT INTO project_area(project_id, area_id) VALUES (31, 10);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (31, 10);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (31, false, false, 31, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (32, 31, 31);
INSERT INTO project_student(id, user_id, project_id) VALUES (33, 32, 31);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (32, 'LibroReview3', null, 'ProyectoReview3', 'FaseReview3', 'ProyectoGradoReview3', null, 1);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (2, 'blueBookPath2', 'phase2', 'projectPath2', true, 32, '2022-1-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (1, 'commentary1', 'documentPath1', 2, 30, '2022-1-7');

INSERT INTO project_area(project_id, area_id) VALUES (32, 10);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (32, 10);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (32,  false, true, 32, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (34, 31, 32);
INSERT INTO project_student(id, user_id, project_id) VALUES (35, 32, 32);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (33, 'LibroReview4', null, 'ProyectoReview4', 'FaseReview4', 'ProyectoGradoReview4', null, 1);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (3, 'blueBookPath3', 'phase3', 'projectPath3', true, 33, '2022-2-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (2, 'commentary2', 'documentPath2', 3, 30, '2022-2-5');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (3, 'commentary3', 'documentPath3', 3, 1, '2022-2-5');

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (4, 'blueBookPath4', 'phase4', 'projectPath4', true, 33, '2022-3-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (4, 'commentary4', 'documentPath4', 4, 30, '2022-3-10');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (5, 'commentary5', 'documentPath5', 4, 1, '2022-3-10');

INSERT INTO project_area(project_id, area_id) VALUES (33, 10);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (33, 10);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (33,  false, true, 33, 30);
INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (34,  false, true, 33, 1);
INSERT INTO project_student(id, user_id, project_id) VALUES (36, 31, 33);
INSERT INTO project_student(id, user_id, project_id) VALUES (37, 32, 33);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (34, 'LibroReview5', null, 'ProyectoReview5', 'FaseReview5', 'ProyectoGradoReview5', null, 1);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (5, 'blueBookPath5', 'phase5', 'projectPath5', true, 34, '2022-1-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (6, 'commentary5', 'documentPath5', 5, 1, '2022-1-7');

INSERT INTO project_area(project_id, area_id) VALUES (34, 10);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (34, 10);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (35,  false, true, 34, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (38, 31, 34);
INSERT INTO project_student(id, user_id, project_id) VALUES (39, 32, 34);



--//project with all kind of reviewers

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (40, 'Libro', 'Perfil', 'Proyecto', 'Fase', 'ProyectoGrado', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (40, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (40, 1);



INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (42, false, false, 40, 3);


INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (43, false, false, 40, 4);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (44, false, false, 40, 5);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (45, false, false, 40, 6);


INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (46, false, false, 40, 7);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (47, false, false, 40, 8);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (48, false, false, 40, 9);


INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (49, false, null, false, 40, 10);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (50, false, null, false, 40, 11);



--cambiar de fase proyectos

INSERT INTO siptis_user(id, email, password) VALUES (50, 'tribunal@mail.com', '12345678');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (50, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosTribunal', 'NombresTribunal', 50);

INSERT INTO siptis_user(id, email, password) VALUES (51, 'tutor@mail.com', '12345678');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (51, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosTutor', 'NombresTutor', 51);

INSERT INTO siptis_user(id, email, password) VALUES (52, 'supervisor@mail.com', '12345678');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (52, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosSupervisor', 'NombresSupervisor', 52);

INSERT INTO siptis_user(id, email, password) VALUES (53, 'docente@mail.com', '12345678');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (53, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosDocente', 'NombresDocente', 53);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (50, 'Libro1', null, 'Proyecto1', 'TRIBUNALS_PHASE', 'ProyectoGrado1', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (60, false, null, false, 50, 50);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (61, false, null, false, 50, 51);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (50, true, false, 50, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (50, true, false, 50, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (50, true, false, 50, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (51, 'Libro2', null, 'Proyecto2', 'TRIBUNALS_PHASE', 'ProyectoGrado2', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (51, false, null, false, 51, 50);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (52, true, null, false, 51, 51);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (53, true, null, false, 51, 52);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (51, false, false, 51, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (51, false, false, 51, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (51, false, false, 51, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (52, 'Libro3', null, 'Proyecto3', 'REVIEWERS_PHASE', 'ProyectoGrado3', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (54, false, null, false, 52, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (52, false, false, 52, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (52, false, false, 52, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (52, false, false, 52, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (53, 'Libro4', null, 'Proyecto4', 'REVIEWERS_PHASE', 'ProyectoGrado4', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (55, true, null, false, 53, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (53, false, false, 53, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (53, true, false, 53, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (53, true, false, 53, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (54, 'Libro5', null, 'Proyecto5', 'REVIEWERS_PHASE', 'ProyectoGrado5', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (56, false, null, false, 54, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (54, false, false, 54, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (54, false, false, 54, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (54, false, false, 54, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (55, 'Libro6', null, 'Proyecto6', 'REVIEWERS_PHASE', 'ProyectoGrado6', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (57, false, null, false, 55, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (55, true, false, 55, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (55, false, false, 55, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (55, true, false, 55, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (56, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (58, false, null, false, 56, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (56, false, false, 56, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (56, false, false, 56, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (56, false, false, 56, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (57, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (59, false, null, false, 57, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (57, true, false, 57, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (57, true, false, 57, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (57, false, false, 57, 53);
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





---- get schedule info

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (100, 'Libro100', 'Perfil100', 'Proyecto100', 'Fase100', 'ProyectoGrado100', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (100, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (100, 1);


INSERT INTO siptis_user(id, email, password) VALUES (100, 'usuario100@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (100, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos100', 'Nombres100',  100);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (100, 2);


INSERT INTO siptis_user(id, email, password) VALUES (101, 'usuario101@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (101, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos101', 'Nombres101',  101);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (101, 2);


INSERT INTO siptis_user(id, email, password) VALUES (102, 'usuario102@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (102, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos102', 'Nombres102',  102);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (102, 2);



INSERT INTO siptis_user(id, email, password) VALUES (103, 'usuario103@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (103, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos103', 'Nombres103',  103);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (103, 6);



INSERT INTO siptis_user(id, email, password) VALUES (104, 'usuario104@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (104, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos104', 'Nombres104',  104);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (104, 6);




INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (100, false, null, false, 100, 100);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (101, false, null, false, 100, 101);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (102, false, null, false, 100, 102);


INSERT INTO project_student(id, user_id, project_id) VALUES (100, 103, 100);

INSERT INTO project_student(id, user_id, project_id) VALUES (101, 101, 100);


INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (1, 'Lunes', '02:55', '13:31', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (2, 'Lunes', '19:01', '10:44', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (3, 'Lunes', '13:32', '12:25', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (4, 'Lunes', '05:31', '04:49', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (5, 'Lunes', '74:62', '65:43', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (6, 'Lunes', '86:27', '76:54', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (7, 'Lunes', '86:35', '86:65', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (8, 'Lunes', '86:24', '98:68', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (9, 'Lunes', '24:53', '48:38', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (10, 'Lunes', '07:96', '28:85', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (11, 'Lunes', '61:25', '42:98', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (12, 'Lunes', '04:37', '83:81', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (13, 'Lunes', '13:18', '17:07', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (14, 'Lunes', '06:78', '81:38', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (15, 'Lunes', '47:97', '24:34', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (16, 'Lunes', '79:67', '27:49', 100);


INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (17, 'Martes', '62:47', '38:62', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (18, 'Martes', '46:46', '46:31', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (19, 'Martes', '46:79', '80:70', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (20, 'Martes', '64:60', '05:90', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (21, 'Martes', '93:18', '23:19', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (22, 'Martes', '32:86', '29:84', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (23, 'Martes', '12:38', '18:38', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (24, 'Martes', '68:67', '16:67', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (25, 'Martes', '13:14', '32:18', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (26, 'Martes', '67:31', '64:91', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (27, 'Martes', '61:49', '91:34', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (28, 'Martes', '42:64', '13:32', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (29, 'Martes', '36:94', '19:75', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (30, 'Martes', '67:91', '36:47', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (31, 'Martes', '67:92', '19:79', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (32, 'Martes', '91:78', '31:47', 101);


INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (33, 'Miercoles', '13:48', '97:61', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (34, 'Miercoles', '17:97', '63:17', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (35, 'Miercoles', '97:13', '47:63', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (36, 'Miercoles', '57:94', '13:74', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (37, 'Miercoles', '67:13', '17:63', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (38, 'Miercoles', '28:97', '31:64', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (39, 'Miercoles', '67:51', '84:17', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (40, 'Miercoles', '93:28', '19:73', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (41, 'Miercoles', '94:82', '46:37', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (42, 'Miercoles', '49:34', '47:93', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (43, 'Miercoles', '71:98', '28:97', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (44, 'Miercoles', '36:61', '64:82', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (45, 'Miercoles', '96:85', '87:41', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (46, 'Miercoles', '37:94', '28:95', 100);


INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (47, 'Jueves', '34:48', '18:91', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (48, 'Jueves', '61:82', '18:91', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (49, 'Jueves', '82:97', '16:28', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (50, 'Jueves', '79:81', '97:58', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (51, 'Jueves', '96:85', '48:28', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (52, 'Jueves', '87:28', '93:94', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (53, 'Jueves', '97:38', '82:19', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (54, 'Jueves', '37:28', '84:58', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (55, 'Jueves', '71:48', '95:47', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (56, 'Jueves', '48:93', '28:84', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (57, 'Jueves', '87:82', '96:94', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (58, 'Jueves', '82:20', '12:14', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (59, 'Jueves', '34:61', '14:50', 103);


INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (60, 'Viernes', '13:02', '80:71', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (61, 'Viernes', '30:10', '20:28', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (62, 'Viernes', '01:47', '82:90', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (63, 'Viernes', '91:27', '80:94', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (64, 'Viernes', '34:91', '28:20', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (65, 'Viernes', '34:80', '74:85', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (66, 'Viernes', '67:69', '69:61', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (67, 'Viernes', '07:50', '34:20', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (68, 'Viernes', '34:20', '34:28', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (69, 'Viernes', '47:85', '67:60', 103);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (70, 'Viernes', '03:24', '82:20', 104);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (71, 'Viernes', '34:80', '17:37', 100);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (72, 'Viernes', '34:82', '80:17', 101);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (73, 'Viernes', '35:80', '47:28', 102);
INSERT INTO schedule(id, days, hour_finish, hour_start, user_id) VALUES (74, 'Viernes', '39:84', '17:80', 103);





INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (200, 'Libro200', 'Perfil200', 'Proyecto200', 'Fase200', 'ProyectoGrado200', null, 1);

INSERT INTO project_area(project_id, area_id) VALUES (200, 1);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (200, 1);


INSERT INTO siptis_user(id, email, password) VALUES (200, 'usuario200@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (200, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos200', 'Nombres200',  200);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (200, 2);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (200, true, null, false, 200, 200);


INSERT INTO siptis_user(id, email, password) VALUES (201, 'usuario201@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (201, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos201', 'Nombres201',  201);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (201, 2);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (201, true, null, false, 200, 201);


INSERT INTO siptis_user(id, email, password) VALUES (202, 'usuario202@mail.com', '12345678');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (202, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos202', 'Nombres202',  202);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (202, 2);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (202, true, null, false, 200, 202);


INSERT INTO defense(id, date, place_to_defense_id, project_id) VALUES (200, '2000-1-19', 1, 200);