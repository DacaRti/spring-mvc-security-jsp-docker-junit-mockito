INSERT INTO users(username, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin', '{bcrypt}$2a$10$SjaaF5bAxX1.6r1eam4lkOvBKp7SLWUfzWVg.TnVnkHPOdvbPh4UK', 'admin@gmail.com', 'Admin', 'Admin', '2003-05-05', 1);
INSERT INTO users(username, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', '{bcrypt}$2a$10$vqXptj0XVvdGRHaYIgvgLuJCv0oemo6qoyrct23IckAxSEj2iz.Le', 'user@gmail.com', 'User', 'User', '1991-05-05', 2);