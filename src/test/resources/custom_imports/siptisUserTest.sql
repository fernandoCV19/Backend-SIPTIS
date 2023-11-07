
INSERT INTO state(id, name) VALUES (1, 'STATE1');
INSERT INTO project(id, blue_book_path, name, perfil_path, period, phase, project_path, report_index, total_defense_points, modality_id, state_id)
            VALUES (1, 'Libro1', 'Name', 'Perfil1', '2-2023', 'Fase1', null, null, null, 1, 1);
INSERT INTO siptis_user(id, email, password) VALUES (1, 'usuario1@mail.com', '12345678');

INSERT INTO project_student(id, user_id, project_id) VALUES (1, 1, 1);

INSERT INTO refresh_token(id, expire_date, token, siptis_user_id) VALUES (1, '2023-10-30 17:19:53.804305-04', '0816a7f9-1aa5-411a-9f83-b97622e94669', 1);
-- INSERT INTO refresh_token(id, expire_date, token, siptis_user_id) VALUES (1, '2023-11-05 16:42:16.581371-04', '0816a7f9-1aa5-411a-9f83-b97622e94669', 1);
