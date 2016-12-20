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