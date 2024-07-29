package konta.bai1.dao.impl;

import konta.bai1.dao.ClassDAO;
import konta.bai1.db.DatabaseUtility;
import konta.bai1.entity.Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAOImpl implements ClassDAO {
    @Override
    public List<Classes> findAll() {
        List<Classes> list = new ArrayList<Classes>();

        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareStatement("select * from Classes");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Classes a = new Classes();
                a.setClassId(rs.getInt("classId"));
                a.setClassName(rs.getString("className"));
                a.setStatus(rs.getBoolean("status"));

                list.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }
        return list;
    }

    @Override
    public Classes findById(Integer classID) {
        Classes u = null;

        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareStatement("select * from Classes where classId=?");
            pstmt.setInt(1, classID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                u = new Classes();
                u.setClassId(rs.getInt("classId"));
                u.setClassName(rs.getString("className"));
                u.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }

        return u;
    }

    @Override
    public boolean add(Classes classes) {
        boolean bl = false;

        Connection con;
        PreparedStatement pstmt;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareStatement("insert into Classes(className,status) values (?,?)");
            pstmt.setString(1, classes.getClassName());
            pstmt.setBoolean(2, classes.getStatus());

            int i = pstmt.executeUpdate();
            if (i > 0)
                bl = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public boolean edit(Classes classes) {
        boolean bl = false;

        Connection con;
        PreparedStatement pstmt;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareStatement("update Classes set className=?,status=? where classId=?");
            pstmt.setString(1, classes.getClassName());
            pstmt.setBoolean(2, classes.getStatus());

            pstmt.setInt(3, classes.getClassId());

            int i = pstmt.executeUpdate();
            if (i > 0)
                bl = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public boolean delete(Integer classID) {
        boolean bl = false;

        Connection con;
        PreparedStatement pstmt;

        con = DatabaseUtility.getConnection();
        try {
            pstmt = con.prepareStatement("delete from Classes where classId=?");
            pstmt.setInt(1, classID);
            int i = pstmt.executeUpdate();
            if (i > 0)
                bl = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }

        return bl;
    }

    @Override
    public List<Classes> findByName(String className) {
        List<Classes> list = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;

        con = DatabaseUtility.getConnection();
        try {
            if (className == null || className.isEmpty())
                className = "%";
            else
                className = "%" + className + "%";
            pstmt = con.prepareStatement("select * from Classes where className like ?");
            pstmt.setString(1, className);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Classes u = new Classes();
                u.setClassId(rs.getInt("classId"));
                u.setClassName(rs.getString("className"));
                u.setStatus(rs.getBoolean("status"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtility.closeConnection(con);
        }

        return list;
    }
}
