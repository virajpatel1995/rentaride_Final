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

  
  <script>
        function myFunction(){
            alert("Added Location Successfully")
        }
    </script>


</head>

<body>

<#include "customerHeader.ftl">

<div class="container">

    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">Profile</a></li>
        <li><a data-toggle="tab" href="#menu1">Membership Price | Late Fees</a></li>
        <li><a data-toggle="tab" href="#menu2">Rental Location</a></li>
        <li><a data-toggle="tab" href="#menu3">Add Vehicle</a></li>
        <li><a data-toggle="tab" href="#menu4">Update Vehicle</a></li>
          <li><a data-toggle="tab" href="#menu5">Add | Update Vehicle Type</a></li>

    </ul>

    <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h3>Profile</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua.</p>
        </div>

        <!-- **********MEMBERSHIP PRICE and LATE FEES -->

        <div id="menu1" class="tab-pane fade">
            <h3>Membership Price | Late Fees</h3>

            <div>

                <form action="UpdateMembershipPrice" method="post">
                <#if mprice ?? && (latefee ??)>
                    Membership Price:<input id="membershipPrice" onClick="this.select();" type="number"
                                            value=${mprice}step="0.01"/> <br>
                    Late Fee:<input id="lateFee" type="number" onClick="this.select();" value=${latefee}step="0.01"/>
                <#else >

                    Membership Price:<input type="number" id="membershipPrice" placeholder="price" step="0.01"/>
                    Late Fee:<input type="number" id="lateFee" step="0.01"/>
                </#if>
                    <input id="submitPrice" class="btn btn-submit" type="button" value="Set/Update Fees"/>

                </form>
                <p style="float: left" id="membershipPriceError"></p>
            </div>


        </div>

        <!-- ********ADD | Update Location************** -->
        <div id="menu2" class="tab-pane fade">
            <h3> Add|Update Rental Location</h3>
            <div class="container">

                <form action="CreateRentalLocation" method="post">
                    Location Name:<input name="locationName" type="text" placeholder="Name" required="required">
                    Address:<input name="locationAddress" type="text" placeholder="Address" required="required"> <br>
                    Zip:<input name="locationZip" type="text" placeholder="ZipCode" required="required">
                    State:<input name="locationState" type="text" placeholder="State" required="required"><br>
                    Capacity:<input name="locationCapacity" type="text" placeholder="Capacity" required="required">

                    <button type="submit" name="action" class="buttonx" onclick="myFunction()">Add/Update Rental
                        Location
                    </button>


                    <div style="float: left" id="rentalLocationMsg"></div>
                </form>
            </div>


        </div>

        <!-- ********ADD VEHICLE************** -->
        <div id="menu3" class="tab-pane fade">
            <h3>Add Vehicle</h3>
            <div class="container">

                <form action="CreateVehicle" method="post">
                    Make:<input type="text" name="make" placeholder="make"/>
                    Model:<input type="text" name="model" placeholder="model"/> <br>
                    Year:<input type="text" name="year" placeholder="year"/>
                    Mileage:<input type="text" name="mileage" placeholder="mileage"/><br>
                    Tag:<input type="text" name="tag" placeholder="tag"/>
                <#if rentalLocationList?? && (vehicleTypeList ??)>

                    Location:<select name="location">
                    <#list rentalLocationList as element>
                        <option value="${element}">${element}</option>
                    </#list>
                </select><br>
                    Vehicle Type:<select name="type">
                    <#list vehicleTypeList as element>
                        <option value="${element}">${element}</option>
                    </#list>
                </select>
                <#else>
                    Location:<input type="text" name="location" placeholder="location"/><br>
                    Type:<input type="text" name="type" placeholder="type"/>

                </#if>
                    <input class="btn btn-submit" type="submit" value="Add Vehicle"/>

                    <div style="float:center" id="createVehicleMsg"></div>

                </form>
            </div>


        </div>

        <!-- ******UPDATE VEHICLE**************** -->
        <div id="menu4" class="tab-pane fade">
            <h3>Update Vehicle</h3>

            <div class="container">

                <form action="UpdateVehicle" method="post">
                    Mileage:<input type="text" name="mileage" placeholder="mileage"/>
                    Tag:<input type="text" name="tag" placeholder="tag"/><br>
                    Location:<input type="text" name="location" placeholder="location"/>
                    Maintenance:<input type="text" name="type" placeholder="maintenance"/><br><br>
                    <input class="btn btn-submit" type="submit" value="Update Vehicle"/>
                    <div style="float:center" id="updateVehicleMsg"></div>
                </form>
            </div>


        </div>
       
 </div>
    <!-- ******ADD | UPDATE VEHICLE TYPE**************** -->
     <div id="menu5" class="tab-pane fade">
      <h3>Add | Update Vehicle</h3>
      
   <div class="container">
  		
    		<form action="CreateVehicleType" method="post">

            New Vehicle Type:<input type="text" name="vehicleType" placeholder="Vehicle Type"/>
         <input class="btn btn-submit" type="submit" value="Add Type" />
         </form> 
         
         
			<form action="UpdateVehicleType" method="post">

            Old Vehicle Type:<input type="text" name="oldVehicleType" placeholder="Old Vehicle Type"/>
            New Vehicle Type:<input type="text" name="newVehicleType" placeholder="New Vehicle Type"/>
         <input class="btn btn-submit" type="submit" value="Update Type" />
         </form> 
</div>
        
        
 </div>
 
      </div>
  
  </div>
 
</div>

</body>
</html>

