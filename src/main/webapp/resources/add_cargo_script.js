$(function() {
    var max_fields      = 10;
    var wrapper         = $(".cargoes_list");
    var add_button      = $('a[name = "add-cargoe-button"]');

    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        if(x < max_fields){
            x++;
            $(wrapper).append('<div class="row"><div class = "cargoes">\n' +
                '                                        <section class="col col-3">\n' +
                '                                            <label class="input">\n' +
                '                                                <input type="text" name="title" placeholder="Cargo title">\n' +
                '                                            </label>\n' +
                '                                        </section>\n' +
                '\n' +
                '                                            <section class="col col-2">\n' +
                '                                                <label class="input">\n' +
                '                                                    <input type="text" name="weight" placeholder="Weight (kg)">\n' +
                '                                                </label>\n' +
                '                                            </section>\n' +
                '\n' +
                '\n' +
                '                                        <section class="col col-3"> <label class="select">\n' +
                '                                            <select name="order_origin">\n' +
                '                                                <option value="">Origin city</option>\n' +
                '                                                <c:forEach items="${cityList}" var="city">\n' +
                '                                                    <option  value=${city.name}>${city.name}</option>\n' +
                '                                                </c:forEach>                        </select></label></section>\n' +
                '\n' +
                '                                        <section class="col col-3"> <label class="select">\n' +
                '                                            <select name="order_destination">\n' +
                '                                                <option value="">Destination city</option>\n' +
                '                                                <c:forEach items="${cityList}" var="city">\n' +
                '                                                    <option  value=${city.name}>${city.name}</option>\n' +
                '                                                </c:forEach>                        </select></label></section></div>\n' +
                '                                        </div>'); //add input box
        }
        else
        {
            alert('You Reached the limits')
        }
    });

    $(wrapper).on("click",".delete", function(e){
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })


});