<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title>RentNow </title>
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="source/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="source/font-awesome-4.5.0/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="style/slider.css">
	<link rel="stylesheet" type="text/css" href="style/mystyle.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="resetPassword.js"></script>
</head>
<body>
<#include "customerHeader.ftl">

 

<div class="container">
  <div class="row header">
  
    <h3  style="padding:25px;">add vehicle type!</h3>
    <hr/>
  </div>
  <div class="row body">
    <form action="CreateVehicleType" method="post">
      
            <input type="text" name="vehicleType" placeholder="Vehicle Type" required="required"/>      
          
          <input class="btn btn-submit" type="submit" value="Add" />

    </form>  
  </div>
</div>








</body>
</html>


