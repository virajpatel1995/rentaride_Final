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
    <li><a data-toggle="tab" href="#menu1">My Rental</a></li>
    <li><a data-toggle="tab" href="#menu2">Update Profile</a></li>
    <li><a data-toggle="tab" href="#menu3">Feedback</a></li>
    
  </ul>

   <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h3>Welcome</h3>
         <!--***********CANCEL MEMBERSHIP*********-->   
         <form action="CancelMembership" method="post">
         <input class="btn btn-submit" type="submit" value="Cancel Membership" />
         </form> 
         
         
            <div class="tab-pane fade">
            <h3>Welcome to your Account </h3>
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
			Email<input type="text" id="email" value="${email}" name="email" placeholder="email"/>
         </#if>

			<#if address??>
			Address<input type="text" id="address" value="${address}" name="address" placeholder="address"/>
         </#if>
			<#if card??>
			Credit Card Number<input type="text" id="card" value="${card}" name="credit" placeholder="Credit Card #"/>
         </#if>
			<#if expire??>
			Expire Date <input type="text" id="expire" value="${expire}" name="expire"/>
         </#if>

         <input id="updateProfilebtn" class="btn btn-submit" type="button" value="Update Profile" />
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
           
         <input class="btn btn-submit" type="submit" value="Comment" />
         
                 	<div style="float:center" id="createVehicleMsg"></div>
         
         </form>   
      </div>
 
    </div>
    
         


  </div>
</div>

</body>
</html>

