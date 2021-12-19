insert into User (username, email) values ('alex', 'alex@email.com');
insert into User (username, email) values ('ana', 'ana@email.com');

insert into Course (code, name, description) values ('java-1', 'Java OO', 'Java and Object Orientation: Encapsulation, Inheritance and Polymorphism.');
insert into Course (code, name, description) values ('java-2', 'Java Collections', 'Java Collections: Lists, Sets, Maps and more.');

insert into Enrollment (id_course, id_user) values (1, 2);
insert into Enrollment (id_course, id_user) values (1, 1);
insert into Enrollment (id_course, id_user) values (2, 1);
insert into Enrollment (id_course, id_user) values (2, 2);
