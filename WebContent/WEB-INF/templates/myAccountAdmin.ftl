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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<script src="membershipPrice.js"></script>

    <script>
        function myFunction(){
            alert("Added Location Successfully")
        }
    </script>

</head>
<body>
<!-- Header -->
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
     <br>
     <br>
  <div class="row">
    <div class="col-xs-5" style="background-color: whitesmoke;">
        <div class="row">
            <div class="col-xs-9">
                <h2 class="text-center">Add Cars</h2>
            </div>
            <div class="col-xs-3">
                <a href="addCar.html" class="buttonb" role="button">Add Car</a>
            </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span style="color:red">Code</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span style="color:red">Car Type</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span style="color:red">Location</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span style="color:red">Color</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span style="color:red">Mileage</span>
        </div>
        </div>
        <br>
    <div class="row">
        <div class="col-xs-1">
            <span style="color:green">1234</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Sedan</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Tate Deck</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Black</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>69,000</span>
        </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span style="color:green">2345</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Sedan</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Ramsey</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>White</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>20,000</span>
        </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span style="color:green">1111
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>SUV</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Tate Deck</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Blue</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>89,400</span>
        </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span style="color:green">7654</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Sedan</span>
        </div>
        <div class="col-xs-2 col-xs-offset-1">
            <span>Tate Deck</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Black</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>69,000</span>
        </div>
        </div>
      </div>
      <div class="col-xs-6 col-xs-offset-1" style="background-color: whitesmoke;">
        <div class="row">
            <div class="col-xs-12">
                <h2 class="text-center">Current Rentals</h2>
            </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span style="color:red">Name</span>
        </div>
        <div class="col-xs-3 col-xs-offset-1">
            <span style="color:red">Email</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span style="color:red">Renting</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span style="color:red">Code</span>
        </div>
        <div class="col-xs-1">
            <span style="color:red">Time</span>
        </div>
        <div class="col-xs-1">
            <span style="color:red">Location</span>
        </div>
        </div>
          <br>
          <div class="row">
        <div class="col-xs-1">
            <span>Viraj</span>
        </div>
        <div class="col-xs-3 col-xs-offset-1">
            <span>Vpatel0400@gmail.com</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Nissan Altima</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span style="color:green">1234</span>
        </div>
        <div class="col-xs-1">
            <span style="color:orange">3hrs</span>
        </div>
        <div class="col-xs-1">
            <span>Tate</span>
        </div>
        </div>
          <div class="row">
        <div class="col-xs-1">
            <span>Jacob</span>
        </div>
        <div class="col-xs-3 col-xs-offset-1">
            <span>Jacob123@idk.com</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>XC60</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span style="color:green">2328</span>
        </div>
        <div class="col-xs-1">
            <span style="color:orange">24hrs</span>
        </div>
        <div class="col-xs-1">
            <span>Ramsey</span>
        </div>
        </div>
      </div>
      
  </div>
    <div class="row">
        <div class="col-xs-6">
        </div>
        <div class="col-xs-4" style="background-color: whitesmoke;">
        <div class="row">
            <div class="col-xs-12">
                <h2 class="text-center">Customers</h2>
            </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span style="color:red">Name</span>
        </div>
        <div class="col-xs-4 col-xs-offset-3">
            <span style="color:red">Email</span>
        </div>
        </div>
            <br>
        <div class="row">
        <div class="col-xs-1">
            <span>Viraj</span>
        </div>
        <div class="col-xs-4 col-xs-offset-3">
            <span>vpatel0400@gmail.com</span>
        </div>
        </div>
        <div class="row">
        <div class="col-xs-1">
            <span>Jacob</span>
        </div>
        <div class="col-xs-4 col-xs-offset-3">
            <span>jacob123@idk.com</span>
        </div>
        </div>
        </div>
        <div class="col-xs-2">
        </div>
    </div>
     
     <br>
     <br>
         
  
</div>
</div>





<!--******************************UPDATE Membership Price*************************************-->
<!--*****************************************************************************-->


<div class="container">
  <div class="row header">
  
    <h3  style="padding:25px;">Set | Update Membership Price</h3>
    <hr/>
  </div>
    <form action="UpdateMembershipPrice" method="post">
     
           <div style="float:left">
               <#if mprice ?? && (latefee ??)>
            Membership Price:<input id="membershipPrice" type="number" value = ${mprice}  placeholder="price" step="0.01"/>
            Late Fee:<input id="lateFee" type="number" value = ${latefee}  placeholder="price" step="0.01"/>

                   <#else >

            Membership Price:<input type="number" id="membershipPrice" placeholder="price" step="0.01"/>
            Late Fee:<input type="number" id="lateFee" placeholder="price" step="0.01"/>
                   </#if>
            </div>
                
         <div style="float:left">
         <input id="submitPrice" class="btn btn-submit" type="button" value="Set/Update Fees" />
         </div>
         <div style="float: left" id="membershipPriceError"></div>
               </form>  
</div>




<!--*****************************************************************************-->
<!--*****************************************************************************-->


  <div class="panel-heading text-center"><h1>Add Rental Location</h1></div>
  <div class="panel-body">
      <div class="container"> 
          <div class="row">
              <div class="col-xs-5">
              <form class="form-horizontal" action="CreateRentalLocation" method="post" >

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carName">Rental Location Name: </label>  
  <div class="col-md-6">
  <input id="carName" name="locationName" type="text" placeholder="Location Name" class="form-control input-md" required="required">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carType">Address: </label>  
  <div class="col-md-4">
  <input id="carType" name="locationAddress" type="text" placeholder="Address" class="form-control input-md" required="required"> 
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="carType">Zip: </label>  
  <div class="col-md-4">
  <input id="carType" name="locationZip" type="text" placeholder="ZipCode" class="form-control input-md" required="required"> 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="carType">State: </label>  
  <div class="col-md-4">
  <input id="carType" name="locationState" type="text" placeholder="State" class="form-control input-md" required="required"> 
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="carColor">Capacity: </label>  
  <div class="col-md-4">
  <input id="carColor" name="locationCapacity" type="text" placeholder="Capacity" class="form-control input-md" required="required"> 
  </div>
</div>
                  <button type="submit" name="action" class="buttonx" onclick="myFunction()">Add/Update Rental Location</button>

</form>
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
	</div>
</div>

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/isotope.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> 
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
</body>
</html>