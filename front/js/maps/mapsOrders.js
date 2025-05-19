ymaps.ready(init);

function init() {
    let coords = {
        "x1": sessionStorage.getItem("x1"),
        "y1": sessionStorage.getItem("y1")
    }
    let myPlacemark,
        map = new ymaps.Map('yandexmap', {
            center: [51.529635, 45.980160],
            zoom: 14
        }, {
            searchControlProvider: 'yandex#search'
        });
    fetch('http://localhost:8080/taxi/driver/get-order/'+ sessionStorage.getItem("tarifDriver"), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(coords)
    })
        .then(response => {
            if (!response.ok) {
                console.log("ошиба");
            }
            return response.json();
        })
        .then(result => {
            const data = [
                {
                    name: 'Ваше местоположение',
                    id: 0,
                    coordinates: [sessionStorage.getItem("x1"), sessionStorage.getItem("y1")]
                }
            ];
            if (result[0]!==undefined){
                data.push({
                    name: 'Заказ 1',
                    id: result[0].id,
                    coordinates: [result[0].addressstartx, result[0].addressstarty]
                })
                if (result[1]!==undefined){
                    data.push({
                        name: 'Заказ 2',
                        id: result[1].id,
                        coordinates: [result[1].addressstartx, result[1].addressstarty]
                    })
                    if (result[2]!==undefined){
                        data.push({
                            name: 'Заказ 3',
                            id: result[2].id,
                            coordinates: [result[2].addressstartx, result[2].addressstarty]
                        })

                    }
                }
            }
            map.geoObjects.add(new ymaps.Placemark([data[0]['coordinates'][0], data[0]['coordinates'][1]], {
                iconCaption: data[0]['name']
            }, {
                preset: 'islands#redIcon'
            }));
            for (var i = 1; i < data.length; i++) {
                map.geoObjects.add(new ymaps.Placemark([data[i]['coordinates'][0], data[i]['coordinates'][1]], {
                    iconCaption: data[i]['name']
                }));
            }
        })

}