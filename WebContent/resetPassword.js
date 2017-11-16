/**
 * 
 */


$(document).ready(function() {
        $('#submitP').click(function(event) {
                var username = $('#username').val();
                var email = $('#email').val();
                var pass = $('#pass').val();
                $.get('ResetPassword', {
                        username : username,
                        email : email,
                        pass : pass
                        
                }, function(responseText) {
                        $('#errorMsg').text(responseText);
                });
        });
});