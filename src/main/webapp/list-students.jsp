<%@ page import="java.util.*, com.myapp.web.jdbc.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>List of Student</title>
  <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!--<%-->
<!--List<Student> theStudents=(List<Student>) request.getAttribute("Student_List");-->
<!--%>-->
<body>
<center>
<div id="wrapper">
  <div id="header">
    <center><h2>SMTEC</h2></center>
  </div>
</div>
<div id="container">
  <div id="content">
    <input type="button" value="Add Student" onclick="window.location.href='add-student.jsp'; return false"
           class="add-student-button"/>
<center><table border="1">
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    <th>Action</th>
  </tr>


<c:forEach var="tempStudent" items="${Student_List}">
  <c:url var="tempLink" value="StudentControllerServlet">
    <c:param name="command" value="LOAD"/>
    <c:param name="studentId" value="${tempStudent.id}"/>
  </c:url>
  <c:url var="deleteLink" value="StudentControllerServlet">
    <c:param name="command" value="DELETE"/>
    <c:param name="studentId" value="${tempStudent.id}"/>
  </c:url>
  <tr>
    <td>${tempStudent.firstName}</td>
    <td>${tempStudent.lastName}</td>
    <td>${tempStudent.email}</td>
    <td> <a href="${tempLink}">Update </a> |
      <a href="${deleteLink}" onclick=
              "if (!(confirm('Are you sure you want to delete this Student'))) return false"> Delete</a> </td>
  </tr>
</c:forEach>

<!--<%-->
<!--  for(Student tempStudent: theStudents){ %>-->
<!--  <tr>-->
<!--    <td><%= tempStudent.getId() %></td>-->
<!--    <td><%= tempStudent.getFirstName() %></td>-->
<!--    <td><%= tempStudent.getLastName() %></td>-->
<!--    <td><%= tempStudent.getEmail() %></td>-->
<!--  </tr>-->
<!--<%  }-->
<!--%>-->

</table></center>
  </div>
</div></center>
</body>
</html>