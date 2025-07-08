  drop user yb cascade;
 
  create user yb
  identified by yb
  default tablespace users
  temporary tablespace temp
  quota 10m on users;

  grant connect, resource, dba 
  to yb;  

  drop table yb.employees   purge;
  drop table yb.departments purge;

  create table yb.departments as select * from hr.departments;
  create table yb.employees   as select * from hr.employees;

  drop sequence yb.sequence_employee;
  create sequence yb.sequence_employee start with 300;
