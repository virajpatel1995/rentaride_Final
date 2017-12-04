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
    margin: 20px 3px 0 0;">


    <div class="container">
        <br>

        <h1>It's time to Rent a Car</h1>
    </div>


    <!-- **********MAKE | VIEW RESERVATION*********************** -->

<#--<div id="menu4" class="tab-pane fade">-->
<#--<h3>Rent A Car</h3>-->

    <div>


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
                <td><b>Action</b></td>
            </tr>
        <#if vehicleList??>
            <#list vehicleList as v>
                <tr>
                    <form action="PlaceRental" method="post">

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
                    <td>
                        <button class="editVehicle">Rent Now</button>
                    </td>
                        <td><input type="hidden" value="${rid}" name="res"/>
                        <td><input type="hidden" value="${v.getRegistrationTag()}" name="tag"/>
                    </form>
                </tr>

            </#list>
        </#if>
        </table>

        <#if msg??>
            <p>${msg}</p>
        </#if>

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