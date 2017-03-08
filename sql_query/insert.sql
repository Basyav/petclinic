INSERT INTO user_roles (name) VALUES
('СLIENT'), ('EMPLOYEE'), ('ADMIN');

INSERT INTO users
(login, password, id_role, created_date)
VALUES
('test1', 'pwd1', '2', '2017-02-02');

INSERT INTO emloyees
(first_name, last_name, middle_name, experience, id_user)
VALUES
('Пупкин','Вася','Андреевич','2','1');


