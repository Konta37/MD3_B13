create database db_jv240408_session13_b1;
use db_jv240408_session13_b1;

create table Classes (
    classId varchar(15) not null primary key ,
    className varchar(100) not null unique ,
    status bit
);

create table Students(
    stuId int auto_increment ,
    fullName varchar(50),
    gender bit,
    birthday datetime,
    address varchar(200),
    classId varchar(15),
    primary key (stuId,classId),
    foreign key (classId) references Classes(classId)
);

delimiter $$
create procedure get_all_students()
begin
    select s.*,c.className from Students s inner join Classes c on s.classId=c.classId;
end $$;

delimiter $$
create procedure insert_student(
    fullName_in varchar(50),
    gender_in bit,
    birthday_in datetime,
    address_in varchar(200),
    classId_in varchar(15)
)
begin
    insert into Students(fullName,gender,birthday,address,classId) value (fullName_in,gender_in,birthday_in,address_in,classId_in);
end $$;

delimiter $$
create procedure update_student(
    stuId_in int,
    fullName_in varchar(50),
    gender_in bit,
    birthday_in datetime,
    address_in varchar(200),
    classId_in varchar(15)
)
begin
    update Students set fullName=fullName_in,gender =gender_in,birthday=birthday_in,address=address_in,classId=classId_in where stuId = stuId_in;
end $$;

delimiter $$
create procedure delete_student(stuId_in int)
begin
    delete from Students where stuId = stuId_in;
end $$;

delimiter $$
create procedure get_student_by_id(stuId_in int)
begin
    select s.*,c.className from Students s inner join Classes c on s.classId = c.classId;
end $$;

delimiter $$
create procedure get_student_by_name(stuName_in varchar(50))
begin
    select s.*,c.className from Students s inner join Classes c on s.classId = c.classId
    like concat('%',stuName_in,'%');
end $$;
