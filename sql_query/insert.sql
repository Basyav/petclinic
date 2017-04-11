INSERT INTO roles (id, name) VALUES
(1, 'CLIENT'), (2, 'EMPLOYEE'), (3, 'ADMIN');

INSERT INTO users
(id, login, password, created_date)
VALUES (1, 'owner', '$2a$10$gfHfR54gY01tWeQljVzaneA8MDt..yl9F/jejJ/AuqF4IKIlt9axC', '2017-04-12'), //pwd owner123
(2, 'employee', '$2a$10$LHpg.DHpzqCC08em5UTy3eqKWOOn3OfrnxYP2tQISvwBcoxbXvgpy', '2017-04-12'), //pwd employee123
(3, 'admin', '$2a$10$QENBOWRNK5y.nHYoV.5.L.5vcrm5oPGDVw3plxS.4Jj7A33GEXbIW', '2017-04-12'); //pwd admin123

INSERT INTO user_roles
(id_user, id_role)
VALUES (1, 1), (2, 2), (3,3);

INSERT INTO owners
(id, first_name, last_name, middle_name, address, id_user)
VALUES
(1, 'Любовь', 'Кошкина', 'Владимировна', 'ул. Стрелковой девизии', 1);

INSERT INTO employees
(id, first_name, last_name, middle_name, experience, id_user)
VALUES
(1, 'Любовь', 'Львова', 'Владимировна', 1, 2),
(2, 'Снежана', 'Самохвалова', 'Владимировна', 1, 3);





