package konta.bai1.dao.impl;

import konta.bai1.dao.StudentDAO;
import konta.bai1.db.DatabaseUtility;
import konta.bai1.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<Student>();

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call get_all_students()}");
            rs = cstmt.executeQuery();
            while(rs.next()){
                Student e = new Student();
                e.setStuId(rs.getInt("stuId"));
                e.setFullName(rs.getString("fullName"));
                e.setGender(rs.getBoolean("gender"));
                e.setBirthday(rs.getDate("birthday"));
                e.setAddress(rs.getString("address"));
                e.setClassId(rs.getString("classId"));

                list.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return list;
    }

    @Override
    public Student findById(Integer studentId) {
        Student e = null;

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {

            cstmt = con.prepareCall("{call get_student_by_id(?)}");
            cstmt.setInt(1, studentId);
            rs = cstmt.executeQuery();
            if(rs.next()){
                e = new Student();
                e.setStuId(rs.getInt("stuId"));
                e.setFullName(rs.getString("fullName"));
                e.setGender(rs.getBoolean("gender"));
                e.setBirthday(rs.getDate("birthday"));
                e.setAddress(rs.getString("address"));
                e.setClassId(rs.getString("classId"));
            }
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return e;
    }

    @Override
    public boolean add(Student student) {
        boolean bl = false;

        Connection con;
        CallableStatement cstmt;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call insert_student(?,?,?,?,?)}");
            cstmt.setString(1, student.getFullName());
            cstmt.setBoolean(2, student.getGender());
            cstmt.setDate(3, new Date(student.getBirthday().getTime()));
            cstmt.setString(4, student.getAddress());
            cstmt.setString(5, student.getClassId());
            int i = cstmt.executeUpdate();
            if(i>0)
                bl = true;
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public boolean edit(Student student) {
        boolean bl = false;

        Connection con;
        CallableStatement cstmt;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call update_student(?,?,?,?,?,?)}");
            cstmt.setInt(1, student.getStuId());
            cstmt.setString(2, student.getFullName());
            cstmt.setBoolean(3, student.getGender());
            cstmt.setDate(4, new Date(student.getBirthday().getTime()));
            cstmt.setString(5, student.getAddress());
            cstmt.setString(6, student.getClassId());
            int i = cstmt.executeUpdate();
            if(i>0)
                bl = true;
        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public boolean delete(Integer studentId) {
        boolean bl = false;
        Connection con;
        CallableStatement cstmt;
        con = DatabaseUtility.getConnection();

        try {
            cstmt =  con.prepareCall("{call delete_student(?)}");
            cstmt.setInt(1, studentId);
            int i = cstmt.executeUpdate();
            if (i>0){
                bl = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeConnection(con);
        }
        return bl;
    }

    @Override
    public List<Student> findByName(String studentName) {
        List<Student> list = new ArrayList<>();

        Connection con;
        CallableStatement cstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            cstmt = con.prepareCall("{call get_student_by_name(?)}");
            if(studentName.equals("null"))
                studentName = "";
            cstmt.setString(1, studentName);
            rs = cstmt.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setStuId(rs.getInt("stuId"));
                s.setFullName(rs.getString("fullName"));
                s.setGender(rs.getBoolean("gender"));
                s.setBirthday(rs.getDate("birthday"));
                s.setAddress(rs.getString("address"));
                s.setClassId(rs.getString("classId"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseUtility.closeConnection(con);
        }

        return list;
    }
}
