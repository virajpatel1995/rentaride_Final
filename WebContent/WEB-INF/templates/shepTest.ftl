<html>
<body>
<form action="CreateComment" method="post">
			Rental ID for comment<input type="text" name="rental" placeholder="rental"/>
            New Comment<input type="text" name="comment" placeholder="comment"/>
         <input class="btn btn-submit" type="submit" value="Create Comment" />
         </form>
         
<form action="CreateVehicle" method="post">
			make<input type="text" name="make" placeholder="make"/>
            model<input type="text" name="model" placeholder="model"/>
            year<input type="text" name="year" placeholder="year"/>
            mileage<input type="text" name="mileage" placeholder="mileage"/>
            tag<input type="text" name="tag" placeholder="tag"/>
            location<input type="text" name="location" placeholder="location"/>
            type<input type="text" name="type" placeholder="type"/>
         <input class="btn btn-submit" type="submit" value="Create Vehicle" />
         </form>    
<form action="UpdateVehicle" method="post">

            mileage<input type="text" name="mileage" placeholder="mileage"/>
            tag<input type="text" name="tag" placeholder="tag"/>
            location<input type="text" name="location" placeholder="location"/>
            maintenance<input type="text" name="type" placeholder="maintenance"/>
         <input class="btn btn-submit" type="submit" value="Update Vehicle(use tag to identify)" />
         </form> 
         
<form action="CreateVehicleType" method="post">

            New Vehicle Type<input type="text" name="vehicleType" placeholder="Vehicle Type"/>
         <input class="btn btn-submit" type="submit" value="Add Type" />
         </form> 
<form action="UpdateVehicleType" method="post">

            Old Vehicle Type<input type="text" name="oldVehicleType" placeholder="Old Vehicle Type"/>
            New Vehicle Type<input type="text" name="newVehicleType" placeholder="New Vehicle Type"/>
         <input class="btn btn-submit" type="submit" value="Update Type" />
         </form> 
</body>
</html>