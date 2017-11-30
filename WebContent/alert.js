/**
 *
 */


//    $('#updateProfile').click(function (event) {
//
//        var fName = $("#fName").val();
//        var lName = $("#lName").val();
//        var email = $("#email").val();
//        var address = $("#address").val();
//        var city = $("#city").val();
//        var state = $("#state").val();
//        var zip = $("#zip").val();
//
//
//        $.post('UpdateProfile', {
//            fName: fName,
//            lName: lName,
//            email: email,
//            address: address,
//            city: city,
//            state: state,
//            zip: zip
//        }, function (responseText) {
//            $('#updatedMsg').text(responseText);
//        });
//    });

$(document).ready(function () {
    $(".editbtn").click(function () {
        var currentTD = $(this).parents('tr').find('td');
        if ($(this).html() == 'Edit') {
            $.each(currentTD, function () {
                if (this.cellIndex != 0) { // make sure id field is not editable
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
                name: rlname,
                address: address,
                capacity: capacity

            }, function (responseText) {
                $('#rentalLocationMsg').text(responseText);
            });
        }

        $(this).html($(this).html() == 'Edit' ? 'Save' : 'Edit');
    });

    /**** UPDATE VEHICLE INFO **/
$(".editVehicle").click(function () {
        var currentTD = $(this).parents('tr').find('td');
        if ($(this).html() == 'Edit') {
            $.each(currentTD, function () {
                if (this.cellIndex != 0) { // make sure id field is not editable
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
            var Make = rowInfo[1];
            var Model = rowInfo[2];
            var Year = rowInfo[3];
            var Mileage = rowInfo[4];
            var RegistrationTag = rowInfo[5];
            var LastServiced = rowInfo[6];
            var Status = rowInfo[7];
            var Condition = rowInfo[8];
            var RentalLocation = rowInfo[9];
            var VehicleType = rowInfo[10];

            console.log(rowInfo);

            $.post('UpdateVehicle', {
                pid :id,
                pMake :Make,
                pModel : Model ,
                pYear: Year ,
                pRegistrationTag :RegistrationTag ,
                pMileage :Mileage ,
                pLastServiced :LastServiced ,
                pStatus :Status ,
                pCondition :Condition ,
                pRentalLocation :RentalLocation ,
                pVehicleType:VehicleType

            }, function (responseText) {
                $('#rentalLocationMsg').text(responseText);
            });
        }

        $(this).html($(this).html() == 'Edit' ? 'Save' : 'Edit');
    });

    $('#updateProfilebtn').click(function (event) {

        var firstName = $('#fName').val();
        var lastName = $('#lName').val();
        var email = $('#email').val();
        var address = $('#address').val();
        var card = $('#card').val();
        var expire = $('#expire').val();


        $.post('UpdateProfile', {
            fName: firstName,
            lName: lastName,
            email: email,
            address: address,
            card: card,
            expire: expire


        }, function (responseText) {
            $('#UpdateProfileError').text(responseText);
        });
    });

    $('#reservationBtn').click(function (event) {

        var rl = $('#rlid').val();
        var vtype = $('#vtid').val();
        var putime = $('#pickUpid').val();
        var length = $('#lengthId').val();


        $.post('PlaceReservation', {
            rentalLocation: rl,
            vehicleType: vtype,
            pickupTime: putime,
            length: length

        }, function (responseText) {
            $('#UpdateProfileError').text(responseText);
        });
    });

    //**************Admin profile update
    $('#updateAdminProfile').click(function (event) {

        var firstName = $('#fName').val();
        var lastName = $('#lName').val();
        var email = $('#email').val();
        var address = $('#address').val();


        $.post('UpdateAdmin', {
            fName: firstName,
            lName: lastName,
            email: email,
            address: address


        }, function (responseText) {
            $('#UpdateProfileErrorAdmin').text(responseText);
        });
    });
});
    



