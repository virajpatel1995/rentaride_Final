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
        function myFunctionx() {
            alert("Added Location Successfully")
        }
        
        
        
        function myFunction() {
       var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[3];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
     
        
           function myFunctionS() {
       var input, filter, table, tr, td, i;
  input = document.getElementById("myInput2");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable2");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}        
              
                    
        
            function myFunctionV() {
       var input, filter, table, tr, td, i;
  input = document.getElementById("myInput3");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable3");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}        
          
        
        
        
    </script>



<style>
* {
  box-sizing: border-box;
}

#myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

#myTable {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 18px;
}

#myTable th, #myTable td {
  text-align: left;
  padding: 12px;
}

#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}
</style>









</head>

<body>

<#include "customerHeader.ftl">

<div class="container">

    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">MyAccount</a></li>
        <li><a data-toggle="tab" href="#menu1">Membership Price | Late Fees</a></li>
        <li><a data-toggle="tab" href="#menu2">Rental Location</a></li>
        <li><a data-toggle="tab" href="#menu3">Create | Update Vehicle</a></li>
        <li><a data-toggle="tab" href="#menu5">Create | Update Vehicle Type</a></li>

        <li><a data-toggle="tab" href="#menu8">Update Profile</a></li>
       
		<li><a data-toggle="tab" href="#menu6">Hourly Rental Price</a></li>
		<li><a data-toggle="tab" href="#menu7">Customers</a></li>
		<li><a data-toggle="tab" href="#menu9">Reservations</a></li>
		<li><a data-toggle="tab" href="#menu10">View Comments</a></li>



    </ul>

    <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h3>Welcome</h3>
            
            <div class="tab-pane fade">
            <h3>Welcome to your Account </h3>

          


        </div>

            
        </div>

        <!-- **********MEMBERSHIP PRICE and LATE FEES -->

        <div id="menu1" class="tab-pane fade">
            <h3>Membership Price | Late Fees</h3>

            <div>

                <form action="UpdateMembershipPrice" method="post">
                <#if mprice?? && (latefee??)>
                    Membership Price:
                    <input id="membershipPrice" onClick="this.select();" type="number" value=${mprice} step="0.01"onkeypress="return event.charCode >= 48" min="1" />
                    Late Fee:
                    <input id="lateFee" type="number" onClick="this.select();" value=${latefee} step="0.01"onkeypress="return event.charCode >= 48" min="1"/>
                <#else >
                    Membership Price:<input type="number" id="membershipPrice" placeholder="price" step="0.01" onkeypress="return event.charCode >= 48" min="1"/>
                    Late Fee:<input type="number" id="lateFee" step="0.01" onkeypress="return event.charCode >= 48" min="1"/>
                </#if>
                    <input id="submitPrice" class="btn btn-submit" type="button" value="Set/Update Fees"/>

                </form>
                <p style="float: left" id="membershipPriceError"></p>
            </div>


        </div>
        
        <!-- **********Update Profile -->

        <div id="menu8" class="tab-pane fade">
            <h3>Update Profile</h3>

            <div>

                <form action="UpdateAdmin" method="post">
	<#if firstName?? && (lastName??) && (email??) && (address??)>
			First Name
			<input type="text" value="${firstName}" id="fName" name="fName" placeholder="fristName"/>
			Last Name
			<input type="text" value="${lastName}" id="lName" name="lName" placeholder="lastName"/><br>
			Email
			<input type="text" value="${email}" id="email" name="email" placeholder="email"/>
			Address
			<input style="width:500px;" type="text" value="${address}" id="address" name="address" placeholder="address"/>
			
         <input class="btn btn-submit" id="updateAdminProfile" type="button" value="Update Profile" />
         </#if>
         </form>  
                <p style="float: left" id="UpdateProfileErrorAdmin"></p>
            </div>


        </div>

        <!-- ********Create | Update Location************** -->
        <div id="menu2" class="tab-pane fade">
        
            <h3> Create Rental Location</h3>
            
            <div class="container">

                <form action="CreateRentalLocation" method="post">
                
                   
                    
                    <input name="locationName" type="text" placeholder="Location Name" required="required">
                    <input name="locationAddress" type="text" placeholder="Address" required="required">
                    <input style="width: 70px;" name="locationZip" type="text" placeholder="Zip" required="required">
                    <input style="width: 100px;" name="locationState" type="text" placeholder="State" required="required">
                    <input style="width: 70px;" name="locationCapacity" type="text" placeholder="Capacity" required="required">
                    <button type="submit" name="action" class="btn btn-submit" onclick="myFunctionx()">Add Rental Location </button>


                    <div style="float: left" id="rentalLocationMsg"></div>
                </form>
                
                <hr>
          Search:  <input type="text" id="myInput2" onkeyup="myFunctionS()" placeholder="Search for names.." title="Type in a Location name">

            <table id="myTable2" class="table table-inverse">
                <tr>
                    <td><b>Id</b></td>
                    <td><b>Name</b></td>
                    <td><b>Address</b></td>
                    <td><b>Capacity</b></td>
                    <td><b>Action</b></td>
                </tr>
            <#if rentalLocations??>
                <#list rentalLocations as rl>
                    <tr>
                        <td>${rl.getId()}</td>
                        <td>${rl.getName()}</td>
                        <td>${rl.getAddress()}</td>
                        <td>${rl.getCapacity()}</td>
                        <td><button class="editbtn">Edit</button></td>
                    </tr>

                </#list>
            </#if>
            </table>
            </div>


        </div>

        <!-- ********CREATE | UPDATE VEHICLE************** -->
        <div id="menu3" class="tab-pane fade">
            <h3>Add Vehicle</h3>
            <div class="container">

                <form action="CreateVehicle" method="post">
                    <input style="width:150px;" type="text" name="make" placeholder="Make"/>
                    <input style="width:150px;" type="text" name="model" placeholder="Model"/>
                    <input style="width:105px;" type="text" name="year" placeholder="Year"/>
                    <input style="width:105px;" type="text" name="mileage" placeholder="Mileage"/>
                    <input style="width:75px;" type="text" name="tag" placeholder="Tag #"/>
                <#if rentalLocationList?? && (vehicleTypeList ??)>

                    <select class="buttonx" name="location">
                    	<option>-Select Location-</option>
                    <#list rentalLocationList as element>
                   
                        <option value="${element}">${element}</option>
                    </#list>
                </select>
                    
                    <select class= "buttonx" name="type">
                     	<option>-Select VehicleType-</option>
                    <#list vehicleTypeList as element>
                        <option value="${element}">${element}</option>
                    </#list>
                </select>
                <#else>
                    <input type="text" name="location" placeholder="Location"/>
                    Type:<input type="text" name="type" placeholder="type"/>

                </#if>
                    <input class="btn btn-submit" type="submit" value="Add Vehicle"/>

                    <div style="float:center" id="createVehicleMsg"></div>

                </form>


 					<hr>
          Search:  <input type="text" id="myInput3" onkeyup="myFunctionV()" placeholder="Search for Vehicle.." title="Type in a Location name">
          
          
                <table id="myTable3" class="table table-inverse">
                    <tr>
                        <td><b>Id</b></td>
                        <td><b>Make</b></td>
                        <td><b>Model</b></td>
                        <td><b>Year</b></td>
                        <td><b>Mileage</b></td>
                        <td><b>Tag</b></td>
                        <td><b>Last Serviced</b></td>
                        <td><b>Status</b></td>
                        <td><b>Condition</b></td>
                        <td><b>Rental Location</b></td>
                        <td><b>Type</b></td>
                    </tr>
                <#if vehicleList??>
                    <#list vehicleList as v>
                        <tr>
                            <td>${v.getId()}</td>
                            <td>${v.getMake()}</td>
                            <td>${v.getModel()}</td>
                            <td>${v.getYear()}</td>
                            <td>${v.getMileage()}</td>
                            <td>${v.getRegistrationTag()}</td>
                            <td>${v.getLastServiced()}</td>
                            <td>${v.getStatus()}</td>
                            <td>${v.getCondition()}</td>
                            <td>${v.getRentalLocation().getName()}</td>
                            <td>${v.getVehicleType().getName()}</td>
                            <td><button class="editVehicle">Edit</button></td>
                        </tr>

                    </#list>
                </#if>
                </table>
            </div>


        </div>

       

        <!-- ******CREATE | UPDATE VEHICLE TYPE**************** -->
        <div id="menu5" class="tab-pane fade">
            <h3>Create Vehicle Type</h3>

            <div class="container">

                <form action="CreateVehicleType" method="post">

                    <input type="text" name="vehicleType" placeholder="New Vehicle Type"/>
                    <button type="submit" name="action" class="btn btn-submit" >Add Vehicle Type</button>

                </form>

			<hr>
			            <h3>Update Vehicle Type</h3>

                <form action="UpdateVehicleType" method="post">

                <#if vehicleTypeList ??>
                    <select class="buttonx" name="oldVehicleType">
                    <option>-Old Vehicle Type-</option>
                        <#list vehicleTypeList as element>
                            <option value="${element}">${element}</option>
                        </#list>
                    </select>
                <#--<#else >-->
                <#--<input type="text" name="oldVehicleType" placeholder="Old Vehicle Type"/>-->
                </#if>
                       <input type="text" name="newVehicleType" placeholder="New Vehicle Type"/>
                        <button type="submit" name="action" class="btn btn-submit">Update Vehicle Type</button>

                </form>
            </div>
        </div>
        
        
        
        <!-- ******HOURLY RENTAL PRICE**************** -->
        <div id="menu6" class="tab-pane fade">
            <h3>Set and Update Hourly Price</h3>

            <div class="container">

                <form action="UpdateHourlyPrice" method="post">

            Vehicle Type:
                    <#--<input type="text" name="vehicleType" placeholder="Vehicle Type"/>-->
                    <select class="buttonx" name="vehicleType">
                    <option>-Select Vehicle Type-</option>
                    <#list vehicleTypeList as element>
                        <option value="${element}">${element}</option>
                    </#list>
                    </select>
            <input type="text" name="hourlyPrice" placeholder="Hourly Price"/>
            <input type="text" name="maxHours" placeholder="Max Hours"/>
          <button type="submit" name="action" class="btn btn-submit" >Set | Update Price | Hours</button>

         </form> 
         
            </div>


        </div>
        
        
        
        
        <!-- ******CUSTOMERS**************** -->
        <div id="menu7" class="tab-pane fade">
            <h3>Customers</h3>

            <div class="container">
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

                <table id="myTable" class="table table-inverse">
                    <tr>
                        <td><b>Id</b></td>
                        <td><b>First Name</b></td>
                        <td><b>Last Name</b></td>
                        <td><b>Username</b></td>
                        <td><b>Email</b></td>
                        <td><b>Address</b></td>
                        <td><b>Join Date</b></td>
                        <td><b>Expire Date</b></td>
                        <td><b>Status</b></td>
                        <td><b>Action</b></td>
                    </tr>
                <#if customerList??>
                    <#list customerList as c>
                        <tr>
                            <td>${c.getId()}</td>
                            <td>${c.getFirstName()}</td>
                            <td>${c.getLastName()}</td>
                            <td>${c.getUserName()}</td>
                            <td>${c.getEmail()}</td>
                            <td>${c.getAddress()}</td>
                            <td>${c.getCreatedDate()}</td>
                            <td>${c.getMemberUntil()}</td>
                            <td>${c.getUserStatus()}</td>
                            <td><button class="terminateCustomer">Terminate</button></td>
                        </tr>

                    </#list>
                </#if>
                </table>

         
            </div>


        </div>
         
        
        <!-- ******RESERVATIONS**************** -->
        <div id="menu9" class="tab-pane fade">
            <h3>Current Reservations</h3>

            <div class="container">

                <table class="table table-inverse">
                    <tr>
                        <td><b>Id</b></td>
                        <td><b>Pickup Time</b></td>
                        <td><b>Duration(hr)</b></td>
                        <td><b>Cancelled</b></td>
                        <td><b>Customer</b></td>
                        <td><b>Location</b></td>
                        <td><b>Vehicle Type</b></td>
                        <#--<td><b>Action</b></td>-->

                    </tr>
                <#if reservationList??>
                    <#list reservationList as r>
                        <tr>
                            <td>${r.getId()}</td>
                            <td>${r.getPickupTime()}</td>
                            <td>${r.getLength()}</td>
                            <td>${r.getCanceledStr()}</td>
                            <td>${r.getCustomer().getFirstName() + " " + r.getCustomer().getFirstName()}</td>
                            <td>${r.getRentalLocation().getName()}</td>
                            <td>${r.getVehicleType().getName()}</td>
                            <#--<td><button class="cancalReservation">Cancel</button></td>-->


                        </tr>

                    </#list>
                </#if>
                </table>

            </div>
        </div>
        
        
        <!-- ******VIEW COMMENTS**************** -->
        <div id="menu10" class="tab-pane fade">
            <h3>Comments</h3>

            <div class="container">

                <table class="table table-inverse">
                    <tr>
                        <td><b>Id</b></td>
                        <td><b>Comment</b></td>
                        <td><b>Date</b></td>
                        <td><b>Customer</b></td>
                        <td><b>Rental ID</b></td>

                        
                    </tr>
                <#if commentList??>
                    <#list commentList as c>
                        <tr>
                            <td>${c.getId()}</td>
                            <td>${c.getText()}</td>
                            <td>${c.getDate()}</td>
                            <td>${c.getCustomer().getFirstName() + " " +c.getCustomer().getLastName()}</td>
                            <td>${c.getRental().getId()}</td>

                        </tr>

                    </#list>
                </#if>
                </table>

            </div>
        </div>
        
        
        
        
        
        
        
        
        
        
        
       
        
    </div>
</div>

</body>
</html>

