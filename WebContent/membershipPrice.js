/**
 * 
 */


$(document).ready(function() {
        $('#submitPrice').click(function(event) {
        	
                var membershipPrice = $('#membershipPrice').val();
                var latefee = $('#lateFee').val();
                if(membershipPrice == -1){
                	alert("WRONG")
                }else
                	
                $.post('UpdateMembershipPrice', {
                	membershipPrice : membershipPrice,
                	lateFee : latefee
                	
                
                }, function(responseText) {
                        $('#membershipPriceError').text(responseText);
                 });
         });
});


