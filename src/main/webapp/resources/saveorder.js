$(document).on('click', '[name = "saveOrder"]', function () {
    if($("#order-form").valid()){
    var pst = {};
    pst.order_number = jQuery('[name="order_number"]').val();
    pst.truck_licennce_plate = jQuery('[name="truck"]').val();
    pst.driver = jQuery('[name="driver"]').val();
    pst.waypoints = [];
    pst.cargoes = [];
    $('.cities').each(function () {
        pst.waypoints.push({
            city_id: jQuery(this).find('[name="waypoint_city"]').val()
        })
    });

    $('.cargoes').each(function () {
        pst.cargoes.push({
            cargo_weight: jQuery(this).find('[name="weight"]').val(),
            cargo_title: jQuery(this).find('[name="title"]').val(),
            cargo_origin_city:jQuery(this).find('[name="order_origin"]').val(),
            cargo_destination_city: jQuery(this).find('[name="order_destination"]').val()
        })
    });

    console.log(pst);
    var json = JSON.stringify(pst);
    $.post('createOrder', {json: json}, function (data) { $("#order-form").after(data);
    }
    )}
});