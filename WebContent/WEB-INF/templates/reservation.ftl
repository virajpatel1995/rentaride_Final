<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="source/bootstrap-3.3.6-dist/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="source/font-awesome-4.5.0/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="style/slider.css">
  <link rel="stylesheet" type="text/css" href="style/mystyle.css">
  	<script src="membershipPrice.js"></script>
  	<script src="alert.js"></script>

</head>

<body>

<#include "customerHeader.ftl">



    <div style="    position: relative; width: 100%; height: 200px; overflow: hidden; z-index: 1; background: white; margin: 20px 3px 0 0;" >


<form>
  <div style="
   margin: auto;
    width: 100%;
    content-align:center;
    padding: 10px;">
    <h1>Make a Reservation</h1>
    <br>
    Location: <select class="buttonx">
				  <option value="Tate">TATE</option>
				  <option value="Mlc">MLC</option>
				  <option value="Boyd">BOYD</option>
			 </select>
    
    
    Vehicle Type: <select class="buttonx">
				  <option value=Truck>Volvo</option>
				  <option value="SUV">Saab</option>
				  <option value="miniVan">Opel</option>
			 </select>
    
    
    Pickup Time: <input type="date" id="pickupTime" required="required">
    Length: <input type="number" id="input-subject" placeholder="# of Hrs" required="required">
     
  <input class="buttonx"type="submit" value="Submit" id="input-submit">
  
    </div>
  
</form>






</div>
    
</body>
</html>

