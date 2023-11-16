-- Proyecto

insert into place_to_defense_(id_, capacity_, location_, name_) values (101, 20, 'Departamento informatica-sistemas', 'Auditorio');
insert into place_to_defense_(id_, capacity_, location_, name_) values (102, 200, 'Edificio nuevo', 'Auditorio');
insert into place_to_defense_(id_, capacity_, location_, name_) values (103, 50, 'Edificio nuevo', 'Aula 692B');

insert into area_(id_, name_) values (101, 'Area1');

insert into area_(id_, name_) values (102, 'Area2');

insert into sub_area_(id_, name_) values (101, 'SubArea1');

insert into sub_area_(id_, name_) values (102, 'SubArea2');

insert into modality_(id_, name_) values (101, 'Adscripcion');
insert into modality_(id_, name_) values (102, 'Proyecto de Grado');
insert into modality_(id_, name_) values (103, 'Tesis');


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_,  state_id_, modality_id_) values (101, 'Libro1', 'Perfil1', 'Proyecto1', 'Fase1', 'ProyectoGrado1',  null, 101);

insert into project_area_(project_id_, area_id_) values (101, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (101, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_)values (102, 'Libro2', 'Perfil2', 'Proyecto2', 'Fase2', 'ProyectoGrado2', null, 101);

insert into project_area_(project_id_, area_id_) values (102, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (102, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (103, 'Libro3', 'Perfil3', 'Proyecto3', 'Fase3', 'ProyectoGrado3', null, 101);

insert into project_area_(project_id_, area_id_) values (103, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (103, 101);

--

insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (104, 'Libro4', 'Perfil4', 'Proyecto4', 'Fase4', 'ProyectoGrado4', null, 101);

insert into project_area_(project_id_, area_id_) values (104, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (104, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (105, 'Libro5', 'Perfil5', 'Proyecto5', 'Fase5', 'ProyectoGrado5', null, 101);

insert into project_area_(project_id_, area_id_) values (105, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (105, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (106, 'Libro6', 'Perfil6', 'Proyecto6', 'Fase6', 'ProyectoGrado6', null, 101);

insert into project_area_(project_id_, area_id_) values (106, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (106, 101);


--

insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (107, 'Libro7', 'Perfil7', 'Proyecto7', 'Fase7', 'ProyectoGrado7', null, 101);

insert into project_area_(project_id_, area_id_) values (107, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (107, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (108, 'Libro8', 'Perfil8', 'Proyecto8', 'Fase8', 'ProyectoGrado8', null, 101);

insert into project_area_(project_id_, area_id_) values (108, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (108, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (109, 'Libro9', 'Perfil9', 'Proyecto9', 'Fase9', 'ProyectoGrado9', null, 101);

insert into project_area_(project_id_, area_id_) values (109, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (109, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (1010, 'Libro10', 'Perfil10', 'Proyecto10', 'Fase10', 'ProyectoGrado10', null, 101);

insert into project_area_(project_id_, area_id_) values (1010, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (1010, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (1011, 'Libro11', 'Perfil11', 'Proyecto11', 'Fase11', 'ProyectoGrado11', null, 101);

insert into project_area_(project_id_, area_id_) values (1011, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (1011, 101);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (1012, 'Libro12', 'Perfil12', 'Proyecto12', 'Fase12', 'ProyectoGrado12',null, 101);

insert into project_area_(project_id_, area_id_) values (1012, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (1012, 101);

insert into defense_(id_, date_, place_to_defense_id_, project_id_) values (101, '2000-1-19', 101, 1012);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (1013, 'Libro13', 'Perfil13', 'Proyecto13', 'Fase13', 'ProyectoGrado13', null, 101);

insert into project_area_(project_id_, area_id_) values (1013, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (1013, 101);

insert into defense_(id_, date_, place_to_defense_id_, project_id_) values (102, '2000-1-19', 102, 1013);

--user
insert into role_(id_, name_) values (101, 'STUDENT');
insert into role_(id_, name_) values (102, 'ADMIN');
insert into role_(id_, name_) values (103, 'TEACHER');
insert into role_(id_, name_) values (104, 'TUTOR');
insert into role_(id_, name_) values (105, 'SUPERVISOR');
insert into role_(id_, name_) values (106, 'TRIBUNAL');

insert into siptis_user_(id_, email_, password_) values (101, 'usuario1@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (101, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos1', 'Nombres1',  101);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (101, 102);

insert into siptis_user_(id_, email_, password_) values (102, 'usuario2@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (102, '2000-1-19', '1234568', '1000001', '12345671', 'Apellidos2', 'Nombres2',  102);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (102, 103);

insert into siptis_user_(id_, email_, password_) values (103, 'tutor2@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (103, '2000-1-19', '1234569', '1000002', '12345672', 'Apellidos3', 'Nombres3', 103);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (103, 104);
insert into siptis_user_role_(siptis_user_id_, role_id_) values (103, 103);


insert into siptis_user_(id_, email_, password_) values (104, 'usuario4@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (104, '2000-1-19', '1234570', '1000003', '12345673', 'Apellidos4', 'Nombres4',  104);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (104, 102);


insert into siptis_user_(id_, email_, password_) values (105, 'usuario5@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (105, '2000-1-19', '1234571', '1000004', '12345674', 'Apellidos5', 'Nombres5',  105);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (105, 102);


insert into siptis_user_(id_, email_, password_) values (106, 'usuario6@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (106, '2000-1-19', '1234572', '1000005', '12345675', 'Apellidos6', 'Nombres6',  106);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (106, 103);


insert into siptis_user_(id_, email_, password_) values (107, 'usuario7@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (107, '2000-1-19', '1234573', '1000006', '12345676', 'Apellidos7', 'Nombres7',  107);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (107, 104);


insert into siptis_user_(id_, email_, password_) values (108, 'usuario8@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (108, '2000-1-19', '1234574', '1000007', '12345677', 'Apellidos8', 'Nombres8',  108);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (108, 105);


insert into siptis_user_(id_, email_, password_) values (109, 'usuario9@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (109, '2000-1-19', '1234575', '1000008', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O', 'Apellidos9', 'Nombres9',  109);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (109, 102);


insert into siptis_user_(id_, email_, password_) values (1010, 'usuario10@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (1010,'2000-1-19', '1234576', '1000009', '12345679', 'Apellidos10', 'Nombres10',  1010);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1010, 103);


insert into siptis_user_(id_, email_, password_) values (1011, 'usuario11@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (1011,'2000-1-19', '1234577', '1000010', '12345680', 'Apellidos11', 'Nombres11',  1011);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1011, 104);


insert into siptis_user_(id_, email_, password_) values (1012, 'usuario12@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1012,'2000-1-19', '1234578', '1000011', '12345681', 'Apellidos12', 'Nombres12', 1012);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1012, 105);


insert into siptis_user_(id_, email_, password_) values (1013, 'usuario13@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1013,'2000-1-19', '1234579', '1000012', '12345682', 'Apellidos13', 'Nombres13', 1013);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1013, 102);


insert into siptis_user_(id_, email_, password_) values (1014, 'usuario14@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1014,'2000-1-19', '1234580', '1000013', '12345683', 'Apellidos14', 'Nombres14', 1014);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1014, 103);


insert into siptis_user_(id_, email_, password_) values (1015, 'usuario15@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1015,'2000-1-19', '1234581', '1000014', '12345684', 'Apellidos15', 'Nombres15', 1015);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1015, 104);


insert into siptis_user_(id_, email_, password_) values (1016, 'usuario16@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1016,'2000-1-19', '1234582', '1000015', '12345685', 'Apellidos16', 'Nombres16', 1016);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1016, 105);


insert into siptis_user_(id_, email_, password_) values (1017, 'usuario17@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1017,'2000-1-19', '1234583', '1000016', '12345686', 'Apellidos17', 'Nombres17', 1017);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1017, 102);


insert into siptis_user_(id_, email_, password_) values (1018, 'usuario18@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (1018,'2000-1-19', '1234584', '1000017', '12345687', 'Apellidos18', 'Nombres18', 1018);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (1018, 103);

-- Estudiantes y revisores

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (101, false, false, 101, 101);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (102, false, true, 102, 101);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (103, true, false, 103, 101);


insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (101, false, false, 104, 103);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (102, false, true, 105, 103);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (103, true, false, 106, 103);


insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (101, false, false, 107, 103);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (102, false, true, 108, 103);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (103, true, false, 109, 103);


insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (101, false, null, false, 1010, 104);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (102, false, null, true, 1011, 104);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (103, true, null, false, 1012, 104);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (104, true, 100, false, 1013, 104);


insert into project_student_(id_, user_id_, project_id_) values (101, 105, 101);

insert into project_student_(id_, user_id_, project_id_) values (102, 106, 102);

insert into project_student_(id_, user_id_, project_id_) values (103, 107, 103);

insert into project_student_(id_, user_id_, project_id_) values (104, 108, 104);

insert into project_student_(id_, user_id_, project_id_) values (105, 109, 105);

insert into project_student_(id_, user_id_, project_id_) values (106, 1010, 106);

insert into project_student_(id_, user_id_, project_id_) values (107, 1011, 107);

insert into project_student_(id_, user_id_, project_id_) values (108, 1012, 108);

insert into project_student_(id_, user_id_, project_id_) values (109, 1013, 109);

insert into project_student_(id_, user_id_, project_id_) values (1010, 1014, 1010);

insert into project_student_(id_, user_id_, project_id_) values (1011, 1015, 1011);

insert into project_student_(id_, user_id_, project_id_) values (1012, 1016, 1012);

insert into project_student_(id_, user_id_, project_id_) values (1013, 1017, 1013);



// review projects

insert into siptis_user_(id_, email_, password_) values (30, 'usuarioReview@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (30, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosReview', 'NombresReview', 30);

insert into siptis_user_(id_, email_, password_) values (31, 'estudiante1@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into siptis_user_role_(siptis_user_id_, role_id_) values (31, 101);

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (31, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosEstudiante1', 'NombresEstudiante1', 31);

insert into siptis_user_(id_, email_, password_) values (32, 'estudiante2@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (32, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosEstudiante2', 'NombresEstudiante2', 32);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (32, 101);



insert into modality_(id_, name_) values (1010, 'Modalidad');

insert into area_(id_, name_) values (1010, 'AreaReview');

insert into sub_area_(id_, name_) values (1010, 'SubAreaReview');

insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (30, 'LibroReview1', null, 'ProyectoReview1', 'TRIBUNALS_PHASE', 'ProyectoGradoReview1', null, 101);

insert into project_area_(project_id_, area_id_) values (30, 1010);

insert into project_sub_area_(project_id_, sub_area_id_) values (30, 1010);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (30, false, false, 30, 30);
insert into project_student_(id_, user_id_, project_id_) values (30, 31, 30);
insert into project_student_(id_, user_id_, project_id_) values (31, 32, 30);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (31, 'LibroReview2', null, 'ProyectoReview2', 'FaseReview2', 'ProyectoGradoReview2', null, 101);

insert into presentation_ (id_, blue_book_path_, phase_, project_path_, reviewed_, project_id_, date_) values (101, 'blueBookPath_1', 'phase1', 'projectPath_1', false, 31, '2022-1-1');

insert into project_area_(project_id_, area_id_) values (31, 1010);

insert into project_sub_area_(project_id_, sub_area_id_) values (31, 1010);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (31, false, false, 31, 30);
--INSERT INTO project_student_(id_, user_id_, project_id_) VALUES (32, 31, 31);
insert into project_student_(id_, user_id_, project_id_) values (33, 32, 31);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (32, 'LibroReview3', null, 'ProyectoReview3', 'FaseReview3', 'ProyectoGradoReview3', null, 101);

insert into presentation_ (id_, blue_book_path_, phase_, project_path_, reviewed_, project_id_, date_) values (102, 'blueBookPath_2', 'phase2', 'projectPath_2', true, 32, '2022-1-1');

insert into review_(id_, commentary_, document_path_, presentation_id_, user_id_, date_) values (101, 'commentary1', 'documentPath_1', 102, 30, '2022-1-7');

insert into project_area_(project_id_, area_id_) values (32, 1010);

insert into project_sub_area_(project_id_, sub_area_id_) values (32, 1010);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (32,  false, true, 32, 30);
--INSERT INTO project_student_(id_, user_id_, project_id_) VALUES (34, 31, 32);
insert into project_student_(id_, user_id_, project_id_) values (35, 32, 32);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (33, 'LibroReview4', null, 'ProyectoReview4', 'FaseReview4', 'ProyectoGradoReview4', null, 101);

insert into presentation_ (id_, blue_book_path_, phase_, project_path_, reviewed_, project_id_, date_) values (103, 'blueBookPath_3', 'phase3', 'projectPath_3', true, 33, '2022-2-1');

insert into review_(id_, commentary_, document_path_, presentation_id_, user_id_, date_) values (102, 'commentary2', 'documentPath_2', 103, 30, '2022-2-5');

insert into review_(id_, commentary_, document_path_, presentation_id_, user_id_, date_) values (103, 'commentary3', 'documentPath_3', 103, 101, '2022-2-5');

insert into presentation_ (id_, blue_book_path_, phase_, project_path_, reviewed_, project_id_, date_) values (104, 'blueBookPath_4', 'phase4', 'projectPath_4', true, 33, '2022-3-1');

insert into review_(id_, commentary_, document_path_, presentation_id_, user_id_, date_) values (104, 'commentary4', 'documentPath_4', 104, 30, '2022-3-10');

insert into review_(id_, commentary_, document_path_, presentation_id_, user_id_, date_) values (105, 'commentary5', 'documentPath_5', 104, 101, '2022-3-10');

insert into project_area_(project_id_, area_id_) values (33, 1010);

insert into project_sub_area_(project_id_, sub_area_id_) values (33, 1010);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (33,  false, true, 33, 30);
insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (34,  false, true, 33, 101);
--INSERT INTO project_student_(id_, user_id_, project_id_) VALUES (36, 31, 33);
insert into project_student_(id_, user_id_, project_id_) values (37, 32, 33);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (34, 'LibroReview5', null, 'ProyectoReview5', 'FaseReview5', 'ProyectoGradoReview5', null, 101);

insert into presentation_ (id_, blue_book_path_, phase_, project_path_, reviewed_, project_id_, date_) values (105, 'blueBookPath_5', 'phase5', 'projectPath_5', true, 34, '2022-1-1');

insert into review_(id_, commentary_, document_path_, presentation_id_, user_id_, date_) values (106, 'commentary5', 'documentPath_5', 105, 101, '2022-1-7');

insert into project_area_(project_id_, area_id_) values (34, 1010);

insert into project_sub_area_(project_id_, sub_area_id_) values (34, 1010);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (35,  false, true, 34, 30);
--INSERT INTO project_student_(id_, user_id_, project_id_) VALUES (38, 31, 34);
insert into project_student_(id_, user_id_, project_id_) values (39, 32, 34);



--//project with all kind of reviewers

insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (35, 'Libro', 'Perfil', 'Proyecto', 'Fase', 'ProyectoGrado', null, 101);

insert into project_area_(project_id_, area_id_) values (35, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (35, 101);



insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (42, false, false, 35, 103);


insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (43, false, false, 35, 104);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (44, false, false, 35, 105);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (45, false, false, 35, 106);


insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (46, false, false, 35, 107);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (47, false, false, 35, 108);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (48, false, false, 35, 109);


insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (49, false, null, false, 35, 1010);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (50, false, null, false, 35, 1011);



--cambiar de fase proyectos

insert into siptis_user_(id_, email_, password_) values (50, 'tribunal@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (50, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosTribunal', 'NombresTribunal', 50);
insert into siptis_user_role_(siptis_user_id_, role_id_) values (50, 106);

insert into siptis_user_(id_, email_, password_) values (51, 'tutor@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (51, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosTutor', 'NombresTutor', 51);
insert into siptis_user_role_(siptis_user_id_, role_id_) values (51, 104);

insert into siptis_user_(id_, email_, password_) values (52, 'supervisor@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (52, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosSupervisor', 'NombresSupervisor', 52);
insert into siptis_user_role_(siptis_user_id_, role_id_) values (52, 105);

insert into siptis_user_(id_, email_, password_) values (53, 'docente@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');
insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_, user_id_) values (53, '2000-1-20', '2234568', '2000000', '22345670', 'ApellidosDocente', 'NombresDocente', 53);
insert into siptis_user_role_(siptis_user_id_, role_id_) values (53, 103);


insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (50, 'Libro1', null, 'Proyecto1', 'TRIBUNALS_PHASE', 'ProyectoGrado1', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (60, false, null, false, 50, 50);
insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (61, false, null, false, 50, 51);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (50, true, false, 50, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (50, true, false, 50, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (50, true, false, 50, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (51, 'Libro2', null, 'Proyecto2', 'TRIBUNALS_PHASE', 'ProyectoGrado2', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (51, false, null, false, 51, 50);
insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (52, true, null, false, 51, 51);
insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (53, true, null, false, 51, 52);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (51, false, false, 51, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (51, false, false, 51, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (51, false, false, 51, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (52, 'Libro3', null, 'Proyecto3', 'REVIEWERS_PHASE', 'ProyectoGrado3', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (54, false, null, false, 52, 50);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (52, false, false, 52, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (52, false, false, 52, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (52, false, false, 52, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (53, 'Libro4', null, 'Proyecto4', 'REVIEWERS_PHASE', 'ProyectoGrado4', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (55, true, null, false, 53, 50);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (53, false, false, 53, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (53, true, false, 53, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (53, true, false, 53, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (54, 'Libro5', null, 'Proyecto5', 'REVIEWERS_PHASE', 'ProyectoGrado5', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (56, false, null, false, 54, 50);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (54, false, false, 54, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (54, false, false, 54, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (54, false, false, 54, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (55, 'Libro6', null, 'Proyecto6', 'REVIEWERS_PHASE', 'ProyectoGrado6', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (57, false, null, false, 55, 50);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (55, true, false, 55, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (55, false, false, 55, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (55, true, false, 55, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (56, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (58, false, null, false, 56, 50);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (56, false, false, 56, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (56, false, false, 56, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (56, false, false, 56, 53);



insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (57, 'Libro7', null, 'Proyecto7', 'REVIEWERS_PHASE', 'ProyectoGrado7', null, 101);

insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (59, false, null, false, 57, 50);

insert into project_tutor_(id_, accepted_, reviewed_, project_id_, user_id_) values (57, true, false, 57, 51);

insert into project_supervisor_(id_, accepted_, reviewed_, project_id_, user_id_) values (57, true, false, 57, 52);

insert into project_teacher_(id_, accepted_, reviewed_, project_id_, user_id_) values (57, false, false, 57, 53);
// General Activities
insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (109, 'Actividad General 1', 'Descripcion 1', '2024-4-15');

insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (108, 'Actividad General 2', 'Descripcion 2', '2024-7-2');

insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (107, 'Actividad General 3', 'Descripcion 3', '2024-7-3');

insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (106, 'Actividad General 4', 'Descripcion 4', '2024-7-4');

insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (105, 'Actividad General 5', 'Descripcion 5', '2024-7-5');

insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (1010, 'Actividad General 5', 'Descripcion 5', '2025-7-5');
insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (1011, 'Actividad General 5', 'Descripcion 5', '2025-7-5');
insert into general_activity_(id_, activity_name_, activity_description_, activity_date_) values (1012, 'Actividad General 5', 'Descripcion 5', '2025-7-5');


insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (101, 'Actividad 1', 'Descripcion 1', '2024-8-1', 30);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (102, 'Actividad 2', 'Descripcion 2', '2024-8-2', 31);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (103, 'Actividad 3', 'Descripcion 3', '2024-8-3', 32);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (104, 'Actividad 4', 'Descripcion 4', '2024-8-4', 33);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (105, 'Actividad 5', 'Descripcion 5', '2024-8-5', 34);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (106, 'Actividad 6', 'Descripcion 6', '2024-8-6', 30);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (107, 'Actividad 7', 'Descripcion 7', '2024-8-7', 31);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (108, 'Actividad 8', 'Descripcion 8', '2024-8-8', 32);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (109, 'Actividad 9', 'Descripcion 9', '2024-8-9', 33);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (11010, 'Actividad 10', 'Descripcion 10', '2024-8-10', 34);
insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (11011, 'Actividad 10', 'Descripcion 11', '2024-8-10', 35);

insert into activity_(id_, activity_name_, activity_description_, activity_date_, project_id_) values (1012, 'Actividad 10', 'Descripcion 12', '2024-8-10', 30);





---- get schedule info

insert into project_(id_, blue_book_path_, perfil_path_, project_path_, phase_, name_, state_id_, modality_id_) values (100, 'Libro100', 'Perfil100', 'Proyecto100', 'Fase100', 'ProyectoGrado100', null, 101);

insert into project_area_(project_id_, area_id_) values (100, 101);

insert into project_sub_area_(project_id_, sub_area_id_) values (100, 101);


insert into siptis_user_(id_, email_, password_) values (200, 'usuario100@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (200, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos100', 'Nombres100',  200);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (200, 102);


insert into siptis_user_(id_, email_, password_) values (201, 'usuario101@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (201, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos101', 'Nombres101',  201);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (201, 102);


insert into siptis_user_(id_, email_, password_) values (202, 'usuario102@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (202, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos102', 'Nombres102',  202);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (202, 102);



insert into siptis_user_(id_, email_, password_) values (203, 'usuario103@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (203, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos103', 'Nombres103',  203);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (203, 106);



insert into siptis_user_(id_, email_, password_) values (204, 'usuario104@mail.com', '$2a$10$JZDKlrNbRrGF81sgSC50SuF9NjfAEy80I5iOV4NuVCmWLBWlZIP1O');

insert into user_information_(id_, birth_date_, cell_number_, ci_, cod_sis_, last_names_, names_,  user_id_) values (204, '2000-1-19', '1234567', '1000000', '12345670', 'Apellidos104', 'Nombres104',  204);

insert into siptis_user_role_(siptis_user_id_, role_id_) values (204, 106);




insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (200, false, null, false, 100, 105);
insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (201, false, null, false, 100, 101);
insert into project_tribunal_(id_, accepted_, defense_points_, reviewed_, project_id_, user_id_) values (202, false, null, false, 100, 102);


insert into project_student_(id_, user_id_, project_id_) values (200, 103, 100);

insert into project_student_(id_, user_id_, project_id_) values (201, 101, 100);


insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (101, 'Lunes', '02:55', '13:31', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (102, 'Lunes', '19:01', '10:44', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (103, 'Lunes', '13:32', '12:25', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (104, 'Lunes', '05:31', '04:49', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (105, 'Lunes', '74:62', '65:43', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (106, 'Lunes', '86:27', '76:54', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (107, 'Lunes', '86:35', '86:65', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (108, 'Lunes', '86:24', '98:68', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (109, 'Lunes', '24:53', '48:38', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1010, 'Lunes', '07:96', '28:85', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1011, 'Lunes', '61:25', '42:98', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1012, 'Lunes', '04:37', '83:81', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1013, 'Lunes', '13:18', '17:07', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1014, 'Lunes', '06:78', '81:38', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1015, 'Lunes', '47:97', '24:34', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1016, 'Lunes', '79:67', '27:49', 105);


insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1017, 'Martes', '62:47', '38:62', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1018, 'Martes', '46:46', '46:31', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1019, 'Martes', '46:79', '80:70', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1020, 'Martes', '64:60', '05:90', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1021, 'Martes', '93:18', '23:19', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1022, 'Martes', '32:86', '29:84', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1023, 'Martes', '12:38', '18:38', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1024, 'Martes', '68:67', '16:67', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1025, 'Martes', '13:14', '32:18', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1026, 'Martes', '67:31', '64:91', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1027, 'Martes', '61:49', '91:34', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1028, 'Martes', '42:64', '13:32', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1029, 'Martes', '36:94', '19:75', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1030, 'Martes', '67:91', '36:47', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1031, 'Martes', '67:92', '19:79', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1032, 'Martes', '91:78', '31:47', 101);


insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1033, 'Miercoles', '13:48', '97:61', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1034, 'Miercoles', '17:97', '63:17', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1035, 'Miercoles', '97:13', '47:63', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1036, 'Miercoles', '57:94', '13:74', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1037, 'Miercoles', '67:13', '17:63', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1038, 'Miercoles', '28:97', '31:64', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1039, 'Miercoles', '67:51', '84:17', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1040, 'Miercoles', '93:28', '19:73', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1041, 'Miercoles', '94:82', '46:37', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1042, 'Miercoles', '49:34', '47:93', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1043, 'Miercoles', '71:98', '28:97', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1044, 'Miercoles', '36:61', '64:82', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1045, 'Miercoles', '96:85', '87:41', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1046, 'Miercoles', '37:94', '28:95', 105);


insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1047, 'Jueves', '34:48', '18:91', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1048, 'Jueves', '61:82', '18:91', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1049, 'Jueves', '82:97', '16:28', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1050, 'Jueves', '79:81', '97:58', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1051, 'Jueves', '96:85', '48:28', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1052, 'Jueves', '87:28', '93:94', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1053, 'Jueves', '97:38', '82:19', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1054, 'Jueves', '37:28', '84:58', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1055, 'Jueves', '71:48', '95:47', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1056, 'Jueves', '48:93', '28:84', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1057, 'Jueves', '87:82', '96:94', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1058, 'Jueves', '82:20', '12:14', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1059, 'Jueves', '34:61', '14:50', 103);


insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1060, 'Viernes', '13:02', '80:71', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1061, 'Viernes', '30:10', '20:28', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1062, 'Viernes', '01:47', '82:90', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1063, 'Viernes', '91:27', '80:94', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1064, 'Viernes', '34:91', '28:20', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1065, 'Viernes', '34:80', '74:85', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1066, 'Viernes', '67:69', '69:61', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1067, 'Viernes', '07:50', '34:20', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1068, 'Viernes', '34:20', '34:28', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1069, 'Viernes', '47:85', '67:60', 103);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1070, 'Viernes', '03:24', '82:20', 104);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1071, 'Viernes', '34:80', '17:37', 105);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1072, 'Viernes', '34:82', '80:17', 101);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1073, 'Viernes', '35:80', '47:28', 102);
insert into schedule_(id_, day_, hour_finish_, hour_start_, user_id_) values (1074, 'Viernes', '39:84', '17:80', 103);




insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (101, 'REVIEWERS_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',101, 1 )
insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (102, 'TRIBUNALS_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',101, 2 )
insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (103, 'DEFENSE_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',101, 3 )


insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (104, 'REVIEWERS_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',102, 1 )
insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (105, 'TRIBUNALS_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',102, 2 )
insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (106, 'DEFENSE_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',102, 3)


insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (107, 'REVIEWERS_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',103, 1 )
insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (108, 'TRIBUNALS_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',103, 2 )
insert into phase_(id_, name_, description_phase_short_, description_phase_long_, modality_id_, number_phase_) values (109, 'DEFENSE_PHASE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus ultrices enim sit amet efficitur. Pellentesque hendrerit est at velit auctor maximus. Vivamus ornare eu ex eget feugiat. Duis et volutpat ipsum. Nunc massa nisl, dictum in bibendum ac, ultrices et lectus. Nam ut orci sed ipsum bibendum eleifend. Suspendisse posuere erat vel dapibus dapibus. Integer quam arcu, ultricies sit amet lobortis vitae, imperdiet sit amet odio. Donec non ligula magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum augue eget quam suscipit convallis. Vivamus ut vulputate ex. Ut iaculis nisl quam, at pretium elit viverra sit amet. Nulla ac dictum libero, eu lobortis sem. Donec lorem eros, bibendum eu facilisis ultricies, eleifend vel erat. Donec ornare turpis vitae augue interdum sodales.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at erat feugiat, laoreet justo nec, lobortis turpis. Sed fringilla, quam ut tincidunt eleifend, eros tellus faucibus ligula, at tempus est est eget massa. Cras ut euismod massa, at venenatis tellus. Nullam non interdum sapien. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer venenatis sollicitudin vehicula. Donec iaculis accumsan egestas. Sed ut viverra leo. Vivamus elementum mauris sed diam sodales tempus. Curabitur semper ligula dui. Ut nunc augue, posuere ac tellus vitae, ultricies vulputate tortor. Duis fringilla ultricies risus venenatis sollicitudin. Aliquam sit amet metus ut sapien gravida sagittis. Etiam cursus quis dui consequat congue. Nulla vulputate mi ut risus suscipit sodales et at libero.Mauris condimentum nisl vel massa tincidunt viverra. Suspendisse commodo eget ex quis hendrerit. Praesent ut magna eget urna aliquam ornare sed id nunc. Etiam eget sollicitudin justo. In efficitur est ipsum, nec rutrum libero consectetur eu. Nullam quis nunc quis enim pretium dapibus. In condimentum turpis quis dignissim placerat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras eu dui tempor, semper enim nec, commodo ante. Vivamus vel pharetra nisi, vel mollis enim. Integer fringilla sollicitudin ex, pharetra imperdiet lectus venenatis ac. Fusce tempus maximus arcu vitae sodales. Aenean a viverra massa.Nunc vel risus molestie, imperdiet nunc sed, ultricies arcu. Fusce at elit quis lectus dignissim luctus a eu leo. Nullam sollicitudin a metus nec blandit. Aenean facilisis lectus eget velit dignissim suscipit. Aliquam euismod mauris sollicitudin, dapibus felis nec, interdum velit. Morbi sollicitudin non mi ut accumsan. Vestibulum imperdiet tristique felis in blandit. Proin finibus diam eget consequat fringilla.',103, 3 )






