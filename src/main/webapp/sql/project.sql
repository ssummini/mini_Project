create table member(
member_seq number,
user_name varchar2(15),
user_id varchar2(15),
user_email varchar2(30),
user_pwd varchar2(20));


CREATE TABLE review (
    review_seq NUMBER PRIMARY KEY,
    user_id VARCHAR2(15),
    title VARCHAR2(50),
    content VARCHAR2(100),
    logtime DATE
);

CREATE SEQUENCE review_seq
START WITH 1       
INCREMENT BY 1     
MAXVALUE 1000
NOCACHE;


CREATE SEQUENCE member_seq
START WITH 1       
INCREMENT BY 1     
MAXVALUE 1000
NOCACHE;
