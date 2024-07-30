package konta.bai1.controller.Student;

import konta.bai1.dao.impl.ClassDAOImpl;
import konta.bai1.dao.impl.StudentDAOImpl;
import konta.bai1.entity.Classes;
import konta.bai1.entity.Student;
import konta.bai1.service.impl.ClassesServiceImpl;
import konta.bai1.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "LoadStudent", value = "/LoadStudent")
public class LoadStudent extends HttpServlet {
    private StudentDAOImpl studentDAO = new StudentDAOImpl();
    private StudentServiceImpl studentService = new StudentServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                deleteStudents(request,response);
                break;
            case "details":
                showDetail(request,response);
                break;
            case "search":
                searchStudents(request,response);
                break;
            default:
                listStudents(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "save":
                saveStudent(request,response);
                break;
            case "update":
                updateStudent(request,response);
                break;
            default:
                listStudents(request,response);
                break;
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("students", studentDAO.findAll());
        request.getRequestDispatcher("/views/student/student-list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Classes> listClass = new ClassesServiceImpl().findAll();
        request.setAttribute("listClass",listClass);
        request.getRequestDispatcher("/views/student/student-form.jsp").forward(request,response);
    }

    private void saveStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        try {
            birthday = sf.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String address = request.getParameter("address");
        String classId = request.getParameter("classId");

        Boolean studentGender = true;


        if (gender == null) studentGender = false;


        Student student = new Student(2,fullName, studentGender,birthday,address,classId);

        studentService.add(student);

        response.sendRedirect("LoadStudent");

    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existStudent = studentService.findById(id);
        request.setAttribute("student", existStudent);
        List<Classes> listClass = new ClassesServiceImpl().findAll();
        request.setAttribute("listClass",listClass);
        request.getRequestDispatcher("/views/student/student-form.jsp").forward(request,response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        try {
            birthday = sf.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String address = request.getParameter("address");
        String classId = request.getParameter("classId");

        Boolean studentGender = true;


        if (gender == null) studentGender = false;

        Student student = new Student(id, fullName, studentGender,birthday,address,classId);
        studentService.edit(student);

        response.sendRedirect("LoadStudent");
    }

    private void deleteStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        studentService.delete(id);

        response.sendRedirect("LoadStudent");
//        request.getRequestDispatcher("LoadStudent").forward(request,response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);

        request.setAttribute("student", student);
        request.getRequestDispatcher("/views/student/student-details.jsp").forward(request, response);
    }

    private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchQuery = request.getParameter("searchQuery");
        List<Student> searchResults = studentService.findByName(searchQuery);

        request.setAttribute("students", searchResults);
        request.getRequestDispatcher("/views/student/student-list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}