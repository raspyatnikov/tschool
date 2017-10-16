$(document).ready(function () {
$("#order-form").validate({

    rules: {
        name: {
            required: true
        },
        order_number: {
            required: true
        },
        surname: {
            required: true
        },
        email: {
            required: true,
            email: true
        },
        password: {
            required: true
        },
        phone: {
            required: true
        },
        employeeId: {
            required: true
        },
        currentCity: {
            required: true
        },
        truck:{
            required: true
        },
        driver:{
            required: true
        }
    },

    // Messages for form validation
    messages: {
        name: {
            required: 'Please driver`s name'
        },
        order_number: {
            required: 'Please driver`s name'
        },
        surname: {
            required: 'Please driver`s surname'
        },
        email: {
            required: 'Please enter email address',
            email: 'Please enter a VALID email address'
        },
        password: {
            required: 'Please enter password'
        },
        employeeId: {
            required: 'Please enter driver`s employee ID'
        },
        currentCity: {
            required: 'Please select driver`s current city'
        },
        truck: {
            required: 'Please select truck for order'
        },
        driver: {
            required: 'Please select drivers for order'
        }
    }

})

});