/**
 * 
 */


$(document).ready(function() {
        $('#submitP').click(function(event) {
                var username = $('#userName').val();
                var email = $('#email').val();
                var newPassword = $('#pass')
                $.get('ResetPassword', {
                        userName : username,
                        email : email,
                        pass : newPassword
                        
                }, function(responseText) {
                        $('#errorMsg').text(responseText);
                });
        });
});