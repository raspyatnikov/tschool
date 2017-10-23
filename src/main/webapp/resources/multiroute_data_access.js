$(document).ready(function () {

    ymaps.ready(init);

$('select[name = "waypoint_city"]').change(function () {
        cities = $("select[name='waypoint_city']")
            .map(function () {
                return $(this).val();
            }).get();
        myMap.geoObjects.removeAll();
        var multiRouteModel = new ymaps.multiRouter.MultiRouteModel(cities
            , {
                wayPointDraggable: false,
                boundsAutoApply: true,
                results: 1
            });

         var multiRoute = new ymaps.multiRouter.MultiRoute(multiRouteModel, {

            wayPointDraggable: false,
            boundsAutoApply: true, results: 1
        });

        myMap.geoObjects.add(multiRoute);
        multiRoute.model.events
        .add("requestsuccess", function (event) {
            var routes = event.get("target").getRoutes();
            $("#route_length").val( routes[0].properties.get("distance").value/1000);

        });

});
    var myMap;
    var cities = [];
    function init() {

            // Создаём выпадающий список для выбора типа маршрута.
            // routeTypeSelector = new ymaps.control.ListBox({
            //     data: {
            //         content: 'Как добраться'
            //     },
            //     items: [
            //         new ymaps.control.ListBoxItem({data: {content: "Авто"}, state: {selected: true}}),
            //         new ymaps.control.ListBoxItem({data: {content: "Общественным транспортом"}}),
            //         new ymaps.control.ListBoxItem({data: {content: "Пешком"}})
            //     ],
            //     options: {
            //         itemSelectOnClick: false
            //     }
        //     // }),
        //     // Получаем прямые ссылки на пункты списка.
        //     autoRouteItem = routeTypeSelector.get(0),
        //     masstransitRouteItem = routeTypeSelector.get(1),
        //     pedestrianRouteItem = routeTypeSelector.get(2);
        //
        // // Подписываемся на события нажатия на пункты выпадающего списка.
        // autoRouteItem.events.add('click', function (e) {
        //     changeRoutingMode('auto', e.get('target'));
        // });
        // masstransitRouteItem.events.add('click', function (e) {
        //     changeRoutingMode('masstransit', e.get('target'));
        // });
        // pedestrianRouteItem.events.add('click', function (e) {
        //     changeRoutingMode('pedestrian', e.get('target'));
        // });


        // Создаем карту с добавленной на нее кнопкой.
        myMap = new ymaps.Map('map', {
                center: [55.750625, 37.626],
                controls: [],
                zoom: 7
            });

            // Создаем на основе существующей модели мультимаршрут.

        // function changeRoutingMode(routingMode, targetItem) {
        //     multiRouteModel.setParams({routingMode: routingMode}, true);
        //
        //     // Отменяем выбор элементов.
        //     autoRouteItem.deselect();
        //     masstransitRouteItem.deselect();
        //     pedestrianRouteItem.deselect();
        //
        //     // Выбираем элемент и закрываем список.
        //     targetItem.select();
        //     routeTypeSelector.collapse();
        // }
    }

});
