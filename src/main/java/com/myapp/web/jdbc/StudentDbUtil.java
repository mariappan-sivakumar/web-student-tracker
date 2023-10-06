package com.myapp.web.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {
    private BasicDataSource dataSource;

    public StudentDbUtil(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = dataSource.getConnection();
            String sql = "select * from student order by first_name";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                int id = set.getInt("id");
                String firstName = set.getString("first_name");
                String lastName = set.getString("last_name");
                String email = set.getString("email");
                Student tempStudent = new Student(id, firstName, lastName, email);
                students.add(tempStudent);
            }
        } finally {
            close(connection, statement, set);
        }

        return students;
    }

    private void close(Connection connection, Statement statement, ResultSet set) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent(Student student) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into student (first_name, last_name, email) values(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.execute();
        } finally {
            close(connection, statement, null);
        }
    }

    public Student getStudent(String theStudentId) throws Exception {
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        int studentId;
        try {
            studentId = Integer.parseInt(theStudentId);
            connection = dataSource.getConnection();
            String sql = "select * from student where id=?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            set = statement.executeQuery();
            if (set.next()) {
                String firstName = set.getString("first_name");
                String lastName = set.getString("last_name");
                String email = set.getString("email");

                student = new Student(studentId, firstName, lastName, email);
            } else {
                throw new Exception("Could not find the student id: " + studentId);
            }
        } finally {
            close(connection, statement, set);
        }
        return student;
    }

    public void updateStudent(Student student) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {


            connection = dataSource.getConnection();
            String sql = "update student set first_name=?, last_name=?, email=? where id=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getId());

            statement.execute();
        } finally {
            close(connection, statement, null);
        }
    }

    public void deleteStudent(int id) throws Exception{
//        databaseInitializer();
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection=dataSource.getConnection();
            String sql="delete from student where id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.execute();
        }finally {
            close(connection,statement,null);
        }

    }
}
