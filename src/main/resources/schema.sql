DROP TABLE IF EXISTS User;

CREATE TABLE User (
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS Course;

CREATE TABLE Course (
    id_course BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(500)
);

CREATE TABLE Enrollment (
    id_enrollment BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_course BIGINT NOT NULL,
    id_user BIGINT NOT NULL,
    dt_enrollment TIMESTAMP DEFAULT(CURDATE()),
    foreign key (id_user) references User,
    foreign key (id_course) references Course
);