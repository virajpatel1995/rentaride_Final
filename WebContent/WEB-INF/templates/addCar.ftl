Q!
Z<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add Car</title>
	
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
        
<div class="panel panel-default">
  <div class="panel-heading text-center"><h1>Add Car</h1></div>
  <div class="panel-body">
      <div class="container"> 
          <div class="row">
              <div class="col-xs-1"></div>
              <div class="col-xs-5">
              <form class="form-horizontal">
<fieldset>

<!-- Form Name -->

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carName">Car Name: </label>  
  <div class="col-md-6">
  <input id="carName" name="carName" type="text" placeholder="Car Name" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carType">Car Type: </label>  
  <div class="col-md-6">
  <input id="carType" name="carType" type="text" placeholder="Type" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carColor">Car Color: </label>  
  <div class="col-md-6">
  <input id="carColor" name="carColor" type="text" placeholder="Color" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carMileage">Car Mileage: </label>  
  <div class="col-md-6">
  <input id="carMileage" name="carMileage" type="text" placeholder="Mileage" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carLocation">Car Location: </label>  
  <div class="col-md-6">
  <input id="carLocation" name="carLocation" type="text" placeholder="Location" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="carCode">Car Code: </label>  
  <div class="col-md-6">
  <input id="carCode" name="carCode" type="text" placeholder="Code" class="form-control input-md" required="">
    
  </div>
</div>

</fieldset>
</form>
              </div>
              <div class="col-xs-5 ">
                  <div class="row">
            <h4>Add Pictures</h4>
            <div class="input-group">
                <span class="input-group-btn">
                    <span class="btn-file">
                        Browse&hellip; <input type="file" single>
                    </span>
                </span>
            </div>
                  </div>
                  <br>
                  <br>
                  <br>
                  <div class="row">
                  <button type="submit" class="buttonx">Add Car</button>
              </div>
              </div>
              <div class="col-xs-1"></div>
          </div>
      </div>
    </div>
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

<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.js"></script>
<script type="text/javascript" src="source/js/isotope.js"></script>
<script type="text/javascript" src="source/js/myscript.js"></script> 
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/jquery.1.11.js"></script>
<script type="text/javascript" src="source/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
</body>
</html>