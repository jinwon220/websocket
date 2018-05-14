--system 立加饶

-- USER SQL
CREATE USER sockettest IDENTIFIED BY 1004 
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- QUOTAS

-- ROLES
GRANT "RESOURCE" TO sockettest WITH ADMIN OPTION;
GRANT "CONNECT" TO sockettest WITH ADMIN OPTION;
ALTER USER sockettest DEFAULT ROLE "RESOURCE","CONNECT";

-- SYSTEM PRIVILEGES

------------------------------------

-- sockettest 立加饶 

CREATE TABLE member
(
	userid VARCHAR2(50 BYTE), 
	password VARCHAR2(200 BYTE), 
	ROLE_NAME VARCHAR2(50 BYTE)
);

CREATE TABLE room
(
	roomnumber number, 
	roomname VARCHAR2(50 BYTE)
);

create sequence room_idx;