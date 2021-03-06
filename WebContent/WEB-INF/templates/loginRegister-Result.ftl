<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css'>
    <script src='https://code.jquery.com/jquery-2.1.1.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js'></script>


    <style type="text/css">


        body
        {
            background: #f5f5f5;
        }

        h5
        {
            font-weight: 400;
        }

        .container
        {
            margin-top: 80px;
            width: 800px;
        }

        .tabs .indicator
        {
            background-color: #e0f2f1;
            height: 60px;
            opacity: 0.3;
        }

        .form-container
        {
            padding: 40px;
            padding-top: 10px;
        }

        .confirmation-tabs-btn
        {
            position: absolute;
        }

    </style>






</head>
<body>




<h3><tt>${registerMessage}</tt></h3>

<div class="container white z-depth-2">
    <ul class="tabs teal">
        <li class="tab col s3"><a class="white-text active" href="#login">login</a></li>
        <li class="tab col s3"><a class="white-text" href="#register">register</a></li>
    </ul>
    <div id="login" class="col s12">
        <form class="col s12">
            <div class="form-container">
                <h3 class="teal-text">Login</h3>
                <div class="row">
                    <div class="input-field col s12">
                        <input name="username" id="email" type="text" class="validate">
                        <label>username</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input name="passwordlogin" id="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <br>
                <div style="text-align:center;">
                    <button class="btn waves-effect waves-light teal" type="submit" name="action">Login</button>
                    <br>
                    <br>
                    <a href="">Forgotten password?</a>
                </div>
            </div>
        </form>
    </div>

    <!-- ******************Register******************************************* -->
    <div id="register" class="col s12">
        <form class="col s12">
            <div class="form-container">
                <h3 class="teal-text">Welcome</h3>
                <div class="row">
                    <div class="input-field col s6">
                        <input name="firstName" id="last_name" type="text" class="validate">
                        <label for="firstName">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input name="lastName" id="last_name" type="text" class="validate">
                        <label for="lastName">Last Name</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <input name="userName" id="last_name" type="text" class="validate">
                        <label for="username">Username</label>
                    </div>

                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input name="email" id="email-confirm" type="email" class="validate">
                        <label for="email-confirm">Email Confirmation</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <input name="address" type="text">
                        <label>Address</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s4">
                        <input name="city" type="text">
                        <label>City</label>
                    </div>
                    <div class="input-field col s4">
                        <input name="zipCode" type="text">
                        <label>Zip Code</label>
                    </div>
                    <div class="input-field col s4">
                        <input name="state" type="text">
                        <label>State</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s4">
                        <input name="creditCard" type="text">
                        <label>Credit Card Number</label>
                    </div>
                    <div class="input-field col s4">
                        <input name="expDate" type="date">
                    </div>
                    <div class="input-field col s4">
                        <input name="licenseNumber" type="text">
                        <label>Driver License Number</label>
                    </div>
                </div>






                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input name="password" id="password-confirm" type="password" class="validate">
                        <label for="password-confirm">Password Confirmation</label>
                    </div>
                </div>
                <div style="text-align:center;">
                    <button class="btn waves-effect waves-light teal" type="submit" name="action">Register</button>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
</html>