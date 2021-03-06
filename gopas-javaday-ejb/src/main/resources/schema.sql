CREATE TABLE MEDIUM (
	ID BIGINT NOT NULL, 
	DTYPE VARCHAR(31), 
	AVAILABILITY VARCHAR(255), 
	CATID VARCHAR(255), 
	TITLE VARCHAR(255), 
	PUBLISHER VARCHAR(255), 
	AUTHOR VARCHAR(255), 
	GENRE VARCHAR(255), 
	ISBN VARCHAR(255), 
	PRIMARY KEY (ID)
);

CREATE TABLE BORROWING (
	ID BIGINT NOT NULL, 
	BORROWED DATE, 
	PRIMARY KEY (ID)
);

CREATE TABLE BORROWING_MEDIUM (
	Borrowing_ID BIGINT NOT NULL, 
	books_ID BIGINT NOT NULL, 
	PRIMARY KEY (Borrowing_ID, books_ID)
);


CREATE TABLE USER (
	USERNAME VARCHAR(255) NOT NULL UNIQUE, 
	NAME VARCHAR(255), 
	PASSWORD VARCHAR(255) NOT NULL, 
	SURNAME VARCHAR(255), 
	USERSTATE VARCHAR(255), 
	PRIMARY KEY (USERNAME)
);

CREATE TABLE USER_ROLES (
	username VARCHAR(255) NOT NULL, 
	rolename VARCHAR(64) NOT NULL
);

ALTER TABLE USER_ROLES ADD CONSTRAINT UNQ_USER_ROLES_0 UNIQUE (username, rolename);

CREATE TABLE SEQUENCE (
	SEQ_NAME VARCHAR(50) NOT NULL, 
	SEQ_COUNT DECIMAL(38), 
	PRIMARY KEY (SEQ_NAME)
);
