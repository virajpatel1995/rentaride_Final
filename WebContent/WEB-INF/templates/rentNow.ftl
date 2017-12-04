<!doctype html>
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
</head>
<body>
<#include "customerHeader.ftl">
    
    <div style="    position: relative;
    width: 100%;
    height: 500px;
    overflow: hidden;
    z-index: 1;
    background: white;
    margin: 20px 3px 0 0;" >
        

        
 <div class="container">
     <br>
    
  <h1>It's time to Rent a Car</h1>
  
  
  
  <!-- **********MAKE | VIEW RESERVATION*********************** --> 
    
    <div id="menu4" class="tab-pane fade">
      <h3>Make Reservations</h3>
      
  <div>
  		
    		

<form action="LoadMakeReservation" method="post">
  
    Location:

      <select class="buttonx" name="type" id="rlid">
          <#if rentalLocationList??>
  <#list rentalLocationList as element>
      <option value="${element}">${element}</option>
  </#list>

          </#if>
  </select>

    
    
    Vehicle Type:

      <select class="buttonx" name="type" id="vtid">
  <#if vehicleTypeList??>
      <#list vehicleTypeList as element>
          <option value="${element}">${element}</option>
      </#list>

  </#if>
  </select>
    
    
    Pickup Date | Time: <input style="width: 210px;" type="datetime-local" id="pickUpid" required="required"placeholder="11/30/2017 10:30 AM" >
    Length: <input type="number" id="lengthId" placeholder="# of Hrs" required="required">  <br>
    <button type="submit" name="action" class="btn btn-submit" id="reservationBtn"  >Make Reservation</button>
<hr>
  
</form>

      <table class="table table-inverse">
          <tr>
              <td><b>Id</b></td>
              <td><b>Pickup Time</b></td>
              <td><b>Duration(hr)</b></td>
              <td><b>Cancelled</b></td>
              <td><b>Location</b></td>
              <td><b>Vehicle Type</b></td>
          <td><b>Action</b></td>

          </tr>
      <#if reservationList??>
          <#list reservationList as r>
              <tr>
                  <td>${r.getId()}</td>
                  <td>${r.getPickupTime()}</td>
                  <td>${r.getLength()}</td>
                  <td>${r.getCanceledStr()}</td>
                  <td>${r.getRentalLocation().getName()}</td>
                  <td>${r.getVehicleType().getName()}</td>
              <td><button class="cancelReservation">Cancel</button></td>
              <td><button class="rentReservation">Rent</button></td>


              </tr>

          </#list>
      </#if>
      </table>







         
         
</div>
        
        
    </div>
    
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  </div>
</div>
     


			<div class="footer">
				<div class="copyright">
				  &copy; Copy right 2017 | <a href="#">Privacy </a>| <a href="#">Policy</a>
				</div>
				<div class="atisda">
					 Designed by <a href="http://www.webdomus.net/">Team 10 </a> 
				</div>
			</div>

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/isotope.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> 
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
</body>
</html>