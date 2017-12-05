
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RentNow </title>
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet"  href="source/bootstrap-3.3.6-dist/css/bootstrap.css">
	<link rel="stylesheet"  href="source/font-awesome-4.5.0/css/font-awesome.css">
	<link rel="stylesheet"  href="style/slider.css">
	<link rel="stylesheet"  href="style/mystyle.css">
</head>
<body> 




<#if username ??>
 <p class="welcome"> Welcome ${username}</p>
			<ul class="logreg">
				<li>
					<form action="Logout" method="get">
						<button class="buttonz" type="submit" name="Logout"> Logout</button>
					</form>
				</li>
				<li>
                    <form action="LoadAccount" method="post">
                        <button class="buttonz" type="submit" name="Account"> Account</button>
                    </form>
				</li>
			</ul>
<#else>
		<ul class="logreg">
				<li><a href="loginRegister.html">Login/Register</a> </li>
			</ul>	
			
		</#if>	
		
	</div>
	<!-- Navbar Up -->
	<nav class="topnavbar navbar-default topnav">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed toggle-costume" data-toggle="collapse" data-target="#upmenu" aria-expanded="false">
					<span class="sr-only"> Toggle navigaion</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand logo" href="#"><img src="image/logo.svg" alt="logo"></a>
			</div>	 
		</div>
		<div class="collapse navbar-collapse" id="upmenu">
			<ul class="nav navbar-nav" id="navbarontop">
			
				<#--<li class="active">-->
				<#--<form action="LoadHomePage" method="post">-->
				<#--<input type="submit" value="myAccountTest">-->
				<#--</form>-->
				<#--</li>-->
				
				<li>
				<form action="LoadHomePage" method="get">
					<button type="submit" style="border:none; background-color:inherit; padding-right:15px; cursor:pointer;"name="Home">Home</button>
				</form>
				</li>
                
				<li>
				<form action="LoadVehiclePage" method="get">
					<button type="submit" style="border:none; background-color:inherit; padding-right:15px; cursor:pointer;"name="LoadVehiclePage">Vehicles</button>
				</form>
				</li>
				<li>
				<form action="LoadLocationPage" method="get">
					<button type="submit" style="border:none; background-color:inherit; padding-right:15px; cursor:pointer;"name="LoadLocationPage">Locations</button>
				</form>
				</li>
				
				 <li>
				<form action="LoadAbout" method="get">
					<button type="submit" style="border:none; background-color:inherit; padding-right:15px; cursor:pointer;"name="About">About</button>
				</form>
				</li>
				
			</ul>
		</div>
	</nav>
</div>
    
  
    
 

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/isotope.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> 
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
</body>
</html>