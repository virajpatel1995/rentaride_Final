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
     <br>
     <br>
  <div class="text-center">
	 <input type="text"  value="" placeholder="Search a car type, location, year, etc..." id="search-field" style="width: 500px;" >
	  <button class="searchbtn">Search</button>
      <br>
      <br>
      <div class="row">
      <div class="col-xs-5 col-xs-offset-1">
        <div class="panel panel-default">
        <div class="panel-heading text-center">Sedan</div>
        <div class="panel-body">
            <i>2017 Acura ILX</i>
            <br>
            <img src="http://st.motortrend.com/uploads/sites/10/2016/05/2017-acura-ilx-technology-plus-a-spec-sedan-angular-front.png?interpolation=lanczos-none&fit=around%7C660%3A439" width="175" height="125">
            <br>
            <span>Location: Ramsey</span>
            <br>
            <span>Mileage: 20,000</span>
        </div>
        </div>
      </div>
          <div class="col-xs-5 col-xs-offset-1">
              <div class="panel panel-default">
                <div class="panel-heading text-center">Credit Card Details</div>
                  <div class="panel-body">
                      <div align="center">
    <form class="form-horizontal" role="form">
      <fieldset>
        <div class="form-group">
          <label class="col-sm-3 control-label" for="card-holder-name">Name</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="card-holder-name" id="card-holder-name" placeholder="Card Holder's Name">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-3 control-label" for="card-number">Number</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" name="card-number" id="card-number" placeholder="Debit/Credit Card Number">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-3 control-label" for="expiry-month">Expiration</label>
          <div class="col-sm-9">
            <div class="row">
              <div class="col-xs-6">
                <select class="form-control col-sm-2" name="expiry-month" id="expiry-month">
                  <option>Month</option>
                  <option value="01">Jan (01)</option>
                  <option value="02">Feb (02)</option>
                  <option value="03">Mar (03)</option>
                  <option value="04">Apr (04)</option>
                  <option value="05">May (05)</option>
                  <option value="06">June (06)</option>
                  <option value="07">July (07)</option>
                  <option value="08">Aug (08)</option>
                  <option value="09">Sep (09)</option>
                  <option value="10">Oct (10)</option>
                  <option value="11">Nov (11)</option>
                  <option value="12">Dec (12)</option>
                </select>
              </div>
              <div class="col-xs-6">
                <select class="form-control col-sm-2" name="expiry-year">
                  <option value="17">2017</option>
                  <option value="18">2018</option>
                  <option value="19">2019</option>
                  <option value="20">2020</option>
                  <option value="21">2021</option>
                  <option value="22">2022</option>
                  <option value="23">2023</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-3 control-label" for="cvv">Card CVV</label>
          <div class="col-sm-6">
            <input type="text" class="form-control" name="cvv" id="cvv" placeholder="Security Code">
          </div>
        </div>
        <br>
        <div class="form-group">
          <div class="col-sm-12">
            <button type="button" class="btn btn-success" id="pay-now">Pay Now</button>
          </div>
        </div>
      </fieldset>
    </form>
                          </div>
                  </div>
              </div>
  </div>

 </div>
	</div>
    </div>
    
    <!-- _____________________________Bottom Menu ______________________________-->

    
     <div class="footerMenu">
        <ul class="nav nav-tabs bottomlinks">
			<li><a href="index.html">Home</a></li>
			<li><a href="pricing.html">Pricing</a></li>
			<li><a href="rentNow.html">RentNow</a></li>
			<li><a href="contact.html">Contact</a></li>
			<li><a href="about.html">About</a></li>
            <li><a href="register.html">Register</a></li>
            <li><a href="login.html">Login</a></li>
		</ul>
    </div>
    
     <!---------------------------------End bottom Menu---------------------------> 
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