package com.myapp.web.jdbc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.util.List;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

    private StudentDbUtil studentDbUtil;
    private BasicDataSource dataSource=new BasicDataSource();

    @Override
    public void init() throws ServletException {
        super.init();
        try{
            dataSource.setUrl("jdbc:mysql://localhost:3306/web_student_tracker");
            dataSource.setUsername("root");
            dataSource.setPassword("Password@123");
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            studentDbUtil =new StudentDbUtil(dataSource);
        }catch (Exception e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String theCommand=req.getParameter("command");

            if (theCommand==null){
                theCommand="LIST";
            }

            switch (theCommand){
                case "LIST":
                    listStudents(req,resp);
                    break;


                case "LOAD":
                    loadStudents(req, resp);
                    break;

                case "UPDATE":
                    updateStudent(req,resp);
                    break;

                case "DELETE":
                    deleteStudent(req,resp);
                    break;

                default:
                    listStudents(req,resp);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // read the "command" parameter
            String theCommand = req.getParameter("command");

            // route to the appropriate method
            switch (theCommand) {

                case "ADD":
                    addStudent(req, resp);
                    break;

                default:
                    listStudents(req, resp);
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        int id=Integer.parseInt(req.getParameter("studentId"));
        studentDbUtil.deleteStudent(id);
        listStudents(req,resp);

    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("studentId"));
        String firstName=req.getParameter("firstName");
        String lastName=req.getParameter("lastName");
        String email=req.getParameter("email");

        Student student=new Student(id,firstName,lastName,email);
        studentDbUtil.updateStudent(student);

        listStudents(req,resp);
    }

    private void loadStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String theStudentId=req.getParameter("studentId");
        Student student= studentDbUtil.getStudent(theStudentId);
        req.setAttribute("THE_STUDENT",student);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/update-student.jsp");
        dispatcher.forward(req,resp);
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String firstName=req.getParameter("firstName");
        String lastName=req.getParameter("lastName");
        String email=req.getParameter("email");
        Student student=new Student(firstName,lastName,email);

        studentDbUtil.addStudent(student);
        listStudents(req, resp);

    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Student> students=studentDbUtil.getStudents();
        req.setAttribute("Student_List",students);
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("/list-students.jsp");
        requestDispatcher.forward(req,resp);
    }
}
