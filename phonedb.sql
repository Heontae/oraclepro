--테이블, 시퀀스 삭제
DRop table person;
DROP SEQUENCE seq_person_id;

--테이블 생성
CREATE TABLE person (
  person_id	NUMBER(5),
  name	VARCHAR2(30) 	NOT NULL,
  hp	VARCHAR2(20),
  company varchar2(20),
  PRIMARY 	KEY(person_id)	
);
--시퀀스 생성
CREATE SEQUENCE seq_person_id INCREMENT BY 1 START WITH 1;

--person Insert
insert INTO person values(seq_person_id.nextVal,'이효리','010-1111-1111','02-1111-1111');
insert INTO person values(seq_person_id.nextVal,'정우성','010-2222-2222','02-2222-2222');
insert INTO person values(seq_person_id.nextVal,'유재석','010-3333-3333','02-3333-3333');
insert INTO person values(seq_person_id.nextVal,'이정재','010-4444-4444','02-4444-4444');
insert INTO person values(seq_person_id.nextVal,'서장훈','010-5555-5555','02-5555-5555');

--person Update
Update person
set hp = '010-9999-9999', --hp을 010-9999-9999으로 변경
    company = '02-9999-9999' --company을 02-9999-9999으로 변경
where person_id=4; --person_id가 4인 조건

--person Delete
delete person
where person_id=5; --person_id가 5이면 삭제

select * from person;