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
    height: 650px;
    overflow: hidden;
    z-index: 1;
    background: white;
    margin: 20px 3px 0 0;" >
        
 <div class="container">
     <br>
  <h1 class="text-center">My Account</h1>
     <br>
    <div class="col-xs-3">
    <div style="background-color: whitesmoke;">
      <h3 class="text-center">Change Password</h3>
        <form><div class="form-group">
            <input type="password" class="form-control" id="currentPassword" placeholder="Current Password">
            <input type="password" class="form-control" id="newPassword" placeholder="New Password">
            <div class="text-center"> 
            <button type="submit" class="buttonb">Submit</button>
            </div>
            </div>
        </form>
        </div>
    <div style="background-color: whitesmoke;">
      <h3 class="text-center">Change Email</h3>
        <form><div class="form-group">
            <input type="email" class="form-control" id="currentEmail" placeholder="Current Email">
            <input type="email" class="form-control" id="newEmail" placeholder="New Email">
            </div>
            <div class="text-center"> 
            <button type="submit" class="buttonb">Submit</button>
            </div>
        </form>
        </div>
      </div>
      
      <div class="col-xs-8 col-xs-offset-1" style="background-color: whitesmoke;">
        <div class="col-xs-12">
                <h2 class="text-center">My Rentals</h2>
            </div>
        <div class="row">
        <div class="col-xs-1">
            <span>Code</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Car</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Type</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Date</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span class="text-center">Location</span>
        </div>
        <div class="col-xs-1">
            <span>Rent Time</span>
        </div>
        <div class="col-xs-1">
            <span>Price</span>
        </div>
      </div>
          <div class="row">
        <div class="col-xs-1">
            <span>1234</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Nissan</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>Altima</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span>09/05/17</span>
        </div>
        <div class="col-xs-1 col-xs-offset-1">
            <span class="text-center">Tate Deck</span>
        </div>
        <div class="col-xs-1">
            <span>24hr</span>
        </div>
        <div class="col-xs-1">
            <span>$29.99</span>
        </div>
        <div class="col-xs-1">
            <label style="color:red">Cancel</label>
        </div>
      </div>

  </div>
  
  
  
  
  <div>

                <form action="UpdateProfile" method="post">
	<#if firstName?? && (lastName??) && (email??) && (address??)>
			First Name<input type="text" value="${firstName}" name="fName" placeholder="fristName"/>
			Last Name<input type="text" value="${lastName}" name="lName" placeholder="lastName"/>
			Email<input type="text" value="${email}" name="email" placeholder="email"/>
			Address<input type="text" value="${address}" name="address" placeholder="address"/>
			
         <input class="btn btn-submit" type="submit" value="Update Profile" />
         </#if>
         </form>  
                <p style="float: left" id="UpdateProfileError"></p>
            </div>
     <br>
     <br>
  

     <br>


         

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