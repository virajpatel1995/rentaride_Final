/**
 * 
 */


$(document).ready(function() {
        $('#updateProfile').click(function(event) {
            
                var fName = $("#fName").val();
                var lName = $("#lName").val();
                var email = $("#email").val();
                var address = $("#address").val();
                var city = $("#city").val();
                var state =$("#state").val();
                var zip = $("#zip").val();
                
               
                $.post('UpdateProfile', {
                	fName : fName,
                	lName : lName,
                	email : email,
                	address : address,
                	city : city,
                	state : state,
                	zip : zip
                }, function(responseText) {
                        $('#updatedMsg').text(responseText);
                });
        });
});




$('#editbtn').click(function() {
	var $this = $(this);
	var tds = $this.closest('tr').find('td').filter(function() {
		return $(this).find('.editbtn').length === 0;
	});
	if ($this.html() === 'Edit') {
		$this.html('Save');
		tds.prop('contenteditable', true);
	} else {
		$this.html('Edit');
		tds.prop('contenteditable', false);
	}
});

