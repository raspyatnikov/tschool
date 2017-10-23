$(document).on('click', '[name="addCargo"]', function () {
    $('#order-form .cargo-row:last').after($('#order-form .cargo-row:last').clone());
    $('#order-form .cargo-row:last input').val("");
    $('#routeForm .waypoint-row:last select').val("");
    // if ($('#order-form .cargo-row').length > 2) $('[name="delWaypoint"]').removeClass("disabled");
});

$(document).on('click', '[name="addWaypoint"]', function () {
    $('#order-form .cities:last').after($('#order-form .cities:last').clone());
    $('#order-form .cities:last input').val("");
    $('#routeForm .cities:last select').val("");
    // if ($('#order-form .cargo-row').length > 2) $('[name="delWaypoint"]').removeClass("disabled");
});

$(document).on('click', '[name="getTruckList"]', function () {
    var pst = {};
    pst.waypoints = [];
    pst.cargoes = [];
    $('.cities').each(function () {
        pst.waypoints.push({
            city_id: $(this).find('[name="waypoint_city"]').val()
        })
    });

    $('.cargoes').each(function () {
        pst.cargoes.push({
            cargo_weight: $(this).find('[name="weight"]').val(),
            cargo_origin_city:$(this).find('[name="order_origin"]').val(),
            cargo_destination_city: $(this).find('[name="order_destination"]').val()
        })
    });
    $.ajax({
        type: "GET",
        url: "getSuitableTrucks",
        data: {"json": JSON.stringify(pst)},
        dataType: 'json',
        success: function(data){
            if(data.code === "400"){
                $('select[name="truck"]')
                    .empty()
                    .append('<option selected="selected" value="">' + data.msg + '</option>')
            }
            else{
            $('select[name="truck"]')
                .empty()
                .append('<option selected="selected" value="">Select truck</option>')

                $.each(data.result, function(index, value) {
                    $('select[name="truck"]')
                        .append($('<option>').text(value).val(value))
                    ;
                })
        }}

    });
});

$(document).on('click', '[name="getDriversList"]', function () {

    $.ajax({
        type: "GET",
        url: "getSuitableDrivers",
        data: {"route_length": $("#route_length").val(),
               "city" : $("#start_waypoint").val() },
        dataType: 'json',
        success: function(data){
            $('select[name="driver"]')
                .empty()
                .append('<option selected="selected" value="">Select driver</option>');
            if(data.length !==0){
                $.each(data, function(index, value) {
                    $('select[name="driver"]')
                        .append($('<option>').text(value).val(value))
                    ;
                });}else {
                $('select[name="driver"]')
                    .empty()
                    .append('<option selected="selected" value="">No available drivers for this order!</option>')
            }
        }
    });
});