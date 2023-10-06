<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Update Student</title>
  <link type="text/css" rel="stylesheet" href="css/style.css">
  <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
  <div id="header">
    <center><h2>SMTEC</h2></center>
  </div>
</div>
<div id="container">
  <h3>Update Student</h3>
  <form action="StudentControllerServlet" method="get">
    <input type="hidden" name="command" value="UPDATE"/>
    <input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>
    <table>
      <tbody>
      <tr>
        <td><label>First Name: </label></td>
        <td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"></td>
      </tr>
      <tr>
        <td><label>Last Name: </label></td>
        <td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"></td>
      </tr>
      <tr>
        <td><label>Email: </label></td>
        <td><input type="text" name="email" value="${THE_STUDENT.email}"></td>
      </tr>
      <tr>
        <td><label> </label></td>
        <td><input type="submit" value="Save"></td>
      </tr>

      </tbody>
    </table>

  </form>
  <div style="clear: both;"></div>
  <p>
    <a href="StudentControllerServlet">Back to List</a>
  </p>
</div>
</body>
</html>