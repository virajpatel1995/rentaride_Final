<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="imageUploadServlet" method="post">



</form>



<div id="menu3" class="tab-pane fade">
      <h3>Add Vehicle</h3>
        <div class="container">
      
<form action="imageUploadServlet" method="post" enctype="multipart/form-data">


			Image:<input type="file" name="file"/>
            
         <input type="submit" value="Add Vehicle Image" />
         
                 	<div style="float:center" id="createVehicleMsg"></div>
         
         </form>   
      </div>
      
      
      
      
    </div>




<center>
        <h1>File Upload to Database Demo</h1>
        <form method="post" action="uploadServlet" enctype="multipart/form-data">
            <table border="0">
              
                <tr>
                    <td>Portrait Photo: </td>
                    <td><input type="file" name="photo" size="50"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
    </center>




</body>
</html>