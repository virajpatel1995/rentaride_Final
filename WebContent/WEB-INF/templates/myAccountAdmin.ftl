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
        function myFunction() {
            alert("Added Location Successfully")
        }
    </script>


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
                    <input id="membershipPrice" onClick="this.select();" type="number" value=${mprice} step="0.01"onkeypress="return event.charCode >= 48" min="1" /> <br>
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
			First Name<input type="text" value="${firstName}" id="fName" name="fName" placeholder="fristName"/>
			Last Name<input type="text" value="${lastName}" id="lName" name="lName" placeholder="lastName"/>
			Email<input type="text" value="${email}" id="email" name="email" placeholder="email"/>
			Address<input type="text" value="${address}" id="address" name="address" placeholder="address"/>
			
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
                    Location Name:<input name="locationName" type="text" placeholder="Name" required="required">
                    Address:<input name="locationAddress" type="text" placeholder="Address" required="required"> <br>
                    Zip:<input name="locationZip" type="text" placeholder="ZipCode" required="required">
                    State:<input name="locationState" type="text" placeholder="State" required="required"><br>
                    Capacity:<input name="locationCapacity" type="text" placeholder="Capacity" required="required">

                    <button type="submit" name="action" class="buttonx" onclick="myFunction()">Add Rental
                        Location
                    </button>


                    <div style="float: left" id="rentalLocationMsg"></div>
                </form>

            <table class="table table-inverse">
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
                    Vehicle Type:
                    <select name="type">
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

                <table class="table table-inverse">
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

       

        <!-- ******ADD | UPDATE VEHICLE TYPE**************** -->
        <div id="menu5" class="tab-pane fade">
            <h3>Create | Update Vehicle</h3>

            <div class="container">

                <form action="CreateVehicleType" method="post">

                    New Vehicle Type:<input type="text" name="vehicleType" placeholder="Vehicle Type"/>
                    <input class="btn btn-submit" type="submit" value="Add Type"/>
                </form>


                <form action="UpdateVehicleType" method="post">

                    Old Vehicle Type:
                <#if vehicleTypeList ??>
                    <select name="oldVehicleType">
                        <#list vehicleTypeList as element>
                            <option value="${element}">${element}</option>
                        </#list>
                    </select>
                <#--<#else >-->
                <#--<input type="text" name="oldVehicleType" placeholder="Old Vehicle Type"/>-->
                </#if>
                    New Vehicle Type:<input type="text" name="newVehicleType" placeholder="New Vehicle Type"/>
                    <input class="btn btn-submit" type="submit" value="Update Type"/>
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
                    <select name="vehicleType">
                    <#list vehicleTypeList as element>
                        <option value="${element}">${element}</option>
                    </#list>
                    </select>
            Hourly Price: <input type="text" name="hourlyPrice" placeholder="Hourly Price"/>
            Max Hours Available: <input type="text" name="maxHours" placeholder="Max Hours"/>
         <input class="btn btn-submit" type="submit" value="Set|Update" />
         </form> 
         
            </div>


        </div>
        
        
        
        
        <!-- ******CUSTOMERS**************** -->
        <div id="menu7" class="tab-pane fade">
            <h3>Customers</h3>

            <div class="container">

                <table class="table table-inverse">
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
         
        
        <!-- ******RESERVATIONS**************** -->
        <div id="menu9" class="tab-pane fade">
            <h3>Current Reservations</h3>

            <div class="container">

                <table class="table table-inverse">
                    <tr>
                        <td><b>Pickup</b></td>
                        <td><b>Length</b></td>
                        <td><b>Canceled</b></td>
                        <td><b>Username</b></td>
                        <td><b>Location</b></td>
                        <td><b>Vehicle Type</b></td>
                        
                    </tr>
                <#if vehicleList??>
                    <#list vehicleList as v>
                        <tr>
                            <td>${v.getModel()}</td>
                            <td>${v.getYear()}</td>
                            <td>${v.getMileage()}</td>
                            <td>${v.getRegistrationTag()}</td>
                            <td>${v.getRentalLocation().getName()}</td>
                            <td>${v.getVehicleType().getName()}</td>
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
                        <td><b>Username</b></td>
                        <td><b>Comment Date</b></td>
                        <td><b>Comment</b></td>
                        
                        
                    </tr>
                <#if vehicleList??>
                    <#list vehicleList as v>
                        <tr>
                            <td>${v.getModel()}</td>
                            <td>${v.getYear()}</td>
                            <td>${v.getMileage()}</td>
                            
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

