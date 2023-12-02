-- ROLES
INSERT INTO role_ (id_, name_)
VALUES (100, 'ADMIN'),
       (101, 'STUDENT'),
       (102, 'TEACHER'),
       (103, 'TUTOR'),
       (104, 'SUPERVISOR'),
       (105, 'TRIBUNAL'),
       (106, 'INF_DIRECTOR'),
       (107, 'SIS_DIRECTOR');
------------------------------------------------------------------------------------------------------------------------
-- USER CARRER
INSERT INTO user_career_ (id_, name_)
VALUES (100, 'INFORMATICA'),
       (101, 'SISTEMAS');
------------------------------------------------------------------------------------------------------------------------
-- USER AREA
INSERT INTO user_area_ (id_, name_)
VALUES (100, 'INGENIRIA DE SOFTWARE'),
       (101, 'BASE DE DATOS'),
       (102, 'REDES'),
       (103, 'SEGURIDAD'),
       (104, 'SISTEMAS OPERATIVOS'),
       (105, 'PROGRAMACION'),
       (106, 'INTELIGENCIA ARTIFICIAL'),
       (107, 'ALGORITMIA'),
       (108, 'ROBOTICA'),
       (109, 'DESARROLLO WEB'),
       (110, 'DESARROLLO MOVIL');
------------------------------------------------------------------------------------------------------------------------
-- MODALITY
INSERT INTO modality_ (id_, name_)
VALUES (100, 'PROYECTO_DE_GRADO'),
       (101, 'TESIS'),
       (102, 'TRABAJO_DIRIGIDO'),
       (103, 'ADSCRIPCION');
------------------------------------------------------------------------------------------------------------------------
-- STATE
INSERT INTO state_ (id_, name_)
VALUES (100, 'IN_PROGRESS'),
       (101, 'FINISHED');
------------------------------------------------------------------------------------------------------------------------
-- PROJECT AREA
INSERT INTO area_(id_, name_)
VALUES (100, 'INGENIRIA DE SOFTWARE'),
       (101, 'BASE DE DATOS'),
       (102, 'REDES'),
       (103, 'SEGURIDAD'),
       (104, 'SISTEMAS OPERATIVOS'),
       (105, 'PROGRAMACION'),
       (106, 'INTELIGENCIA ARTIFICIAL'),
       (107, 'ALGORITMIA'),
       (108, 'ROBOTICA'),
       (109, 'DESARROLLO WEB'),
       (110, 'DESARROLLO MOVIL');
------------------------------------------------------------------------------------------------------------------------
-- PROJECT
INSERT INTO project_ (id_, blue_book_path_, name_, perfil_path_, period_, phase_, project_path_, total_defense_points_,
                      modality_id_, state_id_)
VALUES (100, null, 'Sistema Integral del Proceso de Titulacion de Informatica y Sistemas', null, '1-2023',
        'PRE_DEFENSE_PHASE', null, null, 100, 100),
       (101, null, 'Proyecto de Desarrollo de Aplicación Web', null, '2-2023',
        'DEFENSE_PHASE', null, null, 101, 101),
       (102, null, 'Estudio Comparativo de Lenguajes de Programación', null, '1-2023',
        'PRE_DEFENSE_PHASE', null, null, 102, 100),
       (103, null, 'Implementación de Sistemas de Seguridad en Redes', null, '3-2023',
        'ASSIGN_TRIBUNALS_PHASE', null, null, 103, 101),
       (104, null, 'Desarrollo de Software de Gestión Empresarial', null, '2-2023',
        'REVIEWERS_PHASE', null, null, 100, 101),
       (105, null, 'Análisis de Vulnerabilidades en Sistemas Operativos', null, '1-2023',
        'POST_DEFENSE_PHASE', null, null, 101, 101),
       (106, null, 'Diseño de Base de Datos Escalable', null, '3-2023',
        'PRE_DEFENSE_PHASE', null, null, 102, 100),
       (107, null, 'Evaluación de Rendimiento de Algoritmos de Machine Learning', null, '2-2023',
        'ASSIGN_DEFENSE_PHASE', null, null, 103, 101),
       (108, null, 'Desarrollo de Aplicaciones Móviles Innovadoras', null, '1-2023',
        'DEFENSE_PHASE', null, null, 100, 100),
       (109, null, 'Investigación en Inteligencia Artificial', null, '3-2023',
        'REVIEWERS_PHASE', null, null, 101, 101),
       (110, null, 'Desarrollo de Plataforma de E-commerce', null, '2-2023',
        'PRE_DEFENSE_PHASE', null, null, 100, 100),
       (111, null, 'Investigación en Criptografía', null, '3-2023',
        'DEFENSE_PHASE', null, null, 101, 101),
       (112, null, 'Desarrollo de Sistema de Control de Versiones', null, '1-2023',
        'TRIBUNALS_PHASE', null, null, 102, 100),
       (113, null, 'Análisis de Big Data en Salud Pública', null, '3-2023',
        'REVIEWERS_PHASE', null, null, 103, 101),
       (114, null, 'Diseño de Interfaz de Usuario Inteligente', null, '2-2023',
        'POST_DEFENSE_PHASE', null, null, 101, 101),
       (115, null, 'Desarrollo de Sistema de Gestión de Proyectos', null, '1-2023',
        'PRE_DEFENSE_PHASE', null, null, 100, 100),
       (116, null, 'Investigación en Robótica Avanzada', null, '2-2023',
        'ASSIGN_DEFENSE_PHASE', null, null, 103, 101),
       (117, null, 'Desarrollo de Herramientas de Seguridad Informática', null, '1-2023',
        'DEFENSE_PHASE', null, null, 100, 101),
       (118, null, 'Estudio de Blockchain en Aplicaciones Financieras', null, '3-2023',
        'REVIEWERS_PHASE', null, null, 101, 101),
       (119, null, 'Creación de Algoritmos de Optimización', null, '2-2023',
        'POST_DEFENSE_PHASE', null, null, 101, 101);
-- PROJECT AREA
INSERT INTO project_area_ (project_id_, area_id_)
VALUES (100, 100),
       (101, 101),
       (101, 110),
       (102, 105),
       (103, 102),
       (104, 100),
       (105, 104),
       (106, 101),
       (107, 106),
       (108, 110),
       (109, 106),
       (110, 109),
       (110, 101),
       (111, 103),
       (111, 106),
       (112, 100),
       (112, 107),
       (113, 107),
       (113, 105),
       (114, 106),
       (114, 104),
       (115, 100),
       (115, 101),
       (116, 108),
       (116, 107),
       (117, 103),
       (117, 102),
       (118, 104),
       (118, 101),
       (119, 107),
       (119, 105);
------------------------------------------------------------------------------------------------------------------------
-- STUDENTS
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (100, 'dilan.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (101, 'juan.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (102, 'luisa.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (103, 'mariana.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (104, 'clarisa.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (105, 'carlos.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (106, 'laura.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (107, 'javier.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (108, 'rosa.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (109, 'pedro.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (110, 'jorge.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (111, 'marta.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (112, 'andres.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (113, 'sara.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (114, 'lucas.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (190, 'elena.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (191, 'manuel.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (192, 'clara.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (193, 'oscar.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (194, 'natalia.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (195, 'alberto.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (196, 'elisa.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (197, 'lucia.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (198, 'diego.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (199, 'adriana.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (200, 'victor.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (201, 'sofia.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (202, 'roberto.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (203, 'julio.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (204, 'ana.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (205, 'julian.est@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
-- USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (100, '2001-03-20', '79795112', '9364073', '201900520', 'Dilan Antezana', 'Antezana Serrano', 'Dilan', 100),
       (101, '1998-07-15', '71234567', '8123456', '201800123', 'Juan Estévez', 'Estévez García', 'Juan', 101),
       (102, '2000-05-03', '71234568', '9123456', '201700456', 'Luisa Moreno', 'Moreno Pérez', 'Luisa', 102),
       (103, '1999-12-10', '71234569', '7123456', '201600789', 'Mariana Rodríguez', 'Rodríguez López', 'Mariana', 103),
       (104, '2002-08-25', '71234570', '5123456', '201500987', 'Clarisa Gómez', 'Gómez Martínez', 'Ana', 104),
       (105, '2003-04-18', '71234571', '6123456', '201400654', 'Carlos Martínez', 'Martínez González', 'Carlos', 105),
       (106, '1997-09-30', '71234572', '8123457', '201300321', 'Laura Pérez', 'Pérez Hernández', 'Laura', 106),
       (107, '1995-06-27', '71234573', '9123457', '201200654', 'Javier Sánchez', 'Sánchez Díaz', 'Javier', 107),
       (108, '1994-11-11', '71234574', '7123457', '201100987', 'Rosa Díaz', 'Díaz Flores', 'Rosa', 108),
       (109, '1996-02-28', '71234575', '5123457', '201000654', 'Pedro López', 'López Castro', 'Pedro', 109),
       (110, '1990-05-15', '71234567', '1101234', '202200520', 'Jorge Estévez', 'Estévez Martínez', 'Jorge', 110),
       (111, '1988-08-22', '71234568', '1111234', '202100123', 'Marta García', 'García Pérez', 'Marta', 111),
       (112, '1993-04-10', '71234569', '1121234', '202000456', 'Andrés López', 'López Rodríguez', 'Andrés', 112),
       (113, '1991-11-27', '71234570', '1131234', '201900789', 'Sara Pérez', 'Pérez González', 'Sara', 113),
       (114, '1995-06-18', '71234571', '1141234', '201800987', 'Lucas Martínez', 'Martínez Sánchez', 'Lucas', 114),
       (190, '1987-09-05', '71234572', '1151234', '201700654', 'Elena Díaz', 'Díaz López', 'Elena', 190),
       (191, '1992-02-12', '71234573', '1161234', '201600321', 'Manuel Sánchez', 'Sánchez Gómez', 'Manuel', 191),
       (192, '1994-07-30', '71234574', '1171234', '201500654', 'Clara Rodríguez', 'Rodríguez Hernández', 'Clara', 192),
       (193, '1989-12-11', '71234575', '1181234', '201400987', 'Oscar Gómez', 'Gómez Díaz', 'Oscar', 193),
       (194, '1996-10-28', '71234576', '1191234', '201300654', 'Natalia Martínez', 'Martínez Pérez', 'Natalia', 194),
       (195, '1998-03-19', '71234577', '1201234', '201200321', 'Alberto Sánchez', 'Sánchez Rodríguez', 'Alberto', 195),
       (196, '1997-12-01', '71234578', '1211234', '201100654', 'Elisa Díaz', 'Díaz González', 'Elisa', 196),
       (197, '1990-08-09', '71234579', '1221234', '201000987', 'Lucía Rodríguez', 'Rodríguez Martínez', 'Lucía', 197),
       (198, '1993-04-26', '71234580', '1231234', '200900654', 'Diego González', 'González Sánchez', 'Diego', 198),
       (199, '1999-01-14', '71234581', '1241234', '200800987', 'Adriana Martínez', 'Martínez Rodríguez', 'Adriana',
        199),
       (200, '1994-11-03', '71234582', '1251234', '200700654', 'Víctor Pérez', 'Pérez Gómez', 'Víctor', 200),
       (201, '1991-06-20', '71234583', '1261234', '200600321', 'Sofía Gómez', 'Gómez Sánchez', 'Sofía', 201),
       (202, '1996-09-17', '71234584', '1271234', '200500654', 'Roberto Díaz', 'Díaz Rodríguez', 'Roberto', 202),
       (203, '1998-05-28', '71234585', '1281234', '200400987', 'Julio Rodríguez', 'Rodríguez Pérez', 'Julio', 203),
       (204, '1992-12-07', '71234586', '1291234', '200300654', 'Ana Gómez', 'Gómez Martínez', 'Ana', 204),
       (205, '1990-04-25', '71234587', '1301234', '200200321', 'Julián Pérez', 'Pérez Sánchez', 'Julián', 205);
-- SIPTIS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES (100, 101),
       (101, 101),
       (102, 101),
       (103, 101),
       (104, 101),
       (105, 101),
       (106, 101),
       (107, 101),
       (108, 101),
       (109, 101),
       (110, 101),
       (111, 101),
       (112, 101),
       (113, 101),
       (114, 101),
       (190, 101),
       (191, 101),
       (192, 101),
       (193, 101),
       (194, 101),
       (195, 101),
       (196, 101),
       (197, 101),
       (198, 101),
       (199, 101),
       (200, 101),
       (201, 101),
       (202, 101),
       (203, 101),
       (204, 101),
       (205, 101);
-- SIPTIS USER AREA
INSERT INTO siptis_user_area_ (siptis_user_id_, area_id_)
VALUES (100, 100),
       (101, 101),
       (102, 102),
       (103, 103),
       (104, 104),
       (105, 105),
       (106, 106),
       (107, 107),
       (108, 108),
       (109, 109),
       (110, 100),
       (111, 101),
       (112, 102),
       (113, 103),
       (114, 104),
       (190, 105),
       (191, 106),
       (192, 107),
       (193, 108),
       (194, 109),
       (195, 110),
       (196, 100),
       (197, 101),
       (198, 102),
       (199, 103),
       (200, 104),
       (201, 105),
       (202, 106),
       (203, 107),
       (204, 108),
       (205, 109);
-- PROJECT STUDENT
INSERT INTO project_student_ (id_, project_id_, user_id_)
VALUES (100, 112, 100),
       (101, 100, 101),
       (102, 109, 102),
       (103, 114, 103),
       (104, 110, 104),
       (105, 110, 105),
       (106, 101, 106),
       (107, 119, 107),
       (108, 101, 108),
       (109, 102, 109),
       (110, 113, 110),
       (111, 102, 111),
       (112, 103, 112),
       (113, 118, 113),
       (114, 103, 114),
       (115, 104, 190),
       (116, 104, 191),
       (117, 111, 192),
       (118, 111, 193),
       (119, 105, 194),
       (120, 105, 195),
       (121, 106, 196),
       (122, 115, 197),
       (123, 106, 198),
       (124, 107, 199),
       (125, 116, 200),
       (126, 107, 201),
       (127, 108, 202),
       (128, 117, 203),
       (129, 108, 204),
       (130, 108, 205);
------------------------------------------------------------------------------------------------------------------------
-- ADMINS
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (115, 'carolina.admin@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (116, 'carlos.admin@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (117, 'laura.admin@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (118, 'javier.admin@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (119, 'sofia.admin@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
-- USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (115, '1990-05-15', '789456123', '1234567', '202200115', 'Carolina Perez Serrano', 'Perez Serrano', 'Carolina',
        115),
       (116, '1985-09-28', '654789321', '9876543', '202200116', 'Carlos Gomez Fernandez', 'Gomez Fernandez', 'Carlos',
        116),
       (117, '1988-11-03', '963258741', '4567890', '202200117', 'Laura Rodriguez Lopez', 'Rodriguez Lopez', 'Laura',
        117),
       (118, '1992-07-20', '741852963', '3456789', '202200118', 'Javier Martinez Torres', 'Martinez Torres', 'Javier',
        118),
       (119, '1995-04-12', '852963147', '2345678', '202200119', 'Sofia Hernandez Garcia', 'Hernandez Garcia', 'Sofia',
        119);
-- SIPTIS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES (115, 100),
       (116, 100),
       (117, 100),
       (118, 100),
       (119, 100);
------------------------------------------------------------------------------------------------------------------------
-- TEACHERS
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (120, 'luis.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (121, 'mariana.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (122, 'roberto.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (123, 'valeria.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (124, 'sergio.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (125, 'lucia.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (126, 'natalia.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (127, 'alejandro.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (128, 'claudia.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (129, 'eduardo.teach@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
-- USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (120, '1983-07-25', '785214369', '7890123', '202200120', 'Luis Gonzales Soto', 'Gonzales Soto', 'Luis', 120),
       (121, '1990-11-14', '632145987', '4567890', '202200121', 'Mariana Ramirez Vargas', 'Ramirez Vargas', 'Mariana',
        121),
       (122, '1979-03-02', '987412365', '2345678', '202200122', 'Roberto Fernandez Diaz', 'Fernandez Diaz', 'Roberto',
        122),
       (123, '1985-01-30', '741258963', '8901234', '202200123', 'Valeria Martinez Castillo', 'Martinez Castillo',
        'Valeria', 123),
       (124, '1993-09-18', '896321457', '6789012', '202200124', 'Sergio Lopez Moreno', 'Lopez Moreno', 'Sergio', 124),
       (125, '1988-05-06', '654789321', '3456789', '202200125', 'Lucia Gomez Paredes', 'Gomez Paredes', 'Lucia', 125),
       (126, '1997-12-22', '123456789', '0123456', '202200126', 'Natalia Castro Morales', 'Castro Morales', 'Natalia',
        126),
       (127, '1980-08-10', '987654321', '5678901', '202200127', 'Alejandro Silva Jimenez', 'Silva Jimenez', 'Alejandro',
        127),
       (128, '1991-06-28', '456789123', '9012345', '202200128', 'Claudia Perez Gutierrez', 'Perez Gutierrez', 'Claudia',
        128),
       (129, '1987-04-15', '987654321', '6789012', '202200129', 'Eduardo Torres Mendez', 'Torres Mendez', 'Eduardo',
        129);
-- SIPTIS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES (120, 102),
       (121, 102),
       (122, 102),
       (123, 102),
       (124, 102),
       (125, 102),
       (126, 102),
       (127, 102),
       (128, 102),
       (129, 102);
-- SIPTIS USER AREA
INSERT INTO siptis_user_area_ (siptis_user_id_, area_id_)
VALUES (120, 100),
       (121, 101),
       (122, 102),
       (123, 103),
       (124, 110),
       (125, 105),
       (126, 106),
       (127, 107),
       (128, 108),
       (129, 109);
-- PROJECT TEACHER
INSERT INTO project_teacher_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (100, FALSE, FALSE, 100, 120),
       (101, TRUE, FALSE, 101, 120),
       (102, FALSE, TRUE, 102, 121),
       (103, TRUE, FALSE, 103, 122),
       (104, FALSE, TRUE, 104, 123),
       (105, TRUE, FALSE, 105, 124),
       (106, TRUE, FALSE, 106, 125),
       (107, FALSE, TRUE, 107, 126),
       (108, TRUE, FALSE, 108, 127),
       (109, FALSE, TRUE, 109, 128),
       (110, TRUE, FALSE, 110, 129),
       (111, TRUE, FALSE, 111, 120),
       (112, FALSE, TRUE, 112, 121),
       (113, TRUE, FALSE, 113, 122),
       (114, FALSE, TRUE, 114, 123),
       (115, TRUE, FALSE, 115, 124),
       (116, FALSE, TRUE, 116, 125),
       (117, TRUE, FALSE, 117, 126),
       (118, FALSE, TRUE, 118, 127),
       (119, TRUE, FALSE, 119, 128);
------------------------------------------------------------------------------------------------------------------------
-- TUTORS
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (130, 'andres.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (131, 'patricia.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (132, 'manuel.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (133, 'marcela.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (134, 'jose.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (135, 'andrea.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (136, 'francisco.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (137, 'monica.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (138, 'carolina.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (139, 'oscar.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
-- USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (130, '1982-04-28', '789654123', '1237890', '202200130', 'Andres Flores Soto', 'Flores Soto', 'Andres', 130),
       (131, '1995-11-09', '321987654', '4560123', '202200131', 'Patricia Herrera Vargas', 'Herrera Vargas', 'Patricia',
        131),
       (132, '1989-02-15', '987654321', '7890123', '202200132', 'Manuel Ramirez Diaz', 'Ramirez Diaz', 'Manuel', 132),
       (133, '1990-08-23', '456321789', '0123456', '202200133', 'Marcela Gomez Lopez', 'Gomez Lopez', 'Marcela', 133),
       (134, '1987-06-14', '789654123', '2345678', '202200134', 'Jose Martinez Paredes', 'Martinez Paredes', 'Jose',
        134),
       (135, '1993-03-30', '654987321', '8901234', '202200135', 'Andrea Castro Morales', 'Castro Morales', 'Andrea',
        135),
       (136, '1980-09-02', '147258369', '3456789', '202200136', 'Francisco Silva Jimenez', 'Silva Jimenez', 'Francisco',
        136),
       (137, '1998-12-11', '987456321', '5678901', '202200137', 'Monica Perez Gutierrez', 'Perez Gutierrez', 'Monica',
        137),
       (138, '1985-07-20', '654321987', '9012345', '202200138', 'Carolina Torres Mendez', 'Torres Mendez', 'Carolina',
        138),
       (139, '1991-05-05', '987654321', '6789012', '202200139', 'Oscar Lopez Fernandez', 'Lopez Fernandez', 'Oscar',
        139);
-- SIPTIS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES (130, 103),
       (131, 103),
       (132, 103),
       (133, 103),
       (134, 103),
       (135, 103),
       (136, 103),
       (137, 103),
       (138, 103),
       (139, 103);
-- SIPTIS USER AREA
INSERT INTO siptis_user_area_ (siptis_user_id_, area_id_)
VALUES (130, 100),
       (131, 101),
       (132, 102),
       (133, 103),
       (134, 104),
       (135, 105),
       (136, 106),
       (137, 107),
       (138, 108),
       (139, 109);
-- PROJECT TUTOR
    INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (101, TRUE, FALSE, 101, 131),
       (102, FALSE, TRUE, 102, 132),
       (103, TRUE, FALSE, 103, 133),
       (104, FALSE, TRUE, 104, 134),
       (105, TRUE, FALSE, 105, 135),
       (106, FALSE, TRUE, 106, 136),
       (107, TRUE, FALSE, 107, 137),
       (108, FALSE, TRUE, 108, 138),
       (109, TRUE, FALSE, 109, 139),
       (110, TRUE, FALSE, 110, 130),
       (111, FALSE, TRUE, 111, 131),
       (112, TRUE, FALSE, 112, 132),
       (113, FALSE, TRUE, 113, 133),
       (114, TRUE, FALSE, 114, 134),
       (115, FALSE, TRUE, 115, 135),
       (116, TRUE, FALSE, 116, 136),
       (117, FALSE, TRUE, 117, 137),
       (118, TRUE, FALSE, 118, 138),
       (119, FALSE, TRUE, 119, 139);
------------------------------------------------------------------------------------------------------------------------
-- SUPERVISORS
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (140, 'ana.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (141, 'jorge.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (142, 'carla.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (143, 'daniel.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (144, 'lucas.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (145, 'clara.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (146, 'felipe.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (147, 'sofia.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (148, 'laura.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (149, 'juan.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
-- USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (140, '1990-03-15', '789456123', '1234567', '202200140', 'Ana Herrera Serrano', 'Herrera Serrano', 'Ana', 140),
       (141, '1985-08-28', '654789321', '9876543', '202200141', 'Jorge Ramirez Gomez', 'Ramirez Gomez', 'Jorge', 141),
       (142, '1988-02-03', '963258741', '4567890', '202200142', 'Carla Fernandez Lopez', 'Fernandez Lopez', 'Carla',
        142),
       (143, '1992-01-30', '741852963', '3456789', '202200143', 'Daniel Martinez Torres', 'Martinez Torres', 'Daniel',
        143),
       (144, '1995-09-12', '852963147', '2345678', '202200144', 'Lucas Gomez Rodriguez', 'Gomez Rodriguez', 'Lucas',
        144),
       (145, '1988-05-30', '654321987', '1234567', '202200145', 'Clara Perez Hernandez', 'Perez Hernandez', 'Clara',
        145),
       (146, '1997-12-22', '123456789', '0123456', '202200146', 'Felipe Castro Gomez', 'Castro Gomez', 'Felipe', 146),
       (147, '1980-08-10', '987654321', '5678901', '202200147', 'Sofia Silva Jimenez', 'Silva Jimenez', 'Sofia', 147),
       (148, '1991-06-28', '456789123', '9012345', '202200148', 'Laura Torres Mendez', 'Torres Mendez', 'Laura', 148),
       (149, '1987-04-15', '987654321', '6789012', '202200149', 'Juan Lopez Fernandez', 'Lopez Fernandez', 'Juan', 149);
-- SIPTIS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES (140, 104),
       (141, 104),
       (142, 104),
       (143, 104),
       (144, 104),
       (145, 104),
       (146, 104),
       (147, 104),
       (148, 104),
       (149, 104);
-- SIPTIS USER AREA
INSERT INTO siptis_user_area_ (siptis_user_id_, area_id_)
VALUES (140, 100),
       (141, 101),
       (142, 102),
       (143, 103),
       (144, 104),
       (145, 105),
       (146, 106),
       (147, 107),
       (148, 108),
       (149, 109);
-- PROJECT SUPERVISOR
INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (101, TRUE, FALSE, 102, 140),
       (102, FALSE, TRUE, 103, 141),
       (103, TRUE, FALSE, 106, 142),
       (104, FALSE, TRUE, 107, 143),
       (105, TRUE, FALSE, 112, 144),
       (106, FALSE, TRUE, 113, 145);
------------------------------------------------------------------------------------------------------------------------
-- TRIBUNALS
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (150, 'roberto.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (151, 'valentina.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (152, 'oscar.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (153, 'luciana.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (154, 'martin.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (155, 'camila.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (156, 'facundo.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (157, 'ana.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (158, 'lucas.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (159, 'maria.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
--USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (150, '1986-11-20', '789456123', '1234567', '202200150', 'Roberto Gonzalez Soto', 'Gonzalez Soto', 'Roberto',
        150),
       (151, '1993-05-14', '632145987', '4567890', '202200151', 'Valentina Ramirez Vargas', 'Ramirez Vargas',
        'Valentina', 151),
       (152, '1987-08-30', '987654321', '7890123', '202200152', 'Oscar Fernandez Diaz', 'Fernandez Diaz', 'Oscar', 152),
       (153, '1990-03-25', '741852963', '8901234', '202200153', 'Luciana Martinez Castillo', 'Martinez Castillo',
        'Luciana', 153),
       (154, '1995-09-18', '896321457', '6789012', '202200154', 'Martin Lopez Moreno', 'Lopez Moreno', 'Martin', 154),
       (155, '1990-06-06', '654789321', '3456789', '202200155', 'Camila Gomez Paredes', 'Gomez Paredes', 'Camila', 155),
       (156, '1997-12-22', '123456789', '0123456', '202200156', 'Facundo Castro Morales', 'Castro Morales', 'Facundo',
        156),
       (157, '1982-08-10', '987654321', '5678901', '202200157', 'Ana Silva Jimenez', 'Silva Jimenez', 'Ana', 157),
       (158, '1991-06-28', '456789123', '9012345', '202200158', 'Lucas Torres Mendez', 'Torres Mendez', 'Lucas', 158),
       (159, '1987-04-15', '987654321', '6789012', '202200159', 'Maria Lopez Fernandez', 'Lopez Fernandez', 'Maria',
        159);
-- SIPTIS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES (150, 105),
       (151, 105),
       (152, 105),
       (153, 105),
       (154, 105),
       (155, 105),
       (156, 105),
       (157, 105),
       (158, 105),
       (159, 105);
-- SIPITS USER AREA
INSERT INTO siptis_user_area_ (siptis_user_id_, area_id_)
VALUES (151, 101),
       (151, 103),
       (152, 102),
       (152, 107),
       (153, 100),
       (153, 110),
       (154, 104),
       (154, 108),
       (155, 105),
       (155, 109),
       (156, 106),
       (156, 110),
       (157, 107),
       (157, 108),
       (158, 109),
       (158, 110),
       (159, 100),
       (159, 105);
-- PROJECT TRIBUNAL
INSERT INTO project_tribunal_ (id_, accepted_, defense_points_, reviewed_, project_id_, user_id_)
VALUES (101, FALSE, NULL, FALSE, 103, 150),
       (102, TRUE, NULL, TRUE, 103, 151),
       (103, FALSE, NULL, FALSE, 103, 152),
       (104, TRUE, NULL, TRUE, 105, 153),
       (105, FALSE, NULL, FALSE, 105, 154),
       (106, TRUE, NULL, TRUE, 105, 155),
       (107, FALSE, NULL, FALSE, 106, 156),
       (108, TRUE, NULL, TRUE, 106, 157),
       (109, FALSE, NULL, FALSE, 106, 158),
       (110, TRUE, NULL, TRUE, 107, 159),
       (111, FALSE, NULL, FALSE, 107, 150),
       (112, TRUE, NULL, TRUE, 107, 151),
       (113, FALSE, NULL, FALSE, 110, 152),
       (114, TRUE, NULL, TRUE, 110, 153),
       (115, FALSE, NULL, FALSE, 110, 154),
       (116, TRUE, NULL, TRUE, 112, 155),
       (117, FALSE, NULL, FALSE, 112, 156),
       (118, TRUE, NULL, TRUE, 112, 157),
       (119, FALSE, NULL, FALSE, 113, 158),
       (120, TRUE, NULL, TRUE, 113, 159),
       (121, FALSE, NULL, FALSE, 113, 150),
       (122, TRUE, NULL, TRUE, 114, 151),
       (123, FALSE, NULL, FALSE, 114, 152),
       (124, TRUE, NULL, TRUE, 114, 153),
       (125, FALSE, NULL, FALSE, 117, 154),
       (126, TRUE, NULL, TRUE, 117, 155),
       (127, FALSE, NULL, FALSE, 117, 156),
       (128, TRUE, NULL, TRUE, 119, 157),
       (129, FALSE, NULL, FALSE, 119, 158),
       (130, TRUE, NULL, TRUE, 119, 159);
------------------------------------------------------------------------------------------------------------------------
-- COMBINATED ROLES
-- SIPTIS USER
INSERT INTO siptis_user_ (id_, email_, password_, token_password_)
VALUES (170, 'luis.teach.tutor.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu',
        null),
       (171, 'mariana.teach.tutor.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (172, 'roberto.teach.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (173, 'valentina.tutor.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (174, 'patricia.teach.tutor.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu',
        null),
       (175, 'manuel.teach.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (176, 'lucas.teach.tutor.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (177, 'andrea.tutor.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (178, 'francisco.teach.tutor.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu',
        null),
       (179, 'ana.teach.tutor.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (180, 'carlos.teach.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (181, 'juliana.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (182, 'andres.tutor.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (183, 'laura.teach.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (184, 'sergio.tutor.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (185, 'ana.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (186, 'maria.teach.tutor@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (187, 'juan.tutor.sup@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (188, 'claudia.sup.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null),
       (189, 'pedro.teach.trib@mail.com', '$2a$10$RLp2O1gUM9.WzQVRJDzgV.bHz4bi6awFqEDZeSKLgR2GOyx.3jGNu', null);
-- USER INFORMATION
INSERT INTO user_information_ (id_, birth_date_, cell_number_, ci_, cod_sis_, full_name_, last_names_, names_, user_id_)
VALUES (170, '1988-06-23', '789456123', '1234567', '202200170', 'Luis Gonzalez Soto', 'Gonzalez Soto', 'Luis', 170),
       (171, '1990-11-14', '632145987', '4567890', '202200171', 'Mariana Rodriguez Vargas', 'Rodriguez Vargas',
        'Mariana', 171),
       (172, '1979-03-02', '987412365', '2345678', '202200172', 'Roberto Hernandez Diaz', 'Hernandez Diaz', 'Roberto',
        172),
       (173, '1993-05-14', '321987654', '4560123', '202200173', 'Valentina Lopez Castillo', 'Lopez Castillo',
        'Valentina', 173),
       (174, '1990-01-20', '963258741', '3456789', '202200174', 'Patricia Garcia Vargas', 'Garcia Vargas', 'Patricia',
        174),
       (175, '1989-02-15', '987654321', '7890123', '202200175', 'Manuel Martinez Moreno', 'Martinez Moreno', 'Manuel',
        175),
       (176, '1995-09-18', '896321457', '6789012', '202200176', 'Lucas Diaz Paredes', 'Diaz Paredes', 'Lucas', 176),
       (177, '1993-03-30', '654987321', '8901234', '202200177', 'Andrea Perez Serrano', 'Perez Serrano', 'Andrea', 177),
       (178, '1980-09-02', '147258369', '3456789', '202200178', 'Francisco Sanchez Morales', 'Sanchez Morales',
        'Francisco', 178),
       (179, '1982-08-10', '987654321', '5678901', '202200179', 'Ana Gomez Mendez', 'Gomez Mendez', 'Ana', 179),
       (180, '1985-07-20', '654321987', '9012345', '202200180', 'Carlos Rivera Jimenez', 'Rivera Jimenez', 'Carlos',
        180),
       (181, '1988-04-05', '321654987', '7890123', '202200181', 'Juliana Fernandez Soto', 'Fernandez Soto', 'Juliana',
        181),
       (182, '1987-04-15', '654987321', '5678901', '202200182', 'Andres Gonzalez Soto', 'Gonzalez Soto', 'Andres', 182),
       (183, '1992-12-05', '789654123', '8901234', '202200183', 'Laura Rodriguez Vargas', 'Rodriguez Vargas', 'Laura',
        183),
       (184, '1989-08-22', '321654987', '2345678', '202200184', 'Sergio Ramirez Diaz', 'Ramirez Diaz', 'Sergio', 184),
       (185, '1991-06-17', '963258741', '6789012', '202200185', 'Ana Martinez Castillo', 'Martinez Castillo', 'Ana',
        185),
       (186, '1993-10-28', '147852369', '3456789', '202200186', 'Maria Lopez Paredes', 'Lopez Paredes', 'Maria', 186),
       (187, '1990-09-03', '258963147', '9012345', '202200187', 'Juan Fernandez Moreno', 'Fernandez Moreno', 'Juan',
        187),
       (188, '1988-07-12', '369852147', '7890123', '202200188', 'Claudia Sanchez Jimenez', 'Sanchez Jimenez', 'Claudia',
        188),
       (189, '1995-05-21', '852369741', '0123456', '202200189', 'Pedro Perez Garcia', 'Perez Garcia', 'Pedro', 189);
-- SIPITS USER ROLE
INSERT INTO siptis_user_role_ (siptis_user_id_, role_id_)
VALUES
    -- Usuarios con rol TEACHER
    (170, 102),
    (171, 102),
    (172, 102),
    (174, 102),
    (175, 102),
    (176, 102),
    (178, 102),
    (179, 102),
    (180, 102),
    (183, 102),
    (186, 102),
    (189, 102),
    -- Usuarios con rol TUTOR
    (170, 103),
    (171, 103),
    (173, 103),
    (174, 103),
    (177, 103),
    (178, 103),
    (179, 103),
    (182, 103),
    (184, 103),
    (187, 103),
    -- Usuarios con rol SUPERVISOR
    (170, 104),
    (171, 104),
    (172, 104),
    (173, 104),
    (175, 104),
    (176, 104),
    (177, 104),
    (178, 104),
    (179, 104),
    (181, 104),
    (185, 104),
    (187, 104),
    (188, 104),
    -- Usuarios con rol TRIBUNAL
    (170, 105),
    (171, 105),
    (172, 105),
    (173, 105),
    (174, 105),
    (175, 105),
    (176, 105),
    (177, 105),
    (178, 105),
    (179, 105),
    (181, 105),
    (185, 105),
    (188, 105);
-- SIPTIS USER AREA
INSERT INTO siptis_user_area_ (siptis_user_id_, area_id_)
VALUES (170, 100),
       (170, 105),
       (171, 106),
       (171, 109),
       (172, 102),
       (172, 104),
       (173, 101),
       (173, 103),
       (174, 100),
       (174, 107),
       (175, 100),
       (175, 105),
       (176, 100),
       (176, 106),
       (177, 101),
       (177, 108),
       (178, 100),
       (178, 107),
       (179, 100),
       (179, 109),
       (180, 100),
       (180, 101),
       (181, 108),
       (181, 110),
       (182, 105),
       (182, 106),
       (183, 100),
       (183, 102),
       (184, 101),
       (184, 103),
       (185, 108),
       (185, 109),
       (186, 100),
       (186, 107),
       (187, 106),
       (187, 110),
       (188, 107),
       (188, 108),
       (189, 100),
       (189, 105);
-- SIPTIS USER TO PROJECT
INSERT INTO project_supervisor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (150, TRUE, TRUE, 101, 170),
       (152, FALSE, TRUE, 102, 172),
       (156, FALSE, FALSE, 103, 176),
       (160, TRUE, FALSE, 104, 180),
       (163, TRUE, FALSE, 105, 183),
       (166, FALSE, TRUE, 106, 186),
       (169, TRUE, FALSE, 107, 189),
       (172, FALSE, TRUE, 108, 172),
       (175, TRUE, FALSE, 109, 175),
       (178, FALSE, FALSE, 110, 178);
INSERT INTO project_tutor_ (id_, accepted_, reviewed_, project_id_, user_id_)
VALUES (151, TRUE, FALSE, 101, 171),
       (153, FALSE, FALSE, 102, 173),
       (157, TRUE, TRUE, 103, 177),
       (161, FALSE, TRUE, 104, 181),
       (164, FALSE, TRUE, 105, 184),
       (167, TRUE, FALSE, 106, 187),
       (170, FALSE, TRUE, 107, 170),
       (173, TRUE, FALSE, 108, 173),
       (176, TRUE, TRUE, 109, 176),
       (179, FALSE, FALSE, 110, 179);
INSERT INTO project_tribunal_ (id_, accepted_, defense_points_, reviewed_, project_id_, user_id_)
VALUES (154, TRUE, NULL, FALSE, 102, 174),
       (158, FALSE, NULL, TRUE, 103, 178),
       (162, TRUE, NULL, FALSE, 104, 182),
       (165, TRUE, NULL, TRUE, 105, 185),
       (168, TRUE, NULL, FALSE, 106, 188),
       (171, TRUE, NULL, TRUE, 107, 171),
       (174, FALSE, NULL, TRUE, 108, 174),
       (177, TRUE, NULL, FALSE, 109, 177),
       (180, FALSE, NULL, TRUE, 110, 180);
------------------------------------------------------------------------------------------------------------------------
-- SIPTIS USER CAREER
INSERT INTO siptis_user_career_ (siptis_user_id_, career_id_)
VALUES (100, 100),
       (101, 100),
       (102, 101),
       (103, 101),
       (104, 100),
       (105, 101),
       (106, 100),
       (107, 101),
       (108, 100),
       (109, 101),
       (110, 100),
       (111, 100),
       (112, 101),
       (113, 101),
       (114, 100),
       (190, 101),
       (191, 100),
       (192, 101),
       (193, 100),
       (194, 101),
       (195, 100),
       (196, 101),
       (197, 100),
       (198, 101),
       (199, 100),
       (200, 101),
       (201, 100),
       (202, 101),
       (203, 100),
       (204, 101),
       (205, 100);
------------------------------------------------------------------------------------------------------------------------
-- SUB-AREA
INSERT INTO sub_area_ (id_, name_)
VALUES (100, 'DESARROLLO ÁGIL'),
       (101, 'INGENIERÍA DE REQUISITOS'),
       (102, 'DISEÑO DE SOFTWARE'),
       (103, 'PRUEBAS DE SOFTWARE'),
       (104, 'GESTIÓN DE CONFIGURACIÓN'),
       (105, 'MANTENIMIENTO DE SOFTWARE'),
       (106, 'ARQUITECTURA DE SOFTWARE'),
       (107, 'CALIDAD DE SOFTWARE'),
       (108, 'INGENIERÍA INVERSA'),
       (109, 'PROCESOS DE DESARROLLO'),
       (110, 'MACHINE LEARNING');
-- PROJECT SUB AREA
INSERT INTO project_sub_area_ (project_id_, sub_area_id_)
VALUES (100, 100),
       (101, 106),
       (102, 104),
       (103, 103),
       (104, 101),
       (105, 104),
       (106, 102),
       (107, 109),
       (108, 109),
       (109, 106),
       (110, 105),
       (111, 103),
       (112, 105),
       (113, 106),
       (114, 107),
       (115, 108),
       (116, 107),
       (117, 108),
       (118, 102),
       (119, 103);
------------------------------------------------------------------------------------------------------------------------
-- PHASES
-- Insertar fases para PROYECTO DE GRADO (ID: 100)
INSERT INTO phase_ (id_, description_phase_long_, description_phase_short_, name_, number_phase_, modality_id_)
VALUES (100,
        'La fase de revisión en el proceso de titulación es un paso crucial que implica un análisis detallado y exhaustivo del proyecto presentado por el estudiante. En esta etapa, se asignan revisores expertos que evalúan minuciosamente cada aspecto del trabajo para garantizar su calidad, coherencia, relevancia, profundidad, originalidad y rigurosidad académica.
        Los revisores se centran en diferentes aspectos del proyecto de grado, desde la metodología utilizada hasta los resultados obtenidos y las conclusiones alcanzadas. Su objetivo principal es asegurarse de que el trabajo cumpla con estándares académicos rigurosos, aborde adecuadamente la problemática planteada y aporte conocimiento o aportes significativos al campo de estudio correspondiente.
        Durante esta fase, se realiza un análisis exhaustivo de cada sección del proyecto. La metodología se examina en términos de su idoneidad para abordar la investigación o estudio propuesto, mientras que los resultados son evaluados en cuanto a su relevancia y contribución al tema. Las conclusiones son sometidas a un escrutinio para determinar si están respaldadas por la evidencia presentada y si responden adecuadamente a los objetivos planteados.
        La revisión también busca identificar posibles áreas de mejora, ofreciendo comentarios constructivos que pueden ayudar al estudiante a perfeccionar su trabajo. Los revisores, en este sentido, actúan como guías académicos que brindan orientación para fortalecer el proyecto y elevar su calidad.
        En resumen, la fase de revisión del proyecto en el proceso de titulación es una etapa crítica donde expertos evalúan minuciosamente el trabajo del estudiante para asegurar su calidad, originalidad y rigor académico, proporcionando sugerencias valiosas para mejorar el proyecto en su totalidad.',
        'La fase de revisión en el proceso de titulación implica un análisis exhaustivo del proyecto presentado por el estudiante. Expertos revisores evalúan la calidad, coherencia, relevancia, originalidad y rigor académico del trabajo. Se examinan la metodología, resultados y conclusiones para garantizar su idoneidad y contribución al campo de estudio. Los revisores ofrecen comentarios constructivos para mejorar el proyecto y actúan como guías académicos en este proceso',
        'REVIEWERS_PHASE',
        1,
        100),
       (101,
        'La fase de asignación de tribunales dentro del proceso de titulación es un paso crítico que asegura una evaluación justa y experta del proyecto de grado presentado por el estudiante. Este proceso implica la cuidadosa selección y designación de los miembros del tribunal evaluador, considerando su experiencia, conocimientos y especialización en el área específica del proyecto.
        Los tribunales son elegidos estratégicamente para garantizar una revisión exhaustiva y objetiva del trabajo presentado. Se busca conformar un panel de evaluadores que posean la expertise necesaria para comprender en profundidad los aspectos clave del proyecto, desde su metodología hasta los resultados y conclusiones alcanzadas. La diversidad de enfoques y conocimientos entre los miembros del tribunal puede enriquecer la evaluación al proporcionar diferentes perspectivas sobre el proyecto, enriqueciendo así el proceso de evaluación.
        La asignación de tribunales no solo se centra en la experiencia académica de los evaluadores, sino también en su imparcialidad y capacidad para realizar una evaluación objetiva del trabajo presentado. Esto asegura que la revisión sea rigurosa, justa y basada en criterios académicos sólidos, garantizando la calidad y el reconocimiento del esfuerzo realizado por el estudiante durante su proyecto de grado.
        Una correcta asignación de tribunales no solo es fundamental para la evaluación académica del proyecto, sino que también representa una oportunidad para que el estudiante sea evaluado de manera integral y justa, contribuyendo así a la credibilidad y el reconocimiento del proceso de titulación.',
        'La fase de asignación de tribunales es un paso esencial en el proceso del proyecto de grado. Durante esta etapa, se designan los tribunales o comités evaluadores que serán responsables de revisar y evaluar el trabajo presentado por el estudiante. La asignación se realiza considerando la experiencia y especialización de los evaluadores para garantizar una revisión justa y exhaustiva del proyecto. Este proceso busca asegurar que el trabajo sea evaluado por expertos capacitados en el área correspondiente, promoviendo así la calidad y pertinencia en la evaluación del proyecto de grado.',
        'ASSIGN_TRIBUNALS_PHASE',
        2,
        100),
       (102,
        'La fase de Tribunales dentro del proceso de titulación implica la selección cuidadosa y designación de un comité evaluador que revisará y juzgará el proyecto de grado presentado por el estudiante. Este comité está compuesto por expertos y académicos con experiencia en el área específica del proyecto. La finalidad es garantizar una evaluación justa y exhaustiva del trabajo, considerando su metodología, resultados, conclusiones y contribuciones al campo de estudio.
        Los tribunales se seleccionan estratégicamente, considerando la diversidad de conocimientos y enfoques necesarios para ofrecer una evaluación integral. Se busca incluir miembros que puedan aportar diferentes perspectivas, enriqueciendo así el proceso de revisión y asegurando una evaluación equilibrada y fundamentada.
        Cada miembro del comité evaluador analiza minuciosamente el proyecto. Se examina la coherencia interna, la validez de la metodología utilizada, la interpretación de los resultados y la relevancia de las conclusiones. Los evaluadores emiten comentarios detallados y críticos, señalando fortalezas y áreas de mejora.
        El objetivo es garantizar que el proyecto cumpla con los estándares académicos requeridos para obtener la aprobación final. Esta fase representa una instancia clave donde el estudiante enfrenta la evaluación crítica y constructiva de expertos, lo que permite el refinamiento y validación de su trabajo académico.',
        'En la fase de Tribunales del proceso de titulación, se designa un comité evaluador compuesto por expertos en el área del proyecto. Estos evaluadores revisan minuciosamente el trabajo presentado por el estudiante, ofreciendo análisis detallados y críticos. La finalidad es garantizar la calidad académica del proyecto y su cumplimiento con estándares exigentes. Esta fase proporciona una evaluación crítica y constructiva que valida y perfecciona el trabajo académico del estudiante.',
        'TRIBUNALS_PHASE',
        3,
        100),
       (103,
        'La asignación de la fase de defensa generalmente implica coordinar con los miembros del comité evaluador, asegurándose de su disponibilidad para participar en la sesión de defensa. Se establece una fecha y hora conveniente para el estudiante y los evaluadores. Además, se comunica al estudiante quiénes serán los miembros del comité evaluador que participarán en su defensa, brindándoles información sobre los roles y expectativas para ese día.
        Es crucial que esta asignación se realice con anticipación suficiente para permitir que el estudiante se prepare adecuadamente para la defensa, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión del proyecto. Esto brinda al estudiante el tiempo necesario para afinar su presentación, preparar respuestas a posibles preguntas y fortalecer su comprensión sobre el proyecto en general.',
        'La asignación de la fase de defensa en un proceso de titulación implica la coordinación de la fecha, hora y los miembros del comité evaluador que participarán en la evaluación del proyecto de grado del estudiante. Esto se hace para permitir una preparación adecuada por parte del estudiante y para garantizar que la defensa se realice de manera efectiva, permitiendo la presentación y argumentación adecuada del trabajo académico.',
        'ASSIGN_DEFENSE_PHASE',
        4,
        100),
       (104,
        'La fase previa a la defensa en el proceso de titulación es un período crucial donde el estudiante se prepara meticulosamente para la presentación y defensa de su proyecto de grado ante un comité evaluador. Durante esta etapa, el estudiante realiza una revisión exhaustiva de su trabajo, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión.
        El objetivo principal de la pre-defensa es perfeccionar el proyecto en función de los comentarios proporcionados por los revisores. Esto implica realizar ajustes, clarificar aspectos ambiguos, fortalecer argumentos, validar resultados y asegurarse de que la presentación esté bien estructurada y sea persuasiva.
        Además de la preparación del contenido, la pre-defensa implica práctica y entrenamiento para la presentación oral. El estudiante ensaya su exposición, anticipando posibles preguntas y preparándose para responder de manera clara y fundamentada. También puede recibir retroalimentación de mentores o profesores para mejorar su capacidad de comunicación y manejo de la sesión de defensa.',
        'La fase previa a la defensa es un período de refinamiento y preparación intensiva donde el estudiante ajusta su proyecto, se prepara para la presentación oral y se asegura de estar completamente listo para enfrentar la evaluación del comité evaluador',
        'PRE_DEFENSE_PHASE',
        5,
        100),
       (105,
        'La fase de defensa es esencial en el proceso del proyecto de grado, ya que el estudiante presenta su trabajo ante un comité evaluador. En esta etapa, se espera que el estudiante exponga y justifique sus decisiones, responda preguntas y argumente la validez de sus resultados. La defensa representa un paso crucial para obtener la aprobación final de su proyecto.
          Durante la defensa, se pone a prueba el conocimiento adquirido a lo largo del proyecto y se demuestra la solidez del trabajo realizado. El estudiante debe mostrar dominio sobre el tema, evidenciar comprensión de la metodología empleada y ser capaz de defender cada aspecto de su investigación o proyecto.
          El comité evaluador puede formular preguntas para profundizar en diferentes áreas del trabajo presentado, desde la fundamentación teórica hasta la aplicación de la metodología y la interpretación de los resultados. Además, se espera que el estudiante argumente la relevancia y contribución de su proyecto al campo de estudio correspondiente.
          La defensa brinda la oportunidad de demostrar habilidades comunicativas, capacidad de análisis y pensamiento crítico. Es un momento en el que el estudiante debe mostrar confianza en su trabajo, ser capaz de defenderlo ante posibles críticas y demostrar cómo su investigación o proyecto aporta al conocimiento existente.
          En resumen, la fase de defensa del proyecto de grado implica presentar y justificar el trabajo ante un comité evaluador, demostrando conocimiento, solidez en el proyecto y habilidades de argumentación, siendo crucial para obtener la aprobación final del mismo.',
        'La fase de defensa en el proceso del proyecto de grado es clave: el estudiante presenta su trabajo ante un comité evaluador, justifica decisiones, responde preguntas y valida resultados. Aquí se demuestra el dominio del tema, la solidez del trabajo y la contribución al campo. La defensa evalúa habilidades de comunicación, análisis y argumentación, siendo determinante para la aprobación final del proyecto.',
        'DEFENSE_PHASE',
        6,
        100),
       (106,
        'La fase posterior a la defensa en el proceso de titulación es un momento significativo que sigue inmediatamente después de que el estudiante haya presentado y defendido su proyecto de grado ante el comité evaluador. Esta etapa marca el cierre del proceso académico, pero también implica una serie de pasos y consideraciones importantes.
        Tras la defensa, el estudiante puede experimentar un período de espera mientras el comité evaluador deliberará y tomará una decisión con respecto a la aprobación del proyecto. Durante este tiempo, es común sentir una mezcla de emociones, como nerviosismo y expectación, mientras se espera el resultado final.
        Una vez que se ha recibido la evaluación del comité y se ha confirmado la aprobación del proyecto, el estudiante puede proceder con los trámites administrativos necesarios para finalizar su titulación. Esto puede incluir la presentación de documentación adicional, la firma de formularios o cualquier otro requisito específico de la institución educativa.',
        'La fase posterior a la defensa implica esperar el veredicto del comité evaluador, completar los trámites finales requeridos por la institución educativa y reflexionar sobre la experiencia académica en su totalidad. Es un período donde el estudiante culmina su proceso de titulación y se prepara para dar el siguiente paso en su trayectoria profesional o académica',
        'POST_DEFENSE_PHASE',
        7,
        100);

INSERT INTO phase_ (id_, description_phase_long_, description_phase_short_, name_, number_phase_, modality_id_)
VALUES (107,
        'La fase de revisión en el proceso de titulación es un paso crucial que implica un análisis detallado y exhaustivo del proyecto presentado por el estudiante. En esta etapa, se asignan revisores expertos que evalúan minuciosamente cada aspecto del trabajo para garantizar su calidad, coherencia, relevancia, profundidad, originalidad y rigurosidad académica.
        Los revisores se centran en diferentes aspectos del proyecto de grado, desde la metodología utilizada hasta los resultados obtenidos y las conclusiones alcanzadas. Su objetivo principal es asegurarse de que el trabajo cumpla con estándares académicos rigurosos, aborde adecuadamente la problemática planteada y aporte conocimiento o aportes significativos al campo de estudio correspondiente.
        Durante esta fase, se realiza un análisis exhaustivo de cada sección del proyecto. La metodología se examina en términos de su idoneidad para abordar la investigación o estudio propuesto, mientras que los resultados son evaluados en cuanto a su relevancia y contribución al tema. Las conclusiones son sometidas a un escrutinio para determinar si están respaldadas por la evidencia presentada y si responden adecuadamente a los objetivos planteados.
        La revisión también busca identificar posibles áreas de mejora, ofreciendo comentarios constructivos que pueden ayudar al estudiante a perfeccionar su trabajo. Los revisores, en este sentido, actúan como guías académicos que brindan orientación para fortalecer el proyecto y elevar su calidad.
        En resumen, la fase de revisión del proyecto en el proceso de titulación es una etapa crítica donde expertos evalúan minuciosamente el trabajo del estudiante para asegurar su calidad, originalidad y rigor académico, proporcionando sugerencias valiosas para mejorar el proyecto en su totalidad.',
        'La fase de revisión en el proceso de titulación implica un análisis exhaustivo del proyecto presentado por el estudiante. Expertos revisores evalúan la calidad, coherencia, relevancia, originalidad y rigor académico del trabajo. Se examinan la metodología, resultados y conclusiones para garantizar su idoneidad y contribución al campo de estudio. Los revisores ofrecen comentarios constructivos para mejorar el proyecto y actúan como guías académicos en este proceso',
        'REVIEWERS_PHASE',
        1,
        101),
       (108,
        'La fase de asignación de tribunales dentro del proceso de titulación es un paso crítico que asegura una evaluación justa y experta del proyecto de grado presentado por el estudiante. Este proceso implica la cuidadosa selección y designación de los miembros del tribunal evaluador, considerando su experiencia, conocimientos y especialización en el área específica del proyecto.
        Los tribunales son elegidos estratégicamente para garantizar una revisión exhaustiva y objetiva del trabajo presentado. Se busca conformar un panel de evaluadores que posean la expertise necesaria para comprender en profundidad los aspectos clave del proyecto, desde su metodología hasta los resultados y conclusiones alcanzadas. La diversidad de enfoques y conocimientos entre los miembros del tribunal puede enriquecer la evaluación al proporcionar diferentes perspectivas sobre el proyecto, enriqueciendo así el proceso de evaluación.
        La asignación de tribunales no solo se centra en la experiencia académica de los evaluadores, sino también en su imparcialidad y capacidad para realizar una evaluación objetiva del trabajo presentado. Esto asegura que la revisión sea rigurosa, justa y basada en criterios académicos sólidos, garantizando la calidad y el reconocimiento del esfuerzo realizado por el estudiante durante su proyecto de grado.
        Una correcta asignación de tribunales no solo es fundamental para la evaluación académica del proyecto, sino que también representa una oportunidad para que el estudiante sea evaluado de manera integral y justa, contribuyendo así a la credibilidad y el reconocimiento del proceso de titulación.',
        'La fase de asignación de tribunales es un paso esencial en el proceso del proyecto de grado. Durante esta etapa, se designan los tribunales o comités evaluadores que serán responsables de revisar y evaluar el trabajo presentado por el estudiante. La asignación se realiza considerando la experiencia y especialización de los evaluadores para garantizar una revisión justa y exhaustiva del proyecto. Este proceso busca asegurar que el trabajo sea evaluado por expertos capacitados en el área correspondiente, promoviendo así la calidad y pertinencia en la evaluación del proyecto de grado.',
        'ASSIGN_TRIBUNALS_PHASE',
        2,
        101),
       (109,
        'La fase de Tribunales dentro del proceso de titulación implica la selección cuidadosa y designación de un comité evaluador que revisará y juzgará el proyecto de grado presentado por el estudiante. Este comité está compuesto por expertos y académicos con experiencia en el área específica del proyecto. La finalidad es garantizar una evaluación justa y exhaustiva del trabajo, considerando su metodología, resultados, conclusiones y contribuciones al campo de estudio.
        Los tribunales se seleccionan estratégicamente, considerando la diversidad de conocimientos y enfoques necesarios para ofrecer una evaluación integral. Se busca incluir miembros que puedan aportar diferentes perspectivas, enriqueciendo así el proceso de revisión y asegurando una evaluación equilibrada y fundamentada.
        Cada miembro del comité evaluador analiza minuciosamente el proyecto. Se examina la coherencia interna, la validez de la metodología utilizada, la interpretación de los resultados y la relevancia de las conclusiones. Los evaluadores emiten comentarios detallados y críticos, señalando fortalezas y áreas de mejora.
        El objetivo es garantizar que el proyecto cumpla con los estándares académicos requeridos para obtener la aprobación final. Esta fase representa una instancia clave donde el estudiante enfrenta la evaluación crítica y constructiva de expertos, lo que permite el refinamiento y validación de su trabajo académico.',
        'En la fase de Tribunales del proceso de titulación, se designa un comité evaluador compuesto por expertos en el área del proyecto. Estos evaluadores revisan minuciosamente el trabajo presentado por el estudiante, ofreciendo análisis detallados y críticos. La finalidad es garantizar la calidad académica del proyecto y su cumplimiento con estándares exigentes. Esta fase proporciona una evaluación crítica y constructiva que valida y perfecciona el trabajo académico del estudiante.',
        'TRIBUNALS_PHASE',
        3,
        101),
       (110,
        'La asignación de la fase de defensa generalmente implica coordinar con los miembros del comité evaluador, asegurándose de su disponibilidad para participar en la sesión de defensa. Se establece una fecha y hora conveniente para el estudiante y los evaluadores. Además, se comunica al estudiante quiénes serán los miembros del comité evaluador que participarán en su defensa, brindándoles información sobre los roles y expectativas para ese día.
        Es crucial que esta asignación se realice con anticipación suficiente para permitir que el estudiante se prepare adecuadamente para la defensa, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión del proyecto. Esto brinda al estudiante el tiempo necesario para afinar su presentación, preparar respuestas a posibles preguntas y fortalecer su comprensión sobre el proyecto en general.',
        'La asignación de la fase de defensa en un proceso de titulación implica la coordinación de la fecha, hora y los miembros del comité evaluador que participarán en la evaluación del proyecto de grado del estudiante. Esto se hace para permitir una preparación adecuada por parte del estudiante y para garantizar que la defensa se realice de manera efectiva, permitiendo la presentación y argumentación adecuada del trabajo académico.',
        'ASSIGN_DEFENSE_PHASE',
        4,
        101),
       (111,
        'La fase previa a la defensa en el proceso de titulación es un período crucial donde el estudiante se prepara meticulosamente para la presentación y defensa de su proyecto de grado ante un comité evaluador. Durante esta etapa, el estudiante realiza una revisión exhaustiva de su trabajo, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión.
        El objetivo principal de la pre-defensa es perfeccionar el proyecto en función de los comentarios proporcionados por los revisores. Esto implica realizar ajustes, clarificar aspectos ambiguos, fortalecer argumentos, validar resultados y asegurarse de que la presentación esté bien estructurada y sea persuasiva.
        Además de la preparación del contenido, la pre-defensa implica práctica y entrenamiento para la presentación oral. El estudiante ensaya su exposición, anticipando posibles preguntas y preparándose para responder de manera clara y fundamentada. También puede recibir retroalimentación de mentores o profesores para mejorar su capacidad de comunicación y manejo de la sesión de defensa.',
        'La fase previa a la defensa es un período de refinamiento y preparación intensiva donde el estudiante ajusta su proyecto, se prepara para la presentación oral y se asegura de estar completamente listo para enfrentar la evaluación del comité evaluador',
        'PRE_DEFENSE_PHASE',
        5,
        101),
       (112,
        'La fase de defensa es esencial en el proceso del proyecto de grado, ya que el estudiante presenta su trabajo ante un comité evaluador. En esta etapa, se espera que el estudiante exponga y justifique sus decisiones, responda preguntas y argumente la validez de sus resultados. La defensa representa un paso crucial para obtener la aprobación final de su proyecto.
          Durante la defensa, se pone a prueba el conocimiento adquirido a lo largo del proyecto y se demuestra la solidez del trabajo realizado. El estudiante debe mostrar dominio sobre el tema, evidenciar comprensión de la metodología empleada y ser capaz de defender cada aspecto de su investigación o proyecto.
          El comité evaluador puede formular preguntas para profundizar en diferentes áreas del trabajo presentado, desde la fundamentación teórica hasta la aplicación de la metodología y la interpretación de los resultados. Además, se espera que el estudiante argumente la relevancia y contribución de su proyecto al campo de estudio correspondiente.
          La defensa brinda la oportunidad de demostrar habilidades comunicativas, capacidad de análisis y pensamiento crítico. Es un momento en el que el estudiante debe mostrar confianza en su trabajo, ser capaz de defenderlo ante posibles críticas y demostrar cómo su investigación o proyecto aporta al conocimiento existente.
          En resumen, la fase de defensa del proyecto de grado implica presentar y justificar el trabajo ante un comité evaluador, demostrando conocimiento, solidez en el proyecto y habilidades de argumentación, siendo crucial para obtener la aprobación final del mismo.',
        'La fase de defensa en el proceso del proyecto de grado es clave: el estudiante presenta su trabajo ante un comité evaluador, justifica decisiones, responde preguntas y valida resultados. Aquí se demuestra el dominio del tema, la solidez del trabajo y la contribución al campo. La defensa evalúa habilidades de comunicación, análisis y argumentación, siendo determinante para la aprobación final del proyecto.',
        'DEFENSE_PHASE',
        6,
        101),
       (113,
        'La fase posterior a la defensa en el proceso de titulación es un momento significativo que sigue inmediatamente después de que el estudiante haya presentado y defendido su proyecto de grado ante el comité evaluador. Esta etapa marca el cierre del proceso académico, pero también implica una serie de pasos y consideraciones importantes.
        Tras la defensa, el estudiante puede experimentar un período de espera mientras el comité evaluador deliberará y tomará una decisión con respecto a la aprobación del proyecto. Durante este tiempo, es común sentir una mezcla de emociones, como nerviosismo y expectación, mientras se espera el resultado final.
        Una vez que se ha recibido la evaluación del comité y se ha confirmado la aprobación del proyecto, el estudiante puede proceder con los trámites administrativos necesarios para finalizar su titulación. Esto puede incluir la presentación de documentación adicional, la firma de formularios o cualquier otro requisito específico de la institución educativa.',
        'La fase posterior a la defensa implica esperar el veredicto del comité evaluador, completar los trámites finales requeridos por la institución educativa y reflexionar sobre la experiencia académica en su totalidad. Es un período donde el estudiante culmina su proceso de titulación y se prepara para dar el siguiente paso en su trayectoria profesional o académica',
        'POST_DEFENSE_PHASE',
        7,
        101);

INSERT INTO phase_ (id_, description_phase_long_, description_phase_short_, name_, number_phase_, modality_id_)
VALUES (114,
        'La fase de revisión en el proceso de titulación es un paso crucial que implica un análisis detallado y exhaustivo del proyecto presentado por el estudiante. En esta etapa, se asignan revisores expertos que evalúan minuciosamente cada aspecto del trabajo para garantizar su calidad, coherencia, relevancia, profundidad, originalidad y rigurosidad académica.
        Los revisores se centran en diferentes aspectos del proyecto de grado, desde la metodología utilizada hasta los resultados obtenidos y las conclusiones alcanzadas. Su objetivo principal es asegurarse de que el trabajo cumpla con estándares académicos rigurosos, aborde adecuadamente la problemática planteada y aporte conocimiento o aportes significativos al campo de estudio correspondiente.
        Durante esta fase, se realiza un análisis exhaustivo de cada sección del proyecto. La metodología se examina en términos de su idoneidad para abordar la investigación o estudio propuesto, mientras que los resultados son evaluados en cuanto a su relevancia y contribución al tema. Las conclusiones son sometidas a un escrutinio para determinar si están respaldadas por la evidencia presentada y si responden adecuadamente a los objetivos planteados.
        La revisión también busca identificar posibles áreas de mejora, ofreciendo comentarios constructivos que pueden ayudar al estudiante a perfeccionar su trabajo. Los revisores, en este sentido, actúan como guías académicos que brindan orientación para fortalecer el proyecto y elevar su calidad.
        En resumen, la fase de revisión del proyecto en el proceso de titulación es una etapa crítica donde expertos evalúan minuciosamente el trabajo del estudiante para asegurar su calidad, originalidad y rigor académico, proporcionando sugerencias valiosas para mejorar el proyecto en su totalidad.',
        'La fase de revisión en el proceso de titulación implica un análisis exhaustivo del proyecto presentado por el estudiante. Expertos revisores evalúan la calidad, coherencia, relevancia, originalidad y rigor académico del trabajo. Se examinan la metodología, resultados y conclusiones para garantizar su idoneidad y contribución al campo de estudio. Los revisores ofrecen comentarios constructivos para mejorar el proyecto y actúan como guías académicos en este proceso',
        'REVIEWERS_PHASE',
        1,
        102),
       (115,
        'La fase de asignación de tribunales dentro del proceso de titulación es un paso crítico que asegura una evaluación justa y experta del proyecto de grado presentado por el estudiante. Este proceso implica la cuidadosa selección y designación de los miembros del tribunal evaluador, considerando su experiencia, conocimientos y especialización en el área específica del proyecto.
        Los tribunales son elegidos estratégicamente para garantizar una revisión exhaustiva y objetiva del trabajo presentado. Se busca conformar un panel de evaluadores que posean la expertise necesaria para comprender en profundidad los aspectos clave del proyecto, desde su metodología hasta los resultados y conclusiones alcanzadas. La diversidad de enfoques y conocimientos entre los miembros del tribunal puede enriquecer la evaluación al proporcionar diferentes perspectivas sobre el proyecto, enriqueciendo así el proceso de evaluación.
        La asignación de tribunales no solo se centra en la experiencia académica de los evaluadores, sino también en su imparcialidad y capacidad para realizar una evaluación objetiva del trabajo presentado. Esto asegura que la revisión sea rigurosa, justa y basada en criterios académicos sólidos, garantizando la calidad y el reconocimiento del esfuerzo realizado por el estudiante durante su proyecto de grado.
        Una correcta asignación de tribunales no solo es fundamental para la evaluación académica del proyecto, sino que también representa una oportunidad para que el estudiante sea evaluado de manera integral y justa, contribuyendo así a la credibilidad y el reconocimiento del proceso de titulación.',
        'La fase de asignación de tribunales es un paso esencial en el proceso del proyecto de grado. Durante esta etapa, se designan los tribunales o comités evaluadores que serán responsables de revisar y evaluar el trabajo presentado por el estudiante. La asignación se realiza considerando la experiencia y especialización de los evaluadores para garantizar una revisión justa y exhaustiva del proyecto. Este proceso busca asegurar que el trabajo sea evaluado por expertos capacitados en el área correspondiente, promoviendo así la calidad y pertinencia en la evaluación del proyecto de grado.',
        'ASSIGN_TRIBUNALS_PHASE',
        2,
        102),
       (116,
        'La fase de Tribunales dentro del proceso de titulación implica la selección cuidadosa y designación de un comité evaluador que revisará y juzgará el proyecto de grado presentado por el estudiante. Este comité está compuesto por expertos y académicos con experiencia en el área específica del proyecto. La finalidad es garantizar una evaluación justa y exhaustiva del trabajo, considerando su metodología, resultados, conclusiones y contribuciones al campo de estudio.
        Los tribunales se seleccionan estratégicamente, considerando la diversidad de conocimientos y enfoques necesarios para ofrecer una evaluación integral. Se busca incluir miembros que puedan aportar diferentes perspectivas, enriqueciendo así el proceso de revisión y asegurando una evaluación equilibrada y fundamentada.
        Cada miembro del comité evaluador analiza minuciosamente el proyecto. Se examina la coherencia interna, la validez de la metodología utilizada, la interpretación de los resultados y la relevancia de las conclusiones. Los evaluadores emiten comentarios detallados y críticos, señalando fortalezas y áreas de mejora.
        El objetivo es garantizar que el proyecto cumpla con los estándares académicos requeridos para obtener la aprobación final. Esta fase representa una instancia clave donde el estudiante enfrenta la evaluación crítica y constructiva de expertos, lo que permite el refinamiento y validación de su trabajo académico.',
        'En la fase de Tribunales del proceso de titulación, se designa un comité evaluador compuesto por expertos en el área del proyecto. Estos evaluadores revisan minuciosamente el trabajo presentado por el estudiante, ofreciendo análisis detallados y críticos. La finalidad es garantizar la calidad académica del proyecto y su cumplimiento con estándares exigentes. Esta fase proporciona una evaluación crítica y constructiva que valida y perfecciona el trabajo académico del estudiante.',
        'TRIBUNALS_PHASE',
        3,
        102),
       (117,
        'La asignación de la fase de defensa generalmente implica coordinar con los miembros del comité evaluador, asegurándose de su disponibilidad para participar en la sesión de defensa. Se establece una fecha y hora conveniente para el estudiante y los evaluadores. Además, se comunica al estudiante quiénes serán los miembros del comité evaluador que participarán en su defensa, brindándoles información sobre los roles y expectativas para ese día.
        Es crucial que esta asignación se realice con anticipación suficiente para permitir que el estudiante se prepare adecuadamente para la defensa, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión del proyecto. Esto brinda al estudiante el tiempo necesario para afinar su presentación, preparar respuestas a posibles preguntas y fortalecer su comprensión sobre el proyecto en general.',
        'La asignación de la fase de defensa en un proceso de titulación implica la coordinación de la fecha, hora y los miembros del comité evaluador que participarán en la evaluación del proyecto de grado del estudiante. Esto se hace para permitir una preparación adecuada por parte del estudiante y para garantizar que la defensa se realice de manera efectiva, permitiendo la presentación y argumentación adecuada del trabajo académico.',
        'ASSIGN_DEFENSE_PHASE',
        4,
        102),
       (118,
        'La fase previa a la defensa en el proceso de titulación es un período crucial donde el estudiante se prepara meticulosamente para la presentación y defensa de su proyecto de grado ante un comité evaluador. Durante esta etapa, el estudiante realiza una revisión exhaustiva de su trabajo, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión.
        El objetivo principal de la pre-defensa es perfeccionar el proyecto en función de los comentarios proporcionados por los revisores. Esto implica realizar ajustes, clarificar aspectos ambiguos, fortalecer argumentos, validar resultados y asegurarse de que la presentación esté bien estructurada y sea persuasiva.
        Además de la preparación del contenido, la pre-defensa implica práctica y entrenamiento para la presentación oral. El estudiante ensaya su exposición, anticipando posibles preguntas y preparándose para responder de manera clara y fundamentada. También puede recibir retroalimentación de mentores o profesores para mejorar su capacidad de comunicación y manejo de la sesión de defensa.',
        'La fase previa a la defensa es un período de refinamiento y preparación intensiva donde el estudiante ajusta su proyecto, se prepara para la presentación oral y se asegura de estar completamente listo para enfrentar la evaluación del comité evaluador',
        'PRE_DEFENSE_PHASE',
        5,
        102),
       (119,
        'La fase de defensa es esencial en el proceso del proyecto de grado, ya que el estudiante presenta su trabajo ante un comité evaluador. En esta etapa, se espera que el estudiante exponga y justifique sus decisiones, responda preguntas y argumente la validez de sus resultados. La defensa representa un paso crucial para obtener la aprobación final de su proyecto.
          Durante la defensa, se pone a prueba el conocimiento adquirido a lo largo del proyecto y se demuestra la solidez del trabajo realizado. El estudiante debe mostrar dominio sobre el tema, evidenciar comprensión de la metodología empleada y ser capaz de defender cada aspecto de su investigación o proyecto.
          El comité evaluador puede formular preguntas para profundizar en diferentes áreas del trabajo presentado, desde la fundamentación teórica hasta la aplicación de la metodología y la interpretación de los resultados. Además, se espera que el estudiante argumente la relevancia y contribución de su proyecto al campo de estudio correspondiente.
          La defensa brinda la oportunidad de demostrar habilidades comunicativas, capacidad de análisis y pensamiento crítico. Es un momento en el que el estudiante debe mostrar confianza en su trabajo, ser capaz de defenderlo ante posibles críticas y demostrar cómo su investigación o proyecto aporta al conocimiento existente.
          En resumen, la fase de defensa del proyecto de grado implica presentar y justificar el trabajo ante un comité evaluador, demostrando conocimiento, solidez en el proyecto y habilidades de argumentación, siendo crucial para obtener la aprobación final del mismo.',
        'La fase de defensa en el proceso del proyecto de grado es clave: el estudiante presenta su trabajo ante un comité evaluador, justifica decisiones, responde preguntas y valida resultados. Aquí se demuestra el dominio del tema, la solidez del trabajo y la contribución al campo. La defensa evalúa habilidades de comunicación, análisis y argumentación, siendo determinante para la aprobación final del proyecto.',
        'DEFENSE_PHASE',
        6,
        102),
       (120,
        'La fase posterior a la defensa en el proceso de titulación es un momento significativo que sigue inmediatamente después de que el estudiante haya presentado y defendido su proyecto de grado ante el comité evaluador. Esta etapa marca el cierre del proceso académico, pero también implica una serie de pasos y consideraciones importantes.
        Tras la defensa, el estudiante puede experimentar un período de espera mientras el comité evaluador deliberará y tomará una decisión con respecto a la aprobación del proyecto. Durante este tiempo, es común sentir una mezcla de emociones, como nerviosismo y expectación, mientras se espera el resultado final.
        Una vez que se ha recibido la evaluación del comité y se ha confirmado la aprobación del proyecto, el estudiante puede proceder con los trámites administrativos necesarios para finalizar su titulación. Esto puede incluir la presentación de documentación adicional, la firma de formularios o cualquier otro requisito específico de la institución educativa.',
        'La fase posterior a la defensa implica esperar el veredicto del comité evaluador, completar los trámites finales requeridos por la institución educativa y reflexionar sobre la experiencia académica en su totalidad. Es un período donde el estudiante culmina su proceso de titulación y se prepara para dar el siguiente paso en su trayectoria profesional o académica',
        'POST_DEFENSE_PHASE',
        7,
        102);

INSERT INTO phase_ (id_, description_phase_long_, description_phase_short_, name_, number_phase_, modality_id_)
VALUES (121,
        'La fase de revisión en el proceso de titulación es un paso crucial que implica un análisis detallado y exhaustivo del proyecto presentado por el estudiante. En esta etapa, se asignan revisores expertos que evalúan minuciosamente cada aspecto del trabajo para garantizar su calidad, coherencia, relevancia, profundidad, originalidad y rigurosidad académica.
        Los revisores se centran en diferentes aspectos del proyecto de grado, desde la metodología utilizada hasta los resultados obtenidos y las conclusiones alcanzadas. Su objetivo principal es asegurarse de que el trabajo cumpla con estándares académicos rigurosos, aborde adecuadamente la problemática planteada y aporte conocimiento o aportes significativos al campo de estudio correspondiente.
        Durante esta fase, se realiza un análisis exhaustivo de cada sección del proyecto. La metodología se examina en términos de su idoneidad para abordar la investigación o estudio propuesto, mientras que los resultados son evaluados en cuanto a su relevancia y contribución al tema. Las conclusiones son sometidas a un escrutinio para determinar si están respaldadas por la evidencia presentada y si responden adecuadamente a los objetivos planteados.
        La revisión también busca identificar posibles áreas de mejora, ofreciendo comentarios constructivos que pueden ayudar al estudiante a perfeccionar su trabajo. Los revisores, en este sentido, actúan como guías académicos que brindan orientación para fortalecer el proyecto y elevar su calidad.
        En resumen, la fase de revisión del proyecto en el proceso de titulación es una etapa crítica donde expertos evalúan minuciosamente el trabajo del estudiante para asegurar su calidad, originalidad y rigor académico, proporcionando sugerencias valiosas para mejorar el proyecto en su totalidad.',
        'La fase de revisión en el proceso de titulación implica un análisis exhaustivo del proyecto presentado por el estudiante. Expertos revisores evalúan la calidad, coherencia, relevancia, originalidad y rigor académico del trabajo. Se examinan la metodología, resultados y conclusiones para garantizar su idoneidad y contribución al campo de estudio. Los revisores ofrecen comentarios constructivos para mejorar el proyecto y actúan como guías académicos en este proceso',
        'REVIEWERS_PHASE',
        1,
        103),
       (122,
        'La fase de asignación de tribunales dentro del proceso de titulación es un paso crítico que asegura una evaluación justa y experta del proyecto de grado presentado por el estudiante. Este proceso implica la cuidadosa selección y designación de los miembros del tribunal evaluador, considerando su experiencia, conocimientos y especialización en el área específica del proyecto.
        Los tribunales son elegidos estratégicamente para garantizar una revisión exhaustiva y objetiva del trabajo presentado. Se busca conformar un panel de evaluadores que posean la expertise necesaria para comprender en profundidad los aspectos clave del proyecto, desde su metodología hasta los resultados y conclusiones alcanzadas. La diversidad de enfoques y conocimientos entre los miembros del tribunal puede enriquecer la evaluación al proporcionar diferentes perspectivas sobre el proyecto, enriqueciendo así el proceso de evaluación.
        La asignación de tribunales no solo se centra en la experiencia académica de los evaluadores, sino también en su imparcialidad y capacidad para realizar una evaluación objetiva del trabajo presentado. Esto asegura que la revisión sea rigurosa, justa y basada en criterios académicos sólidos, garantizando la calidad y el reconocimiento del esfuerzo realizado por el estudiante durante su proyecto de grado.
        Una correcta asignación de tribunales no solo es fundamental para la evaluación académica del proyecto, sino que también representa una oportunidad para que el estudiante sea evaluado de manera integral y justa, contribuyendo así a la credibilidad y el reconocimiento del proceso de titulación.',
        'La fase de asignación de tribunales es un paso esencial en el proceso del proyecto de grado. Durante esta etapa, se designan los tribunales o comités evaluadores que serán responsables de revisar y evaluar el trabajo presentado por el estudiante. La asignación se realiza considerando la experiencia y especialización de los evaluadores para garantizar una revisión justa y exhaustiva del proyecto. Este proceso busca asegurar que el trabajo sea evaluado por expertos capacitados en el área correspondiente, promoviendo así la calidad y pertinencia en la evaluación del proyecto de grado.',
        'ASSIGN_TRIBUNALS_PHASE',
        2,
        103),
       (123,
        'La fase de Tribunales dentro del proceso de titulación implica la selección cuidadosa y designación de un comité evaluador que revisará y juzgará el proyecto de grado presentado por el estudiante. Este comité está compuesto por expertos y académicos con experiencia en el área específica del proyecto. La finalidad es garantizar una evaluación justa y exhaustiva del trabajo, considerando su metodología, resultados, conclusiones y contribuciones al campo de estudio.
        Los tribunales se seleccionan estratégicamente, considerando la diversidad de conocimientos y enfoques necesarios para ofrecer una evaluación integral. Se busca incluir miembros que puedan aportar diferentes perspectivas, enriqueciendo así el proceso de revisión y asegurando una evaluación equilibrada y fundamentada.
        Cada miembro del comité evaluador analiza minuciosamente el proyecto. Se examina la coherencia interna, la validez de la metodología utilizada, la interpretación de los resultados y la relevancia de las conclusiones. Los evaluadores emiten comentarios detallados y críticos, señalando fortalezas y áreas de mejora.
        El objetivo es garantizar que el proyecto cumpla con los estándares académicos requeridos para obtener la aprobación final. Esta fase representa una instancia clave donde el estudiante enfrenta la evaluación crítica y constructiva de expertos, lo que permite el refinamiento y validación de su trabajo académico.',
        'En la fase de Tribunales del proceso de titulación, se designa un comité evaluador compuesto por expertos en el área del proyecto. Estos evaluadores revisan minuciosamente el trabajo presentado por el estudiante, ofreciendo análisis detallados y críticos. La finalidad es garantizar la calidad académica del proyecto y su cumplimiento con estándares exigentes. Esta fase proporciona una evaluación crítica y constructiva que valida y perfecciona el trabajo académico del estudiante.',
        'TRIBUNALS_PHASE',
        3,
        103),
       (124,
        'La asignación de la fase de defensa generalmente implica coordinar con los miembros del comité evaluador, asegurándose de su disponibilidad para participar en la sesión de defensa. Se establece una fecha y hora conveniente para el estudiante y los evaluadores. Además, se comunica al estudiante quiénes serán los miembros del comité evaluador que participarán en su defensa, brindándoles información sobre los roles y expectativas para ese día.
        Es crucial que esta asignación se realice con anticipación suficiente para permitir que el estudiante se prepare adecuadamente para la defensa, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión del proyecto. Esto brinda al estudiante el tiempo necesario para afinar su presentación, preparar respuestas a posibles preguntas y fortalecer su comprensión sobre el proyecto en general.',
        'La asignación de la fase de defensa en un proceso de titulación implica la coordinación de la fecha, hora y los miembros del comité evaluador que participarán en la evaluación del proyecto de grado del estudiante. Esto se hace para permitir una preparación adecuada por parte del estudiante y para garantizar que la defensa se realice de manera efectiva, permitiendo la presentación y argumentación adecuada del trabajo académico.',
        'ASSIGN_DEFENSE_PHASE',
        4,
        103),
       (125,
        'La fase previa a la defensa en el proceso de titulación es un período crucial donde el estudiante se prepara meticulosamente para la presentación y defensa de su proyecto de grado ante un comité evaluador. Durante esta etapa, el estudiante realiza una revisión exhaustiva de su trabajo, teniendo en cuenta los comentarios y sugerencias recibidos durante la fase de revisión.
        El objetivo principal de la pre-defensa es perfeccionar el proyecto en función de los comentarios proporcionados por los revisores. Esto implica realizar ajustes, clarificar aspectos ambiguos, fortalecer argumentos, validar resultados y asegurarse de que la presentación esté bien estructurada y sea persuasiva.
        Además de la preparación del contenido, la pre-defensa implica práctica y entrenamiento para la presentación oral. El estudiante ensaya su exposición, anticipando posibles preguntas y preparándose para responder de manera clara y fundamentada. También puede recibir retroalimentación de mentores o profesores para mejorar su capacidad de comunicación y manejo de la sesión de defensa.',
        'La fase previa a la defensa es un período de refinamiento y preparación intensiva donde el estudiante ajusta su proyecto, se prepara para la presentación oral y se asegura de estar completamente listo para enfrentar la evaluación del comité evaluador',
        'PRE_DEFENSE_PHASE',
        5,
        103),
       (126,
        'La fase de defensa es esencial en el proceso del proyecto de grado, ya que el estudiante presenta su trabajo ante un comité evaluador. En esta etapa, se espera que el estudiante exponga y justifique sus decisiones, responda preguntas y argumente la validez de sus resultados. La defensa representa un paso crucial para obtener la aprobación final de su proyecto.
          Durante la defensa, se pone a prueba el conocimiento adquirido a lo largo del proyecto y se demuestra la solidez del trabajo realizado. El estudiante debe mostrar dominio sobre el tema, evidenciar comprensión de la metodología empleada y ser capaz de defender cada aspecto de su investigación o proyecto.
          El comité evaluador puede formular preguntas para profundizar en diferentes áreas del trabajo presentado, desde la fundamentación teórica hasta la aplicación de la metodología y la interpretación de los resultados. Además, se espera que el estudiante argumente la relevancia y contribución de su proyecto al campo de estudio correspondiente.
          La defensa brinda la oportunidad de demostrar habilidades comunicativas, capacidad de análisis y pensamiento crítico. Es un momento en el que el estudiante debe mostrar confianza en su trabajo, ser capaz de defenderlo ante posibles críticas y demostrar cómo su investigación o proyecto aporta al conocimiento existente.
          En resumen, la fase de defensa del proyecto de grado implica presentar y justificar el trabajo ante un comité evaluador, demostrando conocimiento, solidez en el proyecto y habilidades de argumentación, siendo crucial para obtener la aprobación final del mismo.',
        'La fase de defensa en el proceso del proyecto de grado es clave: el estudiante presenta su trabajo ante un comité evaluador, justifica decisiones, responde preguntas y valida resultados. Aquí se demuestra el dominio del tema, la solidez del trabajo y la contribución al campo. La defensa evalúa habilidades de comunicación, análisis y argumentación, siendo determinante para la aprobación final del proyecto.',
        'DEFENSE_PHASE',
        6,
        103),
       (127,
        'La fase posterior a la defensa en el proceso de titulación es un momento significativo que sigue inmediatamente después de que el estudiante haya presentado y defendido su proyecto de grado ante el comité evaluador. Esta etapa marca el cierre del proceso académico, pero también implica una serie de pasos y consideraciones importantes.
        Tras la defensa, el estudiante puede experimentar un período de espera mientras el comité evaluador deliberará y tomará una decisión con respecto a la aprobación del proyecto. Durante este tiempo, es común sentir una mezcla de emociones, como nerviosismo y expectación, mientras se espera el resultado final.
        Una vez que se ha recibido la evaluación del comité y se ha confirmado la aprobación del proyecto, el estudiante puede proceder con los trámites administrativos necesarios para finalizar su titulación. Esto puede incluir la presentación de documentación adicional, la firma de formularios o cualquier otro requisito específico de la institución educativa.',
        'La fase posterior a la defensa implica esperar el veredicto del comité evaluador, completar los trámites finales requeridos por la institución educativa y reflexionar sobre la experiencia académica en su totalidad. Es un período donde el estudiante culmina su proceso de titulación y se prepara para dar el siguiente paso en su trayectoria profesional o académica',
        'POST_DEFENSE_PHASE',
        7,
        103);
------------------------------------------------------------------------------------------------------------------------
-- PLACE TO DEFENSE
INSERT INTO place_to_defense_ (id_, capacity_, location_, name_)
VALUES (100, 100, 'Auditorio', 'Auditorio'),
       (101, 80, 'Sala de conferencias', 'Sala A'),
       (102, 120, 'Salón principal', 'Gran Salón'),
       (103, 50, 'Aula magna', 'Aula Magna 1'),
       (104, 200, 'Patio central', 'Patio Central'),
       (105, 150, 'Sala de reuniones', 'Sala de Juntas'),
       (106, 90, 'Anfiteatro', 'Anfiteatro Exterior'),
       (107, 70, 'Salón de actos', 'Salón Actos'),
       (108, 180, 'Gimnasio', 'Gimnasio Principal'),
       (109, 40, 'Sala de exposiciones', 'Exposiciones Hall');

-- DEFENSE
INSERT INTO defense_ (id_, date_, end_time_, start_time_, substitute_name_, place_to_defense_id_, project_id_)
VALUES (101, '2023-11-20', '14:00', '12:00', 'Juan Pérez', 103, 101),
       (102, '2023-11-22', '16:30', '15:00', 'María Gómez', 108, 108),
       (103, '2023-11-25', '13:00', '11:30', 'Carlos López', 107, 111),
       (104, '2023-11-28', '15:30', '14:00', 'Ana Martínez', 106, 117);
------------------------------------------------------------------------------------------------------------------------
-- GENERAL ACTIVITIES
INSERT INTO general_activity_ (id_, activity_date_, activity_description_, activity_name_)
VALUES (100, '2024-08-01', 'Fecha límite para presentar correcciones sugeridas en la defensa',
        'Correcciones post-defensa'),
       (101, '2024-08-15', 'Fecha límite para entregar versión final corregida del trabajo',
        'Entrega versión final corregida'),
       (102, '2024-09-01', 'Fecha de revisión de documentos finales por parte de comité evaluador',
        'Revisión de documentos finales'),
       (103, '2024-09-15', 'Fecha límite para entrega de documentación requerida para trámite de titulación',
        'Entrega de documentación para trámite'),
       (104, '2024-10-01', 'Fecha límite para pago de derechos de titulación', 'Pago de derechos de titulación'),
       (105, '2024-10-15', 'Fecha de entrega de constancia de culminación de trámite de titulación',
        'Entrega de constancia de titulación'),
       (106, '2024-11-01', 'Fecha de ceremonia de titulación', 'Ceremonia de titulación'),
       (107, '2024-11-15', 'Fecha límite para solicitar expedición de título', 'Solicitud de expedición de título'),
       (108, '2024-12-01', 'Fecha de emisión de títulos', 'Emisión de títulos'),
       (109, '2024-12-15', 'Fecha límite para retiro de títulos emitidos', 'Retiro de títulos'),
       (110, '2025-01-01', 'Fecha límite para solicitar reedición de título en caso de errores',
        'Solicitud de reedición de título'),
       (111, '2025-01-15', 'Fecha límite para realizar trámite de legalización del título',
        'Trámite de legalización de título'),
       (112, '2025-02-01', 'Fecha límite para envío de documentos para apostillado del título',
        'Envío de documentos para apostillado'),
       (113, '2025-02-15', 'Fecha de entrega de título apostillado o legalizado',
        'Entrega de título apostillado o legalizado'),
       (114, '2025-03-01', 'Fecha límite para solicitar certificación de título',
        'Solicitud de certificación de título'),
       (115, '2025-03-15', 'Fecha de emisión de certificados de título', 'Emisión de certificados de título'),
       (116, '2025-04-01', 'Fecha límite para realizar trámite de registro de título en colegios profesionales',
        'Registro de título en colegios profesionales'),
       (117, '2025-04-15', 'Fecha de confirmación de registro de título en colegios profesionales',
        'Confirmación de registro en colegios profesionales'),
       (118, '2025-05-01', 'Fecha límite para realizar trámite de convalidación de título en el extranjero',
        'Convalidación de título en el extranjero'),
       (119, '2025-05-15', 'Fecha de confirmación de convalidación de título en el extranjero',
        'Confirmación de convalidación en el extranjero'),
       (120, '2025-06-01', 'Fecha límite para solicitar duplicado de título por pérdida o deterioro',
        'Solicitud de duplicado por pérdida o deterioro'),
       (121, '2025-06-15', 'Fecha de emisión de duplicados de título', 'Emisión de duplicados de título'),
       (122, '2025-07-01', 'Fecha límite para trámite de homologación de título en el país',
        'Homologación de título en el país'),
       (123, '2025-07-15', 'Fecha de confirmación de homologación de título en el país',
        'Confirmación de homologación en el país'),
       (124, '2025-08-01', 'Fecha límite para solicitud de cambio de denominación de título',
        'Solicitud de cambio de denominación de título'),
       (125, '2025-08-15', 'Fecha de aprobación de cambio de denominación de título',
        'Aprobación de cambio de denominación de título'),
       (126, '2025-09-01', 'Fecha límite para entrega de documentación para reconocimiento de títulos extranjeros',
        'Reconocimiento de títulos extranjeros'),
       (127, '2025-09-15', 'Fecha de resolución de reconocimiento de títulos extranjeros',
        'Resolución de reconocimiento de títulos extranjeros'),
       (128, '2025-10-01', 'Fecha límite para solicitud de certificado supletorio temporal',
        'Solicitud de certificado supletorio temporal'),
       (129, '2025-10-15', 'Fecha de emisión de certificados supletorios temporales',
        'Emisión de certificados supletorios temporales');

-- PERSONAL ACTIVITIES
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES
    -- Proyecto 100: 'Sistema Integral del Proceso de Titulacion de Informatica y Sistemas'
    (100, '2023-01-10', 'Revisión de requisitos del sistema de titulación.', 'Revisión de Requisitos', 100),
    (101, '2023-01-15', 'Definición de la estructura de la base de datos.', 'Diseño de Base de Datos', 100),
    (102, '2023-01-20', 'Investigación sobre frameworks de desarrollo web.', 'Investigación de Frameworks', 100),
    (103, '2023-01-25', 'Desarrollo del módulo de registro de estudiantes.', 'Desarrollo de Módulo', 100),
    (104, '2023-02-05', 'Pruebas de integración del sistema de titulación.', 'Pruebas de Integración', 100),
    (105, '2023-02-10', 'Implementación de medidas de seguridad en la plataforma.', 'Implementación de Seguridad', 100),
    (106, '2023-02-15', 'Preparación de la presentación para la defensa del proyecto.', 'Preparación de Presentación',
     100),
    (107, '2023-02-20', 'Ejecución de simulacro de defensa del proyecto.', 'Simulacro de Defensa', 100),
    (108, '2023-02-25', 'Ajustes finales y revisión antes de la defensa oficial.', 'Revisión Final', 100),
    (109, '2023-03-05', 'Presentación final y defensa del Sistema Integral de Titulación.', 'Defensa Final', 100);

-- Proyecto 101: 'Proyecto de Desarrollo de Aplicación Web'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (200, '2023-01-10', 'Recolección de requerimientos para la aplicación web.', 'Recolección de Requerimientos',
        101),
       (201, '2023-01-15', 'Diseño de la interfaz de usuario de la aplicación.', 'Diseño de Interfaz de Usuario', 101),
       (202, '2023-01-20', 'Desarrollo del backend de la aplicación.', 'Desarrollo del Backend', 101),
       (203, '2023-01-25', 'Creación de los primeros prototipos de la aplicación.', 'Creación de Prototipos', 101),
       (204, '2023-02-05', 'Pruebas de funcionalidad y rendimiento.', 'Pruebas de Funcionalidad', 101),
       (205, '2023-02-10', 'Optimización del código y corrección de errores.', 'Optimización de Código', 101),
       (206, '2023-02-15', 'Implementación de medidas de seguridad en la aplicación.', 'Implementación de Seguridad',
        101),
       (207, '2023-02-20', 'Preparación de la documentación para la fase de defensa.', 'Preparación de Documentación',
        101),
       (208, '2023-02-25', 'Ensayo general para la presentación del proyecto.', 'Ensayo de Presentación', 101),
       (209, '2023-03-05', 'Defensa oficial del Proyecto de Aplicación Web.', 'Defensa Oficial', 101);
-- Proyecto 102: 'Estudio Comparativo de Lenguajes de Programación'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (300, '2023-01-10', 'Investigación sobre características de lenguajes de programación.',
        'Investigación de Lenguajes', 102),
       (301, '2023-01-15', 'Análisis de casos de uso de distintos lenguajes.', 'Análisis de Casos de Uso', 102),
       (302, '2023-01-20', 'Evaluación de rendimiento y eficiencia de lenguajes seleccionados.',
        'Evaluación de Rendimiento', 102),
       (303, '2023-01-25', 'Comparación de ventajas y desventajas de cada lenguaje.', 'Comparación de Ventajas', 102),
       (304, '2023-02-05', 'Elaboración de informe preliminar de estudio comparativo.', 'Informe Preliminar', 102),
       (305, '2023-02-10', 'Revisión y ajustes al informe según feedback recibido.', 'Revisión de Informe', 102),
       (306, '2023-02-15', 'Preparación de la presentación para la fase de pre-defensa.', 'Preparación de Presentación',
        102),
       (307, '2023-02-20', 'Ensayo general de la presentación del estudio comparativo.', 'Ensayo de Presentación', 102),
       (308, '2023-02-25', 'Revisión final de detalles antes de la fase de defensa oficial.', 'Revisión Final', 102),
       (309, '2023-03-05', 'Presentación y defensa del Estudio Comparativo de Lenguajes de Programación.',
        'Defensa Final', 102);
-- Continuar la estructura para los proyectos restantes, adaptando las descripciones a cada proyecto
-- Proyecto 103: 'Implementación de Sistemas de Seguridad en Redes'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (400, '2023-01-10', 'Análisis de vulnerabilidades en la red a proteger.', 'Análisis de Vulnerabilidades', 103),
       (401, '2023-01-15', 'Diseño de estrategias de seguridad para la red identificada.', 'Diseño de Estrategias',
        103),
       (402, '2023-01-20', 'Implementación de firewalls y sistemas de detección de intrusos.',
        'Implementación de Firewalls', 103),
       (403, '2023-01-25', 'Configuración de medidas de seguridad en routers y switches.', 'Configuración de Seguridad',
        103),
       (404, '2023-02-05', 'Evaluación de la efectividad de las medidas implementadas.', 'Evaluación de Efectividad',
        103),
       (405, '2023-02-10', 'Preparación de informe preliminar sobre las mejoras en seguridad.', 'Informe Preliminar',
        103),
       (406, '2023-02-15', 'Revisión de políticas de seguridad y ajustes según retroalimentación.',
        'Revisión de Políticas', 103),
       (407, '2023-02-20', 'Preparación de la presentación para la fase de defensa.', 'Preparación de Presentación',
        103),
       (408, '2023-02-25', 'Ensayo general para la presentación del proyecto de seguridad en redes.',
        'Ensayo de Presentación', 103),
       (409, '2023-03-05', 'Defensa oficial de la Implementación de Sistemas de Seguridad en Redes.', 'Defensa Final',
        103);

-- Proyecto 104: 'Desarrollo de Software de Gestión Empresarial'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (500, '2023-01-10', 'Recolección de requisitos para el software de gestión empresarial.',
        'Recolección de Requisitos', 104),
       (501, '2023-01-15', 'Diseño de la arquitectura del software y base de datos.', 'Diseño de Arquitectura', 104),
       (502, '2023-01-20', 'Desarrollo de módulos principales del software.', 'Desarrollo de Módulos', 104),
       (503, '2023-01-25', 'Integración de los diferentes módulos del software.', 'Integración de Módulos', 104),
       (504, '2023-02-05', 'Pruebas de funcionalidad y corrección de errores identificados.',
        'Pruebas de Funcionalidad', 104),
       (505, '2023-02-10', 'Implementación de interfaz de usuario y experiencia de usuario.', 'Implementación de UI/UX',
        104),
       (506, '2023-02-15', 'Preparación de la documentación técnica del software.', 'Preparación de Documentación',
        104),
       (507, '2023-02-20', 'Ensayo general para la presentación del software de gestión.', 'Ensayo de Presentación',
        104),
       (508, '2023-02-25', 'Revisión final del software y documentación antes de la defensa.', 'Revisión Final', 104),
       (509, '2023-03-05', 'Presentación y defensa del Desarrollo de Software de Gestión Empresarial.', 'Defensa Final',
        104);
-- Proyecto 105: 'Análisis de Vulnerabilidades en Sistemas Operativos'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (600, '2023-01-10', 'Identificación de sistemas operativos a analizar.', 'Identificación de Sistemas', 105),
       (601, '2023-01-15', 'Evaluación de posibles vulnerabilidades conocidas en los sistemas.',
        'Evaluación de Vulnerabilidades', 105),
       (602, '2023-01-20', 'Realización de pruebas de penetración y detección de brechas.', 'Pruebas de Penetración',
        105),
       (603, '2023-01-25', 'Análisis de resultados y categorización de riesgos encontrados.', 'Análisis de Riesgos',
        105),
       (604, '2023-02-05', 'Preparación de informe detallado sobre las vulnerabilidades halladas.',
        'Informe de Vulnerabilidades', 105),
       (605, '2023-02-10', 'Revisión y documentación de posibles soluciones a las vulnerabilidades.',
        'Documentación de Soluciones', 105),
       (606, '2023-02-15', 'Preparación de la presentación para la fase de post-defensa.',
        'Preparación de Presentación', 105),
       (607, '2023-02-20', 'Repaso general y corrección de detalles previo a la presentación final.',
        'Repaso y Corrección', 105),
       (608, '2023-02-25', 'Preparación final para la defensa oficial del Análisis de Vulnerabilidades.',
        'Preparación Final', 105),
       (609, '2023-03-05', 'Defensa oficial del Análisis de Vulnerabilidades en Sistemas Operativos.', 'Defensa Final',
        105);

-- Proyecto 106: 'Diseño de Base de Datos Escalable'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (700, '2023-01-10', 'Definición de requisitos y estructura de la base de datos.', 'Definición de Requisitos',
        106),
       (701, '2023-01-15', 'Diseño conceptual y diagramación inicial de la base de datos.', 'Diseño Conceptual', 106),
       (702, '2023-01-20', 'Evaluación de tecnologías y plataformas para la implementación.',
        'Evaluación de Tecnologías', 106),
       (703, '2023-01-25', 'Creación de la base de datos piloto para pruebas de concepto.', 'Creación de Base Piloto',
        106),
       (704, '2023-02-05', 'Optimización y ajustes según pruebas de desempeño y escalabilidad.',
        'Optimización de Desempeño', 106),
       (705, '2023-02-10', 'Documentación detallada del diseño y funcionalidades de la base de datos.',
        'Documentación Detallada', 106),
       (706, '2023-02-15', 'Preparación de la presentación para la fase de pre-defensa.', 'Preparación de Presentación',
        106),
       (707, '2023-02-20', 'Ensayo general de la presentación del Diseño de Base de Datos.', 'Ensayo de Presentación',
        106),
       (708, '2023-02-25', 'Revisión final de detalles antes de la fase de defensa oficial.', 'Revisión Final', 106),
       (709, '2023-03-05', 'Presentación y defensa del Diseño de Base de Datos Escalable.', 'Defensa Final', 106);
-- Proyecto 107: 'Evaluación de Rendimiento de Algoritmos de Machine Learning'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (800, '2023-01-10', 'Selección de algoritmos de machine learning a evaluar.', 'Selección de Algoritmos', 107),
       (801, '2023-01-15', 'Implementación de algoritmos seleccionados en entorno de pruebas.',
        'Implementación de Algoritmos', 107),
       (802, '2023-01-20', 'Recopilación y preparación de conjuntos de datos para evaluación.',
        'Preparación de Conjuntos de Datos', 107),
       (803, '2023-01-25', 'Entrenamiento y ajuste de parámetros de los modelos de machine learning.',
        'Entrenamiento de Modelos', 107),
       (804, '2023-02-05', 'Evaluación de rendimiento y comparativa entre distintos algoritmos.',
        'Evaluación de Rendimiento', 107),
       (805, '2023-02-10', 'Elaboración de informe con resultados y conclusiones.', 'Informe de Resultados', 107),
       (806, '2023-02-15', 'Preparación de la presentación para la fase de asignación de defensa.',
        'Preparación de Presentación', 107),
       (807, '2023-02-20', 'Repaso general y ajustes finales en la presentación.', 'Repaso y Ajustes', 107),
       (808, '2023-02-25', 'Preparación final para la defensa oficial de la Evaluación de Rendimiento.',
        'Preparación Final', 107),
       (809, '2023-03-05', 'Defensa oficial de la Evaluación de Rendimiento de Algoritmos de Machine Learning.',
        'Defensa Final', 107);

-- Proyecto 108: 'Desarrollo de Aplicaciones Móviles Innovadoras'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (900, '2023-01-10', 'Ideación y selección de conceptos para las aplicaciones móviles.', 'Ideación de Conceptos',
        108),
       (901, '2023-01-15', 'Diseño de la arquitectura y flujo de las aplicaciones.', 'Diseño de Arquitectura', 108),
       (902, '2023-01-20', 'Desarrollo de prototipos de las aplicaciones seleccionadas.', 'Desarrollo de Prototipos',
        108),
       (903, '2023-01-25', 'Pruebas de usabilidad y experiencia de usuario en los prototipos.', 'Pruebas de Usabilidad',
        108),
       (904, '2023-02-05', 'Refinamiento de diseños y ajustes según retroalimentación recibida.',
        'Refinamiento de Diseños', 108),
       (905, '2023-02-10', 'Desarrollo de versión beta de las aplicaciones móviles.', 'Desarrollo de Versión Beta',
        108),
       (906, '2023-02-15', 'Preparación de la documentación técnica y funcional de las apps.',
        'Preparación de Documentación', 108),
       (907, '2023-02-20', 'Ensayo general para la presentación de las Aplicaciones Móviles Innovadoras.',
        'Ensayo de Presentación', 108),
       (908, '2023-02-25', 'Revisión final de las aplicaciones y documentación antes de la defensa.', 'Revisión Final',
        108),
       (909, '2023-03-05', 'Presentación y defensa del Desarrollo de Aplicaciones Móviles Innovadoras.',
        'Defensa Final', 108);
-- Proyecto 109: 'Investigación en Inteligencia Artificial'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1000, '2023-01-10', 'Recolección de literatura relevante sobre inteligencia artificial.',
        'Recolección de Literatura', 109),
       (1001, '2023-01-15', 'Análisis y síntesis de los conceptos clave encontrados.', 'Análisis de Conceptos', 109),
       (1002, '2023-01-20', 'Experimentación con algoritmos y técnicas de IA.', 'Experimentación con Algoritmos', 109),
       (1003, '2023-01-25', 'Desarrollo de modelos y pruebas en conjuntos de datos.', 'Desarrollo de Modelos', 109),
       (1004, '2023-02-05', 'Evaluación y comparativa de resultados obtenidos.', 'Evaluación de Resultados', 109),
       (1005, '2023-02-10', 'Elaboración de informe detallado sobre la investigación realizada.',
        'Informe de Investigación', 109),
       (1006, '2023-02-15', 'Preparación de la presentación para la fase de revisión.', 'Preparación de Presentación',
        109),
       (1007, '2023-02-20', 'Repaso final y ajustes en la presentación para revisión.', 'Repaso y Ajustes', 109),
       (1008, '2023-02-25', 'Preparación final para la defensa oficial de la Investigación en IA.', 'Preparación Final',
        109),
       (1009, '2023-03-05', 'Defensa oficial de la Investigación en Inteligencia Artificial.', 'Defensa Final', 109);

-- Proyecto 110: 'Desarrollo de Plataforma de E-commerce'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1100, '2023-01-10', 'Definición de requisitos y funcionalidades de la plataforma.', 'Definición de Requisitos',
        110),
       (1101, '2023-01-15', 'Diseño de la arquitectura de la plataforma y estructura de datos.',
        'Diseño de Arquitectura', 110),
       (1102, '2023-01-20', 'Desarrollo de la infraestructura base y backend de la plataforma.',
        'Desarrollo de Infraestructura', 110),
       (1103, '2023-01-25', 'Implementación del frontend y experiencia de usuario.', 'Implementación de Frontend', 110),
       (1104, '2023-02-05', 'Integración de métodos de pago y seguridad en la plataforma.',
        'Integración de Métodos de Pago', 110),
       (1105, '2023-02-10', 'Pruebas de funcionamiento y usabilidad en diferentes dispositivos.',
        'Pruebas de Usabilidad', 110),
       (1106, '2023-02-15', 'Preparación de la documentación técnica y funcional de la plataforma.',
        'Preparación de Documentación', 110),
       (1107, '2023-02-20', 'Ensayo general para la presentación del Desarrollo de la Plataforma de E-commerce.',
        'Ensayo de Presentación', 110),
       (1108, '2023-02-25', 'Revisión final de la plataforma y documentación antes de la defensa.', 'Revisión Final',
        110),
       (1109, '2023-03-05', 'Presentación y defensa del Desarrollo de Plataforma de E-commerce.', 'Defensa Final', 110);
-- Proyecto 111: 'Investigación en Criptografía'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1200, '2023-01-10', 'Revisión de literatura especializada en criptografía.', 'Revisión de Literatura', 111),
       (1201, '2023-01-15', 'Análisis de algoritmos criptográficos y sus aplicaciones.', 'Análisis de Algoritmos', 111),
       (1202, '2023-01-20', 'Experimentación con algoritmos y pruebas de cifrado y descifrado.',
        'Experimentación con Algoritmos', 111),
       (1203, '2023-01-25', 'Evaluación de la seguridad y robustez de los algoritmos implementados.',
        'Evaluación de Seguridad', 111),
       (1204, '2023-02-05', 'Elaboración de informe detallado sobre los métodos criptográficos.',
        'Informe Criptográfico', 111),
       (1205, '2023-02-10', 'Preparación de la presentación para la fase de defensa.', 'Preparación de Presentación',
        111),
       (1206, '2023-02-15', 'Repaso final y ajustes en la presentación para la defensa.', 'Repaso y Ajustes', 111),
       (1207, '2023-02-20', 'Preparación final para la defensa oficial de la Investigación en Criptografía.',
        'Preparación Final', 111),
       (1208, '2023-02-25', 'Defensa oficial de la Investigación en Criptografía.', 'Defensa Final', 111);

-- Proyecto 112: 'Desarrollo de Sistema de Control de Versiones'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1300, '2023-01-10', 'Definición de requerimientos para el sistema de control de versiones.',
        'Definición de Requerimientos', 112),
       (1301, '2023-01-15', 'Diseño de la arquitectura del sistema y sus componentes.', 'Diseño de Arquitectura', 112),
       (1302, '2023-01-20', 'Desarrollo del sistema base y funcionalidades principales.',
        'Desarrollo de Funcionalidades', 112),
       (1303, '2023-01-25', 'Pruebas de integración y verificación de la funcionalidad del sistema.',
        'Pruebas de Integración', 112),
       (1304, '2023-02-05', 'Implementación de interfaces de usuario y experiencia de usuario.',
        'Implementación de UI/UX', 112),
       (1305, '2023-02-10', 'Pruebas exhaustivas de funcionamiento y rendimiento del sistema.',
        'Pruebas de Rendimiento', 112),
       (1306, '2023-02-15', 'Preparación de la documentación técnica y funcional del sistema.',
        'Preparación de Documentación', 112),
       (1307, '2023-02-20', 'Ensayo general para la presentación del Desarrollo del Sistema de Control de Versiones.',
        'Ensayo de Presentación', 112),
       (1308, '2023-02-25', 'Revisión final del sistema y documentación antes de la defensa.', 'Revisión Final', 112),
       (1309, '2023-03-05', 'Presentación y defensa del Desarrollo de Sistema de Control de Versiones.',
        'Defensa Final', 112);
-- Proyecto 113: 'Análisis de Big Data en Salud Pública'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1400, '2023-01-10', 'Recopilación y limpieza de datos de salud pública.', 'Recopilación y Limpieza de Datos',
        113),
       (1401, '2023-01-15', 'Procesamiento y análisis exploratorio de los datos recopilados.', 'Análisis Exploratorio',
        113),
       (1402, '2023-01-20', 'Aplicación de técnicas de minería de datos para identificar patrones.', 'Minería de Datos',
        113),
       (1403, '2023-01-25', 'Desarrollo de modelos predictivos para enfermedades o tendencias de salud.',
        'Desarrollo de Modelos Predictivos', 113),
       (1404, '2023-02-05', 'Evaluación de la precisión y validez de los modelos desarrollados.',
        'Evaluación de Modelos', 113),
       (1405, '2023-02-10', 'Elaboración de informe detallado sobre los hallazgos en salud pública.',
        'Informe de Salud Pública', 113),
       (1406, '2023-02-15', 'Preparación de la presentación para la fase de revisión.', 'Preparación de Presentación',
        113),
       (1407, '2023-02-20', 'Repaso final y ajustes en la presentación para la defensa.', 'Repaso y Ajustes', 113),
       (1408, '2023-02-25', 'Preparación final para la defensa oficial del Análisis de Big Data en Salud Pública.',
        'Preparación Final', 113),
       (1409, '2023-03-05', 'Defensa oficial del Análisis de Big Data en Salud Pública.', 'Defensa Final', 113);

-- Proyecto 114: 'Diseño de Interfaz de Usuario Inteligente'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1500, '2023-01-10', 'Identificación de requisitos de usuario para la interfaz inteligente.',
        'Identificación de Requisitos', 114),
       (1501, '2023-01-15', 'Diseño de la estructura y flujos de interacción de la interfaz.', 'Diseño de Estructura',
        114),
       (1502, '2023-01-20', 'Desarrollo de prototipos interactivos de la interfaz.', 'Desarrollo de Prototipos', 114),
       (1503, '2023-01-25', 'Pruebas de usabilidad y experiencia de usuario en los prototipos.',
        'Pruebas de Usabilidad', 114),
       (1504, '2023-02-05', 'Refinamiento de diseños y ajustes según feedback recibido.', 'Refinamiento de Diseños',
        114),
       (1505, '2023-02-10', 'Desarrollo de la versión final de la interfaz inteligente.', 'Desarrollo de Versión Final',
        114),
       (1506, '2023-02-15', 'Preparación de la documentación técnica y funcional de la interfaz.',
        'Preparación de Documentación', 114),
       (1507, '2023-02-20', 'Ensayo general para la presentación del Diseño de Interfaz de Usuario Inteligente.',
        'Ensayo de Presentación', 114),
       (1508, '2023-02-25', 'Revisión final de la interfaz y documentación antes de la defensa.', 'Revisión Final',
        114),
       (1509, '2023-03-05', 'Presentación y defensa del Diseño de Interfaz de Usuario Inteligente.', 'Defensa Final',
        114);
-- Proyecto 115: 'Desarrollo de Sistema de Gestión de Proyectos'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1600, '2023-01-10', 'Definición de requerimientos y alcance del sistema de gestión.', 'Definición de Alcance',
        115),
       (1601, '2023-01-15', 'Diseño de la estructura y funcionalidades del sistema.', 'Diseño de Funcionalidades', 115),
       (1602, '2023-01-20', 'Desarrollo de la base del sistema y sus módulos principales.', 'Desarrollo de Módulos',
        115),
       (1603, '2023-01-25', 'Integración de módulos y pruebas de funcionalidad del sistema.', 'Integración y Pruebas',
        115),
       (1604, '2023-02-05', 'Implementación de la interfaz de usuario y experiencia de usuario.',
        'Implementación de UI/UX', 115),
       (1605, '2023-02-10', 'Pruebas exhaustivas de funcionamiento y usabilidad del sistema.', 'Pruebas de Usabilidad',
        115),
       (1606, '2023-02-15', 'Preparación de la documentación técnica y funcional del sistema.',
        'Preparación de Documentación', 115),
       (1607, '2023-02-20', 'Ensayo general para la presentación del Desarrollo de Sistema de Gestión.',
        'Ensayo de Presentación', 115),
       (1608, '2023-02-25', 'Revisión final del sistema y documentación antes de la defensa.', 'Revisión Final', 115),
       (1609, '2023-03-05', 'Presentación y defensa del Desarrollo de Sistema de Gestión de Proyectos.',
        'Defensa Final', 115);

-- Proyecto 116: 'Investigación en Robótica Avanzada'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1700, '2023-01-10', 'Recopilación de información sobre avances en robótica avanzada.',
        'Recopilación de Información', 116),
       (1701, '2023-01-15', 'Análisis de aplicaciones actuales y potenciales en robótica.', 'Análisis de Aplicaciones',
        116),
       (1702, '2023-01-20', 'Experimentación con técnicas y algoritmos en proyectos de robótica.',
        'Experimentación con Algoritmos', 116),
       (1703, '2023-01-25', 'Desarrollo de prototipos o modelos para validación de conceptos.',
        'Desarrollo de Prototipos', 116),
       (1704, '2023-02-05', 'Evaluación de la efectividad y viabilidad de las soluciones desarrolladas.',
        'Evaluación de Soluciones', 116),
       (1705, '2023-02-10', 'Elaboración de informe detallado sobre los avances en robótica.', 'Informe de Avances',
        116),
       (1706, '2023-02-15', 'Preparación de la presentación para la fase de asignación de defensa.',
        'Preparación de Presentación', 116),
       (1707, '2023-02-20', 'Repaso general y corrección de detalles previo a la presentación final.',
        'Repaso y Corrección', 116),
       (1708, '2023-02-25', 'Preparación final para la defensa oficial de la Investigación en Robótica Avanzada.',
        'Preparación Final', 116),
       (1709, '2023-03-05', 'Defensa oficial de la Investigación en Robótica Avanzada.', 'Defensa Final', 116);
-- Proyecto 117: 'Desarrollo de Herramientas de Seguridad Informática'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1800, '2023-01-10', 'Identificación de requerimientos de seguridad informática.',
        'Identificación de Requerimientos', 117),
       (1801, '2023-01-15', 'Diseño de herramientas y algoritmos de seguridad.', 'Diseño de Herramientas', 117),
       (1802, '2023-01-20', 'Desarrollo de las herramientas y su integración en entornos.', 'Desarrollo e Integración',
        117),
       (1803, '2023-01-25', 'Pruebas de funcionalidad y seguridad de las herramientas desarrolladas.',
        'Pruebas de Seguridad', 117),
       (1804, '2023-02-05', 'Ajustes y mejoras según los resultados de las pruebas.', 'Mejoras y Ajustes', 117),
       (1805, '2023-02-10', 'Preparación de la documentación técnica y funcional de las herramientas.',
        'Preparación de Documentación', 117),
       (1806, '2023-02-15', 'Preparación de la presentación para la fase de defensa.', 'Preparación de Presentación',
        117),
       (1807, '2023-02-20', 'Repaso final y ajustes en la presentación para la defensa.', 'Repaso y Ajustes', 117),
       (1808, '2023-02-25', 'Preparación final para la defensa oficial del Desarrollo de Herramientas.',
        'Preparación Final', 117),
       (1809, '2023-03-05', 'Defensa oficial del Desarrollo de Herramientas de Seguridad Informática.', 'Defensa Final',
        117);
-- Proyecto 118: 'Estudio de Blockchain en Aplicaciones Financieras'
INSERT INTO activity_ (id_, activity_date_, activity_description_, activity_name_, project_id_)
VALUES (1900, '2023-01-10', 'Investigación sobre aplicaciones financieras de tecnología blockchain.',
        'Investigación de Aplicaciones', 118),
       (1901, '2023-01-15', 'Análisis de casos de uso y viabilidad en entornos financieros.', 'Análisis de Viabilidad',
        118),
       (1902, '2023-01-20', 'Experimentación con plataformas blockchain y sus funcionalidades.',
        'Experimentación con Plataformas', 118),
       (1903, '2023-01-25', 'Desarrollo de prototipos para demostrar el potencial financiero.',
        'Desarrollo de Prototipos', 118),
       (1904, '2023-02-05', 'Evaluación de la efectividad y seguridad de las soluciones blockchain.',
        'Evaluación de Soluciones', 118),
       (1905, '2023-02-10', 'Elaboración de informe detallado sobre el estudio de blockchain financiero.',
        'Informe de Estudio', 118),
       (1906, '2023-02-15', 'Preparación de la presentación para la fase de revisión.', 'Preparación de Presentación',
        118),
       (1907, '2023-02-20', 'Repaso final y ajustes en la presentación para la defensa.', 'Repaso y Ajustes', 118),
       (1908, '2023-02-25', 'Preparación final para la defensa oficial del Estudio de Blockchain.', 'Preparación Final',
        118),
       (1909, '2023-03-05', 'Defensa oficial del Estudio de Blockchain en Aplicaciones Financieras.', 'Defensa Final',
        118);
-- SEMESTER INFORMATION
INSERT INTO semester_information_ (id_, end_date_, in_progress_, period_, start_date_)
VALUES (100, '2023-05-31', TRUE, '1-2023', '2023-01-15'),
       (101, '2023-08-31', FALSE, '1-2023', '2023-06-01'),
       (102, '2023-12-15', FALSE, '1-2023', '2023-09-01'),
       (103, '2024-05-30', FALSE, '2-2024', '2024-01-10'),
       (104, '2024-08-31', FALSE, '2-2024', '2024-06-01'),
       (105, '2024-12-15', FALSE, '2-2024', '2024-09-01'),
       (106, '2025-05-30', FALSE, '3-2025', '2025-01-15'),
       (107, '2025-08-31', FALSE, '3-2025', '2025-06-01'),
       (108, '2025-12-15', FALSE, '3-2025', '2025-09-01'),
       (109, '2026-05-30', FALSE, '4-2026', '2026-01-15');


SELECT SETVAL('activity__seq', (SELECT MAX(id_) FROM activity_) + 5);
SELECT SETVAL('area__seq', (SELECT MAX(id_) FROM area_) + 5);
SELECT SETVAL('chat_history__seq', (SELECT MAX(id_) FROM chat_history_) + 5);
SELECT SETVAL('defense__seq', (SELECT MAX(id_) FROM defense_) + 5);
SELECT SETVAL('document__seq', (SELECT MAX(id_) FROM document_) + 5);
SELECT SETVAL('document_phase__seq', (SELECT MAX(id_) FROM document_phase_) + 5);
SELECT SETVAL('general_activity__seq', (SELECT MAX(id_) FROM general_activity_) + 5);
SELECT SETVAL('modality__seq', (SELECT MAX(id_) FROM modality_) + 5);
SELECT SETVAL('phase__seq', (SELECT MAX(id_) FROM phase_) + 5);
SELECT SETVAL('place_to_defense__seq', (SELECT MAX(id_) FROM place_to_defense_) + 5);
SELECT SETVAL('presentation__seq', (SELECT MAX(id_) FROM presentation_) + 5);
SELECT SETVAL('project__seq', (SELECT MAX(id_) FROM project_) + 5);
SELECT SETVAL('project_student__seq', (SELECT MAX(id_) FROM project_student_) + 5);
SELECT SETVAL('project_supervisor__seq', (SELECT MAX(id_) FROM project_supervisor_) + 5);
SELECT SETVAL('project_teacher__seq', (SELECT MAX(id_) FROM project_teacher_) + 5);
SELECT SETVAL('project_tribunal__seq', (SELECT MAX(id_) FROM project_tribunal_) + 5);
SELECT SETVAL('project_tutor__seq', (SELECT MAX(id_) FROM project_tutor_) + 5);
SELECT SETVAL('refresh_token__seq', (SELECT MAX(id_) FROM refresh_token_) + 5);
SELECT SETVAL('review__seq', (SELECT MAX(id_) FROM review_) + 5);
SELECT SETVAL('role__seq', (SELECT MAX(id_) FROM role_) + 5);
SELECT SETVAL('schedule__seq', (SELECT MAX(id_) FROM schedule_) + 5);
SELECT SETVAL('semester_information__seq', (SELECT MAX(id_) FROM semester_information_) + 5);
SELECT SETVAL('siptis_user__seq', (SELECT MAX(id_) FROM siptis_user_) + 5);
SELECT SETVAL('state__seq', (SELECT MAX(id_) FROM state_) + 5);
SELECT SETVAL('sub_area__seq', (SELECT MAX(id_) FROM sub_area_) + 5);
SELECT SETVAL('user_area__seq', (SELECT MAX(id_) FROM user_area_) + 5);
SELECT SETVAL('user_career__seq', (SELECT MAX(id_) FROM user_career_) + 5);
SELECT SETVAL('user_information__seq', (SELECT MAX(id_) FROM user_information_) + 5);
