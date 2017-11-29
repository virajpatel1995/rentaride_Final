/**
 *
 */


$(document).ready(function () {
    $('#updateProfile').click(function (event) {

        var fName = $("#fName").val();
        var lName = $("#lName").val();
        var email = $("#email").val();
        var address = $("#address").val();
        var city = $("#city").val();
        var state = $("#state").val();
        var zip = $("#zip").val();


        $.post('UpdateProfile', {
            fName: fName,
            lName: lName,
            email: email,
            address: address,
            city: city,
            state: state,
            zip: zip
        }, function (responseText) {
            $('#updatedMsg').text(responseText);
        });
    });

    $(".editbtn").click(function () {

        var currentTD = $(this).parents('tr').find('td');
        if ($(this).html() == 'Edit') {
            $.each(currentTD, function () {
                if(this.cellIndex  != 0) { // make sure id field is not editable
                    $(this).prop('contenteditable', true)
                }
            });
        } else {
            var rowInfo = new Array();
            $.each(currentTD, function () {
                $(this).prop('contenteditable', false)
                rowInfo.push($(this).text());
            });

            var id = rowInfo[0];
            var rlname = rowInfo[1];
            var address = rowInfo[2];
            var capacity = rowInfo[3];

                $.post('UpdateRentalLocation', {
                    rlid: id,
                    name : rlname,
                    address : address,
                    capacity: capacity

                }, function(responseText) {
                    $('#rentalLocationMsg').text(responseText);
                });
        }

        $(this).html($(this).html() == 'Edit' ? 'Save' : 'Edit');
    });
});




