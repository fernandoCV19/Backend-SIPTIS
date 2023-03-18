INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (1, "Supervisor1", "S", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (2, "Tutor1", "T", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (3, "Docente1", "D", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (4, "Tribunal1", "T", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (5, "Estudiante1", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (6, "Estudiante2", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (7, "Estudiante3", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (8, "Estudiante4", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (9, "Estudiante5", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (10, "Estudiante6", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (11, "Estudiante7", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (12, "Estudiante8", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (13, "Estudiante9", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (14, "Estudiante10", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (15, "Estudiante11", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (16, "Estudiante12", "E", "1234567", "54321", "111222333", "mail@mail.com");

INSERT INTO usuario(
	id, nombres, apellidos, celular, ci, codsis, email)
	VALUES (17, "Estudiante13", "E", "1234567", "54321", "111222333", "mail@mail.com");

-- Estudiantes y revisores

INSERT INTO supervisor_proyecto(
	id, aceptado, revisado, proyecto_grado_id, usuario_id)
	VALUES (1, false, false, 1, 1);

INSERT INTO supervisor_proyecto(
	id, aceptado, revisado, proyecto_grado_id, usuario_id)
	VALUES (2, false, true, 2, 1);

INSERT INTO supervisor_proyecto(
	id, aceptado, revisado, proyecto_grado_id, usuario_id)
	VALUES (3, true, false, 3, 1);


INSERT INTO tutor_proyecto(
	id, aceptado, revisado, proyecto_grado_id, usuario_id)
	VALUES (1, false, false, 4, 2);

INSERT INTO tutor_proyecto(
	id, aceptado, revisado, proyecto_grado_id, usuario_id)
	VALUES (2, false, true, 5, 2);

INSERT INTO tutor_proyecto(
	id, aceptado, revisado, proyecto_grado_id, usuario_id)
	VALUES (3, true, false, 6, 2);


INSERT INTO docente_tg2_proyecto(
	id, aceptado, revisado, usuario_id, proyecto_grado_id)
	VALUES (1, false, false, 7, 3);

INSERT INTO docente_tg2_proyecto(
	id, aceptado, revisado, usuario_id, proyecto_grado_id)
	VALUES (2, false, true, 8, 3);

INSERT INTO docente_tg2_proyecto(
	id, aceptado, revisado, usuario_id, proyecto_grado_id)
	VALUES (3, true, false, 9, 3);


INSERT INTO tribunal_proyecto(
	id, aceptado, nota_defensa, revisado, proyecto_grado_id, usuario_id)
	VALUES (1, false, null, false, 10, 4);

INSERT INTO tribunal_proyecto(
	id, aceptado, nota_defensa, revisado, proyecto_grado_id, usuario_id)
	VALUES (2, false, null, true, 11, 4);

INSERT INTO tribunal_proyecto(
	id, aceptado, nota_defensa, revisado, proyecto_grado_id, usuario_id)
	VALUES (3, true, null, false, 12, 4);

INSERT INTO tribunal_proyecto(
	id, aceptado, nota_defensa, revisado, proyecto_grado_id, usuario_id)
	VALUES (4, true, 100, false, 13, 4);


INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (1, 5, 1);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (2, 6, 2);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (3, 7, 3);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (4, 8, 4);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (5, 9, 5);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (6, 10, 6);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (7, 11, 7);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (8, 12, 8);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (9, 13, 9);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (10, 14, 10);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (11, 15, 11);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (12, 16, 12);

INSERT INTO estudiante_proyecto(
	id, usuario_id, proyecto_grado_id)
	VALUES (13, 17, 13);

-- Proyecto

INSERT INTO area_proyecto(
	id, nombre)
	VALUES (1, "Area1");

INSERT INTO area_proyecto(
	id, nombre)
	VALUES (2, "Area2");

INSERT INTO sub_area(
	id, nombre)
	VALUES (1, "SubArea1");

INSERT INTO sub_area(
	id, nombre)
	VALUES (2, "SubArea2");

INSERT INTO modalidad(
	id, nombre)
	VALUES (1, "Adscripcion");


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (1, "Libro1", "Perfil1", "Proyecto1", "Fase1", "ProyectoGrado1", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (1, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (1, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (2, "Libro2", "Perfil2", "Proyecto2", "Fase2", "ProyectoGrado2", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (2, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (2, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (3, "Libro3", "Perfil3", "Proyecto3", "Fase3", "ProyectoGrado3", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (3, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (3, 1);


--

INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (4, "Libro4", "Perfil4", "Proyecto4", "Fase4", "ProyectoGrado4", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (4, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (4, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (5, "Libro5", "Perfil5", "Proyecto5", "Fase5", "ProyectoGrado5", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (5, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (5, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (6, "Libro6", "Perfil6", "Proyecto6", "Fase6", "ProyectoGrado6", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (6, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (6, 1);


--

INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (7, "Libro7", "Perfil7", "Proyecto7", "Fase7", "ProyectoGrado7", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (7, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (7, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (8, "Libro8", "Perfil8", "Proyecto8", "Fase8", "ProyectoGrado8", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (8, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (8, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (9, "Libro9", "Perfil9", "Proyecto9", "Fase9", "ProyectoGrado9", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (9, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (9, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (10, "Libro10", "Perfil10", "Proyecto10", "Fase10", "ProyectoGrado10", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (10, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (10, 1);


INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (11, "Libro11", "Perfil11", "Proyecto11", "Fase11", "ProyectoGrado11", null, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (11, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (11, 1);



INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (12, "Libro12", "Perfil12", "Proyecto12", "Fase12", "ProyectoGrado12", 1, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (12, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (12, 1);

INSERT INTO defensa(
	id, hora_defensa, lugar_defensa, proyecto_grado_id)
	VALUES (1, "Hora1", "Defensa1", 12);



INSERT INTO proyecto_grado(
	id, dir_libro_azul, dir_perfil, dir_proyecto, fase, nombre, defensa_id, estado_id, modalidad_id)
	VALUES (13, "Libro13", "Perfil13", "Proyecto13", "Fase13", "ProyectoGrado13", 2, null, 1);

INSERT INTO proyecto_grado_area(
	proyecto_grado_id, area_id)
	VALUES (13, 1);

INSERT INTO proyecto_grado_sub_area(
	proyecto_grado_id, sub_area_id)
	VALUES (13, 1);

INSERT INTO defensa(
	id, hora_defensa, lugar_defensa, proyecto_grado_id)
	VALUES (2, "Hora2", "Defensa2", 13);