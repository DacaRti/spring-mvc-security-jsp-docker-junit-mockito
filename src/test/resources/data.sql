INSERT INTO authorities (name)
VALUES ('admin');
INSERT INTO authorities (name)
VALUES ('user');

INSERT INTO users(username, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin', 'admin', 'admin@gmail.com', 'Admin', 'Admin', '2003-05-05', 1);
INSERT INTO users(username, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', 'user', 'user@gmail.com', 'User', 'User', '1991-05-05', 2);