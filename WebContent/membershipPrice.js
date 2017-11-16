/**
 * 
 */


$(document).ready(function() {
        $('#submitPrice').click(function(event) {
                var membershipPrice = $('#membershipPrice').val();
                $.get('UpdateMembershipPrice', {
                	membershipPrice : membershipPrice
                       
                        
                }, function(responseText) {
                        $('#membershipPriceError').text(responseText);
                });
        });
});


