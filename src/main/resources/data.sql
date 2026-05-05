INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10001, 'JPA in 100 Steps', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10002, 'Spring in 150 Steps', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10003, 'Reactive Spring in 10 Steps', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10004, 'SpringBoot in 15 Steps', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10005, 'Apache Kafka in 100 Steps', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10006, 'TestContainers in 100 Steps', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10007, 'Dummy 1', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10008, 'Dummy 2', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10009, 'Dummy 3', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10010, 'Dummy 4', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10011, 'Dummy 5', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10012, 'Dummy 6', current_timestamp(), current_timestamp(), false);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted)
VALUES (10013, 'Dummy 7', current_timestamp(), current_timestamp(), false);

INSERT INTO passport(id, number)
VALUES (40001, 'N123401');
INSERT INTO passport(id, number)
VALUES (40002, 'NA34134');
INSERT INTO passport(id, number)
VALUES (40003, 'M134831');
INSERT INTO passport(id, number)
VALUES (40004, 'Z134893');

INSERT INTO student(id, name, passport_id)
VALUES (20001, 'Leon', 40001);
INSERT INTO student(id, name, passport_id)
VALUES (20002, 'Pepe', 40002);
INSERT INTO student(id, name, passport_id)
VALUES (20003, 'Sarah', 40003);
INSERT INTO student(id, name, passport_id)
VALUES (20004, 'Daniela', 40004);

INSERT INTO review(id, rating, description, course_id)
VALUES (50001, '5', 'Wonderful Course', 10004);
INSERT INTO review(id, rating, description, course_id)
VALUES (50002, '4', 'Great Course', 10004);
INSERT INTO review(id, rating, description, course_id)
VALUES (50003, '3', 'Good Course', 10002);

INSERT INTO student_course(student_id, course_id)
VALUES (20001, 10001);
INSERT INTO student_course(student_id, course_id)
VALUES (20002, 10001);
INSERT INTO student_course(student_id, course_id)
VALUES (20003, 10001);
INSERT INTO student_course(student_id, course_id)
VALUES (20001, 10003);
INSERT INTO student_course(student_id, course_id)
VALUES (20002, 10003);
INSERT INTO student_course(student_id, course_id)
VALUES (20004, 10004);