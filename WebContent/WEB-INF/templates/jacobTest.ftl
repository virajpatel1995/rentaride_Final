<html>
<body>
 
<form action="UpdateAdmin" method="post">
	<#if firstName?? && (lastName??) && (email??) && (address??)>
			First Name<input type="text" value=${firstName} name="fName" placeholder="fristName"/>
			Last Name<input type="text" value=${lastName} name="lName" placeholder="lastName"/>
			Email<input type="text" value=${email} name="email" placeholder="email"/>
			Address<input type="text" value=${address} name="address" placeholder="address"/>
			City<input type="text" name="city" placeholder="city"/>
			State<input type="text" name="state" placeholder="state"/>
			Zip<input type="text" name="zip" placeholder="zip"/>
         <input class="btn btn-submit" type="submit" value="Update Profile" />
         </#if>
         </form>  
</body>
</html>