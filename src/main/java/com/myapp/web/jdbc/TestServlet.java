package com.myapp.web.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

// Initialize a connection pool


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out= resp.getWriter();
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/web_student_tracker");
        dataSource.setUsername("root");
        dataSource.setPassword("Password@123");

        Statement myStmt=null;
        ResultSet myRs=null;
//        out.println(out);

        try {
//            out.println("<br>1");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= dataSource.getConnection();
//            out.println("<br>1");
            String sql="select * from student";
            myStmt=connection.createStatement();
//            out.println(myStmt);

            myRs=myStmt.executeQuery(sql);

            while (myRs.next()){
                String email= myRs.getString("email");
                out.println("<br>"+email);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
