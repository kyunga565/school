-- �б�
DROP SCHEMA IF EXISTS school;

-- �б�
CREATE SCHEMA school;

-- �л�
CREATE TABLE school.student (
	stud_id INT(11)     NOT NULL COMMENT '�й�', -- �й�
	name    VARCHAR(50) NOT NULL COMMENT '����', -- ����
	email   VARCHAR(50) NOT NULL COMMENT '����', -- ����
	dob     DATE        NULL     COMMENT '����' -- ����
)
COMMENT '�л�';

-- �л�
ALTER TABLE school.student
	ADD CONSTRAINT PK_student -- �л� �⺻Ű
		PRIMARY KEY (
			stud_id -- �й�
		);

insert into student values(3,'�����','kge@test.co.kr','2010-02-01');	
insert into student values (2,'������','dkb@test.co.kr','2000-12-12'),(3,'�����','kky@test.co.kr','1999-12-02');

select * from student;

select stud_id as studid , name,email,dob from student where stud_id = 2;
update student set name = '����', email='ky@test.co.kr',dob ='1977-05-05' where stud_id =2;

delete from student where stud_id =3;
select * from student;





