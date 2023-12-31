<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add New Student</title>
  <link type="text/css" rel="stylesheet" href="css/style.css">
  <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
  <script type="text/javascript" src="js/student-validation.js"></script>

</head>
<body>
  <div id="wrapper">
    <div id="header">
      <center><h2>SMTEC</h2></center>
    </div>
  </div>
  <div id="container">
    <h3>Add Student</h3>
    <form action="StudentControllerServlet" method="post" onSubmit="return validateForm()">
      <input type="hidden" name="command" value="ADD"/>
      <table>
        <tbody>
        <tr>
          <td><label>First Name: </label></td>
          <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
          <td><label>Last Name: </label></td>
          <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
          <td><label>Email: </label></td>
          <td><input type="text" name="email"></td>
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