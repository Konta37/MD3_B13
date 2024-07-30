package konta.bai1.controller.Classes;

import konta.bai1.dao.impl.ClassDAOImpl;
import konta.bai1.entity.Classes;
import konta.bai1.service.impl.ClassesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadClasses", value = "/LoadClasses")
public class LoadClasses extends HttpServlet {
    private ClassDAOImpl classDAO = new ClassDAOImpl();
    private ClassesServiceImpl classesService = new ClassesServiceImpl();

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
                deleteClasses(request,response);
                break;
            case "details":
                showDetail(request,response);
                break;
            case "search":
                searchClasses(request,response);
                break;
            default:
                listClasses(request,response);
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
                saveClass(request,response);
                break;
            case "update":
                updateClass(request,response);
                break;
            default:
                listClasses(request,response);
                break;
        }
    }

    private void listClasses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("classes", classDAO.findAll());
        request.getRequestDispatcher("/views/classes/class-list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/views/classes/class-form.jsp").forward(request,response);
    }

    private void saveClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("id");
        String className = request.getParameter("className");
        String status = request.getParameter("status");

        Boolean classStatus = true;

        if (status == null) classStatus = false;

        Classes classes = new Classes(classId,className, classStatus);

        classesService.add(classes);

        response.sendRedirect("LoadClasses");

    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("id");
        Classes existClass = classesService.findById(classId);
        System.out.println(existClass.getClassId());
        request.setAttribute("classD", existClass);
        request.getRequestDispatcher("/views/classes/class-form.jsp").forward(request,response);
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("id");
        String className = request.getParameter("className");
        String status = request.getParameter("status");

        Boolean accountClass = true;

        if (status == null) accountClass = false;

        Classes classes = new Classes(classId, className, accountClass);
        classesService.edit(classes);
        System.out.println(classes.getClassId());

        response.sendRedirect("LoadClasses");
    }

    private void deleteClasses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("id");
        boolean check = classesService.delete(classId);
//        System.out.println(classesService.delete(classId));
        if(!check){
            String errorDelete = "Can't delete student from Class";
            request.setAttribute("errorDelete",errorDelete);
        }
//        response.sendRedirect("LoadClasses");
        request.setAttribute("classes", classesService.findAll());
        request.getRequestDispatcher("/views/classes/class-list.jsp").forward(request,response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String classId = request.getParameter("id");
        Classes classes = classesService.findById(classId);

        request.setAttribute("class", classes);
        request.getRequestDispatcher("/views/classes/class-details.jsp").forward(request, response);
    }

    private void searchClasses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchQuery = request.getParameter("searchQuery");
        List<Classes> searchResults = classesService.findByUserName(searchQuery);

        request.setAttribute("classes", searchResults);
        request.getRequestDispatcher("/views/classes/class-list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}