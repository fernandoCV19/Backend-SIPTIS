-- Proyecto

INSERT INTO place_to_defense(id, capacity, location, name) VALUES (1, 20, 'Departamento informatica-sistemas', 'Auditorio');
INSERT INTO place_to_defense(id, capacity, location, name) VALUES (2, 200, 'Edificio nuevo', 'Auditorio');
INSERT INTO place_to_defense(id, capacity, location, name) VALUES (3, 50, 'Edificio nuevo', 'Aula 692B');

INSERT INTO place_to_defense(id, capacity, location, name) VALUES (101, 20, 'Departamento informatica-sistemas', 'Auditorio');
INSERT INTO place_to_defense(id, capacity, location, name) VALUES (102, 200, 'Edificio nuevo', 'Auditorio');
INSERT INTO place_to_defense(id, capacity, location, name) VALUES (103, 50, 'Edificio nuevo', 'Aula 692B');

INSERT INTO area(id, name) VALUES (101, 'Area1');

INSERT INTO area(id, name) VALUES (102, 'Area2');

INSERT INTO area(id, name) VALUES (3, 'Area3');

INSERT INTO sub_area(id, name) VALUES (1, 'SubArea1');
INSERT INTO sub_area(id, name) VALUES (101, 'SubArea1');

INSERT INTO sub_area(id, name) VALUES (102, 'SubArea2');

INSERT INTO sub_area(id, name) VALUES (3, 'SubArea3');
INSERT INTO modality(id, name) VALUES (1, 'Adscripcion');


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name,  state_id, modality_id)
VALUES (1, 'Libro1', 'Perfil1', 'Proyecto1', 'Fase1', 'ProyectoGrado1',  null, 1);
INSERT INTO modality(id, name) VALUES (101, 'Adscripcion');
INSERT INTO modality(id, name) VALUES (102, 'Proyecto de Grado');
INSERT INTO modality(id, name) VALUES (103, 'Tesis');


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name,  state_id, modality_id) VALUES (101, 'Libro1', 'Perfil1', 'Proyecto1', 'Fase1', 'ProyectoGrado1',  null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (101, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (101, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id)VALUES (102, 'Libro2', 'Perfil2', 'Proyecto2', 'Fase2', 'ProyectoGrado2', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (102, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (102, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (103, 'Libro3', 'Perfil3', 'Proyecto3', 'Fase3', 'ProyectoGrado3', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (103, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (103, 101);

--

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (104, 'Libro4', 'Perfil4', 'Proyecto4', 'Fase4', 'ProyectoGrado4', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (104, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (104, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (105, 'Libro5', 'Perfil5', 'Proyecto5', 'Fase5', 'ProyectoGrado5', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (105, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (105, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (106, 'Libro6', 'Perfil6', 'Proyecto6', 'Fase6', 'ProyectoGrado6', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (106, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (106, 101);


--

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (107, 'Libro7', 'Perfil7', 'Proyecto7', 'Fase7', 'ProyectoGrado7', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (107, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (107, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (108, 'Libro8', 'Perfil8', 'Proyecto8', 'Fase8', 'ProyectoGrado8', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (108, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (108, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (109, 'Libro9', 'Perfil9', 'Proyecto9', 'Fase9', 'ProyectoGrado9', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (109, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (109, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1010, 'Libro10', 'Perfil10', 'Proyecto10', 'Fase10', 'ProyectoGrado10', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (1010, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1010, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1011, 'Libro11', 'Perfil11', 'Proyecto11', 'Fase11', 'ProyectoGrado11', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (1011, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1011, 101);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1012, 'Libro12', 'Perfil12', 'Proyecto12', 'Fase12', 'ProyectoGrado12',null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (1012, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1012, 101);

INSERT INTO defense(id, date, place_to_defense_id, project_id) VALUES (101, '2000-1-19', 101, 1012);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1013, 'Libro13', 'Perfil13', 'Proyecto13', 'Fase13', 'ProyectoGrado13', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (1013, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1013, 101);

INSERT INTO defense(id, date, place_to_defense_id, project_id) VALUES (102, '2000-1-19', 102, 1013);

--user
INSERT INTO role(id, name) VALUES (101, 'STUDENT');
INSERT INTO role(id, name) VALUES (102, 'ADMIN');
INSERT INTO role(id, name) VALUES (103, 'TEACHER');
INSERT INTO role(id, name) VALUES (104, 'TUTOR');
INSERT INTO role(id, name) VALUES (105, 'SUPERVISOR');
INSERT INTO role(id, name) VALUES (106, 'TRIBUNAL');

INSERT INTO siptis_user(id, email, password) VALUES (101, 'usuario1@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (101, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos1', 'Nombres1',  101);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (101, 102);

INSERT INTO siptis_user(id, email, password) VALUES (102, 'usuario2@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (102, '2000-1-19', '1234568', '1000001', '12345671', 'Apellidos2', 'Nombres2',  102);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (102, 103);

INSERT INTO siptis_user(id, email, password) VALUES (103, 'usuario3@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (103, '2000-1-19', '1234569', '1000002', '12345672', 'Apellidos3', 'Nombres3', 103);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (103, 104);


INSERT INTO siptis_user(id, email, password) VALUES (104, 'usuario4@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (104, '2000-1-19', '1234570', '1000003', '12345673', 'Apellidos4', 'Nombres4',  104);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (104, 102);


INSERT INTO siptis_user(id, email, password) VALUES (105, 'usuario5@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (105, '2000-1-19', '1234571', '1000004', '12345674', 'Apellidos5', 'Nombres5',  105);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (105, 102);


INSERT INTO siptis_user(id, email, password) VALUES (106, 'usuario6@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (106, '2000-1-19', '1234572', '1000005', '12345675', 'Apellidos6', 'Nombres6',  106);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (106, 103);


INSERT INTO siptis_user(id, email, password) VALUES (107, 'usuario7@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (107, '2000-1-19', '1234573', '1000006', '12345676', 'Apellidos7', 'Nombres7',  107);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (107, 104);


INSERT INTO siptis_user(id, email, password) VALUES (108, 'usuario8@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (108, '2000-1-19', '1234574', '1000007', '12345677', 'Apellidos8', 'Nombres8',  108);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (108, 105);


INSERT INTO siptis_user(id, email, password) VALUES (109, 'usuario9@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (109, '2000-1-19', '1234575', '1000008', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O', 'Apellidos9', 'Nombres9',  109);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (109, 102);


INSERT INTO siptis_user(id, email, password) VALUES (1010, 'usuario10@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (1010,'2000-1-19', '1234576', '1000009', '12345679', 'Apellidos10', 'Nombres10',  1010);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1010, 103);


INSERT INTO siptis_user(id, email, password) VALUES (1011, 'usuario11@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (1011,'2000-1-19', '1234577', '1000010', '12345680', 'Apellidos11', 'Nombres11',  1011);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1011, 104);


INSERT INTO siptis_user(id, email, password) VALUES (1012, 'usuario12@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1012,'2000-1-19', '1234578', '1000011', '12345681', 'Apellidos12', 'Nombres12', 1012);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1012, 105);


INSERT INTO siptis_user(id, email, password) VALUES (1013, 'usuario13@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1013,'2000-1-19', '1234579', '1000012', '12345682', 'Apellidos13', 'Nombres13', 1013);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1013, 102);


INSERT INTO siptis_user(id, email, password) VALUES (1014, 'usuario14@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1014,'2000-1-19', '1234580', '1000013', '12345683', 'Apellidos14', 'Nombres14', 1014);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1014, 103);


INSERT INTO siptis_user(id, email, password) VALUES (1015, 'usuario15@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1015,'2000-1-19', '1234581', '1000014', '12345684', 'Apellidos15', 'Nombres15', 1015);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1015, 104);


INSERT INTO siptis_user(id, email, password) VALUES (1016, 'usuario16@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1016,'2000-1-19', '1234582', '1000015', '12345685', 'Apellidos16', 'Nombres16', 1016);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1016, 105);


INSERT INTO siptis_user(id, email, password) VALUES (1017, 'usuario17@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1017,'2000-1-19', '1234583', '1000016', '12345686', 'Apellidos17', 'Nombres17', 1017);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1017, 102);


INSERT INTO siptis_user(id, email, password) VALUES (1018, 'usuario18@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (1018,'2000-1-19', '1234584', '1000017', '12345687', 'Apellidos18', 'Nombres18', 1018);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1018, 103);

-- Estudiantes y revisores

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (101, false, false, 101, 101);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (102, false, true, 102, 101);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (103, true, false, 103, 101);


INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (101, false, false, 104, 102);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (102, false, true, 105, 102);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (103, true, false, 106, 102);


INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (101, false, false, 107, 103);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (102, false, true, 108, 103);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (103, true, false, 109, 103);


--INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1, false, null, false, 10, 4);
--
--INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (2, false, null, true, 11, 4);
--
--INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (3, true, null, false, 12, 4);
--
--INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (4, true, 100, false, 13, 4);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (101, false, null, false, 1010, 104);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (102, false, null, true, 1011, 104);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (103, true, null, false, 1012, 104);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (104, true, 100, false, 1013, 104);


INSERT INTO project_student(id, user_id, project_id) VALUES (101, 105, 101);

INSERT INTO project_student(id, user_id, project_id) VALUES (102, 106, 102);

INSERT INTO project_student(id, user_id, project_id) VALUES (103, 107, 103);

INSERT INTO project_student(id, user_id, project_id) VALUES (104, 108, 104);

INSERT INTO project_student(id, user_id, project_id) VALUES (105, 109, 105);

INSERT INTO project_student(id, user_id, project_id) VALUES (106, 1010, 106);

INSERT INTO project_student(id, user_id, project_id) VALUES (107, 1011, 107);

INSERT INTO project_student(id, user_id, project_id) VALUES (108, 1012, 108);

INSERT INTO project_student(id, user_id, project_id) VALUES (109, 1013, 109);

INSERT INTO project_student(id, user_id, project_id) VALUES (1010, 1014, 1010);

INSERT INTO project_student(id, user_id, project_id) VALUES (1011, 1015, 1011);

INSERT INTO project_student(id, user_id, project_id) VALUES (1012, 1016, 1012);

INSERT INTO project_student(id, user_id, project_id) VALUES (1013, 1017, 1013);



// review projects

INSERT INTO siptis_user(id, email, password) VALUES (30, 'usuarioReview@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (30, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosReview', 'NombresReview', 30);

INSERT INTO siptis_user(id, email, password) VALUES (31, 'estudiante1@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (31, 101);

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (31, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosEstudiante1', 'NombresEstudiante1', 31);

INSERT INTO siptis_user(id, email, password) VALUES (32, 'estudiante2@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (32, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosEstudiante2', 'NombresEstudiante2', 32);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (32, 101);



INSERT INTO modality(id, name) VALUES (1010, 'Modalidad');

INSERT INTO area(id, name) VALUES (1010, 'AreaReview');

INSERT INTO sub_area(id, name) VALUES (1010, 'SubAreaReview');

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (30, 'LibroReview1', null, 'ProyectoReview1', 'FaseReview1', 'ProyectoGradoReview1', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (30, 1010);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (30, 1010);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (30, false, false, 30, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (30, 31, 30);
INSERT INTO project_student(id, user_id, project_id) VALUES (31, 32, 30);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (31, 'LibroReview2', null, 'ProyectoReview2', 'FaseReview2', 'ProyectoGradoReview2', null, 101);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (101, 'blueBookPath1', 'phase1', 'projectPath1', false, 31, '2022-1-1');

INSERT INTO project_area(project_id, area_id) VALUES (31, 1010);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (31, 1010);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (31, false, false, 31, 30);
--INSERT INTO project_student(id, user_id, project_id) VALUES (32, 31, 31);
INSERT INTO project_student(id, user_id, project_id) VALUES (33, 32, 31);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (32, 'LibroReview3', null, 'ProyectoReview3', 'FaseReview3', 'ProyectoGradoReview3', null, 101);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (102, 'blueBookPath2', 'phase2', 'projectPath2', true, 32, '2022-1-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (101, 'commentary1', 'documentPath1', 102, 30, '2022-1-7');

INSERT INTO project_area(project_id, area_id) VALUES (32, 1010);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (32, 1010);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (32,  false, true, 32, 30);
--INSERT INTO project_student(id, user_id, project_id) VALUES (34, 31, 32);
INSERT INTO project_student(id, user_id, project_id) VALUES (35, 32, 32);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (33, 'LibroReview4', null, 'ProyectoReview4', 'FaseReview4', 'ProyectoGradoReview4', null, 101);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (103, 'blueBookPath3', 'phase3', 'projectPath3', true, 33, '2022-2-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (102, 'commentary2', 'documentPath2', 103, 30, '2022-2-5');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (103, 'commentary3', 'documentPath3', 103, 101, '2022-2-5');

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (104, 'blueBookPath4', 'phase4', 'projectPath4', true, 33, '2022-3-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (104, 'commentary4', 'documentPath4', 104, 30, '2022-3-10');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (105, 'commentary5', 'documentPath5', 104, 101, '2022-3-10');

INSERT INTO project_area(project_id, area_id) VALUES (33, 1010);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (33, 1010);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (33,  false, true, 33, 30);
INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (34,  false, true, 33, 101);
--INSERT INTO project_student(id, user_id, project_id) VALUES (36, 31, 33);
INSERT INTO project_student(id, user_id, project_id) VALUES (37, 32, 33);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (34, 'LibroReview5', null, 'ProyectoReview5', 'FaseReview5', 'ProyectoGradoReview5', null, 101);

INSERT INTO presentation (id, blue_book_path, phase, project_path, reviewed, project_id, date) VALUES (105, 'blueBookPath5', 'phase5', 'projectPath5', true, 34, '2022-1-1');

INSERT INTO review(id, commentary, document_path, presentation_id, user_id, date) VALUES (106, 'commentary5', 'documentPath5', 105, 101, '2022-1-7');

INSERT INTO project_area(project_id, area_id) VALUES (34, 1010);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (34, 1010);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (35,  false, true, 34, 30);
--INSERT INTO project_student(id, user_id, project_id) VALUES (38, 31, 34);
INSERT INTO project_student(id, user_id, project_id) VALUES (39, 32, 34);



--//project with all kind of reviewers

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (35, 'Libro', 'Perfil', 'Proyecto', 'Fase', 'ProyectoGrado', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (35, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (35, 101);



INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (42, false, false, 35, 103);


INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (43, false, false, 35, 104);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (44, false, false, 35, 105);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (45, false, false, 35, 106);


INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (46, false, false, 35, 107);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (47, false, false, 35, 108);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (48, false, false, 35, 109);


INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (49, false, null, false, 35, 1010);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (50, false, null, false, 35, 1011);



--cambiar de fase proyectos

INSERT INTO siptis_user(id, email, password) VALUES (50, 'tribunal@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (50, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosTribunal', 'NombresTribunal', 50);
INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (50, 106);

INSERT INTO siptis_user(id, email, password) VALUES (51, 'tutor@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (51, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosTutor', 'NombresTutor', 51);
INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (51, 104);

INSERT INTO siptis_user(id, email, password) VALUES (52, 'supervisor@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (52, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosSupervisor', 'NombresSupervisor', 52);
INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (52, 105);

INSERT INTO siptis_user(id, email, password) VALUES (53, 'docente@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names, user_id) VALUES (53, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosDocente', 'NombresDocente', 53);
INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (53, 103);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (50, 'Libro1', null, 'Proyecto1', 'TRIBUNALS_PHASE', 'ProyectoGrado1', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (60, false, null, false, 50, 50);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (61, false, null, false, 50, 51);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (50, true, false, 50, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (50, true, false, 50, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (50, true, false, 50, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (51, 'Libro2', null, 'Proyecto2', 'TRIBUNALS_PHASE', 'ProyectoGrado2', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (51, false, null, false, 51, 50);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (52, true, null, false, 51, 51);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (53, true, null, false, 51, 52);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (51, false, false, 51, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (51, false, false, 51, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (51, false, false, 51, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (52, 'Libro3', null, 'Proyecto3', 'REVIEWERS_PHASE', 'ProyectoGrado3', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (54, false, null, false, 52, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (52, false, false, 52, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (52, false, false, 52, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (52, false, false, 52, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (53, 'Libro4', null, 'Proyecto4', 'REVIEWERS_PHASE', 'ProyectoGrado4', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (55, true, null, false, 53, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (53, false, false, 53, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (53, true, false, 53, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (53, true, false, 53, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (54, 'Libro5', null, 'Proyecto5', 'REVIEWERS_PHASE', 'ProyectoGrado5', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (56, false, null, false, 54, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (54, false, false, 54, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (54, false, false, 54, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (54, false, false, 54, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (55, 'Libro6', null, 'Proyecto6', 'REVIEWERS_PHASE', 'ProyectoGrado6', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (57, false, null, false, 55, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (55, true, false, 55, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (55, false, false, 55, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (55, true, false, 55, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (56, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (58, false, null, false, 56, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (56, false, false, 56, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (56, false, false, 56, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (56, false, false, 56, 53);



INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id)
VALUES (57, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 1);
INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (57, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 101);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (59, false, null, false, 57, 50);

INSERT INTO project_tutor(id, accepted, reviewed, project_id, user_id) VALUES (57, true, false, 57, 51);

INSERT INTO project_supervisor(id, accepted, reviewed, project_id, user_id) VALUES (57, true, false, 57, 52);

INSERT INTO project_teacher(id, accepted, reviewed, project_id, user_id) VALUES (57, false, false, 57, 53);
// General Activities
INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (109, 'Actividad General 1', 'Descripcion 1', '2024-4-15');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (108, 'Actividad General 2', 'Descripcion 2', '2024-7-2');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (107, 'Actividad General 3', 'Descripcion 3', '2024-7-3');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (106, 'Actividad General 4', 'Descripcion 4', '2024-7-4');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (105, 'Actividad General 5', 'Descripcion 5', '2024-7-5');

INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (1010, 'Actividad General 5', 'Descripcion 5', '2023-7-5');
INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (1011, 'Actividad General 5', 'Descripcion 5', '2023-7-5');
INSERT INTO general_activity(id, activity_name, activity_description, activity_date) VALUES (1012, 'Actividad General 5', 'Descripcion 5', '2023-7-5');


INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (101, 'Actividad 1', 'Descripcion 1', '2024-8-1', 30);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (102, 'Actividad 2', 'Descripcion 2', '2024-8-2', 31);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (103, 'Actividad 3', 'Descripcion 3', '2024-8-3', 32);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (104, 'Actividad 4', 'Descripcion 4', '2024-8-4', 33);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (105, 'Actividad 5', 'Descripcion 5', '2024-8-5', 34);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (106, 'Actividad 6', 'Descripcion 6', '2024-8-6', 30);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (107, 'Actividad 7', 'Descripcion 7', '2024-8-7', 31);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (108, 'Actividad 8', 'Descripcion 8', '2024-8-8', 32);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (109, 'Actividad 9', 'Descripcion 9', '2024-8-9', 33);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (11010, 'Actividad 10', 'Descripcion 10', '2024-8-10', 34);
INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (11011, 'Actividad 10', 'Descripcion 11', '2024-8-10', 35);

INSERT INTO activity (id, activity_name, activity_description, activity_date, project_id) VALUES (1012, 'Actividad 10', 'Descripcion 12', '2024-8-10', 30);





---- get schedule info

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id)
VALUES (100, 'Libro100', 'Perfil100', 'Proyecto100', 'Fase100', 'ProyectoGrado100', null, 1);
INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (100, 'Libro100', 'Perfil100', 'Proyecto100', 'Fase100', 'ProyectoGrado100', null, 101);

INSERT INTO project_area(project_id, area_id) VALUES (100, 101);

INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (100, 101);


INSERT INTO siptis_user(id, email, password) VALUES (200, 'usuario100@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (200, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos100', 'Nombres100',  200);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (200, 102);


INSERT INTO siptis_user(id, email, password) VALUES (201, 'usuario101@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (201, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos101', 'Nombres101',  201);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (201, 102);


INSERT INTO siptis_user(id, email, password) VALUES (202, 'usuario102@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (202, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos102', 'Nombres102',  202);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (202, 102);



INSERT INTO siptis_user(id, email, password) VALUES (203, 'usuario103@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (203, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos103', 'Nombres103',  203);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (203, 106);



INSERT INTO siptis_user(id, email, password) VALUES (204, 'usuario104@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (204, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos104', 'Nombres104',  204);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (204, 106);




INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (200, false, null, false, 100, 105);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (201, false, null, false, 100, 101);
INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (202, false, null, false, 100, 102);


INSERT INTO project_student(id, user_id, project_id) VALUES (200, 103, 100);

INSERT INTO project_student(id, user_id, project_id) VALUES (201, 101, 100);


INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (1, 'Lunes', '02:55', '13:31', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (2, 'Lunes', '19:01', '10:44', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (3, 'Lunes', '13:32', '12:25', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (4, 'Lunes', '05:31', '04:49', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (5, 'Lunes', '74:62', '65:43', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (6, 'Lunes', '86:27', '76:54', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (7, 'Lunes', '86:35', '86:65', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (8, 'Lunes', '86:24', '98:68', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (9, 'Lunes', '24:53', '48:38', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (10, 'Lunes', '07:96', '28:85', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (11, 'Lunes', '61:25', '42:98', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (12, 'Lunes', '04:37', '83:81', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (13, 'Lunes', '13:18', '17:07', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (14, 'Lunes', '06:78', '81:38', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (15, 'Lunes', '47:97', '24:34', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (16, 'Lunes', '79:67', '27:49', 100);


INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (17, 'Martes', '62:47', '38:62', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (18, 'Martes', '46:46', '46:31', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (19, 'Martes', '46:79', '80:70', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (20, 'Martes', '64:60', '05:90', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (21, 'Martes', '93:18', '23:19', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (22, 'Martes', '32:86', '29:84', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (23, 'Martes', '12:38', '18:38', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (24, 'Martes', '68:67', '16:67', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (25, 'Martes', '13:14', '32:18', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (26, 'Martes', '67:31', '64:91', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (27, 'Martes', '61:49', '91:34', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (28, 'Martes', '42:64', '13:32', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (29, 'Martes', '36:94', '19:75', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (30, 'Martes', '67:91', '36:47', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (31, 'Martes', '67:92', '19:79', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (32, 'Martes', '91:78', '31:47', 101);


INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (33, 'Miercoles', '13:48', '97:61', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (34, 'Miercoles', '17:97', '63:17', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (35, 'Miercoles', '97:13', '47:63', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (36, 'Miercoles', '57:94', '13:74', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (37, 'Miercoles', '67:13', '17:63', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (38, 'Miercoles', '28:97', '31:64', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (39, 'Miercoles', '67:51', '84:17', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (40, 'Miercoles', '93:28', '19:73', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (41, 'Miercoles', '94:82', '46:37', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (42, 'Miercoles', '49:34', '47:93', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (43, 'Miercoles', '71:98', '28:97', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (44, 'Miercoles', '36:61', '64:82', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (45, 'Miercoles', '96:85', '87:41', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (46, 'Miercoles', '37:94', '28:95', 100);


INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (47, 'Jueves', '34:48', '18:91', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (48, 'Jueves', '61:82', '18:91', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (49, 'Jueves', '82:97', '16:28', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (50, 'Jueves', '79:81', '97:58', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (51, 'Jueves', '96:85', '48:28', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (52, 'Jueves', '87:28', '93:94', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (53, 'Jueves', '97:38', '82:19', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (54, 'Jueves', '37:28', '84:58', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (55, 'Jueves', '71:48', '95:47', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (56, 'Jueves', '48:93', '28:84', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (57, 'Jueves', '87:82', '96:94', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (58, 'Jueves', '82:20', '12:14', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (59, 'Jueves', '34:61', '14:50', 103);


INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (60, 'Viernes', '13:02', '80:71', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (61, 'Viernes', '30:10', '20:28', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (62, 'Viernes', '01:47', '82:90', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (63, 'Viernes', '91:27', '80:94', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (64, 'Viernes', '34:91', '28:20', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (65, 'Viernes', '34:80', '74:85', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (66, 'Viernes', '67:69', '69:61', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (67, 'Viernes', '07:50', '34:20', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (68, 'Viernes', '34:20', '34:28', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (69, 'Viernes', '47:85', '67:60', 103);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (70, 'Viernes', '03:24', '82:20', 104);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (71, 'Viernes', '34:80', '17:37', 100);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (72, 'Viernes', '34:82', '80:17', 101);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (73, 'Viernes', '35:80', '47:28', 102);
INSERT INTO schedule(id, day, hour_finish, hour_start, user_id) VALUES (74, 'Viernes', '39:84', '17:80', 103);





INSERT INTO siptis_user(id, email, password) VALUES (1000, 'admin@gmail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

INSERT INTO user_information(id, birth_date, cel_number, ci, codsis, lastnames, names,  user_id) VALUES (1000, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos103', 'Nombres103',  1000);

INSERT INTO siptis_user_role(siptis_user_id, role_id) VALUES (1000, 1);


INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1000, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'DEFENSE_PHASE', 'ProyectoGrado1000', null, 1);

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1001, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'DEFENSE_PHASE', 'ProyectoGrado1000', null, 1);

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1002, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'DEFENSE_PHASE', 'ProyectoGrado1000', null, 1);

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1003, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'DEFENSE_PHASE', 'ProyectoGrado1000', null, 1);

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1004, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'TRIBUNALS_PHASE', 'Proyecto sin tribunales', null, 1);

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1005, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'TRIBUNALS_PHASE', 'ProyectoGrado1000', null, 1);

INSERT INTO project(id, blue_book_path, perfil_path, project_path, phase, name, state_id, modality_id) VALUES (1006, 'Libro1000', 'Perfil1000', 'Proyecto1000', 'TRIBUNALS_PHASE', 'ProyectoGrado1000', null, 1);


INSERT INTO project_student(id, user_id, project_id) VALUES (1000, 30, 1004);
INSERT INTO project_student(id, user_id, project_id) VALUES (1001, 31, 1004);
INSERT INTO project_area(project_id, area_id) VALUES (1004, 1);
INSERT INTO project_area(project_id, area_id) VALUES (1004, 2);
INSERT INTO project_area(project_id, area_id) VALUES (1004, 3);
INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1004, 1);
INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1004, 2);
INSERT INTO project_sub_area(project_id, sub_area_id) VALUES (1004, 3);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1000, false, null, false, 1000, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1001, false, null, false, 1000, 2);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1002, false, null, false, 1000, 3);



INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1003, false, null, false, 1001, 6);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1004, false, null, false, 1001, 5);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1005, false, null, false, 1001, 4);


INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1006, false, null, false, 1002, 6);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1007, false, null, false, 1002, 5);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1008, false, null, false, 1002, 4);



INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1009, false, null, false, 1003, 1);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1010, false, null, false, 1003, 2);

INSERT INTO project_tribunal(id, accepted, defense_points, reviewed, project_id, user_id) VALUES (1011, false, null, false, 1003, 3);
