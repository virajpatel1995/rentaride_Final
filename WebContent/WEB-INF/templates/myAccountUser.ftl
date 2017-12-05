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

<div class="container">

    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">My Account</a></li>
        <li><a data-toggle="tab" href="#menu7">My Rental(s)</a></li>

        <li><a data-toggle="tab" href="#menu4">My Reservation(s)</a></li>

        <li><a data-toggle="tab" href="#menu2">Update Profile</a></li>
        <li><a data-toggle="tab" href="#menu3">Feedback</a></li>

    </ul>

    <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h3>Welcome</h3>
            <!--***********CANCEL MEMBERSHIP*********-->
            <form action="CancelMembership" method="post">
                <input class="btn btn-submit" type="submit" value="Cancel Membership" onclick="cancelMembership()"/>
            </form>


            <div class="tab-pane fade">
                <h3>Welcome to your Account </h3>
            </div>
        </div>

   </div>
    
    
    
    
   <!-- **********MY RENTAL*********************** --> 
    
    <div id="menu1" class="tab-pane fade">
      <h3>My Rental</h3>
      
  <div>
  		
    		<form action="UpdateMembershipPrice" method="post">
  
            Membership Price:<input id="membershipPrice"  onClick="this.select();" type="number" step="0.01" /> <br>       
            Late Fee:<input id="lateFee" type="number" onClick="this.select();" step="0.01" />
            <input id="submitPrice" class="btn btn-submit" type="button" value="Set/Update Fees" />
            
         </form>
         <p style="float: left" id="membershipPriceError"></p>
</div>
        
        
    </div>
    
    
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
    
    
    Pickup Date | Time: <input style="width: 210px;" type="datetime-local" id="pickUpid" required="required" placeholder="11/30/2017 10:30 AM" >
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
          <form action="LoadRentNow" method="post">
                  <td>${r.getId()}</td>
                  <td>${r.getPickupTime()}</td>
                  <td>${r.getLength()}</td>
                  <td>${r.getCanceledStr()}</td>
                  <td>${r.getRentalLocation().getName()}</td>
                  <td>${r.getVehicleType().getName()}</td>
              <td><button class="cancelReservation">Cancel</button></td>
              <td><button >Rent</button></td>
              <td><input type="hidden" value="${r.getRentalLocation().getName()}" name="rl"/>
              <td><input type="hidden" value="${r.getVehicleType().getName()}" name="vt"/>
              <td><input type="hidden" value="${r.getId()}" name="rid"/>

          </form>


              </tr>

          </#list>
      </#if>
      </table>







         
         
</div>
        
        
    </div>
    
     <!-- ********Update Profile************** --> 
   <div id="menu2" class="tab-pane fade">
            <h3>Update Profile</h3>

            <div>

                <form action="UpdateProfile" method="post">

                <#if firstName??>
                    First Name<input type="text" id="fName" value="${firstName}" name="fName" placeholder="fristName"/>
                </#if>
                <#if lastName??>
                    Last Name<input type="text" id="lName" value="${lastName}" name="lName" placeholder="lastName"/>
                </#if>
                <#if email??>
                    Email<input style="width:230px;" type="text" id="email" value="${email}" name="email"
                                placeholder="email"/>
                </#if>

                <#if address??>
                    Address<input style="width:280px;" type="text" id="address" value="${address}" name="address"
                                  placeholder="address"/><br>
                </#if>
                <#if card??>
                    Credit Card Number<input style="width:170px;" type="password" id="card" value="${card}"
                                             name="credit" placeholder="Credit Card #"/>
                </#if>
                <#if expire??>
                    Expire Date <input type="text" id="expire" value="${expire}" name="expire"/><br>
                </#if>

                    <input id="updateProfilebtn" class="btn btn-submit" type="button" value="Update Profile"/>

                <#--</#if>-->
                </form>

                <p style="float: left color:Red" id="UpdateProfileError"></p>
            </div>


        </div>


        <!-- ********FEEDBACK************** -->
        <div id="menu3" class="tab-pane fade">
            <h3>Feedback</h3>
            <div class="container">

                <form action="CreateComment" method="post">
                    Rental ID:<input type="text" name="rental" placeholder="rental ID"/> <br>
                    Comment:<input type="text" name="comment" placeholder="Comment"/>

                    <input class="btn btn-submit" type="submit" value="Comment"/>

                    <div style="float:center" id="createVehicleMsg"></div>

                </form>
            </div>

            <#--Needs to be in rental-->
            <table class="table table-inverse">
                <tr>
                    <td><b>ID</b></td>
                    <td><b>Location</b></td>
                    <td><b>Address</b></td>
                    <td><b>Tag #</b></td>
                    <td><b>Year</b></td>
                    <td><b>Make</b></td>
                    <td><b>Model</b></td>
                    <td><b>Pick Up Time</b></td>
                    <td><b>Drop Off time</b></td>


                </tr>

        <#if rentalList ??>
            <#list rentalList as r>

                <tr>
                    <form action="ReturnRental" method="post">
                        <td>${r.getId()}</td>
                        <td>${r.getReservation().getRentalLocation().getName()}</td>
                        <td>${r.getReservation().getRentalLocation().getAddress()}</td>
                        <td>${r.getVehicle().getRegistrationTag()}</td>
                        <td>${r.getVehicle().getYear()}</td>
                        <td>${r.getVehicle().getMake()}</td>
                        <td>${r.getVehicle().getModel()}</td>
                        <td>${r.getPickupTime()}</td>
                        <#if r.getReturnTime() ??>
                        <td>${r.getReturnTime()}</td>
                        <#else>
                        <td><p>null</p></td>
                        </#if>


                        <td>
                            <button>Return</button>
                        </td>

                        <td><input type="hidden" value="${r.getId()}" name="rentalID"/>

                    </form>


                </tr>

            </#list>
        </#if>
            </table>

            <#--Needs to be in rental-->
        </div>


    </div>
</div>

</body>
</html>

