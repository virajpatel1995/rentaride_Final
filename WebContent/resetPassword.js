/**
 * 
 */


$(document).ready(function() {
        $('#submitP').click(function(event) {
                var username = $('#username').val();
               // var email = $('#email').val();
                var firstName = $("#firstName").val();
                var lastName = $("#lastName").val();
                var pass = $('#pass').val();
                $.get('ResetPassword', {
                        username : username,
                        //email : email,
                        firstName : firstName,
                        lastName : lastName,
                        pass : pass
                        
                }, function(responseText) {
                        $('#errorMsg').text(responseText);
                });
        });
});