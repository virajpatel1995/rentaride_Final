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
                
			<#if firstName?? && (lastName??) && (email??) && (credit??) && (expire??)>
			
			First Name<input type="text" value="${firstName}" name="fName" placeholder="fristName"/>
			Last Name<input type="text" value="${lastName}" name="lName" placeholder="lastName"/>
			Email<input type="text" value="${email}" name="email" placeholder="email"/>
			Address<input type="text" value="${address}" name="address" placeholder="address"/>
			Credit Card Number<input type="text" value="${credit}" name="credit" placeholder="Credit Card #"/>
			Expire Date <input type="date" value="${expire}" name="expire"/>

         <input class="btn btn-submit" type="submit" value="Update Profile" />
         </#if>
         </form>  
                <p style="float: left" id="UpdateProfileError"></p>
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

