insert into MEDIUM (ID, DTYPE, CATID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (1,'Book','LM-000001','A Game of Thrones','HarperCollins Publishers','George R. R. Martin','9780006479888','Fantasy','Available');
insert into MEDIUM (ID, DTYPE, CATID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (2,'Book','LM-000002','The Hobbit','Random House Inc','J.R.R. Tolkien','9780345538376','Fantasy','Available');
insert into MEDIUM (ID, DTYPE, CATID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (3,'Book','LM-000003','The Girl with the Dragon Tattoo','Quercus Publishing Plc','Stieg Larsson','9781849162883','Crime','Available');
insert into MEDIUM (ID, DTYPE, CATID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (4,'Book','LM-000004','I, Robot','Random House Inc','Isaac Asimov','9780553382563','Scifi','Available');

insert into USER (USERNAME, NAME, SURNAME, PASSWORD, USERSTATE) values ('john', 'John','Snow','5f4dcc3b5aa765d61d8327deb882cf99','Active');
insert into USER (USERNAME, NAME, SURNAME, PASSWORD, USERSTATE) values ('arya', 'Arya','Stark','5f4dcc3b5aa765d61d8327deb882cf99','Active');
insert into USER (USERNAME, NAME, SURNAME, PASSWORD, USERSTATE) values ('terion','Terion','Lanister','5f4dcc3b5aa765d61d8327deb882cf99','Blocked');


insert into USER_ROLES (USERNAME, ROLENAME) values ('john', 'ADMIN');
insert into USER_ROLES (USERNAME, ROLENAME) values ('john', 'USER');
insert into USER_ROLES (USERNAME, ROLENAME) values ('arya', 'USER');
insert into USER_ROLES (USERNAME, ROLENAME) values ('terion', 'USER');



INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 10);

update user set password='password';



